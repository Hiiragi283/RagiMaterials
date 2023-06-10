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

    // ELEMENTS //
    // --1st Period--
    @JvmField
    val HYDROGEN = HiiragiMaterial.Builder("hydrogen", 1)
        .setColor(RagiColor.BLUE)
        .setFormula("H")
        .setMolar(1.0)
        .setTempBoil(20)
        .setTempMelt(14)
        .build()

    @JvmField
    val HELIUM = HiiragiMaterial.Builder("helium", 2)
        .setColor(RagiColor.YELLOW)
        .setFormula("He")
        .setMolar(4.0)
        .build()

    // --2nd Period--
    @JvmField
    val LITHIUM = HiiragiMaterial.Builder("lithium", 3)
        .setColor(RagiColor.GRAY)
        .setFormula("Li")
        .setMolar(6.9)
        .build()

    @JvmField
    val BERYLLIUM = HiiragiMaterial.Builder("beryllium", 4)
        .setColor(RagiColor.DARK_GREEN)
        .setFormula("Be")
        .setMolar(9.0)
        .build()

    @JvmField
    val BORON = HiiragiMaterial.Builder("boron", 5)
        .setColor(RagiColor.GRAY)
        .setFormula("B")
        .setMolar(10.8)
        .build()

    @JvmField
    val CARBON = HiiragiMaterial.Builder("carbon", 6)
        .setColor(RagiColor.BLACK)
        .setFormula("C")
        .setMolar(12.0)
        .build()

    @JvmField
    val NITROGEN = HiiragiMaterial.Builder("nitrogen", 7)
        .setColor(RagiColor.DARK_AQUA)
        .setFormula("N")
        .setMolar(14.0)
        .build()

    @JvmField
    val OXYGEN = HiiragiMaterial.Builder("oxygen", 8)
        .setColor(RagiColor.AQUA)
        .setFormula("O")
        .setMolar(16.0)
        .build()

    @JvmField
    val FLUORINE = HiiragiMaterial.Builder("fluorine", 9)
        .setColor(RagiColor.BLUE)
        .setFormula("F")
        .setMolar(19.0)
        .build()

    @JvmField
    val NEON = HiiragiMaterial.Builder("neon", 10)
        .setColor(RagiColor.DARK_AQUA)
        .setFormula("Ne")
        .setMolar(20.2)
        .build()

    // --3rd Period--
    @JvmField
    val SODIUM = HiiragiMaterial.Builder("sodium", 11)
        .setColor(RagiColor.BLUE)
        .setFormula("Na")
        .setMolar(23.0)
        .build()

    @JvmField
    val MAGNESIUM = HiiragiMaterial.Builder("magnesium", 12)
        .setColor(RagiColor.GRAY)
        .setFormula("Mg")
        .setMolar(24.3)
        .build()

    @JvmField
    val ALUMINIUM = HiiragiMaterial.Builder("aluminium", 13)
        .setColor(RagiColor.AQUA)
        .setFormula("Al")
        .setMolar(27.0)
        .build()

    @JvmField
    val SILICON = HiiragiMaterial.Builder("silicon", 14)
        .setColor(RagiColor.DARK_GRAY)
        .setFormula("Si")
        .setMolar(28.1)
        .build()

    @JvmField
    val PHOSPHORUS = HiiragiMaterial.Builder("phosphorus", 15)
        .setColor(RagiColor.WHITE)
        .setFormula("P")
        .setMolar(31.0)
        .build()

    @JvmField
    val SULFUR = HiiragiMaterial.Builder("sulfur", 16)
        .setColor(RagiColor.YELLOW)
        .setFormula("S")
        .setMolar(32.1)
        .build()

    @JvmField
    val CHLORINE = HiiragiMaterial.Builder("chlorine", 17)
        .setColor(RagiColor.YELLOW)
        .setFormula("Cl")
        .setMolar(35.5)
        .build()

    @JvmField
    val ARGON = HiiragiMaterial.Builder("argon", 18)
        .setColor(RagiColor.GREEN)
        .setFormula("Ar")
        .setMolar(40.0)
        .build()

    // --4th Period--
    @JvmField
    val POTASSIUM = HiiragiMaterial.Builder("potassium", 19)
        .setColor(RagiColor.GRAY)
        .setFormula("K")
        .setMolar(39.1)
        .build()

    @JvmField
    val CALCIUM = HiiragiMaterial.Builder("calcium", 20)
        .setColor(RagiColor.GRAY)
        .setFormula("Ca")
        .setMolar(40.1)
        .build()

    @JvmField
    val SCANDIUM = HiiragiMaterial.Builder("scandium", 21)
        .setColor(RagiColor.GRAY)
        .setFormula("Sc")
        .setMolar(45.0)
        .build()

    @JvmField
    val TITANIUM = HiiragiMaterial.Builder("titanium", 22)
        .setColor(RagiColor.LIGHT_PURPLE)
        .setFormula("Ti")
        .setMolar(47.9)
        .build()

    @JvmField
    val VANADIUM = HiiragiMaterial.Builder("vanadium", 23)
        .setColor(RagiColor.DARK_GRAY)
        .setFormula("V")
        .setMolar(50.9)
        .build()

    @JvmField
    val CHROMIUM = HiiragiMaterial.Builder("chromium", 24)
        .setColor(RagiColor.GRAY)
        .setFormula("Cr")
        .setMolar(52.0)
        .build()

    @JvmField
    val MANGANESE = HiiragiMaterial.Builder("manganese", 25)
        .setColor(RagiColor.GRAY)
        .setFormula("Mn")
        .setMolar(54.9)
        .build()

    @JvmField
    val IRON = HiiragiMaterial.Builder("iron", 26)
        .setColor(RagiColor.GRAY)
        .setFormula("Fe")
        .setMolar(55.8)
        .build()

    @JvmField
    val COBALT = HiiragiMaterial.Builder("cobalt", 27)
        .setColor(RagiColor.BLUE)
        .setFormula("Co")
        .setMolar(58.9)
        .build()

    @JvmField
    val NICKEL = HiiragiMaterial.Builder("nickel", 28)
        .setColor(RagiColor.GRAY)
        .setFormula("Ni")
        .setMolar(58.7)
        .build()

    @JvmField
    val COPPER = HiiragiMaterial.Builder("copper", 29)
        .setColor(RagiColor.GOLD)
        .setFormula("Cu")
        .setMolar(63.5)
        .build()

    @JvmField
    val ZINC = HiiragiMaterial.Builder("zinc", 30)
        .setColor(RagiColor.GRAY)
        .setFormula("Zn")
        .setMolar(65.4)
        .build()

    @JvmField
    val GALLIUM = HiiragiMaterial.Builder("gallium", 31)
        .setColor(RagiColor.GRAY)
        .setFormula("Ga")
        .setMolar(69.7)
        .build()

    @JvmField
    val GERMANIUM = HiiragiMaterial.Builder("germanium", 32)
        .setColor(RagiColor.GRAY)
        .setFormula("Ge")
        .setMolar(72.6)
        .build()

    @JvmField
    val ARSENIC = HiiragiMaterial.Builder("arsenic", 33)
        .setColor(RagiColor.GRAY)
        .setFormula("As")
        .setMolar(74.9)
        .build()

    @JvmField
    val SELENIUM = HiiragiMaterial.Builder("selenium", 34)
        .setColor(RagiColor.BLACK)
        .setFormula("Se")
        .setMolar(74.9)
        .build()

    @JvmField
    val BROMINE = HiiragiMaterial.Builder("bromine", 35)
        .setColor(RagiColor.GOLD)
        .setFormula("Br")
        .setMolar(79.9)
        .build()

    @JvmField
    val KRYPTON = HiiragiMaterial.Builder("krypton", 36)
        .setColor(RagiColor.DARK_GREEN)
        .setFormula("Kr")
        .setMolar(83.8)
        .build()

    // --5th Period--
    @JvmField
    val RUBIDIUM = HiiragiMaterial.Builder("rubidium", 37)
        .setColor(RagiColor.GRAY)
        .setFormula("Rb")
        .setMolar(85.5)
        .build()

    @JvmField
    val STRONTIUM = HiiragiMaterial.Builder("strontium", 38)
        .setColor(RagiColor.GRAY)
        .setFormula("Sr")
        .setMolar(87.6)
        .build()

    @JvmField
    val YTTRIUM = HiiragiMaterial.Builder("yttrium", 39)
        .setColor(RagiColor.GRAY)
        .setFormula("Y")
        .setMolar(88.9)
        .build()

    @JvmField
    val ZIRCONIUM = HiiragiMaterial.Builder("zirconium", 40)
        .setColor(RagiColor.GRAY)
        .setFormula("Zr")
        .setMolar(91.2)
        .build()

    @JvmField
    val NIOBIUM = HiiragiMaterial.Builder("niobium", 41)
        .setColor(RagiColor.GRAY)
        .setFormula("Nb")
        .setMolar(92.9)
        .build()

    @JvmField
    val MOLYBDENUM = HiiragiMaterial.Builder("molybdenum", 42)
        .setColor(RagiColor.GRAY)
        .setFormula("Mo")
        .setMolar(96.0)
        .build()

    @JvmField
    val TECHNETIUM = HiiragiMaterial.Builder("technetium", 43)
        .setColor(RagiColor.GRAY)
        .setFormula("Tc")
        //.setMolar(97.0/98.0/99.0)
        .build()

    @JvmField
    val RUTHENIUM = HiiragiMaterial.Builder("ruthenium", 44)
        .setColor(RagiColor.GRAY)
        .setFormula("Ru")
        .setMolar(101.1)
        .build()

    @JvmField
    val RHODIUM = HiiragiMaterial.Builder("rhodium", 45)
        .setColor(RagiColor.GRAY)
        .setFormula("Rh")
        .setMolar(102.9)
        .build()

    @JvmField
    val PALLADIUN = HiiragiMaterial.Builder("palladium", 46)
        .setColor(RagiColor.GRAY)
        .setFormula("Pd")
        .setMolar(106.4)
        .build()

    @JvmField
    val SILVER = HiiragiMaterial.Builder("silver", 47)
        .setColor(RagiColor.GRAY)
        .setFormula("Ag")
        .setMolar(107.9)
        .build()

    @JvmField
    val CADMIUM = HiiragiMaterial.Builder("cadmium", 48)
        .setColor(RagiColor.GRAY)
        .setFormula("Cd")
        .setMolar(112.4)
        .build()

    @JvmField
    val INDIUM = HiiragiMaterial.Builder("indium", 49)
        .setColor(RagiColor.GRAY)
        .setFormula("In")
        .setMolar(114.8)
        .build()

    @JvmField
    val TIN = HiiragiMaterial.Builder("tin", 50)
        .setColor(RagiColor.GRAY)
        .setFormula("Sn")
        .setMolar(118.7)
        .build()

    @JvmField
    val ANTIMONY = HiiragiMaterial.Builder("antimony", 51)
        .setColor(RagiColor.GRAY)
        .setFormula("Sb")
        .setMolar(121.8)
        .build()

    @JvmField
    val TELLURIUM = HiiragiMaterial.Builder("tellurium", 52)
        .setColor(RagiColor.GRAY)
        .setFormula("Te")
        .setMolar(127.6)
        .build()

    @JvmField
    val IODINE = HiiragiMaterial.Builder("iodine", 53)
        .setColor(RagiColor.DARK_PURPLE)
        .setFormula("I")
        .setMolar(126.9)
        .build()

    @JvmField
    val XENON = HiiragiMaterial.Builder("xenon", 54)
        .setColor(RagiColor.AQUA)
        .setFormula("Xe")
        .setMolar(131.3)
        .build()

    // 6th Period
    @JvmField
    val CAESIUM = HiiragiMaterial.Builder("caesium", 55)
        .setColor(RagiColor.GRAY)
        .setFormula("Cs")
        .setMolar(132.9)
        .build()

    @JvmField
    val BARIUM = HiiragiMaterial.Builder("barium", 56)
        .setColor(RagiColor.GRAY)
        .setFormula("Ba")
        .setMolar(137.3)
        .build()

    // (Lanthanoid START)
    @JvmField
    val LANTHANUM = HiiragiMaterial.Builder("lanthanoum", 57)
        .setColor(RagiColor.GRAY)
        .setFormula("La")
        .setMolar(138.9)
        .build()

    @JvmField
    val CERIUM = HiiragiMaterial.Builder("cerium", 58)
        .setColor(RagiColor.GRAY)
        .setFormula("Ce")
        .setMolar(140.1)
        .build()

    @JvmField
    val PRASEODYMIUM = HiiragiMaterial.Builder("praseodymium", 59)
        .setColor(RagiColor.GRAY)
        .setFormula("Pr")
        .setMolar(140.9)
        .build()

    @JvmField
    val NEODYMIUM = HiiragiMaterial.Builder("neodymium", 60)
        .setColor(RagiColor.GRAY)
        .setFormula("Nd")
        .setMolar(144.2)
        .build()

    @JvmField
    val PROMETHIUM = HiiragiMaterial.Builder("promethium", 61)
        .setColor(RagiColor.GRAY)
        .setFormula("Pm")
        //.setMolar(145.0)
        .build()

    @JvmField
    val SAMARIUM = HiiragiMaterial.Builder("samarium", 62)
        .setColor(RagiColor.GRAY)
        .setFormula("Sm")
        .setMolar(150.4)
        .build()

    @JvmField
    val EUROPIUM = HiiragiMaterial.Builder("europium", 63)
        .setColor(RagiColor.GRAY)
        .setFormula("Eu")
        .setMolar(152.0)
        .build()

    @JvmField
    val GADOLINIUM = HiiragiMaterial.Builder("gadolinium", 64)
        .setColor(RagiColor.GRAY)
        .setFormula("Gd")
        .setMolar(157.3)
        .build()

    @JvmField
    val TERBIUM = HiiragiMaterial.Builder("terbium", 65)
        .setColor(RagiColor.GRAY)
        .setFormula("Tb")
        .setMolar(158.9)
        .build()

    @JvmField
    val DYSPROSIUM = HiiragiMaterial.Builder("dysprosium", 66)
        .setColor(RagiColor.GRAY)
        .setFormula("Dy")
        .setMolar(157.3)
        .build()

    @JvmField
    val HOLMIUM = HiiragiMaterial.Builder("holmium", 67)
        .setColor(RagiColor.GRAY)
        .setFormula("Ho")
        .setMolar(164.9)
        .build()

    @JvmField
    val ERBIUM = HiiragiMaterial.Builder("erbium", 68)
        .setColor(RagiColor.GRAY)
        .setFormula("Er")
        .setMolar(167.3)
        .build()

    @JvmField
    val THULIUM = HiiragiMaterial.Builder("thulium", 69)
        .setColor(RagiColor.GRAY)
        .setFormula("Tm")
        .setMolar(168.9)
        .build()

    @JvmField
    val YTTERBIUM = HiiragiMaterial.Builder("ytterbium", 70)
        .setColor(RagiColor.GRAY)
        .setFormula("Yb")
        .setMolar(173.0)
        .build()

    @JvmField
    val LUTETIUM = HiiragiMaterial.Builder("lutetium", 71)
        .setColor(RagiColor.GRAY)
        .setFormula("Lu")
        .setMolar(175.0)
        .build()
    // (Lanthanoid END)

    @JvmField
    val HAFNIUM = HiiragiMaterial.Builder("hafnium", 72)
        .setColor(RagiColor.GRAY)
        .setFormula("Hf")
        .setMolar(178.5)
        .build()

    @JvmField
    val TANTALUM = HiiragiMaterial.Builder("tantalum", 73)
        .setColor(RagiColor.GRAY)
        .setFormula("Ta")
        .setMolar(180.9)
        .build()

    @JvmField
    val TUNGSTEN = HiiragiMaterial.Builder("tungsten", 74)
        .setColor(RagiColor.GRAY)
        .setFormula("W")
        .setMolar(183.8)
        .build()

    @JvmField
    val RHENIUM = HiiragiMaterial.Builder("rhenium", 75)
        .setColor(RagiColor.GRAY)
        .setFormula("Re")
        .setMolar(186.2)
        .build()

    @JvmField
    val OSMIUM = HiiragiMaterial.Builder("osmium", 76)
        .setColor(RagiColor.DARK_BLUE)
        .setFormula("Os")
        .setMolar(190.2)
        .build()

    @JvmField
    val IRIDIUM = HiiragiMaterial.Builder("iridium", 77)
        .setColor(RagiColor.GRAY)
        .setFormula("Ir")
        .setMolar(192.2)
        .build()

    @JvmField
    val PLATINUM = HiiragiMaterial.Builder("platinum", 78)
        .setColor(RagiColor.GRAY)
        .setFormula("Pt")
        .setMolar(195.1)
        .build()

    @JvmField
    val GOLD = HiiragiMaterial.Builder("gold", 79)
        .setColor(RagiColor.GOLD)
        .setFormula("Au")
        .setMolar(197.0)
        .build()

    @JvmField
    val MERCURY = HiiragiMaterial.Builder("mercury", 80)
        .setColor(RagiColor.GRAY)
        .setFormula("Hg")
        .setMolar(200.6)
        .build()

    @JvmField
    val THALLIUM = HiiragiMaterial.Builder("thallium", 81)
        .setColor(RagiColor.GRAY)
        .setFormula("Tl")
        .setMolar(204.4)
        .build()

    @JvmField
    val LEAD = HiiragiMaterial.Builder("lead", 82)
        .setColor(RagiColor.GRAY)
        .setFormula("Pb")
        .setMolar(207.2)
        .build()

    @JvmField
    val BISMUTH = HiiragiMaterial.Builder("bismuth", 83)
        .setColor(RagiColor.GRAY)
        .setFormula("Bi")
        .setMolar(209.0)
        .build()

    @JvmField
    val POLONIUM = HiiragiMaterial.Builder("polonium", 84)
        .setColor(RagiColor.GRAY)
        .setFormula("Po")
        //.setMolar(209.0/210.0)
        .build()

    @JvmField
    val ASTATINE = HiiragiMaterial.Builder("astatine", 85)
        .setColor(RagiColor.BLACK)
        .setFormula("At")
        //.setMolar(210.0)
        .build()

    @JvmField
    val RADON = HiiragiMaterial.Builder("radon", 86)
        .setColor(RagiColor.LIGHT_PURPLE)
        .setFormula("Rn")
        //.setMolar(222.0)
        .build()

    // 7th Period
    @JvmField
    val FRANCIUM = HiiragiMaterial.Builder("francium", 87)
        .setColor(RagiColor.GRAY)
        .setFormula("Fr")
        //.setMolar(223.0)
        .build()

    @JvmField
    val RADIUM = HiiragiMaterial.Builder("radium", 88)
        .setColor(RagiColor.GRAY)
        .setFormula("Ra")
        //.setMolar(226.0)
        .build()

    // (Actinoid START)
    @JvmField
    val ACTINIUM = HiiragiMaterial.Builder("actinium", 89)
        .setColor(RagiColor.DARK_AQUA)
        .setFormula("Ac")
        //.setMolar(227.0)
        .build()

    @JvmField
    val THORIUM = HiiragiMaterial.Builder("thorium", 90)
        .setColor(RagiColor.GRAY)
        .setFormula("Th")
        .setMolar(232.0)
        .build()

    @JvmField
    val PROTACTINIUM = HiiragiMaterial.Builder("protactinium", 91)
        .setColor(RagiColor.GRAY)
        .setFormula("Pa")
        .setMolar(231.0)
        .build()

    @JvmField
    val URANIUM238 = HiiragiMaterial.Builder("uranium238", 92)
        .setColor(RagiColor.GREEN)
        .setFormula("U238")
        .setMolar(238.0)
        .build()

    @JvmField
    val NEPTUNIUM = HiiragiMaterial.Builder("neptunium", 93)
        .setColor(RagiColor.GRAY)
        .setFormula("Np")
        //.setMolar(237.0)
        .build()

    @JvmField
    val PLUTONIUM244 = HiiragiMaterial.Builder("plutonium244", 94)
        .setColor(RagiColor.DARK_RED)
        .setFormula("Pu244")
        .setMolar(244.1)
        .build()

    @JvmField
    val AMERICIUM = HiiragiMaterial.Builder("americium", 95)
        .setColor(RagiColor.GRAY)
        .setFormula("Am")
        //.setMolar(243.0)
        .build()

    @JvmField
    val CURIUM = HiiragiMaterial.Builder("curium", 96)
        .setColor(RagiColor.GRAY)
        .setFormula("Cm")
        //.setMolar(247.0)
        .build()

    @JvmField
    val BERKELIUM = HiiragiMaterial.Builder("berkelium", 97)
        .setColor(RagiColor.GRAY)
        .setFormula("Bk")
        //.setMolar(247.0)
        .build()

    @JvmField
    val CALIFORNIUM = HiiragiMaterial.Builder("californium", 98)
        .setColor(RagiColor.GRAY)
        .setFormula("Cf")
        //.setMolar(251.0/252.0)
        .build()

    @JvmField
    val EINSTEINIUM = HiiragiMaterial.Builder("einsteinium", 99)
        .setColor(RagiColor.GRAY)
        .setFormula("Es")
        //.setMolar(252.0)
        .build()

    @JvmField
    val FERMIUM = HiiragiMaterial.Builder("fermium", 100)
        .setColor(RagiColor.WHITE)
        .setFormula("Fm")
        //.setMolar(257.0)
        .build()

    @JvmField
    val MENDELEVIUM = HiiragiMaterial.Builder("mendelevium", 101)
        .setColor(RagiColor.WHITE)
        .setFormula("Md")
        //.setMolar(258.0)
        .build()

    @JvmField
    val NOBELIUM = HiiragiMaterial.Builder("nobelium", 102)
        .setColor(RagiColor.WHITE)
        .setFormula("No")
        //.setMolar(259.0)
        .build()

    @JvmField
    val LAWRENCIUM = HiiragiMaterial.Builder("larrencium", 103)
        .setColor(RagiColor.WHITE)
        .setFormula("Lr")
        //.setMolar(262.0/266.0)
        .build()
    // (Actinoid END)

    @JvmField
    val RUTHERFORDIUM = HiiragiMaterial.Builder("rutherfordium", 104)
        .setColor(RagiColor.WHITE)
        .setFormula("Lr")
        //.setMolar(261.1/267.0)
        .build()

    @JvmField
    val DUBNIUM = HiiragiMaterial.Builder("dubnium", 105)
        .setColor(RagiColor.WHITE)
        .setFormula("Db")
        //.setMolar(265.0/268.0)
        .build()

    @JvmField
    val SEABORGIUM = HiiragiMaterial.Builder("seaborgium", 106)
        .setColor(RagiColor.WHITE)
        .setFormula("Sg")
        //.setMolar(269.0/271.0)
        .build()

    @JvmField
    val BOHRIUM = HiiragiMaterial.Builder("bohrium", 107)
        .setColor(RagiColor.WHITE)
        .setFormula("Bh")
        //.setMolar(270.0/272.0)
        .build()

    @JvmField
    val HASSIUM = HiiragiMaterial.Builder("hassium", 108)
        .setColor(RagiColor.WHITE)
        .setFormula("Hs")
        //.setMolar(269.0/277.0)
        .build()

    @JvmField
    val MEITNERIUM = HiiragiMaterial.Builder("meitnerium", 109)
        .setColor(RagiColor.WHITE)
        .setFormula("Mt")
        //.setMolar(276.0/278.0)
        .build()

    @JvmField
    val DARMSTADTIUM = HiiragiMaterial.Builder("darmstadtium", 110)
        .setColor(RagiColor.WHITE)
        .setFormula("Ds")
        //.setMolar(281.0)
        .build()

    @JvmField
    val ROENTGENIUM = HiiragiMaterial.Builder("roentgenium", 111)
        .setColor(RagiColor.WHITE)
        .setFormula("Rg")
        //.setMolar(280.0/281.0/282.0)
        .build()

    @JvmField
    val COPERNICIUM = HiiragiMaterial.Builder("copernicium", 112)
        .setColor(RagiColor.WHITE)
        .setFormula("Cn")
        //.setMolar(285.0)
        .build()

    @JvmField
    val NIHONIUM = HiiragiMaterial.Builder("nihonium", 113)
        .setColor(RagiColor.WHITE)
        .setFormula("Nh")
        //.setMolar(278.0/286.0)
        .build()

    @JvmField
    val FLEROVIUM = HiiragiMaterial.Builder("flerovium", 114)
        .setColor(RagiColor.WHITE)
        .setFormula("Fl")
        //.setMolar(289.0)
        .build()

    @JvmField
    val MOSCOVIUM = HiiragiMaterial.Builder("moscovium", 115)
        .setColor(RagiColor.WHITE)
        .setFormula("Mc")
        //.setMolar(289.0/290.0)
        .build()

    @JvmField
    val LIVERMORIUM = HiiragiMaterial.Builder("livermorium", 116)
        .setColor(RagiColor.WHITE)
        .setFormula("Lv")
        //.setMolar(293.0)
        .build()

    @JvmField
    val TENNESSINE = HiiragiMaterial.Builder("tennesine", 117)
        .setColor(RagiColor.WHITE)
        .setFormula("Ts")
        //.setMolar(293.0/294.0)
        .build()

    @JvmField
    val OGANESSON = HiiragiMaterial.Builder("oganesson", 118)
        .setColor(RagiColor.WHITE)
        .setFormula("Og")
        //.setMolar(294.0)
        .build()

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
        registerMaterial(PALLADIUN)
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