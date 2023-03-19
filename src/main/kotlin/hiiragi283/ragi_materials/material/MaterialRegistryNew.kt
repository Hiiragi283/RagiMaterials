package hiiragi283.ragi_materials.material

import hiiragi283.ragi_materials.client.render.color.RagiColor
import hiiragi283.ragi_materials.client.render.color.RagiColorManager
import hiiragi283.ragi_materials.material.type.EnumCrystalType
import hiiragi283.ragi_materials.material.type.TypeRegistry
import net.minecraft.item.EnumRarity

object MaterialRegistryNew {

    //Pre-registration
    val HYDROXIDE = RagiMaterial.Builder(-1, "hydroxide", TypeRegistry.INTERNAL).setComponents(listOf(ElementRegistryNew.OXYGEN to 1, ElementRegistryNew.HYDROGEN to 1)).build()

    val CARBONATE = RagiMaterial.Builder(-1, "carbonate", TypeRegistry.INTERNAL).setComponents(listOf(ElementRegistryNew.CARBON to 1, ElementRegistryNew.OXYGEN to 3)).build()

    val NITRATE = RagiMaterial.Builder(-1, "nitrate", TypeRegistry.INTERNAL).setComponents(listOf(ElementRegistryNew.NITROGEN to 1, ElementRegistryNew.OXYGEN to 3)).build()

    val PHOSPHATE = RagiMaterial.Builder(-1, "phosphate", TypeRegistry.INTERNAL).setComponents(listOf(ElementRegistryNew.PHOSPHORUS to 1, ElementRegistryNew.OXYGEN to 4)).build()

    val SULFATE = RagiMaterial.Builder(-1, "sulfate", TypeRegistry.INTERNAL).setComponents(listOf(ElementRegistryNew.SULFUR to 1, ElementRegistryNew.OXYGEN to 4)).build()

    val TUNGSTATE = RagiMaterial.Builder(-1, "tungstate", TypeRegistry.INTERNAL).setComponents(listOf(ElementRegistryNew.TUNGSTEN to 1, ElementRegistryNew.OXYGEN to 4)).build()

    //10 ~ 19: Hydrogen
    val HYDROGEN = RagiMaterial.Builder(10, "hydrogen", TypeRegistry.GAS).setSimple(ElementRegistryNew.HYDROGEN to 2).build()

    val WATER = RagiMaterial.Builder(11, "water", TypeRegistry.INTERNAL).setComponents(listOf(ElementRegistryNew.HYDROGEN to 2, ElementRegistryNew.OXYGEN to 1)).apply {
        color = RagiColor.BLUE
        tempBoil = 100
        tempMelt = 0
    }.build()

    val SNOW = RagiMaterial.Builder(12, "snow", TypeRegistry.INGOT).setComponents(listOf(WATER to 1)).apply {
        color = RagiColor.WHITE
    }.build()

    val ICE = RagiMaterial.Builder(13, "ice", TypeRegistry.INGOT).setComponents(listOf(WATER to 1)).apply {
        color = RagiColorManager.mixColor(RagiColor.AQUA, RagiColor.WHITE)
    }.build()

    val DEUTERIUM = RagiMaterial.Builder(14, "deuterium", TypeRegistry.GAS).setSimple(ElementRegistryNew.DEUTERIUM to 2).apply {
        rarity = EnumRarity.EPIC
    }.build()

    val TRITIUM = RagiMaterial.Builder(15, "tritium", TypeRegistry.GAS).setSimple(ElementRegistryNew.TRITIUM to 2).apply {
        rarity = EnumRarity.EPIC
    }.build()

    //20 ~ 29: Helium, Neon, Argon
    val HELIUM = RagiMaterial.Builder(20, "helium", TypeRegistry.GAS).setSimple(ElementRegistryNew.HELIUM to 1).apply {
        rarity = EnumRarity.RARE
    }.build()

    val NEON = RagiMaterial.Builder(21, "neon", TypeRegistry.GAS).setSimple(ElementRegistryNew.NEON to 1).apply {
        rarity = EnumRarity.RARE
    }.build()

    val ARGON = RagiMaterial.Builder(22, "argon", TypeRegistry.GAS).setSimple(ElementRegistryNew.ARGON to 1).apply{
        rarity = EnumRarity.RARE
    }.build()

    //30 ~ 39: Lithium
    val LITHIUM = RagiMaterial.Builder(30, "lithium", TypeRegistry.METAL).setSimple(ElementRegistryNew.LITHIUM to 1).apply{
        rarity = EnumRarity.RARE
    }.build()

    //40 ~ 49: Beryllium
    val BERYLLIUM = RagiMaterial.Builder(40, "beryllium", TypeRegistry.METAL).setSimple(ElementRegistryNew.BERYLLIUM to 1).apply{
        rarity = EnumRarity.RARE
    }.build()

    val EMERALD = RagiMaterial.Builder(31, "emerald", TypeRegistry.CRYSTAL).setComponents(listOf(ElementRegistryNew.BERYLLIUM to 3, ElementRegistryNew.ALUMINIUM to 2, ElementRegistryNew.SILICON to 6, ElementRegistryNew.OXYGEN to 18)).apply {
        color = RagiColor.GREEN
        crystalType = EnumCrystalType.EMERALD
    }.build()

    //50 ~ 59: Boron
    val BORON = RagiMaterial.Builder(50, "boron", TypeRegistry.METALLOID).setSimple(ElementRegistryNew.BORON to 1).build()

    val BORAX = RagiMaterial.Builder(51, "borax", TypeRegistry.DUST).setComponents(listOf(ElementRegistryNew.SODIUM to 2, ElementRegistryNew.HYDROGEN to 20, ElementRegistryNew.BORON to 4, ElementRegistryNew.OXYGEN to 17)).build()

    val BORON_OXIDE = RagiMaterial.Builder(51, "boron_oxide", TypeRegistry.DUST).setComponents(listOf(ElementRegistryNew.BORON to 2, ElementRegistryNew.OXYGEN to 3)).build()

    //60 ~ 69: Carbon
    val CARBON = RagiMaterial.Builder(60, "carbon", TypeRegistry.METALLOID).setSimple(ElementRegistryNew.CARBON to 1).build()

    val CARBON_MONOXIDE = RagiMaterial.Builder(61, "carbon_monoxide", TypeRegistry.GAS).setComponents(listOf(ElementRegistryNew.CARBON to 1, ElementRegistryNew.OXYGEN to 1)).build()

    val CARON_DIOXIDE = RagiMaterial.Builder(62, "carbon_dioxide", TypeRegistry.GAS).setComponents(listOf(ElementRegistryNew.CARBON to 1, ElementRegistryNew.OXYGEN to 2)).build()

    val COAL = RagiMaterial.Builder(63, "coal", TypeRegistry.FUEL).setComponents(listOf(ElementRegistryNew.CARBON to 1)).apply{
        burnTime = 200 * 8
        crystalType = EnumCrystalType.COAL
    }.build()

    val CHARCOAL = RagiMaterial.Builder(64, "charcoal", TypeRegistry.FUEL).setComponents(listOf(ElementRegistryNew.CARBON to 1)).apply{
        burnTime = 200 * 8
        crystalType = EnumCrystalType.COAL
    }.build()

    val COKE = RagiMaterial.Builder(65, "coke", TypeRegistry.FUEL).setComponents(listOf(ElementRegistryNew.CARBON to 1)).apply{
        burnTime = 200 * 16
        color = RagiColor.DARK_GRAY
        crystalType = EnumCrystalType.COAL
    }.build()

    val ANTHRACITE = RagiMaterial.Builder(66, "anthracite", TypeRegistry.FUEL).setComponents(listOf(ElementRegistryNew.CARBON to 1)).apply{
        burnTime = 200 * 24
        crystalType = EnumCrystalType.COAL
        color = RagiColorManager.mixColor(COAL.color to 5, RagiColor.DARK_BLUE to 1)
    }.build()

    val LIGNITE = RagiMaterial.Builder(67, "lignite", TypeRegistry.FUEL).setComponents(listOf(ElementRegistryNew.CARBON to 1)).apply{
        burnTime = 200 * 4
        crystalType = EnumCrystalType.COAL
        color = RagiColorManager.mixColor(COAL.color to 5, RagiColor.DARK_RED to 1)
    }.build()

    val PEAT = RagiMaterial.Builder(68, "peat", TypeRegistry.FUEL).setComponents(listOf(ElementRegistryNew.CARBON to 1)).apply{
        burnTime = 200 * 2
        crystalType = EnumCrystalType.COAL
        color = RagiColorManager.mixColor(COAL.color to 5, RagiColor.DARK_GREEN to 1)
    }.build()

    val DIAMOND = RagiMaterial.Builder(69, "diamond", TypeRegistry.CRYSTAL).setComponents(listOf(ElementRegistryNew.CARBON to 1)).apply{
        crystalType = EnumCrystalType.DIAMOND
        color = RagiColor.AQUA
    }.build()

    //70 ~ 79: Nitrogen
    val NITROGEN = RagiMaterial.Builder(70, "nitrogen", TypeRegistry.GAS).setSimple(ElementRegistryNew.NITROGEN to 2).build()

    val NITRIC_ACID = RagiMaterial.Builder(71, "nitric_acid", TypeRegistry.LIQUID).setComponents(listOf(ElementRegistryNew.HYDROGEN to 1, NITRATE to 1)).build()

    val NITER = RagiMaterial.Builder(72, "niter", TypeRegistry.DUST).setComponents(listOf(ElementRegistryNew.POTASSIUM to 1, NITRATE to 1)).apply {
        color = RagiColor.WHITE
        oredictAlt = "Saltpeter"
    }.build()

    //80 ~ 89: Oxygen
    val OXYGEN = RagiMaterial.Builder(80, "oxygen", TypeRegistry.GAS).setSimple(ElementRegistryNew.OXYGEN to 2).build()

    //90 ~ 99: Fluorite
    val FLUORINE = RagiMaterial.Builder(90, "fluorine", TypeRegistry.GAS).setSimple(ElementRegistryNew.FLUORINE to 2).build()

    val FLUORITE = RagiMaterial.Builder(91, "fluorite", TypeRegistry.CRYSTAL).setComponents(listOf(ElementRegistryNew.CALCIUM to 1, ElementRegistryNew.FLUORINE to 2)).apply {
        color = RagiColorManager.mixColor(RagiColor.GREEN, RagiColor.AQUA)
        crystalType = EnumCrystalType.DIAMOND
    }.build()

    val HYDROGEN_FLUORIDE = RagiMaterial.Builder(92, "hydrogen_fluoride", TypeRegistry.GAS).setComponents(listOf(ElementRegistryNew.HYDROGEN to 1, ElementRegistryNew.FLUORINE to 1)).build()

    //100 ~ 109:

}