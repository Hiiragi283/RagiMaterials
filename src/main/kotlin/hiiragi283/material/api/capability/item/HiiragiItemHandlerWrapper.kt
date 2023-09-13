package hiiragi283.material.api.capability.item

import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraftforge.common.util.Constants
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.items.IItemHandlerModifiable

/**
 * RagiItemHandlerとスロットの番号を紐づけたPairの一覧を作ることで，複数のRagiItemHandlerをまとめて処理できる
 * Thanks to SkyTheory!
 */

class HiiragiItemHandlerWrapper(vararg itemHandlers: HiiragiItemHandler)
    : IItemHandlerModifiable, INBTSerializable<NBTTagCompound> {

    private val pairs: MutableList<Pair<HiiragiItemHandler, Int>> = mutableListOf()

    init {
        for (itemHandler in itemHandlers) {
            for (slot in 0 until itemHandler.slots) {
                pairs.add(itemHandler to slot)
            }
        }
    }

    //    Slot    //

    override fun getSlots(): Int = pairs.size

    override fun getStackInSlot(slot: Int): ItemStack {
        val (handler: HiiragiItemHandler, index: Int) = getSlotHandler(slot)
        return handler.getStackInSlot(index)
    }

    override fun setStackInSlot(slot: Int, stack: ItemStack) {
        val (handler: HiiragiItemHandler, index: Int) = getSlotHandler(slot)
        handler.setStackInSlot(index, stack)
    }

    override fun getSlotLimit(slot: Int): Int = 64

    private fun getSlotHandler(slot: Int): Pair<HiiragiItemHandler, Int> = pairs[slot]

    //    Extraction    //

    //指定したスロットが搬出可能な場合のみアイテムを取り出すメソッド
    override fun extractItem(slot: Int, amount: Int, simulate: Boolean): ItemStack {
        return if (canExtract(slot)) {
            val (handler: HiiragiItemHandler, index: Int) = getSlotHandler(slot)
            handler.extractItem(index, amount, simulate)
        } else ItemStack.EMPTY
    }

    //アイテムを取り出せるか判定するメソッド
    private fun canExtract(slot: Int): Boolean = getSlotHandler(slot).first.ioType.canExtract

    //    Insertion    //

    //指定したスロットが搬入可能な場合のみアイテムを入れるメソッド
    override fun insertItem(slot: Int, stack: ItemStack, simulate: Boolean): ItemStack {
        return if (canInsert(slot)) {
            val (handler: HiiragiItemHandler, index: Int) = getSlotHandler(slot)
            handler.insertItem(index, stack, simulate)
        } else stack
    }

    //アイテムを入れられるか判定するメソッド
    private fun canInsert(slot: Int): Boolean = getSlotHandler(slot).first.ioType.canInsert

    //    INBTSerializable    //

    override fun serializeNBT(): NBTTagCompound {
        val nbtTagList = NBTTagList()
        for (i: Int in pairs.indices) {
            val (handler: HiiragiItemHandler, index: Int) = getSlotHandler(i)
            val stack: ItemStack = handler.getStackInSlot(index)
            if (!stack.isEmpty) NBTTagCompound().also { tag: NBTTagCompound ->
                tag.setInteger("Slot", i)
                stack.writeToNBT(tag)
                nbtTagList.appendTag(tag)
            }
        }
        return NBTTagCompound().also { it.setTag("Items", nbtTagList) }
    }

    override fun deserializeNBT(nbt: NBTTagCompound) {
        val tagList: NBTTagList = nbt.getTagList("Items", Constants.NBT.TAG_COMPOUND)
        (0 until tagList.tagCount()).forEach { i: Int ->
            val tag: NBTTagCompound = tagList.getCompoundTagAt(i)
            val slot: Int = tag.getInteger("Slot")
            if (slot >= 0 && slot < pairs.size) {
                val (handler: HiiragiItemHandler, index: Int) = getSlotHandler(i)
                handler.setStackInSlot(index, ItemStack(tag))
            }
        }
    }

    //    Custom    //

    fun isEmpty(): Boolean {
        return (0 until slots)
            .map { slot: Int -> getStackInSlot(slot) }
            .all { stack: ItemStack -> stack.isEmpty }
    }

    fun clear(): Unit = clear(0 until slots)

    fun clear(range: IntRange) {
        range.forEach { slot: Int -> setStackInSlot(slot, ItemStack.EMPTY) }
    }

}