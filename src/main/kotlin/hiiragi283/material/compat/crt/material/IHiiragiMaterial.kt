package hiiragi283.material.compat.crt.material

import crafttweaker.annotations.ZenRegister
import crafttweaker.api.item.IItemStack
import crafttweaker.api.liquid.ILiquidDefinition
import crafttweaker.api.liquid.ILiquidStack
import crafttweaker.mc1120.item.MCItemStack
import crafttweaker.mc1120.liquid.MCLiquidDefinition
import crafttweaker.mc1120.liquid.MCLiquidStack
import hiiragi283.material.RMReference
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.compat.crt.part.IHiiragiPart
import hiiragi283.material.compat.crt.shape.IHiiragiShape
import stanhebben.zenscript.annotations.ZenClass
import stanhebben.zenscript.annotations.ZenGetter
import stanhebben.zenscript.annotations.ZenMethod

@Suppress("unused")
@ZenClass("${RMReference.MOD_ID}.material.IHiiragiMaterial")
@ZenRegister
interface IHiiragiMaterial {

    val material: HiiragiMaterial

    //    Property    //

    @ZenGetter("name")
    fun getName(): String = material.name

    @ZenGetter("index")
    fun getIndex(): Int = material.index

    @ZenGetter("color")
    fun getColor(): Int = material.color

    @ZenGetter("formula")
    fun getFormula(): String = material.formula

    @ZenGetter("molar")
    fun getMolar(): Double = material.molar

    @ZenGetter("tempBoil")
    fun getTempBoil(): Int = material.tempBoil

    @ZenGetter("tempMelt")
    fun getTempMelt(): Int = material.tempMelt

    @ZenGetter("translationKey")
    fun getTranslationKey(): String = material.translationKey


    //    Method    //

    @ZenMethod
    fun addBracket(function: BracketFunction): IHiiragiMaterial = Impl(material.addBracket(function::apply))

    @ZenMethod
    fun getILiquidDefinition(): ILiquidDefinition = MCLiquidDefinition(material.getFluid())

    @ZenMethod
    fun getILiquidStack(amount: Int): ILiquidStack = MCLiquidStack(material.getFluidStack(amount))

    @ZenMethod
    fun getIItemStacks(count: Int): List<IItemStack> = material.getItemStacks(count).map(::MCItemStack)

    @ZenMethod
    fun getPart(shape: IHiiragiShape): IHiiragiPart = IHiiragiPart.Impl(material.getPart(shape.shape))

    @ZenMethod
    fun getTranslatedName() = material.getTranslatedName()

    @ZenGetter("hasFluid")
    fun hasFluid(): Boolean = material.hasFluid()

    @ZenGetter("hasFormula")
    fun hasFormula(): Boolean = material.hasFormula()

    @ZenGetter("hasMolar")
    fun hasMolar(): Boolean = material.hasMolar()

    @ZenGetter("hasTempBoil")
    fun hasTempBoil(): Boolean = material.hasTempBoil()

    @ZenGetter("hasTempMelt")
    fun hasTempMelt(): Boolean = material.hasTempMelt()

    //    Implementation    //

    class Impl(override val material: HiiragiMaterial) : IHiiragiMaterial

}