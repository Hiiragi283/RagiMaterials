package hiiragi283.material.api.material

import hiiragi283.material.common.util.ColorUtil
import hiiragi283.material.common.util.RagiColor

object MaterialCommon {

    //    Atomic Groups    //

    @JvmStatic
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

    @JvmField
    val TUNGSTATE = compoundOf("tungstate", mapOf(MaterialElements.TUNGSTEN to 1, MaterialElements.OXYGEN to 4))

    //    Mineral    //

    @JvmStatic
    val STONE = compoundOf("stone", mapOf(SILICATE to 1)) {
        color = RagiColor.GRAY.rgb
    }

    @JvmStatic
    val CALCITE = compoundOf("calcite", mapOf(MaterialElements.CALCIUM to 1, CARBONATE to 1)) {
        color = RagiColor.WHITE.rgb
    }

    @JvmStatic
    val COAL = compoundOf("coal", mapOf(MaterialElements.CARBON to 1)) {
        crystalType = CrystalType.COAL
    }

    @JvmField
    val REDSTONE = materialOf("redstone") {
        color = RagiColor.DARK_RED.rgb
        crystalType = CrystalType.EMERALD
        formula = "Rs"
        molar = 112.2
        tempBoil = 1201
        tempMelt = 1122
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
    }

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
    }

    @JvmField
    val LAPIS = materialOf("lapis") {
        color = RagiColor.BLUE.rgb
        crystalType = CrystalType.LAPIS
    }

    @JvmField
    val DIAMOND = compoundOf("diamond", mapOf(MaterialElements.CARBON to 1)) {
        color = RagiColor.AQUA.rgb
        crystalType = CrystalType.DIAMOND
    }

    @JvmField
    val QUARTZ = compoundOf("quartz", mapOf(SILICATE to 1)) {
        color = RagiColor.WHITE.rgb
        crystalType = CrystalType.QUARTZ
    }

    @JvmField
    val NETHERITE = compoundOf("netherite", mapOf(MaterialElements.IRON to 1, TUNGSTATE to 1)) {
        color = ColorUtil.mixColor(RagiColor.BLACK, RagiColor.GRAY).rgb
    }

    @JvmField
    val AMETHYST = compoundOf("amethyst", mapOf(SILICATE to 1)) {
        color = ColorUtil.mixColor(RagiColor.BLUE, RagiColor.DARK_PURPLE, RagiColor.WHITE).rgb
        crystalType = CrystalType.QUARTZ
    }

    @JvmField
    val OBSIDIAN = compoundOf("obsidian", mapOf(SILICATE to 1)) {
        color = ColorUtil.mixColor(RagiColor.BLACK to 2, RagiColor.BLUE to 1, RagiColor.RED to 1).rgb
    }

    @JvmField
    val CLAY = mixtureOf("clay", listOf(MaterialElements.ALUMINIUM, SILICATE)) {
        color = ColorUtil.mixColor(RagiColor.GRAY to 2, RagiColor.AQUA to 1).rgb
    }

    @JvmField
    val NETHERRACK = compoundOf("netherrack", mapOf(SILICATE to 1)) {
        color = RagiColor.DARK_RED.rgb
    }

    @JvmField
    val GLOWSTONE = materialOf("glowstone") {
        color = ColorUtil.mixColor(RagiColor.GOLD, RagiColor.YELLOW).rgb
        crystalType = CrystalType.EMERALD
        formula = "Gl"
        molar = 112.2
        tempBoil = 1201
        tempMelt = 1122
    }

    @JvmField
    val END_STONE = compoundOf("end_stone", mapOf(SILICATE to 1)) {
        color = ColorUtil.mixColor(RagiColor.YELLOW to 1, RagiColor.WHITE to 3).rgb
    }

    //    Artifact    //

    @JvmField
    val GLASS = compoundOf("glass", mapOf(SILICATE to 1)) {
        color = RagiColor.WHITE.rgb
        crystalType = CrystalType.RUBY
    }

    //    Plant & Tree    //

    @JvmStatic
    val WOOD = mixtureOf("wood", listOf(MaterialElements.CARBON, MaterialElements.HYDROGEN, MaterialElements.OXYGEN)) {
        color = ColorUtil.mixColor(RagiColor.DARK_GRAY to 2, RagiColor.RED to 1, RagiColor.YELLOW to 1).rgb
    }

    fun init() {
        this::class.java.declaredFields
            .map { it.also { it.isAccessible = true } }
            .map { it.get(this) }
            .filterIsInstance<HiiragiMaterial>()
            .forEach(MaterialRegistry::registerMaterial)
    }

}