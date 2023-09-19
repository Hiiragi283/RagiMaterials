package hiiragi283.material.api.capability.fluid

import hiiragi283.material.api.capability.IOControllable
import hiiragi283.material.api.tile.TileEntityModuleMachine

class ModuleMachineFluidTank(
    val index: Int,
    tile: TileEntityModuleMachine,
    override var ioType: IOControllable.Type = IOControllable.Type.CATALYST
) : HiiragiFluidTank(64000, ioType) {

    init {
        this.tile = tile
    }

    fun setIOType(type: IOControllable.Type): ModuleMachineFluidTank = also { ioType = type }

}