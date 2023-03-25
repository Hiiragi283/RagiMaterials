package hiiragi283.ragi_materials.integration.crafttweaker

import crafttweaker.api.item.IItemStack
import crafttweaker.api.liquid.ILiquidStack
import hiiragi283.ragi_materials.util.RagiFluidUtil
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack

object HiiragiUtil {

    //IItemStack -> ItemStack
    fun getStack(iStack: IItemStack): ItemStack {
        val stack = iStack.internal
        return if (stack is ItemStack) stack else ItemStack.EMPTY
    }

    //IItemStack[] -> List<ItemStack>
    fun getStacks(iStacks: Array<IItemStack>): List<ItemStack> {
        val list = mutableListOf<ItemStack>()
        iStacks.forEach { list.add(getStack(it)) }
        return list
    }

    //ILiquidStack -> FluidStack
    fun getFluidStack(iLiquid: ILiquidStack): FluidStack {
        val fluidStack = iLiquid.internal
        return if (fluidStack is FluidStack) fluidStack else RagiFluidUtil.getFluidStack("", 0)
    }

}