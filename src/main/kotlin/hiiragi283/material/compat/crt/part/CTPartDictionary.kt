package hiiragi283.material.compat.crt.part

import crafttweaker.CraftTweakerAPI
import crafttweaker.annotations.ZenRegister
import crafttweaker.api.item.IItemStack
import hiiragi283.material.RMReference
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.PartDictionary
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.compat.crt.HiiragiAction
import hiiragi283.material.compat.crt.toIItemStack
import hiiragi283.material.compat.crt.toIItemStacks
import hiiragi283.material.compat.crt.toItemStack
import stanhebben.zenscript.annotations.OperatorType
import stanhebben.zenscript.annotations.ZenClass
import stanhebben.zenscript.annotations.ZenMethod
import stanhebben.zenscript.annotations.ZenOperator

@Suppress("unused")
@ZenClass("${RMReference.MOD_ID}.part.PartDictionary")
@ZenRegister
object CTPartDictionary {

    @JvmStatic
    @ZenMethod
    fun register(stack: IItemStack, part: IHiiragiPart) {
        CraftTweakerAPI.apply(HiiragiAction("Linking IItemStack: $stack with part: $part") {
            PartDictionary.register(stack.toItemStack(), part.part)
        })
    }

    @JvmStatic
    @ZenOperator(OperatorType.CONTAINS)
    fun contains(stack: IItemStack): Boolean = stack.toItemStack() in PartDictionary

    @JvmStatic
    @ZenMethod
    fun getPart(stack: IItemStack): IHiiragiPart? = PartDictionary.getPart(stack.toItemStack())?.let(IHiiragiPart::Impl)

    //    IHiiragiPart    //

    @JvmStatic
    @ZenMethod
    fun hasStack(part: IHiiragiPart): Boolean = PartDictionary.hasStack(part.part)

    @JvmStatic
    @ZenMethod
    fun getStack(part: IHiiragiPart, count: Int): IItemStack? =
        PartDictionary.getStack(part.part, count)?.toIItemStack()

    @JvmStatic
    @ZenMethod
    fun getStacks(part: IHiiragiPart, count: Int): List<IItemStack> =
        PartDictionary.getStacks(part.part, count).toIItemStacks()

    //    CtHiiragiShape    //

    @JvmStatic
    @ZenMethod
    fun hasStack(shape: HiiragiShape): Boolean = PartDictionary.hasStack(shape)

    @JvmStatic
    @ZenMethod
    fun getStacks(shape: HiiragiShape, count: Int): List<IItemStack> =
        PartDictionary.getStacks(shape, count).toIItemStacks()

    //    CTHiiragiMaterial    //

    @JvmStatic
    @ZenMethod
    fun hasStack(material: HiiragiMaterial): Boolean = PartDictionary.hasStack(material)

    @JvmStatic
    @ZenMethod
    fun getStacks(material: HiiragiMaterial, count: Int): List<IItemStack> =
        PartDictionary.getStacks(material, count).toIItemStacks()

}