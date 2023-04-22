package ragi_materials.core.item

import net.minecraft.item.ItemStack
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.core.material.part.PartRegistry

class ItemMaterialOre: ItemMaterial(PartRegistry.ORE) {

    //    IMaterialItem    //

    override fun getColor(stack: ItemStack, tintIndex: Int) = if (tintIndex == 0) MaterialRegistry.STONE.color else getMaterial(stack).color

}