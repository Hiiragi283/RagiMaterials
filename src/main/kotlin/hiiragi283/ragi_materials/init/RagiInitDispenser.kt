package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.block.BlockForgeFurnace
import hiiragi283.ragi_materials.block.ForgeFurnaceHelper
import net.minecraft.block.BlockDispenser
import net.minecraft.dispenser.BehaviorDefaultDispenseItem
import net.minecraft.dispenser.IBlockSource
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

object RagiInitDispenser {

    //ディスペンサーに機能を登録するメソッド
    fun registerDispense() {
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Items.COAL, DispenseForgeFurnace()) //燃料を投入
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(RagiInit.ItemToolBellow, DispenseForgeFurnace()) //火力UP
    }

    //Forge Furnace用
    class DispenseForgeFurnace : BehaviorDefaultDispenseItem() {
        override fun dispenseStack(source: IBlockSource, stack: ItemStack): ItemStack {
            //各値の取得
            val world = source.world
            val pos = source.blockPos.offset(source.blockState.getValue(BlockDispenser.FACING))
            val state = world.getBlockState(pos)
            val block = state.block
            //blockがForge Furnaceの場合
            return if (block is BlockForgeFurnace) {
                when (stack.item) {
                    Items.COAL -> ForgeFurnaceHelper.setFuel(world, pos, state, stack) //燃料を投入
                    RagiInit.ItemToolBellow -> ForgeFurnaceHelper.setBoosted(world, pos, state, stack) //火力UP
                    else -> super.dispenseStack(source, stack)
                }
            } else super.dispenseStack(source, stack)
        }
    }
}