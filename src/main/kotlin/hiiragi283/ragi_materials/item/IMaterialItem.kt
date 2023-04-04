package hiiragi283.ragi_materials.item

import hiiragi283.ragi_materials.material.RagiMaterial
import net.minecraft.item.ItemStack

interface IMaterialItem {

    fun getColor(stack: ItemStack) = getMaterial(stack).color

    fun getMaterial(stack: ItemStack) = RagiMaterial.EMPTY

    fun setMaterial(stack: ItemStack, material: RagiMaterial) = stack

}