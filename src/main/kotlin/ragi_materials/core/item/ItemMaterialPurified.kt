package ragi_materials.core.item

import net.minecraft.item.ItemStack
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.core.material.part.PartRegistry
import ragi_materials.core.util.ColorUtil

object ItemMaterialPurified : ItemMaterial(PartRegistry.PURIFIED) {

    //    IMaterialItem    //

    override fun getColor(stack: ItemStack, tintIndex: Int) = when (tintIndex) {
        0 -> ColorUtil.mixColor(getMaterial(stack).color to 1, MaterialRegistry.STONE.color to 1)
        1 -> getMaterial(stack).color
        else -> super.getColor(stack, tintIndex)
    }

}