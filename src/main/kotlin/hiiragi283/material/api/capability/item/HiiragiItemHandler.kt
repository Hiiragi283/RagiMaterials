package hiiragi283.material.api.capability.item

import hiiragi283.material.api.capability.IOControllable
import hiiragi283.material.util.HiiragiNBTUtil
import hiiragi283.material.util.getStringOrNull
import hiiragi283.material.util.notEmpty
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

    open fun stacks(): List<ItemStack> = this.stacks

    open fun copyStacks(): List<ItemStack> = stacks().map(ItemStack::copy)

    //実行結果の合否が返される
    open fun transferTo(slotFrom: Int, handlerTo: IItemHandler, simulate: Boolean): Boolean {
        //搬出対象がEMPTY -> false
        val stack: ItemStack = stacks[slotFrom].notEmpty() ?: return false
        //一時的に結果を保存する
        var cache: ItemStack = stack
        for (slotTo: Int in (0 until handlerTo.slots)) {
            //cacheがEMPTY -> true
            if (cache.isEmpty) return true
            //相手に搬入した際の戻り値を取得
            val stackInserted: ItemStack = handlerTo.insertItem(slotTo, cache, true)
            //搬入の前後で変化 -> 搬入する余地がある
            if (cache != stackInserted) {
                //実際に搬出，搬入を行う
                this.extractItem(slotFrom, cache.count - stackInserted.count, simulate)
                cache = handlerTo.insertItem(slotTo, cache, simulate)
            }
        }
        return false
    }

    open fun transferAllTo(handlerTo: IItemHandler, simulate: Boolean): Boolean {
        (0 until slots).forEach {
            return transferTo(it, handlerTo, simulate)
        }
        return false
    }

    //    INBTSerializable    //

    override fun serializeNBT(): NBTTagCompound =
        super.serializeNBT().also { tag: NBTTagCompound -> tag.setString(HiiragiNBTUtil.IO_TYPE, ioType.name) }

    override fun deserializeNBT(nbt: NBTTagCompound) {
        super.deserializeNBT(nbt)
        nbt.getStringOrNull(HiiragiNBTUtil.IO_TYPE)?.let { name: String -> ioType = IOControllable.Type.valueOf(name) }
    }

}