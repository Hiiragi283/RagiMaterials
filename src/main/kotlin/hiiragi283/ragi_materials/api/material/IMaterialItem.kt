package hiiragi283.ragi_materials.api.material

import hiiragi283.ragi_materials.util.RagiColor
import net.minecraft.item.ItemStack

interface IMaterialItem {

    fun getColor(stack: ItemStack, tintIndex: Int) = if (tintIndex == 0) getMaterial(stack).color else RagiColor.WHITE

    fun getMaterial(stack: ItemStack) = RagiMaterial.EMPTY

    fun setMaterial(stack: ItemStack, material: RagiMaterial) = stack

}