package hiiragi283.ragi_materials.client.container

import hiiragi283.ragi_materials.base.TileLockableBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.Container
import net.minecraft.inventory.Slot

abstract class RagiContainer<T : TileLockableBase>(val player: EntityPlayer, val tile: T) : Container() {

    val invPlayer: InventoryPlayer = player.inventory
    val invTile = tile.inventory

    fun initSlotsPlayer(posY: Int) {
        //プレイヤーのインベントリのスロットを設定
        for (y in 0..2) {
            for (x in 0..8) {
                addSlotToContainer(Slot(invPlayer, x + y * 9 + 9, 8 + x * 18, y * 18 + posY))
            }
        }
        //プレイヤーのホットバーのスロットを設定
        for (x in 0..8) {
            addSlotToContainer(Slot(invPlayer, x, 8 + x * 18, 3 * 18 + (posY + 4)))
        }
    }

    override fun canInteractWith(player: EntityPlayer) = tile.inventory.isUsableByPlayer(player)

    override fun onContainerClosed(player: EntityPlayer) {
        super.onContainerClosed(player)
        tile.closeInventory(player)
    }

}