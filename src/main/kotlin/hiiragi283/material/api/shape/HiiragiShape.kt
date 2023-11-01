package hiiragi283.material.api.shape

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import hiiragi283.material.api.event.ShapeRegistryEvent
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.part.PartConvertible
import hiiragi283.material.api.part.PartDictionary
import hiiragi283.material.api.registry.HiiragiRegistry
import hiiragi283.material.init.HiiragiShapeTypes
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.util.HiiragiJsonSerializable
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraftforge.common.MinecraftForge
import rechellatek.snakeToLowerCamelCase
import java.util.function.Function

/**
 * An object which represents the shape of [net.minecraft.item.Item]
 *
 * Should be registered in [net.minecraftforge.event.RegistryEvent.Register]<[HiiragiShape]>
 */

data class HiiragiShape(val name: String, private val scaleFunction: Function<HiiragiMaterial, Int>) :
    HiiragiJsonSerializable {

    constructor(name: String, scaleDefault: Int) : this(name, { scaleDefault })

    private val scaleMap: MutableMap<HiiragiMaterial, Int> = mutableMapOf()

    private val prefixAlts: MutableSet<String> = mutableSetOf()

    //    Registry    //

    fun register() {
        REGISTRY[name] = this
        prefixAlts.forEach { prefixAlt: String ->
            REGISTRY[prefixAlt] = this
        }
    }

    object REGISTRY : HiiragiRegistry<String, HiiragiShape>("Shape") {

        internal fun init() {

            val event = ShapeRegistryEvent()
            MinecraftForge.EVENT_BUS.post(event)

            val nameSorted: List<Pair<String, HiiragiShape>> = registry.toList()
                .sortedBy { (name: String, _: HiiragiShape) -> name }
            registry.clear()
            registry.putAll(nameSorted)

            isLocked = true

        }

    }


    //    Conversion    //

    fun getBlockCount(material: HiiragiMaterial) = getScale(material) / HiiragiShapes.BLOCK.getScale(material)

    fun getIngotCount(material: HiiragiMaterial) = getScale(material) / HiiragiShapes.INGOT.getScale(material)

    fun getNuggetCount(material: HiiragiMaterial) = getScale(material) / HiiragiShapes.NUGGET.getScale(material)

    fun getItem(): PartConvertible.ITEM? = PartConvertible.ITEM[this]

    fun getItemStack(material: HiiragiMaterial, count: Int = 1): ItemStack = getPart(material).getItemStack(count)

    fun getItemStacks(count: Int = 1): List<ItemStack> = PartDictionary.getStacks(this, count)

    fun getOreDict(material: HiiragiMaterial): String = name.snakeToLowerCamelCase() + material.getOreDictName()

    fun getPart(material: HiiragiMaterial): HiiragiPart = HiiragiPart(this, material)

    fun getScale(material: HiiragiMaterial): Int = scaleMap[material] ?: scaleFunction.apply(material)

    fun getTranslatedName(material: HiiragiMaterial): String =
        I18n.format("hiiragi_shape.$name", material.getTranslatedName())

    //    Predicate    //

    fun canCreateMaterialItem(material: HiiragiMaterial): Boolean =
        material.shapeType == HiiragiShapeTypes.WILDCARD || this in material.shapeType.shapes

    fun hasItemStack(): Boolean = PartDictionary.hasStack(this)

    fun hasItemStack(material: HiiragiMaterial): Boolean = PartDictionary.hasStack(getPart(material))

    //    Setter    //

    fun setScale(material: HiiragiMaterial, scale: Int) = also {
        scaleMap[material] = scale
    }

    fun addPrefixAlt(prefix: String) = also {
        prefixAlts.add(prefix)
    }

    fun removePrefixAlt(prefix: String) = also {
        prefixAlts.remove(prefix)
    }

    //    Any    //

    override fun toString(): String = "Shape:$name"

    //    HiiragiJsonSerializable    //

    override fun getJsonElement(): JsonElement = JsonObject().apply { this.addProperty("name", name) }

}