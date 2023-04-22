package ragi_materials.main.container

import ragi_materials.main.tile.TileLaboBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraftforge.items.SlotItemHandler
import ragi_materials.core.container.ContainerBase

class ContainerLaboTable(player: EntityPlayer, override val tile: TileLaboBase) : ContainerBase<TileLaboBase>(player, tile) {

    val inputs = tile.inputs
    val catalyst = tile.catalyst
    val inventory = tile.inventory

    init {
        //実験台のスロットを設定
        for (j in 0 until inputs.slots) {
            addSlotToContainer(SlotItemHandler(inputs, j, 44 + j * 18, 20))
        }
        addSlotToContainer(SlotItemHandler(catalyst, 0, 44 + 9 + 5 * 18, 20))
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
                //Inputs, Catalyst -> Inventory, HotBar
                in 0..5 -> if (!mergeItemStack(stackSlot, inventory.slots, inventorySlots.size, true)) return ItemStack.EMPTY
                //Inventory, HotBar -> Inputs, Catalyst
                else -> if (!mergeItemStack(stackSlot, 0, inventory.slots, false)) return ItemStack.EMPTY
            }
            if (stackSlot.isEmpty) slot.putStack(ItemStack.EMPTY) else slot.onSlotChanged()
        }
        return stack
    }
}