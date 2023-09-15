package hiiragi283.material.api.capability.fluid

import hiiragi283.material.api.capability.IOControllable
import hiiragi283.material.api.tile.TileEntityModuleMachine
import hiiragi283.material.network.HiiragiFluidMessage
import hiiragi283.material.network.HiiragiNetworkWrapper

class ModuleMachineFluidTank(val index: Int, ioType: IOControllable.Type, tile: TileEntityModuleMachine) :
    HiiragiFluidTank(64000, ioType, tile) {

    override fun onContentsChanged() {
        super.onContentsChanged()
        tile?.let { HiiragiNetworkWrapper.sendToAll(HiiragiFluidMessage(tile.pos, index, getFluid())) }
    }

}