package hiiragi283.material.util

import net.minecraft.nbt.CompoundTag
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

data class ItemStackWrapper(val item: Item?, val count: Int, val tag: CompoundTag?) {

    constructor(stack: ItemStack, count: Int = stack.count) : this(stack.item, count, stack.tag)

    fun toItemStack(): ItemStack = item?.let { item -> ItemStack(item, count).also { it.tag = tag } } ?: ItemStack.EMPTY

    fun moreThan(other: ItemStackWrapper): Boolean = copy(count = 1) == other.copy(count = 1) && count >= other.count

}