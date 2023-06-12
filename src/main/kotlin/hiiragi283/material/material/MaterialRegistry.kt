package hiiragi283.material.material

import hiiragi283.material.RagiMaterials
import hiiragi283.material.util.RagiColor

object MaterialRegistry {

    private val REGISTRY: HashMap<String, HiiragiMaterial> = hashMapOf()

    @JvmStatic
    fun getMaterials(): Collection<HiiragiMaterial> = REGISTRY.values

    @JvmStatic
    fun getMaterialFromName(name: String) = REGISTRY.getOrDefault(name, HiiragiMaterial.EMPTY)

    @JvmStatic
    fun registerMaterial(material: HiiragiMaterial) {
        val name = material.getName()
        //同じ名前で登録されていた場合，登録せずに警告を表示する
        REGISTRY.putIfAbsent(name, material)
            ?.let { RagiMaterials.LOGGER.warn("The material: $name has already registered!") }
    }

    //    Materials    //

    /**
     * @author MrKono
     */

    // ELEMENTS //
    // --1st Period--
    @JvmField
    val HYDROGEN = HiiragiMaterial.builder("hydrogen", 1) {
        color = RagiColor.BLUE
        formula = "H"
        molar = 1.0
        tempBoil = 20
        tempMelt = 14
        }

    @JvmField
    val HELIUM = HiiragiMaterial.builder("helium", 2) {
        color = RagiColor.YELLOW
        formula = "He"
        molar = 4.0
        tempBoil = 3
        tempMelt = 1
        }

    // --2nd Period--
    @JvmField
    val LITHIUM = HiiragiMaterial.builder("lithium", 3) {
        color = RagiColor.GRAY
        formula = "Li"
        molar = 6.9
        tempBoil = 1560
        tempMelt = 454
        }

    @JvmField
    val BERYLLIUM = HiiragiMaterial.builder("beryllium", 4) {
        color = RagiColor.DARK_GREEN
        formula = "Be"
        molar = 9.0
        tempBoil = 2742
        tempMelt = 1560
        }

    @JvmField
    val BORON = HiiragiMaterial.builder("boron", 5) {
        color = RagiColor.GRAY
        formula = "B"
        molar = 10.8
        tempBoil = 4200
        tempMelt = 2349
        }

    @JvmField
    val CARBON = HiiragiMaterial.builder("carbon", 6) {
        color = RagiColor.BLACK
        formula = "C"
        molar = 12.0
        //tempBoil = 4300
        //tempMelt = 4000
        TempSubl = 3915
        }

    @JvmField
    val NITROGEN = HiiragiMaterial.builder("nitrogen", 7) {
        color = RagiColor.DARK_AQUA
        formula = "N"
        molar = 14.0
        tempBoil = 77
        tempMelt = 63
        }

    @JvmField
    val OXYGEN = HiiragiMaterial.builder("oxygen", 8) {
        color = RagiColor.AQUA
        formula = "O"
        molar = 16.0
        tempBoil = 90
        tempMelt = 54
        }

    @JvmField
    val FLUORINE = HiiragiMaterial.builder("fluorine", 9) {
        color = RagiColor.BLUE
        formula = "F"
        molar = 19.0
        tempBoil = 85
        tempMelt = 54
        }

    @JvmField
    val NEON = HiiragiMaterial.builder("neon", 10) {
        color = RagiColor.DARK_AQUA
        formula = "Ne"
        molar = 20.2
        tempBoil = 27
        tempMelt = 25
        }

    // --3rd Period--
    @JvmField
    val SODIUM = HiiragiMaterial.builder("sodium", 11) {
        color = RagiColor.BLUE
        formula = "Na"
        molar = 23.0
        tempBoil = 1156
        tempMelt = 371
        }

    @JvmField
    val MAGNESIUM = HiiragiMaterial.builder("magnesium", 12) {
        color = RagiColor.GRAY
        formula = "Mg"
        molar = 24.3
        tempBoil = 1363
        tempMelt = 923
        }

    @JvmField
    val ALUMINIUM = HiiragiMaterial.builder("aluminium", 13) {
        color = RagiColor.AQUA
        formula = "Al"
        molar = 27.0
        tempBoil = 2792
        tempMelt = 933
        }

    @JvmField
    val SILICON = HiiragiMaterial.builder("silicon", 14) {
        color = RagiColor.DARK_GRAY
        formula = "Si"
        molar = 28.1
        tempBoil = 3538
        tempMelt = 1687
        }

    @JvmField
    val PHOSPHORUS = HiiragiMaterial.builder("phosphorus", 15) {
        color = RagiColor.WHITE
        formula = "P"
        molar = 31.0
        tempBoil = 550
        tempMelt = 317
        }

    @JvmField
    val SULFUR = HiiragiMaterial.builder("sulfur", 16) {
        color = RagiColor.YELLOW
        formula = "S"
        molar = 32.1
        tempBoil = 718
        tempMelt = 388
        }

    @JvmField
    val CHLORINE = HiiragiMaterial.builder("chlorine", 17) {
        color = RagiColor.YELLOW
        formula = "Cl"
        molar = 35.5
        tempBoil = 239
        tempMelt = 171
        }

    @JvmField
    val ARGON = HiiragiMaterial.builder("argon", 18) {
        color = RagiColor.GREEN
        formula = "Ar"
        molar = 40.0
        tempBoil = 87
        tempMelt = 84
        }

    // --4th Period--
    @JvmField
    val POTASSIUM = HiiragiMaterial.builder("potassium", 19) {
        color = RagiColor.GRAY
        formula = "K"
        molar = 39.1
        tempBoil = 1032
        tempMelt = 337
        }

    @JvmField
    val CALCIUM = HiiragiMaterial.builder("calcium", 20) {
        color = RagiColor.GRAY
        formula = "Ca"
        molar = 40.1
        tempBoil = 1757
        tempMelt = 1115
        }

    @JvmField
    val SCANDIUM = HiiragiMaterial.builder("scandium", 21) {
        color = RagiColor.GRAY
        formula = "Sc"
        molar = 45.0
        tempBoil = 3109
        tempMelt = 1814
        }

    @JvmField
    val TITANIUM = HiiragiMaterial.builder("titanium", 22) {
        color = RagiColor.LIGHT_PURPLE
        formula = "Ti"
        molar = 47.9
        tempBoil = 3560
        tempMelt = 1941
        }

    @JvmField
    val VANADIUM = HiiragiMaterial.builder("vanadium", 23) {
        color = RagiColor.DARK_GRAY
        formula = "V"
        molar = 50.9
        tempBoil = 3680
        tempMelt = 2183
        }

    @JvmField
    val CHROMIUM = HiiragiMaterial.builder("chromium", 24) {
        color = RagiColor.GRAY
        formula = "Cr"
        molar = 52.0
        tempBoil = 2944
        tempMelt = 2180
        }

    @JvmField
    val MANGANESE = HiiragiMaterial.builder("manganese", 25) {
        color = RagiColor.GRAY
        formula = "Mn"
        molar = 54.9
        tempBoil = 2334
        tempMelt = 1519
        }

    @JvmField
    val IRON = HiiragiMaterial.builder("iron", 26) {
        color = RagiColor.GRAY
        formula = "Fe"
        molar = 55.8
        tempBoil = 3134
        tempMelt = 1811
        }

    @JvmField
    val COBALT = HiiragiMaterial.builder("cobalt", 27) {
        color = RagiColor.BLUE
        formula = "Co"
        molar = 58.9
        tempBoil = 3200
        tempMelt = 1768
        }

    @JvmField
    val NICKEL = HiiragiMaterial.builder("nickel", 28) {
        color = RagiColor.GRAY
        formula = "Ni"
        molar = 58.7
        tempBoil = 3186
        tempMelt = 1728
        }

    @JvmField
    val COPPER = HiiragiMaterial.builder("copper", 29) {
        color = RagiColor.GOLD
        formula = "Cu"
        molar = 63.5
        tempBoil = 2835
        tempMelt = 1358
        }

    @JvmField
    val ZINC = HiiragiMaterial.builder("zinc", 30) {
        color = RagiColor.GRAY
        formula = "Zn"
        molar = 65.4
        tempBoil = 1180
        tempMelt = 693
        }

    @JvmField
    val GALLIUM = HiiragiMaterial.builder("gallium", 31) {
        color = RagiColor.GRAY
        formula = "Ga"
        molar = 69.7
        tempBoil = 2673
        tempMelt = 303
        }

    @JvmField
    val GERMANIUM = HiiragiMaterial.builder("germanium", 32) {
        color = RagiColor.GRAY
        formula = "Ge"
        molar = 72.6
        tempBoil = 3106
        tempMelt = 1211
        }

    @JvmField
    val ARSENIC = HiiragiMaterial.builder("arsenic", 33) {
        color = RagiColor.GRAY
        formula = "As"
        molar = 74.9
        //tempBoil = 887
        //tempMelt = 1090
        TempSubl = 887
        }

    @JvmField
    val SELENIUM = HiiragiMaterial.builder("selenium", 34) {
        color = RagiColor.BLACK
        formula = "Se"
        molar = 74.9
        tempBoil = 958
        tempMelt = 453
        }

    @JvmField
    val BROMINE = HiiragiMaterial.builder("bromine", 35) {
        color = RagiColor.GOLD
        formula = "Br"
        molar = 79.9
        tempBoil = 332
        tempMelt = 267
        }

    @JvmField
    val KRYPTON = HiiragiMaterial.builder("krypton", 36) {
        color = RagiColor.DARK_GREEN
        formula = "Kr"
        molar = 83.8
        tempBoil = 120
        tempMelt = 116
        }

    // --5th Period--
    @JvmField
    val RUBIDIUM = HiiragiMaterial.builder("rubidium", 37) {
        color = RagiColor.GRAY
        formula = "Rb"
        molar = 85.5
        tempBoil = 961
        tempMelt = 312
        }

    @JvmField
    val STRONTIUM = HiiragiMaterial.builder("strontium", 38) {
        color = RagiColor.GRAY
        formula = "Sr"
        molar = 87.6
        tempBoil = 1655
        tempMelt = 1050
        }

    @JvmField
    val YTTRIUM = HiiragiMaterial.builder("yttrium", 39) {
        color = RagiColor.GRAY
        formula = "Y"
        molar = 88.9
        tempBoil = 3609
        tempMelt = 1799
        }

    @JvmField
    val ZIRCONIUM = HiiragiMaterial.builder("zirconium", 40) {
        color = RagiColor.GRAY
        formula = "Zr"
        molar = 91.2
        tempBoil = 4682
        tempMelt = 2128
        }

    @JvmField
    val NIOBIUM = HiiragiMaterial.builder("niobium", 41) {
        color = RagiColor.GRAY
        formula = "Nb"
        molar = 92.9
        tempBoil = 5017
        tempMelt = 2750
        }

    @JvmField
    val MOLYBDENUM = HiiragiMaterial.builder("molybdenum", 42) {
        color = RagiColor.GRAY
        formula = "Mo"
        molar = 96.0
        tempBoil = 4912
        tempMelt = 2896
        }

    @JvmField
    val TECHNETIUM = HiiragiMaterial.builder("technetium", 43) {
        color = RagiColor.GRAY
        formula = "Tc"
        //molar = 97.0/98.0/99.0
        tempBoil = 4538
        tempMelt = 2430
        }

    @JvmField
    val RUTHENIUM = HiiragiMaterial.builder("ruthenium", 44) {
        color = RagiColor.GRAY
        formula = "Ru"
        molar = 101.1
        tempBoil = 4423
        tempMelt = 2607
        }

    @JvmField
    val RHODIUM = HiiragiMaterial.builder("rhodium", 45) {
        color = RagiColor.GRAY
        formula = "Rh"
        molar = 102.9
        tempBoil = 3968
        tempMelt = 2237
        }

    @JvmField
    val PALLADIUM = HiiragiMaterial.builder("palladium", 46) {
        color = RagiColor.GRAY
        formula = "Pd"
        molar = 106.4
        tempBoil = 3236
        tempMelt = 1828
        }

    @JvmField
    val SILVER = HiiragiMaterial.builder("silver", 47) {
        color = RagiColor.GRAY
        formula = "Ag"
        molar = 107.9
        tempBoil = 2435
        tempMelt = 1235
        }

    @JvmField
    val CADMIUM = HiiragiMaterial.builder("cadmium", 48) {
        color = RagiColor.GRAY
        formula = "Cd"
        molar = 112.4
        tempBoil = 1040
        tempMelt = 594
        }

    @JvmField
    val INDIUM = HiiragiMaterial.builder("indium", 49) {
        color = RagiColor.GRAY
        formula = "In"
        molar = 114.8
        tempBoil = 2345
        tempMelt = 430
        }

    @JvmField
    val TIN = HiiragiMaterial.builder("tin", 50) {
        color = RagiColor.GRAY
        formula = "Sn"
        molar = 118.7
        tempBoil = 2875
        tempMelt = 505
        }

    @JvmField
    val ANTIMONY = HiiragiMaterial.builder("antimony", 51) {
        color = RagiColor.GRAY
        formula = "Sb"
        molar = 121.8
        tempBoil = 1860
        tempMelt = 904
        }

    @JvmField
    val TELLURIUM = HiiragiMaterial.builder("tellurium", 52) {
        color = RagiColor.GRAY
        formula = "Te"
        molar = 127.6
        tempBoil = 1261
        tempMelt = 723
        }

    @JvmField
    val IODINE = HiiragiMaterial.builder("iodine", 53) {
        color = RagiColor.DARK_PURPLE
        formula = "I"
        molar = 126.9
        tempBoil = 457
        tempMelt = 387
        }

    @JvmField
    val XENON = HiiragiMaterial.builder("xenon", 54) {
        color = RagiColor.AQUA
        formula = "Xe"
        molar = 131.3
        tempBoil = 165
        tempMelt = 161
        }

    // 6th Period
    @JvmField
    val CAESIUM = HiiragiMaterial.builder("caesium", 55) {
        color = RagiColor.GRAY
        formula = "Cs"
        molar = 132.9
        tempBoil = 944
        tempMelt = 302
        }

    @JvmField
    val BARIUM = HiiragiMaterial.builder("barium", 56) {
        color = RagiColor.GRAY
        formula = "Ba"
        molar = 137.3
        tempBoil = 2170
        tempMelt = 1000
        }

    // (Lanthanoid START)
    @JvmField
    val LANTHANUM = HiiragiMaterial.builder("lanthanum", 57) {
        color = RagiColor.GRAY
        formula = "La"
        molar = 138.9
        tempBoil = 3737
        tempMelt = 1193
        }

    @JvmField
    val CERIUM = HiiragiMaterial.builder("cerium", 58) {
        color = RagiColor.GRAY
        formula = "Ce"
        molar = 140.1
        tempBoil = 3716
        tempMelt = 1068
        }

    @JvmField
    val PRASEODYMIUM = HiiragiMaterial.builder("praseodymium", 59) {
        color = RagiColor.GRAY
        formula = "Pr"
        molar = 140.9
        tempBoil = 3793
        tempMelt = 1208
        }

    @JvmField
    val NEODYMIUM = HiiragiMaterial.builder("neodymium", 60) {
        color = RagiColor.GRAY
        formula = "Nd"
        molar = 144.2
        tempBoil = 3347
        tempMelt = 1297
        }

    @JvmField
    val PROMETHIUM = HiiragiMaterial.builder("promethium", 61) {
        color = RagiColor.GRAY
        formula = "Pm"
        //molar = 145.0
        tempBoil = 3237
        tempMelt = 1315
        }

    @JvmField
    val SAMARIUM = HiiragiMaterial.builder("samarium", 62) {
        color = RagiColor.GRAY
        formula = "Sm"
        molar = 150.4
        tempBoil = 2067
        tempMelt = 1345
        }

    @JvmField
    val EUROPIUM = HiiragiMaterial.builder("europium", 63) {
        color = RagiColor.GRAY
        formula = "Eu"
        molar = 152.0
        tempBoil = 1802
        tempMelt = 1099
        }

    @JvmField
    val GADOLINIUM = HiiragiMaterial.builder("gadolinium", 64) {
        color = RagiColor.GRAY
        formula = "Gd"
        molar = 157.3
        tempBoil = 3546
        tempMelt = 1585
        }

    @JvmField
    val TERBIUM = HiiragiMaterial.builder("terbium", 65) {
        color = RagiColor.GRAY
        formula = "Tb"
        molar = 158.9
        tempBoil = 3503
        tempMelt = 1629
        }

    @JvmField
    val DYSPROSIUM = HiiragiMaterial.builder("dysprosium", 66) {
        color = RagiColor.GRAY
        formula = "Dy"
        molar = 157.3
        tempBoil = 2840
        tempMelt = 1680
        }

    @JvmField
    val HOLMIUM = HiiragiMaterial.builder("holmium", 67) {
        color = RagiColor.GRAY
        formula = "Ho"
        molar = 164.9
        tempBoil = 2993
        tempMelt = 1734
        }

    @JvmField
    val ERBIUM = HiiragiMaterial.builder("erbium", 68) {
        color = RagiColor.GRAY
        formula = "Er"
        molar = 167.3
        tempBoil = 3141
        tempMelt = 1802
        }

    @JvmField
    val THULIUM = HiiragiMaterial.builder("thulium", 69) {
        color = RagiColor.GRAY
        formula = "Tm"
        molar = 168.9
        tempBoil = 2223
        tempMelt = 1818
        }

    @JvmField
    val YTTERBIUM = HiiragiMaterial.builder("ytterbium", 70) {
        color = RagiColor.GRAY
        formula = "Yb"
        molar = 173.0
        tempBoil = 1469
        tempMelt = 1097
        }

    @JvmField
    val LUTETIUM = HiiragiMaterial.builder("lutetium", 71) {
        color = RagiColor.GRAY
        formula = "Lu"
        molar = 175.0
        tempBoil = 3675
        tempMelt = 1925
        }
    // (Lanthanoid END)

    @JvmField
    val HAFNIUM = HiiragiMaterial.builder("hafnium", 72) {
        color = RagiColor.GRAY
        formula = "Hf"
        molar = 178.5
        tempBoil = 4876
        tempMelt = 2506
        }

    @JvmField
    val TANTALUM = HiiragiMaterial.builder("tantalum", 73) {
        color = RagiColor.GRAY
        formula = "Ta"
        molar = 180.9
        tempBoil = 5731
        tempMelt = 3290
        }

    @JvmField
    val TUNGSTEN = HiiragiMaterial.builder("tungsten", 74) {
        color = RagiColor.GRAY
        formula = "W"
        molar = 183.8
        tempBoil = 5828
        tempMelt = 3695
        }

    @JvmField
    val RHENIUM = HiiragiMaterial.builder("rhenium", 75) {
        color = RagiColor.GRAY
        formula = "Re"
        molar = 186.2
        tempBoil = 5869
        tempMelt = 3459
        }

    @JvmField
    val OSMIUM = HiiragiMaterial.builder("osmium", 76) {
        color = RagiColor.DARK_BLUE
        formula = "Os"
        molar = 190.2
        tempBoil = 5285
        tempMelt = 3306
        }

    @JvmField
    val IRIDIUM = HiiragiMaterial.builder("iridium", 77) {
        color = RagiColor.GRAY
        formula = "Ir"
        molar = 192.2
        tempBoil = 4701
        tempMelt = 2719
        }

    @JvmField
    val PLATINUM = HiiragiMaterial.builder("platinum", 78) {
        color = RagiColor.GRAY
        formula = "Pt"
        molar = 195.1
        tempBoil = 4098
        tempMelt = 2041
        }

    @JvmField
    val GOLD = HiiragiMaterial.builder("gold", 79) {
        color = RagiColor.GOLD
        formula = "Au"
        molar = 197.0
        tempBoil = 3129
        tempMelt = 1337
        }

    @JvmField
    val MERCURY = HiiragiMaterial.builder("mercury", 80) {
        color = RagiColor.GRAY
        formula = "Hg"
        molar = 200.6
        tempBoil = 670
        tempMelt = 234
        }

    @JvmField
    val THALLIUM = HiiragiMaterial.builder("thallium", 81) {
        color = RagiColor.GRAY
        formula = "Tl"
        molar = 204.4
        tempBoil = 1749
        tempMelt = 577
        }

    @JvmField
    val LEAD = HiiragiMaterial.builder("lead", 82) {
        color = RagiColor.GRAY
        formula = "Pb"
        molar = 207.2
        tempBoil = 2022
        tempMelt = 601
        }

    @JvmField
    val BISMUTH = HiiragiMaterial.builder("bismuth", 83) {
        color = RagiColor.GRAY
        formula = "Bi"
        molar = 209.0
        tempBoil = 1837
        tempMelt = 545
        }

    @JvmField
    val POLONIUM = HiiragiMaterial.builder("polonium", 84) {
        color = RagiColor.GRAY
        formula = "Po"
        //molar = 209.0/210.0
        tempBoil = 1235
        tempMelt = 527
        }

    @JvmField
    val ASTATINE = HiiragiMaterial.builder("astatine", 85) {
        color = RagiColor.BLACK
        formula = "At"
        //molar = 210.0
        tempBoil = 610
        tempMelt = 575
        }

    @JvmField
    val RADON = HiiragiMaterial.builder("radon", 86) {
        color = RagiColor.LIGHT_PURPLE
        formula = "Rn"
        //molar = 222.0
        tempBoil = 211
        tempMelt = 202
        }

    // 7th Period
    @JvmField
    val FRANCIUM = HiiragiMaterial.builder("francium", 87) {
        color = RagiColor.GRAY
        formula = "Fr"
        //molar = 223.0
        tempBoil = 890
        tempMelt = 281
        }

    @JvmField
    val RADIUM = HiiragiMaterial.builder("radium", 88) {
        color = RagiColor.GRAY
        formula = "Ra"
        //molar = 226.0
        tempBoil = 2010
        tempMelt = 973
        }

    // (Actinoid START)
    @JvmField
    val ACTINIUM = HiiragiMaterial.builder("actinium", 89) {
        color = RagiColor.DARK_AQUA
        formula = "Ac"
        //molar = 227.0
        tempBoil = 3471
        tempMelt = 1323
        }

    @JvmField
    val THORIUM = HiiragiMaterial.builder("thorium", 90) {
        color = RagiColor.GRAY
        formula = "Th"
        molar = 232.0
        tempBoil = 5061
        tempMelt = 2115
        }

    @JvmField
    val PROTACTINIUM = HiiragiMaterial.builder("protactinium", 91) {
        color = RagiColor.GRAY
        formula = "Pa"
        molar = 231.0
        tempBoil = 4300
        tempMelt = 1841
        }

    @JvmField
    val URANIUM238 = HiiragiMaterial.builder("uranium238", 92) {
        color = RagiColor.GREEN
        formula = "U238"
        molar = 238.0
        tempBoil = 4404
        tempMelt = 1405
        }

    @JvmField
    val NEPTUNIUM = HiiragiMaterial.builder("neptunium", 93) {
        color = RagiColor.GRAY
        formula = "Np"
        //molar = 237.0
        tempBoil = 4273
        tempMelt = 917
        }

    @JvmField
    val PLUTONIUM244 = HiiragiMaterial.builder("plutonium244", 94) {
        color = RagiColor.DARK_RED
        formula = "Pu244"
        molar = 244.1
        tempBoil = 3501
        tempMelt = 913
        }

    @JvmField
    val AMERICIUM = HiiragiMaterial.builder("americium", 95) {
        color = RagiColor.GRAY
        formula = "Am"
        //molar = 243.0
        tempBoil = 1880
        tempMelt = 1449
        }

    @JvmField
    val CURIUM = HiiragiMaterial.builder("curium", 96) {
        color = RagiColor.GRAY
        formula = "Cm"
        //molar = 247.0
        tempBoil = 3383
        tempMelt = 1613
        }

    @JvmField
    val BERKELIUM = HiiragiMaterial.builder("berkelium", 97) {
        color = RagiColor.GRAY
        formula = "Bk"
        //molar = 247.0
        tempBoil = 2900
        tempMelt = 1259
        }

    @JvmField
    val CALIFORNIUM = HiiragiMaterial.builder("californium", 98) {
        color = RagiColor.GRAY
        formula = "Cf"
        //molar = 251.0/252.0
        tempBoil = 1743
        tempMelt = 1173
        }

    @JvmField
    val EINSTEINIUM = HiiragiMaterial.builder("einsteinium", 99) {
        color = RagiColor.GRAY
        formula = "Es"
        //molar = 252.0
        tempBoil = 1269
        tempMelt = 1133
        }

    @JvmField
    val FERMIUM = HiiragiMaterial.builder("fermium", 100) {
        color = RagiColor.WHITE
        formula = "Fm"
        //molar = 257.0
        //tempBoil = 
        //tempMelt = 1125/1800
        }

    @JvmField
    val MENDELEVIUM = HiiragiMaterial.builder("mendelevium", 101) {
        color = RagiColor.WHITE
        formula = "Md"
        //molar = 258.0
        //tempBoil = 
        //tempMelt = 1100
        }

    @JvmField
    val NOBELIUM = HiiragiMaterial.builder("nobelium", 102) {
        color = RagiColor.WHITE
        formula = "No"
        //molar = 259.0
        //tempBoil = 
        //tempMelt = 1100
        }

    @JvmField
    val LAWRENCIUM = HiiragiMaterial.builder("lawrencium", 103) {
        color = RagiColor.WHITE
        formula = "Lr"
        //molar = 262.0/266.0
        //tempBoil = 
        //tempMelt = 1900
        }
    // (Actinoid END)

    @JvmField
    val RUTHERFORDIUM = HiiragiMaterial.builder("rutherfordium", 104) {
        color = RagiColor.WHITE
        formula = "Lr"
        //molar = 261.1/267.0
        //tempBoil = 5800
        //tempMelt = 2400
        }

    @JvmField
    val DUBNIUM = HiiragiMaterial.builder("dubnium", 105) {
        color = RagiColor.WHITE
        formula = "Db"
        //molar = 265.0/268.0
        //tempBoil = 
        //tempMelt = 
        }

    @JvmField
    val SEABORGIUM = HiiragiMaterial.builder("seaborgium", 106) {
        color = RagiColor.WHITE
        formula = "Sg"
        //molar = 269.0/271.0
        //tempBoil = 
        //tempMelt = 
        }

    @JvmField
    val BOHRIUM = HiiragiMaterial.builder("bohrium", 107) {
        color = RagiColor.WHITE
        formula = "Bh"
        //molar = 270.0/272.0
        //tempBoil = 
        //tempMelt = 
        }

    @JvmField
    val HASSIUM = HiiragiMaterial.builder("hassium", 108) {
        color = RagiColor.WHITE
        formula = "Hs"
        //molar = 269.0/277.0
        //tempBoil = 
        //tempMelt = 
        }

    @JvmField
    val MEITNERIUM = HiiragiMaterial.builder("meitnerium", 109) {
        color = RagiColor.WHITE
        formula = "Mt"
        //molar = 276.0/278.0
        //tempBoil = 
        //tempMelt = 
        }

    @JvmField
    val DARMSTADTIUM = HiiragiMaterial.builder("darmstadtium", 110) {
        color = RagiColor.WHITE
        formula = "Ds"
        //molar = 281.0
        //tempBoil = 
        //tempMelt = 
        }

    @JvmField
    val ROENTGENIUM = HiiragiMaterial.builder("roentgenium", 111) {
        color = RagiColor.WHITE
        formula = "Rg"
        //molar = 280.0/281.0/282.0
        //tempBoil = 
        //tempMelt = 
        }

    @JvmField
    val COPERNICIUM = HiiragiMaterial.builder("copernicium", 112) {
        color = RagiColor.WHITE
        formula = "Cn"
        //molar = 285.0
        //tempBoil = 340
        //tempMelt = 283
        }

    @JvmField
    val NIHONIUM = HiiragiMaterial.builder("nihonium", 113) {
        color = RagiColor.WHITE
        formula = "Nh"
        //molar = 278.0/286.0
        //tempBoil = 1400
        //tempMelt = 700
        }

    @JvmField
    val FLEROVIUM = HiiragiMaterial.builder("flerovium", 114) {
        color = RagiColor.WHITE
        formula = "Fl"
        //molar = 289.0
        //tempBoil = 
        //tempMelt = 284
        }

    @JvmField
    val MOSCOVIUM = HiiragiMaterial.builder("moscovium", 115) {
        color = RagiColor.WHITE
        formula = "Mc"
        //molar = 289.0/290.0
        //tempBoil = 1400
        //tempMelt = 700
        }

    @JvmField
    val LIVERMORIUM = HiiragiMaterial.builder("livermorium", 116) {
        color = RagiColor.WHITE
        formula = "Lv"
        //molar = 293.0
        //tempBoil = 1100
        //tempMelt = 700
        }

    @JvmField
    val TENNESSINE = HiiragiMaterial.builder("tennessine", 117) {
        color = RagiColor.WHITE
        formula = "Ts"
        //molar = 293.0/294.0
        //tempBoil = 883
        //tempMelt = 700
        }

    @JvmField
    val OGANESSON = HiiragiMaterial.builder("oganesson", 118) {
        color = RagiColor.WHITE
        formula = "Og"
        //molar = 294.0
        //tempBoil = 450
        //tempMelt = 352
        }

    fun init() {
        // ELEMENTS //
        // --1st Period--
        registerMaterial(HYDROGEN)
        registerMaterial(HELIUM)
        // --2nd Period--
        registerMaterial(LITHIUM)
        registerMaterial(BERYLLIUM)
        registerMaterial(BORON)
        registerMaterial(CARBON)
        registerMaterial(NITROGEN)
        registerMaterial(OXYGEN)
        registerMaterial(FLUORINE)
        registerMaterial(NEON)
        // --3rd Period--
        registerMaterial(SODIUM)
        registerMaterial(MAGNESIUM)
        registerMaterial(ALUMINIUM)
        registerMaterial(SILICON)
        registerMaterial(PHOSPHORUS)
        registerMaterial(SULFUR)
        registerMaterial(CHLORINE)
        registerMaterial(ARGON)
        // --4th Period--
        registerMaterial(POTASSIUM)
        registerMaterial(CALCIUM)
        registerMaterial(SCANDIUM)
        registerMaterial(TITANIUM)
        registerMaterial(VANADIUM)
        registerMaterial(CHROMIUM)
        registerMaterial(MANGANESE)
        registerMaterial(IRON)
        registerMaterial(COBALT)
        registerMaterial(NICKEL)
        registerMaterial(COPPER)
        registerMaterial(ZINC)
        registerMaterial(GALLIUM)
        registerMaterial(GERMANIUM)
        registerMaterial(ARSENIC)
        registerMaterial(SELENIUM)
        registerMaterial(BROMINE)
        registerMaterial(KRYPTON)
        // --5th Period--
        registerMaterial(RUBIDIUM)
        registerMaterial(STRONTIUM)
        registerMaterial(YTTRIUM)
        registerMaterial(ZIRCONIUM)
        registerMaterial(NIOBIUM)
        registerMaterial(MOLYBDENUM)
        registerMaterial(TECHNETIUM)
        registerMaterial(RUTHENIUM)
        registerMaterial(RHODIUM)
        registerMaterial(PALLADIUM)
        registerMaterial(SILVER)
        registerMaterial(CADMIUM)
        registerMaterial(INDIUM)
        registerMaterial(TIN)
        registerMaterial(ANTIMONY)
        registerMaterial(TELLURIUM)
        registerMaterial(IODINE)
        registerMaterial(XENON)
        // --6th Period--
        registerMaterial(CAESIUM)
        registerMaterial(BARIUM)
        // (Lanthanoid START)
        registerMaterial(LANTHANUM)
        registerMaterial(CERIUM)
        registerMaterial(PRASEODYMIUM)
        registerMaterial(NEODYMIUM)
        registerMaterial(PROMETHIUM)
        registerMaterial(SAMARIUM)
        registerMaterial(EUROPIUM)
        registerMaterial(GADOLINIUM)
        registerMaterial(TERBIUM)
        registerMaterial(DYSPROSIUM)
        registerMaterial(HOLMIUM)
        registerMaterial(ERBIUM)
        registerMaterial(THULIUM)
        registerMaterial(YTTERBIUM)
        registerMaterial(LUTETIUM)
        // (Lanthanoid END)
        registerMaterial(HAFNIUM)
        registerMaterial(TANTALUM)
        registerMaterial(TUNGSTEN)
        registerMaterial(RHENIUM)
        registerMaterial(OSMIUM)
        registerMaterial(IRIDIUM)
        registerMaterial(PLATINUM)
        registerMaterial(GOLD)
        registerMaterial(MERCURY)
        registerMaterial(THALLIUM)
        registerMaterial(LEAD)
        registerMaterial(BISMUTH)
        registerMaterial(POLONIUM)
        registerMaterial(ASTATINE)
        registerMaterial(RADON)
        // --7th Period
        registerMaterial(FRANCIUM)
        registerMaterial(RADIUM)
        // (Actinoid START)
        registerMaterial(ACTINIUM)
        registerMaterial(THORIUM)
        registerMaterial(PROTACTINIUM)
        registerMaterial(URANIUM238)
        registerMaterial(NEPTUNIUM)
        registerMaterial(PLUTONIUM244)
        registerMaterial(AMERICIUM)
        registerMaterial(CURIUM)
        registerMaterial(BERKELIUM)
        registerMaterial(CALIFORNIUM)
        registerMaterial(EINSTEINIUM)
        registerMaterial(FERMIUM)
        registerMaterial(MENDELEVIUM)
        registerMaterial(NOBELIUM)
        registerMaterial(LAWRENCIUM)
        // (Actinoid END)
        registerMaterial(RUTHERFORDIUM)
        registerMaterial(DUBNIUM)
        registerMaterial(SEABORGIUM)
        registerMaterial(BOHRIUM)
        registerMaterial(HASSIUM)
        registerMaterial(MEITNERIUM)
        registerMaterial(DARMSTADTIUM)
        registerMaterial(ROENTGENIUM)
        registerMaterial(COPERNICIUM)
        registerMaterial(NIHONIUM)
        registerMaterial(FLEROVIUM)
        registerMaterial(MOSCOVIUM)
        registerMaterial(LIVERMORIUM)
        registerMaterial(TENNESSINE)
        registerMaterial(OGANESSON)

    }

}
