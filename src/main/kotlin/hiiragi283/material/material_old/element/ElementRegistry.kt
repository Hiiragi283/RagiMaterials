package hiiragi283.material.material_old.element

import hiiragi283.material.util.ColorUtil
import hiiragi283.material.util.RagiColor

object ElementRegistry {

    fun load() {}

    private val mapElement: HashMap<String, RagiElementOld> = hashMapOf()

    fun getElement(name: String): RagiElementOld = mapElement.getOrDefault(name, RagiElementOld.EMPTY)

    fun getElementAll(): Collection<RagiElementOld> = mapElement.values

    fun setElement(material: RagiElementOld): RagiElementOld? = mapElement.putIfAbsent(material.name, material)

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

    val HYDROGEN = RagiElementOld("hydrogen", RagiColor.BLUE, "H", 1.0f)
    val DEUTERIUM = RagiElementOld("deuterium", RagiColor.BLUE, "D", 2.0f)
    val TRITIUM = RagiElementOld("tritium", RagiColor.BLUE, "T", 3.0f)
    val HELIUM = RagiElementOld("helium", RagiColor.YELLOW, "He", 4.0f)

    val LITHIUM = RagiElementOld("lithium", RagiColor.GRAY, "Li", 7.0f)
    val BERYLLIUM = RagiElementOld("beryllium", RagiColor.DARK_GREEN, "Be", 9.0f)
    val CARBON = RagiElementOld("carbon", colorCarbon, "C", 12.0f)
    val NITROGEN = RagiElementOld("nitrogen", RagiColor.AQUA, "N", 14.0f)
    val OXYGEN = RagiElementOld("oxygen", RagiColor.WHITE, "O", 16.0f)
    val FLUORINE = RagiElementOld("fluorine", RagiColor.GREEN, "F", 19.0f)
    val NEON = RagiElementOld("neon", RagiColor.LIGHT_PURPLE, "Ne", 20.2f)

    val SODIUM = RagiElementOld("sodium", RagiColor.DARK_BLUE, "Na", 23.0f)
    val MAGNESIUM = RagiElementOld("magnesium", colorMagnesium, "Mg", 24.3f)
    val ALUMINIUM = RagiElementOld("aluminium", colorAluminium, "Al", 27.0f)
    val SILICON = RagiElementOld("silicon", colorSilicon, "Si", 28.1f)
    val PHOSPHORUS = RagiElementOld("phosphorus", RagiColor.YELLOW, "P", 31.0f)
    val SULFUR = RagiElementOld("sulfur", colorSulfur, "S", 32.0f)
    val CHLORINE = RagiElementOld("chlorine", RagiColor.YELLOW, "Cl", 35.5f)
    val ARGON = RagiElementOld("argon", RagiColor.LIGHT_PURPLE, "Ar", 40.0f)

    val POTASSIUM = RagiElementOld("potassium", colorPotassium, "K", 39.1f)
    val CALCIUM = RagiElementOld("calcium", RagiColor.DARK_GRAY, "Ca", 40.1f)
    val SCANDIUM = RagiElementOld("scandium", RagiColor.DARK_GRAY, "Sc", 42.0f)
    val TITANIUM = RagiElementOld("titanium", colorTitanium, "Ti", 47.9f)
    val VANADIUM = RagiElementOld("vanadium", colorVanadium, "V", 50.9f)
    val CHROMIUM = RagiElementOld("chromium", RagiColor.GREEN, "Cr", 52.0f)
    val MANGANESE = RagiElementOld("manganese", colorManganese, "Mn", 54.9f)
    val IRON = RagiElementOld("iron", colorIron, "Fe", 55.8f)
    val COBALT = RagiElementOld("cobalt", RagiColor.BLUE, "Co", 58.9f)
    val NICKEL = RagiElementOld("nickel", colorNickel, "Ni", 58.7f)
    val COPPER = RagiElementOld("copper", colorCopper, "Cu", 63.5f)
    val ZINC = RagiElementOld("zinc", colorZinc, "Zn", 65.4f)
    val GALLIUM = RagiElementOld("gallium", colorIron, "Ga", 69.7f)
    val GERMANIUM = RagiElementOld("germanium", colorIron, "Ge", 72.6f)
    val ARSENIC = RagiElementOld("arsenic", RagiColor.DARK_GRAY, "As", 74.9f)
    val SELENIUM = RagiElementOld("selenium", RagiColor.DARK_GRAY, "Se", 79.0f)
    val BROMINE = RagiElementOld("bromine", colorCopper, "Br", 79.9f)
    val KRYPTON = RagiElementOld("krypton", RagiColor.LIGHT_PURPLE, "Kr", 84.0f)

    val SILVER = RagiElementOld("silver", colorSilver, "Ag", 107.9f)
    val TIN = RagiElementOld("tin", colorTin, "Sn", 118.7f)

    val OSMIUM = RagiElementOld("osmium", colorOsmium, "Os", 190.2f)
    val IRIDIUM = RagiElementOld("iridium", colorIridium, "Ir", 192.2f)
    val PLATINUM = RagiElementOld("platinum", colorPlatinum, "Pt", 195.1f)
    val GOLD = RagiElementOld("gold", RagiColor.YELLOW, "Au", 197.0f)
    val LEAD = RagiElementOld("lead", colorLead, "Pb", 207.2f)

    val REDSTONE = RagiElementOld("redstone", RagiColor.DARK_RED, "Rs", 112.2f)
    val GLOWSTONE = RagiElementOld("glowstone", colorSulfur, "Gw", 112.2f)
    val ENDER = RagiElementOld("ender", colorEnder, "En", 112.2f)

    val MITHRIL = RagiElementOld("mithril", colorMithril, "Mt", 193.7f)

}