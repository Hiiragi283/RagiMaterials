package hiiragi283.material.api.container

import hiiragi283.material.api.tile.HiiragiTileEntity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.Container
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack

abstract class HiiragiContainer(val player: EntityPlayer) : Container() {

    val inventoryPlayer: InventoryPlayer = player.inventory

    fun getSlotPosX(index: Int): Int = 8 + 18 * index

    fun getSlotPosY(index: Int): Int = 18 * (index + 1)

    override fun canInteractWith(playerIn: EntityPlayer): Boolean = true

    override fun transferStackInSlot(playerIn: EntityPlayer, index: Int): ItemStack {
        var stack: ItemStack = ItemStack.EMPTY
        val slot: Slot = inventorySlots[index]
        if (slot.hasStack) {
            val stackSlot: ItemStack = slot.stack
            stack = stackSlot.copy()
            if (!mergeItemStack(stackSlot, 0, inventorySlots.size, false)) return ItemStack.EMPTY
            if (stackSlot.isEmpty) slot.putStack(ItemStack.EMPTY) else slot.onSlotChanged()
        }
        return stack
    }

    fun initSlotsPlayer(posY: Int) {
        //プレイヤーのインベントリのスロットを設定
        (0 .. 2).forEach { y: Int ->
            (0 .. 8).forEach { x: Int ->
                addSlotToContainer(Slot(inventoryPlayer, x + y * 9 + 9, 8 + x * 18, y * 18 + posY))
            }
        }
        //プレイヤーのホットバーのスロットを設定
        (0..8).forEach { x: Int ->
            addSlotToContainer(Slot(inventoryPlayer, x, 8 + x * 18, 3 * 18 + (posY + 4)))
        }
    }

    abstract class TileEntity<T : HiiragiTileEntity>(val tile: T, player: EntityPlayer) : HiiragiContainer(player)

}