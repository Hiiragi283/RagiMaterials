package hiiragi283.material.material.element

import hiiragi283.material.material.type.TypeRegistry
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
    private val colorAluminium = ColorUtil.mixColor(RagiColor.AQUA, RagiColor.WHITE)
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

    private val colorEnder = ColorUtil.mixColor(RagiColor.DARK_GREEN to 1, RagiColor.BLUE to 1)

    //    Element    //

    /**
     * https://github.com/Hiiragi283/RagiMaterials/blob/1.12_old/src/main/kotlin/ragi_materials/core/material/ElementRegistry.kt
     */

    val HYDROGEN = RagiElement("hydrogen", TypeRegistry.GAS, RagiColor.BLUE, "H", 1.0f)
    val DEUTERIUM = RagiElement("deuterium", TypeRegistry.GAS, RagiColor.BLUE, "D", 2.0f)
    val TRITIUM = RagiElement("tritium", TypeRegistry.GAS, RagiColor.BLUE, "T", 3.0f)
    val HELIUM = RagiElement("helium", TypeRegistry.GAS, RagiColor.YELLOW, "He", 4.0f)

    val LITHIUM = RagiElement("lithium", TypeRegistry.METAL, RagiColor.GRAY, "Li", 7.0f)
    val BERYLLIUM = RagiElement("beryllium", TypeRegistry.METAL, RagiColor.DARK_GREEN, "Be", 9.0f)
    val CARBON = RagiElement("carbon", TypeRegistry.INGOT, colorCarbon, "C", 12.0f)
    val NITROGEN = RagiElement("nitrogen", TypeRegistry.GAS, RagiColor.AQUA, "N", 14.0f)
    val OXYGEN = RagiElement("oxygen", TypeRegistry.GAS, RagiColor.WHITE, "O", 16.0f)
    val FLUORINE = RagiElement("fluorine", TypeRegistry.GAS, RagiColor.GREEN, "F", 19.0f)
    val NEON = RagiElement("neon", TypeRegistry.GAS, RagiColor.LIGHT_PURPLE, "Ne", 20.2f)

    val SODIUM = RagiElement("sodium", TypeRegistry.METAL, RagiColor.DARK_BLUE, "Na", 23.0f)
    val MAGNESIUM = RagiElement("magnesium", TypeRegistry.METAL, colorMagnesium, "Mg", 24.3f)
    val ALUMINIUM = RagiElement("aluminium", TypeRegistry.METAL, colorAluminium, "Al", 27.0f)
    val SILICON = RagiElement("silicon", TypeRegistry.INGOT, colorSilicon, "Si", 28.1f)
    val PHOSPHORUS = RagiElement("phosphorus", TypeRegistry.DUST, RagiColor.YELLOW, "P", 31.0f)
    val SULFUR = RagiElement("sulfur", TypeRegistry.DUST, colorSulfur, "S", 32.0f)
    val CHLORINE = RagiElement("chlorine", TypeRegistry.GAS, RagiColor.YELLOW, "Cl", 35.5f)
    val ARGON = RagiElement("argon", TypeRegistry.GAS, RagiColor.LIGHT_PURPLE, "Ar", 40.0f)

    val POTASSIUM = RagiElement("potassium", TypeRegistry.METAL, colorPotassium, "K", 39.1f)
    val CALCIUM = RagiElement("calcium", TypeRegistry.METAL, RagiColor.DARK_GRAY, "Ca", 40.1f)
    val SCANDIUM = RagiElement("scandium", TypeRegistry.METAL, RagiColor.DARK_GRAY, "Sc", 42.0f)
    val TITANIUM = RagiElement("titanium", TypeRegistry.METAL, colorTitanium, "Ti", 47.9f)
    val VANADIUM = RagiElement("vanadium", TypeRegistry.METAL, colorVanadium, "V", 50.9f)
    val CHROMIUM = RagiElement("chromium", TypeRegistry.METAL, RagiColor.GREEN, "Cr", 52.0f)
    val MANGANESE = RagiElement("manganese", TypeRegistry.METAL, colorManganese, "Mn", 54.9f)
    val IRON = RagiElement("iron", TypeRegistry.METAL, colorIron, "Fe", 55.8f)
    val COBALT = RagiElement("cobalt", TypeRegistry.METAL, RagiColor.BLUE, "Co", 58.9f)
    val NICKEL = RagiElement("nickel", TypeRegistry.METAL, colorNickel, "Ni", 58.7f)
    val COPPER = RagiElement("copper", TypeRegistry.METAL, colorCopper, "Cu", 63.5f)
    val ZINC = RagiElement("zinc", TypeRegistry.METAL, colorZinc, "Zn", 65.4f)
    val GALLIUM = RagiElement("gallium", TypeRegistry.METAL, colorIron, "Ga", 69.7f)
    val GERMANIUM = RagiElement("germanium", TypeRegistry.METAL, colorIron, "Ge", 72.6f)
    val ARSENIC = RagiElement("arsenic", TypeRegistry.INGOT, RagiColor.DARK_GRAY, "As", 74.9f)
    val SELENIUM = RagiElement("selenium", TypeRegistry.INGOT, RagiColor.DARK_GRAY, "Se", 79.0f)
    val BROMINE = RagiElement("bromine", TypeRegistry.GAS, colorCopper, "Br", 79.9f)
    val KRYPTON = RagiElement("krypton", TypeRegistry.GAS, RagiColor.LIGHT_PURPLE, "Kr", 84.0f)

    val GOLD = RagiElement("gold", TypeRegistry.METAL, RagiColor.YELLOW, "Au", 197.0f)

    val REDSTONE = RagiElement("redstone", TypeRegistry.CRYSTAL, RagiColor.DARK_RED, "Rs", 112.2f)
    val GLOWSTONE = RagiElement("glowstone", TypeRegistry.CRYSTAL, colorSulfur, "Gw", 112.2f)
    val ENDER = RagiElement("ender", TypeRegistry.CRYSTAL, colorEnder, "En", 112.2f)

}