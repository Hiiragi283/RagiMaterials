package hiiragi283.material.api.material

import hiiragi283.material.util.ColorUtil
import hiiragi283.material.util.RagiColor
import hiiragi283.material.util.isAprilFools
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
        mixtureOf("wood", 1010, listOf(MaterialElements.CARBON, MaterialElements.HYDROGEN, MaterialElements.OXYGEN)) {
            color = ColorUtil.mixColor(RagiColor.DARK_GRAY to 2, RagiColor.RED to 1, RagiColor.YELLOW to 1).rgb
            partsAdditional = listOf("gear", "plate")
        }

    @JvmField
    val WATER = compoundOf("water", 1011, mapOf(MaterialElements.HYDROGEN to 2, MaterialElements.OXYGEN to 1)) {
        color = RagiColor.BLUE.rgb
        tempBoil = 373
        tempMelt = 273
        if (isAprilFools()) translationKey = "material.dhmo"
    }

    //    Beryllium    //

    @JvmField
    val AQUAMARINE = compoundOf(
        "aquamarine", 1041, mapOf(
            MaterialElements.BERYLLIUM to 3,
            MaterialElements.ALUMINIUM to 2,
            MaterialElements.SILICON to 6,
            MaterialElements.OXYGEN to 16
        )
    ) {
        color = RagiColor.AQUA.rgb
        crystalType = CrystalType.EMERALD
    }

    @JvmField
    val EMERALD = compoundOf(
        "emerald", 1040, mapOf(
            MaterialElements.BERYLLIUM to 3,
            MaterialElements.ALUMINIUM to 2,
            MaterialElements.SILICON to 6,
            MaterialElements.OXYGEN to 16
        )
    ) {
        color = RagiColor.GREEN.rgb
        crystalType = CrystalType.EMERALD
    }

    //    Carbon    //

    @JvmField
    val COAL = compoundOf("coal", 1060, mapOf(MaterialElements.CARBON to 1)) {
        crystalType = CrystalType.COAL
    }

    @JvmField
    val CHARCOAL = compoundOf("charcoal", 1061, mapOf(MaterialElements.CARBON to 1)) {
        crystalType = CrystalType.COAL
    }

    @JvmField
    val COKE = compoundOf("coke", 1062, mapOf(MaterialElements.CARBON to 1)) {
        color = RagiColor.DARK_GRAY.rgb
        crystalType = CrystalType.COAL
    }

    @JvmField
    val DIAMOND = compoundOf("diamond", 1063, mapOf(MaterialElements.CARBON to 1)) {
        color = RagiColor.AQUA.rgb
        crystalType = CrystalType.DIAMOND
    }

    @JvmField
    val SUGAR = compoundOf(
        "sugar",
        1064,
        mapOf(MaterialElements.CARBON to 6, MaterialElements.HYDROGEN to 12, MaterialElements.OXYGEN to 6)
    ) {
        color = RagiColor.WHITE.rgb
    }

    //    Nitrogen    //

    @JvmField
    val NITER = compoundOf("niter", 1070, mapOf(MaterialElements.POTASSIUM to 1, NITRATE to 1)) {
        color = RagiColor.WHITE.rgb
    }


    @JvmField
    val NITRIC_ACID = compoundOf("nitric_acid", 1071, mapOf(MaterialElements.HYDROGEN to 1, NITRATE to 1)) {
        tempBoil = WATER.tempBoil
        tempMelt = WATER.tempMelt
    }

    @JvmField
    val GUNPOWDER = compoundOf(
        "gunpowder",
        1072,
        mapOf(NITER.addBracket() to 1, MaterialElements.CARBON to 1, MaterialElements.SULFUR to 1)
    ) {
        color = RagiColor.DARK_GRAY.rgb
    }

    //    Fluorine    //

    @JvmField
    val CRYOLITE = compoundOf(
        "cryolite",
        1090,
        mapOf(MaterialElements.SODIUM to 3, MaterialElements.ALUMINIUM to 1, MaterialElements.FLUORINE to 6)
    ) {
        color = RagiColor.WHITE.rgb
        crystalType = CrystalType.CUBIC
    }

    @JvmField
    val FLUORITE = compoundOf("fluorite", 1091, mapOf(MaterialElements.CALCIUM to 1, MaterialElements.FLUORINE to 2)) {
        color = ColorUtil.mixColor(RagiColor.GREEN, RagiColor.AQUA).rgb
        crystalType = CrystalType.CUBIC
    }

    @JvmField
    val HYDROGEN_FLUORIDE =
        compoundOf("hydrogen_fluoride", 1092, mapOf(MaterialElements.HYDROGEN to 1, MaterialElements.FLUORINE to 1)) {
            tempBoil = WATER.tempBoil
            tempMelt = WATER.tempMelt
        }

    //    Sodium    //

    @JvmField
    val SALT = compoundOf("salt", 1110, mapOf(MaterialElements.SODIUM to 1, MaterialElements.CHLORINE to 1)) {
        color = RagiColor.WHITE.rgb
        crystalType = CrystalType.CUBIC
    }

    //    Aluminium    //

    @JvmField
    val BAUXITE = compoundOf("bauxite", 1130, mapOf(MaterialElements.ALUMINIUM to 2, MaterialElements.OXYGEN to 3)) {
        color = ColorUtil.mixColor(RagiColor.BLACK to 1, RagiColor.DARK_RED to 2, RagiColor.GOLD to 1).rgb
    }

    @JvmField
    val RUBY = compoundOf("ruby", 1131, mapOf(MaterialElements.ALUMINIUM to 2, MaterialElements.OXYGEN to 3)) {
        color = RagiColor.RED.rgb
        crystalType = CrystalType.RUBY
    }

    @JvmField
    val SAPPHIRE = compoundOf("sapphire", 1132, mapOf(MaterialElements.ALUMINIUM to 2, MaterialElements.OXYGEN to 3)) {
        color = RagiColor.BLUE.rgb
        crystalType = CrystalType.RUBY
    }

    //    Silicon    //

    @JvmField
    val CLAY = compoundOf("clay", 1140, mapOf(BAUXITE to 1, SILICATE to 1)) {
        color = ColorUtil.mixColor(RagiColor.GRAY to 2, RagiColor.AQUA to 1).rgb
    }

    @JvmField
    val END_STONE = compoundOf("end_stone", 1141, mapOf(SILICATE to 1)) {
        color = ColorUtil.mixColor(RagiColor.YELLOW to 1, RagiColor.WHITE to 3).rgb
    }

    @JvmField
    val GLASS = compoundOf("glass", 1142, mapOf(SILICATE to 1)) {
        color = RagiColor.WHITE.rgb
        crystalType = CrystalType.RUBY
    }

    @JvmField
    val LAVA = compoundOf("lava", 1143, mapOf(SILICATE to 1)) {
        color = ColorUtil.mixColor(RagiColor.DARK_RED, RagiColor.GOLD).rgb
        tempMelt = FluidRegistry.LAVA.temperature
    }

    @JvmField
    val NETHERRACK = compoundOf("netherrack", 1144, mapOf(SILICATE to 1)) {
        color = RagiColor.DARK_RED.rgb
    }

    @JvmField
    val OBSIDIAN = compoundOf("obsidian", 1145, mapOf(SILICATE to 1)) {
        color = ColorUtil.mixColor(RagiColor.BLACK to 2, RagiColor.BLUE to 1, RagiColor.RED to 1).rgb
    }

    @JvmField
    val QUARTZ = compoundOf("quartz", 1146, mapOf(SILICATE to 1)) {
        color = RagiColor.WHITE.rgb
        crystalType = CrystalType.QUARTZ
    }

    @JvmField
    val SOUL_SAND = compoundOf("soul_sand", 1147, mapOf(SILICATE to 1)) {
        color = ColorUtil.mixColor(RagiColor.BLACK to 5, RagiColor.GOLD to 1).rgb
    }

    @JvmField
    val STONE = compoundOf("stone", 1148, mapOf(SILICATE to 1)) {
        color = RagiColor.GRAY.rgb
        partsAdditional = listOf("gear", "plate", "stick")
    }

    //    Sulfur    //

    val SULFURIC_ACID = compoundOf("sulfuric_acid", 1160, mapOf(MaterialElements.HYDROGEN to 2, SULFATE to 1)) {
        color = ColorUtil.mixColor(RagiColor.GOLD, RagiColor.YELLOW).rgb
        tempBoil = WATER.tempBoil
        tempMelt = WATER.tempMelt
    }

    //    Chlorine    //

    @JvmField
    val HYDROGEN_CHLORIDE =
        compoundOf("hydrogen_chloride", 1170, mapOf(MaterialElements.HYDROGEN to 1, MaterialElements.CHLORINE to 1)) {
            tempBoil = WATER.tempBoil
            tempMelt = WATER.tempMelt
        }

    //    Calcium    //

    @JvmField
    val APATITE =
        compoundOf("apatite", 1200, mapOf(MaterialElements.CALCIUM to 5, PHOSPHATE.addBracket() to 3, HYDROXIDE to 1)) {
            color = ColorUtil.mixColor(RagiColor.YELLOW, RagiColor.WHITE).rgb
            crystalType = CrystalType.EMERALD
        }

    @JvmField
    val GYPSUM = compoundOf("gypsum", 1201, mapOf(MaterialElements.CALCIUM to 1, SULFATE to 1)) {
        crystalType = CrystalType.CUBIC
    }

    @JvmField
    val LIME = compoundOf("lime", 1202, mapOf(MaterialElements.CALCIUM to 1, CARBONATE to 1)) {
        color = RagiColor.WHITE.rgb
    }

    //    Titanium    //

    @JvmField
    val RUTILE = compoundOf("rutile", 1220, mapOf(MaterialElements.TITANIUM to 1, MaterialElements.OXYGEN to 2)) {
        color = RagiColor.YELLOW.rgb
        crystalType = CrystalType.QUARTZ
    }

    //    Chromium    //

    @JvmField
    val STAINLESS_STEEL = compoundOf(
        "stainless_steel", 1240, mapOf(
            MaterialElements.IRON to 6,
            MaterialElements.CHROMIUM to 1,
            MaterialElements.MANGANESE to 1,
            MaterialElements.NICKEL to 1
        )
    )
    {
        color = ColorUtil.mixColor(RagiColor.GRAY, RagiColor.WHITE).rgb
        crystalType = CrystalType.METAL
    }

    //    Iron    //

    @JvmField
    val STEEL = mixtureOf("steel", 1260, listOf(MaterialElements.IRON, MaterialElements.CARBON)) {
        color = RagiColor.GRAY.rgb
        crystalType = CrystalType.METAL
    }

    //    Nickel    //

    @JvmField
    val CONSTANTAN = compoundOf("constantan", 1280, mapOf(MaterialElements.NICKEL to 1, MaterialElements.COPPER to 1)) {
        crystalType = CrystalType.METAL
    }

    @JvmField
    val INVAR = compoundOf("invar", 1281, mapOf(MaterialElements.NICKEL to 2, MaterialElements.IRON to 1)) {
        color = ColorUtil.mixColor(
            RagiColor.GREEN to 1,
            RagiColor.GRAY to 3,
            RagiColor.WHITE to 4
        ).rgb
        crystalType = CrystalType.METAL
    }

    //    Copper    //

    @JvmField
    val BRASS = compoundOf("brass", 1290, mapOf(MaterialElements.COPPER to 3, MaterialElements.ZINC to 1)) {
        color = RagiColor.GOLD.rgb
        crystalType = CrystalType.METAL
    }

    @JvmField
    val BRONZE = compoundOf("bronze", 1291, mapOf(MaterialElements.COPPER to 3, MaterialElements.TIN to 1)) {
        crystalType = CrystalType.METAL
    }

    //    Silver    //

    val ELECTRUM = compoundOf("electrum", 1470, mapOf(MaterialElements.SILVER to 1, MaterialElements.GOLD to 1)) {
        color = ColorUtil.mixColor(RagiColor.GOLD, RagiColor.YELLOW, RagiColor.WHITE).rgb
        crystalType = CrystalType.METAL
    }

    //    Other    //

    @JvmField
    val REDSTONE = materialOf("redstone", 200) {
        color = RagiColor.DARK_RED.rgb
        crystalType = CrystalType.EMERALD
        formula = "Rs"
        molar = 112.2
        tempBoil = 1201
        tempMelt = 1122
    }

    @JvmField
    val LAPIS = materialOf("lapis", 201) {
        color = RagiColor.BLUE.rgb
        crystalType = CrystalType.LAPIS
    }

    @JvmField
    val GLOWSTONE = materialOf("glowstone", 202) {
        color = ColorUtil.mixColor(RagiColor.GOLD, RagiColor.YELLOW).rgb
        crystalType = CrystalType.EMERALD
        formula = "Gl"
        molar = 112.2
        tempBoil = 1201
        tempMelt = 1122
    }

    @JvmField
    val ENDER = materialOf("ender", 203) {
        color = ColorUtil.mixColor(RagiColor.DARK_GREEN to 1, RagiColor.BLUE to 1).rgb
        crystalType = CrystalType.EMERALD
        formula = "En"
        molar = 112.2
        tempBoil = 1201
        tempMelt = 1122
    }

    fun init() {
        val fields = this::class.java.declaredFields
        fields.forEach { it.isAccessible = true }
        fields.map { it.get(this) }
            .filterIsInstance<HiiragiMaterial>()
            .forEach { MaterialRegistry.registerMaterial(it) }
    }

}