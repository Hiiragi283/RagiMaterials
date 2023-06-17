package hiiragi283.material.part

import hiiragi283.material.RagiMaterials
import hiiragi283.material.material.HiiragiMaterial
import net.minecraft.client.resources.I18n
import rechellatek.snakeToLowerCamelCase

data class HiiragiPart(val name: String, val scale: Double) {

    companion object {
        @JvmField
        val EMPTY = HiiragiPart("empty", 0.0)
    }

    fun isEmpty(): Boolean = this.name == "empty"

    fun getOreDictPrefix() = name.snakeToLowerCamelCase()

    fun getTranslatedName(
        material: HiiragiMaterial,
        name: (HiiragiMaterial) -> String = { I18n.format(getTranslationKey(), it.getTranslatedName()) }
    ): String = name(material)

    fun getTranslationKey(key: () -> String = { "item.${RagiMaterials.MODID}.$name.name" }): String = key()

}