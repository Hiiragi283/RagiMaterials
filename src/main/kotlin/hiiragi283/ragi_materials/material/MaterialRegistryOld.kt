package hiiragi283.ragi_materials.material

import hiiragi283.ragi_materials.client.render.color.RagiColor
import hiiragi283.ragi_materials.material.type.TypeRegistry
import hiiragi283.ragi_materials.client.render.color.RagiColorManager
import hiiragi283.ragi_materials.material.builder.*
import hiiragi283.ragi_materials.material.part.MaterialPart
import net.minecraft.item.EnumRarity
import net.minecraftforge.fluids.FluidRegistry

class MaterialRegistryOld {

    val list: MutableList<MaterialBuilder> = mutableListOf()
    val mapIndex: LinkedHashMap<Int, MaterialBuilder> = linkedMapOf()
    val mapName: LinkedHashMap<String, MaterialBuilder> = linkedMapOf()

    val validPair: MutableList<Pair<MaterialPart, MaterialBuilder>> = mutableListOf()

    //Pre-registration
    val HYDROXIDE = CompoundBuilder(-1, "hydroxide", TypeRegistry.INTERNAL, mapOf(ElementRegistryOld.OXYGEN to 1, ElementRegistryOld.HYDROGEN to 1))

    val BORATE = CompoundBuilder(-1, "borate", TypeRegistry.INTERNAL, mapOf(ElementRegistryOld.BORON to 1, ElementRegistryOld.OXYGEN to 3))

    val CARBONATE = CompoundBuilder(-1, "carbonate", TypeRegistry.INTERNAL, mapOf(ElementRegistryOld.CARBON to 1, ElementRegistryOld.OXYGEN to 3))

    val NITRATE = CompoundBuilder(-1, "nitrate", TypeRegistry.INTERNAL, mapOf(ElementRegistryOld.NITROGEN to 1, ElementRegistryOld.OXYGEN to 3))

    val SILICATE = CompoundBuilder(-1, "silicate", TypeRegistry.INTERNAL, mapOf(ElementRegistryOld.SILICON to 1, ElementRegistryOld.OXYGEN to 4))

    val PHOSPHATE = CompoundBuilder(-1, "phosphate", TypeRegistry.INTERNAL, mapOf(ElementRegistryOld.PHOSPHORUS to 1, ElementRegistryOld.OXYGEN to 4))

    val SULFATE = CompoundBuilder(-1, "sulfate", TypeRegistry.INTERNAL, mapOf(ElementRegistryOld.SULFUR to 1, ElementRegistryOld.OXYGEN to 4))

    val TITANATE = CompoundBuilder(-1, "titanate", TypeRegistry.INTERNAL, mapOf(ElementRegistryOld.TITANIUM to 1, ElementRegistryOld.OXYGEN to 4))

    val TUNGSTATE = CompoundBuilder(-1, "tungstate", TypeRegistry.INTERNAL, mapOf(ElementRegistryOld.TUNGSTEN to 1, ElementRegistryOld.OXYGEN to 4))

    //10 ~ 19: Hydrogen
    val HYDROGEN = SimpleBuilder(ElementRegistryOld.HYDROGEN, 2)

    val WATER = CompoundBuilder(11, "water", TypeRegistry.INTERNAL, mapOf(ElementRegistryOld.HYDROGEN to 2, ElementRegistryOld.OXYGEN to 1)).apply {
        color = RagiColor.BLUE
        tempBoil = 100
        tempMelt = 0
    }

    val SNOW = CompoundBuilder(12, "snow", TypeRegistry.INGOT, mapOf(WATER to 1)).apply {
        color = RagiColor.WHITE
    }

    val ICE = CompoundBuilder(13, "ice", TypeRegistry.INGOT, mapOf(WATER to 1)).apply {
        color = RagiColorManager.mixColor(RagiColor.AQUA, RagiColor.WHITE)
        hasOre = true
    }

    val DEUTERIUM = SimpleBuilder(ElementRegistryOld.DEUTERIUM, 2).apply {
        rarity = EnumRarity.EPIC
    }

    val TRITIUM = SimpleBuilder(ElementRegistryOld.TRITIUM, 2).apply {
        rarity = EnumRarity.EPIC
    }

    //20 ~ 29: Helium, Neon, Argon
    val HELIUM = SimpleBuilder(ElementRegistryOld.HELIUM, 1).apply {
        rarity = EnumRarity.RARE
    }

    val NEON = SimpleBuilder(ElementRegistryOld.NEON, 1).apply {
        rarity = EnumRarity.RARE
    } //21

    val ARGON = SimpleBuilder(ElementRegistryOld.ARGON, 1).apply {
        rarity = EnumRarity.RARE
    } //22

    //30 ~ 39: Lithium
    val LITHIUM = SimpleBuilder(ElementRegistryOld.LITHIUM, 1)

    //40 ~ 49: Beryllium
    val BERYLLIUM = SimpleBuilder(ElementRegistryOld.BERYLLIUM, 1)

    val EMERALD = CrystalBuilder(41, "emerald", mapOf(ElementRegistryOld.BERYLLIUM to 3, ElementRegistryOld.ALUMINIUM to 2, ElementRegistryOld.SILICON to 6, ElementRegistryOld.OXYGEN to 18), "emerald").apply {
        color = RagiColor.GREEN
        hasOre = true
    }

    //50 ~ 59: Boron
    val BORON = SimpleBuilder(ElementRegistryOld.BORON, 1)

    val BORAX = CompoundBuilder(51, "borax", TypeRegistry.DUST, mapOf(ElementRegistryOld.SODIUM to 2, ElementRegistryOld.HYDROGEN to 20, ElementRegistryOld.BORON to 4, ElementRegistryOld.OXYGEN to 17)).apply {
        hasOre = true
    }

    val BORON_OXIDE = CompoundBuilder(52, "boron_oxide", TypeRegistry.DUST, mapOf(ElementRegistryOld.BORON to 2, ElementRegistryOld.OXYGEN to 3))

    //60 ~ 69: Carbon
    val CARBON = SimpleBuilder(ElementRegistryOld.CARBON, 1)

    val CARBON_DIOXIDE = CompoundBuilder(61, "carbon_dioxide", TypeRegistry.GAS, mapOf(ElementRegistryOld.CARBON to 1, ElementRegistryOld.OXYGEN to 2))

    val COAL = CrystalBuilder(62, "coal", mapOf(ElementRegistryOld.CARBON to 1), "coal").apply {
        burnTime = 200 * 8
        hasOre = true
    }

    val CHARCOAL = CrystalBuilder(63, "charcoal", mapOf(ElementRegistryOld.CARBON to 1), "coal").apply {
        burnTime = 200 * 8
    }

    val COKE = CrystalBuilder(64, "coke", mapOf(ElementRegistryOld.CARBON to 1), "coal").apply {
        burnTime = 200 * 16
        color = RagiColor.DARK_GRAY
    }

    val ANTHRACITE = CrystalBuilder(65, "anthracite", mapOf(ElementRegistryOld.CARBON to 1), "coal").apply {
        burnTime = 200 * 24
        color = RagiColorManager.mixColor(mapOf(COAL.color to 5, RagiColor.DARK_BLUE to 1))
        hasOre = true
    }

    val LIGNITE = CrystalBuilder(66, "lignite", mapOf(ElementRegistryOld.CARBON to 1), "coal").apply {
        burnTime = 200 * 4
        color = RagiColorManager.mixColor(mapOf(COAL.color to 5, RagiColor.DARK_RED to 1))
        hasOre = true
    }

    val PEAT = CrystalBuilder(67, "peat", mapOf(ElementRegistryOld.CARBON to 1), "coal").apply {
        burnTime = 200 * 2
        color = RagiColorManager.mixColor(mapOf(COAL.color to 5, RagiColor.DARK_GREEN to 1, RagiColor.DARK_RED to 1))
        hasOre = true
    }

    val DIAMOND = CrystalBuilder(68, "diamond", mapOf(ElementRegistryOld.CARBON to 1), "diamond").apply {
        color = RagiColorManager.mixColor(RagiColor.AQUA, RagiColor.WHITE)
        hasOre = true
        rarity = EnumRarity.RARE
    }

    val GRAPHITE = CompoundBuilder(69, "graphite", TypeRegistry.DUST, mapOf(ElementRegistryOld.CARBON to 1))

    //70 ~ 79: Nitrogen
    val NITROGEN = SimpleBuilder(ElementRegistryOld.NITROGEN, 2)

    val NITRIC_ACID = CompoundBuilder(71, "nitric_acid", TypeRegistry.LIQUID, mapOf(ElementRegistryOld.HYDROGEN to 1, NITRATE to 1))

    val NITER = CrystalBuilder(72, "niter", mapOf(ElementRegistryOld.POTASSIUM to 1, NITRATE to 1), "quartz").apply {
        color = RagiColor.WHITE
        hasOre = true
        oredictAlt = "Saltpeter"
    }

    val NITRATINE = CrystalBuilder(73, "nitratine", mapOf(ElementRegistryOld.SODIUM to 1, NITRATE to 1), "quartz").apply {
        color = RagiColor.WHITE
        hasOre = true
    }

    //80 ~ 89: Oxygen
    val OXYGEN = SimpleBuilder(ElementRegistryOld.OXYGEN, 2)

    //90 ~ 99: Fluorite
    val FLUORINE = SimpleBuilder(ElementRegistryOld.FLUORINE, 2)

    val FLUORITE = CrystalBuilder(91, "fluorite", mapOf(ElementRegistryOld.CALCIUM to 1, ElementRegistryOld.FLUORINE to 2), "cubic").apply {
        color = RagiColorManager.mixColor(RagiColor.GREEN, RagiColor.AQUA)
        hasOre = true
    }

    val HYDROGEN_FLUORIDE = CompoundBuilder(92, "hydrogen_fluoride", TypeRegistry.LIQUID, mapOf(ElementRegistryOld.HYDROGEN to 1, ElementRegistryOld.FLUORINE to 1))

    //100 ~ 109:

    //110 ~ 119: Sodium
    val SODIUM = SimpleBuilder(ElementRegistryOld.SODIUM, 1)

    val SODIUM_HYDROXIDE = CompoundBuilder(111, "sodium_hydroxide", TypeRegistry.DUST, mapOf(ElementRegistryOld.SODIUM to 1, HYDROXIDE to 1))

    val SODIUM_BICARBONATE = CompoundBuilder(112, "sodium_bicarbonate", TypeRegistry.DUST, mapOf(ElementRegistryOld.SODIUM to 1, ElementRegistryOld.HYDROGEN to 1, CARBONATE to 1))

    val SODIUM_CARBONATE = CompoundBuilder(113, "sodium_carbonate", TypeRegistry.DUST, mapOf(ElementRegistryOld.SODIUM to 2, CARBONATE to 1))

    val SODIUM_SULFIDE = CompoundBuilder(114, "sodium_sulfide", TypeRegistry.DUST, mapOf(ElementRegistryOld.SODIUM to 2, ElementRegistryOld.SULFUR to 1))

    val SODIUM_BISULFATE = CompoundBuilder(115, "sodium_bisulfate", TypeRegistry.DUST, mapOf(ElementRegistryOld.SODIUM to 1, ElementRegistryOld.HYDROGEN to 1, SULFATE to 1))

    val SODIUM_SULFATE = CompoundBuilder(116, "sodium_sulfate", TypeRegistry.DUST, mapOf(ElementRegistryOld.SODIUM to 2, SULFATE to 1))

    //120 ~ 129: Magnesium
    val MAGNESIUM = SimpleBuilder(ElementRegistryOld.MAGNESIUM, 1)

    val MAGNESIUM_CARBONATE = CompoundBuilder(121, "magnesium_carbonate", TypeRegistry.DUST, mapOf(ElementRegistryOld.MAGNESIUM to 1, CARBONATE to 1)).apply {
        hasOre = true
    }

    //130 ~ 139: Aluminium
    val ALUMINIUM = SimpleBuilder(ElementRegistryOld.ALUMINIUM, 1).apply {
        oredictAlt = "Aluminum"
    }

    val ALUMINIUM_OXIDE = CompoundBuilder(131, "aluminium_oxide", TypeRegistry.DUST, mapOf(ElementRegistryOld.ALUMINIUM to 2, ElementRegistryOld.OXYGEN to 3))

    val BAUXITE = CompoundBuilder(132, "bauxite", TypeRegistry.DUST, mapOf(ALUMINIUM_OXIDE to 1, WATER.addBracket() to 2)).apply {
        color = RagiColorManager.mixColor(mapOf(RagiColor.BLACK to 1, RagiColor.DARK_RED to 2, RagiColor.GOLD to 1))
        hasOre = true
    }

    val RUBY = CrystalBuilder(133, "ruby", mapOf(ElementRegistryOld.CHROMIUM to 1, ALUMINIUM_OXIDE to 1), "ruby").apply {
        color = RagiColor.RED
        hasOre = true
    }

    val SAPPHIRE = CrystalBuilder(134, "sapphire", mapOf(ElementRegistryOld.IRON to 1, ALUMINIUM_OXIDE to 1), "ruby").apply {
        color = RagiColor.BLUE
        hasOre = true
    }

    val ALUMINA_SOLUTION = CompoundBuilder(135, "alumina_solution", TypeRegistry.LIQUID, mapOf(
            ElementRegistryOld.SODIUM to 1,
            FormulaString("[") to 1,
            ElementRegistryOld.ALUMINIUM to 1,
            HYDROXIDE.addBracket() to 4,
            FormulaString("]") to 1
    ))

    //140 ~ 149: Silicon
    val SILICON = SimpleBuilder(ElementRegistryOld.SILICON, 1)

    val SILICON_DIOXIDE = CrystalBuilder(141, "silicon_dioxide", mapOf(ElementRegistryOld.SILICON to 1, ElementRegistryOld.OXYGEN to 2), "ruby")

    val GLASS = CompoundBuilder(142, "glass", TypeRegistry.DUST, mapOf(SILICON_DIOXIDE to 1)).apply {
        color = RagiColor.WHITE
    }

    //150 ~ 159: Phosphorus
    val PHOSPHORUS = SimpleBuilder(ElementRegistryOld.PHOSPHORUS, 1).apply {
        hasOre = true
    }

    //160 ~ 169: Sulfur
    val SULFUR = SimpleBuilder(ElementRegistryOld.SULFUR, 8).apply {
        hasOre = true
    }

    val SULFURIC_ACID = CompoundBuilder(161, "sulfuric_acid", TypeRegistry.LIQUID, mapOf(ElementRegistryOld.HYDROGEN to 2, SULFATE to 1)).apply {
        color = RagiColorManager.mixColor(RagiColor.GOLD, RagiColor.YELLOW)
    }

    //170 ~ 179: Chlorine
    val CHLORINE = SimpleBuilder(ElementRegistryOld.CHLORINE, 2)

    val HYDROGEN_CHLORIDE = CompoundBuilder(171, "hydrogen_chloride", TypeRegistry.LIQUID, mapOf(ElementRegistryOld.HYDROGEN to 1, ElementRegistryOld.CHLORINE to 1))

    val LITHIUM_CHLORIDE = CompoundBuilder(172, "lithium_chloride", TypeRegistry.DUST, mapOf(ElementRegistryOld.LITHIUM to 1, ElementRegistryOld.CHLORINE to 1)).apply {
        color = RagiColor.GOLD
    }

    val SALT = CompoundBuilder(173, "salt", TypeRegistry.DUST, mapOf(ElementRegistryOld.SODIUM to 1, ElementRegistryOld.CHLORINE to 1)).apply {
        color = RagiColor.WHITE
        hasOre = true
    }

    val MAGNESIUM_CHLORIDE = CompoundBuilder(174, "magnesium_chloride", TypeRegistry.DUST, mapOf(ElementRegistryOld.MAGNESIUM to 1, ElementRegistryOld.CHLORINE to 2)).apply {
        color = RagiColor.YELLOW
    }

    val SALTWATER = MixtureBuilder(175, "saltwater", TypeRegistry.LIQUID, listOf(WATER, SALT)).apply {
        color = RagiColor.YELLOW
    }

    val BRINE = MixtureBuilder(176, "brine", TypeRegistry.LIQUID, listOf(WATER, MAGNESIUM_CHLORIDE)).apply {
        color = RagiColor.GOLD
    }

    //180 ~ 189: Stone
    val STONE = CompoundBuilder(180, "stone", TypeRegistry.STONE, mapOf(SILICON_DIOXIDE to 1)).apply {
        color = RagiColor.GRAY
    }

    val LAVA = CompoundBuilder(181, "lava", TypeRegistry.INTERNAL, mapOf(SILICON_DIOXIDE to 1)).apply {
        color = RagiColorManager.mixColor(RagiColor.DARK_RED, RagiColor.GOLD)
        tempMelt = FluidRegistry.LAVA.temperature
    }

    val OBSIDIAN = CompoundBuilder(182, "obsidian", TypeRegistry.DUST, mapOf(SILICON_DIOXIDE to 1)).apply {
        color = RagiColorManager.mixColor(mapOf(RagiColor.BLACK to 2, RagiColor.BLUE to 1, RagiColor.RED to 1))
    }

    val NETHERRACK = CompoundBuilder(183, "netherrack", TypeRegistry.DUST, mapOf(SILICON_DIOXIDE.addBracket() to 1, ElementRegistryOld.SULFUR to 1, ElementRegistryOld.PHOSPHORUS to 1)).apply {
        color = RagiColor.DARK_RED
    }

    val SOUL_SAND = CompoundBuilder(184, "soul_sand", TypeRegistry.DUST, mapOf(SILICON_DIOXIDE to 1)).apply {
        color = RagiColorManager.mixColor(mapOf(RagiColor.BLACK to 5, RagiColor.GOLD to 1))
    }

    val END_STONE = CompoundBuilder(185, "end_stone", TypeRegistry.DUST, mapOf(STONE.addBracket() to 1, ElementRegistryOld.HELIUM to 1, ElementRegistryOld.TUNGSTEN to 1)).apply {
        color = RagiColorManager.mixColor(mapOf(RagiColor.YELLOW to 1, RagiColor.WHITE to 3))
    }

    //190 ~ 199: Potassium
    val POTASSIUM = SimpleBuilder(ElementRegistryOld.POTASSIUM, 1)

    //200 ~ 209: Calcium
    val CALCIUM = SimpleBuilder(ElementRegistryOld.CALCIUM, 1)

    val CALCIUM_HYDROXIDE = CompoundBuilder(201, "calcium_hydroxide", TypeRegistry.DUST, mapOf(ElementRegistryOld.CALCIUM to 1, HYDROXIDE.addBracket() to 2))

    val CALCIUM_CARBONATE = CompoundBuilder(202, "calcium_carbonate", TypeRegistry.DUST, mapOf(ElementRegistryOld.CALCIUM to 1, CARBONATE to 1))

    val CALCIUM_OXIDE = CompoundBuilder(203, "calcium_oxide", TypeRegistry.DUST, mapOf(ElementRegistryOld.CALCIUM to 1, ElementRegistryOld.OXYGEN to 1))

    val APATITE = CrystalBuilder(204, "apatite", mapOf(ElementRegistryOld.CALCIUM to 5, PHOSPHATE.addBracket() to 3, HYDROXIDE to 1), "emerald").apply {
        color = RagiColorManager.mixColor(RagiColor.YELLOW, RagiColor.WHITE)
        hasOre = true
    }

    val GYPSUM = CrystalBuilder(205, "gypsum", mapOf(ElementRegistryOld.CALCIUM to 1, SULFATE to 1), "cubic")

    val CALCIUM_CHLORIDE = CompoundBuilder(206, "calcium_chloride", TypeRegistry.DUST, mapOf(ElementRegistryOld.CALCIUM to 1, ElementRegistryOld.CHLORINE to 2))

    //220 ~ 229: Titanium
    val TITANIUM = SimpleBuilder(ElementRegistryOld.TITANIUM, 1).apply {
        rarity = EnumRarity.RARE
    }

    val RUTILE = CrystalBuilder(221, "rutile", mapOf(ElementRegistryOld.TITANIUM to 1, ElementRegistryOld.OXYGEN to 2), "quartz").apply {
        hasOre = true
    }

    val TITANIUM_TETRACHLORIDE = CompoundBuilder(222, "titanium_tetrachloride", TypeRegistry.LIQUID, mapOf(ElementRegistryOld.TITANIUM to 1, ElementRegistryOld.CHLORINE to 4))

    //240 ~ 249: Chromium
    val CHROMIUM = SimpleBuilder(ElementRegistryOld.CHROMIUM, 1).apply {
        oredictAlt = "Chrome"
    }

    val CHROMITE = CompoundBuilder(241, "chromite", TypeRegistry.DUST, mapOf(ElementRegistryOld.IRON to 1, ElementRegistryOld.CHROMIUM to 2, ElementRegistryOld.OXYGEN to 4)).apply {
        hasOre = true
    }

    val STAINLESS_STEEL = AlloyBuilder(242, "stainless_steel", mapOf(ElementRegistryOld.IRON to 6, ElementRegistryOld.CHROMIUM to 1, ElementRegistryOld.MANGANESE to 1, ElementRegistryOld.NICKEL to 1)).apply {
        color = RagiColorManager.mixColor(RagiColor.GRAY, RagiColor.WHITE)
        rarity = EnumRarity.RARE
    }

    //250 ~ 259: Manganese
    val MANGANESE = SimpleBuilder(ElementRegistryOld.MANGANESE, 1)

    val RHODOCHROSITE = CompoundBuilder(251, "rhodochrosite", TypeRegistry.DUST, mapOf(ElementRegistryOld.MANGANESE to 1, CARBONATE to 1)).apply {
        hasOre = true
    }

    val PYROLUSITE = CompoundBuilder(252, "pyrolusite", TypeRegistry.DUST, mapOf(ElementRegistryOld.MANGANESE to 1, ElementRegistryOld.OXYGEN to 2)).apply {
        hasOre = true
    }

    //260 ~ 269: Iron
    val IRON = SimpleBuilder(ElementRegistryOld.IRON, 1)

    val HEMATITE = CompoundBuilder(261, "hematite", TypeRegistry.DUST, mapOf(ElementRegistryOld.IRON to 2, ElementRegistryOld.OXYGEN to 3)).apply {
        color = RagiColorManager.mixColor(mapOf(RagiColor.BLACK to 1, RagiColor.DARK_RED to 2, RagiColor.GOLD to 1))
        hasOre = true
    }

    val MAGNETITE = CompoundBuilder(262, "magnetite", TypeRegistry.DUST, mapOf(ElementRegistryOld.IRON to 3, ElementRegistryOld.OXYGEN to 4)).apply {
        color = RagiColorManager.mixColor(RagiColor.BLACK, RagiColor.DARK_GRAY)
        hasOre = true
    }

    val PYRITE = CompoundBuilder(263, "pyrite", TypeRegistry.DUST, mapOf(ElementRegistryOld.IRON to 1, ElementRegistryOld.SULFUR to 2)).apply {
        hasOre = true
    }

    val STEEL = AlloyBuilder(264, "steel", mapOf(ElementRegistryOld.IRON to 1, ElementRegistryOld.CARBON to 1)).apply {
        rarity = EnumRarity.UNCOMMON
    }

    val TOOL_STEEL = AlloyBuilder(265, "tool_steel", mapOf(ElementRegistryOld.IRON to 6, ElementRegistryOld.CHROMIUM to 1, ElementRegistryOld.MANGANESE to 1, ElementRegistryOld.MOLYBDENUM to 1)).apply {
        rarity = EnumRarity.RARE
    }

    //270 ~ 279: Cobalt
    val COBALT = SimpleBuilder(ElementRegistryOld.COBALT, 1)

    val COBALTITE = CompoundBuilder(271, "cobaltite", TypeRegistry.DUST, mapOf(ElementRegistryOld.COBALT to 1, ElementRegistryOld.ARSENIC to 1, ElementRegistryOld.SULFUR to 1)).apply {
        hasOre = true
    }

    //280 ~ 289: Nickel
    val NICKEL = SimpleBuilder(ElementRegistryOld.NICKEL, 1)

    val NICKELINE = CompoundBuilder(281, "nickeline", TypeRegistry.DUST, mapOf(ElementRegistryOld.NICKEL to 1, ElementRegistryOld.ARSENIC to 1)).apply {
        hasOre = true
    }

    val INVAR = AlloyBuilder(282, "invar", mapOf(ElementRegistryOld.IRON to 2, ElementRegistryOld.NICKEL to 1))

    val CONSTANTAN = AlloyBuilder(283, "constantan", mapOf(ElementRegistryOld.NICKEL to 1, ElementRegistryOld.COPPER to 1))

    //290 ~ 299: Copper
    val COPPER = SimpleBuilder(ElementRegistryOld.COPPER, 1).apply {
        hasOre = true
    }

    val CHALCOCITE = CompoundBuilder(291, "chalcocite", TypeRegistry.DUST, mapOf(ElementRegistryOld.COPPER to 2, ElementRegistryOld.SULFUR to 1)).apply {
        color = RagiColorManager.mixColor(RagiColor.BLACK, RagiColor.DARK_GRAY)
        hasOre = true
    }

    val CHALCOPYRITE = CompoundBuilder(293, "chalcopyrite", TypeRegistry.DUST, mapOf(ElementRegistryOld.COPPER to 1, ElementRegistryOld.IRON to 1, ElementRegistryOld.SULFUR to 2)).apply {
        hasOre = true
    }

    //300 ~ 309: Zinc
    val ZINC = SimpleBuilder(ElementRegistryOld.ZINC, 1)

    val SPHALERITE = CompoundBuilder(301, "sphalerite", TypeRegistry.DUST, mapOf(ElementRegistryOld.ZINC to 1, ElementRegistryOld.SULFUR to 1)).apply {
        hasOre = true
    }

    val BRASS = AlloyBuilder(302, "brass", mapOf(ElementRegistryOld.COPPER to 3, ElementRegistryOld.ZINC to 1)).apply {
        color = RagiColor.GOLD
    }

    //310 ~ 319: Gallium, Arsenic
    val GALLIUM = SimpleBuilder(ElementRegistryOld.GALLIUM, 1)

    val ARSENIC = SimpleBuilder(ElementRegistryOld.ARSENIC, 1) //311

}