package hiiragi283.material.api.material

import hiiragi283.material.api.registry.HiiragiRegistries
import net.minecraft.item.ItemStack

interface IHiiragiMaterialItem {

    fun getMaterial(stack: ItemStack): HiiragiMaterial? = HiiragiRegistries.MATERIAL_INDEX.getValue(stack.metadata)

}