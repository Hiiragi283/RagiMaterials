package hiiragi283.material.tile

import hiiragi283.api.capability.HiiragiCapability
import hiiragi283.api.capability.HiiragiCapabilityProvider
import hiiragi283.api.capability.IOType
import hiiragi283.api.capability.material.IMaterialHandler
import hiiragi283.api.capability.material.MaterialHandler
import hiiragi283.api.item.ICastItem
import hiiragi283.api.material.MaterialStack
import hiiragi283.api.part.HiiragiPart
import hiiragi283.api.part.PartRegistry
import hiiragi283.api.registry.HiiragiRegistry
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
        if (!world.isRemote) {
            val stack = player.getHeldItem(hand)
            //利き手に持っているItemStackがEMPTYでない -> レシピを実行
            if (!stack.isEmpty) {
                val item: Item = stack.item
                //ItemStackのItemがICastItemを実装している -> 鋳造レシピを実行
                if (item is ICastItem) {
                    val materialStack: MaterialStack = materialHandler.getMaterialStack()
                    val result: ItemStack = item.getResult(materialStack)
                    if (materialHandler.canExtract(materialStack) && !result.isEmpty) {
                        materialHandler.extractMaterial(materialStack, false)
                        stack.itemDamage += 1
                        dropItemAtPlayer(player, result)
                        succeeded(this, player)
                        playSound(this, SoundEvents.BLOCK_FIRE_EXTINGUISH)
                    }
                }
                //実装していない -> ItemStackから溶融レシピを取得
                else {
                    val materialStack =
                        PartRegistry.getParts(stack).getOrElse(0) { HiiragiPart.EMPTY }.toMaterialStack()
                    //融点が有効な場合
                    if (materialStack.material.hasTempMelt()) {
                        val tempMelt: Int = materialStack.material.tempMelt
                        //現在の温度が要求温度以上 ->
                        if (getHeat(world, pos) >= tempMelt) {
                            //tankにレシピの出力を搬入できる ->溶融レシピを実行
                            if (materialHandler.canInsert(materialStack)) {
                                stack.shrink(1)
                                materialHandler.insertMaterial(materialStack, false)
                                succeeded(this, player)
                                playSound(this, SoundEvents.ITEM_BUCKET_FILL_LAVA)
                            }
                            //tankに搬入できない -> 警告
                            else {
                                player.sendMessage(TextComponentTranslation("error.ragi_materials.crucible.cannot_fill"))
                                playSound(this, SoundEvents.ENTITY_VILLAGER_NO)
                            }
                        }
                        //温度が足りていない -> 警告
                        else {
                            player.sendMessage(
                                TextComponentTranslation(
                                    "error.ragi_materials.crucible.more_heat",
                                    tempMelt
                                )
                            )
                            playSound(this, SoundEvents.ENTITY_VILLAGER_NO)
                        }
                    }
                    //融点が有効でない
                    else {

                    }
                }
            }
            //EMPTY -> 現在の温度を表示する
            else {
                player.sendMessage(
                    TextComponentTranslation("info.ragi_materials.crucible.temperature", getHeat(world, pos))
                )
                playSound(this, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP)
            }
            return true
        }
        return false
    }

    //    HiiragiProvider    //

    private lateinit var materialHandler: IMaterialHandler

    override fun createHandler(): HiiragiCapabilityProvider<IMaterialHandler> {
        materialHandler = MaterialHandler(capacity = 144 * 9).setIOType(IOType.GENERAL)
        return HiiragiCapabilityProvider(HiiragiCapability.MATERIAL, materialHandler)
    }

}