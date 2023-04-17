package hiiragi283.ragi_materials.api.stack

import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

data class ItemMetaNBT(val item: Item?, val meta: Int, val tag: NBTTagCompound?) {

    constructor(stack: ItemStack) : this(stack.item, stack.metadata, stack.tagCompound)

    fun toItemStack(amount: Int = 1): ItemStack = item?.let { item -> ItemStack(item, amount, meta).also { it.tagCompound = tag } }
            ?: ItemStack.EMPTY

}