@file:JvmName("FluidUtil")

package ragi_materials.core.util

import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack
import ragi_materials.core.RagiRegistry
import ragi_materials.core.material.RagiMaterial

fun getFluidTag(name: String, amount: Int) = NBTTagCompound().also {
    it.setString("FluidName", name)
    it.setInteger("Amount", amount)
}

fun getFluidStack(name: String, amount: Int) = FluidStack(FluidRegistry.getFluid(name) ?: FluidRegistry.WATER, amount)

fun getBottle(fluidStack: FluidStack, count: Int = 1) = ItemStack(RagiRegistry.ItemFullBottle, count, 0).also { stack ->
    stack.tagCompound = NBTTagCompound().also { it.setTag("Fluid", fluidStack.writeToNBT(NBTTagCompound())) }
}

fun getBottle(name: String, amount: Int = 1000, count: Int = 1) = ItemStack(RagiRegistry.ItemFullBottle, count, 0).also { stack ->
    stack.tagCompound = NBTTagCompound().also { it.setTag("Fluid", getFluidTag(name, amount)) }
}

fun getBottle(material: RagiMaterial, amount: Int = 1000, count: Int = 1) = getBottle(material.name, amount, count)