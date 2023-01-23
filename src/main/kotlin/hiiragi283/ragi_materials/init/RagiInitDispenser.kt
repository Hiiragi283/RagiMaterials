package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.blocks.BlockForgeFurnace
import net.minecraft.block.BlockDispenser
import net.minecraft.dispenser.BehaviorDefaultDispenseItem
import net.minecraft.dispenser.IBlockSource
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

object RagiInitDispenser : BehaviorDefaultDispenseItem() {

    /*
      Thanks to defeatedcrow!
      Source: https://github.com/defeatedcrow/HarvestWithDispenser/blob/1.12.2_master/java/defeatedcrow/dispenser/DispenserHervestDC.java
              https://github.com/defeatedcrow/HarvestWithDispenser/blob/1.12.2_master/java/defeatedcrow/dispenser/DispenseBonemeal.java
    */

    //ディスペンサーに機能を登録するメソッド
    fun registerDispense() {
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Items.COAL, this) //燃料を投入
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Items.FIRE_CHARGE, this) //着火
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Items.FLINT_AND_STEEL, this) //着火
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(RagiInit.ItemToolBellow, this) //火力UP
    }

    override fun dispenseStack(source: IBlockSource, stack: ItemStack): ItemStack {
        //各値の取得
        val world = source.world
        val pos = source.blockPos.offset(source.blockState.getValue(BlockDispenser.FACING))
        val state = world.getBlockState(pos)
        val block = state.block
        //blockがForge Furnaceの場合
        return if (block is BlockForgeFurnace) {
            when (stack.item) {
                Items.COAL -> {
                    BlockForgeFurnace.ForgeFurnaceHelper.setFuel(world, pos, state, stack) //燃料を投入
                    stack
                }
                Items.FIRE_CHARGE -> {
                    BlockForgeFurnace.ForgeFurnaceHelper.setFireItem(world, pos, state, stack) //着火
                    stack
                }
                Items.FLINT_AND_STEEL -> {
                    BlockForgeFurnace.ForgeFurnaceHelper.setFireTool(world, pos, state, stack) //着火
                    stack
                }
                RagiInit.ItemToolBellow -> {
                    BlockForgeFurnace.ForgeFurnaceHelper.setBlasting(world, pos, state, stack) //火力UP
                    stack
                }
                else -> super.dispenseStack(source, stack)
            }
        } else super.dispenseStack(source, stack)
    }

}