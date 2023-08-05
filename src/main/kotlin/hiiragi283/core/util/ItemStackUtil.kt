@file:JvmName("ItemStackUtil")

package hiiragi283.core.util

import net.minecraft.item.ItemStack


//ItemとMetadataのみで比較
fun ItemStack.isSameWithoutCount(other: ItemStack): Boolean =
    this.item == other.item && (this.metadata == other.metadata || this.metadata == 32767 || other.metadata == 32767)

//Item, Count, Metadataで比較
fun ItemStack.isSame(other: ItemStack): Boolean = this.isSameWithoutCount(other) && this.count == other.count

//NBTタグも含めて比較
fun ItemStack.isSameWithNBT(other: ItemStack): Boolean = this.isSame(other) && this.tagCompound == other.tagCompound