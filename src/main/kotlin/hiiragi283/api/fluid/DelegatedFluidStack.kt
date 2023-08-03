package hiiragi283.api.fluid

import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack

class DelegatedFluidStack(private val name: String, private val amount: Int) {

    private lateinit var value: FluidStack

    fun get(): FluidStack {
        if(!::value.isInitialized) {
            value = FluidStack(FluidRegistry.getFluid(name), amount)
        }
        return value
    }

}