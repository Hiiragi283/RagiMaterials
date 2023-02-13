package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.block.BlockForgeFurnace
import hiiragi283.ragi_materials.block.BlockLitForgeFurnace
import hiiragi283.ragi_materials.block.ForgeFurnaceHelper
import net.minecraft.block.BlockDispenser
import net.minecraft.dispenser.BehaviorDefaultDispenseItem
import net.minecraft.dispenser.IBlockSource
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

object RagiInitDispenser {

    //ディスペンサーに機能を登録するメソッド
    fun registerDispense() {
        //燃料を投入
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Items.COAL, object : BehaviorDefaultDispenseItem() {
            override fun dispenseStack(source: IBlockSource, stack: ItemStack): ItemStack {
                //各値の取得
                val world = source.world
                val pos = source.blockPos.offset(source.blockState.getValue(BlockDispenser.FACING))
                val state = world.getBlockState(pos)
                val block = state.block
                //blockがForge Furnaceの場合
                return if (block is BlockForgeFurnace && stack.item == Items.COAL) {
                    ForgeFurnaceHelper.setFuel(world, pos, state, stack) //燃料を投入
                } else if(block is BlockLitForgeFurnace) stack else super.dispenseStack(source, stack)
            }
        })
    }
}