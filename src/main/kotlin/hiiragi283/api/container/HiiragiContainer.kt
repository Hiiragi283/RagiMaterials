package hiiragi283.api.container

import hiiragi283.api.tileentity.HiiragiTileEntity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.Container
import net.minecraft.inventory.Slot

abstract class HiiragiContainer<T : HiiragiTileEntity>(val player: EntityPlayer, val tile: T) : Container() {

    val invPlayer: InventoryPlayer = player.inventory

    override fun canInteractWith(playerIn: EntityPlayer) = true

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
}