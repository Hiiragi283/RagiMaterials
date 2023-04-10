package hiiragi283.ragi_materials.container

import hiiragi283.ragi_materials.tile.TileFullBottleStation
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack

class ContainerFullBottle(player: EntityPlayer, override val tile: TileFullBottleStation) : ContainerBase<TileFullBottleStation>(player, tile) {

    val input = tile.input

    init {
        addSlotToContainer(SlotOutItemHandler(input, 0, 44 + 2 * 18, 20))
        initSlotsPlayer(51)
    }

    override fun canInteractWith(playerIn: EntityPlayer) = true

    override fun transferStackInSlot(playerIn: EntityPlayer, index: Int): ItemStack {
        var stack = ItemStack.EMPTY
        val slot = inventorySlots[index]
        if (slot.hasStack) {
            val stackSlot = slot.stack
            stack = stackSlot.copy()
            if (index == 0) {
                if (!mergeItemStack(stackSlot, input.slots + 1, inventorySlots.size, true)) return ItemStack.EMPTY
            } else {
                if (!mergeItemStack(stackSlot, input.slots, inventorySlots.size, true)) return ItemStack.EMPTY
            }
            if (stackSlot.isEmpty) slot.putStack(ItemStack.EMPTY) else slot.onSlotChanged()
        }
        return stack
    }

}