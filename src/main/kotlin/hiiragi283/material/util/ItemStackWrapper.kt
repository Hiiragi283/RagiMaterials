package hiiragi283.material.util

import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound

data class ItemStackWrapper(val item: Item?, val count: Int, val tag: NbtCompound?) {

    constructor(stack: ItemStack, count: Int = stack.count) : this(stack.item, count, stack.nbt)

    fun toItemStack(): ItemStack = item?.let { item -> ItemStack(item, count).also { it.nbt = tag } } ?: ItemStack.EMPTY

    fun moreThan(other: ItemStackWrapper) = copy(count = 1) == other.copy(count = 1) && count >= other.count

}