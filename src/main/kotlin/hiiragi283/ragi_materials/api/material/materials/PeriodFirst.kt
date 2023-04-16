package hiiragi283.ragi_materials.api.material.materials

import hiiragi283.ragi_materials.api.material.ElementRegistry
import hiiragi283.ragi_materials.api.material.MaterialRegistry
import hiiragi283.ragi_materials.api.material.RagiMaterial
import hiiragi283.ragi_materials.api.material.type.TypeRegistry
import hiiragi283.ragi_materials.client.color.ColorManager
import hiiragi283.ragi_materials.client.color.RagiColor
import net.minecraft.item.EnumRarity

object PeriodFirst {

    fun load() {
        //Pre-registration
        MaterialRegistry.HYDROXIDE = RagiMaterial.Builder(-1, "hydroxide", TypeRegistry.INTERNAL).setComponents(listOf(ElementRegistry.OXYGEN to 1, ElementRegistry.HYDROGEN to 1)).build()
        MaterialRegistry.CARBONATE = RagiMaterial.Builder(-1, "carbonate", TypeRegistry.INTERNAL).setComponents(listOf(ElementRegistry.CARBON to 1, ElementRegistry.OXYGEN to 3)).build()
        MaterialRegistry.NITRATE = RagiMaterial.Builder(-1, "nitrate", TypeRegistry.INTERNAL).setComponents(listOf(ElementRegistry.NITROGEN to 1, ElementRegistry.OXYGEN to 3)).build()
        MaterialRegistry.PHOSPHATE = RagiMaterial.Builder(-1, "phosphate", TypeRegistry.INTERNAL).setComponents(listOf(ElementRegistry.PHOSPHORUS to 1, ElementRegistry.OXYGEN to 4)).build()
        MaterialRegistry.SULFATE = RagiMaterial.Builder(-1, "sulfate", TypeRegistry.INTERNAL).setComponents(listOf(ElementRegistry.SULFUR to 1, ElementRegistry.OXYGEN to 4)).build()
        MaterialRegistry.TUNGSTATE = RagiMaterial.Builder(-1, "tungstate", TypeRegistry.INTERNAL).setComponents(listOf(ElementRegistry.TUNGSTEN to 1, ElementRegistry.OXYGEN to 4)).build()

        //10 ~ 19: Hydrogen
        MaterialRegistry.HYDROGEN = RagiMaterial.Builder(10, "hydrogen", TypeRegistry.GAS).setSimple(ElementRegistry.HYDROGEN to 2).build()

        MaterialRegistry.WATER = RagiMaterial.Builder(11, "water", TypeRegistry.INTERNAL).setComponents(listOf(ElementRegistry.HYDROGEN to 2, ElementRegistry.OXYGEN to 1)).apply {
            color = RagiColor.BLUE
            tempBoil = 100
            tempMelt = 0
        }.build()

        MaterialRegistry.SNOW = RagiMaterial.Builder(12, "snow", TypeRegistry.INGOT).setComponents(listOf(MaterialRegistry.WATER to 1)).apply {
            color = RagiColor.WHITE
        }.build()

        MaterialRegistry.ICE = RagiMaterial.Builder(13, "ice", TypeRegistry.INGOT).setComponents(listOf(MaterialRegistry.WATER to 1)).apply {
            color = ColorManager.mixColor(RagiColor.AQUA, RagiColor.WHITE)
        }.build()

        MaterialRegistry.DEUTERIUM = RagiMaterial.Builder(14, "deuterium", TypeRegistry.GAS).setSimple(ElementRegistry.DEUTERIUM to 2).apply {
            rarity = EnumRarity.EPIC
        }.build()

        MaterialRegistry.TRITIUM = RagiMaterial.Builder(15, "tritium", TypeRegistry.GAS).setSimple(ElementRegistry.TRITIUM to 2).apply {
            rarity = EnumRarity.EPIC
        }.build()

        //20 ~ 29: Helium, Neon, Argon
        MaterialRegistry.HELIUM = RagiMaterial.Builder(20, "helium", TypeRegistry.GAS).setSimple(ElementRegistry.HELIUM to 1).apply {
            rarity = EnumRarity.RARE
        }.build()

        MaterialRegistry.NEON = RagiMaterial.Builder(21, "neon", TypeRegistry.GAS).setSimple(ElementRegistry.NEON to 1).apply {
            rarity = EnumRarity.RARE
        }.build()

        MaterialRegistry.ARGON = RagiMaterial.Builder(22, "argon", TypeRegistry.GAS).setSimple(ElementRegistry.ARGON to 1).apply {
            rarity = EnumRarity.RARE
        }.build()
    }
}