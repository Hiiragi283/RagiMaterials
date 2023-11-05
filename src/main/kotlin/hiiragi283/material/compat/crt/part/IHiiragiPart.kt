package hiiragi283.material.compat.crt.part

import crafttweaker.CraftTweakerAPI
import crafttweaker.annotations.ZenRegister
import crafttweaker.api.item.IItemStack
import crafttweaker.api.oredict.IOreDictEntry
import crafttweaker.mc1120.oredict.MCOreDictEntry
import hiiragi283.material.RMReference
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.compat.crt.toIItemStacks
import stanhebben.zenscript.annotations.ZenClass
import stanhebben.zenscript.annotations.ZenGetter
import stanhebben.zenscript.annotations.ZenMethod

@Suppress("unused")
@ZenClass("${RMReference.MOD_ID}.part.HiiragiPart")
@ZenRegister
interface IHiiragiPart : IOreDictEntry {

    val part: HiiragiPart

    //    Property    //

    @ZenGetter("shape")
    fun getShape(): HiiragiShape = part.shape

    @ZenGetter("material")
    fun getMaterial(): HiiragiMaterial = part.material

    //    Method    //

    @ZenMethod
    fun getIItemStacks(count: Int): List<IItemStack> = part.getItemStacks(count).toIItemStacks()

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

    class Impl(override val part: HiiragiPart) : MCOreDictEntry(part.getOreDict()), IHiiragiPart {

        constructor(shape: HiiragiShape, material: HiiragiMaterial) : this(shape.getPart(material))

    }

}