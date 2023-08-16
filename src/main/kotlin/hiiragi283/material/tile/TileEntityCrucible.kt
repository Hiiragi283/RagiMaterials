package hiiragi283.material.tile

import hiiragi283.api.capability.HiiragiCapabilityProvider
import hiiragi283.api.capability.IOType
import hiiragi283.api.capability.fluid.HiiragiFluidTank
import hiiragi283.api.capability.fluid.HiiragiFluidTankWrapper
import hiiragi283.api.item.ICastItem
import hiiragi283.api.recipe.CrucibleRecipe
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
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.IFluidBlock
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.fluids.capability.IFluidHandler

class TileEntityCrucible : HiiragiTileEntity(), HiiragiProvider.Tank {

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
                    val amount: Int = item.getFluidAmount(stack)
                    val result: ItemStack = item.getResult(stack, tankCrucible.fluid)
                    if (tankCrucible.fluidAmount >= amount && !result.isEmpty) {
                        tankCrucible.drain(amount, true)
                        stack.itemDamage += 1
                        dropItemAtPlayer(player, result)
                        succeeded(this, player)
                        playSound(this, SoundEvents.BLOCK_FIRE_EXTINGUISH)
                    }
                }
                //実装していない -> レジストリから溶融レシピを取得
                else {
                    val recipe: CrucibleRecipe? = HiiragiRegistry.CRUCIBLE.valuesCollection
                        .firstOrNull { it.matches(stack) }
                    //レシピが存在する ->
                    if (recipe !== null) {
                        //現在の温度が要求温度以上 ->
                        if (getHeat(world, pos) >= recipe.tempMin) {
                            val output: FluidStack = recipe.getOutput(stack)
                            //tankにレシピの出力を搬入できる ->溶融レシピを実行
                            if (tank.fill(output, false) == output.amount) {
                                stack.shrink(1)
                                tank.fill(output, true)
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
                                    recipe.tempMin
                                )
                            )
                            playSound(this, SoundEvents.ENTITY_VILLAGER_NO)
                        }
                    }
                    //レシピが見つからない -> 警告
                    else {
                        player.sendMessage(TextComponentTranslation("error.ragi_materials.crucible.no_recipe"))
                        playSound(this, SoundEvents.ENTITY_VILLAGER_NO)
                    }
                }
            }
            //EMPTYの -> 現在の温度を表示する
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

    private lateinit var tankCrucible: HiiragiFluidTank

    /*override fun createInventory(): HiiragiCapabilityProvider<IItemHandler> {
        invCrucible = object : HiiragiItemHandler(1, this) {

            override fun getSlotLimit(slot: Int): Int = 1

            override fun onContentsChanged(slot: Int) {
                val stack = this.getStackInSlot(0)
                if (stack.isEmpty) return
                val item = stack.item
                if (item is ICastItem) {
                    val inventory = this@TileEntityCrucible.invCrucible
                    val tank = this@TileEntityCrucible.tankCrucible
                    val amount = item.getFluidAmount(stack)
                    val result = item.getResult(stack, tank.fluid)
                    if (tank.fluidAmount >= amount && !result.isEmpty) {
                        inventory.extractItem(0, 1, false)
                        inventory.insertItem(0, result, false)
                        tank.drain(amount, true)
                    }
                } else {
                    HiiragiRegistry.CRUCIBLE.valuesCollection
                        .firstOrNull { it.matches(invCrucible, tankCrucible) }
                        ?.process(invCrucible, tankCrucible)
                }
            }

        }.setIOType(IOType.GENERAL)
        inventory = HiiragiItemHandlerWrapper(invCrucible)
        return HiiragiCapabilityProvider(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, inventory)
    }*/

    override fun createTank(): HiiragiCapabilityProvider<IFluidHandler> {
        tankCrucible = HiiragiFluidTank(144 * 9).setIOType(IOType.GENERAL)
        tank = HiiragiFluidTankWrapper(tankCrucible)
        return HiiragiCapabilityProvider(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, tank)
    }

}