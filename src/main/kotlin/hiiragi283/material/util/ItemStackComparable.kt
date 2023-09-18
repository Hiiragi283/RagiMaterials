package hiiragi283.material.util

import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

fun ItemStack.toComparable() = ItemStackComparable(this.item, this.count, this.metadata, this.tagCompound)

data class ItemStackComparable(
    val item: Item,
    var count: Int,
    var meta: Int,
    var tag: NBTTagCompound? = null
) {

    fun toItemStack() = ItemStack(item, count, meta).also { stack: ItemStack -> stack.tagCompound = tag }

}