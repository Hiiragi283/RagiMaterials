package ragi_materials.core.container

import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.Container
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.Slot

class ContainerStateBase(val player: EntityPlayer, val inventory: IInventory, val state: IBlockState) : Container() {

    val invPlayer: InventoryPlayer = player.inventory

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

    override fun canInteractWith(player: EntityPlayer) = inventory.isUsableByPlayer(player)

    override fun onContainerClosed(player: EntityPlayer) {
        super.onContainerClosed(player)
        inventory.closeInventory(player)
    }

}