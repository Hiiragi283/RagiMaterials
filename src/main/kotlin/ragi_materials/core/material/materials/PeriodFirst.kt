package ragi_materials.core.material.materials

import net.minecraft.item.EnumRarity
import ragi_materials.core.material.ElementRegistry
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.material.type.TypeRegistry
import ragi_materials.core.util.RagiColor

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
        MaterialRegistry.HYDROGEN = RagiMaterial.Builder(10, "hydrogen", TypeRegistry.GAS).setSimple(ElementRegistry.HYDROGEN to 2).buildAndRegister()

        MaterialRegistry.WATER = RagiMaterial.Builder(11, "water", TypeRegistry.INTERNAL).setComponents(listOf(ElementRegistry.HYDROGEN to 2, ElementRegistry.OXYGEN to 1)).apply {
            color = RagiColor.BLUE
            tempBoil = 100
            tempMelt = 0
        }.buildAndRegister()

        MaterialRegistry.DEUTERIUM = RagiMaterial.Builder(12, "deuterium", TypeRegistry.GAS).setSimple(ElementRegistry.DEUTERIUM to 2).apply {
            rarity = EnumRarity.EPIC
        }.build()

        MaterialRegistry.TRITIUM = RagiMaterial.Builder(13, "tritium", TypeRegistry.GAS).setSimple(ElementRegistry.TRITIUM to 2).apply {
            rarity = EnumRarity.EPIC
        }.build()

        //20 ~ 29: Helium
        MaterialRegistry.HELIUM = RagiMaterial.Builder(20, "helium", TypeRegistry.GAS).setSimple(ElementRegistry.HELIUM to 1).apply {
            rarity = EnumRarity.RARE
        }.buildAndRegister()
    }
}