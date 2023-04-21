@file:JvmName("FluidUtil")

package hiiragi283.ragi_materials.util

import hiiragi283.ragi_materials.api.init.RagiItems
import hiiragi283.ragi_materials.api.material.RagiMaterial
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack

fun FluidStack.writeToTag(tag: NBTTagCompound): NBTTagCompound {
    val tagFluid = NBTTagCompound()
    this.writeToNBT(tagFluid)
    tag.setTag("Fluid", tagFluid)
    return tag
}

fun getFluidTag(name: String, amount: Int) = NBTTagCompound().also { tag ->
    tag.setTag("Fluid", NBTTagCompound().also {
        it.setString("FluidName", name)
        it.setInteger("Amount", amount)
    })
}

fun getFluidStack(name: String, amount: Int) = FluidStack(FluidRegistry.getFluid(name) ?: FluidRegistry.WATER, amount)

fun getBottle(fluidStack: FluidStack, count: Int = 1) = ItemStack(RagiItems.ItemFullBottle, count, 0).also { it.tagCompound = fluidStack.writeToTag(NBTTagCompound()) }

fun getBottle(name: String, amount: Int = 1000, count: Int = 1) = ItemStack(RagiItems.ItemFullBottle, count, 0).also { it.tagCompound = getFluidTag(name, amount) }

fun getBottle(material: RagiMaterial, amount: Int = 1000, count: Int = 1) = getBottle(material.name, amount, count)