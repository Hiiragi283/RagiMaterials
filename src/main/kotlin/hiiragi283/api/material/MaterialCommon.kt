package hiiragi283.api.material

import hiiragi283.core.config.RMConfig
import hiiragi283.core.util.HiiragiColor
import hiiragi283.core.util.isAprilFools
import net.minecraftforge.fluids.FluidRegistry

object MaterialCommon {

    @JvmField
    val HYDROXIDE = compoundOf("hydroxide", -1, mapOf(MaterialElements.OXYGEN to 1, MaterialElements.HYDROGEN to 1))

    @JvmField
    val CARBONATE = compoundOf("carbonate", -1, mapOf(MaterialElements.CARBON to 1, MaterialElements.OXYGEN to 3))

    @JvmField
    val NITRATE = compoundOf("nitrate", -1, mapOf(MaterialElements.NITROGEN to 1, MaterialElements.OXYGEN to 3))

    @JvmField
    val SILICATE = compoundOf("silicate", -1, mapOf(MaterialElements.SILICON to 1, MaterialElements.OXYGEN to 2))

    @JvmField
    val PHOSPHATE = compoundOf("phosphate", -1, mapOf(MaterialElements.PHOSPHORUS to 1, MaterialElements.OXYGEN to 3))

    @JvmField
    val SULFATE = compoundOf("sulfate", -1, mapOf(MaterialElements.SULFUR to 1, MaterialElements.OXYGEN to 4))

    //    Hydrogen    //

    @JvmField
    val WOOD =
        mixtureOf("wood", 10100, listOf(MaterialElements.CARBON, MaterialElements.HYDROGEN, MaterialElements.OXYGEN)) {
            color =
                HiiragiColor.mixColor(HiiragiColor.DARK_GRAY to 2, HiiragiColor.RED to 1, HiiragiColor.YELLOW to 1).rgb
            validShapes.addAll(MaterialType.WOOD)
        }

    @JvmField
    val WATER = compoundOf("water", 10101, mapOf(MaterialElements.HYDROGEN to 2, MaterialElements.OXYGEN to 1)) {
        color = HiiragiColor.BLUE.rgb
        tempBoil = 373
        tempMelt = 273
        if (isAprilFools()) translationKey = "material.dhmo"
    }

    //    Beryllium    //

    @JvmField
    val AQUAMARINE = compoundOf(
        "aquamarine", 10401, mapOf(
            MaterialElements.BERYLLIUM to 3,
            MaterialElements.ALUMINIUM to 2,
            MaterialElements.SILICON to 6,
            MaterialElements.OXYGEN to 16
        )
    ) {
        color = HiiragiColor.AQUA.rgb
        crystalType = CrystalType.EMERALD
        validShapes.addAll(MaterialType.GEM_9xADVANCED)
    }

    @JvmField
    val EMERALD = compoundOf(
        "emerald", 10400, mapOf(
            MaterialElements.BERYLLIUM to 3,
            MaterialElements.ALUMINIUM to 2,
            MaterialElements.SILICON to 6,
            MaterialElements.OXYGEN to 16
        )
    ) {
        color = HiiragiColor.GREEN.rgb
        crystalType = CrystalType.EMERALD
        validShapes.addAll(MaterialType.GEM_9xADVANCED)
        if (RMConfig.MATERIAL.disableVanillaParts) {
            validShapes.remove("block")
            validShapes.remove("gem")
        }

    }

    //    Carbon    //

    @JvmField
    val COAL = compoundOf("coal", 10600, mapOf(MaterialElements.CARBON to 1)) {
        crystalType = CrystalType.COAL
        validShapes.addAll(MaterialType.GEM_9x)
        if (RMConfig.MATERIAL.disableVanillaParts) {
            validShapes.remove("block")
            validShapes.remove("gem")
        }
    }

    @JvmField
    val CHARCOAL = compoundOf("charcoal", 10601, mapOf(MaterialElements.CARBON to 1)) {
        crystalType = CrystalType.COAL
        validShapes.addAll(MaterialType.GEM_9x)
        if (RMConfig.MATERIAL.disableVanillaParts) {
            //validShapes.remove("block")
            validShapes.remove("gem")
        }
    }

    @JvmField
    val COKE = compoundOf("coke", 10602, mapOf(MaterialElements.CARBON to 1)) {
        color = HiiragiColor.DARK_GRAY.rgb
        crystalType = CrystalType.COAL
        validShapes.addAll(MaterialType.GEM_9x)
    }

    @JvmField
    val DIAMOND = compoundOf("diamond", 10603, mapOf(MaterialElements.CARBON to 1)) {
        color = HiiragiColor.AQUA.rgb
        crystalType = CrystalType.DIAMOND
        validShapes.addAll(MaterialType.GEM_9xADVANCED)
        if (RMConfig.MATERIAL.disableVanillaParts) {
            validShapes.remove("block")
            validShapes.remove("gem")
        }
    }

    @JvmField
    val SUGAR = compoundOf(
        "sugar",
        10604,
        mapOf(MaterialElements.CARBON to 6, MaterialElements.HYDROGEN to 12, MaterialElements.OXYGEN to 6)
    ) {
        color = HiiragiColor.WHITE.rgb
        validShapes.addAll(MaterialType.SOLID)
        if (RMConfig.MATERIAL.disableVanillaParts) {
            validShapes.remove("dust")
        }
    }

    @JvmField
    val AMBER = compoundOf("amber", 10605, mapOf(WOOD to 1)) {
        color = HiiragiColor.GOLD.rgb
        crystalType = CrystalType.AMORPHOUS
        validShapes.addAll(MaterialType.GEM_4x)
    }

    @JvmField
    val ASH =
        mixtureOf("ash", 10606, listOf(MaterialElements.CARBON, MaterialElements.SODIUM, MaterialElements.POTASSIUM)) {
            color = HiiragiColor.GRAY.rgb
            validShapes.addAll(MaterialType.SOLID)
        }

    @JvmField
    val RUBBER = polymerOf("rubber", 10607, mapOf(MaterialElements.CARBON to 5, MaterialElements.HYDROGEN to 8)) {
        color = HiiragiColor.mixColor(HiiragiColor.BLACK, HiiragiColor.DARK_GRAY).rgb
        formula = "(CC(=C)C=C)n"
        validShapes.addAll(MaterialType.SOLID)
        validShapes.add("block")
        validShapes.add("ingot")
        validShapes.add("plate")
    }

    @JvmField
    val PLASTIC = polymerOf("plastic", 10608, mapOf(MaterialElements.CARBON to 2, MaterialElements.HYDROGEN to 4)) {
        color = HiiragiColor.mixColor(HiiragiColor.GRAY, HiiragiColor.WHITE).rgb
        oreDictAlt = mutableListOf("polyethylene")
        validShapes.addAll(MaterialType.SOLID)
        validShapes.add("block")
        validShapes.add("ingot")
        validShapes.add("plate")
    }

    //    Nitrogen    //

    @JvmField
    val NITER = compoundOf("niter", 10700, mapOf(MaterialElements.POTASSIUM to 1, NITRATE to 1)) {
        color = HiiragiColor.WHITE.rgb
        oreDictAlt = mutableListOf("saltpeter")
        validShapes.addAll(MaterialType.GEM_9x)
    }


    @JvmField
    val NITRIC_ACID = compoundOf("nitric_acid", 10701, mapOf(MaterialElements.HYDROGEN to 1, NITRATE to 1)) {
        tempBoil = WATER.tempBoil
        tempMelt = WATER.tempMelt
    }

    @JvmField
    val GUNPOWDER = compoundOf(
        "gunpowder",
        10702,
        mapOf(NITER.addBracket() to 1, MaterialElements.CARBON to 1, MaterialElements.SULFUR to 1)
    ) {
        color = HiiragiColor.DARK_GRAY.rgb
        validShapes.addAll(MaterialType.SOLID)
        if (RMConfig.MATERIAL.disableVanillaParts) {
            validShapes.remove("dust")
        }
    }

    //    Fluorine    //

    @JvmField
    val CRYOLITE = compoundOf(
        "cryolite",
        10900,
        mapOf(MaterialElements.SODIUM to 3, MaterialElements.ALUMINIUM to 1, MaterialElements.FLUORINE to 6)
    ) {
        color = HiiragiColor.WHITE.rgb
        crystalType = CrystalType.CUBIC
        validShapes.addAll(MaterialType.GEM_9x)
    }

    @JvmField
    val FLUORITE = compoundOf("fluorite", 10901, mapOf(MaterialElements.CALCIUM to 1, MaterialElements.FLUORINE to 2)) {
        color = HiiragiColor.mixColor(HiiragiColor.GREEN, HiiragiColor.AQUA).rgb
        crystalType = CrystalType.CUBIC
        validShapes.addAll(MaterialType.GEM_9x)
    }

    @JvmField
    val HYDROGEN_FLUORIDE =
        compoundOf("hydrogen_fluoride", 10902, mapOf(MaterialElements.HYDROGEN to 1, MaterialElements.FLUORINE to 1)) {
            tempBoil = WATER.tempBoil
            tempMelt = WATER.tempMelt
        }

    //    Sodium    //

    @JvmField
    val SALT = compoundOf("salt", 11100, mapOf(MaterialElements.SODIUM to 1, MaterialElements.CHLORINE to 1)) {
        color = HiiragiColor.WHITE.rgb
        crystalType = CrystalType.CUBIC
        validShapes.addAll(MaterialType.GEM_4x)
    }

    //    Magnesium    //

    /*@JvmField
    val OLIVINE = compoundOf("olivine", 11200, mapOf(MaterialElements.MAGNESIUM to 2, SILICATE to 1)) {
        color = HiiragiColor.GREEN.rgb
        crystalType = CrystalType.EMERALD
        validShapes.addAll(MaterialType.GEM_9xADVANCED)
    }*/

    @JvmField
    val PERIDOT = compoundOf("peridot", 11200, mapOf(MaterialElements.MAGNESIUM to 2, SILICATE to 1)) {
        color = HiiragiColor.GREEN.rgb
        crystalType = CrystalType.EMERALD
        oreDictAlt = mutableListOf("olivine")
        validShapes.addAll(MaterialType.GEM_9xADVANCED)
    }

    //    Aluminium    //

    @JvmField
    val ALUMINA = compoundOf("alumina", 11300, mapOf(MaterialElements.ALUMINIUM to 2, MaterialElements.OXYGEN to 3)) {
        color = HiiragiColor.WHITE.rgb
        validShapes.addAll(MaterialType.SOLID)
    }

    @JvmField
    val BAUXITE = hydrateOf("bauxite", 11301, ALUMINA, 2) {
        color = HiiragiColor.mixColor(HiiragiColor.BLACK to 1, HiiragiColor.DARK_RED to 2, HiiragiColor.GOLD to 1).rgb
        validShapes.addAll(MaterialType.SOLID)
    }

    @JvmField
    val RUBY = compoundOf("ruby", 11302, mapOf(ALUMINA to 1)) {
        color = HiiragiColor.RED.rgb
        crystalType = CrystalType.RUBY
        validShapes.addAll(MaterialType.GEM_9xADVANCED)
    }

    @JvmField
    val SAPPHIRE = compoundOf("sapphire", 11303, mapOf(ALUMINA to 1)) {
        color = HiiragiColor.BLUE.rgb
        crystalType = CrystalType.RUBY
        validShapes.addAll(MaterialType.GEM_9xADVANCED)
    }

    //    Silicon    //

    @JvmField
    val CLAY = mixtureOf("clay", 11400, listOf(ALUMINA, SILICATE)) {
        color = HiiragiColor.mixColor( HiiragiColor.AQUA to 1, HiiragiColor.WHITE to 2).rgb
        validShapes.addAll(MaterialType.SOLID)
    }

    @JvmField
    val END_STONE = compoundOf("end_stone", 11401, mapOf(SILICATE to 1)) {
        color = HiiragiColor.mixColor(HiiragiColor.YELLOW to 1, HiiragiColor.WHITE to 3).rgb
        validShapes.addAll(MaterialType.SOLID)
    }

    @JvmField
    val GLASS = compoundOf("glass", 11402, mapOf(SILICATE to 1)) {
        color = HiiragiColor.WHITE.rgb
        validShapes.addAll(MaterialType.SOLID)
    }

    @JvmField
    val LAVA = compoundOf("lava", 11403, mapOf(SILICATE to 1)) {
        color = HiiragiColor.mixColor(HiiragiColor.DARK_RED, HiiragiColor.GOLD).rgb
        tempMelt = FluidRegistry.LAVA.temperature
    }

    @JvmField
    val NETHERRACK = compoundOf("netherrack", 11404, mapOf(SILICATE to 1)) {
        color = HiiragiColor.DARK_RED.rgb
        validShapes.addAll(MaterialType.SOLID)
    }

    @JvmField
    val OBSIDIAN = compoundOf("obsidian", 11405, mapOf(SILICATE to 1)) {
        color = HiiragiColor.mixColor(HiiragiColor.BLACK to 2, HiiragiColor.BLUE to 1, HiiragiColor.RED to 1).rgb
        validShapes.addAll(MaterialType.SOLID)
    }

    @JvmField
    val QUARTZ = compoundOf("quartz", 11406, mapOf(SILICATE to 1)) {
        color = HiiragiColor.WHITE.rgb
        crystalType = CrystalType.QUARTZ
        oreDictAlt = mutableListOf("nether_quartz")
        validShapes.addAll(MaterialType.GEM_4xADVANCED)
        if (RMConfig.MATERIAL.disableVanillaParts) {
            validShapes.remove("gem")
        }
    }

    @JvmField
    val SOUL_SAND = compoundOf("soul_sand", 11407, mapOf(SILICATE to 1)) {
        color = HiiragiColor.mixColor(HiiragiColor.BLACK to 5, HiiragiColor.GOLD to 1).rgb
        validShapes.addAll(MaterialType.SOLID)
    }

    @JvmField
    val STONE = compoundOf("stone", 11408, mapOf(SILICATE to 1)) {
        color = HiiragiColor.GRAY.rgb
        validShapes.addAll(MaterialType.SOLID)
        validShapes.add("gear")
        validShapes.add("plate")
        validShapes.add("stick")
    }

    //    Sulfur    //

    val SULFURIC_ACID = compoundOf("sulfuric_acid", 11600, mapOf(MaterialElements.HYDROGEN to 2, SULFATE to 1)) {
        color = HiiragiColor.mixColor(HiiragiColor.GOLD, HiiragiColor.YELLOW).rgb
        tempBoil = WATER.tempBoil
        tempMelt = WATER.tempMelt
    }

    //    Chlorine    //

    @JvmField
    val HYDROGEN_CHLORIDE =
        compoundOf("hydrogen_chloride", 11700, mapOf(MaterialElements.HYDROGEN to 1, MaterialElements.CHLORINE to 1)) {
            tempBoil = WATER.tempBoil
            tempMelt = WATER.tempMelt
        }

    //    Calcium    //

    @JvmField
    val APATITE =
        compoundOf(
            "apatite",
            12000,
            mapOf(MaterialElements.CALCIUM to 5, PHOSPHATE.addBracket() to 3, HYDROXIDE to 1)
        ) {
            color = HiiragiColor.mixColor(HiiragiColor.YELLOW, HiiragiColor.WHITE).rgb
            crystalType = CrystalType.EMERALD
            validShapes.addAll(MaterialType.GEM_9x)
        }

    @JvmField
    val GYPSUM = compoundOf("gypsum", 12001, mapOf(MaterialElements.CALCIUM to 1, SULFATE to 1)) {
        crystalType = CrystalType.CUBIC
        validShapes.addAll(MaterialType.GEM_9x)
    }

    @JvmField
    val LIME = compoundOf("lime", 12002, mapOf(MaterialElements.CALCIUM to 1, CARBONATE to 1)) {
        color = HiiragiColor.WHITE.rgb
        validShapes.addAll(MaterialType.SOLID)
    }

    //    Titanium    //

    @JvmField
    val RUTILE = compoundOf("rutile", 12200, mapOf(MaterialElements.TITANIUM to 1, MaterialElements.OXYGEN to 2)) {
        color = HiiragiColor.YELLOW.rgb
        crystalType = CrystalType.QUARTZ
        validShapes.addAll(MaterialType.GEM_9x)
    }

    //    Chromium    //

    @JvmField
    val STAINLESS_STEEL = alloyOf(
        "stainless_steel", 12400, mapOf(
            MaterialElements.IRON to 6,
            MaterialElements.CHROMIUM to 1,
            MaterialElements.MANGANESE to 1,
            MaterialElements.NICKEL to 1
        )
    )
    {
        color = HiiragiColor.mixColor(HiiragiColor.GRAY, HiiragiColor.WHITE).rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    //    Iron    //

    @JvmField
    val STEEL = mixtureOf("steel", 12600, listOf(MaterialElements.IRON, MaterialElements.CARBON)) {
        color = HiiragiColor.GRAY.rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    //    Nickel    //

    @JvmField
    val CONSTANTAN =
        alloyOf("constantan", 12800, mapOf(MaterialElements.NICKEL to 1, MaterialElements.COPPER to 1)) {
            crystalType = CrystalType.METAL
            validShapes.addAll(MaterialType.METAL_ADVANCED)
        }

    @JvmField
    val INVAR = alloyOf("invar", 12801, mapOf(MaterialElements.NICKEL to 2, MaterialElements.IRON to 1)) {
        color = HiiragiColor.mixColor(
            HiiragiColor.GREEN to 1,
            HiiragiColor.GRAY to 3,
            HiiragiColor.WHITE to 4
        ).rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    //    Copper    //

    @JvmField
    val BRASS = alloyOf("brass", 12900, mapOf(MaterialElements.COPPER to 3, MaterialElements.ZINC to 1)) {
        color = HiiragiColor.GOLD.rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    @JvmField
    val BRONZE = alloyOf("bronze", 12901, mapOf(MaterialElements.COPPER to 3, MaterialElements.TIN to 1)) {
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    //    Silver    //

    val ELECTRUM = alloyOf("electrum", 14700, mapOf(MaterialElements.SILVER to 1, MaterialElements.GOLD to 1)) {
        color = HiiragiColor.mixColor(HiiragiColor.GOLD, HiiragiColor.YELLOW, HiiragiColor.WHITE).rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    //    Mercury    //

    val CINNABAR = compoundOf("cinnabar", 18000, mapOf(MaterialElements.MERCURY to 1, MaterialElements.SULFUR to 1)) {
        color = HiiragiColor.RED.rgb
        crystalType = CrystalType.EMERALD
        validShapes.addAll(MaterialType.GEM_9x)
    }

    fun register(registry: MutableList<HiiragiMaterial>) {
        this::class.java.declaredFields
            .map { it.also { it.isAccessible = true } }
            .map { it.get(this) }
            .filterIsInstance<HiiragiMaterial>()
            .forEach { registry.add(it) }
    }

}