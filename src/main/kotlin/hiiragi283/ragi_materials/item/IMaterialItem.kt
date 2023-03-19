package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.material.RagiMaterial
import net.minecraft.item.ItemStack

interface IMaterialItem {

    fun getMaterial(stack: ItemStack): RagiMaterial

    fun setMaterial(stack: ItemStack, material: RagiMaterial): ItemStack
}