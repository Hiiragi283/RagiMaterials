package hiiragi283.ragi_materials.material

import hiiragi283.ragi_materials.client.render.color.RagiColor
import hiiragi283.ragi_materials.client.render.color.RagiColorManager
import hiiragi283.ragi_materials.material.type.EnumCrystalType
import hiiragi283.ragi_materials.material.type.TypeRegistry
import net.minecraft.item.EnumRarity
import net.minecraftforge.fluids.FluidRegistry

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

    val ARGON = RagiMaterial.Builder(22, "argon", TypeRegistry.GAS).setSimple(ElementRegistryNew.ARGON to 1).apply {
        rarity = EnumRarity.RARE
    }.build()

    //30 ~ 39: Lithium
    val LITHIUM = RagiMaterial.Builder(30, "lithium", TypeRegistry.METAL).setSimple(ElementRegistryNew.LITHIUM to 1).apply {
        rarity = EnumRarity.RARE
    }.build()

    //40 ~ 49: Beryllium
    val BERYLLIUM = RagiMaterial.Builder(40, "beryllium", TypeRegistry.METAL).setSimple(ElementRegistryNew.BERYLLIUM to 1).apply {
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

    val CARBON_DIOXIDE = RagiMaterial.Builder(62, "carbon_dioxide", TypeRegistry.GAS).setComponents(listOf(ElementRegistryNew.CARBON to 1, ElementRegistryNew.OXYGEN to 2)).build()

    val COAL = RagiMaterial.Builder(63, "coal", TypeRegistry.FUEL).setSimple(ElementRegistryNew.CARBON to 1).apply {
        burnTime = 200 * 8
        crystalType = EnumCrystalType.COAL
    }.build()

    val CHARCOAL = RagiMaterial.Builder(64, "charcoal", TypeRegistry.FUEL).setSimple(ElementRegistryNew.CARBON to 1).apply {
        burnTime = 200 * 8
        crystalType = EnumCrystalType.COAL
    }.build()

    val COKE = RagiMaterial.Builder(65, "coke", TypeRegistry.FUEL).setSimple(ElementRegistryNew.CARBON to 1).apply {
        burnTime = 200 * 16
        color = RagiColor.DARK_GRAY
        crystalType = EnumCrystalType.COAL
    }.build()

    val ANTHRACITE = RagiMaterial.Builder(66, "anthracite", TypeRegistry.FUEL).setSimple(ElementRegistryNew.CARBON to 1).apply {
        burnTime = 200 * 24
        crystalType = EnumCrystalType.COAL
        color = RagiColorManager.mixColor(COAL.color to 5, RagiColor.DARK_BLUE to 1)
    }.build()

    val LIGNITE = RagiMaterial.Builder(67, "lignite", TypeRegistry.FUEL).setSimple(ElementRegistryNew.CARBON to 1).apply {
        burnTime = 200 * 4
        crystalType = EnumCrystalType.COAL
        color = RagiColorManager.mixColor(COAL.color to 5, RagiColor.DARK_RED to 1)
    }.build()

    val PEAT = RagiMaterial.Builder(68, "peat", TypeRegistry.FUEL).setSimple(ElementRegistryNew.CARBON to 1).apply {
        burnTime = 200 * 2
        crystalType = EnumCrystalType.COAL
        color = RagiColorManager.mixColor(COAL.color to 5, RagiColor.DARK_GREEN to 1)
    }.build()

    val DIAMOND = RagiMaterial.Builder(69, "diamond", TypeRegistry.CRYSTAL).setSimple(ElementRegistryNew.CARBON to 1).apply {
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

    //100 ~ 109: Misc (1)
    val WOOD = RagiMaterial.Builder(100, "wood", TypeRegistry.WOOD).setComponents(listOf(ElementRegistryNew.CARBON to 1, ElementRegistryNew.HYDROGEN to 1, ElementRegistryNew.OXYGEN to 1)).apply {
        color = RagiColorManager.mixColor(RagiColor.DARK_GRAY to 2, RagiColor.RED to 1, RagiColor.YELLOW to 1)
    }.setMixture().build()

    //110 ~ 119: Sodium
    val SODIUM = RagiMaterial.Builder(110, "sodium", TypeRegistry.INTERNAL).setSimple(ElementRegistryNew.SODIUM to 1).build()

    val SODIUM_HYDROXIDE = RagiMaterial.Builder(111, "sodium_hydroxide", TypeRegistry.DUST).setComponents(listOf(ElementRegistryNew.SODIUM to 1, HYDROXIDE to 1)).build()

    val SALT = RagiMaterial.Builder(112, "salt", TypeRegistry.DUST).setComponents(listOf(ElementRegistryNew.SODIUM to 1, ElementRegistryNew.CHLORINE to 1)).apply {
        color = RagiColor.WHITE
    }.build()

    val SODIUM_SULFATE = RagiMaterial.Builder(113, "sodium_sulfate", TypeRegistry.DUST).setComponents(listOf(ElementRegistryNew.SODIUM to 2, SULFATE to 1)).build()

    //120 ~ 129: Magnesium
    val MAGNESIUM = RagiMaterial.Builder(120, "magnesium", TypeRegistry.INTERNAL).setSimple(ElementRegistryNew.MAGNESIUM to 1).build()

    val MAGNESITE = RagiMaterial.Builder(121, "magnesite", TypeRegistry.DUST).setComponents(listOf(ElementRegistryNew.MAGNESIUM to 1, CARBONATE to 1)).build()

    val MAGNESIUM_CHLORIDE = RagiMaterial.Builder(122, "magnesium_chloride", TypeRegistry.DUST).setComponents(listOf(ElementRegistryNew.MAGNESIUM to 1, ElementRegistryNew.CHLORINE to 2)).build()

    //130 ~ 139: Aluminium
    val ALUMINIUM = RagiMaterial.Builder(130, "aluminium", TypeRegistry.METAL).setSimple(ElementRegistryNew.ALUMINIUM to 1).apply {
        oredictAlt = "Aluminum"
    }.build()

    val ALUMINA = RagiMaterial.Builder(131, "alumina", TypeRegistry.DUST).setComponents(listOf(ElementRegistryNew.ALUMINIUM to 2, ElementRegistryNew.OXYGEN to 3)).build()

    val ALUMINA_SOLUTION = RagiMaterial.Builder(132, "alumina_solution", TypeRegistry.LIQUID).setComponents(listOf(ElementRegistryNew.SODIUM to 1, RagiMaterial.Formula("[").build() to 1, ElementRegistryNew.ALUMINIUM to 1, HYDROXIDE.setBracket() to 4, RagiMaterial.Formula("]").build() to 1)).build()

    val BAUXITE = RagiMaterial.Builder(133, "bauxite", TypeRegistry.DUST).setComponents(listOf(ElementRegistryNew.ALUMINIUM to 2, ElementRegistryNew.OXYGEN to 3)).apply {
        color = RagiColorManager.mixColor(RagiColor.BLACK to 1, RagiColor.DARK_RED to 2, RagiColor.GOLD to 1)
    }.build()

    val RUBY = RagiMaterial.Builder(134, "ruby", TypeRegistry.CRYSTAL).setComponents(listOf(ElementRegistryNew.CHROMIUM to 1, ALUMINA to 1)).apply {
        color = RagiColor.RED
        crystalType = EnumCrystalType.RUBY
    }.build()

    val SAPPHIRE = RagiMaterial.Builder(135, "sapphire", TypeRegistry.CRYSTAL).setComponents(listOf(ElementRegistryNew.IRON to 1, ALUMINA to 1)).apply {
        color = RagiColor.BLUE
        crystalType = EnumCrystalType.RUBY
    }.build()

    //140 ~ 149: Silicon
    val SILICON = RagiMaterial.Builder(140, "silicon", TypeRegistry.METALLOID).setSimple(ElementRegistryNew.SILICON to 1).build()

    val SILICON_DIOXIDE = RagiMaterial.Builder(141, "silicon_dioxide", TypeRegistry.DUST).setComponents(listOf(ElementRegistryNew.SILICON to 1, ElementRegistryNew.OXYGEN to 2)).build()

    val GLASS = RagiMaterial.Builder(142, "glass", TypeRegistry.CRYSTAL).setSimple(SILICON_DIOXIDE to 1).apply {
        crystalType = EnumCrystalType.RUBY
    }.build()

    val QUARTZ = RagiMaterial.Builder(143, "quartz", TypeRegistry.CRYSTAL).setSimple(SILICON_DIOXIDE to 1).apply {
        crystalType = EnumCrystalType.QUARTZ
    }.build()

    //150 ~ 159: Phosphorus
    val PHOSPHORUS = RagiMaterial.Builder(150, "phosphorus", TypeRegistry.DUST).setSimple(ElementRegistryNew.PHOSPHORUS to 1).build()

    //160 ~ 169: Sulfur
    val SULFUR = RagiMaterial.Builder(160, "sulfur", TypeRegistry.DUST).setSimple(ElementRegistryNew.SULFUR to 8).build()

    val SULFURIC_ACID = RagiMaterial.Builder(161, "sulfuric_acid", TypeRegistry.LIQUID).setComponents(listOf(ElementRegistryNew.HYDROGEN to 2, SULFATE to 1)).apply {
        color = RagiColorManager.mixColor(RagiColor.GOLD, RagiColor.YELLOW)
    }.build()

    //170 ~ 179: Chlorine
    val CHLORINE = RagiMaterial.Builder(170, "chlorine", TypeRegistry.GAS).build()

    val HYDROGEN_CHLORIDE = RagiMaterial.Builder(171, "hydrogen_chloride", TypeRegistry.GAS).setComponents(listOf(ElementRegistryNew.HYDROGEN to 1, ElementRegistryNew.CHLORINE to 1)).build()

    //180 ~ 189: Stone
    val STONE = RagiMaterial.Builder(180, "stone", TypeRegistry.STONE).setSimple(SILICON_DIOXIDE to 1).apply {
        color = RagiColor.GRAY
    }.build()

    val LAVA = RagiMaterial.Builder(181, "lava", TypeRegistry.INTERNAL).setSimple(SILICON_DIOXIDE to 1).apply {
        color = RagiColorManager.mixColor(RagiColor.DARK_RED, RagiColor.GOLD)
        tempMelt = FluidRegistry.LAVA.temperature
    }.build()

    val OBSIDIAN = RagiMaterial.Builder(182, "obsidian", TypeRegistry.DUST).setSimple(SILICON_DIOXIDE to 1).apply {
        color = RagiColorManager.mixColor(RagiColor.BLACK to 2, RagiColor.BLUE to 1, RagiColor.RED to 1)
    }.build()

    val NETHERRACK = RagiMaterial.Builder(183, "netherrack", TypeRegistry.DUST).setSimple(SILICON_DIOXIDE to 1).apply {
        color = RagiColor.DARK_RED
    }.build()

    val SOUL_SAND = RagiMaterial.Builder(184, "soul_sand", TypeRegistry.DUST).setSimple(SILICON_DIOXIDE to 1).apply {
        color = RagiColorManager.mixColor(RagiColor.BLACK to 5, RagiColor.GOLD to 1)
    }.build()

    val END_STONE = RagiMaterial.Builder(185, "end_stone", TypeRegistry.DUST).setSimple(SILICON_DIOXIDE to 1).apply {
        color = RagiColorManager.mixColor(RagiColor.YELLOW to 1, RagiColor.WHITE to 3)
    }.build()

}