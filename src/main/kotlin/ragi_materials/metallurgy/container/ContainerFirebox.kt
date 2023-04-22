package ragi_materials.metallurgy.container

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraftforge.items.SlotItemHandler
import ragi_materials.core.container.ContainerBase
import ragi_materials.main.tile.TileFireboxPrimitive

class ContainerFirebox(player: EntityPlayer, override val tile: TileFireboxPrimitive) : ContainerBase<TileFireboxPrimitive>(player, tile) {

    val input = tile.input

    init {
        addSlotToContainer(SlotItemHandler(input, 0, 44 + 2 * 18, 20))
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