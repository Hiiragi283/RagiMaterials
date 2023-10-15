package hiiragi283.material.compat.crt

import crafttweaker.api.item.IItemStack
import crafttweaker.api.liquid.ILiquidStack
import crafttweaker.api.minecraft.CraftTweakerMC
import crafttweaker.api.oredict.IOreDictEntry
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidStack

//    IItemStack    //

fun IItemStack.toItem(): Item = CraftTweakerMC.getItem(this.definition)

fun IItemStack.toItemStack(): ItemStack = CraftTweakerMC.getItemStack(this)

//    IOreDictEntry    //

fun IOreDictEntry.firstStack(): ItemStack = this.firstItem.toItemStack()

//    ILiquidStack    //

fun ILiquidStack.toFluid(): Fluid = CraftTweakerMC.getFluid(this.definition)

fun ILiquidStack.toFluidStack(): FluidStack = CraftTweakerMC.getLiquidStack(this)