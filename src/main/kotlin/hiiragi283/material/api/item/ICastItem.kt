package hiiragi283.material.api.item

import hiiragi283.material.api.material.MaterialStack
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

interface ICastItem {

    fun getCastItem(): Item

    fun getMaterialAmount(): Int

    fun getResult(materialStack: MaterialStack): ItemStack

    fun onCast(stack: ItemStack) {}

}