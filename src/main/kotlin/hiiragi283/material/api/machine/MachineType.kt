package hiiragi283.material.api.machine

import hiiragi283.material.api.block.ModuleMachineBlock
import hiiragi283.material.api.item.RecipeModuleItem
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.registry.HiiragiRegistries
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

    fun getTranslatedName(material: HiiragiMaterial): String =
        I18n.format(translationKey, material.getTranslatedName())

    companion object {

        fun createMachineBlock() {
            values()
                .filter { it != NONE }
                .map(::ModuleMachineBlock)
                .forEach(HiiragiRegistries.BLOCK::register)
        }

        fun createRecipeModule() {
            values()
                .filter { it != NONE }
                .map(::RecipeModuleItem)
                .forEach(HiiragiRegistries.ITEM::register)
        }

        fun from(name: String): MachineType? = values().firstOrNull { it.name == name }

    }

}