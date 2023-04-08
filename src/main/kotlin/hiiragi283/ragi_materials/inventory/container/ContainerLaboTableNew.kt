package hiiragi283.ragi_materials.inventory.container

import hiiragi283.ragi_materials.tile.TileLaboTable
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraftforge.items.SlotItemHandler

class ContainerLaboTableNew(player: EntityPlayer, override val tile: TileLaboTable) : ContainerBase<TileLaboTable>(player, tile) {

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
            val stack1 = slot.stack
            stack = stack1.copy()
            if (index < inventory.slots) {
                if (!mergeItemStack(stack1, inventory.slots, inventorySlots.size, true)) {
                    return ItemStack.EMPTY
                }
            } else if (!mergeItemStack(stack1, 0, inventory.slots, false)) {
                return ItemStack.EMPTY
            }
            if (stack1.isEmpty) slot.putStack(ItemStack.EMPTY) else slot.onSlotChanged()
        }
        return stack
    }
}