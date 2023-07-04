package hiiragi283.material.api.material

import hiiragi283.material.common.util.ColorUtil
import hiiragi283.material.common.util.RagiColor

object MaterialCommon {

    //    Atomic Groups    //

    @JvmStatic
    val HYDROXIDE = HiiragiMaterial.Builder("hydroxide", MaterialType.INTERNAL)
        .build(MaterialElements.OXYGEN to 1, MaterialElements.HYDROGEN to 1)

    @JvmField
    val CARBONATE = HiiragiMaterial.Builder("carbonate", MaterialType.INTERNAL)
        .build(MaterialElements.CARBON to 1, MaterialElements.OXYGEN to 3)

    @JvmField
    val NITRATE = HiiragiMaterial.Builder("nitrate", MaterialType.INTERNAL)
        .build(MaterialElements.NITROGEN to 1, MaterialElements.OXYGEN to 3)

    @JvmField
    val SILICATE = HiiragiMaterial.Builder("silicate", MaterialType.INTERNAL)
        .build(MaterialElements.SILICON to 1, MaterialElements.OXYGEN to 2)

    @JvmField
    val PHOSPHATE = HiiragiMaterial.Builder("phosphate", MaterialType.INTERNAL)
        .build(MaterialElements.PHOSPHORUS to 1, MaterialElements.OXYGEN to 3)

    @JvmField
    val SULFATE = HiiragiMaterial.Builder("sulfate", MaterialType.INTERNAL)
        .build(MaterialElements.SULFUR to 1, MaterialElements.OXYGEN to 4)

    @JvmField
    val TUNGSTATE = HiiragiMaterial.Builder("tungstate", MaterialType.INTERNAL)
        .build(MaterialElements.TUNGSTEN to 1, MaterialElements.OXYGEN to 4)

    //    Mineral    //

    @JvmStatic
    val STONE = HiiragiMaterial.Builder("stone", MaterialType.STONE)
        .build(SILICATE to 1) {
            color = RagiColor.GRAY.rgb
        }

    @JvmStatic
    val CALCITE = HiiragiMaterial.Builder("calcite", MaterialType.OTHER)
        .build(MaterialElements.CALCIUM to 1, CARBONATE to 1) {
            color = RagiColor.WHITE.rgb
        }

    @JvmStatic
    val COAL = HiiragiMaterial.Builder("coal", MaterialType.FUEL)
        .build(MaterialElements.CARBON to 1) {
            crystalType = CrystalType.COAL
        }

    @JvmField
    val REDSTONE = HiiragiMaterial.Builder("redstone", MaterialType.GEM)
        .build {
            color = RagiColor.DARK_RED.rgb
            crystalType = CrystalType.EMERALD
            formula = "Rs"
            molar = 112.2
            tempBoil = 1201
            tempMelt = 1122
        }

    @JvmField
    val EMERALD = HiiragiMaterial.Builder("emerald", MaterialType.GEM)
        .build(
            MaterialElements.BERYLLIUM to 3,
            MaterialElements.ALUMINIUM to 2,
            MaterialElements.SILICON to 6,
            MaterialElements.OXYGEN to 16
        ) {
            color = RagiColor.GREEN.rgb
            crystalType = CrystalType.EMERALD
        }

    @JvmField
    val AQUAMARINE = HiiragiMaterial.Builder("aquamarine", MaterialType.GEM)
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
    val LAPIS = HiiragiMaterial.Builder("lapis", MaterialType.GEM)
        .build {
            color = RagiColor.BLUE.rgb
            crystalType = CrystalType.LAPIS
        }

    @JvmField
    val DIAMOND = HiiragiMaterial.Builder("diamond", MaterialType.GEM)
        .build(MaterialElements.CARBON to 1) {
            color = RagiColor.AQUA.rgb
            crystalType = CrystalType.DIAMOND
        }

    @JvmField
    val QUARTZ = HiiragiMaterial.Builder("quartz", MaterialType.GEM)
        .build(SILICATE to 1) {
            color = RagiColor.WHITE.rgb
            crystalType = CrystalType.QUARTZ
        }

    @JvmField
    val NETHERITE = HiiragiMaterial.Builder("netherite", MaterialType.METAL)
        .build(MaterialElements.IRON to 1, TUNGSTATE to 1) {
            color = ColorUtil.mixColor(RagiColor.BLACK, RagiColor.GRAY).rgb
        }

    @JvmField
    val AMETHYST = HiiragiMaterial.Builder("amethyst", MaterialType.GEM)
        .build(MaterialElements.IRON to 1, TUNGSTATE to 1) {
            color = ColorUtil.mixColor(RagiColor.BLUE, RagiColor.DARK_PURPLE, RagiColor.WHITE).rgb
            crystalType = CrystalType.QUARTZ
        }

    @JvmField
    val OBSIDIAN = HiiragiMaterial.Builder("obsidian", MaterialType.OTHER)
        .build(SILICATE to 1) {
            color = ColorUtil.mixColor(RagiColor.BLACK to 2, RagiColor.BLUE to 1, RagiColor.RED to 1).rgb
        }

    @JvmField
    val CLAY = HiiragiMaterial.Builder("clay", MaterialType.OTHER)
        .build(MaterialElements.ALUMINIUM to 1, SILICATE to 1) {
            color = ColorUtil.mixColor(RagiColor.GRAY to 2, RagiColor.AQUA to 1).rgb
        }

    @JvmField
    val NETHERRACK = HiiragiMaterial.Builder("netherrack", MaterialType.OTHER)
        .build(SILICATE to 1) {
            color = RagiColor.DARK_RED.rgb
        }

    @JvmField
    val GLOWSTONE = HiiragiMaterial.Builder("glowstone", MaterialType.GEM)
        .build {
            color = ColorUtil.mixColor(RagiColor.GOLD, RagiColor.YELLOW).rgb
            crystalType = CrystalType.EMERALD
            formula = "Gl"
            molar = 112.2
            tempBoil = 1201
            tempMelt = 1122
        }

    @JvmField
    val END_STONE = HiiragiMaterial.Builder("end_stone", MaterialType.OTHER)
        .build(SILICATE to 1) {
            color = ColorUtil.mixColor(RagiColor.YELLOW to 1, RagiColor.WHITE to 3).rgb
        }

    //    Artifact    //

    @JvmField
    val GLASS = HiiragiMaterial.Builder("glass", MaterialType.GEM)
        .build(SILICATE to 1) {
            color = RagiColor.WHITE.rgb
            crystalType = CrystalType.RUBY
        }

    //    Plant & Tree    //

    @JvmStatic
    val WOOD = HiiragiMaterial.Builder("wood", MaterialType.OTHER)
        .build {
            color = ColorUtil.mixColor(RagiColor.DARK_GRAY to 2, RagiColor.RED to 1, RagiColor.YELLOW to 1).rgb
        }

    fun load() {
        STONE.register()
        CALCITE.register()
        COAL.register()
        REDSTONE.register()
        EMERALD.register()
        AQUAMARINE.register()
        LAPIS.register()
        DIAMOND.register()
        QUARTZ.register()
        NETHERITE.register()
        AMETHYST.register()
        OBSIDIAN.register()
        CLAY.register()
        NETHERRACK.register()
        GLOWSTONE.register()
        END_STONE.register()

        GLASS.register()

        WOOD.register()
    }

}