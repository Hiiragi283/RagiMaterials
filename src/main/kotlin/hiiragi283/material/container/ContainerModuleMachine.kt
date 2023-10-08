package hiiragi283.material.container

import hiiragi283.material.api.container.HiiragiContainer
import hiiragi283.material.api.container.SlotItemHandlerControllable
import hiiragi283.material.network.HiiragiMessage
import hiiragi283.material.network.HiiragiNetworkWrapper
import hiiragi283.material.tile.TileEntityModuleMachine
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.SlotItemHandler


class ContainerModuleMachine(tile: TileEntityModuleMachine, player: EntityPlayer) :
    HiiragiContainer.TileEntity<TileEntityModuleMachine>(tile, player) {

    init {
        addSlotToContainer(SlotModuleMachine(tile, tile.inventoryInput, 0, getSlotPosX(1), getSlotPosY(0)))
        addSlotToContainer(SlotModuleMachine(tile, tile.inventoryInput, 1, getSlotPosX(2), getSlotPosY(0)))
        addSlotToContainer(SlotModuleMachine(tile, tile.inventoryInput, 2, getSlotPosX(3), getSlotPosY(0)))
        addSlotToContainer(SlotModuleMachine(tile, tile.inventoryInput, 3, getSlotPosX(1), getSlotPosY(1)))
        addSlotToContainer(SlotModuleMachine(tile, tile.inventoryInput, 4, getSlotPosX(2), getSlotPosY(1)))
        addSlotToContainer(SlotModuleMachine(tile, tile.inventoryInput, 5, getSlotPosX(3), getSlotPosY(1)))
        addSlotToContainer(SlotItemHandlerControllable(tile.inventoryOutput, 0, getSlotPosX(5), getSlotPosY(0)))
        addSlotToContainer(SlotItemHandlerControllable(tile.inventoryOutput, 1, getSlotPosX(6), getSlotPosY(0)))
        addSlotToContainer(SlotItemHandlerControllable(tile.inventoryOutput, 2, getSlotPosX(7), getSlotPosY(0)))
        addSlotToContainer(SlotItemHandlerControllable(tile.inventoryOutput, 3, getSlotPosX(5), getSlotPosY(1)))
        addSlotToContainer(SlotItemHandlerControllable(tile.inventoryOutput, 4, getSlotPosX(6), getSlotPosY(1)))
        addSlotToContainer(SlotItemHandlerControllable(tile.inventoryOutput, 5, getSlotPosX(7), getSlotPosY(1)))
        initSlotsPlayer(84)
        HiiragiNetworkWrapper.sendToAll(HiiragiMessage.SyncCount(tile.pos, tile.currentCount))
    }

    override fun transferStackInSlot(player: EntityPlayer, index: Int): ItemStack {
        var stack: ItemStack = ItemStack.EMPTY
        val slot: Slot = inventorySlots[index]
        if (slot.hasStack) {
            val stackSlot: ItemStack = slot.stack
            stack = stackSlot.copy()
            if (index in (0..11)) {
                if (!mergeItemStack(stackSlot, 12, inventorySlots.size, true)) return ItemStack.EMPTY
            } else {
                if (!mergeItemStack(stackSlot, 0, 6, false )) return ItemStack.EMPTY
            }
            if (stackSlot.isEmpty) {
                slot.putStack(ItemStack.EMPTY)
            } else slot.onSlotChanged()
        }
        return stack
    }

    class SlotModuleMachine(
        private val tile: TileEntityModuleMachine,
        itemHandler: IItemHandler,
        index: Int,
        xPosition: Int,
        yPosition: Int
    ) : SlotItemHandler(itemHandler, index, xPosition, yPosition) {

        override fun isItemValid(stack: ItemStack): Boolean = tile.machineProperty.itemSlots >= slotIndex + 1

        override fun isEnabled(): Boolean = tile.machineProperty.itemSlots >= slotIndex + 1

    }

}