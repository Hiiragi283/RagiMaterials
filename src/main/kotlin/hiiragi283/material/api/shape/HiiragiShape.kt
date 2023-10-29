package hiiragi283.material.api.shape

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.part.PartConvertible
import hiiragi283.material.api.part.PartDictionary
import hiiragi283.material.init.HiiragiRegistries
import hiiragi283.material.init.HiiragiShapeTypes
import hiiragi283.material.util.HiiragiJsonSerializable
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import rechellatek.snakeToLowerCamelCase
import java.util.function.IntFunction

/**
 * An object which represents the shape of [net.minecraft.item.Item]
 *
 * Should be registered in [net.minecraftforge.event.RegistryEvent.Register]<[HiiragiShape]>
 */

data class HiiragiShape(val name: String, private val scaleDefault: Int) : HiiragiJsonSerializable {

    private val scaleMap: MutableMap<HiiragiMaterial, Int> = mutableMapOf()

    private val prefixAlts: MutableSet<String> = mutableSetOf()

    //    Conversion    //

    fun getBlockCount(material: HiiragiMaterial) = getIngotCount(material) / 9

    fun getIngotCount(material: HiiragiMaterial) = getScale(material) / 144

    fun getNuggetCount(material: HiiragiMaterial) = getIngotCount(material) * 9

    fun getItem(): PartConvertible.ITEM? = HiiragiRegistries.MATERIAL_ITEM.getValue(this)

    fun getItemStack(material: HiiragiMaterial, count: Int = 1): ItemStack = getPart(material).getItemStack(count)

    fun getItemStack(count: Int = 1): ItemStack? = PartDictionary.getStack(this, count)

    fun getItemStacks(count: Int = 1): List<ItemStack> = PartDictionary.getStacks(this, count)

    fun getOreDict(material: HiiragiMaterial): String = name.snakeToLowerCamelCase() + material.getOreDictName()

    fun getPart(material: HiiragiMaterial): HiiragiPart = HiiragiPart(this, material)

    fun getScale(material: HiiragiMaterial): Int = scaleMap[material] ?: scaleDefault

    fun getTranslatedName(material: HiiragiMaterial): String =
        I18n.format("hiiragi_shape.$name", material.getTranslatedName())

    //    Predicate    //

    fun canCreateMaterialItem(material: HiiragiMaterial): Boolean =
        material.shapeType == HiiragiShapeTypes.WILDCARD || this in material.shapeType.shapes

    fun hasItemStack(): Boolean = PartDictionary.hasStack(this)

    fun hasItemStack(material: HiiragiMaterial): Boolean = PartDictionary.hasStack(getPart(material))

    //    Setter    //

    fun setScale(material: HiiragiMaterial, function: IntFunction<Int>) = also {
        setScale(material, function.apply(scaleDefault))
    }

    fun setScale(material: HiiragiMaterial, scale: Int) = also {
        scaleMap[material] = scale
    }

    fun addPrefixAlt(prefix: String) = also {
        prefixAlts.add(prefix)
    }

    fun removePrefixAlt(prefix: String) = also {
        prefixAlts.remove(prefix)
    }

    //    Registration    //

    fun register() {
        HiiragiRegistries.SHAPE.register(name, this)
        prefixAlts.forEach { prefixAlt: String ->
            HiiragiRegistries.SHAPE.register(prefixAlt, this)
        }
    }

    //    Any    //

    override fun toString(): String = "Shape:$name"

    //    HiiragiJsonSerializable    //

    override fun getJsonElement(): JsonElement {
        val root = JsonObject()
        root.addProperty("name", name)
        root.addProperty("scale", scaleDefault)
        return root
    }

}