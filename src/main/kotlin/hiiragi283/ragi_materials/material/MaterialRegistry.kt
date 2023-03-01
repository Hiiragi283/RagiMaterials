package hiiragi283.ragi_materials.material

import hiiragi283.ragi_materials.material.element.ElementRegistry
import hiiragi283.ragi_materials.material.type.TypeRegistry
import hiiragi283.ragi_materials.render.color.RagiColor
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

    val WATER = CompoundBuilder(11, "water", TypeRegistry.INTERNAL, mapOf(ElementRegistry.HYDROGEN to 2, ElementRegistry.OXYGEN to 1)).apply{
        color = RagiColor.BLUE
        tempBoil = 100
        tempMelt = 0
    }

    val SNOW = CompoundBuilder(12, "snow", TypeRegistry.INGOT, mapOf(WATER to 1)).apply{
        color = RagiColor.WHITE
    }

    val ICE = CompoundBuilder(13, "ice", TypeRegistry.INGOT, mapOf(WATER to 1)).apply{
        color = RagiColor.mixColor(RagiColor.AQUA, RagiColor.WHITE)
        hasOre = true
    }

    val DEUTERIUM = SimpleBuilder(ElementRegistry.DEUTERIUM, 2)

    val TRITIUM = SimpleBuilder(ElementRegistry.TRITIUM, 2)

    //20 ~ 29: Helium, Neon, Argon
    val HELIUM = SimpleBuilder(ElementRegistry.HELIUM, 1)

    val NEON = SimpleBuilder(ElementRegistry.NEON, 1)

    val ARGON = SimpleBuilder(ElementRegistry.ARGON, 1)

    //30 ~ 39: Lithium
    val LITHIUM = SimpleBuilder(ElementRegistry.LITHIUM, 1)

    //40 ~ 49: Beryllium
    val BERYLLIUM = SimpleBuilder(ElementRegistry.BERYLLIUM, 1)

    //50 ~ 59: Boron
    val BORON = SimpleBuilder(ElementRegistry.BORON, 1)

    //60 ~ 69: Carbon
    val CARBON = SimpleBuilder(ElementRegistry.CARBON, 1)

    val CARBON_DIOXIDE = CompoundBuilder(61, "carbon_dioxide", TypeRegistry.GAS, mapOf(ElementRegistry.CARBON to 1, ElementRegistry.OXYGEN to 2))

    val COAL = CrystalBuilder(62, "coal", mapOf(ElementRegistry.CARBON to 1), "coal").apply{
        burnTime = 200 * 8
        hasOre = true
    }

    val CHARCOAL = CrystalBuilder(63, "charcoal", mapOf(ElementRegistry.CARBON to 1), "coal").apply{
        burnTime = 200 * 8
    }

    val COKE = CrystalBuilder(64, "coke", mapOf(ElementRegistry.CARBON to 1), "coal").apply{
        burnTime = 200 * 16
        color = RagiColor.DARK_GRAY
    }

    val ANTHRACITE = CrystalBuilder(65, "anthracite", mapOf(ElementRegistry.CARBON to 1), "coal").apply{
        burnTime = 200 * 24
        color = RagiColor.mixColor(mapOf(COAL.color!! to 5, RagiColor.DARK_BLUE to 1))
        hasOre = true
    }

    val LIGNITE = CrystalBuilder(66, "lignite", mapOf(ElementRegistry.CARBON to 1), "coal").apply{
        burnTime = 200 * 4
        color = RagiColor.mixColor(mapOf(COAL.color!! to 5, RagiColor.DARK_RED to 1))
        hasOre = true
    }

    val PEAT = CrystalBuilder(67, "peat", mapOf(ElementRegistry.CARBON to 1), "coal").apply{
        burnTime = 200 * 2
        color = RagiColor.mixColor(mapOf(COAL.color!! to 5, RagiColor.DARK_GREEN to 1, RagiColor.DARK_RED to 1))
        hasOre = true
    }

    val DIAMOND = CrystalBuilder(68, "diamond", mapOf(ElementRegistry.CARBON to 1), "diamond").apply{
        color = RagiColor.mixColor(RagiColor.AQUA, RagiColor.WHITE)
        hasOre = true
    }

    //70 ~ 79: Nitrogen
    val NITROGEN = SimpleBuilder(ElementRegistry.NITROGEN, 2)

    val NITRIC_ACID = CompoundBuilder(71, "nitric_acid", TypeRegistry.LIQUID, mapOf(ElementRegistry.HYDROGEN to 1, NITRATE to 1))

    val NITER = CrystalBuilder(72, "niter", mapOf(ElementRegistry.POTASSIUM to 1, NITRATE to 1), "orthorhombic").apply{
        color = RagiColor.WHITE
        hasOre = true
        oredictAlt = "Saltpeter"
    }

    //80 ~ 89: Oxygen
    val OXYGEN = SimpleBuilder(ElementRegistry.OXYGEN, 2)

    //90 ~ 99: Fluorite
    val FLUORINE = SimpleBuilder(ElementRegistry.FLUORINE, 2)

    val FLUORITE = CrystalBuilder(91, "fluorite", mapOf(ElementRegistry.CALCIUM to 1, ElementRegistry.FLUORINE to 2), "diamond").apply{
        color = RagiColor.mixColor(RagiColor.GREEN, RagiColor.AQUA)
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

    //130 ~ 139: Aluminium
    val ALUMINIUM = SimpleBuilder(ElementRegistry.ALUMINIUM, 1).setOreDictAlt("Aluminum").setOre()

    val ALUMINIUM_OXIDE = CompoundBuilder(131, "aluminium_oxide", TypeRegistry.DUST, mapOf(ElementRegistry.ALUMINIUM to 2, ElementRegistry.OXYGEN to 3))

    val BAUXITE = CompoundBuilder(132, "bauxite", TypeRegistry.DUST, mapOf(ALUMINIUM_OXIDE to 1, WATER.addBracket() to 2))

    val RUBY = CrystalBuilder(133, "ruby", mapOf(ElementRegistry.CHROMIUM to 1, ALUMINIUM_OXIDE to 1), "trigonal").apply{
        color = RagiColor.RED
    }

    val SAPPHIRE = CrystalBuilder(134, "sapphire", mapOf(ElementRegistry.IRON to 1, ALUMINIUM_OXIDE to 1), "trigonal").apply{
        color = RagiColor.BLUE
    }

    //140 ~ 149: Silicon
    val SILICON = SimpleBuilder(ElementRegistry.SILICON, 1)

    val SILICON_DIOXIDE = CrystalBuilder(141, "silicon_dioxide", mapOf(ElementRegistry.SILICON to 1, ElementRegistry.OXYGEN to 2), "trigonal")

    val GLASS = CompoundBuilder(142, "glass", TypeRegistry.DUST, mapOf(SILICON_DIOXIDE to 1)).setColor(RagiColor.WHITE)

    //150 ~ 159: Phosphorus
    val PHOSPHORUS = SimpleBuilder(ElementRegistry.PHOSPHORUS, 1).setOre()

    val APATITE = CrystalBuilder(151, "apatite", mapOf(ElementRegistry.CALCIUM to 5, PHOSPHATE.addBracket() to 3, HYDROXIDE to 1), "hexagonal").apply{
        color = RagiColor.mixColor(RagiColor.YELLOW, RagiColor.WHITE)
    }

    //160 ~ 169: Sulfur
    val SULFUR = SimpleBuilder(ElementRegistry.SULFUR, 8).setOre()

    val SULFURIC_ACID = CompoundBuilder(161, "sulfuric_acid", TypeRegistry.LIQUID, mapOf(ElementRegistry.HYDROGEN to 2, SULFATE to 1))

    //170 ~ 179: Chroline
    val CHLORINE = SimpleBuilder(ElementRegistry.CHLORINE, 2)

    val HYDROGEN_CHLORIDE = CompoundBuilder(171, "hydrogen_chloride", TypeRegistry.LIQUID, mapOf(ElementRegistry.HYDROGEN to 1, ElementRegistry.CHLORINE to 1))

    val LIHTIUM_CHLORIDE = CompoundBuilder(172, "lithium_chloride", TypeRegistry.DUST, mapOf(ElementRegistry.LITHIUM to 1, ElementRegistry.CHLORINE to 1)).apply{
        color = RagiColor.WHITE
    }

    val SALT = CompoundBuilder(173, "salt", TypeRegistry.DUST, mapOf(ElementRegistry.SODIUM to 1, ElementRegistry.CHLORINE to 1)).apply{
        color = RagiColor.WHITE
    }

    //180 ~ 189: Stone
    val STONE = CompoundBuilder(180, "stone", TypeRegistry.STONE, mapOf(SILICON_DIOXIDE to 1)).apply{
        color = RagiColor.GRAY
    }

    val LAVA = CompoundBuilder(181, "lava", TypeRegistry.INTERNAL, mapOf(SILICON_DIOXIDE to 1)).apply{
        color = RagiColor.mixColor(RagiColor.DARK_RED, RagiColor.GOLD)
        tempMelt = FluidRegistry.LAVA.temperature
    }

    val OBSIDIAN = CompoundBuilder(182, "obsidian", TypeRegistry.DUST, mapOf(SILICON_DIOXIDE to 1)).apply{
        color = RagiColor.mixColor(mapOf(RagiColor.BLACK to 2, RagiColor.BLUE to 1, RagiColor.RED to 1))
    }

    val NETHERRACK = CompoundBuilder(183, "netherrack", TypeRegistry.DUST, mapOf(SILICON_DIOXIDE.addBracket() to 1, ElementRegistry.SULFUR to 1, ElementRegistry.PHOSPHORUS to 1)).apply{
        color = RagiColor.DARK_RED
    }

    val SOUL_SAND = CompoundBuilder(184, "soul_sand", TypeRegistry.DUST, mapOf(SILICON_DIOXIDE to 1)).apply{
        color = RagiColor.mixColor(mapOf(RagiColor.BLACK to 5, RagiColor.GOLD to 1))
    }

    val END_STONE = CompoundBuilder(185, "end_stone", TypeRegistry.DUST, mapOf(STONE.addBracket() to 1, ElementRegistry.HELIUM to 1, ElementRegistry.TUNGSTEN to 1)).apply{
        color = RagiColor.mixColor(mapOf(RagiColor.YELLOW to 1, RagiColor.WHITE to 3))
    }

    //190 ~ 199: Potassium
    //val POTASSIUM = SimpleBuilder(ElementRegistry.POTASSIUM, 1)

    //200 ~ 209: Calcium
    //val CALCIUM = SimpleBuilder(ElementRegistry.CALCIUM, 1)

    //val CALCIUM_HYDROXIDE = CompoundBuilder(201, "calcium_hydroxide", TypeRegistry.DUST, mapOf(ElementRegistry.CALCIUM to 1, HYDROXIDE.addBracket() to 2))

}