package hiiragi283.ragi_materials.init

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack

class RagiCreativeTabs {

    class Materials : CreativeTabs("ragi_materials.materials") {

        override fun getTabIconItem(): ItemStack {
            return ItemStack(RagiInit.ItemIngot, 1, 26)
        }
    }

    class Blocks : CreativeTabs("ragi_materials.blocks") {

        override fun getTabIconItem(): ItemStack {
            return ItemStack(RagiInit.ItemBlockForgeFurnace)
        }
    }
}