package hiiragi283.material.api.material

import hiiragi283.material.config.enableMek
import hiiragi283.material.config.enableTE
import hiiragi283.material.util.ColorUtil
import hiiragi283.material.util.RagiColor

object MaterialIntegration {

    //    Thermal Series    //

    @JvmField
    val MITHRIL = materialOf("mithril", 1100) {
        color = ColorUtil.mixColor(RagiColor.BLUE, RagiColor.AQUA, RagiColor.WHITE).rgb
        crystalType = CrystalType.METAL
    }

    @JvmField
    val SIGNALUM = compoundOf(
        "signalum",
        1101,
        mapOf(MaterialElements.COPPER to 4, MaterialElements.GOLD to 1, MaterialCommon.REDSTONE to 10)
    ) {
        color = ColorUtil.mixColor(RagiColor.DARK_RED, RagiColor.GOLD).rgb
        crystalType = CrystalType.METAL
    }

    @JvmField
    val LUMIUM = compoundOf(
        "lumium",
        1102,
        mapOf(MaterialElements.TIN to 4, MaterialElements.SILVER to 1, MaterialCommon.GLOWSTONE to 10)
    ) {
        color = ColorUtil.mixColor(RagiColor.YELLOW to 1, RagiColor.WHITE to 2).rgb
        crystalType = CrystalType.METAL
    }

    @JvmField
    val ENDERIUM = compoundOf(
        "enderium",
        1103,
        mapOf(MaterialElements.LEAD to 4, MaterialElements.PLATINUM to 1, MaterialCommon.ENDER to 4)
    ) {
        color = ColorUtil.mixColor(RagiColor.DARK_GREEN to 1, RagiColor.BLUE to 1).rgb
        crystalType = CrystalType.METAL
    }

    @JvmField
    val PYROTHEUM = compoundOf(
        "pyrotheum",
        1104,
        mapOf(HiiragiMaterial.UNKNOWN to 2, MaterialCommon.REDSTONE to 1, MaterialElements.SULFUR to 1)
    ) {
        color = ColorUtil.mixColor(RagiColor.YELLOW, RagiColor.WHITE).rgb
    }

    @JvmField
    val CRYOTHEUM = compoundOf(
        "cryotheum",
        1105,
        mapOf(HiiragiMaterial.UNKNOWN to 2, MaterialCommon.REDSTONE to 1, MaterialCommon.WATER.addBracket() to 1)
    ) {
        color = ColorUtil.mixColor(RagiColor.AQUA, RagiColor.WHITE).rgb
    }

    @JvmField
    val AEROTHEUM = compoundOf(
        "aerotheum",
        1106,
        mapOf(HiiragiMaterial.UNKNOWN to 2, MaterialCommon.REDSTONE to 1, MaterialCommon.NITER.addBracket() to 1)
    ) {
        color = ColorUtil.mixColor(RagiColor.GREEN, RagiColor.YELLOW, RagiColor.WHITE).rgb
    }

    @JvmField
    val PETROTHEUM = compoundOf(
        "petrotheum",
        1107,
        mapOf(HiiragiMaterial.UNKNOWN to 2, MaterialCommon.REDSTONE to 1, MaterialCommon.OBSIDIAN.addBracket() to 1)
    ) {
        color =
            ColorUtil.mixColor(RagiColor.BLACK to 4, RagiColor.GRAY to 2, RagiColor.BLUE to 1, RagiColor.RED to 1).rgb
    }

    //    Mekanism    //

    @JvmField
    val OBSIDIAN_REFINED = compoundOf(
        "refined_obsidian",
        1200,
        mapOf(MaterialCommon.OBSIDIAN.addBracket() to 1, MaterialElements.OSMIUM to 1, MaterialCommon.DIAMOND to 1)
    ) {
        color = ColorUtil.mixColor(RagiColor.DARK_RED to 2, RagiColor.BLUE to 5, RagiColor.WHITE to 2).rgb
        crystalType = CrystalType.METAL
    }

    @JvmField
    val GLOWSTONE_REFINED = compoundOf(
        "refined_glowstone",
        1201,
        mapOf(MaterialCommon.GLOWSTONE to 1, MaterialElements.OSMIUM to 1)
    ) {
        color = ColorUtil.mixColor(RagiColor.YELLOW, RagiColor.WHITE).rgb
        crystalType = CrystalType.METAL
    }

    fun init() {
        if (enableTE()) {
            MaterialRegistry.registerMaterial(MITHRIL)
            MaterialRegistry.registerMaterial(SIGNALUM)
            MaterialRegistry.registerMaterial(LUMIUM)
            MaterialRegistry.registerMaterial(ENDERIUM)
            MaterialRegistry.registerMaterial(PYROTHEUM)
            MaterialRegistry.registerMaterial(CRYOTHEUM)
            MaterialRegistry.registerMaterial(AEROTHEUM)
            MaterialRegistry.registerMaterial(PETROTHEUM)
        }
        if (enableMek()) {
            MaterialRegistry.registerMaterial(OBSIDIAN_REFINED)
            MaterialRegistry.registerMaterial(GLOWSTONE_REFINED)
        }
    }

}