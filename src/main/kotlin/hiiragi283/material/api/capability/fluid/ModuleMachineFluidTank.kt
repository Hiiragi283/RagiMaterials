package hiiragi283.material.api.capability.fluid

import hiiragi283.material.api.capability.IOControllable
import hiiragi283.material.network.HiiragiMessage
import hiiragi283.material.network.HiiragiNetworkWrapper
import hiiragi283.material.tile.TileEntityModuleMachine

class ModuleMachineFluidTank(
    val index: Int,
    tile: TileEntityModuleMachine,
    override var ioType: IOControllable.Type = IOControllable.Type.CATALYST
) : HiiragiFluidTank(16000, ioType) {

    init {
        this.tile = tile
    }

    fun setIOType(type: IOControllable.Type): ModuleMachineFluidTank = also { ioType = type }

    override fun onContentsChanged() {
        tile?.let { HiiragiNetworkWrapper.sendToAll(HiiragiMessage.Client(tile.pos, tile.updateTag)) }
    }

}