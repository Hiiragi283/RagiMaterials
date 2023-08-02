package hiiragi283.material.api.shape

import hiiragi283.material.api.HiiragiEntry
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.util.HiiragiModelManager
import net.minecraft.client.resources.I18n
import rechellatek.snakeToLowerCamelCase

/**
 * Creates new shape
 */
fun shapeOf(
    name: String,
    scale: Double,
    model: (HiiragiEntry<*>) -> Unit = { HiiragiModelManager.setModelSame(it.asItem()) },
    recipe: (HiiragiEntry<*>, HiiragiMaterial) -> Unit = { _, _ -> },
): HiiragiShape {
    return object : HiiragiShape(name, scale) {

        override fun registerModel(entry: HiiragiEntry<*>) = model(entry)

        override fun registerRecipe(entry: HiiragiEntry<*>, material: HiiragiMaterial) = recipe(entry, material)

    }
}

/**
 * An object which represents the shape of [net.minecraft.item.Item]
 *
 * Should be registered in [net.minecraftforge.event.RegistryEvent.Register]<[HiiragiShape]>
 */

abstract class HiiragiShape internal constructor(val name: String, val scale: Double) {

    /**
     * Registers model on [net.minecraftforge.client.event.ModelRegistryEvent]
     */
    abstract fun registerModel(entry: HiiragiEntry<*>)

    /**
     * Registers recipes for [entry] with [material]
     */
    abstract fun registerRecipe(entry: HiiragiEntry<*>, material: HiiragiMaterial)

    companion object {
        @JvmField
        val EMPTY = shapeOf("empty", 0.0)
    }

    /**
     * Returns true if [name] equals "empty"
     */
    fun isEmpty(): Boolean = this.name == "empty"

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
        I18n.format("shape.$name", I18n.format(material.translationKey))

    /**
     * Returns true if [scale] is bigger than 0.0
     */
    fun hasScale(): Boolean = scale > 0.0

    /**
     * Returns true if [HiiragiMaterial.validShapes] contains this [name]
     */
    fun isValid(material: HiiragiMaterial): Boolean = name in material.validShapes

}