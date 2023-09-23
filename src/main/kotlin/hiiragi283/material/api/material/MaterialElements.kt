package hiiragi283.material.api.material

import hiiragi283.material.api.shape.HiiragiShapeTypes
import hiiragi283.material.config.RMConfig
import hiiragi283.material.util.HiiragiColor

/**
 * @author MrKono
 * @author turtton
 */
object MaterialElements {

    //    1st Period    //

    @JvmField
    val HYDROGEN = materialOf("hydrogen", 1) {
        color = HiiragiColor.BLUE.rgb
        formula = "H"
        molar = 1.0
        shapeType = HiiragiShapeTypes.GAS
        tempBoil = 20
        tempMelt = 14
    }

    @JvmField
    val HELIUM = materialOf("helium", 2) {
        color = HiiragiColor.YELLOW.rgb
        formula = "He"
        molar = 4.0
        shapeType = HiiragiShapeTypes.GAS
        tempBoil = 3
        tempMelt = 1
    }

    //    2nd Period    //

    @JvmField
    val LITHIUM = materialOf("lithium", 3) {
        color = HiiragiColor.GRAY.rgb
        formula = "Li"
        molar = 6.9
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 1560
        tempMelt = 454
    }

    @JvmField
    val BERYLLIUM = materialOf("beryllium", 4) {
        color = HiiragiColor.DARK_GREEN.rgb
        formula = "Be"
        molar = 9.0
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 2742
        tempMelt = 1560
    }

    @JvmField
    val BORON = materialOf("boron", 5) {
        color = HiiragiColor.GRAY.rgb
        formula = "B"
        molar = 10.8
        shapeType = HiiragiShapeTypes.SOLID
        tempBoil = 4200
        tempMelt = 2349
    }

    @JvmField
    val CARBON = materialOf("carbon", 6) {
        color = HiiragiColor.mixColor(HiiragiColor.BLACK, HiiragiColor.DARK_GRAY).rgb
        fluidSupplier = { null }
        formula = "C"
        molar = 12.0
        shapeType = HiiragiShapeTypes.SOLID
        //tempBoil = 4300
        //tempMelt = 4000
    }

    @JvmField
    val NITROGEN = materialOf("nitrogen", 7) {
        color = HiiragiColor.AQUA.rgb
        formula = "N"
        molar = 14.0
        shapeType = HiiragiShapeTypes.GAS
        tempBoil = 77
        tempMelt = 63
    }

    @JvmField
    val OXYGEN = materialOf("oxygen", 8) {
        formula = "O"
        molar = 16.0
        shapeType = HiiragiShapeTypes.GAS
        tempBoil = 90
        tempMelt = 54
    }

    @JvmField
    val FLUORINE = materialOf("fluorine", 9) {
        color = HiiragiColor.GREEN.rgb
        formula = "F"
        molar = 19.0
        shapeType = HiiragiShapeTypes.GAS
        tempBoil = 85
        tempMelt = 54
    }

    @JvmField
    val NEON = materialOf("neon", 10) {
        color = HiiragiColor.LIGHT_PURPLE.rgb
        formula = "Ne"
        molar = 20.2
        shapeType = HiiragiShapeTypes.GAS
        tempBoil = 27
        tempMelt = 25
    }

    //    3rd Period    //

    @JvmField
    val SODIUM = materialOf("sodium", 11) {
        color = HiiragiColor.mixColor(HiiragiColor.DARK_BLUE to 1, HiiragiColor.BLUE to 4).rgb
        formula = "Na"
        molar = 23.0
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 1156
        tempMelt = 371
    }

    @JvmField
    val MAGNESIUM = materialOf("magnesium", 12) {
        color = HiiragiColor.mixColor(HiiragiColor.LIGHT_PURPLE, HiiragiColor.WHITE).rgb
        formula = "Mg"
        molar = 24.3
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 1363
        tempMelt = 923
    }

    @JvmField
    val ALUMINIUM = materialOf("aluminium", 13) {
        color = HiiragiColor.mixColor(HiiragiColor.BLUE to 1, HiiragiColor.WHITE to 5).rgb
        formula = "Al"
        molar = 27.0
        oreDictAlt.add("aluminum")
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
        tempBoil = 2792
        tempMelt = 933
    }

    @JvmField
    val SILICON = materialOf("silicon", 14) {
        color = HiiragiColor.mixColor(HiiragiColor.BLACK to 2, HiiragiColor.GRAY to 1, HiiragiColor.BLUE to 1).rgb
        formula = "Si"
        molar = 28.1
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 3538
        tempMelt = 1687
    }

    @JvmField
    val PHOSPHORUS = materialOf("phosphorus", 15) {
        color = HiiragiColor.YELLOW.rgb
        formula = "P"
        molar = 31.0
        shapeType = HiiragiShapeTypes.SOLID
        tempBoil = 550
        tempMelt = 317
    }

    @JvmField
    val SULFUR = materialOf("sulfur", 16) {
        color = HiiragiColor.mixColor(HiiragiColor.GOLD, HiiragiColor.YELLOW).rgb
        formula = "S"
        molar = 32.1
        shapeType = HiiragiShapeTypes.SOLID
        tempBoil = 718
        tempMelt = 388
    }

    @JvmField
    val CHLORINE = materialOf("chlorine", 17) {
        color = HiiragiColor.YELLOW.rgb
        formula = "Cl"
        molar = 35.5
        shapeType = HiiragiShapeTypes.GAS
        tempBoil = 239
        tempMelt = 171
    }

    @JvmField
    val ARGON = materialOf("argon", 18) {
        color = HiiragiColor.LIGHT_PURPLE.rgb
        formula = "Ar"
        molar = 40.0
        shapeType = HiiragiShapeTypes.GAS
        tempBoil = 87
        tempMelt = 84
    }

    //    4th Period    //

    @JvmField
    val POTASSIUM = materialOf("potassium", 19) {
        color = HiiragiColor.mixColor(HiiragiColor.DARK_BLUE to 2, HiiragiColor.BLUE to 3).rgb
        formula = "K"
        molar = 39.1
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 1032
        tempMelt = 337
    }

    @JvmField
    val CALCIUM = materialOf("calcium", 20) {
        color = HiiragiColor.GRAY.rgb
        formula = "Ca"
        molar = 40.1
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 1757
        tempMelt = 1115
    }

    @JvmField
    val SCANDIUM = materialOf("scandium", 21) {
        color = HiiragiColor.GRAY.rgb
        formula = "Sc"
        molar = 45.0
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 3109
        tempMelt = 1814
    }

    @JvmField
    val TITANIUM = materialOf("titanium", 22) {
        color = HiiragiColor.mixColor(HiiragiColor.GOLD to 1, HiiragiColor.WHITE to 2).rgb
        formula = "Ti"
        molar = 47.9
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
        tempBoil = 3560
        tempMelt = 1941
    }

    @JvmField
    val VANADIUM = materialOf("vanadium", 23) {
        color = HiiragiColor.GRAY.rgb
        formula = "V"
        molar = 50.9
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 3680
        tempMelt = 2183
    }

    @JvmField
    val CHROMIUM = materialOf("chromium", 24) {
        color = HiiragiColor.GREEN.rgb
        formula = "Cr"
        molar = 52.0
        oreDictAlt.add("chrome")
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 2944
        tempMelt = 2180
    }

    @JvmField
    val MANGANESE = materialOf("manganese", 25) {
        color = HiiragiColor.mixColor(HiiragiColor.RED, HiiragiColor.WHITE).rgb
        formula = "Mn"
        molar = 54.9
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 2334
        tempMelt = 1519
    }

    @JvmField
    val IRON = materialOf("iron", 26) {
        color = HiiragiColor.mixColor(HiiragiColor.GRAY to 1, HiiragiColor.WHITE to 2).rgb
        formula = "Fe"
        molar = 55.8
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
        tempBoil = 3134
        tempMelt = 1811
    }

    @JvmField
    val COBALT = materialOf("cobalt", 27) {
        color = HiiragiColor.BLUE.rgb
        formula = "Co"
        molar = 58.9
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
        tempBoil = 3200
        tempMelt = 1768
    }

    @JvmField
    val NICKEL = materialOf("nickel", 28) {
        color = HiiragiColor.mixColor(HiiragiColor.GOLD, HiiragiColor.WHITE).rgb
        formula = "Ni"
        molar = 58.7
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
        tempBoil = 3186
        tempMelt = 1728
    }

    @JvmField
    val COPPER = materialOf("copper", 29) {
        color = HiiragiColor.mixColor(HiiragiColor.GOLD, HiiragiColor.RED).rgb
        formula = "Cu"
        molar = 63.5
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
        tempBoil = 2835
        tempMelt = 1358
    }

    @JvmField
    val ZINC = materialOf("zinc", 30) {
        color = HiiragiColor.mixColor(HiiragiColor.GREEN to 1, HiiragiColor.WHITE to 2).rgb
        formula = "Zn"
        molar = 65.4
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
        tempBoil = 1180
        tempMelt = 693
    }

    @JvmField
    val GALLIUM = materialOf("gallium", 31) {
        color = HiiragiColor.GRAY.rgb
        formula = "Ga"
        molar = 69.7
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 2673
        tempMelt = 303
    }

    @JvmField
    val GERMANIUM = materialOf("germanium", 32) {
        color = HiiragiColor.GRAY.rgb
        formula = "Ge"
        molar = 72.6
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 3106
        tempMelt = 1211
    }

    @JvmField
    val ARSENIC = materialOf("arsenic", 33) {
        color = HiiragiColor.GRAY.rgb
        fluidSupplier = { null }
        formula = "As"
        molar = 74.9
        shapeType = HiiragiShapeTypes.SOLID
        //tempBoil = 887
        //tempMelt = 1090
    }

    @JvmField
    val SELENIUM = materialOf("selenium", 34) {
        color = HiiragiColor.GRAY.rgb
        formula = "Se"
        molar = 74.9
        shapeType = HiiragiShapeTypes.SOLID
        tempBoil = 958
        tempMelt = 453
    }

    @JvmField
    val BROMINE = materialOf("bromine", 35) {
        color = HiiragiColor.GOLD.rgb
        formula = "Br"
        molar = 79.9
        shapeType = HiiragiShapeTypes.LIQUID
        tempBoil = 332
        tempMelt = 267
    }

    @JvmField
    val KRYPTON = materialOf("krypton", 36) {
        color = HiiragiColor.LIGHT_PURPLE.rgb
        formula = "Kr"
        molar = 83.8
        shapeType = HiiragiShapeTypes.GAS
        tempBoil = 120
        tempMelt = 116
    }

    //    5th Period    //

    @JvmField
    val RUBIDIUM = materialOf("rubidium", 37) {
        color = HiiragiColor.mixColor(HiiragiColor.DARK_BLUE to 3, HiiragiColor.BLUE to 2).rgb
        formula = "Rb"
        molar = 85.5
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 961
        tempMelt = 312
    }

    @JvmField
    val STRONTIUM = materialOf("strontium", 38) {
        color = HiiragiColor.GRAY.rgb
        formula = "Sr"
        molar = 87.6
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 1655
        tempMelt = 1050
    }

    @JvmField
    val YTTRIUM = materialOf("yttrium", 39) {
        color = HiiragiColor.GRAY.rgb
        formula = "Y"
        molar = 88.9
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 3609
        tempMelt = 1799
    }

    @JvmField
    val ZIRCONIUM = materialOf("zirconium", 40) {
        color = HiiragiColor.GRAY.rgb
        formula = "Zr"
        molar = 91.2
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 4682
        tempMelt = 2128
    }

    @JvmField
    val NIOBIUM = materialOf("niobium", 41) {
        color = HiiragiColor.GRAY.rgb
        formula = "Nb"
        molar = 92.9
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 5017
        tempMelt = 2750
    }

    @JvmField
    val MOLYBDENUM = materialOf("molybdenum", 42) {
        tempMelt = 2896
        tempBoil = 4912
        shapeType = HiiragiShapeTypes.METAL_COMMON
        molar = 96.0
        formula = "Mo"
        color = HiiragiColor.GRAY.rgb
    }

    @JvmField
    val TECHNETIUM = materialOf("technetium", 43) {
        color = HiiragiColor.GRAY.rgb
        formula = "Tc"
        //molar = 97.0/98.0/99.0
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 4538
        tempMelt = 2430
    }

    @JvmField
    val RUTHENIUM = materialOf("ruthenium", 44) {
        color = HiiragiColor.mixColor(HiiragiColor.BLUE to 1, HiiragiColor.LIGHT_PURPLE to 3).rgb
        formula = "Ru"
        molar = 101.1
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 4423
        tempMelt = 2607
    }

    @JvmField
    val RHODIUM = materialOf("rhodium", 45) {
        color = HiiragiColor.mixColor(HiiragiColor.BLUE to 1, HiiragiColor.RED to 3).rgb
        formula = "Rh"
        molar = 102.9
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 3968
        tempMelt = 2237
    }

    @JvmField
    val PALLADIUM = materialOf("palladium", 46) {
        color = HiiragiColor.mixColor(HiiragiColor.BLUE to 1, HiiragiColor.YELLOW to 3).rgb
        formula = "Pd"
        molar = 106.4
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 3236
        tempMelt = 1828
    }

    @JvmField
    val SILVER = materialOf("silver", 47) {
        color = HiiragiColor.mixColor(HiiragiColor.AQUA to 1, HiiragiColor.WHITE to 3).rgb
        formula = "Ag"
        molar = 107.9
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
        tempBoil = 2435
        tempMelt = 1235
    }

    @JvmField
    val CADMIUM = materialOf("cadmium", 48) {
        color = HiiragiColor.GRAY.rgb
        formula = "Cd"
        molar = 112.4
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 1040
        tempMelt = 594
    }

    @JvmField
    val INDIUM = materialOf("indium", 49) {
        color = HiiragiColor.GRAY.rgb
        formula = "In"
        molar = 114.8
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 2345
        tempMelt = 430
    }

    @JvmField
    val TIN = materialOf("tin", 50) {
        color = HiiragiColor.mixColor(HiiragiColor.BLUE to 1, HiiragiColor.AQUA to 1, HiiragiColor.WHITE to 3).rgb
        formula = "Sn"
        molar = 118.7
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
        tempBoil = 2875
        tempMelt = 505
    }

    @JvmField
    val ANTIMONY = materialOf("antimony", 51) {
        color = HiiragiColor.GRAY.rgb
        formula = "Sb"
        molar = 121.8
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 1860
        tempMelt = 904
    }

    @JvmField
    val TELLURIUM = materialOf("tellurium", 52) {
        color = HiiragiColor.GRAY.rgb
        formula = "Te"
        molar = 127.6
        shapeType = HiiragiShapeTypes.SOLID
        tempBoil = 1261
        tempMelt = 723
    }

    @JvmField
    val IODINE = materialOf("iodine", 53) {
        color = HiiragiColor.DARK_PURPLE.rgb
        formula = "I"
        molar = 126.9
        shapeType = HiiragiShapeTypes.SOLID
        tempBoil = 457
        tempMelt = 387
    }

    @JvmField
    val XENON = materialOf("xenon", 54) {
        color = HiiragiColor.LIGHT_PURPLE.rgb
        formula = "Xe"
        molar = 131.3
        shapeType = HiiragiShapeTypes.GAS
        tempBoil = 165
        tempMelt = 161
    }

    //    6th Period    //

    @JvmField
    val CAESIUM = materialOf("caesium", 55) {
        color = HiiragiColor.mixColor(HiiragiColor.DARK_BLUE to 4, HiiragiColor.BLUE to 1).rgb
        formula = "Cs"
        molar = 132.9
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 944
        tempMelt = 302
    }

    @JvmField
    val BARIUM = materialOf("barium", 56) {
        color = HiiragiColor.GRAY.rgb
        formula = "Ba"
        molar = 137.3
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 2170
        tempMelt = 1000
    }

    //    Lanthanides Start    //

    @JvmField
    val LANTHANUM = materialOf("lanthanum", 57) {
        color = HiiragiColor.GRAY.rgb
        formula = "La"
        molar = 138.9
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 3737
        tempMelt = 1193
    }

    @JvmField
    val CERIUM = materialOf("cerium", 58) {
        color = HiiragiColor.GRAY.rgb
        formula = "Ce"
        molar = 140.1
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 3716
        tempMelt = 1068
    }

    @JvmField
    val PRASEODYMIUM = materialOf("praseodymium", 59) {
        color = HiiragiColor.GRAY.rgb
        formula = "Pr"
        molar = 140.9
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 3793
        tempMelt = 1208
    }

    @JvmField
    val NEODYMIUM = materialOf("neodymium", 60) {
        color = HiiragiColor.GRAY.rgb
        formula = "Nd"
        molar = 144.2
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 3347
        tempMelt = 1297
    }

    @JvmField
    val PROMETHIUM = materialOf("promethium", 61) {
        color = HiiragiColor.GRAY.rgb
        formula = "Pm"
        //molar = 145.0
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 3237
        tempMelt = 1315
    }

    @JvmField
    val SAMARIUM = materialOf("samarium", 62) {
        color = HiiragiColor.GRAY.rgb
        formula = "Sm"
        molar = 150.4
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 2067
        tempMelt = 1345
    }

    @JvmField
    val EUROPIUM = materialOf("europium", 63) {
        color = HiiragiColor.GRAY.rgb
        formula = "Eu"
        molar = 152.0
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 1802
        tempMelt = 1099
    }

    @JvmField
    val GADOLINIUM = materialOf("gadolinium", 64) {
        color = HiiragiColor.GRAY.rgb
        formula = "Gd"
        molar = 157.3
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 3546
        tempMelt = 1585
    }

    @JvmField
    val TERBIUM = materialOf("terbium", 65) {
        color = HiiragiColor.GRAY.rgb
        formula = "Tb"
        molar = 158.9
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 3503
        tempMelt = 1629
    }

    @JvmField
    val DYSPROSIUM = materialOf("dysprosium", 66) {
        color = HiiragiColor.GRAY.rgb
        formula = "Dy"
        molar = 157.3
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 2840
        tempMelt = 1680
    }

    @JvmField
    val HOLMIUM = materialOf("holmium", 67) {
        color = HiiragiColor.GRAY.rgb
        formula = "Ho"
        molar = 164.9
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 2993
        tempMelt = 1734
    }

    @JvmField
    val ERBIUM = materialOf("erbium", 68) {
        color = HiiragiColor.GRAY.rgb
        formula = "Er"
        molar = 167.3
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 3141
        tempMelt = 1802
    }

    @JvmField
    val THULIUM = materialOf("thulium", 69) {
        color = HiiragiColor.GRAY.rgb
        formula = "Tm"
        molar = 168.9
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 2223
        tempMelt = 1818
    }

    @JvmField
    val YTTERBIUM = materialOf("ytterbium", 70) {
        color = HiiragiColor.GRAY.rgb
        formula = "Yb"
        molar = 173.0
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 1469
        tempMelt = 1097
    }

    @JvmField
    val LUTETIUM = materialOf("lutetium", 71) {
        color = HiiragiColor.GRAY.rgb
        formula = "Lu"
        molar = 175.0
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 3675
        tempMelt = 1925
    }

    //    Lanthanides End    //

    @JvmField
    val HAFNIUM = materialOf("hafnium", 72) {
        color = HiiragiColor.GRAY.rgb
        formula = "Hf"
        molar = 178.5
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 4876
        tempMelt = 2506
    }

    @JvmField
    val TANTALUM = materialOf("tantalum", 73) {
        color = HiiragiColor.GRAY.rgb
        formula = "Ta"
        molar = 180.9
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 5731
        tempMelt = 3290
    }

    @JvmField
    val TUNGSTEN = materialOf("tungsten", 74) {
        color = HiiragiColor.mixColor(HiiragiColor.BLACK to 2, HiiragiColor.DARK_GRAY to 1).rgb
        formula = "W"
        molar = 183.8
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
        tempBoil = 5828
        tempMelt = 3695
    }

    @JvmField
    val RHENIUM = materialOf("rhenium", 75) {
        color = HiiragiColor.GRAY.rgb
        formula = "Re"
        molar = 186.2
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 5869
        tempMelt = 3459
    }

    @JvmField
    val OSMIUM = materialOf("osmium", 76) {
        color = HiiragiColor.mixColor(HiiragiColor.BLUE to 1, HiiragiColor.WHITE to 3).rgb
        formula = "Os"
        molar = 190.2
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
        tempBoil = 5285
        tempMelt = 3306
    }

    @JvmField
    val IRIDIUM = materialOf("iridium", 77) {
        color = HiiragiColor.mixColor(HiiragiColor.AQUA to 1, HiiragiColor.WHITE to 3).rgb
        formula = "Ir"
        molar = 192.2
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
        tempBoil = 4701
        tempMelt = 2719
    }

    @JvmField
    val PLATINUM = materialOf("platinum", 78) {
        color = HiiragiColor.mixColor(HiiragiColor.GREEN to 1, HiiragiColor.WHITE to 3).rgb
        formula = "Pt"
        molar = 195.1
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
        tempBoil = 4098
        tempMelt = 2041
    }

    @JvmField
    val GOLD = materialOf("gold", 79) {
        color = HiiragiColor.mixColor(HiiragiColor.GOLD, HiiragiColor.YELLOW).rgb
        formula = "Au"
        molar = 197.0
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
        tempBoil = 3129
        tempMelt = 1337
    }

    @JvmField
    val MERCURY = materialOf("mercury", 80) {
        formula = "Hg"
        molar = 200.6
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 670
        tempMelt = 234
    }

    @JvmField
    val THALLIUM = materialOf("thallium", 81) {
        color = HiiragiColor.GRAY.rgb
        formula = "Tl"
        molar = 204.4
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 1749
        tempMelt = 577
    }

    @JvmField
    val LEAD = materialOf("lead", 82) {
        color = HiiragiColor.mixColor(HiiragiColor.DARK_BLUE, HiiragiColor.DARK_GRAY, HiiragiColor.WHITE).rgb
        formula = "Pb"
        molar = 207.2
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
        tempBoil = 2022
        tempMelt = 601
    }

    @JvmField
    val BISMUTH = materialOf("bismuth", 83) {
        color = HiiragiColor.AQUA.rgb
        formula = "Bi"
        molar = 209.0
        shapeType = HiiragiShapeTypes.METAL_ADVANCED
        tempBoil = 1837
        tempMelt = 545
    }

    @JvmField
    val POLONIUM = materialOf("polonium", 84) {
        color = HiiragiColor.GRAY.rgb
        formula = "Po"
        //molar = 209.0/210.0
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 1235
        tempMelt = 527
    }

    @JvmField
    val ASTATINE = materialOf("astatine", 85) {
        color = HiiragiColor.BLACK.rgb
        formula = "At"
        //molar = 210.0
        shapeType = HiiragiShapeTypes.SOLID
        tempBoil = 610
        tempMelt = 575
    }

    @JvmField
    val RADON = materialOf("radon", 86) {
        color = HiiragiColor.LIGHT_PURPLE.rgb
        formula = "Rn"
        //molar = 222.0
        shapeType = HiiragiShapeTypes.GAS
        tempBoil = 211
        tempMelt = 202
    }

    //    7th Period    //

    @JvmField
    val FRANCIUM = materialOf("francium", 87) {
        color = HiiragiColor.DARK_BLUE.rgb
        formula = "Fr"
        //molar = 223.0
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 890
        tempMelt = 281
    }

    @JvmField
    val RADIUM = materialOf("radium", 88) {
        color = HiiragiColor.GRAY.rgb
        formula = "Ra"
        //molar = 226.0
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 2010
        tempMelt = 973
    }

    //    Actinides Start    //

    @JvmField
    val ACTINIUM = materialOf("actinium", 89) {
        color = HiiragiColor.GRAY.rgb
        formula = "Ac"
        //molar = 227.0
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 3471
        tempMelt = 1323
    }

    @JvmField
    val THORIUM = materialOf("thorium", 90) {
        color = HiiragiColor.mixColor(HiiragiColor.BLACK to 2, HiiragiColor.DARK_GREEN to 1, HiiragiColor.GRAY to 1).rgb
        formula = "Th"
        molar = 232.0
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 5061
        tempMelt = 2115
    }

    @JvmField
    val PROTACTINIUM = materialOf("protactinium", 91) {
        color = HiiragiColor.GRAY.rgb
        formula = "Pa"
        molar = 231.0
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 4300
        tempMelt = 1841
    }

    @JvmField
    val URANIUM = materialOf("uranium", 92) {
        color = HiiragiColor.GREEN.rgb
        formula = "U"
        molar = 238.0
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 4404
        tempMelt = 1405
    }

    @JvmField
    val NEPTUNIUM = materialOf("neptunium", 93) {
        color = HiiragiColor.BLUE.rgb
        formula = "Np"
        //molar = 237.0
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 4273
        tempMelt = 917
    }

    @JvmField
    val PLUTONIUM = materialOf("plutonium", 94) {
        color = HiiragiColor.RED.rgb
        formula = "Pu244"
        molar = 244.1
        shapeType = HiiragiShapeTypes.METAL_COMMON
        tempBoil = 3501
        tempMelt = 913
    }

    @JvmField
    val AMERICIUM = materialOf("americium", 95) {
        formula = "Am"
        //molar = 243.0
        tempBoil = 1880
        tempMelt = 1449
    }

    @JvmField
    val CURIUM = materialOf("curium", 96) {
        formula = "Cm"
        //molar = 247.0
        tempBoil = 3383
        tempMelt = 1613
    }

    @JvmField
    val BERKELIUM = materialOf("berkelium", 97) {
        formula = "Bk"
        //molar = 247.0
        tempBoil = 2900
        tempMelt = 1259
    }

    @JvmField
    val CALIFORNIUM = materialOf("californium", 98) {
        formula = "Cf"
        //molar = 251.0/252.0
        tempBoil = 1743
        tempMelt = 1173
    }

    @JvmField
    val EINSTEINIUM = materialOf("einsteinium", 99) {
        formula = "Es"
        //molar = 252.0
        tempBoil = 1269
        tempMelt = 1133
    }

    @JvmField
    val FERMIUM = materialOf("fermium", 100) {
        formula = "Fm"
        //molar = 257.0
        //tempBoil =
        //tempMelt = 1125/1800
    }

    @JvmField
    val MENDELEVIUM = materialOf("mendelevium", 101) {
        formula = "Md"
        //molar = 258.0
        //tempBoil =
        //tempMelt = 1100
    }

    @JvmField
    val NOBELIUM = materialOf("nobelium", 102) {
        formula = "No"
        //molar = 259.0
        //tempBoil =
        //tempMelt = 1100
    }

    @JvmField
    val LAWRENCIUM = materialOf("lawrencium", 103) {
        formula = "Lr"
        //molar = 262.0/266.0
        //tempBoil =
        //tempMelt = 1900
    }

    //    Actinides End    //

    @JvmField
    val RUTHERFORDIUM = materialOf("rutherfordium", 104) {
        formula = "Lr"
        //molar = 261.1/267.0
        //tempBoil = 5800
        //tempMelt = 2400
    }

    @JvmField
    val DUBNIUM = materialOf("dubnium", 105) {
        formula = "Db"
        //molar = 265.0/268.0
        //tempBoil =
        //tempMelt =
    }

    @JvmField
    val SEABORGIUM = materialOf("seaborgium", 106) {
        formula = "Sg"
        //molar = 269.0/271.0
        //tempBoil =
        //tempMelt =
    }

    @JvmField
    val BOHRIUM = materialOf("bohrium", 107) {
        formula = "Bh"
        //molar = 270.0/272.0
        //tempBoil =
        //tempMelt =
    }

    @JvmField
    val HASSIUM = materialOf("hassium", 108) {
        formula = "Hs"
        //molar = 269.0/277.0
        //tempBoil =
        //tempMelt =
    }

    @JvmField
    val MEITNERIUM = materialOf("meitnerium", 109) {
        formula = "Mt"
        //molar = 276.0/278.0
        //tempBoil =
        //tempMelt =
    }

    @JvmField
    val DARMSTADTIUM = materialOf("darmstadtium", 110) {
        formula = "Ds"
        //molar = 281.0
        //tempBoil =
        //tempMelt =
    }

    @JvmField
    val ROENTGENIUM = materialOf("roentgenium", 111) {
        formula = "Rg"
        //molar = 280.0/281.0/282.0
        //tempBoil =
        //tempMelt =
    }

    @JvmField
    val COPERNICIUM = materialOf("copernicium", 112) {
        formula = "Cn"
        //molar = 285.0
        //tempBoil = 340
        //tempMelt = 283
    }

    @JvmField
    val NIHONIUM = materialOf("nihonium", 113) {
        formula = "Nh"
        //molar = 278.0/286.0
        //tempBoil = 1400
        //tempMelt = 700
    }

    @JvmField
    val FLEROVIUM = materialOf("flerovium", 114) {
        formula = "Fl"
        //molar = 289.0
        //tempBoil =
        //tempMelt = 284
    }

    @JvmField
    val MOSCOVIUM = materialOf("moscovium", 115) {
        formula = "Mc"
        //molar = 289.0/290.0
        //tempBoil = 1400
        //tempMelt = 700
    }

    @JvmField
    val LIVERMORIUM = materialOf("livermorium", 116) {
        formula = "Lv"
        //molar = 293.0
        //tempBoil = 1100
        //tempMelt = 700
    }

    @JvmField
    val TENNESSINE = materialOf("tennessine", 117) {
        formula = "Ts"
        //molar = 293.0/294.0
        //tempBoil = 883
        //tempMelt = 700
    }

    @JvmField
    val OGANESSON = materialOf("oganesson", 118) {
        formula = "Og"
        //molar = 294.0
        //tempBoil = 450
        //tempMelt = 352
    }

    //    Isotope    //

    @JvmField
    val DEUTERIUM = isotopeOf("deuterium", 120, HYDROGEN) {
        formula = "D"
        molar = 2.0
    }

    @JvmField
    val TRITIUM = isotopeOf("tritium", 121, HYDROGEN) {
        formula = "T"
        molar = 3.0
    }

    @JvmField
    val URANIUM235 = isotopeOf("uranium235", 122, URANIUM) {
        formula = "U235"
        molar = 235.0
    }

    fun register() {
        // --1st Period--
        HYDROGEN.register()
        HELIUM.register()
        // --2nd Period--
        LITHIUM.register()
        BERYLLIUM.register()
        BORON.register()
        CARBON.register()
        NITROGEN.register()
        OXYGEN.register()
        FLUORINE.register()
        NEON.register()
        // --3rd Period--
        SODIUM.register()
        MAGNESIUM.register()
        ALUMINIUM.register()
        SILICON.register()
        PHOSPHORUS.register()
        SULFUR.register()
        CHLORINE.register()
        ARGON.register()
        // --4th Period--
        POTASSIUM.register()
        CALCIUM.register()
        SCANDIUM.register()
        TITANIUM.register()
        VANADIUM.register()
        CHROMIUM.register()
        MANGANESE.register()
        IRON.register()
        COBALT.register()
        NICKEL.register()
        COPPER.register()
        ZINC.register()
        GALLIUM.register()
        GERMANIUM.register()
        ARSENIC.register()
        SELENIUM.register()
        BROMINE.register()
        KRYPTON.register()
        // --5th Period--
        RUBIDIUM.register()
        STRONTIUM.register()
        YTTRIUM.register()
        ZIRCONIUM.register()
        NIOBIUM.register()
        MOLYBDENUM.register()
        TECHNETIUM.register()
        RUTHENIUM.register()
        RHODIUM.register()
        PALLADIUM.register()
        SILVER.register()
        CADMIUM.register()
        INDIUM.register()
        TIN.register()
        ANTIMONY.register()
        TELLURIUM.register()
        IODINE.register()
        XENON.register()
        // --6th Period--
        CAESIUM.register()
        BARIUM.register()
        // --Lanthanides--
        if (RMConfig.MATERIAL.lanthanoides) {
            LANTHANUM.register()
            CERIUM.register()
            PRASEODYMIUM.register()
            NEODYMIUM.register()
            PROMETHIUM.register()
            SAMARIUM.register()
            EUROPIUM.register()
            GADOLINIUM.register()
            TERBIUM.register()
            DYSPROSIUM.register()
            HOLMIUM.register()
            ERBIUM.register()
            THULIUM.register()
            YTTERBIUM.register()
            LUTETIUM.register()
        }
        HAFNIUM.register()
        TANTALUM.register()
        TUNGSTEN.register()
        RHENIUM.register()
        OSMIUM.register()
        IRIDIUM.register()
        PLATINUM.register()
        GOLD.register()
        MERCURY.register()
        THALLIUM.register()
        LEAD.register()
        BISMUTH.register()
        POLONIUM.register()
        ASTATINE.register()
        RADON.register()
        // --7th Period--
        FRANCIUM.register()
        RADIUM.register()
        // --Actinides Start--
        ACTINIUM.register()
        THORIUM.register()
        PROTACTINIUM.register()
        URANIUM.register()
        NEPTUNIUM.register()
        PLUTONIUM.register()
        if (RMConfig.MATERIAL.transUran) {
            AMERICIUM.register()
            CURIUM.register()
            BERKELIUM.register()
            CALIFORNIUM.register()
            EINSTEINIUM.register()
            FERMIUM.register()
            MENDELEVIUM.register()
            NOBELIUM.register()
            LAWRENCIUM.register()
            // --Actinides End--
            RUTHERFORDIUM.register()
            DUBNIUM.register()
            SEABORGIUM.register()
            BOHRIUM.register()
            HASSIUM.register()
            MEITNERIUM.register()
            DARMSTADTIUM.register()
            ROENTGENIUM.register()
            COPERNICIUM.register()
            NIHONIUM.register()
            FLEROVIUM.register()
            MOSCOVIUM.register()
            LIVERMORIUM.register()
            TENNESSINE.register()
            OGANESSON.register()
        }
        // --Isotope--
        DEUTERIUM.register()
        TRITIUM.register()
        URANIUM235.register()
    }
}