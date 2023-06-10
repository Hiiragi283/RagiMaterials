package hiiragi283.material.util.wrapper

import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidStack

data class FluidStackWrapper(val fluid: Fluid, val amount: Int) {

    constructor(stack: FluidStack, amount: Int = stack.amount) : this(stack.fluid, amount)

    fun toFluidStack(): FluidStack = FluidStack(fluid, amount)

    fun moreThan(other: FluidStackWrapper): Boolean = fluid == other.fluid && amount >= other.amount

}