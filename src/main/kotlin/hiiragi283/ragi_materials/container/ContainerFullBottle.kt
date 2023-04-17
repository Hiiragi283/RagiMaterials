package hiiragi283.ragi_materials.container

import hiiragi283.ragi_materials.tile.TileFullBottleStation
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack

class ContainerFullBottle(player: EntityPlayer, override val tile: TileFullBottleStation) : ContainerBase<TileFullBottleStation>(player, tile) {

    val output = tile.output

    init {
        addSlotToContainer(SlotOutItemHandler(output, 0, 44 + 2 * 18, 20))
        initSlotsPlayer(51)
    }

    override fun canInteractWith(playerIn: EntityPlayer) = true

    override fun transferStackInSlot(playerIn: EntityPlayer, index: Int): ItemStack {
        var stack = ItemStack.EMPTY
        val slot = inventorySlots[index]
        if (slot.hasStack) {
            val stackSlot = slot.stack
            stack = stackSlot.copy()
            when (index) {
                //Output -> Inventory, Hotbar
                0 -> if (!mergeItemStack(stackSlot, 1, inventorySlots.size, true)) return ItemStack.EMPTY
                //Inventory, Hotbar -> Input
                else -> if (!mergeItemStack(stackSlot, 0, 1, true)) return ItemStack.EMPTY
            }
            if (stackSlot.isEmpty) slot.putStack(ItemStack.EMPTY) else slot.onSlotChanged()
        }
        return stack
    }

}