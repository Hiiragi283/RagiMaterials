package hiiragi283.material.api.machine;

import hiiragi283.material.api.capability.fluid.HiiragiFluidTank;
import hiiragi283.material.api.capability.item.HiiragiItemHandler;

public record MachineContents(
        HiiragiItemHandler inputItemHandler,
        HiiragiFluidTank inputTank0,
        HiiragiFluidTank inputTank1,
        HiiragiFluidTank inputTank2,
        IMachineProperty property
) {
}