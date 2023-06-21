package hiiragi283.material.part

import hiiragi283.material.RagiMaterials
import hiiragi283.material.material.HiiragiMaterial
import net.minecraft.client.resources.I18n
import rechellatek.snakeToLowerCamelCase

data class HiiragiPart(val name: String, val scale: Double) {

    var translationKey: String = "item.${RagiMaterials.MODID}.$name.name"
    var translatedName: (HiiragiMaterial) -> String = { I18n.format(translationKey, it.translatedName) }

    companion object {
        @JvmField
        val EMPTY = HiiragiPart("empty", 0.0)
    }

    fun isEmpty(): Boolean = this.name == "empty"

    fun getOreDictPrefix() = name.snakeToLowerCamelCase()

}