package hiiragi283.material.util.wrapper

import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

data class ItemStackWrapper(val item: Item?, val count: Int, val meta: Int, val tag: NBTTagCompound?) {

    constructor(stack: ItemStack, count: Int = stack.count) : this(stack.item, count, stack.metadata, stack.tagCompound)

    fun toItemStack(): ItemStack = item?.let { item -> ItemStack(item, count, meta).also { it.tagCompound = tag } }
        ?: ItemStack.EMPTY

    fun moreThan(other: ItemStackWrapper): Boolean = copy(count = 1) == other.copy(count = 1) && count >= other.count

}