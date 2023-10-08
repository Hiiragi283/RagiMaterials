package hiiragi283.material.container

import hiiragi283.material.api.container.HiiragiContainer
import hiiragi283.material.tile.TileTransferStationFluid
import net.minecraft.entity.player.EntityPlayer

class ContainerTransferStationFluid(tile: TileTransferStationFluid, player: EntityPlayer) :
    HiiragiContainer.TileEntity<TileTransferStationFluid>(tile, player) {

    init {
        initSlotsPlayer(51)
    }

}