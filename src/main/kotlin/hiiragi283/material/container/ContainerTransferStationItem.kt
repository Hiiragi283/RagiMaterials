package hiiragi283.material.container

import hiiragi283.material.api.container.HiiragiContainer
import hiiragi283.material.tile.TileTransferStationItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack
import net.minecraftforge.items.SlotItemHandler

class ContainerTransferStationItem(tile: TileTransferStationItem, player: EntityPlayer) :
    HiiragiContainer.TileEntity<TileTransferStationItem>(tile, player) {

    init {
        addSlotToContainer(SlotItemHandler(tile.inventory, 0, getSlotPosX(4), 20))
        initSlotsPlayer(51)
    }

    override fun transferStackInSlot(player: EntityPlayer, index: Int): ItemStack {
        var stack: ItemStack = ItemStack.EMPTY
        val slot: Slot = inventorySlots[index]
        if (slot.hasStack) {
            val stackSlot: ItemStack = slot.stack
            stack = stackSlot.copy()
            when (index) {
                //Output -> Inventory, Hotbar
                0 -> if (!mergeItemStack(stackSlot, 1, inventorySlots.size, true)) return ItemStack.EMPTY
                //Inventory, Hotbar -> Input
                else -> if (!mergeItemStack(stackSlot, 0, 1, false)) return ItemStack.EMPTY
            }
            if (stackSlot.isEmpty) slot.putStack(ItemStack.EMPTY) else slot.onSlotChanged()
        }
        return stack
    }

}