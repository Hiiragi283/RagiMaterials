package hiiragi283.material.api.material

import hiiragi283.material.common.util.ColorUtil
import hiiragi283.material.common.util.RagiColor

/**
 * @author MrKono
 * @author turtton
 */
object MaterialElements {

    //    1st Period    //

    @JvmField
    val HYDROGEN = HiiragiMaterial.Builder("hydrogen", MaterialType.OTHER).build {
        color = RagiColor.BLUE.rgb
        formula = "H"
        molar = 1.0
        tempBoil = 20
        tempMelt = 14
    }

    @JvmField
    val HELIUM = HiiragiMaterial.Builder("helium", MaterialType.OTHER).build {
        color = RagiColor.YELLOW.rgb
        formula = "He"
        molar = 4.0
        tempBoil = 3
        tempMelt = 1
    }

    //    2nd Period    //

    @JvmField
    val LITHIUM = HiiragiMaterial.Builder("lithium", MaterialType.ALKALI_METAL).build {
        color = RagiColor.GRAY.rgb
        formula = "Li"
        molar = 6.9
        tempBoil = 1560
        tempMelt = 454
    }

    @JvmField
    val BERYLLIUM = HiiragiMaterial.Builder("beryllium", MaterialType.ALKALINE_METAL).build {
        color = RagiColor.DARK_GREEN.rgb
        formula = "Be"
        molar = 9.0
        tempBoil = 2742
        tempMelt = 1560
    }

    @JvmField
    val BORON = HiiragiMaterial.Builder("boron", MaterialType.METALLOID).build {
        color = RagiColor.GRAY.rgb
        formula = "B"
        molar = 10.8
        tempBoil = 4200
        tempMelt = 2349
    }

    @JvmField
    val CARBON = HiiragiMaterial.Builder("carbon", MaterialType.OTHER).build {
        color = ColorUtil.mixColor(RagiColor.BLACK, RagiColor.DARK_GRAY).rgb
        formula = "C"
        molar = 12.0
        //tempBoil = 4300
        //tempMelt = 4000
        tempSubl = 3915
    }

    @JvmField
    val NITROGEN = HiiragiMaterial.Builder("nitrogen", MaterialType.OTHER).build {
        color = RagiColor.AQUA.rgb
        formula = "N"
        molar = 14.0
        tempBoil = 77
        tempMelt = 63
    }

    @JvmField
    val OXYGEN = HiiragiMaterial.Builder("oxygen", MaterialType.OTHER).build {
        formula = "O"
        molar = 16.0
        tempBoil = 90
        tempMelt = 54
    }

    @JvmField
    val FLUORINE = HiiragiMaterial.Builder("fluorine", MaterialType.OTHER).build {
        color = RagiColor.GREEN.rgb
        formula = "F"
        molar = 19.0
        tempBoil = 85
        tempMelt = 54
    }

    @JvmField
    val NEON = HiiragiMaterial.Builder("neon", MaterialType.OTHER).build {
        color = RagiColor.LIGHT_PURPLE.rgb
        formula = "Ne"
        molar = 20.2
        tempBoil = 27
        tempMelt = 25
    }

    //    3rd Period    //

    @JvmField
    val SODIUM = HiiragiMaterial.Builder("sodium", MaterialType.ALKALI_METAL).build {
        color = ColorUtil.mixColor(RagiColor.DARK_BLUE to 1, RagiColor.BLUE to 4).rgb
        formula = "Na"
        molar = 23.0
        tempBoil = 1156
        tempMelt = 371
    }

    @JvmField
    val MAGNESIUM = HiiragiMaterial.Builder("magnesium", MaterialType.ALKALINE_METAL).build {
        color = ColorUtil.mixColor(RagiColor.LIGHT_PURPLE, RagiColor.WHITE).rgb
        formula = "Mg"
        molar = 24.3
        tempBoil = 1363
        tempMelt = 923
    }

    @JvmField
    val ALUMINIUM = HiiragiMaterial.Builder("aluminium", MaterialType.METAL).build {
        color = ColorUtil.mixColor(RagiColor.BLUE to 1, RagiColor.WHITE to 5).rgb
        formula = "Al"
        molar = 27.0
        tempBoil = 2792
        tempMelt = 933
    }

    @JvmField
    val SILICON = HiiragiMaterial.Builder("silicon", MaterialType.METALLOID).build {
        color = ColorUtil.mixColor(RagiColor.BLACK to 2, RagiColor.GRAY to 1, RagiColor.BLUE to 1).rgb
        formula = "Si"
        molar = 28.1
        tempBoil = 3538
        tempMelt = 1687
    }

    @JvmField
    val PHOSPHORUS = HiiragiMaterial.Builder("phosphorus", MaterialType.OTHER).build {
        color = RagiColor.YELLOW.rgb
        formula = "P"
        molar = 31.0
        tempBoil = 550
        tempMelt = 317
    }

    @JvmField
    val SULFUR = HiiragiMaterial.Builder("sulfur", MaterialType.OTHER).build {
        color = ColorUtil.mixColor(RagiColor.GOLD, RagiColor.YELLOW).rgb
        formula = "S"
        molar = 32.1
        tempBoil = 718
        tempMelt = 388
    }

    @JvmField
    val CHLORINE = HiiragiMaterial.Builder("chlorine", MaterialType.OTHER).build {
        color = RagiColor.YELLOW.rgb
        formula = "Cl"
        molar = 35.5
        tempBoil = 239
        tempMelt = 171
    }

    @JvmField
    val ARGON = HiiragiMaterial.Builder("argon", MaterialType.OTHER).build {
        color = RagiColor.LIGHT_PURPLE.rgb
        formula = "Ar"
        molar = 40.0
        tempBoil = 87
        tempMelt = 84
    }

    //    4th Period    //

    @JvmField
    val POTASSIUM = HiiragiMaterial.Builder("potassium", MaterialType.ALKALI_METAL).build {
        color = ColorUtil.mixColor(RagiColor.DARK_BLUE to 2, RagiColor.BLUE to 3).rgb
        formula = "K"
        molar = 39.1
        tempBoil = 1032
        tempMelt = 337
    }

    @JvmField
    val CALCIUM = HiiragiMaterial.Builder("calcium", MaterialType.ALKALINE_METAL).build {
        color = RagiColor.GRAY.rgb
        formula = "Ca"
        molar = 40.1
        tempBoil = 1757
        tempMelt = 1115
    }

    @JvmField
    val SCANDIUM = HiiragiMaterial.Builder("scandium", MaterialType.RARE_EARTH).build {
        color = RagiColor.GRAY.rgb
        formula = "Sc"
        molar = 45.0
        tempBoil = 3109
        tempMelt = 1814
    }

    @JvmField
    val TITANIUM = HiiragiMaterial.Builder("titanium", MaterialType.METAL).build {
        color = ColorUtil.mixColor(RagiColor.GOLD to 1, RagiColor.WHITE to 2).rgb
        formula = "Ti"
        molar = 47.9
        tempBoil = 3560
        tempMelt = 1941
    }

    @JvmField
    val VANADIUM = HiiragiMaterial.Builder("vanadium", MaterialType.METAL).build {
        color = RagiColor.GRAY.rgb
        formula = "V"
        molar = 50.9
        tempBoil = 3680
        tempMelt = 2183
    }

    @JvmField
    val CHROMIUM = HiiragiMaterial.Builder("chromium", MaterialType.METAL).build {
        color = RagiColor.GREEN.rgb
        formula = "Cr"
        molar = 52.0
        tempBoil = 2944
        tempMelt = 2180
    }

    @JvmField
    val MANGANESE = HiiragiMaterial.Builder("manganese", MaterialType.METAL).build {
        color = ColorUtil.mixColor(RagiColor.RED, RagiColor.WHITE).rgb
        formula = "Mn"
        molar = 54.9
        tempBoil = 2334
        tempMelt = 1519
    }

    @JvmField
    val IRON = HiiragiMaterial.Builder("iron", MaterialType.METAL).build {
        color = ColorUtil.mixColor(RagiColor.GRAY to 1, RagiColor.WHITE to 2).rgb
        formula = "Fe"
        molar = 55.8
        tempBoil = 3134
        tempMelt = 1811
    }

    @JvmField
    val COBALT = HiiragiMaterial.Builder("cobalt", MaterialType.METAL).build {
        color = RagiColor.BLUE.rgb
        formula = "Co"
        molar = 58.9
        tempBoil = 3200
        tempMelt = 1768
    }

    @JvmField
    val NICKEL = HiiragiMaterial.Builder("nickel", MaterialType.METAL).build {
        color = ColorUtil.mixColor(RagiColor.GOLD, RagiColor.WHITE).rgb
        formula = "Ni"
        molar = 58.7
        tempBoil = 3186
        tempMelt = 1728
    }

    @JvmField
    val COPPER = HiiragiMaterial.Builder("copper", MaterialType.METAL).build {
        color = ColorUtil.mixColor(RagiColor.GOLD, RagiColor.RED).rgb
        formula = "Cu"
        molar = 63.5
        tempBoil = 2835
        tempMelt = 1358
    }

    @JvmField
    val ZINC = HiiragiMaterial.Builder("zinc", MaterialType.METAL).build {
        color = ColorUtil.mixColor(RagiColor.GREEN to 1, RagiColor.WHITE to 2).rgb
        formula = "Zn"
        molar = 65.4
        tempBoil = 1180
        tempMelt = 693
    }

    @JvmField
    val GALLIUM = HiiragiMaterial.Builder("gallium", MaterialType.METAL).build {
        color = RagiColor.GRAY.rgb
        formula = "Ga"
        molar = 69.7
        tempBoil = 2673
        tempMelt = 303
    }

    @JvmField
    val GERMANIUM = HiiragiMaterial.Builder("germanium", MaterialType.METAL).build {
        color = RagiColor.GRAY.rgb
        formula = "Ge"
        molar = 72.6
        tempBoil = 3106
        tempMelt = 1211
    }

    @JvmField
    val ARSENIC = HiiragiMaterial.Builder("arsenic", MaterialType.METALLOID).build {
        color = RagiColor.GRAY.rgb
        formula = "As"
        molar = 74.9
        //tempBoil = 887
        //tempMelt = 1090
        tempSubl = 887
    }

    @JvmField
    val SELENIUM = HiiragiMaterial.Builder("selenium", MaterialType.METALLOID).build {
        color = RagiColor.GRAY.rgb
        formula = "Se"
        molar = 74.9
        tempBoil = 958
        tempMelt = 453
    }

    @JvmField
    val BROMINE = HiiragiMaterial.Builder("bromine", MaterialType.OTHER).build {
        color = RagiColor.GOLD.rgb
        formula = "Br"
        molar = 79.9
        tempBoil = 332
        tempMelt = 267
    }

    @JvmField
    val KRYPTON = HiiragiMaterial.Builder("krypton", MaterialType.OTHER).build {
        color = RagiColor.LIGHT_PURPLE.rgb
        formula = "Kr"
        molar = 83.8
        tempBoil = 120
        tempMelt = 116
    }

    //    5th Period    //

    @JvmField
    val RUBIDIUM = HiiragiMaterial.Builder("rubidium", MaterialType.ALKALI_METAL).build {
        color = ColorUtil.mixColor(RagiColor.DARK_BLUE to 3, RagiColor.BLUE to 2).rgb
        formula = "Rb"
        molar = 85.5
        tempBoil = 961
        tempMelt = 312
    }

    @JvmField
    val STRONTIUM = HiiragiMaterial.Builder("strontium", MaterialType.ALKALINE_METAL).build {
        color = RagiColor.GRAY.rgb
        formula = "Sr"
        molar = 87.6
        tempBoil = 1655
        tempMelt = 1050
    }

    @JvmField
    val YTTRIUM = HiiragiMaterial.Builder("yttrium", MaterialType.RARE_EARTH).build {
        color = RagiColor.GRAY.rgb
        formula = "Y"
        molar = 88.9
        tempBoil = 3609
        tempMelt = 1799
    }

    @JvmField
    val ZIRCONIUM = HiiragiMaterial.Builder("zirconium", MaterialType.METAL).build {
        color = RagiColor.GRAY.rgb
        formula = "Zr"
        molar = 91.2
        tempBoil = 4682
        tempMelt = 2128
    }

    @JvmField
    val NIOBIUM = HiiragiMaterial.Builder("niobium", MaterialType.METAL).build {
        color = RagiColor.GRAY.rgb
        formula = "Nb"
        molar = 92.9
        tempBoil = 5017
        tempMelt = 2750
    }

    @JvmField
    val MOLYBDENUM = HiiragiMaterial.Builder("molybdenum", MaterialType.METAL).build {
        color = RagiColor.GRAY.rgb
        formula = "Mo"
        molar = 96.0
        tempBoil = 4912
        tempMelt = 2896
    }

    @JvmField
    val TECHNETIUM = HiiragiMaterial.Builder("technetium", MaterialType.METAL).build {
        color = RagiColor.GRAY.rgb
        formula = "Tc"
        //molar = 97.0/98.0/99.0
        tempBoil = 4538
        tempMelt = 2430
    }

    @JvmField
    val RUTHENIUM = HiiragiMaterial.Builder("ruthenium", MaterialType.METAL).build {
        color = ColorUtil.mixColor(RagiColor.BLUE to 1, RagiColor.LIGHT_PURPLE to 3).rgb
        formula = "Ru"
        molar = 101.1
        tempBoil = 4423
        tempMelt = 2607
    }

    @JvmField
    val RHODIUM = HiiragiMaterial.Builder("rhodium", MaterialType.METAL).build {
        color = ColorUtil.mixColor(RagiColor.BLUE to 1, RagiColor.RED to 3).rgb
        formula = "Rh"
        molar = 102.9
        tempBoil = 3968
        tempMelt = 2237
    }

    @JvmField
    val PALLADIUM = HiiragiMaterial.Builder("palladium", MaterialType.METAL).build {
        color = ColorUtil.mixColor(RagiColor.BLUE to 1, RagiColor.YELLOW to 3).rgb
        formula = "Pd"
        molar = 106.4
        tempBoil = 3236
        tempMelt = 1828
    }

    @JvmField
    val SILVER = HiiragiMaterial.Builder("silver", MaterialType.METAL).build {
        color = ColorUtil.mixColor(RagiColor.AQUA to 1, RagiColor.WHITE to 3).rgb
        formula = "Ag"
        molar = 107.9
        tempBoil = 2435
        tempMelt = 1235
    }

    @JvmField
    val CADMIUM = HiiragiMaterial.Builder("cadmium", MaterialType.METAL).build {
        color = RagiColor.GRAY.rgb
        formula = "Cd"
        molar = 112.4
        tempBoil = 1040
        tempMelt = 594
    }

    @JvmField
    val INDIUM = HiiragiMaterial.Builder("indium", MaterialType.METAL).build {
        color = RagiColor.GRAY.rgb
        formula = "In"
        molar = 114.8
        tempBoil = 2345
        tempMelt = 430
    }

    @JvmField
    val TIN = HiiragiMaterial.Builder("tin", MaterialType.METAL).build {
        color = ColorUtil.mixColor(RagiColor.BLUE to 1, RagiColor.AQUA to 1, RagiColor.WHITE to 3).rgb
        formula = "Sn"
        molar = 118.7
        tempBoil = 2875
        tempMelt = 505
    }

    @JvmField
    val ANTIMONY = HiiragiMaterial.Builder("antimony", MaterialType.METAL).build {
        color = RagiColor.GRAY.rgb
        formula = "Sb"
        molar = 121.8
        tempBoil = 1860
        tempMelt = 904
    }

    @JvmField
    val TELLURIUM = HiiragiMaterial.Builder("tellurium", MaterialType.METALLOID).build {
        color = RagiColor.GRAY.rgb
        formula = "Te"
        molar = 127.6
        tempBoil = 1261
        tempMelt = 723
    }

    @JvmField
    val IODINE = HiiragiMaterial.Builder("iodine", MaterialType.METALLOID).build {
        color = RagiColor.DARK_PURPLE.rgb
        formula = "I"
        molar = 126.9
        tempBoil = 457
        tempMelt = 387
    }

    @JvmField
    val XENON = HiiragiMaterial.Builder("xenon", MaterialType.OTHER).build {
        color = RagiColor.LIGHT_PURPLE.rgb
        formula = "Xe"
        molar = 131.3
        tempBoil = 165
        tempMelt = 161
    }

    //    6th Period    //

    @JvmField
    val CAESIUM = HiiragiMaterial.Builder("caesium", MaterialType.ALKALI_METAL).build {
        color = ColorUtil.mixColor(RagiColor.DARK_BLUE to 4, RagiColor.BLUE to 1).rgb
        formula = "Cs"
        molar = 132.9
        tempBoil = 944
        tempMelt = 302
    }

    @JvmField
    val BARIUM = HiiragiMaterial.Builder("barium", MaterialType.ALKALINE_METAL).build {
        color = RagiColor.GRAY.rgb
        formula = "Ba"
        molar = 137.3
        tempBoil = 2170
        tempMelt = 1000
    }

    //    Lanthanides Start    //

    @JvmField
    val LANTHANUM = HiiragiMaterial.Builder("lanthanum", MaterialType.RARE_EARTH).build {
        color = RagiColor.GRAY.rgb
        formula = "La"
        molar = 138.9
        tempBoil = 3737
        tempMelt = 1193
    }

    @JvmField
    val CERIUM = HiiragiMaterial.Builder("cerium", MaterialType.RARE_EARTH).build {
        color = RagiColor.GRAY.rgb
        formula = "Ce"
        molar = 140.1
        tempBoil = 3716
        tempMelt = 1068
    }

    @JvmField
    val PRASEODYMIUM = HiiragiMaterial.Builder("praseodymium", MaterialType.RARE_EARTH).build {
        color = RagiColor.GRAY.rgb
        formula = "Pr"
        molar = 140.9
        tempBoil = 3793
        tempMelt = 1208
    }

    @JvmField
    val NEODYMIUM = HiiragiMaterial.Builder("neodymium", MaterialType.RARE_EARTH).build {
        color = RagiColor.GRAY.rgb
        formula = "Nd"
        molar = 144.2
        tempBoil = 3347
        tempMelt = 1297
    }

    @JvmField
    val PROMETHIUM = HiiragiMaterial.Builder("promethium", MaterialType.RARE_EARTH).build {
        color = RagiColor.GRAY.rgb
        formula = "Pm"
        //molar = 145.0
        tempBoil = 3237
        tempMelt = 1315
    }

    @JvmField
    val SAMARIUM = HiiragiMaterial.Builder("samarium", MaterialType.RARE_EARTH).build {
        color = RagiColor.GRAY.rgb
        formula = "Sm"
        molar = 150.4
        tempBoil = 2067
        tempMelt = 1345
    }

    @JvmField
    val EUROPIUM = HiiragiMaterial.Builder("europium", MaterialType.RARE_EARTH).build {
        color = RagiColor.GRAY.rgb
        formula = "Eu"
        molar = 152.0
        tempBoil = 1802
        tempMelt = 1099
    }

    @JvmField
    val GADOLINIUM = HiiragiMaterial.Builder("gadolinium", MaterialType.RARE_EARTH).build {
        color = RagiColor.GRAY.rgb
        formula = "Gd"
        molar = 157.3
        tempBoil = 3546
        tempMelt = 1585
    }

    @JvmField
    val TERBIUM = HiiragiMaterial.Builder("terbium", MaterialType.RARE_EARTH).build {
        color = RagiColor.GRAY.rgb
        formula = "Tb"
        molar = 158.9
        tempBoil = 3503
        tempMelt = 1629
    }

    @JvmField
    val DYSPROSIUM = HiiragiMaterial.Builder("dysprosium", MaterialType.RARE_EARTH).build {
        color = RagiColor.GRAY.rgb
        formula = "Dy"
        molar = 157.3
        tempBoil = 2840
        tempMelt = 1680
    }

    @JvmField
    val HOLMIUM = HiiragiMaterial.Builder("holmium", MaterialType.RARE_EARTH).build {
        color = RagiColor.GRAY.rgb
        formula = "Ho"
        molar = 164.9
        tempBoil = 2993
        tempMelt = 1734
    }

    @JvmField
    val ERBIUM = HiiragiMaterial.Builder("erbium", MaterialType.RARE_EARTH).build {
        color = RagiColor.GRAY.rgb
        formula = "Er"
        molar = 167.3
        tempBoil = 3141
        tempMelt = 1802
    }

    @JvmField
    val THULIUM = HiiragiMaterial.Builder("thulium", MaterialType.RARE_EARTH).build {
        color = RagiColor.GRAY.rgb
        formula = "Tm"
        molar = 168.9
        tempBoil = 2223
        tempMelt = 1818
    }

    @JvmField
    val YTTERBIUM = HiiragiMaterial.Builder("ytterbium", MaterialType.RARE_EARTH).build {
        color = RagiColor.GRAY.rgb
        formula = "Yb"
        molar = 173.0
        tempBoil = 1469
        tempMelt = 1097
    }

    @JvmField
    val LUTETIUM = HiiragiMaterial.Builder("lutetium", MaterialType.RARE_EARTH).build {
        color = RagiColor.GRAY.rgb
        formula = "Lu"
        molar = 175.0
        tempBoil = 3675
        tempMelt = 1925
    }

    //    Lanthanides End    //

    @JvmField
    val HAFNIUM = HiiragiMaterial.Builder("hafnium", MaterialType.METAL).build {
        color = RagiColor.GRAY.rgb
        formula = "Hf"
        molar = 178.5
        tempBoil = 4876
        tempMelt = 2506
    }

    @JvmField
    val TANTALUM = HiiragiMaterial.Builder("tantalum", MaterialType.METAL).build {
        color = RagiColor.GRAY.rgb
        formula = "Ta"
        molar = 180.9
        tempBoil = 5731
        tempMelt = 3290
    }

    @JvmField
    val TUNGSTEN = HiiragiMaterial.Builder("tungsten", MaterialType.METAL).build {
        color = ColorUtil.mixColor(RagiColor.BLACK to 2, RagiColor.DARK_GRAY to 1).rgb
        formula = "W"
        molar = 183.8
        tempBoil = 5828
        tempMelt = 3695
    }

    @JvmField
    val RHENIUM = HiiragiMaterial.Builder("rhenium", MaterialType.METAL).build {
        color = RagiColor.GRAY.rgb
        formula = "Re"
        molar = 186.2
        tempBoil = 5869
        tempMelt = 3459
    }

    @JvmField
    val OSMIUM = HiiragiMaterial.Builder("osmium", MaterialType.METAL).build {
        color = ColorUtil.mixColor(RagiColor.BLUE to 1, RagiColor.WHITE to 3).rgb
        formula = "Os"
        molar = 190.2
        tempBoil = 5285
        tempMelt = 3306
    }

    @JvmField
    val IRIDIUM = HiiragiMaterial.Builder("iridium", MaterialType.METAL).build {
        color = ColorUtil.mixColor(RagiColor.AQUA to 1, RagiColor.WHITE to 3).rgb
        formula = "Ir"
        molar = 192.2
        tempBoil = 4701
        tempMelt = 2719
    }

    @JvmField
    val PLATINUM = HiiragiMaterial.Builder("platinum", MaterialType.METAL).build {
        color = ColorUtil.mixColor(RagiColor.GREEN to 1, RagiColor.WHITE to 3).rgb
        formula = "Pt"
        molar = 195.1
        tempBoil = 4098
        tempMelt = 2041
    }

    @JvmField
    val GOLD = HiiragiMaterial.Builder("gold", MaterialType.METAL).build {
        color = ColorUtil.mixColor(RagiColor.GOLD, RagiColor.YELLOW).rgb
        formula = "Au"
        molar = 197.0
        tempBoil = 3129
        tempMelt = 1337
    }

    @JvmField
    val MERCURY = HiiragiMaterial.Builder("mercury", MaterialType.METAL).build {
        formula = "Hg"
        molar = 200.6
        tempBoil = 670
        tempMelt = 234
    }

    @JvmField
    val THALLIUM = HiiragiMaterial.Builder("thallium", MaterialType.METAL).build {
        color = RagiColor.GRAY.rgb
        formula = "Tl"
        molar = 204.4
        tempBoil = 1749
        tempMelt = 577
    }

    @JvmField
    val LEAD = HiiragiMaterial.Builder("lead", MaterialType.METAL).build {
        color = ColorUtil.mixColor(RagiColor.DARK_BLUE, RagiColor.DARK_GRAY, RagiColor.WHITE).rgb
        formula = "Pb"
        molar = 207.2
        tempBoil = 2022
        tempMelt = 601
    }

    @JvmField
    val BISMUTH = HiiragiMaterial.Builder("bismuth", MaterialType.METAL).build {
        color = RagiColor.AQUA.rgb
        formula = "Bi"
        molar = 209.0
        tempBoil = 1837
        tempMelt = 545
    }

    @JvmField
    val POLONIUM = HiiragiMaterial.Builder("polonium", MaterialType.METAL).build {
        color = RagiColor.GRAY.rgb
        formula = "Po"
        //molar = 209.0/210.0
        tempBoil = 1235
        tempMelt = 527
    }

    @JvmField
    val ASTATINE = HiiragiMaterial.Builder("astatine", MaterialType.OTHER).build {
        color = RagiColor.BLACK.rgb
        formula = "At"
        //molar = 210.0
        tempBoil = 610
        tempMelt = 575
    }

    @JvmField
    val RADON = HiiragiMaterial.Builder("radon", MaterialType.OTHER).build {
        color = RagiColor.LIGHT_PURPLE.rgb
        formula = "Rn"
        //molar = 222.0
        tempBoil = 211
        tempMelt = 202
    }

    //    7th Period    //

    @JvmField
    val FRANCIUM = HiiragiMaterial.Builder("francium", MaterialType.ALKALI_METAL).build {
        color = RagiColor.DARK_BLUE.rgb
        formula = "Fr"
        //molar = 223.0
        tempBoil = 890
        tempMelt = 281
    }

    @JvmField
    val RADIUM = HiiragiMaterial.Builder("radium", MaterialType.ALKALINE_METAL).build {
        color = RagiColor.GRAY.rgb
        formula = "Ra"
        //molar = 226.0
        tempBoil = 2010
        tempMelt = 973
    }

    //    Actinides Start    //

    @JvmField
    val ACTINIUM = HiiragiMaterial.Builder("actinium", MaterialType.ACTINOID).build {
        color = RagiColor.GRAY.rgb
        formula = "Ac"
        //molar = 227.0
        tempBoil = 3471
        tempMelt = 1323
    }

    @JvmField
    val THORIUM = HiiragiMaterial.Builder("thorium", MaterialType.ACTINOID).build {
        color = ColorUtil.mixColor(RagiColor.BLACK to 2, RagiColor.DARK_GREEN to 1, RagiColor.GRAY to 1).rgb
        formula = "Th"
        molar = 232.0
        tempBoil = 5061
        tempMelt = 2115
    }

    @JvmField
    val PROTACTINIUM = HiiragiMaterial.Builder("protactinium", MaterialType.ACTINOID).build {
        color = RagiColor.GRAY.rgb
        formula = "Pa"
        molar = 231.0
        tempBoil = 4300
        tempMelt = 1841
    }

    @JvmField
    val URANIUM = HiiragiMaterial.Builder("uranium", MaterialType.ACTINOID).build {
        color = RagiColor.GREEN.rgb
        formula = "U238"
        molar = 238.0
        tempBoil = 4404
        tempMelt = 1405
    }

    @JvmField
    val NEPTUNIUM = HiiragiMaterial.Builder("neptunium", MaterialType.ACTINOID).build {
        color = RagiColor.BLUE.rgb
        formula = "Np"
        //molar = 237.0
        tempBoil = 4273
        tempMelt = 917
    }

    @JvmField
    val PLUTONIUM244 = HiiragiMaterial.Builder("plutonium244", MaterialType.ACTINOID).build {
        color = RagiColor.RED.rgb
        formula = "Pu244"
        molar = 244.1
        tempBoil = 3501
        tempMelt = 913
    }

    @JvmField
    val AMERICIUM = HiiragiMaterial.Builder("americium", MaterialType.TRANS_URANIUM).build {
        formula = "Am"
        //molar = 243.0
        tempBoil = 1880
        tempMelt = 1449
    }

    @JvmField
    val CURIUM = HiiragiMaterial.Builder("curium", MaterialType.TRANS_URANIUM).build {
        formula = "Cm"
        //molar = 247.0
        tempBoil = 3383
        tempMelt = 1613
    }

    @JvmField
    val BERKELIUM = HiiragiMaterial.Builder("berkelium", MaterialType.TRANS_URANIUM).build {
        formula = "Bk"
        //molar = 247.0
        tempBoil = 2900
        tempMelt = 1259
    }

    @JvmField
    val CALIFORNIUM = HiiragiMaterial.Builder("californium", MaterialType.TRANS_URANIUM).build {
        formula = "Cf"
        //molar = 251.0/252.0
        tempBoil = 1743
        tempMelt = 1173
    }

    @JvmField
    val EINSTEINIUM = HiiragiMaterial.Builder("einsteinium", MaterialType.TRANS_URANIUM).build {
        formula = "Es"
        //molar = 252.0
        tempBoil = 1269
        tempMelt = 1133
    }

    @JvmField
    val FERMIUM = HiiragiMaterial.Builder("fermium", MaterialType.TRANS_URANIUM).build {
        formula = "Fm"
        //molar = 257.0
        //tempBoil =
        //tempMelt = 1125/1800
    }

    @JvmField
    val MENDELEVIUM = HiiragiMaterial.Builder("mendelevium", MaterialType.TRANS_URANIUM).build {
        formula = "Md"
        //molar = 258.0
        //tempBoil =
        //tempMelt = 1100
    }

    @JvmField
    val NOBELIUM = HiiragiMaterial.Builder("nobelium", MaterialType.TRANS_URANIUM).build {
        formula = "No"
        //molar = 259.0
        //tempBoil =
        //tempMelt = 1100
    }

    @JvmField
    val LAWRENCIUM = HiiragiMaterial.Builder("lawrencium", MaterialType.TRANS_URANIUM).build {
        formula = "Lr"
        //molar = 262.0/266.0
        //tempBoil =
        //tempMelt = 1900
    }

    //    Actinides End    //

    @JvmField
    val RUTHERFORDIUM = HiiragiMaterial.Builder("rutherfordium", MaterialType.TRANS_URANIUM).build {
        formula = "Lr"
        //molar = 261.1/267.0
        //tempBoil = 5800
        //tempMelt = 2400
    }

    @JvmField
    val DUBNIUM = HiiragiMaterial.Builder("dubnium", MaterialType.TRANS_URANIUM).build {
        formula = "Db"
        //molar = 265.0/268.0
        //tempBoil =
        //tempMelt =
    }

    @JvmField
    val SEABORGIUM = HiiragiMaterial.Builder("seaborgium", MaterialType.TRANS_URANIUM).build {
        formula = "Sg"
        //molar = 269.0/271.0
        //tempBoil =
        //tempMelt =
    }

    @JvmField
    val BOHRIUM = HiiragiMaterial.Builder("bohrium", MaterialType.TRANS_URANIUM).build {
        formula = "Bh"
        //molar = 270.0/272.0
        //tempBoil =
        //tempMelt =
    }

    @JvmField
    val HASSIUM = HiiragiMaterial.Builder("hassium", MaterialType.TRANS_URANIUM).build {
        formula = "Hs"
        //molar = 269.0/277.0
        //tempBoil =
        //tempMelt =
    }

    @JvmField
    val MEITNERIUM = HiiragiMaterial.Builder("meitnerium", MaterialType.TRANS_URANIUM).build {
        formula = "Mt"
        //molar = 276.0/278.0
        //tempBoil =
        //tempMelt =
    }

    @JvmField
    val DARMSTADTIUM = HiiragiMaterial.Builder("darmstadtium", MaterialType.TRANS_URANIUM).build {
        formula = "Ds"
        //molar = 281.0
        //tempBoil =
        //tempMelt =
    }

    @JvmField
    val ROENTGENIUM = HiiragiMaterial.Builder("roentgenium", MaterialType.TRANS_URANIUM).build {
        formula = "Rg"
        //molar = 280.0/281.0/282.0
        //tempBoil =
        //tempMelt =
    }

    @JvmField
    val COPERNICIUM = HiiragiMaterial.Builder("copernicium", MaterialType.TRANS_URANIUM).build {
        formula = "Cn"
        //molar = 285.0
        //tempBoil = 340
        //tempMelt = 283
    }

    @JvmField
    val NIHONIUM = HiiragiMaterial.Builder("nihonium", MaterialType.TRANS_URANIUM).build {
        formula = "Nh"
        //molar = 278.0/286.0
        //tempBoil = 1400
        //tempMelt = 700
    }

    @JvmField
    val FLEROVIUM = HiiragiMaterial.Builder("flerovium", MaterialType.TRANS_URANIUM).build {
        formula = "Fl"
        //molar = 289.0
        //tempBoil =
        //tempMelt = 284
    }

    @JvmField
    val MOSCOVIUM = HiiragiMaterial.Builder("moscovium", MaterialType.TRANS_URANIUM).build {
        formula = "Mc"
        //molar = 289.0/290.0
        //tempBoil = 1400
        //tempMelt = 700
    }

    @JvmField
    val LIVERMORIUM = HiiragiMaterial.Builder("livermorium", MaterialType.TRANS_URANIUM).build {
        formula = "Lv"
        //molar = 293.0
        //tempBoil = 1100
        //tempMelt = 700
    }

    @JvmField
    val TENNESSINE = HiiragiMaterial.Builder("tennessine", MaterialType.TRANS_URANIUM).build {
        formula = "Ts"
        //molar = 293.0/294.0
        //tempBoil = 883
        //tempMelt = 700
    }

    @JvmField
    val OGANESSON = HiiragiMaterial.Builder("oganesson", MaterialType.TRANS_URANIUM).build {
        formula = "Og"
        //molar = 294.0
        //tempBoil = 450
        //tempMelt = 352
    }

    //    Isotope    //

    @JvmField
    val DEUTERIUM = HiiragiMaterial.Builder("deuterium", MaterialType.OTHER).build {
        color = HYDROGEN.color
        formula = "D"
        molar = 2.0
        tempBoil = HYDROGEN.tempBoil
        tempMelt = HYDROGEN.tempMelt
    }

    @JvmField
    val TRITIUM = HiiragiMaterial.Builder("tritium", MaterialType.OTHER).build {
        color = HYDROGEN.color
        formula = "T"
        molar = 3.0
        tempBoil = HYDROGEN.tempBoil
        tempMelt = HYDROGEN.tempMelt
    }

    @JvmField
    val URANIUM235 = HiiragiMaterial.Builder("uranium235", MaterialType.ACTINOID).build {
        color = URANIUM.color
        crystalType = URANIUM.crystalType
        formula = "U235"
        molar = 235.0
        tempBoil = URANIUM.tempBoil
        tempMelt = URANIUM.tempMelt
    }

    fun load() {
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
        if (true) {
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
        PLUTONIUM244.register()
        if (true) {
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