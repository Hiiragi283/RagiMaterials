package hiiragi283.material.material

import hiiragi283.material.RagiMaterials

object MaterialRegistry {

    private val REGISTRY: LinkedHashMap<String, HiiragiMaterial> = linkedMapOf()

    @JvmStatic
    fun getMaterials(): Collection<HiiragiMaterial> = REGISTRY.values

    @JvmStatic
    fun getMaterialFromName(name: String) = REGISTRY.getOrDefault(name, HiiragiMaterial.EMPTY)

    @JvmStatic
    fun registerMaterial(material: HiiragiMaterial) {
        val name = material.name
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
    val HYDROGEN = HiiragiMaterial.Builder("hydrogen", 1).build {
        color = RagiColor.BLUE.rgb
        formula = "H"
        molar = 1.0
        tempBoil = 20
        tempMelt = 14
        }

    @JvmField
    val HELIUM = HiiragiMaterial.Builder("helium", 2).build {
        color = RagiColor.YELLOW.rgb
        formula = "He"
        molar = 4.0
        tempBoil = 3
        tempMelt = 1
        }

    // --2nd Period--
    @JvmField
    val LITHIUM = HiiragiMaterial.Builder("lithium", 3).build {
        color = RagiColor.GRAY.rgb
        formula = "Li"
        molar = 6.9
        tempBoil = 1560
        tempMelt = 454
        }

    @JvmField
    val BERYLLIUM = HiiragiMaterial.Builder("beryllium", 4).build {
        color = RagiColor.DARK_GREEN.rgb
        formula = "Be"
        molar = 9.0
        tempBoil = 2742
        tempMelt = 1560
        }

    @JvmField
    val BORON = HiiragiMaterial.Builder("boron", 5).build {
        color = RagiColor.GRAY.rgb
        formula = "B"
        molar = 10.8
        tempBoil = 4200
        tempMelt = 2349
        }

    @JvmField
    val CARBON = HiiragiMaterial.Builder("carbon", 6).build {
        color = RagiColor.BLACK.rgb
        formula = "C"
        molar = 12.0
        //tempBoil = 4300
        //tempMelt = 4000
        tempSubl = 3915
        }

    @JvmField
    val NITROGEN = HiiragiMaterial.Builder("nitrogen", 7).build {
        color = RagiColor.DARK_AQUA.rgb
        formula = "N"
        molar = 14.0
        tempBoil = 77
        tempMelt = 63
        }

    @JvmField
    val OXYGEN = HiiragiMaterial.Builder("oxygen", 8).build {
        color = RagiColor.AQUA.rgb
        formula = "O"
        molar = 16.0
        tempBoil = 90
        tempMelt = 54
        }

    @JvmField
    val FLUORINE = HiiragiMaterial.Builder("fluorine", 9).build {
        color = RagiColor.BLUE.rgb
        formula = "F"
        molar = 19.0
        tempBoil = 85
        tempMelt = 54
        }

    @JvmField
    val NEON = HiiragiMaterial.Builder("neon", 10).build {
        color = RagiColor.DARK_AQUA.rgb
        formula = "Ne"
        molar = 20.2
        tempBoil = 27
        tempMelt = 25
        }

    // --3rd Period--
    @JvmField
    val SODIUM = HiiragiMaterial.Builder("sodium", 11).build {
        color = RagiColor.BLUE.rgb
        formula = "Na"
        molar = 23.0
        tempBoil = 1156
        tempMelt = 371
        }

    @JvmField
    val MAGNESIUM = HiiragiMaterial.Builder("magnesium", 12).build {
        color = RagiColor.GRAY.rgb
        formula = "Mg"
        molar = 24.3
        tempBoil = 1363
        tempMelt = 923
        }

    @JvmField
    val ALUMINIUM = HiiragiMaterial.Builder("aluminium", 13).build {
        color = RagiColor.AQUA.rgb
        formula = "Al"
        molar = 27.0
        tempBoil = 2792
        tempMelt = 933
        }

    @JvmField
    val SILICON = HiiragiMaterial.Builder("silicon", 14).build {
        color = RagiColor.DARK_GRAY.rgb
        formula = "Si"
        molar = 28.1
        tempBoil = 3538
        tempMelt = 1687
        }

    @JvmField
    val PHOSPHORUS = HiiragiMaterial.Builder("phosphorus", 15).build {
        color = RagiColor.WHITE.rgb
        formula = "P"
        molar = 31.0
        tempBoil = 550
        tempMelt = 317
        }

    @JvmField
    val SULFUR = HiiragiMaterial.Builder("sulfur", 16).build {
        color = RagiColor.YELLOW.rgb
        formula = "S"
        molar = 32.1
        tempBoil = 718
        tempMelt = 388
        }

    @JvmField
    val CHLORINE = HiiragiMaterial.Builder("chlorine", 17).build {
        color = RagiColor.YELLOW.rgb
        formula = "Cl"
        molar = 35.5
        tempBoil = 239
        tempMelt = 171
        }

    @JvmField
    val ARGON = HiiragiMaterial.Builder("argon", 18).build {
        color = RagiColor.GREEN.rgb
        formula = "Ar"
        molar = 40.0
        tempBoil = 87
        tempMelt = 84
        }

    // --4th Period--
    @JvmField
    val POTASSIUM = HiiragiMaterial.Builder("potassium", 19).build {
        color = RagiColor.GRAY.rgb
        formula = "K"
        molar = 39.1
        tempBoil = 1032
        tempMelt = 337
        }

    @JvmField
    val CALCIUM = HiiragiMaterial.Builder("calcium", 20).build {
        color = RagiColor.GRAY.rgb
        formula = "Ca"
        molar = 40.1
        tempBoil = 1757
        tempMelt = 1115
        }

    @JvmField
    val SCANDIUM = HiiragiMaterial.Builder("scandium", 21).build {
        color = RagiColor.GRAY.rgb
        formula = "Sc"
        molar = 45.0
        tempBoil = 3109
        tempMelt = 1814
        }

    @JvmField
    val TITANIUM = HiiragiMaterial.Builder("titanium", 22).build {
        color = RagiColor.LIGHT_PURPLE.rgb
        formula = "Ti"
        molar = 47.9
        tempBoil = 3560
        tempMelt = 1941
        }

    @JvmField
    val VANADIUM = HiiragiMaterial.Builder("vanadium", 23).build {
        color = RagiColor.DARK_GRAY.rgb
        formula = "V"
        molar = 50.9
        tempBoil = 3680
        tempMelt = 2183
        }

    @JvmField
    val CHROMIUM = HiiragiMaterial.Builder("chromium", 24).build {
        color = RagiColor.GRAY.rgb
        formula = "Cr"
        molar = 52.0
        tempBoil = 2944
        tempMelt = 2180
        }

    @JvmField
    val MANGANESE = HiiragiMaterial.Builder("manganese", 25).build {
        color = RagiColor.GRAY.rgb
        formula = "Mn"
        molar = 54.9
        tempBoil = 2334
        tempMelt = 1519
        }

    @JvmField
    val IRON = HiiragiMaterial.Builder("iron", 26).build {
        color = RagiColor.GRAY.rgb
        formula = "Fe"
        molar = 55.8
        tempBoil = 3134
        tempMelt = 1811
        }

    @JvmField
    val COBALT = HiiragiMaterial.Builder("cobalt", 27).build {
        color = RagiColor.BLUE.rgb
        formula = "Co"
        molar = 58.9
        tempBoil = 3200
        tempMelt = 1768
        }

    @JvmField
    val NICKEL = HiiragiMaterial.Builder("nickel", 28).build {
        color = RagiColor.GRAY.rgb
        formula = "Ni"
        molar = 58.7
        tempBoil = 3186
        tempMelt = 1728
        }

    @JvmField
    val COPPER = HiiragiMaterial.Builder("copper", 29).build {
        color = RagiColor.GOLD.rgb
        formula = "Cu"
        molar = 63.5
        tempBoil = 2835
        tempMelt = 1358
        }

    @JvmField
    val ZINC = HiiragiMaterial.Builder("zinc", 30).build {
        color = RagiColor.GRAY.rgb
        formula = "Zn"
        molar = 65.4
        tempBoil = 1180
        tempMelt = 693
        }

    @JvmField
    val GALLIUM = HiiragiMaterial.Builder("gallium", 31).build {
        color = RagiColor.GRAY.rgb
        formula = "Ga"
        molar = 69.7
        tempBoil = 2673
        tempMelt = 303
        }

    @JvmField
    val GERMANIUM = HiiragiMaterial.Builder("germanium", 32).build {
        color = RagiColor.GRAY.rgb
        formula = "Ge"
        molar = 72.6
        tempBoil = 3106
        tempMelt = 1211
        }

    @JvmField
    val ARSENIC = HiiragiMaterial.Builder("arsenic", 33).build {
        color = RagiColor.GRAY.rgb
        formula = "As"
        molar = 74.9
        //tempBoil = 887
        //tempMelt = 1090
        tempSubl = 887
        }

    @JvmField
    val SELENIUM = HiiragiMaterial.Builder("selenium", 34).build {
        color = RagiColor.BLACK.rgb
        formula = "Se"
        molar = 74.9
        tempBoil = 958
        tempMelt = 453
        }

    @JvmField
    val BROMINE = HiiragiMaterial.Builder("bromine", 35).build {
        color = RagiColor.GOLD.rgb
        formula = "Br"
        molar = 79.9
        tempBoil = 332
        tempMelt = 267
        }

    @JvmField
    val KRYPTON = HiiragiMaterial.Builder("krypton", 36).build {
        color = RagiColor.DARK_GREEN.rgb
        formula = "Kr"
        molar = 83.8
        tempBoil = 120
        tempMelt = 116
        }

    // --5th Period--
    @JvmField
    val RUBIDIUM = HiiragiMaterial.Builder("rubidium", 37).build {
        color = RagiColor.GRAY.rgb
        formula = "Rb"
        molar = 85.5
        tempBoil = 961
        tempMelt = 312
        }

    @JvmField
    val STRONTIUM = HiiragiMaterial.Builder("strontium", 38).build {
        color = RagiColor.GRAY.rgb
        formula = "Sr"
        molar = 87.6
        tempBoil = 1655
        tempMelt = 1050
        }

    @JvmField
    val YTTRIUM = HiiragiMaterial.Builder("yttrium", 39).build {
        color = RagiColor.GRAY.rgb
        formula = "Y"
        molar = 88.9
        tempBoil = 3609
        tempMelt = 1799
        }

    @JvmField
    val ZIRCONIUM = HiiragiMaterial.Builder("zirconium", 40).build {
        color = RagiColor.GRAY.rgb
        formula = "Zr"
        molar = 91.2
        tempBoil = 4682
        tempMelt = 2128
        }

    @JvmField
    val NIOBIUM = HiiragiMaterial.Builder("niobium", 41).build {
        color = RagiColor.GRAY.rgb
        formula = "Nb"
        molar = 92.9
        tempBoil = 5017
        tempMelt = 2750
        }

    @JvmField
    val MOLYBDENUM = HiiragiMaterial.Builder("molybdenum", 42).build {
        color = RagiColor.GRAY.rgb
        formula = "Mo"
        molar = 96.0
        tempBoil = 4912
        tempMelt = 2896
        }

    @JvmField
    val TECHNETIUM = HiiragiMaterial.Builder("technetium", 43).build {
        color = RagiColor.GRAY.rgb
        formula = "Tc"
        //molar = 97.0/98.0/99.0
        tempBoil = 4538
        tempMelt = 2430
        }

    @JvmField
    val RUTHENIUM = HiiragiMaterial.Builder("ruthenium", 44).build {
        color = RagiColor.GRAY.rgb
        formula = "Ru"
        molar = 101.1
        tempBoil = 4423
        tempMelt = 2607
        }

    @JvmField
    val RHODIUM = HiiragiMaterial.Builder("rhodium", 45).build {
        color = RagiColor.GRAY.rgb
        formula = "Rh"
        molar = 102.9
        tempBoil = 3968
        tempMelt = 2237
        }

    @JvmField
    val PALLADIUM = HiiragiMaterial.Builder("palladium", 46).build {
        color = RagiColor.GRAY.rgb
        formula = "Pd"
        molar = 106.4
        tempBoil = 3236
        tempMelt = 1828
        }

    @JvmField
    val SILVER = HiiragiMaterial.Builder("silver", 47).build {
        color = RagiColor.GRAY.rgb
        formula = "Ag"
        molar = 107.9
        tempBoil = 2435
        tempMelt = 1235
        }

    @JvmField
    val CADMIUM = HiiragiMaterial.Builder("cadmium", 48).build {
        color = RagiColor.GRAY.rgb
        formula = "Cd"
        molar = 112.4
        tempBoil = 1040
        tempMelt = 594
        }

    @JvmField
    val INDIUM = HiiragiMaterial.Builder("indium", 49).build {
        color = RagiColor.GRAY.rgb
        formula = "In"
        molar = 114.8
        tempBoil = 2345
        tempMelt = 430
        }

    @JvmField
    val TIN = HiiragiMaterial.Builder("tin", 50).build {
        color = RagiColor.GRAY.rgb
        formula = "Sn"
        molar = 118.7
        tempBoil = 2875
        tempMelt = 505
        }

    @JvmField
    val ANTIMONY = HiiragiMaterial.Builder("antimony", 51).build {
        color = RagiColor.GRAY.rgb
        formula = "Sb"
        molar = 121.8
        tempBoil = 1860
        tempMelt = 904
        }

    @JvmField
    val TELLURIUM = HiiragiMaterial.Builder("tellurium", 52).build {
        color = RagiColor.GRAY.rgb
        formula = "Te"
        molar = 127.6
        tempBoil = 1261
        tempMelt = 723
        }

    @JvmField
    val IODINE = HiiragiMaterial.Builder("iodine", 53).build {
        color = RagiColor.DARK_PURPLE.rgb
        formula = "I"
        molar = 126.9
        tempBoil = 457
        tempMelt = 387
        }

    @JvmField
    val XENON = HiiragiMaterial.Builder("xenon", 54).build {
        color = RagiColor.AQUA.rgb
        formula = "Xe"
        molar = 131.3
        tempBoil = 165
        tempMelt = 161
        }

    // 6th Period
    @JvmField
    val CAESIUM = HiiragiMaterial.Builder("caesium", 55).build {
        color = RagiColor.GRAY.rgb
        formula = "Cs"
        molar = 132.9
        tempBoil = 944
        tempMelt = 302
        }

    @JvmField
    val BARIUM = HiiragiMaterial.Builder("barium", 56).build {
        color = RagiColor.GRAY.rgb
        formula = "Ba"
        molar = 137.3
        tempBoil = 2170
        tempMelt = 1000
        }

    // (Lanthanoid START)
    @JvmField
    val LANTHANUM = HiiragiMaterial.Builder("lanthanum", 57).build {
        color = RagiColor.GRAY.rgb
        formula = "La"
        molar = 138.9
        tempBoil = 3737
        tempMelt = 1193
        }

    @JvmField
    val CERIUM = HiiragiMaterial.Builder("cerium", 58).build {
        color = RagiColor.GRAY.rgb
        formula = "Ce"
        molar = 140.1
        tempBoil = 3716
        tempMelt = 1068
        }

    @JvmField
    val PRASEODYMIUM = HiiragiMaterial.Builder("praseodymium", 59).build {
        color = RagiColor.GRAY.rgb
        formula = "Pr"
        molar = 140.9
        tempBoil = 3793
        tempMelt = 1208
        }

    @JvmField
    val NEODYMIUM = HiiragiMaterial.Builder("neodymium", 60).build {
        color = RagiColor.GRAY.rgb
        formula = "Nd"
        molar = 144.2
        tempBoil = 3347
        tempMelt = 1297
        }

    @JvmField
    val PROMETHIUM = HiiragiMaterial.Builder("promethium", 61).build {
        color = RagiColor.GRAY.rgb
        formula = "Pm"
        //molar = 145.0
        tempBoil = 3237
        tempMelt = 1315
        }

    @JvmField
    val SAMARIUM = HiiragiMaterial.Builder("samarium", 62).build {
        color = RagiColor.GRAY.rgb
        formula = "Sm"
        molar = 150.4
        tempBoil = 2067
        tempMelt = 1345
        }

    @JvmField
    val EUROPIUM = HiiragiMaterial.Builder("europium", 63).build {
        color = RagiColor.GRAY.rgb
        formula = "Eu"
        molar = 152.0
        tempBoil = 1802
        tempMelt = 1099
        }

    @JvmField
    val GADOLINIUM = HiiragiMaterial.Builder("gadolinium", 64).build {
        color = RagiColor.GRAY.rgb
        formula = "Gd"
        molar = 157.3
        tempBoil = 3546
        tempMelt = 1585
        }

    @JvmField
    val TERBIUM = HiiragiMaterial.Builder("terbium", 65).build {
        color = RagiColor.GRAY.rgb
        formula = "Tb"
        molar = 158.9
        tempBoil = 3503
        tempMelt = 1629
        }

    @JvmField
    val DYSPROSIUM = HiiragiMaterial.Builder("dysprosium", 66).build {
        color = RagiColor.GRAY.rgb
        formula = "Dy"
        molar = 157.3
        tempBoil = 2840
        tempMelt = 1680
        }

    @JvmField
    val HOLMIUM = HiiragiMaterial.Builder("holmium", 67).build {
        color = RagiColor.GRAY.rgb
        formula = "Ho"
        molar = 164.9
        tempBoil = 2993
        tempMelt = 1734
        }

    @JvmField
    val ERBIUM = HiiragiMaterial.Builder("erbium", 68).build {
        color = RagiColor.GRAY.rgb
        formula = "Er"
        molar = 167.3
        tempBoil = 3141
        tempMelt = 1802
        }

    @JvmField
    val THULIUM = HiiragiMaterial.Builder("thulium", 69).build {
        color = RagiColor.GRAY.rgb
        formula = "Tm"
        molar = 168.9
        tempBoil = 2223
        tempMelt = 1818
        }

    @JvmField
    val YTTERBIUM = HiiragiMaterial.Builder("ytterbium", 70).build {
        color = RagiColor.GRAY.rgb
        formula = "Yb"
        molar = 173.0
        tempBoil = 1469
        tempMelt = 1097
        }

    @JvmField
    val LUTETIUM = HiiragiMaterial.Builder("lutetium", 71).build {
        color = RagiColor.GRAY.rgb
        formula = "Lu"
        molar = 175.0
        tempBoil = 3675
        tempMelt = 1925
        }
    // (Lanthanoid END)

    @JvmField
    val HAFNIUM = HiiragiMaterial.Builder("hafnium", 72).build {
        color = RagiColor.GRAY.rgb
        formula = "Hf"
        molar = 178.5
        tempBoil = 4876
        tempMelt = 2506
        }

    @JvmField
    val TANTALUM = HiiragiMaterial.Builder("tantalum", 73).build {
        color = RagiColor.GRAY.rgb
        formula = "Ta"
        molar = 180.9
        tempBoil = 5731
        tempMelt = 3290
        }

    @JvmField
    val TUNGSTEN = HiiragiMaterial.Builder("tungsten", 74).build {
        color = RagiColor.GRAY.rgb
        formula = "W"
        molar = 183.8
        tempBoil = 5828
        tempMelt = 3695
        }

    @JvmField
    val RHENIUM = HiiragiMaterial.Builder("rhenium", 75).build {
        color = RagiColor.GRAY.rgb
        formula = "Re"
        molar = 186.2
        tempBoil = 5869
        tempMelt = 3459
        }

    @JvmField
    val OSMIUM = HiiragiMaterial.Builder("osmium", 76).build {
        color = RagiColor.DARK_BLUE.rgb
        formula = "Os"
        molar = 190.2
        tempBoil = 5285
        tempMelt = 3306
        }

    @JvmField
    val IRIDIUM = HiiragiMaterial.Builder("iridium", 77).build {
        color = RagiColor.GRAY.rgb
        formula = "Ir"
        molar = 192.2
        tempBoil = 4701
        tempMelt = 2719
        }

    @JvmField
    val PLATINUM = HiiragiMaterial.Builder("platinum", 78).build {
        color = RagiColor.GRAY.rgb
        formula = "Pt"
        molar = 195.1
        tempBoil = 4098
        tempMelt = 2041
        }

    @JvmField
    val GOLD = HiiragiMaterial.Builder("gold", 79).build {
        color = RagiColor.GOLD.rgb
        formula = "Au"
        molar = 197.0
        tempBoil = 3129
        tempMelt = 1337
        }

    @JvmField
    val MERCURY = HiiragiMaterial.Builder("mercury", 80).build {
        color = RagiColor.GRAY.rgb
        formula = "Hg"
        molar = 200.6
        tempBoil = 670
        tempMelt = 234
        }

    @JvmField
    val THALLIUM = HiiragiMaterial.Builder("thallium", 81).build {
        color = RagiColor.GRAY.rgb
        formula = "Tl"
        molar = 204.4
        tempBoil = 1749
        tempMelt = 577
        }

    @JvmField
    val LEAD = HiiragiMaterial.Builder("lead", 82).build {
        color = RagiColor.GRAY.rgb
        formula = "Pb"
        molar = 207.2
        tempBoil = 2022
        tempMelt = 601
        }

    @JvmField
    val BISMUTH = HiiragiMaterial.Builder("bismuth", 83).build {
        color = RagiColor.GRAY.rgb
        formula = "Bi"
        molar = 209.0
        tempBoil = 1837
        tempMelt = 545
        }

    @JvmField
    val POLONIUM = HiiragiMaterial.Builder("polonium", 84).build {
        color = RagiColor.GRAY.rgb
        formula = "Po"
        //molar = 209.0/210.0
        tempBoil = 1235
        tempMelt = 527
        }

    @JvmField
    val ASTATINE = HiiragiMaterial.Builder("astatine", 85).build {
        color = RagiColor.BLACK.rgb
        formula = "At"
        //molar = 210.0
        tempBoil = 610
        tempMelt = 575
        }

    @JvmField
    val RADON = HiiragiMaterial.Builder("radon", 86).build {
        color = RagiColor.LIGHT_PURPLE.rgb
        formula = "Rn"
        //molar = 222.0
        tempBoil = 211
        tempMelt = 202
        }

    // 7th Period
    @JvmField
    val FRANCIUM = HiiragiMaterial.Builder("francium", 87).build {
        color = RagiColor.GRAY.rgb
        formula = "Fr"
        //molar = 223.0
        tempBoil = 890
        tempMelt = 281
        }

    @JvmField
    val RADIUM = HiiragiMaterial.Builder("radium", 88).build {
        color = RagiColor.GRAY.rgb
        formula = "Ra"
        //molar = 226.0
        tempBoil = 2010
        tempMelt = 973
        }

    // (Actinoid START)
    @JvmField
    val ACTINIUM = HiiragiMaterial.Builder("actinium", 89).build {
        color = RagiColor.DARK_AQUA.rgb
        formula = "Ac"
        //molar = 227.0
        tempBoil = 3471
        tempMelt = 1323
        }

    @JvmField
    val THORIUM = HiiragiMaterial.Builder("thorium", 90).build {
        color = RagiColor.GRAY.rgb
        formula = "Th"
        molar = 232.0
        tempBoil = 5061
        tempMelt = 2115
        }

    @JvmField
    val PROTACTINIUM = HiiragiMaterial.Builder("protactinium", 91).build {
        color = RagiColor.GRAY.rgb
        formula = "Pa"
        molar = 231.0
        tempBoil = 4300
        tempMelt = 1841
        }

    @JvmField
    val URANIUM238 = HiiragiMaterial.Builder("uranium238", 92).build {
        color = RagiColor.GREEN.rgb
        formula = "U238"
        molar = 238.0
        tempBoil = 4404
        tempMelt = 1405
        }

    @JvmField
    val NEPTUNIUM = HiiragiMaterial.Builder("neptunium", 93).build {
        color = RagiColor.GRAY.rgb
        formula = "Np"
        //molar = 237.0
        tempBoil = 4273
        tempMelt = 917
        }

    @JvmField
    val PLUTONIUM244 = HiiragiMaterial.Builder("plutonium244", 94).build {
        color = RagiColor.DARK_RED.rgb
        formula = "Pu244"
        molar = 244.1
        tempBoil = 3501
        tempMelt = 913
        }

    @JvmField
    val AMERICIUM = HiiragiMaterial.Builder("americium", 95).build {
        color = RagiColor.GRAY.rgb
        formula = "Am"
        //molar = 243.0
        tempBoil = 1880
        tempMelt = 1449
        }

    @JvmField
    val CURIUM = HiiragiMaterial.Builder("curium", 96).build {
        color = RagiColor.GRAY.rgb
        formula = "Cm"
        //molar = 247.0
        tempBoil = 3383
        tempMelt = 1613
        }

    @JvmField
    val BERKELIUM = HiiragiMaterial.Builder("berkelium", 97).build {
        color = RagiColor.GRAY.rgb
        formula = "Bk"
        //molar = 247.0
        tempBoil = 2900
        tempMelt = 1259
        }

    @JvmField
    val CALIFORNIUM = HiiragiMaterial.Builder("californium", 98).build {
        color = RagiColor.GRAY.rgb
        formula = "Cf"
        //molar = 251.0/252.0
        tempBoil = 1743
        tempMelt = 1173
        }

    @JvmField
    val EINSTEINIUM = HiiragiMaterial.Builder("einsteinium", 99).build {
        color = RagiColor.GRAY.rgb
        formula = "Es"
        //molar = 252.0
        tempBoil = 1269
        tempMelt = 1133
        }

    @JvmField
    val FERMIUM = HiiragiMaterial.Builder("fermium", 100).build {
        color = RagiColor.WHITE.rgb
        formula = "Fm"
        //molar = 257.0
        //tempBoil = 
        //tempMelt = 1125/1800
        }

    @JvmField
    val MENDELEVIUM = HiiragiMaterial.Builder("mendelevium", 101).build {
        color = RagiColor.WHITE.rgb
        formula = "Md"
        //molar = 258.0
        //tempBoil = 
        //tempMelt = 1100
        }

    @JvmField
    val NOBELIUM = HiiragiMaterial.Builder("nobelium", 102).build {
        color = RagiColor.WHITE.rgb
        formula = "No"
        //molar = 259.0
        //tempBoil = 
        //tempMelt = 1100
        }

    @JvmField
    val LAWRENCIUM = HiiragiMaterial.Builder("lawrencium", 103).build {
        color = RagiColor.WHITE.rgb
        formula = "Lr"
        //molar = 262.0/266.0
        //tempBoil = 
        //tempMelt = 1900
        }
    // (Actinoid END)

    @JvmField
    val RUTHERFORDIUM = HiiragiMaterial.Builder("rutherfordium", 104).build {
        color = RagiColor.WHITE.rgb
        formula = "Lr"
        //molar = 261.1/267.0
        //tempBoil = 5800
        //tempMelt = 2400
        }

    @JvmField
    val DUBNIUM = HiiragiMaterial.Builder("dubnium", 105).build {
        color = RagiColor.WHITE.rgb
        formula = "Db"
        //molar = 265.0/268.0
        //tempBoil = 
        //tempMelt = 
        }

    @JvmField
    val SEABORGIUM = HiiragiMaterial.Builder("seaborgium", 106).build {
        color = RagiColor.WHITE.rgb
        formula = "Sg"
        //molar = 269.0/271.0
        //tempBoil = 
        //tempMelt = 
        }

    @JvmField
    val BOHRIUM = HiiragiMaterial.Builder("bohrium", 107).build {
        color = RagiColor.WHITE.rgb
        formula = "Bh"
        //molar = 270.0/272.0
        //tempBoil = 
        //tempMelt = 
        }

    @JvmField
    val HASSIUM = HiiragiMaterial.Builder("hassium", 108).build {
        color = RagiColor.WHITE.rgb
        formula = "Hs"
        //molar = 269.0/277.0
        //tempBoil = 
        //tempMelt = 
        }

    @JvmField
    val MEITNERIUM = HiiragiMaterial.Builder("meitnerium", 109).build {
        color = RagiColor.WHITE.rgb
        formula = "Mt"
        //molar = 276.0/278.0
        //tempBoil = 
        //tempMelt = 
        }

    @JvmField
    val DARMSTADTIUM = HiiragiMaterial.Builder("darmstadtium", 110).build {
        color = RagiColor.WHITE.rgb
        formula = "Ds"
        //molar = 281.0
        //tempBoil = 
        //tempMelt = 
        }

    @JvmField
    val ROENTGENIUM = HiiragiMaterial.Builder("roentgenium", 111).build {
        color = RagiColor.WHITE.rgb
        formula = "Rg"
        //molar = 280.0/281.0/282.0
        //tempBoil = 
        //tempMelt = 
        }

    @JvmField
    val COPERNICIUM = HiiragiMaterial.Builder("copernicium", 112).build {
        color = RagiColor.WHITE.rgb
        formula = "Cn"
        //molar = 285.0
        //tempBoil = 340
        //tempMelt = 283
        }

    @JvmField
    val NIHONIUM = HiiragiMaterial.Builder("nihonium", 113).build {
        color = RagiColor.WHITE.rgb
        formula = "Nh"
        //molar = 278.0/286.0
        //tempBoil = 1400
        //tempMelt = 700
        }

    @JvmField
    val FLEROVIUM = HiiragiMaterial.Builder("flerovium", 114).build {
        color = RagiColor.WHITE.rgb
        formula = "Fl"
        //molar = 289.0
        //tempBoil = 
        //tempMelt = 284
        }

    @JvmField
    val MOSCOVIUM = HiiragiMaterial.Builder("moscovium", 115).build {
        color = RagiColor.WHITE.rgb
        formula = "Mc"
        //molar = 289.0/290.0
        //tempBoil = 1400
        //tempMelt = 700
        }

    @JvmField
    val LIVERMORIUM = HiiragiMaterial.Builder("livermorium", 116).build {
        color = RagiColor.WHITE.rgb
        formula = "Lv"
        //molar = 293.0
        //tempBoil = 1100
        //tempMelt = 700
        }

    @JvmField
    val TENNESSINE = HiiragiMaterial.Builder("tennessine", 117).build {
        color = RagiColor.WHITE.rgb
        formula = "Ts"
        //molar = 293.0/294.0
        //tempBoil = 883
        //tempMelt = 700
        }

    @JvmField
    val OGANESSON = HiiragiMaterial.Builder("oganesson", 118).build {
        color = RagiColor.WHITE.rgb
        formula = "Og"
        //molar = 294.0
        //tempBoil = 450
        //tempMelt = 352
        }

    fun init() {
        MaterialElements.init()
    }

}
