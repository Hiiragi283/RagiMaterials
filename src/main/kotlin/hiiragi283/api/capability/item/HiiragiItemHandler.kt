package hiiragi283.api.capability.item

import hiiragi283.api.capability.CapabilityIO
import hiiragi283.api.capability.IOType
import hiiragi283.api.tile.HiiragiTileEntity
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraftforge.common.util.Constants
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.IItemHandlerModifiable
import net.minecraftforge.items.ItemHandlerHelper
import kotlin.math.min

open class HiiragiItemHandler @JvmOverloads constructor(size: Int = 1) : IItemHandler,
    IItemHandlerModifiable, INBTSerializable<NBTTagCompound>, CapabilityIO<HiiragiItemHandler> {

    private var stacks: Array<ItemStack> = Array(size) { ItemStack.EMPTY }

    //    IItemHandler    //

    override fun getSlots(): Int = stacks.size

    override fun getStackInSlot(slot: Int): ItemStack {
        validateSlotIndex(slot)
        return stacks[slot]
    }

    //搬入できなかったItemStackが返される
    override fun insertItem(slot: Int, stack: ItemStack, simulate: Boolean): ItemStack {
        if (stack.isEmpty) return ItemStack.EMPTY
        validateSlotIndex(slot)
        val existing: ItemStack = stacks[slot]
        var limit: Int = getStackLimit(slot, stack)
        if (!existing.isEmpty) {
            if (!ItemHandlerHelper.canItemStacksStack(stack, existing)) return stack
            limit -= existing.count
        }
        if (limit <= 0) return stack
        val reachedLimit = stack.count > limit
        if (!simulate) {
            if (existing.isEmpty) {
                stacks[slot] = if (reachedLimit) ItemHandlerHelper.copyStackWithSize(stack, limit) else stack
            } else {
                existing.grow(if (reachedLimit) limit else stack.count)
            }
            onContentsChanged(slot)
        }
        return if (reachedLimit) ItemHandlerHelper.copyStackWithSize(stack, stack.count - limit) else ItemStack.EMPTY
    }

    //搬出できたItemStackが返される
    override fun extractItem(slot: Int, amount: Int, simulate: Boolean): ItemStack {
        if (amount == 0) return ItemStack.EMPTY
        validateSlotIndex(slot)
        val existing: ItemStack = stacks[slot]
        if (existing.isEmpty) return ItemStack.EMPTY
        val toExtract: Int = min(amount.toDouble(), existing.maxStackSize.toDouble()).toInt()
        return if (existing.count <= toExtract) {
            if (!simulate) {
                stacks[slot] = ItemStack.EMPTY
                onContentsChanged(slot)
            }
            existing
        } else {
            if (!simulate) {
                stacks[slot] = ItemHandlerHelper.copyStackWithSize(existing, existing.count - toExtract)
                onContentsChanged(slot)
            }
            ItemHandlerHelper.copyStackWithSize(existing, toExtract)
        }
    }

    override fun getSlotLimit(slot: Int): Int = 64

    private fun getStackLimit(slot: Int, stack: ItemStack): Int = min(getSlotLimit(slot), stack.maxStackSize)

    override fun isItemValid(slot: Int, stack: ItemStack): Boolean = true

    //    IItemHandlerModifiable    //

    override fun setStackInSlot(slot: Int, stack: ItemStack) {
        validateSlotIndex(slot)
        stacks[slot] = stack
        onContentsChanged(slot)
    }

    //    INBTSerializable    //

    override fun serializeNBT(): NBTTagCompound {
        val nbtTagList = NBTTagList()
        stacks.indices.forEach {
            if (!stacks[it].isEmpty) {
                NBTTagCompound().run {
                    this.setInteger("Slot", it)
                    stacks[it].writeToNBT(this)
                    nbtTagList.appendTag(this)
                }
            }
        }
        return NBTTagCompound().also {
            it.setTag("Items", nbtTagList)
            it.setInteger("Size", stacks.size)
        }
    }

    override fun deserializeNBT(nbt: NBTTagCompound) {
        setSize(if (nbt.hasKey("Size", Constants.NBT.TAG_INT)) nbt.getInteger("Size") else stacks.size)
        val tagList: NBTTagList = nbt.getTagList("Items", Constants.NBT.TAG_COMPOUND)
        (0 until tagList.tagCount()).forEach {
            val itemTags: NBTTagCompound = tagList.getCompoundTagAt(it)
            val slot: Int = itemTags.getInteger("Slot")
            if (slot in stacks.indices) {
                stacks[slot] = ItemStack(itemTags)
            }
        }
        onLoad()
    }

    //    ICapabilityIO    //

    override var ioType: IOType = IOType.INPUT

    override fun getIOType(): IOType = ioType

    override fun setIOType(type: IOType): HiiragiItemHandler = also { ioType = type }

    //    Custom    //

    private var tile: HiiragiTileEntity? = null

    open fun setTile(tile: HiiragiTileEntity): HiiragiItemHandler = also { this.tile = tile }
    open fun setSize(size: Int) {
        stacks = Array(size) { ItemStack.EMPTY }
    }

    open fun validateSlotIndex(slot: Int) {
        if (slot !in stacks.indices) throw RuntimeException("Slot " + slot + " not in valid range - [0," + stacks.size + ")")
    }

    open fun onLoad() {}
    open fun onContentsChanged(slot: Int) {
        tile?.markDirty()
    }

    open fun isEmpty(): Boolean {
        var result = 0
        for (slot in 0 until slots) {
            val stack = getStackInSlot(slot)
            if (stack.isEmpty) result++
        }
        return result == slots
    }

    open fun clear(): Unit = clear(0 until slots)

    open fun clear(range: IntRange) {
        for (slot in range) {
            setStackInSlot(slot, ItemStack.EMPTY)
        }
    }

    //実行結果の合否が返される
    open fun transferTo(slotFrom: Int, handlerTo: IItemHandler): Boolean {
        //搬出対象のItemStack
        val stack: ItemStack = stacks[slotFrom]
        if (!stack.isEmpty) {
            var cache: ItemStack = stack
            //相手のIItemHandlerの各スロットに対して実行
            (0 until handlerTo.slots).forEach {
                //cacheが空になるまで搬入を試みる
                if (!cache.isEmpty) {
                    val cacheTried: ItemStack = handlerTo.insertItem(it, cache, true)
                    //cacheが検証の前後で変化 -> 搬入可能なので実行
                    if (cache !== cacheTried) {
                        this.extractItem(slotFrom, cache.count - cacheTried.count, false)
                        cache = handlerTo.insertItem(it, cache, false)
                    }
                }
            }
            if (cache.isEmpty) return true
        }
        return false
    }

    open fun transferAllTo(handlerTo: IItemHandler): Boolean {
        (0 until slots).forEach {
            return transferTo(it, handlerTo)
        }
        return false
    }

    //実行結果の合否が返される
    open fun transferFrom(slotTo: Int, handlerFrom: IItemHandler): Boolean {
        //搬入対象のスロットにあるItemStack
        val stack: ItemStack = stacks[slotTo]
        //stackの個数が上限に達している場合はfalseを返す
        if (stack.count == getSlotLimit(slotTo)) return false
        //相手のIItemHandlerの各スロットに対して実行
        for (slotFrom in 0 until handlerFrom.slots) {
            val stackFrom: ItemStack = handlerFrom.getStackInSlot(slotFrom)
            //相手のスロットにあるItemStackが空の場合はパス
            if (stackFrom.isEmpty) continue
            //搬入の検証結果をcacheに保存
            val cache: ItemStack = insertItem(slotTo, handlerFrom.extractItem(slotFrom, stackFrom.count, false), false)
            //cacheがEMPTY -> すべて搬入できた -> trueを返して終了
            if (cache.isEmpty) return true
        }
        return false
    }

    open fun transferAllFrom(handlerFrom: IItemHandler): Boolean {
        (0 until slots).forEach {
            return transferFrom(it, handlerFrom)
        }
        return false
    }

}