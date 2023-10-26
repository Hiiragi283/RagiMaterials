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

/**
 * An object which represents the shape of [net.minecraft.item.Item]
 *
 * Should be registered in [net.minecraftforge.event.RegistryEvent.Register]<[HiiragiShape]>
 */

data class HiiragiShape(val name: String, val scale: Int) : HiiragiJsonSerializable {

    fun getItem(): PartConvertible.ITEM? = HiiragiRegistries.MATERIAL_ITEM.getValue(this)

    fun getItemStack(material: HiiragiMaterial, count: Int = 1): ItemStack = getPart(material).getItemStack(count)

    fun getItemStack(count: Int = 1): ItemStack? = ShapeDictionary.getPrimalStack(this, count)

    fun getItemStacks(count: Int = 1): List<ItemStack> = ShapeDictionary.getItemStacks(this, count)

    fun getOreDict(material: HiiragiMaterial): String = name.snakeToLowerCamelCase() + material.getOreDictName()

    fun getOreDictAlts(material: HiiragiMaterial): List<String> = material
        .getOreDictNameAlt()
        .map { name.snakeToLowerCamelCase() + it }

    fun getPart(material: HiiragiMaterial): HiiragiPart = HiiragiPart(this, material)

    fun getTranslatedName(material: HiiragiMaterial): String =
        I18n.format("hiiragi_shape.$name", material.getTranslatedName())

    fun hasItemStack(): Boolean = ShapeDictionary.hasItemStack(this)

    fun hasItemStack(material: HiiragiMaterial): Boolean = PartDictionary.hasItemStack(getPart(material))

    fun hasScale(): Boolean = scale > 0

    fun canCreateMaterialItem(material: HiiragiMaterial): Boolean =
        material.shapeType == HiiragiShapeTypes.WILDCARD || this in material.shapeType.shapes

    override fun toString(): String = "Shape:$name"

    //    Registration    //

    fun register() {
        HiiragiRegistries.SHAPE.register(name, this)
    }

    //    HiiragiJsonSerializable    //

    override fun getJsonElement(): JsonElement {
        val root = JsonObject()
        root.addProperty("name", name)
        root.addProperty("scale", scale)
        return root
    }

}