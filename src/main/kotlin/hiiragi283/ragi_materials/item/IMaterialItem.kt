package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.material.builder.MaterialBuilder
import net.minecraft.item.ItemStack

interface IMaterialItem {

    fun getMaterial(stack: ItemStack): MaterialBuilder

    fun setMaterial(stack: ItemStack, material: MaterialBuilder): ItemStack
}