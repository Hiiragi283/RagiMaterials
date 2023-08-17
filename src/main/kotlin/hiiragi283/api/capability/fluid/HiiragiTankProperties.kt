package hiiragi283.api.capability.fluid

import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.capability.IFluidTankProperties


class HiiragiTankProperties(private val tank: HiiragiFluidTank) : IFluidTankProperties {

    override fun getContents(): FluidStack? = tank.fluid?.copy()

    override fun getCapacity(): Int = tank.capacity

    override fun canFill(): Boolean = tank.canFill

    override fun canDrain(): Boolean = tank.canDrain

    override fun canFillFluidType(fluidStack: FluidStack): Boolean = tank.canFillFluidType(fluidStack)

    override fun canDrainFluidType(fluidStack: FluidStack): Boolean = tank.canDrainFluidType(fluidStack)

}