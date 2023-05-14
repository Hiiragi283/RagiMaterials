package hiiragi283.material.material.element

import hiiragi283.material.util.ColorUtil
import hiiragi283.material.util.RagiColor

object ElementRegistry {

    fun load() {}

    private val mapElement: HashMap<String, RagiElement> = hashMapOf()

    fun getElement(name: String): RagiElement = mapElement.getOrDefault(name, RagiElement.EMPTY)

    fun getElementAll(): Collection<RagiElement> = mapElement.values

    fun setElement(material: RagiElement): RagiElement? = mapElement.putIfAbsent(material.name, material)

    //    Color    //

    private val colorCarbon = ColorUtil.mixColor(RagiColor.BLACK, RagiColor.DARK_GRAY)
    private val colorMagnesium = ColorUtil.mixColor(RagiColor.LIGHT_PURPLE, RagiColor.WHITE)
    private val colorAluminium = ColorUtil.mixColor(RagiColor.BLUE to 1, RagiColor.WHITE to 5)
    private val colorSilicon = ColorUtil.mixColor(RagiColor.BLACK to 3, RagiColor.BLUE to 1)
    private val colorSulfur = ColorUtil.mixColor(RagiColor.GOLD, RagiColor.YELLOW)
    private val colorPotassium = ColorUtil.mixColor(RagiColor.DARK_BLUE, RagiColor.BLUE)
    private val colorTitanium = ColorUtil.mixColor(RagiColor.GOLD to 1, RagiColor.WHITE to 2)
    private val colorVanadium = ColorUtil.mixColor(RagiColor.DARK_AQUA to 1, RagiColor.BLACK to 2)
    private val colorManganese = ColorUtil.mixColor(RagiColor.RED, RagiColor.WHITE)
    private val colorIron = ColorUtil.mixColor(RagiColor.GRAY to 1, RagiColor.WHITE to 2)
    private val colorNickel = ColorUtil.mixColor(RagiColor.GOLD, RagiColor.WHITE)
    private val colorCopper = ColorUtil.mixColor(RagiColor.GOLD, RagiColor.RED)
    private val colorZinc = ColorUtil.mixColor(RagiColor.GREEN to 1, RagiColor.WHITE to 2)

    private val colorSilver = ColorUtil.mixColor(RagiColor.AQUA to 1, RagiColor.WHITE to 3)
    private val colorTin = ColorUtil.mixColor(RagiColor.BLUE to 1, RagiColor.AQUA to 1, RagiColor.WHITE to 3)

    private val colorOsmium = ColorUtil.mixColor(RagiColor.BLUE to 1, RagiColor.WHITE to 3)
    private val colorIridium = ColorUtil.mixColor(RagiColor.AQUA to 1, RagiColor.WHITE to 3)
    private val colorPlatinum = ColorUtil.mixColor(RagiColor.GREEN to 1, RagiColor.WHITE to 3)
    private val colorLead = ColorUtil.mixColor(RagiColor.DARK_BLUE, RagiColor.DARK_GRAY, RagiColor.WHITE)

    private val colorEnder = ColorUtil.mixColor(RagiColor.DARK_GREEN to 1, RagiColor.BLUE to 1)

    private val colorMithril = ColorUtil.mixColor(RagiColor.BLUE to 1, RagiColor.AQUA to 2)

    //    Element    //

    /**
     * https://github.com/Hiiragi283/RagiMaterials/blob/1.12_old/src/main/kotlin/ragi_materials/core/material/ElementRegistry.kt
     */

    val HYDROGEN = RagiElement("hydrogen", RagiColor.BLUE, "H", 1.0f)
    val DEUTERIUM = RagiElement("deuterium", RagiColor.BLUE, "D", 2.0f)
    val TRITIUM = RagiElement("tritium", RagiColor.BLUE, "T", 3.0f)
    val HELIUM = RagiElement("helium", RagiColor.YELLOW, "He", 4.0f)

    val LITHIUM = RagiElement("lithium", RagiColor.GRAY, "Li", 7.0f)
    val BERYLLIUM = RagiElement("beryllium", RagiColor.DARK_GREEN, "Be", 9.0f)
    val CARBON = RagiElement("carbon", colorCarbon, "C", 12.0f)
    val NITROGEN = RagiElement("nitrogen", RagiColor.AQUA, "N", 14.0f)
    val OXYGEN = RagiElement("oxygen", RagiColor.WHITE, "O", 16.0f)
    val FLUORINE = RagiElement("fluorine", RagiColor.GREEN, "F", 19.0f)
    val NEON = RagiElement("neon", RagiColor.LIGHT_PURPLE, "Ne", 20.2f)

    val SODIUM = RagiElement("sodium", RagiColor.DARK_BLUE, "Na", 23.0f)
    val MAGNESIUM = RagiElement("magnesium", colorMagnesium, "Mg", 24.3f)
    val ALUMINIUM = RagiElement("aluminium", colorAluminium, "Al", 27.0f)
    val SILICON = RagiElement("silicon", colorSilicon, "Si", 28.1f)
    val PHOSPHORUS = RagiElement("phosphorus", RagiColor.YELLOW, "P", 31.0f)
    val SULFUR = RagiElement("sulfur", colorSulfur, "S", 32.0f)
    val CHLORINE = RagiElement("chlorine", RagiColor.YELLOW, "Cl", 35.5f)
    val ARGON = RagiElement("argon", RagiColor.LIGHT_PURPLE, "Ar", 40.0f)

    val POTASSIUM = RagiElement("potassium", colorPotassium, "K", 39.1f)
    val CALCIUM = RagiElement("calcium", RagiColor.DARK_GRAY, "Ca", 40.1f)
    val SCANDIUM = RagiElement("scandium", RagiColor.DARK_GRAY, "Sc", 42.0f)
    val TITANIUM = RagiElement("titanium", colorTitanium, "Ti", 47.9f)
    val VANADIUM = RagiElement("vanadium", colorVanadium, "V", 50.9f)
    val CHROMIUM = RagiElement("chromium", RagiColor.GREEN, "Cr", 52.0f)
    val MANGANESE = RagiElement("manganese", colorManganese, "Mn", 54.9f)
    val IRON = RagiElement("iron", colorIron, "Fe", 55.8f)
    val COBALT = RagiElement("cobalt", RagiColor.BLUE, "Co", 58.9f)
    val NICKEL = RagiElement("nickel", colorNickel, "Ni", 58.7f)
    val COPPER = RagiElement("copper", colorCopper, "Cu", 63.5f)
    val ZINC = RagiElement("zinc", colorZinc, "Zn", 65.4f)
    val GALLIUM = RagiElement("gallium", colorIron, "Ga", 69.7f)
    val GERMANIUM = RagiElement("germanium", colorIron, "Ge", 72.6f)
    val ARSENIC = RagiElement("arsenic", RagiColor.DARK_GRAY, "As", 74.9f)
    val SELENIUM = RagiElement("selenium", RagiColor.DARK_GRAY, "Se", 79.0f)
    val BROMINE = RagiElement("bromine", colorCopper, "Br", 79.9f)
    val KRYPTON = RagiElement("krypton", RagiColor.LIGHT_PURPLE, "Kr", 84.0f)

    val SILVER = RagiElement("silver", colorSilver, "Ag", 107.9f)
    val TIN = RagiElement("tin", colorTin, "Sn", 118.7f)

    val OSMIUM = RagiElement("osmium", colorOsmium, "Os", 190.2f)
    val IRIDIUM = RagiElement("iridium", colorIridium, "Ir", 192.2f)
    val PLATINUM = RagiElement("platinum", colorPlatinum, "Pt", 195.1f)
    val GOLD = RagiElement("gold", RagiColor.YELLOW, "Au", 197.0f)
    val LEAD = RagiElement("lead", colorLead, "Pb", 207.2f)

    val REDSTONE = RagiElement("redstone", RagiColor.DARK_RED, "Rs", 112.2f)
    val GLOWSTONE = RagiElement("glowstone", colorSulfur, "Gw", 112.2f)
    val ENDER = RagiElement("ender", colorEnder, "En", 112.2f)

    val MITHRIL = RagiElement("mithril", colorMithril, "Mt", 193.7f)

}