package hiiragi283.material.item


import hiiragi283.material.api.item.HiiragiItem
import hiiragi283.material.api.item.ICastItem
import hiiragi283.material.api.item.MaterialItem
import hiiragi283.material.api.material.MaterialStack
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

class ItemCast(val item: MaterialItem) : HiiragiItem("cast_${item.shape.name}", 0), ICastItem {

    init {
        maxDamage = 63
        maxStackSize = 1
    }

    //    ICastItem    //

    override fun getCastItem(): Item = this

    override fun getMaterialAmount(): Int = item.shape.scale

    override fun getResult(materialStack: MaterialStack): ItemStack = ItemStack.EMPTY

    override fun onCast(stack: ItemStack) {
        stack.itemDamage += 1
    }

}