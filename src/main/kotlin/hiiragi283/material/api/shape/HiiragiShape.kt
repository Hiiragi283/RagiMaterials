package hiiragi283.material.api.shape

import hiiragi283.api.material.HiiragiMaterial
import net.minecraft.client.resources.I18n
import rechellatek.snakeToLowerCamelCase

/**
 * An object which represents the shape of [net.minecraft.item.Item]
 *
 * Should be registered in [net.minecraftforge.event.RegistryEvent.Register]<[HiiragiShape]>
 */

class HiiragiShape(val name: String, val scale: Int) {

    companion object {
        @JvmField
        val EMPTY = HiiragiShape("empty", 0)
    }

    fun isEmpty(): Boolean = this == EMPTY || this.name == "empty"

    /**
     * Returns primal Ore Dictionary name with given [material]
     * @return a String value
     */
    fun getOreDict(material: HiiragiMaterial): String = StringBuilder().also {
        it.append(name.snakeToLowerCamelCase())
        it.append(material.getOreDictName())
    }.toString()

    /**
     * Returns all Ore Dictionary name with given [material]
     * @return a list of String
     */
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

    /**
     * Returns translated name with given [material]
     */
    fun getTranslatedName(material: HiiragiMaterial): String =
        I18n.format("hiiragi_shape.$name", material.getTranslatedName())

    /**
     * Returns true if [scale] is bigger than 0
     */
    fun hasScale(): Boolean = scale > 0

    /**
     * Returns true if [HiiragiMaterial.validShapes] contains this [name]
     */
    fun isValid(material: HiiragiMaterial): Boolean = name in material.validShapes

}