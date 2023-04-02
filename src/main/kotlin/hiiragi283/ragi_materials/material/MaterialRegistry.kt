package hiiragi283.ragi_materials.material

import hiiragi283.ragi_materials.client.render.color.ColorManager
import hiiragi283.ragi_materials.client.render.color.RagiColor
import hiiragi283.ragi_materials.material.type.EnumCrystalType
import hiiragi283.ragi_materials.material.type.TypeRegistry
import net.minecraft.item.EnumRarity
import net.minecraftforge.fluids.FluidRegistry

object MaterialRegistry {

    //Pre-registration
    val HYDROXIDE = RagiMaterial.Builder(-1, "hydroxide", TypeRegistry.INTERNAL).setComponents(listOf(ElementRegistry.OXYGEN to 1, ElementRegistry.HYDROGEN to 1)).build()

    val CARBONATE = RagiMaterial.Builder(-1, "carbonate", TypeRegistry.INTERNAL).setComponents(listOf(ElementRegistry.CARBON to 1, ElementRegistry.OXYGEN to 3)).build()

    val NITRATE = RagiMaterial.Builder(-1, "nitrate", TypeRegistry.INTERNAL).setComponents(listOf(ElementRegistry.NITROGEN to 1, ElementRegistry.OXYGEN to 3)).build()

    val PHOSPHATE = RagiMaterial.Builder(-1, "phosphate", TypeRegistry.INTERNAL).setComponents(listOf(ElementRegistry.PHOSPHORUS to 1, ElementRegistry.OXYGEN to 4)).build()

    val SULFATE = RagiMaterial.Builder(-1, "sulfate", TypeRegistry.INTERNAL).setComponents(listOf(ElementRegistry.SULFUR to 1, ElementRegistry.OXYGEN to 4)).build()

    val TUNGSTATE = RagiMaterial.Builder(-1, "tungstate", TypeRegistry.INTERNAL).setComponents(listOf(ElementRegistry.TUNGSTEN to 1, ElementRegistry.OXYGEN to 4)).build()

    //10 ~ 19: Hydrogen
    val HYDROGEN = RagiMaterial.Builder(10, "hydrogen", TypeRegistry.GAS).setSimple(ElementRegistry.HYDROGEN to 2).build()

    val WATER = RagiMaterial.Builder(11, "water", TypeRegistry.INTERNAL).setComponents(listOf(ElementRegistry.HYDROGEN to 2, ElementRegistry.OXYGEN to 1)).apply {
        color = RagiColor.BLUE
        tempBoil = 100
        tempMelt = 0
    }.build()

    val SNOW = RagiMaterial.Builder(12, "snow", TypeRegistry.INGOT).setComponents(listOf(WATER to 1)).apply {
        color = RagiColor.WHITE
    }.build()

    val ICE = RagiMaterial.Builder(13, "ice", TypeRegistry.INGOT).setComponents(listOf(WATER to 1)).apply {
        color = ColorManager.mixColor(RagiColor.AQUA, RagiColor.WHITE)
    }.build()

    val DEUTERIUM = RagiMaterial.Builder(14, "deuterium", TypeRegistry.GAS).setSimple(ElementRegistry.DEUTERIUM to 2).apply {
        rarity = EnumRarity.EPIC
    }.build()

    val TRITIUM = RagiMaterial.Builder(15, "tritium", TypeRegistry.GAS).setSimple(ElementRegistry.TRITIUM to 2).apply {
        rarity = EnumRarity.EPIC
    }.build()

    //20 ~ 29: Helium, Neon, Argon
    val HELIUM = RagiMaterial.Builder(20, "helium", TypeRegistry.GAS).setSimple(ElementRegistry.HELIUM to 1).apply {
        rarity = EnumRarity.RARE
    }.build()

    val NEON = RagiMaterial.Builder(21, "neon", TypeRegistry.GAS).setSimple(ElementRegistry.NEON to 1).apply {
        rarity = EnumRarity.RARE
    }.build()

    val ARGON = RagiMaterial.Builder(22, "argon", TypeRegistry.GAS).setSimple(ElementRegistry.ARGON to 1).apply {
        rarity = EnumRarity.RARE
    }.build()

    //30 ~ 39: Lithium
    val LITHIUM = RagiMaterial.Builder(30, "lithium", TypeRegistry.METAL).setSimple(ElementRegistry.LITHIUM to 1).apply {
        rarity = EnumRarity.RARE
    }.build()

    val SPODUMENE = RagiMaterial.Builder(31, "spodumene", TypeRegistry.CRYSTAL).setComponents(listOf(ElementRegistry.LITHIUM to 1, ElementRegistry.ALUMINIUM to 1, ElementRegistry.SILICON to 2, ElementRegistry.OXYGEN to 6)).apply {
        color = ColorManager.mixColor(RagiColor.LIGHT_PURPLE, RagiColor.WHITE)
        crystalType = EnumCrystalType.LAPIS
    }.build()

    //40 ~ 49: Beryllium
    val BERYLLIUM = RagiMaterial.Builder(40, "beryllium", TypeRegistry.METAL).setSimple(ElementRegistry.BERYLLIUM to 1).apply {
        rarity = EnumRarity.RARE
    }.build()

    val EMERALD = RagiMaterial.Builder(41, "emerald", TypeRegistry.CRYSTAL).setComponents(listOf(ElementRegistry.BERYLLIUM to 3, ElementRegistry.ALUMINIUM to 2, ElementRegistry.SILICON to 6, ElementRegistry.OXYGEN to 18)).apply {
        color = RagiColor.GREEN
        crystalType = EnumCrystalType.EMERALD
    }.build()

    val AQUAMARINE = RagiMaterial.Builder(42, "aquamarine", TypeRegistry.CRYSTAL).setComponents(listOf(ElementRegistry.BERYLLIUM to 3, ElementRegistry.ALUMINIUM to 2, ElementRegistry.SILICON to 6, ElementRegistry.OXYGEN to 18)).apply {
        color = RagiColor.AQUA
        crystalType = EnumCrystalType.EMERALD
    }.build()

    //50 ~ 59: Boron
    val BORON = RagiMaterial.Builder(50, "boron", TypeRegistry.METALLOID).setSimple(ElementRegistry.BORON to 1).build()

    val BORAX = RagiMaterial.Builder(51, "borax", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.SODIUM to 2, ElementRegistry.HYDROGEN to 20, ElementRegistry.BORON to 4, ElementRegistry.OXYGEN to 17)).build()

    val BORON_OXIDE = RagiMaterial.Builder(52, "boron_oxide", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.BORON to 2, ElementRegistry.OXYGEN to 3)).build()

    //60 ~ 69: Carbon
    val CARBON = RagiMaterial.Builder(60, "carbon", TypeRegistry.METALLOID).setSimple(ElementRegistry.CARBON to 1).build()

    val CARBON_MONOXIDE = RagiMaterial.Builder(61, "carbon_monoxide", TypeRegistry.GAS).setComponents(listOf(ElementRegistry.CARBON to 1, ElementRegistry.OXYGEN to 1)).build()

    val CARBON_DIOXIDE = RagiMaterial.Builder(62, "carbon_dioxide", TypeRegistry.GAS).setComponents(listOf(ElementRegistry.CARBON to 1, ElementRegistry.OXYGEN to 2)).build()

    val COAL = RagiMaterial.Builder(63, "coal", TypeRegistry.FUEL).setSimple(ElementRegistry.CARBON to 1).apply {
        burnTime = 200 * 8
        crystalType = EnumCrystalType.COAL
    }.build()

    val CHARCOAL = RagiMaterial.Builder(64, "charcoal", TypeRegistry.FUEL).setSimple(ElementRegistry.CARBON to 1).apply {
        burnTime = 200 * 8
        crystalType = EnumCrystalType.COAL
    }.build()

    val COKE = RagiMaterial.Builder(65, "coke", TypeRegistry.FUEL).setSimple(ElementRegistry.CARBON to 1).apply {
        burnTime = 200 * 16
        color = RagiColor.DARK_GRAY
        crystalType = EnumCrystalType.COAL
    }.build()

    val ANTHRACITE = RagiMaterial.Builder(66, "anthracite", TypeRegistry.FUEL).setSimple(ElementRegistry.CARBON to 1).apply {
        burnTime = 200 * 24
        crystalType = EnumCrystalType.COAL
        color = ColorManager.mixColor(COAL.color to 5, RagiColor.DARK_BLUE to 1)
    }.build()

    val LIGNITE = RagiMaterial.Builder(67, "lignite", TypeRegistry.FUEL).setSimple(ElementRegistry.CARBON to 1).apply {
        burnTime = 200 * 4
        crystalType = EnumCrystalType.COAL
        color = ColorManager.mixColor(COAL.color to 5, RagiColor.DARK_RED to 1)
    }.build()

    val PEAT = RagiMaterial.Builder(68, "peat", TypeRegistry.FUEL).setSimple(ElementRegistry.CARBON to 1).apply {
        burnTime = 200 * 2
        crystalType = EnumCrystalType.COAL
        color = ColorManager.mixColor(COAL.color to 5, RagiColor.DARK_GREEN to 1)
    }.build()

    val DIAMOND = RagiMaterial.Builder(69, "diamond", TypeRegistry.CRYSTAL).setSimple(ElementRegistry.CARBON to 1).apply {
        crystalType = EnumCrystalType.DIAMOND
        color = RagiColor.AQUA
    }.build()

    //70 ~ 79: Nitrogen
    val NITROGEN = RagiMaterial.Builder(70, "nitrogen", TypeRegistry.GAS).setSimple(ElementRegistry.NITROGEN to 2).build()

    val NITRIC_ACID = RagiMaterial.Builder(71, "nitric_acid", TypeRegistry.LIQUID).setComponents(listOf(ElementRegistry.HYDROGEN to 1, NITRATE to 1)).build()

    val NITER = RagiMaterial.Builder(72, "niter", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.POTASSIUM to 1, NITRATE to 1)).apply {
        color = RagiColor.WHITE
        oredictAlt = "Saltpeter"
    }.build()

    //80 ~ 89: Oxygen
    val OXYGEN = RagiMaterial.Builder(80, "oxygen", TypeRegistry.GAS).setSimple(ElementRegistry.OXYGEN to 2).build()

    //90 ~ 99: Fluorite
    val FLUORINE = RagiMaterial.Builder(90, "fluorine", TypeRegistry.GAS).setSimple(ElementRegistry.FLUORINE to 2).build()

    val FLUORITE = RagiMaterial.Builder(91, "fluorite", TypeRegistry.CRYSTAL).setComponents(listOf(ElementRegistry.CALCIUM to 1, ElementRegistry.FLUORINE to 2)).apply {
        color = ColorManager.mixColor(RagiColor.GREEN, RagiColor.AQUA)
        crystalType = EnumCrystalType.DIAMOND
    }.build()

    val HYDROGEN_FLUORIDE = RagiMaterial.Builder(92, "hydrogen_fluoride", TypeRegistry.GAS).setComponents(listOf(ElementRegistry.HYDROGEN to 1, ElementRegistry.FLUORINE to 1)).build()

    val CRYOLITE = RagiMaterial.Builder(93, "cryolite", TypeRegistry.CRYSTAL).setComponents(listOf(ElementRegistry.SODIUM to 3, ElementRegistry.ALUMINIUM to 1, ElementRegistry.FLUORINE to 6)).apply {
        color = RagiColor.WHITE
        crystalType = EnumCrystalType.DIAMOND
    }.build()

    //100 ~ 109: Misc (1)
    val WOOD = RagiMaterial.Builder(100, "wood", TypeRegistry.WOOD).setComponents(listOf(ElementRegistry.CARBON to 1, ElementRegistry.HYDROGEN to 1, ElementRegistry.OXYGEN to 1)).apply {
        color = ColorManager.mixColor(RagiColor.DARK_GRAY to 2, RagiColor.RED to 1, RagiColor.YELLOW to 1)
    }.setMixture().build()

    val LAPIS = RagiMaterial.Builder(101, "lapis", TypeRegistry.CRYSTAL).setComponents(listOf(RagiMaterial.Formula("?").build() to 1)).setMixture().apply {
        color = RagiColor.BLUE
        crystalType = EnumCrystalType.LAPIS
    }.build()

    val REDSTONE = RagiMaterial.Builder(102, "redstone", TypeRegistry.DUST).setSimple(ElementRegistry.REDSTONE to 1).build()

    val GLOWSTONE = RagiMaterial.Builder(103, "glowstone", TypeRegistry.DUST).setSimple(ElementRegistry.GLOWSTONE to 1).build()

    val ENDER_PEARL = RagiMaterial.Builder(104, "ender_pearl", TypeRegistry.DUST).setSimple(ElementRegistry.ENDER to 1).apply {
        oredictAlt = "Ender"
    }.build()

    //110 ~ 119: Sodium
    val SODIUM = RagiMaterial.Builder(110, "sodium", TypeRegistry.INTERNAL).setSimple(ElementRegistry.SODIUM to 1).build()

    val SODIUM_HYDROXIDE = RagiMaterial.Builder(111, "sodium_hydroxide", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.SODIUM to 1, HYDROXIDE to 1)).build()

    val SALT = RagiMaterial.Builder(112, "salt", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.SODIUM to 1, ElementRegistry.CHLORINE to 1)).apply {
        color = RagiColor.WHITE
    }.build()

    val SODIUM_SULFATE = RagiMaterial.Builder(113, "sodium_sulfate", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.SODIUM to 2, SULFATE to 1)).build()

    //120 ~ 129: Magnesium
    val MAGNESIUM = RagiMaterial.Builder(120, "magnesium", TypeRegistry.INTERNAL).setSimple(ElementRegistry.MAGNESIUM to 1).build()

    val MAGNESITE = RagiMaterial.Builder(121, "magnesite", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.MAGNESIUM to 1, CARBONATE to 1)).build()

    val MAGNESIUM_CHLORIDE = RagiMaterial.Builder(122, "magnesium_chloride", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.MAGNESIUM to 1, ElementRegistry.CHLORINE to 2)).build()

    //130 ~ 139: Aluminium
    val ALUMINIUM = RagiMaterial.Builder(130, "aluminium", TypeRegistry.METAL).setSimple(ElementRegistry.ALUMINIUM to 1).apply {
        oredictAlt = "Aluminum"
    }.build()

    val ALUMINA = RagiMaterial.Builder(131, "alumina", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.ALUMINIUM to 2, ElementRegistry.OXYGEN to 3)).build()

    val ALUMINA_SOLUTION = RagiMaterial.Builder(132, "alumina_solution", TypeRegistry.LIQUID).setComponents(listOf(ElementRegistry.SODIUM to 1, RagiMaterial.Formula("[").build() to 1, ElementRegistry.ALUMINIUM to 1, HYDROXIDE.setBracket() to 4, RagiMaterial.Formula("]").build() to 1)).build()

    val BAUXITE = RagiMaterial.Builder(133, "bauxite", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.ALUMINIUM to 2, ElementRegistry.OXYGEN to 3)).apply {
        color = ColorManager.mixColor(RagiColor.BLACK to 1, RagiColor.DARK_RED to 2, RagiColor.GOLD to 1)
    }.build()

    val RUBY = RagiMaterial.Builder(134, "ruby", TypeRegistry.CRYSTAL).setComponents(listOf(ElementRegistry.CHROMIUM to 1, ALUMINA to 1)).apply {
        color = RagiColor.RED
        crystalType = EnumCrystalType.RUBY
    }.build()

    val SAPPHIRE = RagiMaterial.Builder(135, "sapphire", TypeRegistry.CRYSTAL).setComponents(listOf(ElementRegistry.IRON to 1, ALUMINA to 1)).apply {
        color = RagiColor.BLUE
        crystalType = EnumCrystalType.RUBY
    }.build()

    //140 ~ 149: Silicon
    val SILICON = RagiMaterial.Builder(140, "silicon", TypeRegistry.METALLOID).setSimple(ElementRegistry.SILICON to 1).build()

    val SILICON_DIOXIDE = RagiMaterial.Builder(141, "silicon_dioxide", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.SILICON to 1, ElementRegistry.OXYGEN to 2)).build()

    val GLASS = RagiMaterial.Builder(142, "glass", TypeRegistry.CRYSTAL).setSimple(SILICON_DIOXIDE to 1).apply {
        color = RagiColor.WHITE
        crystalType = EnumCrystalType.RUBY
    }.build()

    val QUARTZ = RagiMaterial.Builder(143, "quartz", TypeRegistry.CRYSTAL).setSimple(SILICON_DIOXIDE to 1).apply {
        color = RagiColor.WHITE
        crystalType = EnumCrystalType.QUARTZ
    }.build()

    //150 ~ 159: Phosphorus
    val PHOSPHORUS = RagiMaterial.Builder(150, "phosphorus", TypeRegistry.DUST).setSimple(ElementRegistry.PHOSPHORUS to 1).build()

    //160 ~ 169: Sulfur
    val SULFUR = RagiMaterial.Builder(160, "sulfur", TypeRegistry.DUST).setSimple(ElementRegistry.SULFUR to 8).build()

    val SULFURIC_ACID = RagiMaterial.Builder(161, "sulfuric_acid", TypeRegistry.LIQUID).setComponents(listOf(ElementRegistry.HYDROGEN to 2, SULFATE to 1)).apply {
        color = ColorManager.mixColor(RagiColor.GOLD, RagiColor.YELLOW)
    }.build()

    //170 ~ 179: Chlorine
    val CHLORINE = RagiMaterial.Builder(170, "chlorine", TypeRegistry.GAS).setSimple(ElementRegistry.CHLORINE to 2).build()

    val HYDROGEN_CHLORIDE = RagiMaterial.Builder(171, "hydrogen_chloride", TypeRegistry.GAS).setComponents(listOf(ElementRegistry.HYDROGEN to 1, ElementRegistry.CHLORINE to 1)).build()

    //180 ~ 189: Stone
    val STONE = RagiMaterial.Builder(180, "stone", TypeRegistry.STONE).setSimple(SILICON_DIOXIDE to 1).apply {
        color = RagiColor.GRAY
    }.build()

    val LAVA = RagiMaterial.Builder(181, "lava", TypeRegistry.INTERNAL).setSimple(SILICON_DIOXIDE to 1).apply {
        color = ColorManager.mixColor(RagiColor.DARK_RED, RagiColor.GOLD)
        tempMelt = FluidRegistry.LAVA.temperature
    }.build()

    val OBSIDIAN = RagiMaterial.Builder(182, "obsidian", TypeRegistry.DUST).setSimple(SILICON_DIOXIDE to 1).apply {
        color = ColorManager.mixColor(RagiColor.BLACK to 2, RagiColor.BLUE to 1, RagiColor.RED to 1)
    }.build()

    val NETHERRACK = RagiMaterial.Builder(183, "netherrack", TypeRegistry.DUST).setSimple(SILICON_DIOXIDE to 1).apply {
        color = RagiColor.DARK_RED
    }.build()

    val SOUL_SAND = RagiMaterial.Builder(184, "soul_sand", TypeRegistry.DUST).setSimple(SILICON_DIOXIDE to 1).apply {
        color = ColorManager.mixColor(RagiColor.BLACK to 5, RagiColor.GOLD to 1)
    }.build()

    val END_STONE = RagiMaterial.Builder(185, "end_stone", TypeRegistry.DUST).setSimple(SILICON_DIOXIDE to 1).apply {
        color = ColorManager.mixColor(RagiColor.YELLOW to 1, RagiColor.WHITE to 3)
    }.build()

    //190 ~ 199: Potassium
    val POTASSIUM = RagiMaterial.Builder(190, "potassium", TypeRegistry.INTERNAL).setSimple(ElementRegistry.POTASSIUM to 1).build()

    //200 ~ 209: Calcium
    val CALCIUM = RagiMaterial.Builder(200, "calcium", TypeRegistry.INTERNAL).setSimple(ElementRegistry.CALCIUM to 1).build()

    val CALCIUM_HYDROXIDE = RagiMaterial.Builder(201, "calcium_hydroxide", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.CALCIUM to 1, HYDROXIDE.setBracket() to 2)).build()

    val LIME = RagiMaterial.Builder(202, "lime", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.CALCIUM to 1, CARBONATE to 1)).build()

    val QUICK_LIME = RagiMaterial.Builder(203, "quick_lime", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.CALCIUM to 1, ElementRegistry.OXYGEN to 1)).build()

    val APATITE = RagiMaterial.Builder(204, "apatite", TypeRegistry.CRYSTAL).setComponents(listOf(ElementRegistry.CALCIUM to 5, PHOSPHATE.setBracket() to 3, HYDROXIDE to 1)).apply {
        color = ColorManager.mixColor(RagiColor.YELLOW, RagiColor.WHITE)
        crystalType = EnumCrystalType.EMERALD
    }.build()

    val GYPSUM = RagiMaterial.Builder(205, "gypsum", TypeRegistry.CRYSTAL).setComponents(listOf(ElementRegistry.CALCIUM to 1, SULFATE to 1)).build()

    //220 ~ 229: Titanium
    val TITANIUM = RagiMaterial.Builder(220, "titanium", TypeRegistry.METAL).setSimple(ElementRegistry.TITANIUM to 1).apply {
        rarity = EnumRarity.RARE
    }.build()

    val RUTILE = RagiMaterial.Builder(221, "rutile", TypeRegistry.CRYSTAL).setComponents(listOf(ElementRegistry.TITANIUM to 1, ElementRegistry.OXYGEN to 2)).apply {
        color = RagiColor.YELLOW
        crystalType = EnumCrystalType.QUARTZ
        rarity = EnumRarity.RARE
    }.build()

    val TITANIUM_TETRACHLORIDE = RagiMaterial.Builder(222, "titanium_tetrachloride", TypeRegistry.LIQUID).setComponents(listOf(ElementRegistry.TITANIUM to 1, ElementRegistry.CHLORINE to 4)).apply {
        rarity = EnumRarity.RARE
    }.build()

    //240 ~ 249: Chromium
    val CHROMIUM = RagiMaterial.Builder(240, "chromium", TypeRegistry.METAL).setSimple(ElementRegistry.CHROMIUM to 1).apply {
        oredictAlt = "Chrome"
        rarity = EnumRarity.UNCOMMON
    }.build()

    val STAINLESS_STEEL = RagiMaterial.Builder(241, "stainless_steel", TypeRegistry.METAL).setComponents(listOf(ElementRegistry.IRON to 6, ElementRegistry.CHROMIUM to 1, ElementRegistry.MANGANESE to 1, ElementRegistry.NICKEL to 1)).apply {
        color = ColorManager.mixColor(RagiColor.GRAY, RagiColor.WHITE)
        rarity = EnumRarity.RARE
    }.build()

    //250 ~ 259: Manganese
    val MANGANESE = RagiMaterial.Builder(250, "manganese", TypeRegistry.METAL).setSimple(ElementRegistry.MANGANESE to 1).apply {
        rarity = EnumRarity.UNCOMMON
    }.build()

    //260 ~ 269: Iron
    val IRON = RagiMaterial.Builder(260, "iron", TypeRegistry.METAL).setSimple(ElementRegistry.IRON to 1).build()

    val HEMATITE = RagiMaterial.Builder(261, "hematite", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.IRON to 2, ElementRegistry.OXYGEN to 3)).apply {
        color = ColorManager.mixColor(RagiColor.BLACK to 1, RagiColor.DARK_RED to 2, RagiColor.GOLD to 1)
    }.build()

    val MAGNETITE = RagiMaterial.Builder(262, "magnetite", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.IRON to 3, ElementRegistry.OXYGEN to 4)).apply {
        color = ColorManager.mixColor(RagiColor.BLACK, RagiColor.DARK_GRAY)
    }.build()

    val PYRITE = RagiMaterial.Builder(263, "pyrite", TypeRegistry.CRYSTAL).setComponents(listOf(ElementRegistry.IRON to 1, ElementRegistry.SULFUR to 2)).apply {
        color = RagiColor.YELLOW
        crystalType = EnumCrystalType.CUBIC
    }.build()

    val ARSENOPYRITE = RagiMaterial.Builder(264, "arsenopyrite", TypeRegistry.CRYSTAL).setComponents(listOf(ElementRegistry.IRON to 1, ElementRegistry.ARSENIC to 1, ElementRegistry.SULFUR to 1)).apply {
        crystalType = EnumCrystalType.CUBIC
    }.build()

    val STEEL = RagiMaterial.Builder(265, "steel", TypeRegistry.METAL).setComponents(listOf(ElementRegistry.IRON to 1, ElementRegistry.CARBON to 1)).apply {
        rarity = EnumRarity.UNCOMMON
    }.build()

    //270 ~ 279: Cobalt
    val COBALT = RagiMaterial.Builder(270, "cobalt", TypeRegistry.METAL).setSimple(ElementRegistry.COBALT to 1).apply {
        rarity = EnumRarity.UNCOMMON
    }.build()

    //280 ~ 289: Nickel
    val NICKEL = RagiMaterial.Builder(280, "nickel", TypeRegistry.METAL).setSimple(ElementRegistry.NICKEL to 1).apply {
        rarity = EnumRarity.UNCOMMON
    }.build()

    val INVAR = RagiMaterial.Builder(281, "invar", TypeRegistry.METAL).setComponents(listOf(ElementRegistry.IRON to 1, ElementRegistry.NICKEL to 2)).apply {
        rarity = EnumRarity.UNCOMMON
    }.build()

    val CONSTANTAN = RagiMaterial.Builder(282, "constantan", TypeRegistry.METAL).setComponents(listOf(ElementRegistry.NICKEL to 1, ElementRegistry.COPPER to 1)).apply {
        rarity = EnumRarity.UNCOMMON
    }.build()

    //290 ~ 299: Copper
    val COPPER = RagiMaterial.Builder(290, "copper", TypeRegistry.METAL).setSimple(ElementRegistry.COPPER to 1).build()

    //300 ~ 309: Zinc
    val ZINC = RagiMaterial.Builder(300, "zinc", TypeRegistry.METAL).setSimple(ElementRegistry.ZINC to 1).build()

    //val SPHALERITE = RagiMaterial.Builder(301, "sphalerite", TypeRegistry.DUST).setComponents(listOf(ElementRegistry.ZINC to 1, ElementRegistry.SULFUR to 1)).build()

    val BRASS = RagiMaterial.Builder(302, "brass", TypeRegistry.METAL).setComponents(listOf(ElementRegistry.COPPER to 3, ElementRegistry.ZINC to 1)).apply {
        color = RagiColor.GOLD
    }.build()

    //310 ~ 319: Gallium, Arsenic
    val GALLIUM = RagiMaterial.Builder(310, "gallium", TypeRegistry.METAL).setSimple(ElementRegistry.GALLIUM to 1).apply {
        rarity = EnumRarity.RARE
    }.build()

    val ARSENIC = RagiMaterial.Builder(311, "arsenic", TypeRegistry.METALLOID).setSimple(ElementRegistry.ARSENIC to 1).apply {
        rarity = EnumRarity.RARE
    }.build()

    //380 ~ 389: Strontium
    val STRONTIUM = RagiMaterial.Builder(380, "strontium", TypeRegistry.INTERNAL).setSimple(ElementRegistry.STRONTIUM to 1).build()

    //400 ~ 409: Zirconium
    val ZIRCONIUM = RagiMaterial.Builder(400, "zirconium", TypeRegistry.METAL).setSimple(ElementRegistry.ZIRCONIUM to 1).build()

    //410 ~ 419: Niobium
    val NIOBIUM = RagiMaterial.Builder(410, "niobium", TypeRegistry.METAL).setSimple(ElementRegistry.NIOBIUM to 1).build()

    //420 ~ 429: Molybdenum
    val MOLYBDENUM = RagiMaterial.Builder(420, "molybdenum", TypeRegistry.METAL).setSimple(ElementRegistry.MOLYBDENUM to 1).build()

    //440 ~ 449: Platinum Group Metal
    val RUTHENIUM = RagiMaterial.Builder(440, "ruthenium", TypeRegistry.METAL).setSimple(ElementRegistry.RUTHENIUM to 1).build()

    val RHODIUM = RagiMaterial.Builder(441, "rhodium", TypeRegistry.METAL).setSimple(ElementRegistry.RHODIUM to 1).build()

    val PALLADIUM = RagiMaterial.Builder(442, "palladium", TypeRegistry.METAL).setSimple(ElementRegistry.PALLADIUM to 1).build()

    val OSMIUM = RagiMaterial.Builder(443, "osmium", TypeRegistry.METAL).setSimple(ElementRegistry.OSMIUM to 1).build()

    val IRIDIUM = RagiMaterial.Builder(444, "iridium", TypeRegistry.METAL).setSimple(ElementRegistry.IRIDIUM to 1).build()

    val PLATINUM = RagiMaterial.Builder(445, "platinum", TypeRegistry.METAL).setSimple(ElementRegistry.PLATINUM to 1).build()

    //470 ~ 479: Silver
    val SILVER = RagiMaterial.Builder(470, "silver", TypeRegistry.METAL).setSimple(ElementRegistry.SILVER to 1).build()

    //490 ~ 499: Indium
    val INDIUM = RagiMaterial.Builder(490, "indium", TypeRegistry.METAL).setSimple(ElementRegistry.INDIUM to 1).build()

    //500 ~ 509: Tin
    val TIN = RagiMaterial.Builder(500, "tin", TypeRegistry.METAL).setSimple(ElementRegistry.TIN to 1).build()

    //510 ~ 519: Antimony
    val ANTIMONY = RagiMaterial.Builder(510, "antimony", TypeRegistry.METAL).setSimple(ElementRegistry.ANTIMONY to 1).build()

    //530 ~ 539: Iodine
    val IODINE = RagiMaterial.Builder(530, "iodine", TypeRegistry.METALLOID).setSimple(ElementRegistry.IODINE to 2).build()

    //560 ~ 569: Barium
    val BARIUM = RagiMaterial.Builder(560, "barium", TypeRegistry.INTERNAL).setSimple(ElementRegistry.BARIUM to 1).build()

    //570 ~ 579: Rare Earth
    val NEODYMIUM = RagiMaterial.Builder(570, "neodymium", TypeRegistry.METAL).setSimple(ElementRegistry.NEODYMIUM to 1).build()

    val SAMARIUM = RagiMaterial.Builder(571, "samarium", TypeRegistry.METAL).setSimple(ElementRegistry.SAMARIUM to 1).build()

    //720 ~ 729: Hafnium
    val HAFNIUM = RagiMaterial.Builder(720, "hafnium", TypeRegistry.METAL).setSimple(ElementRegistry.HAFNIUM to 1).build()

    //730 ~ 739: Tantalum
    val TANTALUM = RagiMaterial.Builder(730, "tantalum", TypeRegistry.METAL).setSimple(ElementRegistry.TANTALUM to 1).build()

    //740 ~ 749: Tungsten
    val TUNGSTEN = RagiMaterial.Builder(740, "tungsten", TypeRegistry.METAL).setSimple(ElementRegistry.TUNGSTEN to 1).build()

    //790 ~ 799: Gold
    val GOLD = RagiMaterial.Builder(790, "gold", TypeRegistry.METAL).setSimple(ElementRegistry.GOLD to 1).build()

    //800 ~ 809: Mercury
    val MERCURY = RagiMaterial.Builder(800, "mercury", TypeRegistry.LIQUID).setSimple(ElementRegistry.MERCURY to 1).build()

    //820 ~ 829: Lead
    val LEAD = RagiMaterial.Builder(820, "lead", TypeRegistry.METAL).setSimple(ElementRegistry.LEAD to 1).build()

    //830 ~ 839: Bismuth
    val BISMUTH = RagiMaterial.Builder(830, "bismuth", TypeRegistry.METAL).setSimple(ElementRegistry.BISMUTH to 1).build()

    //900 ~ 909: Thorium
    val THORIUM = RagiMaterial.Builder(900, "thorium", TypeRegistry.METAL_RADIO).setSimple(ElementRegistry.THORIUM to 1).build()

    //920 ~ 929: Uranium
    val URANIUM_238 = RagiMaterial.Builder(920, "uranium", TypeRegistry.METAL_RADIO).setSimple(ElementRegistry.URANIUM_238 to 1).build()

    val URANIUM_235 = RagiMaterial.Builder(921, "uranium235", TypeRegistry.METAL_RADIO).setSimple(ElementRegistry.URANIUM_235 to 1).build()

    //940 ~ 949: Plutonium
    val PLUTONIUM_244 = RagiMaterial.Builder(940, "plutonium", TypeRegistry.METAL_RADIO).setSimple(ElementRegistry.PLUTONIUM_244 to 1).build()

    val PLUTONIUM_239 = RagiMaterial.Builder(941, "plutonium239", TypeRegistry.METAL_RADIO).setSimple(ElementRegistry.PLUTONIUM_239 to 1).build()

}