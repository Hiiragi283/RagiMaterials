package ragi_materials.main.container

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraftforge.items.SlotItemHandler
import ragi_materials.core.container.ContainerBase
import ragi_materials.core.container.SlotOutItemHandler
import ragi_materials.main.tile.TileOreDictConv

class ContainerOreDictConv(player: EntityPlayer, tile: TileOreDictConv) : ContainerBase<TileOreDictConv>(player, tile) {

    val input = tile.input
    val output = tile.output
    val inventory = tile.inventory

    init {
        addSlotToContainer(SlotItemHandler(input, 0, 44 + 1 * 18, 20)) //Input
        addSlotToContainer(SlotOutItemHandler(output, 0, 44 + 3 * 18, 20)) //Output
        initSlotsPlayer(51) //Player
    }

    override fun transferStackInSlot(playerIn: EntityPlayer, index: Int): ItemStack {
        var stack = ItemStack.EMPTY
        val slot = inventorySlots[index]
        //slot内にアイテムがある場合
        if (slot.hasStack) {
            val stackSlot = slot.stack
            stack = stackSlot.copy()
            when (index) {
                //Input, Output -> Inventory, Hotbar
                in 0..1 -> if (!mergeItemStack(stackSlot, 2, inventorySlots.size, true)) return ItemStack.EMPTY
                //Inventory, Hotbar -> Input
                else -> if (!mergeItemStack(stackSlot, 0, 1, true)) return ItemStack.EMPTY
            }
            if (stackSlot.isEmpty) slot.putStack(ItemStack.EMPTY) else slot.onSlotChanged()
        }
        return stack
    }
}