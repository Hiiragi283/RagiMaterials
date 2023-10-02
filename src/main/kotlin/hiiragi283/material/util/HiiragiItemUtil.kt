package hiiragi283.material.util

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import net.minecraft.block.Block
import net.minecraft.block.properties.IProperty
import net.minecraft.block.state.IBlockState
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary

//    Block    //

fun Block.item(): Item = Item.getItemFromBlock(this)

fun Block.itemStack(count: Int = 1, meta: Int = 0) = ItemStack(this, count, meta)

fun Block.itemStack(material: HiiragiMaterial?, count: Int = 1): ItemStack =
    material?.let { this.itemStack(count, it.index) } ?: ItemStack.EMPTY

fun Block.itemStack(part: HiiragiPart): ItemStack {
    val scale: Int = part.shape.scale
    return if (scale >= 144) this.itemStack(part.material, scale / 144) else ItemStack.EMPTY
}

fun Block.itemStackWild(count: Int = 1) = this.itemStack(count, Short.MAX_VALUE.toInt())

//    BlockState    //

fun IBlockState.itemStack(amount: Int = 1) = ItemStack(this.block, amount, this.block.getMetaFromState(this))

fun IBlockState.isSame(other: IBlockState): Boolean {
    return when {
        this.block != other.block -> false
        !this.propertyKeys.hasSameElements(other.propertyKeys) -> false
        else -> {
            val properties: Map<IProperty<*>, Comparable<*>> = this.properties
            val properties1: Map<IProperty<*>, Comparable<*>> = other.properties
            this.propertyKeys.all { key: IProperty<*> -> properties[key] == properties1[key] }
        }
    }
}

//    Item    //

fun Item.block(): Block? = (this as? ItemBlock)?.block

fun Item.itemStack(count: Int = 1, meta: Int = 0) = ItemStack(this, count, meta)

fun Item.itemStack(material: HiiragiMaterial?, count: Int = 1): ItemStack =
    material?.let { this.itemStack(count, it.index) } ?: ItemStack.EMPTY

fun Item.itemStack(part: HiiragiPart): ItemStack {
    val scale: Int = part.shape.scale
    return if (scale >= 144) this.itemStack(part.material, scale / 144) else ItemStack.EMPTY
}

fun Item.itemStackWild(count: Int = 1) = this.itemStack(count, Short.MAX_VALUE.toInt())

//    ItemStack    //

fun ItemStack.block(): Block? = this.item.block()

fun ItemStack.oreDicts(): List<String> = OreDictionary.getOreIDs(this).map(OreDictionary::getOreName)

fun ItemStack.notEmpty(): ItemStack? = this.takeUnless(ItemStack::isEmpty)

//ItemとMetadataのみで比較
fun ItemStack.isSameWithoutCount(other: ItemStack): Boolean =
    this.item == other.item && (this.metadata == other.metadata || this.metadata == Short.MAX_VALUE.toInt() || other.metadata == Short.MAX_VALUE.toInt())

//Item, Count, Metadataで比較
fun ItemStack.isSame(other: ItemStack): Boolean = this.isSameWithoutCount(other) && this.count == other.count

//NBTタグも含めて比較
fun ItemStack.isSameWithNBT(other: ItemStack): Boolean = this.isSame(other) && this.tagCompound == other.tagCompound