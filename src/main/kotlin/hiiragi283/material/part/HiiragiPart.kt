package hiiragi283.material.part

import hiiragi283.material.RagiMaterials
import hiiragi283.material.item.ItemMaterialBase
import hiiragi283.material.material.HiiragiMaterial
import hiiragi283.material.util.RMModelManager
import net.minecraft.client.resources.I18n
import rechellatek.snakeToLowerCamelCase

class HiiragiPart private constructor(val name: String, val scale: Double) {

    var isMatch: (HiiragiMaterial) -> Boolean = { true }
    var model: (ItemMaterialBase) -> Unit = { RMModelManager.setModelSame(it) }
    var recipe: (ItemMaterialBase, HiiragiMaterial) -> Unit = { _, _ -> }

    var translationKey: String = "item.${RagiMaterials.MODID}.$name.name"
    var translatedName: (HiiragiMaterial) -> String = { I18n.format(translationKey, it.translatedName) }

    companion object {
        @JvmField
        val EMPTY = HiiragiPart("empty", 0.0)
    }

    fun isEmpty(): Boolean = this.name == "empty"

    fun getOreDictPrefix() = name.snakeToLowerCamelCase()

    class Builder(val name: String, val scale: Double) {

        fun build(init: HiiragiPart.() -> Unit = {}): HiiragiPart {
            val part = HiiragiPart(name, scale)
            part.init()
            return part
        }

    }

}