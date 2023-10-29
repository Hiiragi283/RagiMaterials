package hiiragi283.material.init.materials

import hiiragi283.material.api.material.*
import hiiragi283.material.init.HiiragiIconSets
import hiiragi283.material.init.HiiragiShapeTypes
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.util.HiiragiColor
import hiiragi283.material.util.enableAccess
import hiiragi283.material.util.isAprilFools
import net.minecraftforge.fluids.FluidRegistry
import java.lang.reflect.Field

object MaterialCommons {

    @JvmField
    val HYDROXIDE = compoundOf("hydroxide", -1, mapOf(MaterialElements.OXYGEN to 1, MaterialElements.HYDROGEN to 1)) {
        hasFluid = false
    }

    @JvmField
    val CARBONATE = compoundOf("carbonate", -1, mapOf(MaterialElements.CARBON to 1, MaterialElements.OXYGEN to 3)) {
        hasFluid = false
    }

    @JvmField
    val NITRATE = compoundOf("nitrate", -1, mapOf(MaterialElements.NITROGEN to 1, MaterialElements.OXYGEN to 3)) {
        hasFluid = false
    }

    @JvmField
    val SILICATE = compoundOf("silicate", -1, mapOf(MaterialElements.SILICON to 1, MaterialElements.OXYGEN to 2)) {
        hasFluid = false
    }

    @JvmField
    val PHOSPHATE = compoundOf("phosphate", -1, mapOf(MaterialElements.PHOSPHORUS to 1, MaterialElements.OXYGEN to 3)) {
        hasFluid = false
    }

    @JvmField
    val SULFATE = compoundOf("sulfate", -1, mapOf(MaterialElements.SULFUR to 1, MaterialElements.OXYGEN to 4)) {
        hasFluid = false
    }

    //    Hydrogen    //

    @JvmField
    val WOOD = mixtureOf(
        "wood",
        10100,
        listOf(MaterialElements.CARBON, MaterialElements.HYDROGEN, MaterialElements.OXYGEN)
    ) {
        color = HiiragiColor.mixColor(
            HiiragiColor.DARK_GRAY to 2,
            HiiragiColor.RED to 1,
            HiiragiColor.YELLOW to 1
        ).rgb
        hasFluid = false
        oreDictAlt.add("saw_dust")
        shapeType = HiiragiShapeTypes.WOOD
    }.setSmelted(MaterialElements.CARBON)

    @JvmField
    val WATER = compoundOf("water", 10101, mapOf(MaterialElements.HYDROGEN to 2, MaterialElements.OXYGEN to 1)) {
        color = HiiragiColor.BLUE.rgb
        hasFluid = false
        tempBoil = 373
        tempMelt = 273
        if (isAprilFools()) translationKey = "material.dhmo"
    }

    //    Beryllium    //

    @JvmField
    val AQUAMARINE = compoundOf(
        "aquamarine", 10400, mapOf(
            MaterialElements.BERYLLIUM to 3,
            MaterialElements.ALUMINIUM to 2,
            MaterialElements.SILICON to 6,
            MaterialElements.OXYGEN to 18
        )
    ) {
        color = HiiragiColor.AQUA.rgb
        crystalType = CrystalType.EMERALD
        iconSet = HiiragiIconSets.EMERALD
        shapeType = HiiragiShapeTypes.GEM_9x_ADVANCED
    }

    @JvmField
    val EMERALD = allotropeOf("emerald", 10401, AQUAMARINE) {
        color = HiiragiColor.GREEN.rgb
        shapeType = shapeType.copy {
            shapes.remove(HiiragiShapes.BLOCK)
            shapes.remove(HiiragiShapes.GEM)
        }
    }

    //    Carbon    //

    @JvmField
    val COAL = compoundOf("coal", 10600, mapOf(MaterialElements.CARBON to 1)) {
        crystalType = CrystalType.COAL
        hasFluid = false
        iconSet = HiiragiIconSets.COAL
        shapeType = HiiragiShapeTypes.GEM_9x.copy {
            shapes.remove(HiiragiShapes.BLOCK)
            shapes.remove(HiiragiShapes.GEM)
        }
    }

    @JvmField
    val CHARCOAL = allotropeOf("charcoal", 10601, COAL) {
        shapeType = shapeType.copy {
            shapes.add(HiiragiShapes.BLOCK)
        }
    }

    @JvmField
    val COKE = allotropeOf("coke", 10602, COAL) {
        color = HiiragiColor.DARK_GRAY.rgb
        oreDictAlt.add("fuel_coke")
        shapeType = shapeType.copy {
            shapes.add(HiiragiShapes.BLOCK)
            shapes.add(HiiragiShapes.GEM)
        }
    }

    @JvmField
    val DIAMOND = compoundOf("diamond", 10603, mapOf(MaterialElements.CARBON to 1)) {
        color = HiiragiColor.AQUA.rgb
        crystalType = CrystalType.DIAMOND
        hasFluid = false
        iconSet = HiiragiIconSets.DIAMOND
        shapeType = HiiragiShapeTypes.GEM_9x.copy {
            shapes.remove(HiiragiShapes.BLOCK)
            shapes.remove(HiiragiShapes.GEM)
        }
    }.setSmelted(MaterialElements.CARBON)

    @JvmField
    val SUGAR = compoundOf(
        "sugar",
        10604,
        mapOf(MaterialElements.CARBON to 6, MaterialElements.HYDROGEN to 12, MaterialElements.OXYGEN to 6)
    ) {
        color = HiiragiColor.WHITE.rgb
        hasFluid = false
        shapeType = HiiragiShapeTypes.SOLID.copy {
            shapes.remove(HiiragiShapes.DUST)
        }
    }.setSmelted(MaterialElements.CARBON, 6)

    @JvmField
    val AMBER = compoundOf("amber", 10605, mapOf(WOOD to 1)) {
        color = HiiragiColor.GOLD.rgb
        crystalType = CrystalType.AMORPHOUS
        hasFluid = false
        iconSet = HiiragiIconSets.AMORPHOUS
        shapeType = HiiragiShapeTypes.GEM_9x
    }.setScale(HiiragiShapes.BLOCK, 144 * 4)

    @JvmField
    val ASH = mixtureOf(
        "ash",
        10606,
        listOf(MaterialElements.CARBON, MaterialElements.SODIUM, MaterialElements.POTASSIUM)
    ) {
            color = HiiragiColor.GRAY.rgb
        hasFluid = false
        shapeType = HiiragiShapeTypes.SOLID
    }

    @JvmField
    val RUBBER = polymerOf("rubber", 10607, mapOf(MaterialElements.CARBON to 5, MaterialElements.HYDROGEN to 8)) {
        color = HiiragiColor.mixColor(HiiragiColor.BLACK, HiiragiColor.DARK_GRAY).rgb
        formula = "(CC(=C)C=C)n"
        shapeType = HiiragiShapeTypes.SOLID
        /*validShapes.add("block")
        validShapes.add("ingot")
        validShapes.add("plate")*/
    }.setSmelted(MaterialElements.CARBON, 5)

    @JvmField
    val PLASTIC = polymerOf("plastic", 10608, mapOf(MaterialElements.CARBON to 2, MaterialElements.HYDROGEN to 4)) {
        color = HiiragiColor.mixColor(HiiragiColor.GRAY, HiiragiColor.WHITE).rgb
        oreDictAlt.add("polyethylene")
        shapeType = HiiragiShapeTypes.SOLID
        /*validShapes.add("block")
        validShapes.add("scaffolding")
        validShapes.add("ingot")
        validShapes.add("plate")*/
    }.setSmelted(MaterialElements.CARBON, 2)

    //    Nitrogen    //

    @JvmField
    val NITER = compoundOf("niter", 10700, mapOf(MaterialElements.POTASSIUM to 1, NITRATE to 1)) {
        color = HiiragiColor.WHITE.rgb
        crystalType = CrystalType.CUBIC
        iconSet = HiiragiIconSets.CUBIC
        oreDictAlt.add("saltpeter")
        shapeType = HiiragiShapeTypes.GEM_9x
    }

    @JvmField
    val NITRIC_ACID = compoundOf("nitric_acid", 10701, mapOf(MaterialElements.HYDROGEN to 1, NITRATE to 1)) {
        hasFluidBlock = true
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
        hasFluid = false
        shapeType = HiiragiShapeTypes.SOLID.copy {
            shapes.remove(HiiragiShapes.DUST)
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
        iconSet = HiiragiIconSets.CUBIC
        shapeType = HiiragiShapeTypes.GEM_9x
    }

    @JvmField
    val FLUORITE = compoundOf("fluorite", 10901, mapOf(MaterialElements.CALCIUM to 1, MaterialElements.FLUORINE to 2)) {
        color = HiiragiColor.mixColor(HiiragiColor.GREEN, HiiragiColor.AQUA).rgb
        crystalType = CrystalType.CUBIC
        iconSet = HiiragiIconSets.CUBIC
        shapeType = HiiragiShapeTypes.GEM_9x
    }

    @JvmField
    val HYDROGEN_FLUORIDE = compoundOf(
        "hydrogen_fluoride",
        10902,
        mapOf(MaterialElements.HYDROGEN to 1, MaterialElements.FLUORINE to 1)
    ) {
        hasFluid = false
            tempBoil = WATER.tempBoil
            tempMelt = WATER.tempMelt
    }

    //    Sodium    //

    @JvmField
    val SALT = compoundOf("salt", 11100, mapOf(MaterialElements.SODIUM to 1, MaterialElements.CHLORINE to 1)) {
        color = HiiragiColor.WHITE.rgb
        shapeType = HiiragiShapeTypes.SOLID
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
        hasFluid = false
        iconSet = HiiragiIconSets.EMERALD
        oreDictAlt.add("olivine")
        shapeType = HiiragiShapeTypes.GEM_9x_ADVANCED
    }

    //    Aluminium    //

    @JvmField
    val ALUMINA = compoundOf("alumina", 11300, mapOf(MaterialElements.ALUMINIUM to 2, MaterialElements.OXYGEN to 3)) {
        color = HiiragiColor.WHITE.rgb
        shapeType = HiiragiShapeTypes.SOLID
    }

    @JvmField
    val BAUXITE = hydrateOf("bauxite", 11301, ALUMINA, 2) {
        color = HiiragiColor.mixColor(HiiragiColor.BLACK to 1, HiiragiColor.DARK_RED to 2, HiiragiColor.GOLD to 1).rgb
        hasFluid = false
        shapeType = HiiragiShapeTypes.SOLID
    }

    @JvmField
    val RUBY = compoundOf("ruby", 11302, mapOf(ALUMINA to 1)) {
        color = HiiragiColor.RED.rgb
        crystalType = CrystalType.RUBY
        iconSet = HiiragiIconSets.RUBY
        shapeType = HiiragiShapeTypes.GEM_9x_ADVANCED
    }

    @JvmField
    val SAPPHIRE = compoundOf("sapphire", 11303, mapOf(ALUMINA to 1)) {
        color = HiiragiColor.BLUE.rgb
        crystalType = CrystalType.RUBY
        iconSet = HiiragiIconSets.RUBY
        shapeType = HiiragiShapeTypes.GEM_9x_ADVANCED
    }

    //    Silicon    //

    @JvmField
    val CLAY = mixtureOf("clay", 11400, listOf(ALUMINA, SILICATE)) {
        color = 0xC8C8DC
        hasFluid = false
        shapeType = HiiragiShapeTypes.SOLID
    }.setScale(HiiragiShapes.BLOCK, 144 * 4)

    @JvmField
    val END_STONE = compoundOf("end_stone", 11401, mapOf(SILICATE to 1)) {
        color = HiiragiColor.mixColor(HiiragiColor.YELLOW to 1, HiiragiColor.WHITE to 3).rgb
        hasFluid = false
        shapeType = HiiragiShapeTypes.SOLID
    }

    @JvmField
    val GLASS = compoundOf("glass", 11402, mapOf(SILICATE to 1)) {
        color = HiiragiColor.WHITE.rgb
        shapeType = HiiragiShapeTypes.SOLID
    }.setScale(HiiragiShapes.BLOCK, 1000)

    @JvmField
    val LAVA = compoundOf("lava", 11403, mapOf(SILICATE to 1)) {
        color = HiiragiColor.mixColor(HiiragiColor.DARK_RED, HiiragiColor.GOLD).rgb
        hasFluid = false
        tempMelt = FluidRegistry.LAVA.temperature
        shapeType = HiiragiShapeTypes.LIQUID
    }

    @JvmField
    val NETHERRACK = compoundOf("netherrack", 11404, mapOf(SILICATE to 1)) {
        color = HiiragiColor.DARK_RED.rgb
        hasFluid = false
        shapeType = HiiragiShapeTypes.SOLID
    }

    @JvmField
    val OBSIDIAN = compoundOf("obsidian", 11405, mapOf(SILICATE to 1)) {
        color = HiiragiColor.mixColor(HiiragiColor.BLACK to 2, HiiragiColor.BLUE to 1, HiiragiColor.RED to 1).rgb
        hasFluid = false
        shapeType = HiiragiShapeTypes.SOLID
    }

    @JvmField
    val QUARTZ = compoundOf("quartz", 11406, mapOf(SILICATE to 1)) {
        color = HiiragiColor.WHITE.rgb
        crystalType = CrystalType.QUARTZ
        hasFluid = false
        iconSet = HiiragiIconSets.QUARTZ
        oreDictAlt.add("nether_quartz")
        shapeType = HiiragiShapeTypes.GEM_4x_ADVANCED.copy {
            shapes.remove(HiiragiShapes.GEM)
        }
    }.setScale(HiiragiShapes.BLOCK, 144 * 4)

    @JvmField
    val SOUL_SAND = compoundOf("soul_sand", 11407, mapOf(SILICATE to 1)) {
        color = HiiragiColor.mixColor(HiiragiColor.BLACK to 5, HiiragiColor.GOLD to 1).rgb
        hasFluid = false
        shapeType = HiiragiShapeTypes.SOLID
    }

    @JvmField
    val STONE = compoundOf("stone", 11408, mapOf(SILICATE to 1)) {
        color = HiiragiColor.GRAY.rgb
        hasFluid = false
        shapeType = HiiragiShapeTypes.STONE
    }

    //    Sulfur    //

    val SULFURIC_ACID = compoundOf("sulfuric_acid", 11600, mapOf(MaterialElements.HYDROGEN to 2, SULFATE to 1)) {
        color = HiiragiColor.mixColor(HiiragiColor.GOLD, HiiragiColor.YELLOW).rgb
        tempBoil = WATER.tempBoil
        tempMelt = WATER.tempMelt
    }

    //    Chlorine    //

    @JvmField
    val HYDROGEN_CHLORIDE = compoundOf(
        "hydrogen_chloride",
        11700,
        mapOf(MaterialElements.HYDROGEN to 1, MaterialElements.CHLORINE to 1)
    ) {
            tempBoil = WATER.tempBoil
            tempMelt = WATER.tempMelt
    }

    //    Calcium    //

    @JvmField
    val APATITE = compoundOf(
            "apatite",
            12000,
            mapOf(MaterialElements.CALCIUM to 5, PHOSPHATE.addBracket() to 3, HYDROXIDE to 1)
        ) {
        color = HiiragiColor.mixColor(HiiragiColor.YELLOW, HiiragiColor.WHITE).rgb
        crystalType = CrystalType.EMERALD
        iconSet = HiiragiIconSets.EMERALD
        shapeType = HiiragiShapeTypes.GEM_9x
    }

    @JvmField
    val GYPSUM = compoundOf("gypsum", 12001, mapOf(MaterialElements.CALCIUM to 1, SULFATE to 1)) {
        crystalType = CrystalType.CUBIC
        iconSet = HiiragiIconSets.CUBIC
        shapeType = HiiragiShapeTypes.GEM_9x
    }

    @JvmField
    val LIME = compoundOf("lime", 12002, mapOf(MaterialElements.CALCIUM to 1, CARBONATE to 1)) {
        color = HiiragiColor.WHITE.rgb
        hasFluid = false
        shapeType = HiiragiShapeTypes.SOLID
    }

    //    Titanium    //

    @JvmField
    val RUTILE = compoundOf("rutile", 12200, mapOf(MaterialElements.TITANIUM to 1, MaterialElements.OXYGEN to 2)) {
        color = HiiragiColor.YELLOW.rgb
        crystalType = CrystalType.CUBIC
        hasFluid = false
        iconSet = HiiragiIconSets.CUBIC
        shapeType = HiiragiShapeTypes.GEM_9x
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
    ) {
        color = HiiragiColor.mixColor(HiiragiColor.GRAY, HiiragiColor.WHITE).rgb
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
    }

    @JvmField
    val CHROMITE = compoundOf(
        "chromite",
        12401,
        mapOf(MaterialElements.IRON to 2, MaterialElements.CHROMIUM to 2, MaterialElements.OXYGEN to 4)
    ) {
        hasFluid = false
        shapeType = HiiragiShapeTypes.SOLID
    }.setSmelted(MaterialElements.CHROMIUM, 2)

    //    Manganese    //

    @JvmField
    val PYROLUSITE = compoundOf(
        "pyrolusite",
        12500,
        mapOf(MaterialElements.MANGANESE to 1, MaterialElements.OXYGEN to 2)
    ) {
        hasFluid = false
        shapeType = HiiragiShapeTypes.SOLID
    }.setSmelted(MaterialElements.MANGANESE)

    //    Iron    //

    @JvmField
    val STEEL = mixtureOf("steel", 12600, listOf(MaterialElements.IRON, MaterialElements.CARBON)) {
        color = HiiragiColor.GRAY.rgb
        iconSet = HiiragiIconSets.METAL
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
        tempBoil = MaterialElements.IRON.tempBoil
        tempMelt = MaterialElements.IRON.tempMelt
    }

    @JvmField
    val RAW_STEEL = mixtureOf("raw_steel", 12601, listOf(MaterialElements.IRON, MaterialElements.CARBON)) {
        color = HiiragiColor.mixColor(HiiragiColor.BLACK, HiiragiColor.GRAY).rgb
        hasFluid = false
        shapeType = HiiragiShapeTypes.SOLID
    }.setSmelted(STEEL)

    @JvmField
    val HEMATITE = compoundOf("hematite", 12602, mapOf(MaterialElements.IRON to 2, MaterialElements.OXYGEN to 3)) {
        color = HiiragiColor.mixColor(HiiragiColor.BLACK to 1, HiiragiColor.DARK_RED to 2, HiiragiColor.GOLD to 1).rgb
        hasFluid = false
        shapeType = HiiragiShapeTypes.SOLID
    }.setSmelted(MaterialElements.IRON, 2)

    @JvmField
    val MAGNETITE = compoundOf("magnetite", 12603, mapOf(MaterialElements.IRON to 3, MaterialElements.OXYGEN to 4)) {
        color = HiiragiColor.mixColor(HiiragiColor.BLACK, HiiragiColor.GRAY).rgb
        hasFluid = false
        shapeType = HiiragiShapeTypes.SOLID
    }.setSmelted(MaterialElements.IRON, 3)

    @JvmField
    val PYRITE = compoundOf("pyrite", 12604, mapOf(MaterialElements.IRON to 1, MaterialElements.SULFUR to 2)) {
        color = HiiragiColor.mixColor(HiiragiColor.GOLD, HiiragiColor.YELLOW).rgb
        crystalType = CrystalType.CUBIC
        hasFluid = false
        iconSet = HiiragiIconSets.CUBIC
        shapeType = HiiragiShapeTypes.GEM_9x
        if (isAprilFools()) translationKey = MaterialElements.GOLD.translationKey
    }.setSmelted(MaterialElements.IRON)

    //    Cobalt    //

    @JvmField
    val COBALTITE = compoundOf(
        "cobaltite",
        12700,
        mapOf(MaterialElements.COBALT to 1, MaterialElements.ARSENIC to 1, MaterialElements.SULFUR to 1)
    ) {
        color = HiiragiColor.DARK_BLUE.rgb
        hasFluid = false
        shapeType = HiiragiShapeTypes.SOLID
    }.setSmelted(MaterialElements.COBALT)

    //    Nickel    //

    @JvmField
    val CONSTANTAN = alloyOf(
        "constantan",
        12800,
        mapOf(MaterialElements.NICKEL to 1, MaterialElements.COPPER to 1)
    ) {
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
    }

    @JvmField
    val INVAR = alloyOf("invar", 12801, mapOf(MaterialElements.NICKEL to 2, MaterialElements.IRON to 1)) {
        color = HiiragiColor.mixColor(
            HiiragiColor.GREEN to 1,
            HiiragiColor.GRAY to 3,
            HiiragiColor.WHITE to 4
        ).rgb
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
    }

    @JvmField
    val GARNIERITE = compoundOf(
        "garnierite",
        12802,
        mapOf(MaterialElements.NICKEL to 1, MaterialElements.OXYGEN to 1)
    ) {
        hasFluid = false
        shapeType = HiiragiShapeTypes.SOLID
    }.setSmelted(MaterialElements.NICKEL)

    //    Copper    //

    @JvmField
    val BRASS = alloyOf("brass", 12900, mapOf(MaterialElements.COPPER to 3, MaterialElements.ZINC to 1)) {
        color = HiiragiColor.GOLD.rgb
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
    }

    @JvmField
    val BRONZE = alloyOf("bronze", 12901, mapOf(MaterialElements.COPPER to 3, MaterialElements.TIN to 1)) {
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
    }

    @JvmField
    val MALACHITE = compoundOf(
        "malachite",
        12902,
        mapOf(MaterialElements.COPPER to 2, CARBONATE.addBracket() to 1, HYDROXIDE.addBracket() to 2)
    ) {
        color = HiiragiColor.mixColor(HiiragiColor.GREEN, HiiragiColor.AQUA).rgb
        hasFluid = false
        shapeType = HiiragiShapeTypes.SOLID
    }.setSmelted(MaterialElements.COPPER, 2)

    @JvmField
    val CHALCOCITE = compoundOf(
        "chalcocite",
        12903,
        mapOf(MaterialElements.COPPER to 2, MaterialElements.SULFUR to 1)
    ) {
        hasFluid = false
        shapeType = HiiragiShapeTypes.SOLID
    }.setSmelted(MaterialElements.COPPER, 2)

    //    Zinc    //

    @JvmField
    val SPHALERITE = compoundOf("sphalerite", 13000, mapOf(MaterialElements.ZINC to 1, MaterialElements.SULFUR to 1)) {
        hasFluid = false
        shapeType = HiiragiShapeTypes.SOLID
    }.setSmelted(MaterialElements.ZINC)

    //    Molybdenum    //

    @JvmField
    val MOLYBDENITE = compoundOf(
        "molybdenite",
        14200,
        mapOf(MaterialElements.MOLYBDENUM to 2, MaterialElements.SULFUR to 3)
    ) {
        hasFluid = false
        shapeType = HiiragiShapeTypes.SOLID
    }.setSmelted(MaterialElements.MOLYBDENUM, 2)

    //    Silver    //

    @JvmField
    val ELECTRUM = alloyOf("electrum", 14700, mapOf(MaterialElements.SILVER to 1, MaterialElements.GOLD to 1)) {
        color = HiiragiColor.mixColor(HiiragiColor.GOLD, HiiragiColor.YELLOW, HiiragiColor.WHITE).rgb
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
    }

    @JvmField
    val ARGENTITE = compoundOf("argentite", 14701, mapOf(MaterialElements.SILVER to 2, MaterialElements.SULFUR to 1)) {
        color = HiiragiColor.mixColor(HiiragiColor.BLACK, HiiragiColor.GRAY).rgb
        hasFluid = false
        shapeType = HiiragiShapeTypes.SOLID
    }.setSmelted(MaterialElements.SILVER, 2)

    //    Tin    //

    @JvmField
    val CASSITERITE = compoundOf("cassiterite", 15000, mapOf(MaterialElements.TIN to 1, MaterialElements.OXYGEN to 2)) {
        hasFluid = false
        shapeType = HiiragiShapeTypes.SOLID
    }.setSmelted(MaterialElements.TIN)

    //    Tungsten    //

    @JvmField
    val TUNGSTEN_STEEL = alloyOf("tungsten_steel", 17400, mapOf(STEEL to 1, MaterialElements.TUNGSTEN to 1)) {
        color = HiiragiColor.mixColor(HiiragiColor.BLACK, HiiragiColor.DARK_GRAY).rgb
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
    }

    //    Mercury    //

    @JvmField
    val CINNABAR = compoundOf("cinnabar", 18000, mapOf(MaterialElements.MERCURY to 1, MaterialElements.SULFUR to 1)) {
        color = HiiragiColor.RED.rgb
        crystalType = CrystalType.EMERALD
        hasFluid = false
        iconSet = HiiragiIconSets.EMERALD
        oreDictAlt.add("quicksilver")
        oreDictAlt.add("quick_silver")
        shapeType = HiiragiShapeTypes.GEM_9x
    }

    //    Lead    //

    @JvmField
    val GALENA = compoundOf("galena", 18200, mapOf(MaterialElements.LEAD to 1, MaterialElements.SULFUR to 1)) {
        hasFluid = false
        shapeType = HiiragiShapeTypes.SOLID
    }.setSmelted(MaterialElements.LEAD)

    //    Bismuth    //

    @JvmField
    val BISMUTHINITE = compoundOf(
        "bismuthinite",
        18300,
        mapOf(MaterialElements.BISMUTH to 2, MaterialElements.SULFUR to 3)
    ) {
        color = HiiragiColor.mixColor(HiiragiColor.BLUE, HiiragiColor.AQUA).rgb
        crystalType = CrystalType.RUBY
        hasFluid = false
        iconSet = HiiragiIconSets.RUBY
        shapeType = HiiragiShapeTypes.GEM_9x
    }.setSmelted(MaterialElements.BISMUTH, 2)

    fun register() {
        this::class.java.declaredFields
            .map(Field::enableAccess)
            .map { it.get(this) }
            .filterIsInstance<HiiragiMaterial>()
            .forEach(HiiragiMaterial::register)
    }

}