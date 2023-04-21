package hiiragi283.ragi_materials.api.wrapper

import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

data class ItemStackWrapper(val item: Item?, val count: Int, val meta: Int, val tag: NBTTagCompound?) {

    constructor(stack: ItemStack) : this(stack.item, stack.count, stack.metadata, stack.tagCompound)

    fun toItemStack(): ItemStack = item?.let { item -> ItemStack(item, count, meta).also { it.tagCompound = tag } }
            ?: ItemStack.EMPTY

}