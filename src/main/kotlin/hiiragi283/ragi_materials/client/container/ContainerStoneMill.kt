package hiiragi283.ragi_materials.client.container

import hiiragi283.ragi_materials.tile.TileStoneMill
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack

class ContainerStoneMill(player: EntityPlayer, tile: TileStoneMill) : RagiContainer<TileStoneMill>(player, tile) {

    init {

        /*
          0 ~ 26: Player Inventory
          27 ~ 35: Player Hotbar
          36: Input
          37: Output
        */
        initSlotsPlayer(51) //Player
        addSlotToContainer(Slot(tile, 1, 44 + 1 * 18, 20)) //Input
        addSlotToContainer(SlotOutput(tile, 0, 44 + 3 * 18, 20)) //Output
    }

    override fun transferStackInSlot(playerIn: EntityPlayer, index: Int): ItemStack {
        var stack = ItemStack.EMPTY
        val slot = inventorySlots[index]
        //slot内にアイテムがある場合
        if (slot.hasStack) {
            val stack1 = slot.stack
            stack = stack1.copy()
            //Output -> Inventory, Hotbar, Input
            if (index == 37) {
                if (!mergeItemStack(stack1, 0, 37, true)) {
                    return ItemStack.EMPTY
                }
            }
            //Input -> Inventory, Hotbar
            else if (index == 36) {
                if (!mergeItemStack(stack1, 0, 36, true)) {
                    return ItemStack.EMPTY
                }
            }
            //Inventory -> Hotbar, Input
            else if (index in 0 .. 26 && !mergeItemStack(stack1, 27, 37, true)) {
                    return ItemStack.EMPTY
            }
            //Hotbar -> Inventory
            else if (!mergeItemStack(stack1, 0, 27, true)) {
                return ItemStack.EMPTY
            }
            if (stack1.isEmpty) slot.putStack(ItemStack.EMPTY) else slot.onSlotChanged()
        }
        return stack
    }

}