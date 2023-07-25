package hiiragi283.material.api.shape

import hiiragi283.material.api.item.ItemMaterial
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.util.RMModelManager
import net.minecraft.client.resources.I18n
import rechellatek.snakeToLowerCamelCase

fun shapeOf(
    name: String,
    scale: Double,
    model: (ItemMaterial) -> Unit = { RMModelManager.setModelSame(it) },
    recipe: (ItemMaterial, HiiragiMaterial) -> Unit = { _, _ -> },
): HiiragiShape {
    return object : HiiragiShape(name, scale) {

        override fun getModel(item: ItemMaterial) = model(item)

        override fun getRecipe(item: ItemMaterial, material: HiiragiMaterial) = recipe(item, material)

    }
}

abstract class HiiragiShape internal constructor(val name: String, val scale: Double) {

    abstract fun getModel(item: ItemMaterial)

    abstract fun getRecipe(item: ItemMaterial, material: HiiragiMaterial)

    companion object {
        @JvmField
        val EMPTY = shapeOf("empty", 0.0)
    }

    fun isEmpty(): Boolean = this.name == "empty"

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


    fun getTranslatedName(material: HiiragiMaterial): String = I18n.format("shape.$name", material.translatedName)

    fun hasScale(): Boolean = scale > 0.0

    fun isValid(material: HiiragiMaterial): Boolean = name in material.validShapes

}