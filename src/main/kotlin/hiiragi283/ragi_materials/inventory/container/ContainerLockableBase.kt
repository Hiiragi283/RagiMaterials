package hiiragi283.ragi_materials.inventory.container

import hiiragi283.ragi_materials.base.TileLockableBase
import net.minecraft.entity.player.EntityPlayer

abstract class ContainerLockableBase<T : TileLockableBase>(player: EntityPlayer, override val tile: T) : ContainerBase<T>(player, tile) {

    val invTile = tile.inventory

    override fun canInteractWith(player: EntityPlayer) = tile.inventory.isUsableByPlayer(player)

    override fun onContainerClosed(player: EntityPlayer) {
        super.onContainerClosed(player)
        tile.closeInventory(player)
    }

}