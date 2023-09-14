package hiiragi283.material.api.shape

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.registry.HiiragiRegistries
import net.minecraft.client.resources.I18n
import rechellatek.snakeToLowerCamelCase

/**
 * An object which represents the shape of [net.minecraft.item.Item]
 *
 * Should be registered in [net.minecraftforge.event.RegistryEvent.Register]<[HiiragiShape]>
 */

class HiiragiShape(val name: String, val scale: Int) {

    fun getOreDict(material: HiiragiMaterial): String = StringBuilder().also {
        it.append(name.snakeToLowerCamelCase())
        it.append(material.getOreDictName())
    }.toString()

    fun getOreDicts(material: HiiragiMaterial): List<String> {
        val list: MutableList<String> = mutableListOf()
        list.add(getOreDict(material))
        if (material.hasOreDictAlt()) {
            material.getOreDictNameAlt().forEach { oreDict ->
                list.add(StringBuilder().also {
                    it.append(name.snakeToLowerCamelCase())
                    it.append(oreDict)
                }.toString())
            }
        }
        return list.filter(String::isNotEmpty)
    }

    fun getTranslatedName(material: HiiragiMaterial): String =
        I18n.format("hiiragi_shape.$name", material.getTranslatedName())

    fun hasScale(): Boolean = scale > 0

    fun isValid(material: HiiragiMaterial): Boolean = this in material.shapeType.shapes

    override fun toString(): String = "Shape:$name"

    //    Registration    //

    fun register() {
        HiiragiRegistries.SHAPE.register(name, this)
    }

}