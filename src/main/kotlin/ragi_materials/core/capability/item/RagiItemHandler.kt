package ragi_materials.core.capability.item

import net.minecraft.item.ItemStack
import net.minecraftforge.items.ItemStackHandler
import ragi_materials.core.capability.EnumIOType
import ragi_materials.core.capability.ICapabilityIO
import ragi_materials.core.tile.TileBase

open class RagiItemHandler<T: TileBase>(slots: Int, val tile: T) : ItemStackHandler(slots), ICapabilityIO<RagiItemHandler<*>> {

    override fun onContentsChanged(slot: Int) {
        tile.markDirty()
    }

    //    ICapabilityIO    //

    override var ioType = EnumIOType.INPUT

    override fun getIOType() = ioType

    override fun setIOType(type: EnumIOType): RagiItemHandler<T> = also { ioType = type }

    //    Custom    //

    open fun isEmpty(): Boolean {
        var result = 0
        for (slot in 0 until slots) {
            val stack = getStackInSlot(slot)
            if (stack.isEmpty) result++
        }
        return result == slots
    }

    open fun clear() {
        clear(0 until slots)
    }

    open fun clear(range: IntRange) {
        for (slot in range) {
            setStackInSlot(slot, ItemStack.EMPTY)
        }
    }
}