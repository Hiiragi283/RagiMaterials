@file:JvmName("FluidUtil")

package hiiragi283.material.util

import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack

fun getFluidTag(name: String, amount: Int): NBTTagCompound = NBTTagCompound().also {
    it.setString("FluidName", name)
    it.setInteger("Amount", amount)
}

fun getFluidStack(name: String, amount: Int): FluidStack =
    FluidStack(FluidRegistry.getFluid(name) ?: FluidRegistry.WATER, amount)