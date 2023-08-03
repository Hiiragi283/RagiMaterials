package hiiragi283.chemistry.container

import hiiragi283.api.container.HiiragiContainer
import hiiragi283.api.container.SlotOutItemHandler
import hiiragi283.chemistry.tile.TileEntityCrucible
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack

class ContainerCrucible(player: EntityPlayer, tile: TileEntityCrucible) :
    HiiragiContainer<TileEntityCrucible>(player, tile) {

    init {
        addSlotToContainer(SlotOutItemHandler(tile.invInput, 0, 8 + 4 * 18, 20))
        initSlotsPlayer(51)
    }

    override fun transferStackInSlot(playerIn: EntityPlayer, index: Int): ItemStack {
        var stack = ItemStack.EMPTY
        val slot = inventorySlots[index]
        if (slot.hasStack) {
            val stackSlot = slot.stack
            stack = stackSlot.copy()
            when (index) {
                //Input -> Inventory, Hotbar
                0 -> if (!mergeItemStack(stackSlot, 1, inventorySlots.size, true)) return ItemStack.EMPTY
                //Inventory, Hotbar -> Input
                else -> if (!mergeItemStack(stackSlot, 0, 1, true)) return ItemStack.EMPTY
            }
            if (stackSlot.isEmpty) slot.putStack(ItemStack.EMPTY) else slot.onSlotChanged()
        }
        return stack
    }

}