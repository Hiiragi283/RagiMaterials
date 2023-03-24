package hiiragi283.ragi_materials.client.container

import hiiragi283.ragi_materials.tile.TileStoneMill
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack

class ContainerStoneMill(player: EntityPlayer, tile: TileStoneMill) : RagiContainer<TileStoneMill>(player, tile) {

    init {

        /*
          0: Output
          1: Input
          2 ~ 28: Player Inventory
          29 ~ 37: Player Hotbar
        */

        addSlotToContainer(SlotOutput(tile, 0, 44 + 3 * 18, 20)) //Output
        addSlotToContainer(Slot(tile, 1, 44 + 1 * 18, 20)) //Input
        initSlotsPlayer(51) //Player
    }

    override fun transferStackInSlot(playerIn: EntityPlayer, index: Int): ItemStack {
        var stack = ItemStack.EMPTY
        val slot = inventorySlots[index]
        //slot内にアイテムがある場合
        if (slot.hasStack) {
            val stack1 = slot.stack
            stack = stack1.copy()
            //Output -> Inventory, Hotbar
            if (index == 0) {
                if (!mergeItemStack(stack1, 2, 37, true)) {
                    return ItemStack.EMPTY
                }
            } else if (index != 1) {
                //Inventory, Hotbar -> Input
                if (!mergeItemStack(stack1, 1, 2, true)) {
                    return ItemStack.EMPTY
                }
                //Inventory -> Hotbar
                else if (index in 2 until 28) {
                    if (!mergeItemStack(stack1, 29, 37, true)) {
                        return ItemStack.EMPTY
                    }
                }
                //Hotbar -> Inventory
                else if (index in 29 until 37 && !mergeItemStack(stack1, 2, 28, true)) {
                    return ItemStack.EMPTY
                }
            }
            //Input-> Inventory, Hotbar
            else if (!mergeItemStack(stack1, 2, 37, true)) {
                return ItemStack.EMPTY
            }

            if (stack1.isEmpty) slot.putStack(ItemStack.EMPTY) else slot.onSlotChanged()
        }
        return stack
    }

}