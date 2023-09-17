package hiiragi283.material.api.capability.fluid

import hiiragi283.material.api.capability.IOControllable
import hiiragi283.material.api.tile.TileEntityModuleMachine
import hiiragi283.material.network.HiiragiFluidMessage
import hiiragi283.material.network.HiiragiNetworkWrapper

class ModuleMachineFluidTank(
    val index: Int,
    tile: TileEntityModuleMachine,
    override var ioType: IOControllable.Type = IOControllable.Type.CATALYST
    ) : HiiragiFluidTank(64000, ioType, tile) {

    fun setIOType(type: IOControllable.Type): ModuleMachineFluidTank = also { ioType = type }

    override fun onContentsChanged() {
        super.onContentsChanged()
        tile?.let { HiiragiNetworkWrapper.sendToAll(HiiragiFluidMessage(tile.pos, index, getFluid())) }
    }

}