package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.blocks.BlockForgeFurnace
import hiiragi283.ragi_materials.util.RagiUtils
import net.minecraft.block.BlockCauldron
import net.minecraft.block.BlockDispenser
import net.minecraft.dispenser.BehaviorDefaultDispenseItem
import net.minecraft.dispenser.IBlockSource
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

object RagiInitDispenser {

    /*
      Thanks to defeatedcrow!
      Source: https://github.com/defeatedcrow/HarvestWithDispenser/blob/1.12.2_master/java/defeatedcrow/dispenser/DispenserHervestDC.java
              https://github.com/defeatedcrow/HarvestWithDispenser/blob/1.12.2_master/java/defeatedcrow/dispenser/DispenseBonemeal.java
    */

    //ディスペンサーに機能を登録するメソッド
    fun registerDispense() {
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Items.WATER_BUCKET, DispenseCauldron()) //水バケツで水を搬入
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Items.BUCKET, DispenseCauldron()) //水バケツで水を搬出

        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Items.COAL, DispenseForgeFurnace()) //燃料を投入
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Items.FIRE_CHARGE, DispenseForgeFurnace()) //着火
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Items.FLINT_AND_STEEL, DispenseForgeFurnace()) //着火
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

    //大釜から水を出し入れする用
    class DispenseCauldron : BehaviorDefaultDispenseItem() {
        override fun dispenseStack(source: IBlockSource, stack: ItemStack): ItemStack {
            //各値の取得
            val world = source.world
            val pos = source.blockPos.offset(source.blockState.getValue(BlockDispenser.FACING))
            val state = world.getBlockState(pos)
            val block = state.block
            //blockが大釜の場合
            return if (block is BlockCauldron) {
                //stackが水入りバケツの場合
                if (stack.item == Items.WATER_BUCKET) {
                    //大釜が空の場合
                    if (block.getMetaFromState(state) == 0) {
                        world.setBlockState(pos, RagiUtils.getState(block, 3)) //大釜のblockstateを上書き
                        ItemStack(Items.BUCKET) //空のバケツを返す
                    } else super.dispenseStack(source, stack)
                }
                //stackが水入りバケツの場合
                else if (stack.item == Items.BUCKET) {
                    //大釜が満タンの場合
                    if (block.getMetaFromState(state) == 3) {
                        world.setBlockState(pos, RagiUtils.getState(block, 0)) //大釜のblockstateを上書き
                        ItemStack(Items.WATER_BUCKET) //水入りバケツを返す
                    } else super.dispenseStack(source, stack)
                } else super.dispenseStack(source, stack)
            } else super.dispenseStack(source, stack)
        }
    }

}