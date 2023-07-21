package hiiragi283.material.api.shape

import hiiragi283.material.api.item.ItemMaterial
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.util.RMModelManager
import net.minecraft.client.resources.I18n
import rechellatek.snakeToLowerCamelCase
import kotlin.math.roundToInt

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

    open fun getModel(item: ItemMaterial): Unit = RMModelManager.setModelSame(item)

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

    fun getOreDictAlt(material: HiiragiMaterial): String =
        if (material.hasOreDictAlt()) StringBuilder().also {
            it.append(name.snakeToLowerCamelCase())
            it.append(material.getOreDictNameAlt())
        }.toString() else ""

    fun getOreDicts(material: HiiragiMaterial): List<String> =
        listOf(getOreDict(material), getOreDictAlt(material)).filter(String::isNotEmpty)

    fun getTranslatedName(material: HiiragiMaterial): String = I18n.format("shape.$name", material.translatedName)

    fun getWeight(material: HiiragiMaterial): Double = (material.molar * scale * 10.0).roundToInt() / 10.0

    fun hasScale(): Boolean = scale > 0.0

    fun isValid(material: HiiragiMaterial): Boolean = name in material.validShapes

}