@file:Suppress("unused")

package hiiragi283.material.compat.crt

import crafttweaker.CraftTweakerAPI
import crafttweaker.api.block.IBlock
import crafttweaker.api.block.IBlockDefinition
import crafttweaker.api.item.IItemStack
import crafttweaker.api.liquid.ILiquidDefinition
import crafttweaker.api.liquid.ILiquidStack
import crafttweaker.api.minecraft.CraftTweakerMC
import crafttweaker.api.oredict.IOreDictEntry
import hiiragi283.material.RMReference
import hiiragi283.material.compat.HiiragiPluginBase
import hiiragi283.material.compat.crt.event.HiiragiEventManager
import hiiragi283.material.compat.crt.material.CTMaterialFactory
import hiiragi283.material.compat.crt.shape.CTShapeFactory
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fml.common.Loader

//    IBlock, IBlockDefinition    //

fun IBlock.toBlock(): Block = CraftTweakerMC.getBlock(this)

fun IBlockDefinition.toBlock(): Block = CraftTweakerMC.getBlock(this)

//    IItemStack    //

fun Collection<ItemStack>.toIItemStacks(): List<IItemStack> = CraftTweakerMC.getIItemStacks(this)

fun ItemStack.toIItemStack(): IItemStack = CraftTweakerMC.getIItemStack(this)

fun IItemStack.toItem(): Item = CraftTweakerMC.getItem(this.definition)

fun IItemStack.toItemStack(): ItemStack = CraftTweakerMC.getItemStack(this)

//    IOreDictEntry    //

fun IOreDictEntry.firstStack(): ItemStack = this.firstItem.toItemStack()

//    ILiquidStack    //

fun Fluid.toILiquidDefinition(): ILiquidDefinition = CraftTweakerMC.getILiquidDefinition(this)

fun FluidStack.toILiquidDefinition(): ILiquidDefinition = this.fluid.toILiquidDefinition()

fun Fluid.toILiquidStack(amount: Int): ILiquidStack = FluidStack(this, amount).toILiquidStack()

fun FluidStack.toILiquidStack(): ILiquidStack = CraftTweakerMC.getILiquidStack(this)

fun ILiquidStack.toFluid(): Fluid = CraftTweakerMC.getFluid(this.definition)

fun ILiquidStack.toFluidStack(): FluidStack = CraftTweakerMC.getLiquidStack(this)

object HiiragiCrTPlugin : HiiragiPluginBase("crafttweaker", "Crafttweaker", { true }) {

    override fun enabled(): Boolean = Loader.isModLoaded(modid)

    fun registerEvent() {
        if (!enabled()) return
        MinecraftForge.EVENT_BUS.register(HiiragiEventManager.EventHandler)
    }

    fun loadScript() {
        if (!enabled()) return
        CraftTweakerAPI.tweaker.loadScript(false, RMReference.MOD_ID)
    }

    fun registerShape() {
        if (!enabled()) return
        CTShapeFactory.registerShape()
    }

    fun registerMaterial() {
        if (!enabled()) return
        CTMaterialFactory.registerMaterial()
    }

}