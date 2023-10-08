package hiiragi283.material.util

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

fun ItemStack.toComparable(
    count: Int = this.count,
    meta: Int = this.metadata,
    tag: NBTTagCompound? = this.tagCompound
) = ItemStackComparable(this.item, count, meta, tag)

data class ItemStackComparable(
    val item: Item,
    var count: Int,
    var meta: Int,
    var tag: NBTTagCompound? = null
) {

    constructor(block: Block, count: Int, meta: Int, tag: NBTTagCompound? = null) : this(
        Item.getItemFromBlock(block),
        count,
        meta,
        tag
    )

    fun toItemStack(
        count: Int = this.count,
        meta: Int = this.meta,
        tag: NBTTagCompound? = this.tag
    ) = ItemStack(item, count, meta).also { stack: ItemStack -> stack.tagCompound = tag }

}