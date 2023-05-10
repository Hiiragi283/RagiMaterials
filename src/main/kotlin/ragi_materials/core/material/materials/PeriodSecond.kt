package ragi_materials.core.material.materials

import net.minecraft.item.EnumRarity
import ragi_materials.core.material.ElementRegistry
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.material.type.EnumCrystalType
import ragi_materials.core.material.type.TypeRegistry
import ragi_materials.core.util.ColorUtil
import ragi_materials.core.util.RagiColor

object PeriodSecond {

    fun load() {
        //30 ~ 39: Lithium
        MaterialRegistry.LITHIUM = RagiMaterial.Builder(30, "lithium", TypeRegistry.METAL).setSimple(ElementRegistry.LITHIUM to 1).apply {
            rarity = EnumRarity.RARE
        }.buildAndRegister()

        //40 ~ 49: Beryllium
        MaterialRegistry.EMERALD = RagiMaterial.Builder(41, "emerald", TypeRegistry.CRYSTAL.enableOre()).setComponents(listOf(ElementRegistry.BERYLLIUM to 3, ElementRegistry.ALUMINIUM to 2, ElementRegistry.SILICON to 6, ElementRegistry.OXYGEN to 18)).apply {
            color = RagiColor.GREEN
            crystalType = EnumCrystalType.EMERALD
        }.buildAndRegister()

        MaterialRegistry.AQUAMARINE = RagiMaterial.Builder(42, "aquamarine", TypeRegistry.CRYSTAL.enableOre()).setComponents(listOf(ElementRegistry.BERYLLIUM to 3, ElementRegistry.ALUMINIUM to 2, ElementRegistry.SILICON to 6, ElementRegistry.OXYGEN to 18)).apply {
            color = RagiColor.AQUA
            crystalType = EnumCrystalType.EMERALD
        }.buildAndRegister()

        //50 ~ 59: Boron
        MaterialRegistry.BORAX = RagiMaterial.Builder(51, "borax", TypeRegistry.DUST.enableOre()).setComponents(listOf(ElementRegistry.SODIUM to 2, ElementRegistry.HYDROGEN to 20, ElementRegistry.BORON to 4, ElementRegistry.OXYGEN to 17)).buildAndRegister()

        //60 ~ 69: Carbon
        MaterialRegistry.CARBON = RagiMaterial.Builder(60, "carbon", TypeRegistry.METALLOID).setSimple(ElementRegistry.CARBON to 1).buildAndRegister()

        MaterialRegistry.CARBON_MONOXIDE = RagiMaterial.Builder(61, "carbon_monoxide", TypeRegistry.GAS).setComponents(listOf(ElementRegistry.CARBON to 1, ElementRegistry.OXYGEN to 1)).build()

        MaterialRegistry.CARBON_DIOXIDE = RagiMaterial.Builder(62, "carbon_dioxide", TypeRegistry.GAS).setComponents(listOf(ElementRegistry.CARBON to 1, ElementRegistry.OXYGEN to 2)).build()

        MaterialRegistry.COAL = RagiMaterial.Builder(63, "coal", TypeRegistry.FUEL.enableOre()).setSimple(ElementRegistry.CARBON to 1).apply {
            burnTime = 200 * 8
            crystalType = EnumCrystalType.COAL
        }.buildAndRegister()

        MaterialRegistry.CHARCOAL = RagiMaterial.Builder(64, "charcoal", TypeRegistry.FUEL).setSimple(ElementRegistry.CARBON to 1).apply {
            burnTime = 200 * 8
            crystalType = EnumCrystalType.COAL
        }.buildAndRegister()

        MaterialRegistry.COKE = RagiMaterial.Builder(65, "coke", TypeRegistry.FUEL).setSimple(ElementRegistry.CARBON to 1).apply {
            burnTime = 200 * 16
            color = RagiColor.DARK_GRAY
            crystalType = EnumCrystalType.COAL
        }.buildAndRegister()

        MaterialRegistry.ANTHRACITE = RagiMaterial.Builder(66, "anthracite", TypeRegistry.FUEL).setSimple(ElementRegistry.CARBON to 1).apply {
            burnTime = 200 * 24
            crystalType = EnumCrystalType.COAL
            color = ColorUtil.mixColor(MaterialRegistry.COAL.color to 5, RagiColor.DARK_BLUE to 1)
        }.build()

        MaterialRegistry.LIGNITE = RagiMaterial.Builder(67, "lignite", TypeRegistry.FUEL).setSimple(ElementRegistry.CARBON to 1).apply {
            burnTime = 200 * 4
            crystalType = EnumCrystalType.COAL
            color = ColorUtil.mixColor(MaterialRegistry.COAL.color to 5, RagiColor.DARK_RED to 1)
        }.build()

        MaterialRegistry.PEAT = RagiMaterial.Builder(68, "peat", TypeRegistry.FUEL).setSimple(ElementRegistry.CARBON to 1).apply {
            burnTime = 200 * 2
            crystalType = EnumCrystalType.COAL
            color = ColorUtil.mixColor(MaterialRegistry.COAL.color to 5, RagiColor.DARK_GREEN to 1)
        }.build()

        MaterialRegistry.DIAMOND = RagiMaterial.Builder(69, "diamond", TypeRegistry.CRYSTAL.enableOre()).setSimple(ElementRegistry.CARBON to 1).apply {
            crystalType = EnumCrystalType.DIAMOND
            color = RagiColor.AQUA
        }.buildAndRegister()

        //70 ~ 79: Nitrogen
        MaterialRegistry.NITROGEN = RagiMaterial.Builder(70, "nitrogen", TypeRegistry.GAS).setSimple(ElementRegistry.NITROGEN to 2).buildAndRegister()

        MaterialRegistry.NITRIC_ACID = RagiMaterial.Builder(71, "nitric_acid", TypeRegistry.LIQUID).setComponents(listOf(ElementRegistry.HYDROGEN to 1, MaterialRegistry.NITRATE to 1)).buildAndRegister()

        MaterialRegistry.NITER = RagiMaterial.Builder(72, "niter", TypeRegistry.DUST.enableOre()).setComponents(listOf(ElementRegistry.POTASSIUM to 1, MaterialRegistry.NITRATE to 1)).apply {
            color = RagiColor.WHITE
            oredictAlt = "Saltpeter"
        }.buildAndRegister()

        //80 ~ 89: Oxygen
        MaterialRegistry.OXYGEN = RagiMaterial.Builder(80, "oxygen", TypeRegistry.GAS).setSimple(ElementRegistry.OXYGEN to 2).buildAndRegister()

        //90 ~ 99: Fluorite
        MaterialRegistry.FLUORINE = RagiMaterial.Builder(90, "fluorine", TypeRegistry.GAS).setSimple(ElementRegistry.FLUORINE to 2).buildAndRegister()

        MaterialRegistry.FLUORITE = RagiMaterial.Builder(91, "fluorite", TypeRegistry.CRYSTAL.enableOre()).setComponents(listOf(ElementRegistry.CALCIUM to 1, ElementRegistry.FLUORINE to 2)).apply {
            color = ColorUtil.mixColor(RagiColor.GREEN, RagiColor.AQUA)
            crystalType = EnumCrystalType.DIAMOND
        }.buildAndRegister()

        MaterialRegistry.HYDROGEN_FLUORIDE = RagiMaterial.Builder(92, "hydrogen_fluoride", TypeRegistry.GAS).setComponents(listOf(ElementRegistry.HYDROGEN to 1, ElementRegistry.FLUORINE to 1)).buildAndRegister()

        MaterialRegistry.CRYOLITE = RagiMaterial.Builder(93, "cryolite", TypeRegistry.CRYSTAL.enableOre()).setComponents(listOf(ElementRegistry.SODIUM to 3, ElementRegistry.ALUMINIUM to 1, ElementRegistry.FLUORINE to 6)).apply {
            color = RagiColor.WHITE
            crystalType = EnumCrystalType.DIAMOND
        }.buildAndRegister()

        //100 ~ 109: Neon
        MaterialRegistry.NEON = RagiMaterial.Builder(21, "neon", TypeRegistry.GAS).setSimple(ElementRegistry.NEON to 1).apply {
            rarity = EnumRarity.RARE
        }.build()

    }
}