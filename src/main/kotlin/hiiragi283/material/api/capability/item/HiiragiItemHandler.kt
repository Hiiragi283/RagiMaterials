package hiiragi283.material.api.capability.item

import hiiragi283.material.api.capability.IOControllable
import hiiragi283.material.util.HiiragiNBTKey
import hiiragi283.material.util.getStringOrNull
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.ItemStackHandler

open class HiiragiItemHandler(
    size: Int = 1,
    override var ioType: IOControllable.Type = IOControllable.Type.GENERAL,
    val tile: TileEntity? = null
) : ItemStackHandler(size), IOControllable {

    override fun onContentsChanged(slot: Int) {
        tile?.markDirty();
    }

    //    Custom    //

    open fun isEmpty(): Boolean {
        return (0 until slots)
            .map { slot: Int -> getStackInSlot(slot) }
            .all { stack: ItemStack -> stack.isEmpty }
    }

    open fun clear(): Unit = clear(0 until slots)

    open fun clear(range: IntRange) {
        range.forEach { slot: Int -> setStackInSlot(slot, ItemStack.EMPTY) }
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

    //    INBTSerializable    //

    override fun serializeNBT(): NBTTagCompound =
        super.serializeNBT().also { tag: NBTTagCompound -> tag.setString(HiiragiNBTKey.IO_TYPE, ioType.name) }

    override fun deserializeNBT(nbt: NBTTagCompound) {
        super.deserializeNBT(nbt)
        nbt.getStringOrNull(HiiragiNBTKey.IO_TYPE)?.let { name: String -> ioType = IOControllable.Type.valueOf(name) }
    }

}