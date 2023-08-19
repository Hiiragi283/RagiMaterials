package hiiragi283.api.item

import hiiragi283.api.material.MaterialStack
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

interface ICastItem {

    fun getCastItem(): Item

    fun getMaterialAmount(): Int

    fun getResult(materialStack: MaterialStack): ItemStack

    fun onCast(stack: ItemStack) {}

}