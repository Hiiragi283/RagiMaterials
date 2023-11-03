package hiiragi283.material.api.shape

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import hiiragi283.material.api.event.ShapeBuildEvent
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.part.PartConvertible
import hiiragi283.material.api.part.PartDictionary
import hiiragi283.material.api.registry.HiiragiRegistry
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.util.HiiragiJsonSerializable
import hiiragi283.material.util.HiiragiLogger
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraftforge.common.MinecraftForge
import rechellatek.snakeToLowerCamelCase
import java.util.function.Function
import java.util.function.Predicate

/**
 * An object which represents the shape of [net.minecraft.item.Item]
 *
 * Should be registered in [net.minecraftforge.event.RegistryEvent.Register]<[HiiragiShape]>
 */

data class HiiragiShape(
    val name: String,
    private val scaleFunction: Function<HiiragiMaterial, Int>,
    private val itemPredicate: Predicate<HiiragiMaterial>
) : HiiragiJsonSerializable {

    companion object {

        private val ingotScales: MutableMap<HiiragiMaterial, Int> = mutableMapOf()

        @JvmStatic
        fun getIngotScale(material: HiiragiMaterial): Int = ingotScales.getOrDefault(material, 144)

        @JvmStatic
        fun setIngotScale(material: HiiragiMaterial, scale: Int) = ingotScales.put(material, scale)

        private val blockMultiplier: MutableMap<HiiragiMaterial, Int> = mutableMapOf()

        @JvmStatic
        fun getBlockMultiplier(material: HiiragiMaterial): Int = blockMultiplier.getOrDefault(material, 9)

        @JvmStatic
        fun setBlockMultiplier(material: HiiragiMaterial, multiplier: Int) = blockMultiplier.put(material, multiplier)

        @JvmStatic
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

    fun getIngotCount(material: HiiragiMaterial) = getScale(material) / HiiragiShapes.INGOT.getScale(material)

    fun getItem(): PartConvertible.ITEM? = PartConvertible.ITEM[this]

    fun getItemStack(material: HiiragiMaterial, count: Int = 1): ItemStack = getPart(material).getItemStack(count)

    fun getItemStacks(count: Int = 1): List<ItemStack> = PartDictionary.getStacks(this, count)

    fun getOreDict(material: HiiragiMaterial): String = name.snakeToLowerCamelCase() + material.getOreDictName()

    fun getPart(material: HiiragiMaterial): HiiragiPart = HiiragiPart(this, material)

    fun getScale(material: HiiragiMaterial): Int = scaleFunction.apply(material)

    fun getTranslatedName(material: HiiragiMaterial): String =
        I18n.format("hiiragi_shape.$name", material.getTranslatedName())

    //    Predicate    //

    fun canCreateMaterialItem(material: HiiragiMaterial): Boolean = itemPredicate.test(material)

    fun hasItemStack(): Boolean = PartDictionary.hasStack(this)

    fun hasItemStack(material: HiiragiMaterial): Boolean = PartDictionary.hasStack(getPart(material))

    //    Function    //

    fun addAlternativeName(vararg name: String) = also {
        name.forEach { REGISTRY[it] = this }
    }

    //    Any    //

    override fun toString(): String = "Shape:$name"

    //    HiiragiJsonSerializable    //

    override fun getJsonElement(): JsonElement = JsonObject().apply { this.addProperty("name", name) }

    //    Builder    //

    class Builder(val name: String) {

        var scaleFunction: Function<HiiragiMaterial, Int> = Function { 0 }
        var itemPredicate: Predicate<HiiragiMaterial> =
            Predicate { it.shapeType.map(HiiragiShape::name).contains(name) }

        fun build(): HiiragiShape {
            MinecraftForge.EVENT_BUS.post(ShapeBuildEvent(this))
            return HiiragiShape(name, scaleFunction, itemPredicate).also { shape ->
                REGISTRY[name] = shape
                HiiragiLogger.debugInfo("Shape: $name is registered!")
            }
        }

    }

}