package hiiragi283.material.api.machine

import hiiragi283.material.api.material.HiiragiMaterial
import net.minecraft.client.resources.I18n

enum class MachineType {
    ASSEMBLER,
    CENTRIFUGE,
    COMPRESSOR,
    CUTTING,
    DISTILLER,
    ELECTROLYZER,
    FREEZER,
    GRINDER,
    METAL_FORMER,
    MIXER,
    ROCK_GENERATOR,
    SMELTER,
    TEST,
    NONE;

    val translationKey: String = "hiiragi_machine.${this.lowercase()}"

    fun lowercase() = name.lowercase()

    fun getTranslatedName(material: HiiragiMaterial): String = getTranslatedName(material.getTranslatedName())

    fun getTranslatedName(name: String): String = I18n.format(translationKey, name)

    companion object {

        fun from(name: String): MachineType? = values().firstOrNull { it.name == name }

    }

}