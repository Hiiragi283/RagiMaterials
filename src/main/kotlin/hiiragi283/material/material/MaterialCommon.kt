package hiiragi283.material.material

import hiiragi283.material.util.ColorUtil
import hiiragi283.material.util.RagiColor
import net.minecraftforge.fluids.FluidRegistry

object MaterialCommon {

    @JvmField
    val HYDROXIDE = HiiragiMaterial.Builder("hydroxide", -1)
        .build(MaterialElements.OXYGEN to 1, MaterialElements.HYDROGEN to 1)

    @JvmField
    val CARBONATE = HiiragiMaterial.Builder("carbonate", -1)
        .build(MaterialElements.CARBON to 1, MaterialElements.OXYGEN to 3)

    @JvmField
    val NITRATE = HiiragiMaterial.Builder("nitrate", -1)
        .build(MaterialElements.NITROGEN to 1, MaterialElements.OXYGEN to 3)

    @JvmField
    val SILICATE = HiiragiMaterial.Builder("silicate", -1)
        .build(MaterialElements.SILICON to 1, MaterialElements.OXYGEN to 2)

    @JvmField
    val PHOSPHATE = HiiragiMaterial.Builder("phosphate", -1)
        .build(MaterialElements.PHOSPHORUS to 1, MaterialElements.OXYGEN to 3)

    @JvmField
    val SULFATE = HiiragiMaterial.Builder("sulfate", -1)
        .build(MaterialElements.SULFUR to 1, MaterialElements.OXYGEN to 4)

    //    Hydrogen    //

    @JvmField
    val WOOD = HiiragiMaterial.Builder("wood", 1010).build {
        color = ColorUtil.mixColor(RagiColor.DARK_GRAY to 2, RagiColor.RED to 1, RagiColor.YELLOW to 1).rgb
        partsAdditional = listOf("gear", "plate")
    }

    @JvmField
    val WATER = HiiragiMaterial.Builder("water", 1011)
        .build(MaterialElements.HYDROGEN to 2, MaterialElements.OXYGEN to 1) {
            color = RagiColor.BLUE.rgb
            tempBoil = 373
            tempMelt = 273
        }

    //    Beryllium    //

    @JvmField
    val AQUAMARINE = HiiragiMaterial.Builder("aquamarine", 1041)
        .build(
            MaterialElements.BERYLLIUM to 3,
            MaterialElements.ALUMINIUM to 2,
            MaterialElements.SILICON to 6,
            MaterialElements.OXYGEN to 16
        ) {
            color = RagiColor.AQUA.rgb
            crystalType = CrystalType.EMERALD
        }

    @JvmField
    val EMERALD = HiiragiMaterial.Builder("emerald", 1040)
        .build(
            MaterialElements.BERYLLIUM to 3,
            MaterialElements.ALUMINIUM to 2,
            MaterialElements.SILICON to 6,
            MaterialElements.OXYGEN to 16
        ) {
            color = RagiColor.GREEN.rgb
            crystalType = CrystalType.EMERALD
        }

    //    Carbon    //

    @JvmField
    val COAL = HiiragiMaterial.Builder("coal", 1060)
        .build(MaterialElements.CARBON to 1) {
            crystalType = CrystalType.COAL
        }

    @JvmField
    val CHARCOAL = HiiragiMaterial.Builder("charcoal", 1061)
        .build(MaterialElements.CARBON to 1) {
            crystalType = CrystalType.COAL
        }

    @JvmField
    val COKE = HiiragiMaterial.Builder("coke", 1062)
        .build(MaterialElements.CARBON to 1) {
            color = RagiColor.DARK_GRAY.rgb
            crystalType = CrystalType.COAL
        }

    @JvmField
    val DIAMOND = HiiragiMaterial.Builder("diamond", 1063)
        .build(MaterialElements.CARBON to 1) {
            color = RagiColor.AQUA.rgb
            crystalType = CrystalType.DIAMOND
        }

    //    Nitrogen    //

    @JvmField
    val NITER = HiiragiMaterial.Builder("niter", 1070)
        .build(MaterialElements.POTASSIUM to 1, NITRATE to 1) {
            color = RagiColor.WHITE.rgb
        }


    @JvmField
    val NITRIC_ACID = HiiragiMaterial.Builder("nitric_acid", 1071)
        .build(MaterialElements.HYDROGEN to 1, NITRATE to 1) {
            tempBoil = WATER.tempBoil
            tempMelt = WATER.tempMelt
        }

    //    Fluorine    //

    @JvmField
    val CRYOLITE = HiiragiMaterial.Builder("cryolite", 1090)
        .build(
            MaterialElements.SODIUM to 3,
            MaterialElements.ALUMINIUM to 1,
            MaterialElements.FLUORINE to 6
        ) {
            color = RagiColor.WHITE.rgb
            crystalType = CrystalType.CUBIC
        }

    @JvmField
    val FLUORITE = HiiragiMaterial.Builder("fluorite", 1091)
        .build(MaterialElements.CALCIUM to 1, MaterialElements.FLUORINE to 2) {
            color = ColorUtil.mixColor(RagiColor.GREEN, RagiColor.AQUA).rgb
            crystalType = CrystalType.CUBIC
        }

    @JvmField
    val HYDROGEN_FLUORIDE = HiiragiMaterial.Builder("hydrogen_fluoride", 1092)
        .build(MaterialElements.HYDROGEN to 1, MaterialElements.FLUORINE to 1) {
            tempBoil = WATER.tempBoil
            tempMelt = WATER.tempMelt
        }

    //    Sodium    //

    @JvmField
    val SALT = HiiragiMaterial.Builder("salt", 1110)
        .build(MaterialElements.SODIUM to 1, MaterialElements.CHLORINE to 1) {
            color = RagiColor.WHITE.rgb
            crystalType = CrystalType.CUBIC
        }

    //    Aluminium    //

    @JvmField
    val BAUXITE = HiiragiMaterial.Builder("bauxite", 1130)
        .build(MaterialElements.ALUMINIUM to 2, MaterialElements.OXYGEN to 3) {
            color = ColorUtil.mixColor(RagiColor.BLACK to 1, RagiColor.DARK_RED to 2, RagiColor.GOLD to 1).rgb
        }

    @JvmField
    val RUBY = HiiragiMaterial.Builder("ruby", 1131)
        .build(MaterialElements.ALUMINIUM to 2, MaterialElements.OXYGEN to 3) {
            color = RagiColor.RED.rgb
            crystalType = CrystalType.RUBY
        }

    @JvmField
    val SAPPHIRE = HiiragiMaterial.Builder("sapphire", 1132)
        .build(MaterialElements.ALUMINIUM to 2, MaterialElements.OXYGEN to 3) {
            color = RagiColor.BLUE.rgb
            crystalType = CrystalType.RUBY
        }

    //    Silicon    //

    @JvmField
    val CLAY = HiiragiMaterial.Builder("clay", 1140)
        .build(BAUXITE to 1, SILICATE to 1) {
            color = ColorUtil.mixColor(RagiColor.GRAY to 2, RagiColor.AQUA to 1).rgb
        }

    @JvmField
    val END_STONE = HiiragiMaterial.Builder("end_stone", 1141)
        .build(SILICATE to 1) {
            color = ColorUtil.mixColor(RagiColor.YELLOW to 1, RagiColor.WHITE to 3).rgb
        }

    @JvmField
    val GLASS = HiiragiMaterial.Builder("glass", 1142)
        .build(SILICATE to 1) {
            color = RagiColor.WHITE.rgb
            crystalType = CrystalType.RUBY
        }

    @JvmField
    val LAVA = HiiragiMaterial.Builder("lava", 1143)
        .build(SILICATE to 1) {
            color = ColorUtil.mixColor(RagiColor.DARK_RED, RagiColor.GOLD).rgb
            tempMelt = FluidRegistry.LAVA.temperature
        }

    @JvmField
    val NETHERRACK = HiiragiMaterial.Builder("netherrack", 1144)
        .build(SILICATE to 1) {
            color = RagiColor.DARK_RED.rgb
        }

    @JvmField
    val OBSIDIAN = HiiragiMaterial.Builder("obsidian", 1145)
        .build(SILICATE to 1) {
            color = ColorUtil.mixColor(RagiColor.BLACK to 2, RagiColor.BLUE to 1, RagiColor.RED to 1).rgb
        }

    @JvmField
    val QUARTZ = HiiragiMaterial.Builder("quartz", 1146)
        .build(SILICATE to 1) {
            color = RagiColor.WHITE.rgb
            crystalType = CrystalType.QUARTZ
        }

    @JvmField
    val SOUL_SAND = HiiragiMaterial.Builder("soul_sand", 1147)
        .build(SILICATE to 1) {
            color = ColorUtil.mixColor(RagiColor.BLACK to 5, RagiColor.GOLD to 1).rgb
        }

    @JvmField
    val STONE = HiiragiMaterial.Builder("stone", 1148)
        .build(SILICATE to 1) {
            color = RagiColor.GRAY.rgb
            partsAdditional = listOf("gear", "plate", "stick")
        }

    //    Sulfur    //

    val SULFURIC_ACID = HiiragiMaterial.Builder("sulfuric_acid", 1160)
        .build(MaterialElements.HYDROGEN to 2, SULFATE to 1) {
            color = ColorUtil.mixColor(RagiColor.GOLD, RagiColor.YELLOW).rgb
            tempBoil = WATER.tempBoil
            tempMelt = WATER.tempMelt
        }

    //    Chlorine    //

    @JvmField
    val HYDROGEN_CHLORIDE = HiiragiMaterial.Builder("hydrogen_chloride", 1170)
        .build(MaterialElements.HYDROGEN to 1, MaterialElements.CHLORINE to 1) {
            tempBoil = WATER.tempBoil
            tempMelt = WATER.tempMelt
        }

    //    Calcium    //

    @JvmField
    val APATITE = HiiragiMaterial.Builder("apatite", 1200)
        .build(
            MaterialElements.CALCIUM to 5,
            PHOSPHATE.addBracket() to 3,
            HYDROXIDE to 1
        ) {
            color = ColorUtil.mixColor(RagiColor.YELLOW, RagiColor.WHITE).rgb
            crystalType = CrystalType.EMERALD
        }

    @JvmField
    val GYPSUM = HiiragiMaterial.Builder("gypsum", 1201)
        .build(MaterialElements.CALCIUM to 1, SULFATE to 1) {
            crystalType = CrystalType.CUBIC
        }

    @JvmField
    val LIME = HiiragiMaterial.Builder("lime", 1202)
        .build(
            MaterialElements.CALCIUM to 1,
            CARBONATE to 1
        ) {
            color = RagiColor.WHITE.rgb
        }

    //    Titanium    //

    @JvmField
    val RUTILE = HiiragiMaterial.Builder("rutile", 1220)
        .build(MaterialElements.TITANIUM to 1, MaterialElements.OXYGEN to 2) {
            color = RagiColor.YELLOW.rgb
            crystalType = CrystalType.QUARTZ
        }

    //    Chromium    //

    @JvmField
    val STAINLESS_STEEL = HiiragiMaterial.Builder("stainless_steel", 1240)
        .build(
            MaterialElements.IRON to 6,
            MaterialElements.CHROMIUM to 1,
            MaterialElements.MANGANESE to 1,
            MaterialElements.NICKEL to 1
        ) {
            color = ColorUtil.mixColor(RagiColor.GRAY, RagiColor.WHITE).rgb
            crystalType = CrystalType.METAL
        }

    //    Iron    //

    @JvmField
    val STEEL = HiiragiMaterial.Builder("steel", 1260)
        .build(MaterialElements.IRON to 1, MaterialElements.CARBON to 4) {
            color = RagiColor.GRAY.rgb
            crystalType = CrystalType.METAL
        }

    //    Nickel    //

    @JvmField
    val CONSTANTAN = HiiragiMaterial.Builder("constantan", 1280)
        .build(MaterialElements.NICKEL to 1, MaterialElements.COPPER to 1) {
            crystalType = CrystalType.METAL
        }

    @JvmField
    val INVAR = HiiragiMaterial.Builder("invar", 1281)
        .build(MaterialElements.NICKEL to 2, MaterialElements.IRON to 1) {
            color = ColorUtil.mixColor(
                RagiColor.GREEN to 1,
                RagiColor.GRAY to 3,
                RagiColor.WHITE to 4
            ).rgb
            crystalType = CrystalType.METAL
        }

    //    Copper    //

    @JvmField
    val BRASS = HiiragiMaterial.Builder("brass", 1290)
        .build(MaterialElements.COPPER to 3, MaterialElements.ZINC to 1) {
            color = RagiColor.GOLD.rgb
            crystalType = CrystalType.METAL
        }

    @JvmField
    val BRONZE = HiiragiMaterial.Builder("bronze", 1291)
        .build(MaterialElements.COPPER to 3, MaterialElements.TIN to 1) {
            crystalType = CrystalType.METAL
        }

    //    Silver    //

    val ELECTRUM = HiiragiMaterial.Builder("electrum", 1470)
        .build(MaterialElements.SILVER to 1, MaterialElements.GOLD to 1) {
            color = ColorUtil.mixColor(RagiColor.GOLD, RagiColor.YELLOW, RagiColor.WHITE).rgb
            crystalType = CrystalType.METAL
        }

    //    Other    //

    @JvmField
    val REDSTONE = HiiragiMaterial.Builder("redstone", 200)
        .build {
            color = RagiColor.DARK_RED.rgb
            crystalType = CrystalType.EMERALD
            formula = "Rs"
            molar = 112.2
            tempBoil = 1201
            tempMelt = 1122
        }

    @JvmField
    val LAPIS = HiiragiMaterial.Builder("lapis", 201)
        .build {
            color = RagiColor.BLUE.rgb
            crystalType = CrystalType.LAPIS
        }

    @JvmField
    val GLOWSTONE = HiiragiMaterial.Builder("glowstone", 202)
        .build {
            color = ColorUtil.mixColor(RagiColor.GOLD, RagiColor.YELLOW).rgb
            crystalType = CrystalType.EMERALD
            formula = "Gl"
            molar = 112.2
            tempBoil = 1201
            tempMelt = 1122
        }

    @JvmField
    val ENDER = HiiragiMaterial.Builder("ender", 203)
        .build {
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