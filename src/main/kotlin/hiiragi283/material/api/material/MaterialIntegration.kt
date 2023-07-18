package hiiragi283.material.api.material

import hiiragi283.material.util.ColorUtil
import hiiragi283.material.util.RagiColor

object MaterialIntegration {

    //    Vanilla    //

    @JvmField
    val REDSTONE = materialOf("redstone", 1000) {
        color = RagiColor.DARK_RED.rgb
        crystalType = CrystalType.EMERALD
        formula = "Rs"
        molar = 112.2
        tempBoil = 1201
        tempMelt = 1122
        validShapes.addAll(MaterialType.SOLID)
        validShapes.add("block")
    }

    @JvmField
    val LAPIS = materialOf("lapis", 1001) {
        color = RagiColor.BLUE.rgb
        crystalType = CrystalType.LAPIS
        validShapes.addAll(MaterialType.GEM_9xADVANCED)
    }

    @JvmField
    val GLOWSTONE = materialOf("glowstone", 1002) {
        color = ColorUtil.mixColor(RagiColor.GOLD to 1, RagiColor.YELLOW to 2).rgb
        crystalType = CrystalType.EMERALD
        formula = "Gl"
        molar = 112.2
        tempBoil = 1201
        tempMelt = 1122
        validShapes.addAll(MaterialType.SOLID)
    }

    @JvmField
    val ENDER_PEARL = materialOf("enderpearl", 1003) {
        color = ColorUtil.mixColor(RagiColor.DARK_GREEN to 1, RagiColor.BLUE to 1).rgb
        crystalType = CrystalType.EMERALD
        formula = "En"
        molar = 112.2
        oreDictAlt = "ender_pearl"
        tempBoil = 1201
        tempMelt = 1122
        validShapes.addAll(MaterialType.SOLID)
    }

    //    Thermal Series    //

    @JvmField
    val MITHRIL = materialOf("mithril", 1010) {
        color = ColorUtil.mixColor(RagiColor.BLUE, RagiColor.AQUA, RagiColor.WHITE).rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    @JvmField
    val SIGNALUM = compoundOf(
        "signalum",
        1011,
        mapOf(MaterialElements.COPPER to 4, MaterialElements.GOLD to 1, REDSTONE to 10)
    ) {
        color = ColorUtil.mixColor(RagiColor.RED, RagiColor.YELLOW).rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    @JvmField
    val LUMIUM = compoundOf(
        "lumium",
        1012,
        mapOf(MaterialElements.TIN to 4, MaterialElements.SILVER to 1, GLOWSTONE to 4)
    ) {
        color = ColorUtil.mixColor(RagiColor.GOLD, RagiColor.YELLOW, RagiColor.WHITE).rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    @JvmField
    val ENDERIUM = compoundOf(
        "enderium",
        1013,
        mapOf(MaterialElements.LEAD to 4, MaterialElements.PLATINUM to 1, ENDER_PEARL to 4)
    ) {
        color = ColorUtil.mixColor(RagiColor.DARK_GREEN to 1, RagiColor.BLUE to 1).rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    @JvmField
    val PYROTHEUM = compoundOf(
        "pyrotheum",
        1014,
        mapOf(HiiragiMaterial.UNKNOWN to 2, REDSTONE to 1, MaterialElements.SULFUR to 1)
    ) {
        color = ColorUtil.mixColor(RagiColor.YELLOW, RagiColor.WHITE).rgb
    }

    @JvmField
    val CRYOTHEUM = compoundOf(
        "cryotheum",
        1015,
        mapOf(HiiragiMaterial.UNKNOWN to 2, REDSTONE to 1, MaterialCommon.WATER.addBracket() to 1)
    ) {
        color = ColorUtil.mixColor(RagiColor.AQUA, RagiColor.WHITE).rgb
    }

    @JvmField
    val AEROTHEUM = compoundOf(
        "aerotheum",
        1016,
        mapOf(HiiragiMaterial.UNKNOWN to 2, REDSTONE to 1, MaterialCommon.NITER.addBracket() to 1)
    ) {
        color = ColorUtil.mixColor(RagiColor.GREEN, RagiColor.YELLOW, RagiColor.WHITE).rgb
    }

    @JvmField
    val PETROTHEUM = compoundOf(
        "petrotheum",
        1017,
        mapOf(HiiragiMaterial.UNKNOWN to 2, REDSTONE to 1, MaterialCommon.OBSIDIAN.addBracket() to 1)
    ) {
        color =
            ColorUtil.mixColor(RagiColor.BLACK to 4, RagiColor.GRAY to 2, RagiColor.BLUE to 1, RagiColor.RED to 1).rgb
    }

    //    Mekanism    //

    @JvmField
    val OBSIDIAN_REFINED = compoundOf(
        "refined_obsidian",
        1020,
        mapOf(MaterialCommon.OBSIDIAN.addBracket() to 1, MaterialElements.OSMIUM to 1, MaterialCommon.DIAMOND to 1)
    ) {
        color = ColorUtil.mixColor(RagiColor.DARK_RED to 2, RagiColor.BLUE to 5, RagiColor.WHITE to 2).rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    @JvmField
    val GLOWSTONE_REFINED = compoundOf(
        "refined_glowstone",
        1021,
        mapOf(GLOWSTONE to 1, MaterialElements.OSMIUM to 1)
    ) {
        color = ColorUtil.mixColor(RagiColor.YELLOW to 2, RagiColor.WHITE to 1).rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    //    Ender IO    //

    @JvmField
    val ELECTRICAL_STEEL = mixtureOf(
        "electrical_steel",
        1030,
        listOf(MaterialElements.IRON, MaterialElements.SILICON, MaterialElements.CARBON)
    ) {
        color = ColorUtil.mixColor(RagiColor.GRAY to 1, RagiColor.WHITE to 2).rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    @JvmField
    val ENERGETIC_ALLOY = compoundOf(
        "energetic_alloy",
        1031,
        mapOf(MaterialElements.GOLD to 1, REDSTONE to 1, GLOWSTONE to 1)
    ) {
        color = ColorUtil.mixColor(RagiColor.GOLD, RagiColor.RED).rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    @JvmField
    val VIBRANT_ALLOY = compoundOf(
        "vibrant_alloy",
        1032,
        mapOf(ENERGETIC_ALLOY.addBracket() to 1, ENDER_PEARL to 1)
    ) {
        color = ColorUtil.mixColor(RagiColor.GREEN, RagiColor.YELLOW).rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    @JvmField
    val REDSTONE_ALLOY = compoundOf(
        "redstone_alloy",
        1033,
        mapOf(MaterialElements.SILICON to 1, REDSTONE to 1)
    ) {
        color = ColorUtil.mixColor(RagiColor.DARK_RED, RagiColor.RED).rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    @JvmField
    val CONDUCTIVE_IRON = compoundOf(
        "conductive_iron",
        1034,
        mapOf(MaterialElements.IRON to 1, REDSTONE to 1)
    ) {
        color = ColorUtil.mixColor(RagiColor.RED to 1, RagiColor.WHITE to 2).rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    @JvmField
    val PULSATING_IRON = compoundOf(
        "pulsating_iron",
        1035,
        mapOf(MaterialElements.IRON to 1, ENDER_PEARL to 1)
    ) {
        color = ColorUtil.mixColor(RagiColor.GREEN to 1, RagiColor.WHITE to 2).rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    @JvmField
    val DARK_STEEL = mixtureOf(
        "dark_steel",
        1036,
        listOf(MaterialElements.IRON, MaterialElements.CARBON, MaterialCommon.OBSIDIAN)
    ) {
        color = ColorUtil.mixColor(RagiColor.BLACK to 1, RagiColor.DARK_GRAY to 3).rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    @JvmField
    val SOULARIUM = compoundOf(
        "soularium",
        1037,
        mapOf(MaterialElements.GOLD to 1, MaterialCommon.SOUL_SAND.addBracket() to 1)
    ) {
        color = ColorUtil.mixColor(RagiColor.DARK_GRAY to 5, RagiColor.GOLD to 1).rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    @JvmField
    val END_STEEL = mixtureOf(
        "end_steel",
        1038,
        listOf(DARK_STEEL, MaterialCommon.END_STONE, MaterialCommon.OBSIDIAN)
    ) {
        color = ColorUtil.mixColor(RagiColor.YELLOW to 1, RagiColor.WHITE to 3).rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    @JvmField
    val IRON_ALLOY = mixtureOf(
        "construction_alloy",
        1039,
        listOf(HiiragiMaterial.UNKNOWN, MaterialElements.IRON, HiiragiMaterial.UNKNOWN)
    ) {
        color = ColorUtil.mixColor(RagiColor.BLUE, RagiColor.GREEN, RagiColor.RED).rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_COMMON)
    }

    //    Thaumcraft    //

    @JvmField
    val THAUMIUM = materialOf("thaumium", 1040) {
        color = ColorUtil.mixColor(RagiColor.DARK_RED to 2, RagiColor.BLUE to 5, RagiColor.WHITE to 4).rgb
        crystalType = CrystalType.METAL
        formula = "✡"
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    @JvmField
    val VOID_METAL = materialOf("void", 1041) {
        color = ColorUtil.mixColor(RagiColor.BLACK to 4, RagiColor.DARK_PURPLE to 1, RagiColor.BLUE to 1).rgb
        crystalType = CrystalType.METAL
        formula = "Vm9pZA=="
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    //    Botania    //

    @JvmField
    val MANA = materialOf("mana", -1) {
        formula = "☆"
    }

    @JvmField
    val MANASTEEL = mixtureOf("manasteel", 1050, listOf(MaterialElements.IRON, MANA)) {
        color = ColorUtil.mixColor(RagiColor.DARK_BLUE to 1, RagiColor.AQUA to 1, RagiColor.WHITE to 2).rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    @JvmField
    val MANA_DIAMOND = mixtureOf("mana_diamond", 1052, listOf(MaterialCommon.DIAMOND, MANA)) {
        color = ColorUtil.mixColor(RagiColor.AQUA to 1, RagiColor.WHITE to 2).rgb
        crystalType = CrystalType.DIAMOND
        validShapes.addAll(MaterialType.GEM_9xADVANCED)
    }

    @JvmField
    val TERRASTEEL = mixtureOf("terrasteel", 1053, listOf(MaterialElements.IRON, MANA)) {
        color = ColorUtil.mixColor(RagiColor.DARK_GREEN, RagiColor.GREEN).rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    @JvmField
    val ELEMENTIUM = mixtureOf("elven_elementium", 1054, listOf(MaterialElements.IRON, MANA)) {
        color = RagiColor.LIGHT_PURPLE.rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    @JvmField
    val DRAGONSTONE = mixtureOf("elven_dragonstone", 1055, listOf(MaterialCommon.DIAMOND, MANA)) {
        color = RagiColor.LIGHT_PURPLE.rgb
        crystalType = CrystalType.DIAMOND
        validShapes.addAll(MaterialType.GEM_9xADVANCED)
    }

    //    Embers    //

    @JvmField
    val DAWNSTONE = compoundOf("dawnstone", 1060, mapOf(MaterialElements.COPPER to 1, MaterialElements.GOLD to 1)) {
        color = ColorUtil.mixColor(RagiColor.GOLD to 2, RagiColor.RED to 1, RagiColor.YELLOW to 1).rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_ADVANCED)
    }

    //    Project Red: Core    //

    @JvmField
    val ELECTROTINE = materialOf("electrotine", 1070) {
        color = ColorUtil.mixColor(RagiColor.DARK_AQUA, RagiColor.BLUE, RagiColor.AQUA).rgb
        formula = "Er"
        molar = 112.2
        tempBoil = 1201
        tempMelt = 1122
        validShapes.addAll(MaterialType.SOLID)
    }

    @JvmField
    val RED_ALLOY = compoundOf("red_alloy", 1071, mapOf(MaterialElements.IRON to 1, REDSTONE to 8)) {
        color = RagiColor.DARK_RED.rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_COMMON)
    }

    @JvmField
    val ELECTROTINE_ALLOY = compoundOf("electrotine_alloy", 1072, mapOf(MaterialElements.IRON to 1, ELECTROTINE to 8)) {
        color = ColorUtil.mixColor(RagiColor.DARK_AQUA, RagiColor.BLUE, RagiColor.AQUA).rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_COMMON)
    }

    //    Tinker's Construct    //

    @JvmField
    val ARDITE = materialOf("ardite", 1080) {
        color = ColorUtil.mixColor(RagiColor.DARK_RED to 2, RagiColor.GOLD to 1).rgb
        crystalType = CrystalType.METAL
        formula = "Ad"
        molar = 116.0
        tempBoil = 5000
        tempMelt = 3000
        validShapes.addAll(MaterialType.METAL_COMMON)
    }

    @JvmField
    val MANYULLYN = compoundOf("manyullyn", 1081, mapOf(MaterialElements.COBALT to 1, ARDITE to 1)) {
        color = ColorUtil.mixColor(RagiColor.DARK_RED to 2, RagiColor.BLUE to 5, RagiColor.WHITE to 2).rgb
        crystalType = CrystalType.METAL
        validShapes.addAll(MaterialType.METAL_COMMON)
    }

    @JvmField
    val ALUMINIUM_BRASS =
        compoundOf("aluminium_brass", 1084, mapOf(MaterialElements.ALUMINIUM to 3, MaterialElements.COPPER to 1)) {
            color = ColorUtil.mixColor(RagiColor.GOLD, RagiColor.YELLOW, RagiColor.WHITE).rgb
            crystalType = CrystalType.METAL
            oreDictAlt = "alubrass"
            validShapes.addAll(MaterialType.METAL_ADVANCED)
        }

}