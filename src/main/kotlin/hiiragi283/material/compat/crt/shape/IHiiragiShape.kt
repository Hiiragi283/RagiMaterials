package hiiragi283.material.compat.crt.shape

import crafttweaker.annotations.ZenRegister
import crafttweaker.api.item.IItemStack
import crafttweaker.api.oredict.IOreDictEntry
import crafttweaker.mc1120.item.MCItemStack
import crafttweaker.mc1120.oredict.MCOreDictEntry
import hiiragi283.material.RMReference
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.compat.crt.material.IHiiragiMaterial
import hiiragi283.material.compat.crt.part.IHiiragiPart
import stanhebben.zenscript.annotations.ZenClass
import stanhebben.zenscript.annotations.ZenGetter
import stanhebben.zenscript.annotations.ZenMethod

@Suppress("unused")
@ZenClass("${RMReference.MOD_ID}.shape.IHiiragiShape")
@ZenRegister
interface IHiiragiShape {

    val shape: HiiragiShape

    //    Property    //

    @ZenGetter("name")
    fun getName(): String = shape.name

    //    Method    //

    @ZenMethod
    fun getIngotCount(material: IHiiragiMaterial): Int = shape.getIngotCount(material.material)

    @ZenMethod
    fun getIItemStack(material: IHiiragiMaterial, count: Int): IItemStack =
        MCItemStack(shape.getItemStack(material.material, count))

    @ZenMethod
    fun getIItemStacks(count: Int): List<IItemStack> = shape.getItemStacks(count).map(::MCItemStack)

    @ZenMethod
    fun getOreDictEntry(material: IHiiragiMaterial): IOreDictEntry = MCOreDictEntry(shape.getOreDict(material.material))

    @ZenMethod
    fun getPart(material: IHiiragiMaterial): IHiiragiPart = IHiiragiPart.Impl(shape.getPart(material.material))

    @ZenMethod
    fun getScale(material: IHiiragiMaterial): Int = shape.getScale(material.material)

    @ZenMethod
    fun getTranslatedName(material: IHiiragiMaterial): String = shape.getTranslatedName(material.material)

    @ZenMethod
    fun addAlternativeName(vararg name: String): IHiiragiShape = also {
        shape.addAlternativeName(*name)
    }

    //    Static Methods    //

    companion object {

        @JvmStatic
        @ZenMethod
        fun getIngotScale(material: IHiiragiMaterial): Int = HiiragiShape.getIngotScale(material.material)

        @JvmStatic
        @ZenMethod
        fun setIngotScale(material: IHiiragiMaterial, scale: Int) = HiiragiShape.setIngotScale(material.material, scale)

        @JvmStatic
        @ZenMethod
        fun getBlockMultiplier(material: IHiiragiMaterial): Int = HiiragiShape.getBlockMultiplier(material.material)

        @JvmStatic
        @ZenMethod
        fun setBlockMultiplier(material: IHiiragiMaterial, multiplier: Int) =
            HiiragiShape.setBlockMultiplier(material.material, multiplier)

        @JvmStatic
        @ZenMethod
        fun getBlockScale(material: IHiiragiMaterial) = HiiragiShape.getBlockScale(material.material)

    }

    //    Implementation    //

    class Impl(override val shape: HiiragiShape) : IHiiragiShape

}