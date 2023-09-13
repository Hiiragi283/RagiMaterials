package hiiragi283.material.api.machine

import hiiragi283.material.api.capability.fluid.HiiragiFluidTank
import hiiragi283.material.api.capability.item.HiiragiItemHandler

data class MachineContents(
    val inputItemHandler: HiiragiItemHandler,
    val inputTank0: HiiragiFluidTank,
    val inputTank1: HiiragiFluidTank,
    val inputTank2: HiiragiFluidTank,
    val property: IMachineProperty
)