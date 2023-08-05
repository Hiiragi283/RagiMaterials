@file:JvmName("FluidStackUtil")

package hiiragi283.core.util

import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack

fun Pair<String, Int>.toFluidStack(): FluidStack =
    FluidStack(FluidRegistry.getFluidStack(this.first, this.second), this.second)