package hiiragi283.ragi_materials.inventory.container

import hiiragi283.ragi_materials.base.TileLockableBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack

class ContainerLaboTable(player: EntityPlayer, tile: TileLockableBase) : ContainerTileBase<TileLockableBase>(player, tile) {

    init {
        //実験台のスロットを設定
        for (j in 0 until tile.sizeInventory) {
            addSlotToContainer(Slot(tile, j, 44 + j * 18, 20))
        }
        initSlotsPlayer(51)
    }

    override fun transferStackInSlot(playerIn: EntityPlayer, index: Int): ItemStack {
        var stack = ItemStack.EMPTY
        val slot = inventorySlots[index]
        if (slot.hasStack) {
            val stack1 = slot.stack
            stack = stack1.copy()
            if (index < tile.sizeInventory) {
                if (!mergeItemStack(stack1, tile.sizeInventory, inventorySlots.size, true)) {
                    return ItemStack.EMPTY
                }
            } else if (!mergeItemStack(stack1, 0, tile.sizeInventory, false)) {
                return ItemStack.EMPTY
            }
            if (stack1.isEmpty) slot.putStack(ItemStack.EMPTY) else slot.onSlotChanged()
        }
        return stack
    }

}