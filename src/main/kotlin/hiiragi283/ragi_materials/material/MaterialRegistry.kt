package hiiragi283.ragi_materials.material

import hiiragi283.ragi_materials.material.type.TypeRegistry
import hiiragi283.ragi_materials.client.render.color.RagiColorManager
import hiiragi283.ragi_materials.material.builder.*
import net.minecraft.item.EnumRarity
import net.minecraftforge.fluids.FluidRegistry

object MaterialRegistry {

    val mapIndex: LinkedHashMap<Int, MaterialBuilder> = linkedMapOf()
    val mapName: LinkedHashMap<String, MaterialBuilder> = linkedMapOf()

    //Pre-registration
    val HYDROXIDE = CompoundBuilder(-1, "hydroxide", TypeRegistry.INTERNAL, mapOf(ElementRegistry.OXYGEN to 1, ElementRegistry.HYDROGEN to 1))

    val BORATE = CompoundBuilder(-1, "borate", TypeRegistry.INTERNAL, mapOf(ElementRegistry.BORON to 1, ElementRegistry.OXYGEN to 3))

    val CARBONATE = CompoundBuilder(-1, "carbonate", TypeRegistry.INTERNAL, mapOf(ElementRegistry.CARBON to 1, ElementRegistry.OXYGEN to 3))

    val NITRATE = CompoundBuilder(-1, "nitrate", TypeRegistry.INTERNAL, mapOf(ElementRegistry.NITROGEN to 1, ElementRegistry.OXYGEN to 3))

    val SILICATE = CompoundBuilder(-1, "silicate", TypeRegistry.INTERNAL, mapOf(ElementRegistry.SILICON to 1, ElementRegistry.OXYGEN to 4))

    val PHOSPHATE = CompoundBuilder(-1, "phosphate", TypeRegistry.INTERNAL, mapOf(ElementRegistry.PHOSPHORUS to 1, ElementRegistry.OXYGEN to 4))

    val SULFATE = CompoundBuilder(-1, "sulfate", TypeRegistry.INTERNAL, mapOf(ElementRegistry.SULFUR to 1, ElementRegistry.OXYGEN to 4))

    val TITANATE = CompoundBuilder(-1, "titanate", TypeRegistry.INTERNAL, mapOf(ElementRegistry.TITANIUM to 1, ElementRegistry.OXYGEN to 4))

    val TUNGSTATE = CompoundBuilder(-1, "tungstate", TypeRegistry.INTERNAL, mapOf(ElementRegistry.TUNGSTEN to 1, ElementRegistry.OXYGEN to 4))

    //10 ~ 19: Hydrogen
    val HYDROGEN = SimpleBuilder(ElementRegistry.HYDROGEN, 2)

    val WATER = CompoundBuilder(11, "water", TypeRegistry.INTERNAL, mapOf(ElementRegistry.HYDROGEN to 2, ElementRegistry.OXYGEN to 1)).apply {
        color = RagiColorManager.BLUE
        tempBoil = 100
        tempMelt = 0
    }

    val SNOW = CompoundBuilder(12, "snow", TypeRegistry.INGOT, mapOf(WATER to 1)).apply {
        color = RagiColorManager.WHITE
    }

    val ICE = CompoundBuilder(13, "ice", TypeRegistry.INGOT, mapOf(WATER to 1)).apply {
        color = RagiColorManager.mixColor(RagiColorManager.AQUA, RagiColorManager.WHITE)
        hasOre = true
    }

    val DEUTERIUM = SimpleBuilder(ElementRegistry.DEUTERIUM, 2).apply {
        rarity = EnumRarity.EPIC
    }

    val TRITIUM = SimpleBuilder(ElementRegistry.TRITIUM, 2).apply {
        rarity = EnumRarity.EPIC
    }

    //20 ~ 29: Helium, Neon, Argon
    val HELIUM = SimpleBuilder(ElementRegistry.HELIUM, 1).apply {
        rarity = EnumRarity.RARE
    }

    val NEON = SimpleBuilder(ElementRegistry.NEON, 1).apply {
        rarity = EnumRarity.RARE
    } //21

    val ARGON = SimpleBuilder(ElementRegistry.ARGON, 1).apply {
        rarity = EnumRarity.RARE
    } //22

    //30 ~ 39: Lithium
    val LITHIUM = SimpleBuilder(ElementRegistry.LITHIUM, 1)

    //40 ~ 49: Beryllium
    val BERYLLIUM = SimpleBuilder(ElementRegistry.BERYLLIUM, 1)

    val EMERALD = CrystalBuilder(41, "emerald", mapOf(ElementRegistry.BERYLLIUM to 3, ElementRegistry.ALUMINIUM to 2, ElementRegistry.SILICON to 6, ElementRegistry.OXYGEN to 18), "hexagonal").apply {
        color = RagiColorManager.GREEN
        hasOre = true
    }

    //50 ~ 59: Boron
    val BORON = SimpleBuilder(ElementRegistry.BORON, 1)

    val BORAX = CompoundBuilder(51, "borax", TypeRegistry.DUST, mapOf(ElementRegistry.SODIUM to 2, ElementRegistry.HYDROGEN to 20, ElementRegistry.BORON to 4, ElementRegistry.OXYGEN to 17)).apply {
        hasOre = true
    }

    val BORON_OXIDE = CompoundBuilder(52, "boron_oxide", TypeRegistry.DUST, mapOf(ElementRegistry.BORON to 2, ElementRegistry.OXYGEN to 3))

    //60 ~ 69: Carbon
    val CARBON = SimpleBuilder(ElementRegistry.CARBON, 1)

    val CARBON_DIOXIDE = CompoundBuilder(61, "carbon_dioxide", TypeRegistry.GAS, mapOf(ElementRegistry.CARBON to 1, ElementRegistry.OXYGEN to 2))

    val COAL = CrystalBuilder(62, "coal", mapOf(ElementRegistry.CARBON to 1), "coal").apply {
        burnTime = 200 * 8
        hasOre = true
    }

    val CHARCOAL = CrystalBuilder(63, "charcoal", mapOf(ElementRegistry.CARBON to 1), "coal").apply {
        burnTime = 200 * 8
    }

    val COKE = CrystalBuilder(64, "coke", mapOf(ElementRegistry.CARBON to 1), "coal").apply {
        burnTime = 200 * 16
        color = RagiColorManager.DARK_GRAY
    }

    val ANTHRACITE = CrystalBuilder(65, "anthracite", mapOf(ElementRegistry.CARBON to 1), "coal").apply {
        burnTime = 200 * 24
        color = RagiColorManager.mixColor(mapOf(COAL.color to 5, RagiColorManager.DARK_BLUE to 1))
        hasOre = true
    }

    val LIGNITE = CrystalBuilder(66, "lignite", mapOf(ElementRegistry.CARBON to 1), "coal").apply {
        burnTime = 200 * 4
        color = RagiColorManager.mixColor(mapOf(COAL.color to 5, RagiColorManager.DARK_RED to 1))
        hasOre = true
    }

    val PEAT = CrystalBuilder(67, "peat", mapOf(ElementRegistry.CARBON to 1), "coal").apply {
        burnTime = 200 * 2
        color = RagiColorManager.mixColor(mapOf(COAL.color to 5, RagiColorManager.DARK_GREEN to 1, RagiColorManager.DARK_RED to 1))
        hasOre = true
    }

    val DIAMOND = CrystalBuilder(68, "diamond", mapOf(ElementRegistry.CARBON to 1), "diamond").apply {
        color = RagiColorManager.mixColor(RagiColorManager.AQUA, RagiColorManager.WHITE)
        hasOre = true
        rarity = EnumRarity.RARE
    }

    //70 ~ 79: Nitrogen
    val NITROGEN = SimpleBuilder(ElementRegistry.NITROGEN, 2)

    val NITRIC_ACID = CompoundBuilder(71, "nitric_acid", TypeRegistry.LIQUID, mapOf(ElementRegistry.HYDROGEN to 1, NITRATE to 1))

    val NITER = CrystalBuilder(72, "niter", mapOf(ElementRegistry.POTASSIUM to 1, NITRATE to 1), "orthorhombic").apply {
        color = RagiColorManager.WHITE
        hasOre = true
        oredictAlt = "Saltpeter"
    }

    //80 ~ 89: Oxygen
    val OXYGEN = SimpleBuilder(ElementRegistry.OXYGEN, 2)

    //90 ~ 99: Fluorite
    val FLUORINE = SimpleBuilder(ElementRegistry.FLUORINE, 2)

    val FLUORITE = CrystalBuilder(91, "fluorite", mapOf(ElementRegistry.CALCIUM to 1, ElementRegistry.FLUORINE to 2), "diamond").apply {
        color = RagiColorManager.mixColor(RagiColorManager.GREEN, RagiColorManager.AQUA)
        hasOre = true
    }

    val HYDROGEN_FLUORIDE = CompoundBuilder(92, "hydrogen_fluoride", TypeRegistry.LIQUID, mapOf(ElementRegistry.HYDROGEN to 1, ElementRegistry.FLUORINE to 1))

    //100 ~ 109:

    //110 ~ 119: Sodium
    val SODIUM = SimpleBuilder(ElementRegistry.SODIUM, 1)

    val SODIUM_HYDROXIDE = CompoundBuilder(111, "sodium_hydroxide", TypeRegistry.DUST, mapOf(ElementRegistry.SODIUM to 1, HYDROXIDE to 1))

    val SODIUM_BICARBONATE = CompoundBuilder(112, "sodium_bicarbonate", TypeRegistry.DUST, mapOf(ElementRegistry.SODIUM to 1, ElementRegistry.HYDROGEN to 1, CARBONATE to 1))

    val SODIUM_CARBONATE = CompoundBuilder(113, "sodium_carbonate", TypeRegistry.DUST, mapOf(ElementRegistry.SODIUM to 2, CARBONATE to 1))

    val SODIUM_SULFIDE = CompoundBuilder(114, "sodium_sulfide", TypeRegistry.DUST, mapOf(ElementRegistry.SODIUM to 2, ElementRegistry.SULFUR to 1))

    val SODIUM_BISULFATE = CompoundBuilder(115, "sodium_bisulfate", TypeRegistry.DUST, mapOf(ElementRegistry.SODIUM to 1, ElementRegistry.HYDROGEN to 1, SULFATE to 1))

    val SODIUM_SULFATE = CompoundBuilder(116, "sodium_sulfate", TypeRegistry.DUST, mapOf(ElementRegistry.SODIUM to 2, SULFATE to 1))

    //120 ~ 129: Magnesium
    val MAGNESIUM = SimpleBuilder(ElementRegistry.MAGNESIUM, 1)

    val MAGNESIUM_CARBONATE = CompoundBuilder(121, "magnesium_carbonate", TypeRegistry.DUST, mapOf(ElementRegistry.MAGNESIUM to 1, CARBONATE to 1)).apply {
        hasOre = true
    }

    //130 ~ 139: Aluminium
    val ALUMINIUM = SimpleBuilder(ElementRegistry.ALUMINIUM, 1).apply {
        oredictAlt = "Aluminum"
    }

    val ALUMINIUM_OXIDE = CompoundBuilder(131, "aluminium_oxide", TypeRegistry.DUST, mapOf(ElementRegistry.ALUMINIUM to 2, ElementRegistry.OXYGEN to 3))

    val BAUXITE = CompoundBuilder(132, "bauxite", TypeRegistry.DUST, mapOf(ALUMINIUM_OXIDE to 1, WATER.addBracket() to 2)).apply {
        color = RagiColorManager.mixColor(mapOf(RagiColorManager.BLACK to 1, RagiColorManager.DARK_RED to 2, RagiColorManager.GOLD to 1))
        hasOre = true
    }

    val RUBY = CrystalBuilder(133, "ruby", mapOf(ElementRegistry.CHROMIUM to 1, ALUMINIUM_OXIDE to 1), "trigonal").apply {
        color = RagiColorManager.RED
        hasOre = true
    }

    val SAPPHIRE = CrystalBuilder(134, "sapphire", mapOf(ElementRegistry.IRON to 1, ALUMINIUM_OXIDE to 1), "trigonal").apply {
        color = RagiColorManager.BLUE
        hasOre = true
    }

    val ALUMINA_SOLUTION = CompoundBuilder(135, "alumina_solution", TypeRegistry.LIQUID, mapOf(
            ElementRegistry.SODIUM to 1,
            FormulaString("[") to 1,
            ElementRegistry.ALUMINIUM to 1,
            HYDROXIDE.addBracket() to 4,
            FormulaString("]") to 1
    ))

    //140 ~ 149: Silicon
    val SILICON = SimpleBuilder(ElementRegistry.SILICON, 1)

    val SILICON_DIOXIDE = CrystalBuilder(141, "silicon_dioxide", mapOf(ElementRegistry.SILICON to 1, ElementRegistry.OXYGEN to 2), "trigonal")

    val GLASS = CompoundBuilder(142, "glass", TypeRegistry.DUST, mapOf(SILICON_DIOXIDE to 1)).apply {
        color = RagiColorManager.WHITE
    }

    //150 ~ 159: Phosphorus
    val PHOSPHORUS = SimpleBuilder(ElementRegistry.PHOSPHORUS, 1).apply {
        hasOre = true
    }

    //160 ~ 169: Sulfur
    val SULFUR = SimpleBuilder(ElementRegistry.SULFUR, 8).apply {
        hasOre = true
    }

    val SULFURIC_ACID = CompoundBuilder(161, "sulfuric_acid", TypeRegistry.LIQUID, mapOf(ElementRegistry.HYDROGEN to 2, SULFATE to 1)).apply {
        color = RagiColorManager.mixColor(RagiColorManager.GOLD, RagiColorManager.YELLOW)
    }

    //170 ~ 179: Chlorine
    val CHLORINE = SimpleBuilder(ElementRegistry.CHLORINE, 2)

    val HYDROGEN_CHLORIDE = CompoundBuilder(171, "hydrogen_chloride", TypeRegistry.LIQUID, mapOf(ElementRegistry.HYDROGEN to 1, ElementRegistry.CHLORINE to 1))

    val LITHIUM_CHLORIDE = CompoundBuilder(172, "lithium_chloride", TypeRegistry.DUST, mapOf(ElementRegistry.LITHIUM to 1, ElementRegistry.CHLORINE to 1)).apply {
        color = RagiColorManager.GOLD
    }

    val SALT = CompoundBuilder(173, "salt", TypeRegistry.DUST, mapOf(ElementRegistry.SODIUM to 1, ElementRegistry.CHLORINE to 1)).apply {
        color = RagiColorManager.WHITE
        hasOre = true
    }

    val MAGNESIUM_CHLORIDE = CompoundBuilder(174, "magnesium_chloride", TypeRegistry.DUST, mapOf(ElementRegistry.MAGNESIUM to 1, ElementRegistry.CHLORINE to 2)).apply {
        color = RagiColorManager.YELLOW
    }

    val SALTWATER = MixtureBuilder(175, "saltwater", TypeRegistry.LIQUID, listOf(WATER, SALT)).apply {
        color = RagiColorManager.YELLOW
    }

    val BRINE = MixtureBuilder(176, "brine", TypeRegistry.LIQUID, listOf(WATER, MAGNESIUM_CHLORIDE)).apply {
        color = RagiColorManager.GOLD
    }

    //180 ~ 189: Stone
    val STONE = CompoundBuilder(180, "stone", TypeRegistry.STONE, mapOf(SILICON_DIOXIDE to 1)).apply {
        color = RagiColorManager.GRAY
    }

    val LAVA = CompoundBuilder(181, "lava", TypeRegistry.INTERNAL, mapOf(SILICON_DIOXIDE to 1)).apply {
        color = RagiColorManager.mixColor(RagiColorManager.DARK_RED, RagiColorManager.GOLD)
        tempMelt = FluidRegistry.LAVA.temperature
    }

    val OBSIDIAN = CompoundBuilder(182, "obsidian", TypeRegistry.DUST, mapOf(SILICON_DIOXIDE to 1)).apply {
        color = RagiColorManager.mixColor(mapOf(RagiColorManager.BLACK to 2, RagiColorManager.BLUE to 1, RagiColorManager.RED to 1))
    }

    val NETHERRACK = CompoundBuilder(183, "netherrack", TypeRegistry.DUST, mapOf(SILICON_DIOXIDE.addBracket() to 1, ElementRegistry.SULFUR to 1, ElementRegistry.PHOSPHORUS to 1)).apply {
        color = RagiColorManager.DARK_RED
    }

    val SOUL_SAND = CompoundBuilder(184, "soul_sand", TypeRegistry.DUST, mapOf(SILICON_DIOXIDE to 1)).apply {
        color = RagiColorManager.mixColor(mapOf(RagiColorManager.BLACK to 5, RagiColorManager.GOLD to 1))
    }

    val END_STONE = CompoundBuilder(185, "end_stone", TypeRegistry.DUST, mapOf(STONE.addBracket() to 1, ElementRegistry.HELIUM to 1, ElementRegistry.TUNGSTEN to 1)).apply {
        color = RagiColorManager.mixColor(mapOf(RagiColorManager.YELLOW to 1, RagiColorManager.WHITE to 3))
    }

    //190 ~ 199: Potassium
    val POTASSIUM = SimpleBuilder(ElementRegistry.POTASSIUM, 1)

    //200 ~ 209: Calcium
    val CALCIUM = SimpleBuilder(ElementRegistry.CALCIUM, 1)

    val CALCIUM_HYDROXIDE = CompoundBuilder(201, "calcium_hydroxide", TypeRegistry.DUST, mapOf(ElementRegistry.CALCIUM to 1, HYDROXIDE.addBracket() to 2))

    val CALCIUM_CARBONATE = CompoundBuilder(202, "calcium_carbonate", TypeRegistry.DUST, mapOf(ElementRegistry.CALCIUM to 1, CARBONATE to 1))

    val CALCIUM_OXIDE = CompoundBuilder(203, "calcium_oxide", TypeRegistry.DUST, mapOf(ElementRegistry.CALCIUM to 1, ElementRegistry.OXYGEN to 1))

    val APATITE = CrystalBuilder(204, "apatite", mapOf(ElementRegistry.CALCIUM to 5, PHOSPHATE.addBracket() to 3, HYDROXIDE to 1), "hexagonal").apply {
        color = RagiColorManager.mixColor(RagiColorManager.YELLOW, RagiColorManager.WHITE)
        hasOre = true
    }

    val GYPSUM = CrystalBuilder(205, "gypsum", mapOf(ElementRegistry.CALCIUM to 1, SULFATE to 1), "monoclinic")

    val CALCIUM_CHLORIDE = CompoundBuilder(206, "calcium_chloride", TypeRegistry.DUST, mapOf(ElementRegistry.CALCIUM to 1, ElementRegistry.CHLORINE to 2))

    //220 ~ 229: Titanium
    val TITANIUM = SimpleBuilder(ElementRegistry.TITANIUM, 1).apply {
        rarity = EnumRarity.RARE
    }

    val RUTILE = CrystalBuilder(221, "rutile", mapOf(ElementRegistry.TITANIUM to 1, ElementRegistry.OXYGEN to 2), "tetragonal").apply {
        hasOre = true
    }

    val TITANIUM_TETRACHLORIDE = CompoundBuilder(222, "titanium_tetrachloride", TypeRegistry.LIQUID, mapOf(ElementRegistry.TITANIUM to 1, ElementRegistry.CHLORINE to 4))

    //240 ~ 249: Chromium
    val CHROMIUM = SimpleBuilder(ElementRegistry.CHROMIUM, 1).apply {
        oredictAlt = "Chrome"
    }

    val CHROMITE = CompoundBuilder(241, "chromite", TypeRegistry.DUST, mapOf(ElementRegistry.IRON to 1, ElementRegistry.CHROMIUM to 2, ElementRegistry.OXYGEN to 4)).apply {
        hasOre = true
    }

    val STAINLESS_STEEL = AlloyBuilder(242, "stainless_steel", mapOf(ElementRegistry.IRON to 6, ElementRegistry.CHROMIUM to 1, ElementRegistry.MANGANESE to 1, ElementRegistry.NICKEL to 1)).apply {
        color = RagiColorManager.mixColor(RagiColorManager.GRAY, RagiColorManager.WHITE)
        rarity = EnumRarity.RARE
    }

    //250 ~ 259: Manganese
    val MANGANESE = SimpleBuilder(ElementRegistry.MANGANESE, 1)

    val RHODOCHROSITE = CompoundBuilder(251, "rhodochrosite", TypeRegistry.DUST, mapOf(ElementRegistry.MANGANESE to 1, CARBONATE to 1)).apply {
        hasOre = true
    }

    val PYROLUSITE = CompoundBuilder(252, "pyrolusite", TypeRegistry.DUST, mapOf(ElementRegistry.MANGANESE to 1, ElementRegistry.OXYGEN to 2)).apply {
        hasOre = true
    }

    //260 ~ 269: Iron
    val IRON = SimpleBuilder(ElementRegistry.IRON, 1)

    val HEMATITE = CompoundBuilder(261, "hematite", TypeRegistry.DUST, mapOf(ElementRegistry.IRON to 2, ElementRegistry.OXYGEN to 3)).apply {
        color = RagiColorManager.mixColor(mapOf(RagiColorManager.BLACK to 1, RagiColorManager.DARK_RED to 2, RagiColorManager.GOLD to 1))
        hasOre = true
    }

    val MAGNETITE = CompoundBuilder(262, "magnetite", TypeRegistry.DUST, mapOf(ElementRegistry.IRON to 3, ElementRegistry.OXYGEN to 4)).apply {
        color = RagiColorManager.mixColor(RagiColorManager.BLACK, RagiColorManager.DARK_GRAY)
        hasOre = true
    }

    val PYRITE = CompoundBuilder(263, "pyrite", TypeRegistry.DUST, mapOf(ElementRegistry.IRON to 1, ElementRegistry.SULFUR to 2)).apply {
        hasOre = true
    }

    val STEEL = AlloyBuilder(264, "steel", mapOf(ElementRegistry.IRON to 1, ElementRegistry.CARBON to 1)).apply {
        rarity = EnumRarity.UNCOMMON
    }

    val TOOL_STEEL = AlloyBuilder(265, "tool_steel", mapOf(ElementRegistry.IRON to 6, ElementRegistry.CHROMIUM to 1, ElementRegistry.MANGANESE to 1, ElementRegistry.MOLYBDENUM to 1)).apply {
        rarity = EnumRarity.RARE
    }

    //270 ~ 279: Cobalt
    val COBALT = SimpleBuilder(ElementRegistry.COBALT, 1)

    val COBALTITE = CompoundBuilder(271, "cobaltite", TypeRegistry.DUST, mapOf(ElementRegistry.COBALT to 1, ElementRegistry.ARSENIC to 1, ElementRegistry.SULFUR to 1)).apply {
        hasOre = true
    }

    //280 ~ 289: Nickel
    val NICKEL = SimpleBuilder(ElementRegistry.NICKEL, 1)

    val NICKELINE = CompoundBuilder(281, "nickeline", TypeRegistry.DUST, mapOf(ElementRegistry.NICKEL to 1, ElementRegistry.ARSENIC to 1)).apply {
        hasOre = true
    }

    val INVAR = AlloyBuilder(282, "invar", mapOf(ElementRegistry.IRON to 2, ElementRegistry.NICKEL to 1))

    val CONSTANTAN = AlloyBuilder(283, "constantan", mapOf(ElementRegistry.NICKEL to 1, ElementRegistry.COPPER to 1))

    //290 ~ 299: Copper
    val COPPER = SimpleBuilder(ElementRegistry.COPPER, 1).apply {
        hasOre = true
    }

    val CHALCOCITE = CompoundBuilder(291, "chalcocite", TypeRegistry.DUST, mapOf(ElementRegistry.COPPER to 2, ElementRegistry.SULFUR to 1)).apply {
        color = RagiColorManager.mixColor(RagiColorManager.BLACK, RagiColorManager.DARK_GRAY)
        hasOre = true
    }

    val CHALCOPYRITE = CompoundBuilder(293, "chalcopyrite", TypeRegistry.DUST, mapOf(ElementRegistry.COPPER to 1, ElementRegistry.IRON to 1, ElementRegistry.SULFUR to 2)).apply {
        hasOre = true
    }

    //300 ~ 309: Zinc
    val ZINC = SimpleBuilder(ElementRegistry.ZINC, 1)

    val SPHALERITE = CompoundBuilder(301, "sphalerite", TypeRegistry.DUST, mapOf(ElementRegistry.ZINC to 1, ElementRegistry.SULFUR to 1)).apply {
        hasOre = true
    }

    val BRASS = AlloyBuilder(302, "brass", mapOf(ElementRegistry.COPPER to 3, ElementRegistry.ZINC to 1)).apply {
        color = RagiColorManager.GOLD
    }

    //310 ~ 319: Gallium, Arsenic
    val GALLIUM = SimpleBuilder(ElementRegistry.GALLIUM, 1)

    val ARSENIC = SimpleBuilder(ElementRegistry.ARSENIC, 1) //311

}