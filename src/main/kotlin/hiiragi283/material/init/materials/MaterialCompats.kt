package hiiragi283.material.init.materials

import hiiragi283.material.api.material.*
import hiiragi283.material.compat.HiiragiThermalPlugin
import hiiragi283.material.init.HiiragiIconSets
import hiiragi283.material.init.HiiragiShapeTypes
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.util.HiiragiColor

object MaterialCompats {

    //    Vanilla    //

    @JvmField
    val REDSTONE = materialOf("redstone", 1000) {
        color = HiiragiColor.DARK_RED.rgb
        formula = "Rs"
        if (HiiragiThermalPlugin.enabled()) {
            hasFluid = false
        }
        shapeType = HiiragiShapeTypes.SOLID.copy {
            shapes.remove(HiiragiShapes.DUST)
        }
    }

    @JvmField
    val LAPIS = materialOf("lapis", 1001) {
        color = HiiragiColor.BLUE.rgb
        crystalType = CrystalType.LAPIS
        iconSet = HiiragiIconSets.LAPIS
        shapeType = HiiragiShapeTypes.GEM_9x_ADVANCED.copy {
            shapes.remove(HiiragiShapes.BLOCK)
            shapes.remove(HiiragiShapes.GEM)
        }
    }

    @JvmField
    val GLOWSTONE = materialOf("glowstone", 1002) {
        color = HiiragiColor.mixColor(HiiragiColor.GOLD to 1, HiiragiColor.YELLOW to 2).rgb
        formula = "Gl"
        if (HiiragiThermalPlugin.enabled()) {
            hasFluid = false
        }
        shapeType = HiiragiShapeTypes.SOLID.copy {
            shapes.remove(HiiragiShapes.DUST)
        }

    }

    @JvmField
    val ENDER_PEARL = materialOf("enderpearl", 1003) {
        color = HiiragiColor.mixColor(HiiragiColor.DARK_GREEN to 1, HiiragiColor.BLUE to 1).rgb
        formula = "En"
        if (HiiragiThermalPlugin.enabled()) {
            hasFluid = false
        }
        oreDictAlt.add("ender")
        oreDictAlt.add("ender_pearl")
        shapeType = HiiragiShapeTypes.SOLID
    }

    //    Thermal Series    //

    @JvmField
    val MITHRIL = materialOf("mithril", 1010) {
        color = HiiragiColor.mixColor(HiiragiColor.BLUE, HiiragiColor.AQUA, HiiragiColor.WHITE).rgb
        iconSet = HiiragiIconSets.METAL
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
    }

    @JvmField
    val SIGNALUM = alloyOf(
        "signalum",
        1011,
        mapOf(MaterialElements.COPPER to 4, MaterialElements.GOLD to 1, REDSTONE to 10)
    ) {
        color = 0xFF9E08
        iconSet = HiiragiIconSets.METAL
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
    }

    @JvmField
    val LUMIUM = alloyOf(
        "lumium",
        1012,
        mapOf(MaterialElements.TIN to 4, MaterialElements.SILVER to 1, GLOWSTONE to 4)
    ) {
        color = 0xDFE58F
        iconSet = HiiragiIconSets.METAL
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
    }

    @JvmField
    val ENDERIUM = alloyOf(
        "enderium",
        1013,
        mapOf(MaterialElements.LEAD to 4, MaterialElements.PLATINUM to 1, ENDER_PEARL to 4)
    ) {
        color = HiiragiColor.mixColor(HiiragiColor.DARK_GREEN to 1, HiiragiColor.BLUE to 1).rgb
        iconSet = HiiragiIconSets.METAL
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
    }

    @JvmField
    val PYROTHEUM = compoundOf(
        "pyrotheum",
        1014,
        mapOf(HiiragiMaterial.UNKNOWN to 2, REDSTONE to 1, MaterialElements.SULFUR to 1)
    ) {
        color = HiiragiColor.YELLOW.rgb
        if (HiiragiThermalPlugin.enabled()) {
            hasFluid = false
        }
    }

    @JvmField
    val CRYOTHEUM = compoundOf(
        "cryotheum",
        1015,
        mapOf(HiiragiMaterial.UNKNOWN to 2, REDSTONE to 1, MaterialCommons.WATER.addBracket() to 1)
    ) {
        color = HiiragiColor.AQUA.rgb
        if (HiiragiThermalPlugin.enabled()) {
            hasFluid = false
        }
    }

    @JvmField
    val AEROTHEUM = compoundOf(
        "aerotheum",
        1016,
        mapOf(HiiragiMaterial.UNKNOWN to 2, REDSTONE to 1, MaterialCommons.NITER.addBracket() to 1)
    ) {
        color = HiiragiColor.mixColor(HiiragiColor.GREEN, HiiragiColor.YELLOW, HiiragiColor.WHITE).rgb
        if (HiiragiThermalPlugin.enabled()) {
            hasFluid = false
        }
    }

    @JvmField
    val PETROTHEUM = compoundOf(
        "petrotheum",
        1017,
        mapOf(HiiragiMaterial.UNKNOWN to 2, REDSTONE to 1, MaterialCommons.OBSIDIAN.addBracket() to 1)
    ) {
        color =
            HiiragiColor.mixColor(
                HiiragiColor.BLACK to 4,
                HiiragiColor.GRAY to 2,
                HiiragiColor.BLUE to 1,
                HiiragiColor.RED to 1
            ).rgb
        if (HiiragiThermalPlugin.enabled()) {
            hasFluid = false
        }
    }

    //    Mekanism    //

    @JvmField
    val OBSIDIAN_REFINED = alloyOf(
        "refined_obsidian",
        1020,
        mapOf(MaterialCommons.OBSIDIAN.addBracket() to 1, MaterialElements.OSMIUM to 1, MaterialCommons.DIAMOND to 1)
    ) {
        color = HiiragiColor.mixColor(HiiragiColor.DARK_RED to 2, HiiragiColor.BLUE to 5, HiiragiColor.WHITE to 2).rgb
        iconSet = HiiragiIconSets.METAL
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
    }

    @JvmField
    val GLOWSTONE_REFINED = alloyOf(
        "refined_glowstone",
        1021,
        mapOf(GLOWSTONE to 1, MaterialElements.OSMIUM to 1)
    ) {
        color = HiiragiColor.YELLOW.rgb
        iconSet = HiiragiIconSets.METAL
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
    }

    //    Ender IO    //

    @JvmField
    val ELECTRICAL_STEEL = mixtureOf(
        "electrical_steel",
        1030,
        listOf(MaterialElements.IRON, MaterialElements.SILICON, MaterialElements.CARBON)
    ) {
        color = HiiragiColor.mixColor(HiiragiColor.GRAY to 1, HiiragiColor.WHITE to 2).rgb
        iconSet = HiiragiIconSets.METAL
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
        tempBoil = MaterialElements.IRON.tempBoil
        tempMelt = MaterialElements.IRON.tempMelt
    }

    @JvmField
    val ENERGETIC_ALLOY = alloyOf(
        "energetic_alloy",
        1031,
        mapOf(MaterialElements.GOLD to 1, REDSTONE to 1, GLOWSTONE to 1)
    ) {
        color = HiiragiColor.mixColor(HiiragiColor.GOLD, HiiragiColor.RED).rgb
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
    }

    @JvmField
    val VIBRANT_ALLOY = alloyOf(
        "vibrant_alloy",
        1032,
        mapOf(ENERGETIC_ALLOY.addBracket() to 1, ENDER_PEARL to 1)
    ) {
        color = HiiragiColor.mixColor(HiiragiColor.GREEN, HiiragiColor.YELLOW).rgb
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
    }

    @JvmField
    val REDSTONE_ALLOY = alloyOf(
        "redstone_alloy",
        1033,
        mapOf(MaterialElements.SILICON to 1, REDSTONE to 1)
    ) {
        color = HiiragiColor.mixColor(HiiragiColor.DARK_RED, HiiragiColor.RED).rgb
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
    }

    @JvmField
    val CONDUCTIVE_IRON = alloyOf(
        "conductive_iron",
        1034,
        mapOf(MaterialElements.IRON to 1, REDSTONE to 1)
    ) {
        color = HiiragiColor.mixColor(HiiragiColor.RED to 1, HiiragiColor.WHITE to 2).rgb
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
    }

    @JvmField
    val PULSATING_IRON = alloyOf(
        "pulsating_iron",
        1035,
        mapOf(MaterialElements.IRON to 1, ENDER_PEARL to 1)
    ) {
        color = HiiragiColor.mixColor(HiiragiColor.GREEN to 1, HiiragiColor.WHITE to 2).rgb
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
    }

    @JvmField
    val DARK_STEEL = mixtureOf(
        "dark_steel",
        1036,
        listOf(MaterialElements.IRON, MaterialElements.CARBON, MaterialCommons.OBSIDIAN)
    ) {
        color = HiiragiColor.mixColor(HiiragiColor.BLACK to 1, HiiragiColor.DARK_GRAY to 3).rgb
        iconSet = HiiragiIconSets.METAL
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
        tempBoil = MaterialElements.IRON.tempBoil
        tempMelt = MaterialElements.IRON.tempMelt
    }

    @JvmField
    val SOULARIUM = alloyOf(
        "soularium",
        1037,
        mapOf(MaterialElements.GOLD to 1, MaterialCommons.SOUL_SAND.addBracket() to 1)
    ) {
        color = HiiragiColor.mixColor(HiiragiColor.DARK_GRAY to 5, HiiragiColor.GOLD to 1).rgb
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
    }

    @JvmField
    val END_STEEL = mixtureOf(
        "end_steel",
        1038,
        listOf(DARK_STEEL, MaterialCommons.END_STONE, MaterialCommons.OBSIDIAN)
    ) {
        color = HiiragiColor.mixColor(HiiragiColor.YELLOW to 1, HiiragiColor.WHITE to 3).rgb
        iconSet = HiiragiIconSets.METAL
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
        tempBoil = MaterialElements.IRON.tempBoil
        tempMelt = MaterialElements.IRON.tempMelt
    }

    @JvmField
    val IRON_ALLOY = mixtureOf(
        "construction_alloy",
        1039,
        listOf(HiiragiMaterial.UNKNOWN, MaterialElements.IRON, HiiragiMaterial.UNKNOWN)
    ) {
        color = HiiragiColor.mixColor(HiiragiColor.BLUE, HiiragiColor.GREEN, HiiragiColor.RED).rgb
        iconSet = HiiragiIconSets.METAL
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = MaterialElements.IRON.tempBoil
        tempMelt = MaterialElements.IRON.tempMelt
    }

    //    Thaumcraft    //

    @JvmField
    val THAUMIUM = materialOf("thaumium", 1040) {
        color = HiiragiColor.mixColor(HiiragiColor.DARK_RED to 2, HiiragiColor.BLUE to 5, HiiragiColor.WHITE to 4).rgb
        iconSet = HiiragiIconSets.METAL
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
        formula = "✡"
    }

    @JvmField
    val VOID_METAL = materialOf("void", 1041) {
        color = HiiragiColor.mixColor(
            HiiragiColor.BLACK to 4,
            HiiragiColor.DARK_PURPLE to 1,
            HiiragiColor.BLUE to 1
        ).rgb
        formula = "Vm9pZA=="
        iconSet = HiiragiIconSets.METAL
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
    }

    //    Botania    //

    @JvmField
    val MANA = materialOf("mana", -1) {
        formula = "☆"
    }

    @JvmField
    val MANASTEEL = mixtureOf("manasteel", 1050, listOf(MaterialElements.IRON, MANA)) {
        color = HiiragiColor.BLUE.rgb
        iconSet = HiiragiIconSets.METAL
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
        tempBoil = MaterialElements.IRON.tempBoil
        tempMelt = MaterialElements.IRON.tempMelt
    }

    @JvmField
    val MANA_DIAMOND = mixtureOf("mana_diamond", 1052, listOf(MaterialCommons.DIAMOND, MANA)) {
        color = HiiragiColor.AQUA.rgb
        crystalType = CrystalType.DIAMOND
        iconSet = HiiragiIconSets.DIAMOND
        shapeType = HiiragiShapeTypes.GEM_9x_ADVANCED
    }

    @JvmField
    val TERRASTEEL = mixtureOf("terrasteel", 1053, listOf(MaterialElements.IRON, MANA)) {
        color = HiiragiColor.mixColor(HiiragiColor.DARK_GREEN, HiiragiColor.GREEN).rgb
        iconSet = HiiragiIconSets.METAL
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
        tempBoil = MANASTEEL.tempBoil
        tempMelt = MANASTEEL.tempMelt
    }

    @JvmField
    val ELEMENTIUM = mixtureOf("elven_elementium", 1054, listOf(MaterialElements.IRON, MANA)) {
        color = HiiragiColor.LIGHT_PURPLE.rgb
        iconSet = HiiragiIconSets.METAL
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
        tempBoil = MANASTEEL.tempBoil
        tempMelt = MANASTEEL.tempMelt
    }

    @JvmField
    val DRAGONSTONE = mixtureOf("elven_dragonstone", 1055, listOf(MaterialCommons.DIAMOND, MANA)) {
        color = HiiragiColor.LIGHT_PURPLE.rgb
        crystalType = CrystalType.DIAMOND
        iconSet = HiiragiIconSets.DIAMOND
        shapeType = HiiragiShapeTypes.GEM_9x_ADVANCED
    }

    //    Embers    //

    @JvmField
    val DAWNSTONE = alloyOf("dawnstone", 1060, mapOf(MaterialElements.COPPER to 1, MaterialElements.GOLD to 1)) {
        color = HiiragiColor.mixColor(HiiragiColor.GOLD to 2, HiiragiColor.RED to 1, HiiragiColor.YELLOW to 1).rgb
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
    }

    //    Project Red: Core    //

    @JvmField
    val ELECTROTINE = materialOf("electrotine", 1070) {
        color = HiiragiColor.mixColor(HiiragiColor.DARK_AQUA, HiiragiColor.BLUE, HiiragiColor.AQUA).rgb
        formula = "Er"
        molar = 112.2
        shapeType = HiiragiShapeTypes.SOLID
        tempBoil = 1201
        tempMelt = 1122
    }

    @JvmField
    val RED_ALLOY = alloyOf("red_alloy", 1071, mapOf(MaterialElements.IRON to 1, REDSTONE to 8)) {
        color = HiiragiColor.DARK_RED.rgb
        shapeType = HiiragiShapeTypes.METAL_COMMON
    }

    @JvmField
    val ELECTROTINE_ALLOY = alloyOf("electrotine_alloy", 1072, mapOf(MaterialElements.IRON to 1, ELECTROTINE to 8)) {
        color = HiiragiColor.mixColor(HiiragiColor.DARK_AQUA, HiiragiColor.BLUE, HiiragiColor.AQUA).rgb
        shapeType = HiiragiShapeTypes.METAL_COMMON
    }

    //    Tinker's Construct    //

    @JvmField
    val ARDITE = materialOf("ardite", 1080) {
        color = HiiragiColor.mixColor(HiiragiColor.DARK_RED to 2, HiiragiColor.GOLD to 1).rgb
        formula = "Ad"
        iconSet = HiiragiIconSets.METAL
        molar = 116.0
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 5000
        tempMelt = 3000
    }

    @JvmField
    val MANYULLYN = alloyOf("manyullyn", 1081, mapOf(MaterialElements.COBALT to 1, ARDITE to 1)) {
        color = HiiragiColor.mixColor(HiiragiColor.DARK_RED to 2, HiiragiColor.BLUE to 5, HiiragiColor.WHITE to 2).rgb
        shapeType = HiiragiShapeTypes.METAL_COMMON
    }

    @JvmField
    val ALUMINIUM_BRASS = alloyOf(
        "aluminium_brass",
        1084,
        mapOf(MaterialElements.ALUMINIUM to 3, MaterialElements.COPPER to 1)
    ) {
            color = HiiragiColor.mixColor(HiiragiColor.GOLD, HiiragiColor.YELLOW, HiiragiColor.WHITE).rgb
            oreDictAlt.add("alubrass")
            oreDictAlt.add("aluminiumbrass")
            oreDictAlt.add("aluminum_brass")
            oreDictAlt.add("aluminumbrass")
            shapeType = HiiragiShapeTypes.METAL_COMMON
    }

    //    Immersive Engineering    //

    @JvmField
    val TREATED_WOOD = mixtureOf("treated_wood", 1090, listOf(MaterialCommons.WOOD)) {
        shapeType = HiiragiShapeTypes.WOOD
    }

}