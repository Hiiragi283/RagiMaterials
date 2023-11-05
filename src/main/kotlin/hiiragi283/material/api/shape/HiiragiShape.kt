package hiiragi283.material.api.shape

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import crafttweaker.CraftTweakerAPI
import crafttweaker.annotations.ZenRegister
import crafttweaker.api.item.IItemStack
import hiiragi283.material.RMReference
import hiiragi283.material.api.event.ShapeBuildEvent
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.material.MaterialPredicate
import hiiragi283.material.api.material.MaterialScaleFunction
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.part.PartConvertible
import hiiragi283.material.api.part.PartDictionary
import hiiragi283.material.api.registry.HiiragiRegistry
import hiiragi283.material.compat.crt.HiiragiAction
import hiiragi283.material.compat.crt.toIItemStack
import hiiragi283.material.compat.crt.toIItemStacks
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.util.HiiragiJsonSerializable
import hiiragi283.material.util.HiiragiLogger
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraftforge.common.MinecraftForge
import rechellatek.snakeToLowerCamelCase
import stanhebben.zenscript.annotations.ZenClass
import stanhebben.zenscript.annotations.ZenMethod
import stanhebben.zenscript.annotations.ZenProperty

/**
 * An object which represents the shape of [net.minecraft.item.Item]
 *
 * Should be registered in [net.minecraftforge.event.RegistryEvent.Register]<[HiiragiShape]>
 */

@ZenClass("${RMReference.MOD_ID}.shape.HiiragiShape")
@ZenRegister
data class HiiragiShape(
    @JvmField @ZenProperty val name: String,
    private val scaleFunction: MaterialScaleFunction,
    private val itemPredicate: MaterialPredicate
) : HiiragiJsonSerializable {

    companion object {

        private val ingotScales: MutableMap<HiiragiMaterial, Int> = mutableMapOf()

        @JvmStatic
        @ZenMethod
        fun getIngotScale(material: HiiragiMaterial): Int = ingotScales.getOrDefault(material, 144)

        @JvmStatic
        fun setIngotScale(material: HiiragiMaterial, scale: Int) = ingotScales.put(material, scale)

        @JvmStatic
        @ZenMethod("setIngotScale")
        fun setIngotScaleCT(material: HiiragiMaterial, scale: Int) {
            CraftTweakerAPI.apply(HiiragiAction("Setting ingot scale for the material: $material with $scale mb") {
                setIngotScale(material, scale)
            })
        }

        private val blockMultiplier: MutableMap<HiiragiMaterial, Int> = mutableMapOf()

        @JvmStatic
        @ZenMethod
        fun getBlockMultiplier(material: HiiragiMaterial): Int = blockMultiplier.getOrDefault(material, 9)

        @JvmStatic
        fun setBlockMultiplier(material: HiiragiMaterial, multiplier: Int) = blockMultiplier.put(material, multiplier)

        @JvmStatic
        @ZenMethod("setBlockMultiplier")
        fun setBlockMultiplierCT(material: HiiragiMaterial, multiplier: Int) {
            CraftTweakerAPI.apply(HiiragiAction("Setting block multiplier for the material: $material with x$multiplier") {
                setBlockMultiplier(material, multiplier)
            })
        }

        @JvmStatic
        @ZenMethod
        fun getBlockScale(material: HiiragiMaterial): Int = getIngotScale(material) * getBlockMultiplier(material)

        fun build(name: String, init: Builder.() -> Unit = {}): HiiragiShape = Builder(name).apply(init).build()

    }

    //    Registry    //

    object REGISTRY : HiiragiRegistry<String, HiiragiShape>("Shape") {

        internal fun init() {

            val nameSorted: List<Pair<String, HiiragiShape>> = registry.toList()
                .sortedBy { (name: String, _: HiiragiShape) -> name }
            registry.clear()
            registry.putAll(nameSorted)

            isLocked = true

        }

    }

    //    Conversion    //

    @ZenMethod
    fun getIngotCount(material: HiiragiMaterial): Int = getScale(material) / HiiragiShapes.INGOT.getScale(material)

    fun getItem(): PartConvertible.ITEM? = PartConvertible.ITEM[this]

    fun getItemStack(material: HiiragiMaterial, count: Int = 1): ItemStack = getPart(material).getItemStack(count)

    fun getItemStacks(count: Int = 1): List<ItemStack> = PartDictionary.getStacks(this, count)

    fun getOreDict(material: HiiragiMaterial): String = name.snakeToLowerCamelCase() + material.getOreDictName()

    fun getPart(material: HiiragiMaterial): HiiragiPart = HiiragiPart(this, material)

    @ZenMethod
    fun getScale(material: HiiragiMaterial): Int = scaleFunction.apply(material)

    fun getTranslatedName(material: HiiragiMaterial): String =
        I18n.format("hiiragi_shape.$name", material.getTranslatedName())

    //    Predicate    //

    fun canCreateMaterialItem(material: HiiragiMaterial): Boolean = itemPredicate.test(material)

    fun hasItemStack(): Boolean = PartDictionary.hasStack(this)

    fun hasItemStack(material: HiiragiMaterial): Boolean = PartDictionary.hasStack(getPart(material))

    //    Function    //

    @ZenMethod
    fun addAlternativeName(vararg name: String) = also {
        name.forEach { REGISTRY[it] = this }
    }

    //    Any    //

    override fun toString(): String = "Shape:$name"

    //    HiiragiJsonSerializable    //

    override fun getJsonElement(): JsonElement = JsonObject().apply { this.addProperty("name", name) }

    //    CraftTweaker    //

    @ZenMethod
    fun getIItemStack(material: HiiragiMaterial, count: Int): IItemStack = getItemStack(material, count).toIItemStack()

    @ZenMethod
    fun getIItemStacks(count: Int): List<IItemStack> = getItemStacks(count).toIItemStacks()

    //    Builder    //

    @ZenClass("${RMReference.MOD_ID}.shape.ShapeBuilder")
    @ZenRegister
    class Builder(@JvmField @ZenProperty val name: String) {

        @JvmField
        @ZenProperty
        var scaleFunction: MaterialScaleFunction = MaterialScaleFunction { 0 }

        @JvmField
        @ZenProperty
        var itemPredicate: MaterialPredicate = MaterialPredicate {
            it.shapeType.map(HiiragiShape::name).contains(name)
        }

        fun build(): HiiragiShape {
            MinecraftForge.EVENT_BUS.post(ShapeBuildEvent(this))
            return HiiragiShape(name, scaleFunction, itemPredicate).also { shape ->
                REGISTRY[name] = shape
                HiiragiLogger.debugInfo("Shape: $name is registered!")
            }
        }

    }

}