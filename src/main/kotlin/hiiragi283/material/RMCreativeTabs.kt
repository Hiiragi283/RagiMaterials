package hiiragi283.material

import hiiragi283.material.api.material.MaterialElements
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

object RMCreativeTabs {

    @JvmField
    val BOTTLE = object : CreativeTabs("${RMReference.MOD_ID}.bottle") {
        override fun createIcon(): ItemStack =
            ItemStack(RMItems.MATERIAL_BOTTLE, 1, MaterialElements.HYDROGEN.index)
    }

    @JvmField
    val MATERIAL_BLOCK = object : CreativeTabs("${RMReference.MOD_ID}.material_block") {
        override fun createIcon(): ItemStack = ItemStack(Blocks.IRON_BLOCK)
    }

    @JvmField
    val MATERIAL_ITEM = object : CreativeTabs("${RMReference.MOD_ID}.material_item") {
        override fun createIcon(): ItemStack = ItemStack(Items.IRON_INGOT)
    }

}