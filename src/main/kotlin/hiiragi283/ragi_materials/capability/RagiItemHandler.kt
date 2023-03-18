package hiiragi283.ragi_materials.capability

import net.minecraftforge.items.ItemStackHandler

class RagiItemHandler(slots: Int): ItemStackHandler(slots) {

    fun isEmpty(): Boolean {
        var count = 0
        for (i in 0 until this.slots) {
            if (this.getStackInSlot(i).isEmpty) count++
        }
        return count == this.slots
    }
}