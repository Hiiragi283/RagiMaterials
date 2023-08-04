package hiiragi283.core

import hiiragi283.api.material.MaterialElements
import hiiragi283.material.RMItems
import hiiragi283.material.RMReference
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

object RMCreativeTabs {

    val BOTTLE by lazy {
        object : CreativeTabs("${RMReference.MOD_ID}.bottle") {
        override fun createIcon(): ItemStack =
            ItemStack(RMItems.MATERIAL_BOTTLE, 1, MaterialElements.HYDROGEN.index)
        }
    }

    val MATERIAL_BLOCK by lazy {
        object : CreativeTabs("${RMReference.MOD_ID}.material_block") {
            override fun createIcon(): ItemStack = ItemStack(Blocks.IRON_BLOCK)
        }
    }

    val MATERIAL_ITEM by lazy {
        object : CreativeTabs("${RMReference.MOD_ID}.material_item") {
            override fun createIcon(): ItemStack = ItemStack(Items.IRON_INGOT)
        }
    }

}