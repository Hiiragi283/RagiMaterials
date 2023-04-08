package hiiragi283.ragi_materials.capability.itemhandler

import hiiragi283.ragi_materials.capability.EnumIOType
import net.minecraft.item.ItemStack
import net.minecraftforge.items.ItemStackHandler

class RagiItemHandler(slots: Int) : ItemStackHandler(slots) {

    private var ioType = EnumIOType.INPUT

    //    Custom    //

    fun getIOType() = ioType

    fun setIOType(type: EnumIOType): RagiItemHandler = also { ioType = type }

    fun isEmpty(): Boolean {
        var result = 0
        for (slot in 0 until slots) {
            val stack = getStackInSlot(slot)
            if (stack.isEmpty) result++
        }
        return result == slots
    }

    fun clear() {
        clear(0 until slots)
    }

    fun clear(range: IntRange) {
        for (slot in range) {
            setStackInSlot(slot, ItemStack.EMPTY)
        }
    }
}