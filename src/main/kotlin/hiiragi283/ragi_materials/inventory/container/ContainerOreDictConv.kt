package hiiragi283.ragi_materials.inventory.container

import hiiragi283.ragi_materials.inventory.slot.SlotOutput
import hiiragi283.ragi_materials.tile.TileOreDictConv
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack

class ContainerOreDictConv(player: EntityPlayer, tile: TileOreDictConv) : ContainerTileBase<TileOreDictConv>(player, tile) {

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
            //Output -> Inventory, Hotbar
            if (index == 37) {
                if (!mergeItemStack(stack1, 0, 36, true)) {
                    return ItemStack.EMPTY
                }
            }
            //Input -> Inventory, Hotbar
            else if (index == 36) {
                if (!mergeItemStack(stack1, 0, 36, true)) {
                    return ItemStack.EMPTY
                }
            }
            //Inventory, Hotbar -> Input
            else if (!mergeItemStack(stack1, 36, 37, true)) {
                return ItemStack.EMPTY
            }
            if (stack1.isEmpty) slot.putStack(ItemStack.EMPTY) else slot.onSlotChanged()
        }
        return stack
    }
}