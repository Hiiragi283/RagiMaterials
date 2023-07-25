package hiiragi283.material

import hiiragi283.material.api.material.MaterialElements
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

object RMCreativeTabs {

    @JvmField
    val BOTTLE = object : CreativeTabs("${RagiMaterials.MODID}.bottle") {
        override fun createIcon(): ItemStack =
            ItemStack(RMItems.MATERIAL_BOTTLE, 1, MaterialElements.HYDROGEN.index)
    }

    @JvmField
    val MATERIAL = object : CreativeTabs("${RagiMaterials.MODID}.material") {
        override fun createIcon(): ItemStack = ItemStack(Items.IRON_INGOT)
    }

}