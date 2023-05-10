package hiiragi283.material.util.wrapper

import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidStack

data class FluidStackWrapper(val fluid: Fluid?, val amount: Int) {

    constructor(stack: FluidStack?, amount: Int = stack?.amount ?: 0) : this(stack?.fluid, amount)

    fun toFluidStack() = FluidStack(fluid, amount)

    fun moreThan(other: FluidStackWrapper?): Boolean {
        //相手がnullでない場合
        if (other !== null) {
            //お互いの液体がnullでない場合
            if (fluid !== null && other.fluid !== null) {
                //中身と量の比較を行う
                return fluid == other.fluid && amount >= other.amount
            }
        }
        return false
    }
}