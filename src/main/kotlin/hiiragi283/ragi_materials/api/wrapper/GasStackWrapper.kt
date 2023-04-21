package hiiragi283.ragi_materials.api.wrapper

import mekanism.api.gas.Gas
import mekanism.api.gas.GasStack

data class GasStackWrapper(val gas: Gas, val amount: Int) {

    constructor(stack: GasStack) : this(stack.gas, stack.amount)

    fun toFluidStack() = GasStack(gas, amount)

}