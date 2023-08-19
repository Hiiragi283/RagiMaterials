package hiiragi283.material.tile

import hiiragi283.api.HiiragiRegistry
import hiiragi283.api.capability.HiiragiCapability
import hiiragi283.api.capability.HiiragiCapabilityProvider
import hiiragi283.api.capability.IOType
import hiiragi283.api.capability.material.IMaterialHandler
import hiiragi283.api.capability.material.MaterialHandler
import hiiragi283.api.item.ICastItem
import hiiragi283.api.material.MaterialStack
import hiiragi283.api.part.HiiragiPart
import hiiragi283.api.tileentity.HiiragiProvider
import hiiragi283.api.tileentity.HiiragiTileEntity
import hiiragi283.material.util.dropItemAtPlayer
import hiiragi283.material.util.playSound
import hiiragi283.material.util.succeeded
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.SoundEvents
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.world.World
import net.minecraftforge.fluids.IFluidBlock

class TileEntityCrucible : HiiragiTileEntity(), HiiragiProvider.Material {

    //液体ブロックの温度を優先して取得する
    private fun getHeat(world: World, pos: BlockPos): Int {
        val state: IBlockState = world.getBlockState(pos.down())
        val block: Block = state.block
        return if (block is IFluidBlock) block.fluid.temperature else HiiragiRegistry.getHeat(state)
    }

    //    RCTileEntity    //

    override fun onTileActivated(
        world: World,
        pos: BlockPos,
        player: EntityPlayer,
        hand: EnumHand,
        facing: EnumFacing
    ): Boolean {
        return if (!world.isRemote) {
            val stack: ItemStack = player.getHeldItem(hand)
            if (stack.isEmpty) printCurrentTemp(player)
            else {
                val item: Item = stack.item
                if (item is ICastItem) processCast(player, stack, item)
                else processMelt(player, stack)
            }
            true
        } else false
    }

    private fun printCurrentTemp(player: EntityPlayer) {
        player.sendMessage(
            TextComponentTranslation(
                "info.ragi_materials.crucible.temperature",
                getHeat(world, pos)
            )
        )
        playSound(this, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP)
    }

    private fun processCast(player: EntityPlayer, stack: ItemStack, item: ICastItem) {
        val materialStack: MaterialStack = materialHandler.getMaterialStack()
        val required: Int = item.getMaterialAmount()
        val result: ItemStack = item.getResult(materialStack)
        //handler内の量 < 要求量 -> 終了
        if (materialHandler.getMaterialAmount() < required) {
            player.sendMessage(TextComponentTranslation("error.ragi_materials.crucible.cannot_cast"))
            playSound(this, SoundEvents.ENTITY_VILLAGER_NO)
            return
        }
        //result == ItemStack.EMPTy -> 終了
        if (result.isEmpty) {
            player.sendMessage(TextComponentTranslation("error.ragi_materials.crucible.cannot_cast"))
            playSound(this, SoundEvents.ENTITY_VILLAGER_NO)
            return
        }
        //実行する
        else {
            materialHandler.extractMaterial(materialStack.copy(amount = required), false)
            item.onCast(stack)
            dropItemAtPlayer(player, result)
            succeeded(this, player)
            playSound(this, SoundEvents.BLOCK_FIRE_EXTINGUISH)
        }
    }

    private fun processMelt(player: EntityPlayer, stack: ItemStack) {
        val materialStack = HiiragiRegistry.getParts(stack)
            .getOrElse(0) { HiiragiPart.EMPTY }
            .toMaterialStack()
        //融点が有効でない -> 終了
        if (!materialStack.material.hasTempMelt()) {
            player.sendMessage(TextComponentTranslation("error.ragi_materials.crucible.no_recipe"))
            playSound(this, SoundEvents.ENTITY_VILLAGER_NO)
            return
        }
        val tempMelt: Int = materialStack.material.tempMelt
        //温度が不足している -> 終了
        if (getHeat(world, pos) < tempMelt) {
            player.sendMessage(
                TextComponentTranslation(
                    "error.ragi_materials.crucible.more_heat",
                    tempMelt
                )
            )
            playSound(this, SoundEvents.ENTITY_VILLAGER_NO)
            return
        }
        //搬入できない -> 終了
        if (!materialHandler.canInsert(materialStack)) {
            player.sendMessage(TextComponentTranslation("error.ragi_materials.crucible.cannot_fill"))
            playSound(this, SoundEvents.ENTITY_VILLAGER_NO)
            return
        }
        //実行する
        else {
            stack.shrink(1)
            materialHandler.insertMaterial(materialStack, false)
            succeeded(this, player)
            playSound(this, SoundEvents.ITEM_BUCKET_FILL_LAVA)
        }
    }

    //    HiiragiProvider    //

    private lateinit var materialHandler: IMaterialHandler

    override fun createHandler(): HiiragiCapabilityProvider<IMaterialHandler> {
        materialHandler = MaterialHandler(capacity = 144 * 9).setIOType(IOType.GENERAL)
        return HiiragiCapabilityProvider(HiiragiCapability.MATERIAL, materialHandler)
    }

}