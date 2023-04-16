package hiiragi283.ragi_materials.api.stack

import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidStack

data class FluidStackWrapper(val fluid: Fluid, val amount: Int, val tag: NBTTagCompound) {

    constructor(stack: FluidStack) : this(stack.fluid, stack.amount, stack.tag)

    fun toFluidStack() = FluidStack(fluid, amount, tag)

}