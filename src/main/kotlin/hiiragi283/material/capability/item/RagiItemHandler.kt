package hiiragi283.material.capability.item

import hiiragi283.material.base.TileBase
import hiiragi283.material.capability.EnumIOType
import hiiragi283.material.capability.ICapabilityIO
import net.minecraft.item.ItemStack
import net.minecraftforge.items.ItemStackHandler

open class RagiItemHandler<T : TileBase>(slots: Int, val tile: T) : ItemStackHandler(slots),
    ICapabilityIO<RagiItemHandler<*>> {

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