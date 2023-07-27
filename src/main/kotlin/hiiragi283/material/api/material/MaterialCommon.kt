package hiiragi283.material.api.material

import hiiragi283.material.common.util.ColorUtil
import hiiragi283.material.common.util.RagiColor

object MaterialCommon {

    @JvmField
    val HYDROXIDE = compoundOf("hydroxide", mapOf(MaterialElements.OXYGEN to 1, MaterialElements.HYDROGEN to 1))

    @JvmField
    val CARBONATE = compoundOf("carbonate", mapOf(MaterialElements.CARBON to 1, MaterialElements.OXYGEN to 3))

    @JvmField
    val NITRATE = compoundOf("nitrate", mapOf(MaterialElements.NITROGEN to 1, MaterialElements.OXYGEN to 3))

    @JvmField
    val SILICATE = compoundOf("silicate", mapOf(MaterialElements.SILICON to 1, MaterialElements.OXYGEN to 2))

    @JvmField
    val PHOSPHATE = compoundOf("phosphate", mapOf(MaterialElements.PHOSPHORUS to 1, MaterialElements.OXYGEN to 3))

    @JvmField
    val SULFATE = compoundOf("sulfate", mapOf(MaterialElements.SULFUR to 1, MaterialElements.OXYGEN to 4))

    //    Hydrogen    //

    @JvmField
    val WOOD =
        mixtureOf("wood", listOf(MaterialElements.CARBON, MaterialElements.HYDROGEN, MaterialElements.OXYGEN)) {
            color = ColorUtil.mixColor(RagiColor.DARK_GRAY to 2, RagiColor.RED to 1, RagiColor.YELLOW to 1).rgb
            validShapes.addAll(MaterialType.WOOD)
        }

    @JvmField
    val WATER = compoundOf("water", mapOf(MaterialElements.HYDROGEN to 2, MaterialElements.OXYGEN to 1)) {
        color = RagiColor.BLUE.rgb
        tempBoil = 373
        tempMelt = 273
        //if (isAprilFools()) translationKey = "material.dhmo"
    }

    //    Beryllium    //

    @JvmField
    val AQUAMARINE = compoundOf(
        "aquamarine", mapOf(
            MaterialElements.BERYLLIUM to 3,
            MaterialElements.ALUMINIUM to 2,
            MaterialElements.SILICON to 6,
            MaterialElements.OXYGEN to 16
        )
    ) {
        color = RagiColor.AQUA.rgb
        crystalType = CrystalType.EMERALD
        hasOre = true
        validShapes.addAll(MaterialType.GEM_9xADVANCED)
    }

    @JvmField
    val EMERALD = compoundOf(
        "emerald", mapOf(
            MaterialElements.BERYLLIUM to 3,
            MaterialElements.ALUMINIUM to 2,
            MaterialElements.SILICON to 6,
            MaterialElements.OXYGEN to 16
        )
    ) {
        color = RagiColor.GREEN.rgb
        crystalType = CrystalType.EMERALD
        hasOre = true
        validShapes.addAll(MaterialType.GEM_9xADVANCED)
    }

    //    Carbon    //

    @JvmField
    val COAL = compoundOf("coal", mapOf(MaterialElements.CARBON to 1)) {
        crystalType = CrystalType.COAL
        hasOre = true
        validShapes.addAll(MaterialType.GEM_9x)
    }

    @JvmField
    val CHARCOAL = compoundOf("charcoal", mapOf(MaterialElements.CARBON to 1)) {
        crystalType = CrystalType.COAL
        validShapes.addAll(MaterialType.GEM_9x)
    }

    @JvmField
    val COKE = compoundOf("coke", mapOf(MaterialElements.CARBON to 1)) {
        color = RagiColor.DARK_GRAY.rgb
        crystalType = CrystalType.COAL
        validShapes.addAll(MaterialType.GEM_9x)
    }

    @JvmField
    val DIAMOND = compoundOf("diamond", mapOf(MaterialElements.CARBON to 1)) {
        color = RagiColor.AQUA.rgb
        crystalType = CrystalType.DIAMOND
        hasOre = true
        validShapes.addAll(MaterialType.GEM_9xADVANCED)
    }

    @JvmField
    val SUGAR = compoundOf(
        "sugar",
        mapOf(MaterialElements.CARBON to 6, MaterialElements.HYDROGEN to 12, MaterialElements.OXYGEN to 6)
    ) {
        color = RagiColor.WHITE.rgb
        validShapes.addAll(MaterialType.SOLID)
    }

    @JvmField
    val AMBER = compoundOf("amber", mapOf(WOOD to 1)) {
        color = RagiColor.GOLD.rgb
        crystalType = CrystalType.AMORPHOUS
        hasOre = true
        validShapes.addAll(MaterialType.GEM_4x)
    }

    @JvmField
    val ASH =
        mixtureOf("ash", listOf(MaterialElements.CARBON, MaterialElements.SODIUM, MaterialElements.POTASSIUM)) {
            color = RagiColor.GRAY.rgb
            validShapes.addAll(MaterialType.SOLID)
        }

    @JvmField
    val RUBBER = polymerOf("rubber", mapOf(MaterialElements.CARBON to 5, MaterialElements.HYDROGEN to 8)) {
        color = ColorUtil.mixColor(RagiColor.BLACK, RagiColor.DARK_GRAY).rgb
        formula = "(CC(=C)C=C)n"
        validShapes.addAll(MaterialType.SOLID)
        validShapes.add("block")
        validShapes.add("ingot")
        validShapes.add("plate")
    }

    @JvmField
    val PLASTIC = polymerOf("plastic", mapOf(MaterialElements.CARBON to 2, MaterialElements.HYDROGEN to 4)) {
        color = ColorUtil.mixColor(RagiColor.GRAY, RagiColor.WHITE).rgb
        validShapes.addAll(MaterialType.SOLID)
        validShapes.add("block")
        validShapes.add("ingot")
        validShapes.add("plate")
    }

    //    Nitrogen    //

    @JvmField
    val NITER = compoundOf("niter", mapOf(MaterialElements.POTASSIUM to 1, NITRATE to 1)) {
        color = RagiColor.WHITE.rgb
        hasOre = true
        validShapes.addAll(MaterialType.GEM_9x)
    }


    @JvmField
    val NITRIC_ACID = compoundOf("nitric_acid", mapOf(MaterialElements.HYDROGEN to 1, NITRATE to 1)) {
        tempBoil = WATER.tempBoil
        tempMelt = WATER.tempMelt
    }

    @JvmField
    val GUNPOWDER = compoundOf(
        "gunpowder", mapOf(
            NITER.addBracket() to 1,
            MaterialElements.CARBON to 1,
            MaterialElements.SULFUR to 1
        )
    ) {
        color = RagiColor.DARK_GRAY.rgb
        validShapes.addAll(MaterialType.SOLID)
    }

    //    Fluorine    //

    @JvmField
    val CRYOLITE = compoundOf(
        "cryolite", mapOf(
            MaterialElements.SODIUM to 3,
            MaterialElements.ALUMINIUM to 1,
            MaterialElements.FLUORINE to 6
        )
    ) {
        color = RagiColor.WHITE.rgb
        crystalType = CrystalType.CUBIC
        hasOre = true
        validShapes.addAll(MaterialType.GEM_9x)
    }

    @JvmField
    val FLUORITE = compoundOf("fluorite", mapOf(MaterialElements.CALCIUM to 1, MaterialElements.FLUORINE to 2)) {
        color = ColorUtil.mixColor(RagiColor.GREEN, RagiColor.AQUA).rgb
        crystalType = CrystalType.CUBIC
        hasOre = true
        validShapes.addAll(MaterialType.GEM_9x)
    }

    @JvmField
    val HYDROGEN_FLUORIDE =
        compoundOf("hydrogen_fluoride", mapOf(MaterialElements.HYDROGEN to 1, MaterialElements.FLUORINE to 1)) {
            tempBoil = WATER.tempBoil
            tempMelt = WATER.tempMelt
        }

    //    Sodium    //

    @JvmField
    val SALT = compoundOf("salt", mapOf(MaterialElements.SODIUM to 1, MaterialElements.CHLORINE to 1)) {
        color = RagiColor.WHITE.rgb
        crystalType = CrystalType.CUBIC
        hasOre = true
        validShapes.addAll(MaterialType.GEM_4x)
    }

    //    Magnesium    //

    /*@JvmField
    val OLIVINE = compoundOf("olivine", 11200, mapOf(MaterialElements.MAGNESIUM to 2, SILICATE to 1)) {
        color = RagiColor.GREEN.rgb
        crystalType = CrystalType.EMERALD
        validShapes.addAll(MaterialType.GEM_9xADVANCED)
    }*/

    @JvmField
    val PERIDOT = compoundOf("peridot", mapOf(MaterialElements.MAGNESIUM to 2, SILICATE to 1)) {
        color = RagiColor.GREEN.rgb
        crystalType = CrystalType.EMERALD
        hasOre = true
        validShapes.addAll(MaterialType.GEM_9xADVANCED)
    }

    //    Aluminium    //

    @JvmField
    val ALUMINA = compoundOf("alumina", mapOf(MaterialElements.ALUMINIUM to 2, MaterialElements.OXYGEN to 3)) {
        color = RagiColor.WHITE.rgb
        validShapes.addAll(MaterialType.SOLID)
    }

    @JvmField
    val BAUXITE = hydrateOf("bauxite", ALUMINA, 2) {
        color = ColorUtil.mixColor(RagiColor.BLACK to 1, RagiColor.DARK_RED to 2, RagiColor.GOLD to 1).rgb
        hasOre = true
        validShapes.addAll(MaterialType.SOLID)
    }

    @JvmField
    val RUBY = compoundOf("ruby", mapOf(ALUMINA to 1)) {
        color = RagiColor.RED.rgb
        crystalType = CrystalType.RUBY
        hasOre = true
        validShapes.addAll(MaterialType.GEM_9xADVANCED)
    }

    @JvmField
    val SAPPHIRE = compoundOf("sapphire", mapOf(ALUMINA to 1)) {
        color = RagiColor.BLUE.rgb
        crystalType = CrystalType.RUBY
        hasOre = true
        validShapes.addAll(MaterialType.GEM_9xADVANCED)
    }

    //    Silicon    //

    @JvmField
    val CLAY = mixtureOf("clay", listOf(ALUMINA, SILICATE)) {
        color = ColorUtil.mixColor(RagiColor.GRAY to 2, RagiColor.AQUA to 1).rgb
        validShapes.addAll(MaterialType.SOLID)
    }

    @JvmField
    val END_STONE = compoundOf("end_stone", mapOf(SILICATE to 1)) {
        color = ColorUtil.mixColor(RagiColor.YELLOW to 1, RagiColor.WHITE to 3).rgb
        validShapes.addAll(MaterialType.SOLID)
    }

    @JvmField
    val GLASS = compoundOf("glass", mapOf(SILICATE to 1)) {
        color = RagiColor.WHITE.rgb
        validShapes.addAll(MaterialType.SOLID)
    }

    @JvmField
    val LAVA = compoundOf("lava", mapOf(SILICATE to 1)) {
        color = ColorUtil.mixColor(RagiColor.DARK_RED, RagiColor.GOLD).rgb
    }

    @JvmField
    val NETHERRACK = compoundOf("netherrack", mapOf(SILICATE to 1)) {
        color = RagiColor.DARK_RED.rgb
        validShapes.addAll(MaterialType.SOLID)
    }

    @JvmField
    val OBSIDIAN = compoundOf("obsidian", mapOf(SILICATE to 1)) {
        color = ColorUtil.mixColor(RagiColor.BLACK to 2, RagiColor.BLUE to 1, RagiColor.RED to 1).rgb
        validShapes.addAll(MaterialType.SOLID)
    }

    @JvmField
    val QUARTZ = compoundOf("quartz", mapOf(SILICATE to 1)) {
        color = RagiColor.WHITE.rgb
        crystalType = CrystalType.QUARTZ
        hasOre = true
        validShapes.addAll(MaterialType.GEM_4xADVANCED)
    }

    @JvmField
    val SOUL_SAND = compoundOf("soul_sand", mapOf(SILICATE to 1)) {
        color = ColorUtil.mixColor(RagiColor.BLACK to 5, RagiColor.GOLD to 1).rgb
        validShapes.addAll(MaterialType.SOLID)
    }

    @JvmField
    val STONE = compoundOf("stone", mapOf(SILICATE to 1)) {
        color = RagiColor.GRAY.rgb
        validShapes.addAll(MaterialType.SOLID)
        validShapes.add("gear")
        validShapes.add("plate")
        validShapes.add("stick")
    }

    //    Sulfur    //

    val SULFURIC_ACID = compoundOf("sulfuric_acid", mapOf(MaterialElements.HYDROGEN to 2, SULFATE to 1)) {
        color = ColorUtil.mixColor(RagiColor.GOLD, RagiColor.YELLOW).rgb
        tempBoil = WATER.tempBoil
        tempMelt = WATER.tempMelt
    }

    //    Chlorine    //

    @JvmField
    val HYDROGEN_CHLORIDE =
        compoundOf("hydrogen_chloride", mapOf(MaterialElements.HYDROGEN to 1, MaterialElements.CHLORINE to 1)) {
            tempBoil = WATER.tempBoil
            tempMelt = WATER.tempMelt
        }

    //    Calcium    //

    @JvmField
    val APATITE = compoundOf(
        "apatite", mapOf(
            MaterialElements.CALCIUM to 5,
            PHOSPHATE.addBracket() to 3,
            HYDROXIDE to 1
        )
    ) {
        color = ColorUtil.mixColor(RagiColor.YELLOW, RagiColor.WHITE).rgb
        crystalType = CrystalType.EMERALD
        hasOre = true
        validShapes.addAll(MaterialType.GEM_9x)
    }

    @JvmField
    val GYPSUM = compoundOf("gypsum", mapOf(MaterialElements.CALCIUM to 1, SULFATE to 1)) {
        crystalType = CrystalType.CUBIC
        hasOre = true
        hasOre = true
        validShapes.addAll(MaterialType.GEM_9x)
    }

    @JvmField
    val LIME = compoundOf("lime", mapOf(MaterialElements.CALCIUM to 1, CARBONATE to 1)) {
        color = RagiColor.WHITE.rgb
        hasOre = true
        validShapes.addAll(MaterialType.SOLID)
    }

    //    Titanium    //

    @JvmField
    val RUTILE = compoundOf("rutile", mapOf(MaterialElements.TITANIUM to 1, MaterialElements.OXYGEN to 2)) {
        color = RagiColor.YELLOW.rgb
        crystalType = CrystalType.QUARTZ
        hasOre = true
        validShapes.addAll(MaterialType.GEM_9x)
    }

    //    Chromium    //

    @JvmField
    val STAINLESS_STEEL = compoundOf(
        "stainless_steel", mapOf(
            MaterialElements.IRON to 6,
            MaterialElements.CHROMIUM to 1,
            MaterialElements.MANGANESE to 1,
            MaterialElements.NICKEL to 1
        )
    )
    {
        color = ColorUtil.mixColor(RagiColor.GRAY, RagiColor.WHITE).rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    //    Iron    //

    @JvmField
    val STEEL = mixtureOf("steel", listOf(MaterialElements.IRON, MaterialElements.CARBON)) {
        color = RagiColor.GRAY.rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    //    Nickel    //

    @JvmField
    val CONSTANTAN =
        compoundOf("constantan", mapOf(MaterialElements.NICKEL to 1, MaterialElements.COPPER to 1)) {
            crystalType = CrystalType.METAL
            validShapes.addAll(MaterialType.METAL_ADVANCED)
        }

    @JvmField
    val INVAR = compoundOf("invar", mapOf(MaterialElements.NICKEL to 2, MaterialElements.IRON to 1)) {
        color = ColorUtil.mixColor(
            RagiColor.GREEN to 1,
            RagiColor.GRAY to 3,
            RagiColor.WHITE to 4
        ).rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    //    Copper    //

    @JvmField
    val BRASS = compoundOf("brass", mapOf(MaterialElements.COPPER to 3, MaterialElements.ZINC to 1)) {
        color = RagiColor.GOLD.rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    @JvmField
    val BRONZE = compoundOf("bronze", mapOf(MaterialElements.COPPER to 3, MaterialElements.TIN to 1)) {
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    //    Silver    //

    val ELECTRUM = compoundOf("electrum", mapOf(MaterialElements.SILVER to 1, MaterialElements.GOLD to 1)) {
        color = ColorUtil.mixColor(RagiColor.GOLD, RagiColor.YELLOW, RagiColor.WHITE).rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    //    Mercury    //

    val CINNABAR = compoundOf("cinnabar", mapOf(MaterialElements.MERCURY to 1, MaterialElements.SULFUR to 1)) {
        color = RagiColor.RED.rgb
        crystalType = CrystalType.EMERALD
        hasOre = true
        validShapes.addAll(MaterialType.GEM_9x)
    }

    fun register() {
        this::class.java.declaredFields
            .map { it.also { it.isAccessible = true } }
            .map { it.get(this) }
            .filterIsInstance<HiiragiMaterial>()
            .forEach(MaterialRegistry::registerMaterial)
    }

}