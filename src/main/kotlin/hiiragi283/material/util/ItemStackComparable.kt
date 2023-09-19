package hiiragi283.material.util

import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

fun ItemStack.toComparable(
    count: Int = this.count,
    meta: Int = this.metadata
) = ItemStackComparable(this.item, count, meta, this.tagCompound)

data class ItemStackComparable(
    val item: Item,
    var count: Int,
    var meta: Int,
    var tag: NBTTagCompound? = null
) {

    fun toItemStack(
        count: Int = this.count,
        meta: Int = this.meta,
    ) = ItemStack(item, count, meta).also { stack: ItemStack -> stack.tagCompound = tag }

}