package ragi_materials.core.util.wrapper

import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

data class ItemStackWrapper(val item: Item?, val count: Int, val meta: Int, val tag: NBTTagCompound?) {

    constructor(stack: ItemStack, count: Int = stack.count) : this(stack.item, count, stack.metadata, stack.tagCompound)

    fun toItemStack(): ItemStack = item?.let { item -> ItemStack(item, count, meta).also { it.tagCompound = tag } }
            ?: ItemStack.EMPTY

}