package hiiragi283.material.api.part

import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.item.ItemMaterial
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.util.RMModelManager
import net.minecraft.client.resources.I18n
import rechellatek.snakeToLowerCamelCase

fun partOf(name: String, scale: Double, init: HiiragiPart.() -> Unit = {}): HiiragiPart {
    val part = HiiragiPart(name, scale)
    part.init()
    return part
}

class HiiragiPart internal constructor(val name: String, val scale: Double) {

    var isMatch: (HiiragiMaterial) -> Boolean = { true }
    var model: (ItemMaterial) -> Unit = { RMModelManager.setModelSame(it) }
    var recipe: (ItemMaterial, HiiragiMaterial) -> Unit = { _, _ -> }

    var translationKey: String = "item.${RagiMaterials.MODID}.$name.name"
    var translatedName: (HiiragiMaterial) -> String = { I18n.format(translationKey, it.translatedName) }

    companion object {
        @JvmField
        val EMPTY = HiiragiPart("empty", 0.0)
    }

    fun isEmpty(): Boolean = this.name == "empty"

    fun getOreDictPrefix() = name.snakeToLowerCamelCase()

    fun getOreDict(material: HiiragiMaterial): String = StringBuilder().also {
        it.append(this.getOreDictPrefix())
        it.append(material.getOreDictName())
    }.toString()

}