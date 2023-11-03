package hiiragi283.material.compat.crt.part

import crafttweaker.CraftTweakerAPI
import crafttweaker.annotations.ZenRegister
import crafttweaker.api.item.IItemStack
import crafttweaker.api.oredict.IOreDictEntry
import crafttweaker.mc1120.item.MCItemStack
import crafttweaker.mc1120.oredict.MCOreDictEntry
import hiiragi283.material.RMReference
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.compat.crt.material.IHiiragiMaterial
import hiiragi283.material.compat.crt.shape.IHiiragiShape
import stanhebben.zenscript.annotations.ZenClass
import stanhebben.zenscript.annotations.ZenGetter
import stanhebben.zenscript.annotations.ZenMethod

@Suppress("unused")
@ZenClass("${RMReference.MOD_ID}.part.IHiiragiPart")
@ZenRegister
interface IHiiragiPart {

    val part: HiiragiPart

    //    Property    //

    @ZenGetter("shape")
    fun getShape(): IHiiragiShape = IHiiragiShape.Impl(part.shape)

    @ZenGetter("material")
    fun getMaterial(): IHiiragiMaterial = IHiiragiMaterial.Impl(part.material)

    //    Method    //

    @ZenMethod
    fun getIItemStack(count: Int): IItemStack = MCItemStack(part.getItemStack(count))

    @ZenMethod
    fun getIItemStacks(count: Int): List<IItemStack> = part.getItemStacks(count).map(::MCItemStack)

    @ZenMethod
    fun getOreDict(): IOreDictEntry = MCOreDictEntry(part.getOreDict())

    @ZenMethod
    fun getScale(): Int = part.getScale()

    @ZenMethod
    fun getTranslatedName() = part.getTranslatedName()

    //    Static Method    //

    companion object {

        @JvmStatic
        @ZenMethod
        fun fromOreDict(oreDictEntry: IOreDictEntry): IHiiragiPart {
            val part: HiiragiPart? = HiiragiPart.fromOreDict(oreDictEntry.name)
            if (part == null) {
                CraftTweakerAPI.logError("Could not find part with oreDict: $oreDictEntry")
            }
            return Impl(part!!)
        }

    }

    //    Implementation    //

    class Impl(override val part: HiiragiPart) : IHiiragiPart {

        constructor(shape: HiiragiShape, material: HiiragiMaterial) : this(shape.getPart(material))

    }

}