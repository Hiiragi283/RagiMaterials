package hiiragi283.material.container

import hiiragi283.material.api.container.HiiragiContainer
import hiiragi283.material.api.container.SlotOutputItemHandler
import hiiragi283.material.api.tile.TileEntityModuleMachine
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack


class ContainerModuleMachine(tile: TileEntityModuleMachine, player: EntityPlayer) :
    HiiragiContainer<TileEntityModuleMachine>(tile, player) {

    init {
        addSlotToContainer(SlotModuleMachine(tile, tile.inventoryInput, 0, getSlotPositionX(1), getSlotPositionY(0)))
        addSlotToContainer(SlotModuleMachine(tile, tile.inventoryInput, 1, getSlotPositionX(2), getSlotPositionY(0)))
        addSlotToContainer(SlotModuleMachine(tile, tile.inventoryInput, 2, getSlotPositionX(3), getSlotPositionY(0)))
        addSlotToContainer(SlotModuleMachine(tile, tile.inventoryInput, 3, getSlotPositionX(1), getSlotPositionY(1)))
        addSlotToContainer(SlotModuleMachine(tile, tile.inventoryInput, 4, getSlotPositionX(2), getSlotPositionY(1)))
        addSlotToContainer(SlotModuleMachine(tile, tile.inventoryInput, 5, getSlotPositionX(3), getSlotPositionY(1)))
        addSlotToContainer(SlotOutputItemHandler(tile.inventoryOutput, 0, getSlotPositionX(5), getSlotPositionY(0)))
        addSlotToContainer(SlotOutputItemHandler(tile.inventoryOutput, 1, getSlotPositionX(6), getSlotPositionY(0)))
        addSlotToContainer(SlotOutputItemHandler(tile.inventoryOutput, 2, getSlotPositionX(7), getSlotPositionY(0)))
        addSlotToContainer(SlotOutputItemHandler(tile.inventoryOutput, 3, getSlotPositionX(5), getSlotPositionY(1)))
        addSlotToContainer(SlotOutputItemHandler(tile.inventoryOutput, 4, getSlotPositionX(6), getSlotPositionY(1)))
        addSlotToContainer(SlotOutputItemHandler(tile.inventoryOutput, 5, getSlotPositionX(7), getSlotPositionY(1)))
        initSlotsPlayer(84)
    }

    override fun transferStackInSlot(player: EntityPlayer, index: Int): ItemStack {
        var stack: ItemStack = ItemStack.EMPTY
        val slot: Slot = inventorySlots[index]
        if (slot.hasStack) {
            val stackSlot: ItemStack = slot.stack
            stack = stackSlot.copy()
            if (index in (0..11)) {
                if (!mergeItemStack(stackSlot, 4, inventorySlots.size, false)) return ItemStack.EMPTY
            } else {
                if (!mergeItemStack(stackSlot, 0, 4, false)) return ItemStack.EMPTY
            }
            if (stackSlot.isEmpty) {
                slot.putStack(ItemStack.EMPTY)
            } else slot.onSlotChanged()
        }
        return stack
    }

}