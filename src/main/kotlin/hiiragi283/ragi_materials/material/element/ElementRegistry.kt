package hiiragi283.ragi_materials.material.element

import hiiragi283.ragi_materials.material.type.TypeRegistry
import hiiragi283.ragi_materials.render.color.RagiColor

object ElementRegistry {

    //Periodic Table
    //1st Period
    val HYDROGEN = ElementBuilder(1, "hydrogen", TypeRegistry.GAS, RagiColor.BLUE, 1.0f, "H", -259, -253)

    val HELIUM = ElementBuilder(2, "helium", TypeRegistry.GAS, RagiColor.YELLOW, 4.0f, "He", -272, -269)

    //2nd Period
    val LITHIUM = ElementBuilder(3, "lithium", TypeRegistry.METAL, RagiColor.GRAY, 7.0f, "Li", 181, 1342)

    val BERYLLIUM = ElementBuilder(4, "beryllium", TypeRegistry.METAL, RagiColor.DARK_GREEN, 9.0f, "Be", 1287, 2469)

    val BORON = ElementBuilder(5, "boron", TypeRegistry.DUST, RagiColor.DARK_GRAY, 10.8f, "B", 2075, 4000)

    val CARBON = ElementBuilder(6, "carbon", TypeRegistry.METALLOID, RagiColor.mixColor(RagiColor.BLACK, RagiColor.DARK_GRAY), 12.0f, "C", null, null).setTempSubl(4400) as ElementBuilder

    val NITROGEN = ElementBuilder(7, "nitrogen", TypeRegistry.GAS, RagiColor.AQUA, 14.0f, "N", -210, -196)

    val OXYGEN = ElementBuilder(8, "oxygen", TypeRegistry.GAS, RagiColor.AQUA, 16.0f, "O", -219, -183)

    val FLUORINE = ElementBuilder(9, "fluorine", TypeRegistry.GAS, RagiColor.GREEN, 19.0f, "F", -220, -188)

    val NEON = ElementBuilder(10, "neon", TypeRegistry.GAS, RagiColor.LIGHT_PURPLE, 20.2f, "Ne", -249, -246)

    //3rd Period
    val SODIUM = ElementBuilder(11, "sodium", TypeRegistry.INTERNAL, RagiColor.WHITE, 23.0f, "Na", 98, 883)

    val MAGNESIUM = ElementBuilder(12, "magnesium", TypeRegistry.METAL, RagiColor.mixColor(RagiColor.LIGHT_PURPLE, RagiColor.WHITE), 24.3f, "Mg", 650, 1090)

    val ALUMINIUM = ElementBuilder(13, "aluminium", TypeRegistry.METAL, RagiColor.mixColor(RagiColor.AQUA, RagiColor.WHITE), 27.0f, "Al", 660, 2519)

    val SILICON = ElementBuilder(14, "silicon", TypeRegistry.METAL, RagiColor.DARK_GRAY, 28.1f, "Si", 1414, 3265)

    val PHOSPHORUS = ElementBuilder(15, "phosphorus", TypeRegistry.DUST, RagiColor.YELLOW, 31.0f, "P", 44, 281)

    val SULFUR = ElementBuilder(16, "sulfur", TypeRegistry.DUST, RagiColor.mixColor(RagiColor.GOLD, RagiColor.YELLOW), 32.1f, "S", 120, 445)

    val CHLORINE = ElementBuilder(17, "chlorine", TypeRegistry.GAS, RagiColor.YELLOW, 35.5f, "Cl", -102, -34)

    val ARGON = ElementBuilder(18, "argon", TypeRegistry.GAS, RagiColor.LIGHT_PURPLE, 40.0f, "Ar", -189, -186)

    //4th Period
    val POTASSIUM = ElementBuilder(19, "potassium", TypeRegistry.INTERNAL, RagiColor.WHITE, 39.1f, "K", 64, 759)

    val CALCIUM = ElementBuilder(20, "calcium", TypeRegistry.INTERNAL, RagiColor.WHITE, 40.1f, "Ca", 842, 1484)

    val TITANIUM = ElementBuilder(22, "titanium", TypeRegistry.METAL, RagiColor.mixColor(mapOf(RagiColor.GOLD to 1, RagiColor.WHITE to 2)), 47.9f, "Ti", 1668, 3287)

    val CHROMIUM = ElementBuilder(24, "chromium", TypeRegistry.METAL, RagiColor.GREEN, 52.0f, "Cr", 1907, 2671)

    val MANGANESE = ElementBuilder(25, "manganese", TypeRegistry.METAL, RagiColor.mixColor(RagiColor.RED, RagiColor.WHITE), 54.9f, "Mn", 1246, 2061)

    val IRON = ElementBuilder(26, "iron", TypeRegistry.METAL, RagiColor.WHITE, 55.8f, "Fe", 1538, 2861)

    val COBALT = ElementBuilder(27, "cobalt", TypeRegistry.METAL, RagiColor.BLUE, 58.9f, "Co", 1495, 2927)

    val NICKEL = ElementBuilder(28, "nickel", TypeRegistry.METAL, RagiColor.mixColor(RagiColor.GOLD, RagiColor.WHITE), 58.7f, "Ni", 1455, 2913)

    val COPPER = ElementBuilder(29, "copper", TypeRegistry.METAL, RagiColor.mixColor(RagiColor.GOLD, RagiColor.RED), 63.5f, "Cu", 1085, 2562)

    val ZINC = ElementBuilder(30, "zinc", TypeRegistry.METAL, RagiColor.WHITE, 65.4f, "Zn", 420, 907)

    val GALLIUM = ElementBuilder(31, "gallium", TypeRegistry.METAL, RagiColor.WHITE, 69.7f, "Ga", 30, 2204)

    val ARSENIC = ElementBuilder(33, "arsenic", TypeRegistry.METALLOID, RagiColor.DARK_GRAY, 74.9f, "As", null, null).setTempSubl(603) as ElementBuilder

    //5th Period
    val STRONTIUM = ElementBuilder(38, "strontium", TypeRegistry.INTERNAL, RagiColor.WHITE, 87.6f, "Sr", 777, 1377)

    val ZIRCONIUM = ElementBuilder(40, "zirconium", TypeRegistry.METAL, RagiColor.mixColor(RagiColor.BLUE, RagiColor.LIGHT_PURPLE), 91.2f, "Zr", 1855, 4409)

    val NIOBIUM = ElementBuilder(41, "niobium", TypeRegistry.METAL, RagiColor.mixColor(RagiColor.DARK_BLUE, RagiColor.WHITE), 92.9f, "Nb", 2477, 4744)

    val MOLYBDENUM = ElementBuilder(42, "molybdenum", TypeRegistry.METAL, RagiColor.mixColor(RagiColor.DARK_BLUE, RagiColor.WHITE), 96.0f, "Mo", 2023, 4639)

    val RUTHENIUM = ElementBuilder(44, "ruthenium", TypeRegistry.METAL, RagiColor.mixColor(mapOf(RagiColor.LIGHT_PURPLE to 1, RagiColor.WHITE to 3)), 101.1f, "Ru", 2334, 4150)

    val RHODIUM = ElementBuilder(45, "rhodium", TypeRegistry.METAL, RagiColor.mixColor(mapOf(RagiColor.RED to 1, RagiColor.WHITE to 3)), 102.9f, "Rh", 1964, 3695)

    val PALLADIUM = ElementBuilder(46, "palladium", TypeRegistry.METAL, RagiColor.mixColor(mapOf(RagiColor.YELLOW to 1, RagiColor.WHITE to 3)), 106.4f, "Pa", 1555, 2963)

    val SILVER = ElementBuilder(47, "silver", TypeRegistry.METAL, RagiColor.mixColor(mapOf(RagiColor.AQUA to 1, RagiColor.WHITE to 2)), 107.9f, "Ag", 962, 2162)

    val INDIUM = ElementBuilder(49, "indium", TypeRegistry.METAL, RagiColor.mixColor(RagiColor.DARK_BLUE, RagiColor.RED), 114.8f, "In", 157, 2072)

    val TIN = ElementBuilder(50, "tin", TypeRegistry.METAL, RagiColor.WHITE, 118.7f, "Sn", 232, 2602)

    val ANTIMONY = ElementBuilder(51, "antimony", TypeRegistry.METAL, RagiColor.DARK_GRAY, 121.8f, "Sb", 631, 1587)

    val IODINE = ElementBuilder(53, "iodine", TypeRegistry.DUST, RagiColor.mixColor(RagiColor.DARK_BLUE, RagiColor.DARK_RED), 126.9f, "I", 114, 184)

    //6th Period
    val BARIUM = ElementBuilder(56, "barium", TypeRegistry.INTERNAL, RagiColor.WHITE, 137.3f, "Ba", 727, 1845)

    val NEODYMIUM = ElementBuilder(60, "neodymium", TypeRegistry.METAL, RagiColor.GRAY, 144.2f, "Nd", 1021, 3074)

    val SAMARIUM = ElementBuilder(62, "samarium", TypeRegistry.METAL, RagiColor.GRAY, 150.4f, "Sa", 1074, 1794)

    val HAFNIUM = ElementBuilder(72, "hafnium", TypeRegistry.METAL, RagiColor.GRAY, 178.5f, "Hf", 2233, 4603)

    val TANTALUM = ElementBuilder(73, "tantalum", TypeRegistry.METAL, RagiColor.GRAY, 180.9f, "Ta", 3017, 5458)

    val TUNGSTEN = ElementBuilder(74, "tungsten", TypeRegistry.METAL, RagiColor.mixColor(RagiColor.BLACK, RagiColor.DARK_GRAY), 183.8f, "W", 3422, 5555)

    val OSMIUM = ElementBuilder(76, "osmium", TypeRegistry.METAL, RagiColor.mixColor(mapOf(RagiColor.BLUE to 1, RagiColor.WHITE to 3)), 190.2f, "Os", 3033, 5012)

    val IRIDIUM = ElementBuilder(77, "iridium", TypeRegistry.METAL, RagiColor.mixColor(mapOf(RagiColor.AQUA to 1, RagiColor.WHITE to 3)), 192.2f, "Ir", 2446, 4428)

    val PLATINUM = ElementBuilder(78, "platinum", TypeRegistry.METAL, RagiColor.mixColor(mapOf(RagiColor.GREEN to 1, RagiColor.WHITE to 3)), 195.1f, "Pt", 1768, 3825)

    val GOLD = ElementBuilder(79, "gold", TypeRegistry.METAL, RagiColor.YELLOW, 197.0f, "Au", 1064, 2856)

    val MERCURY = ElementBuilder(80, "mercury", TypeRegistry.LIQUID, RagiColor.WHITE, 200.6f, "Hg", -39, 357)

    val LEAD = ElementBuilder(82, "lead", TypeRegistry.METAL, RagiColor.mixColor(RagiColor.DARK_BLUE, RagiColor.DARK_GRAY, RagiColor.WHITE), 207.2f, "Pb", 327, 1749)

    val BISMUTH = ElementBuilder(83, "bismuth", TypeRegistry.METAL, RagiColor.DARK_AQUA, 209.0f, "Bi", 271, 1564)

    //7th Period
    val THORIUM = ElementBuilder(90, "thorium", TypeRegistry.METAL_RADIO, RagiColor.mixColor(RagiColor.BLACK, RagiColor.DARK_GREEN), 232.0f, "Th", 1750, 4788)

    val URANIUM_238 = ElementBuilder(92, "uranium", TypeRegistry.METAL_RADIO, RagiColor.GREEN, 238.0f, "U", 1135, 4131)

    val PLUTONIUM_244 = ElementBuilder(94, "plutonium", TypeRegistry.METAL_RADIO, RagiColor.RED, 244.0f, "Pu", 640, 3228)

    //Isotope
    val DEUTERIUM = IsotopeBuilder(95, "deuterium", HYDROGEN, 2.0f, "D")

    val TRITIUM = IsotopeBuilder(96, "tritium", HYDROGEN, 3.0f, "T")

    val URANIUM_235 = IsotopeBuilder(97, "uranium235", URANIUM_238, 235.0f, "U-235")

    val PLUTONIUM_239 = IsotopeBuilder(98, "plutonium239", PLUTONIUM_244, 239.0f, "Pu-239")

    //Imaginary Elements
    val REDSTONE = ElementBuilder(209, "redstone", TypeRegistry.DUST, RagiColor.DARK_RED, 112.2f, "Rs", 1122, 1194)

    val GLOWSTONE = ElementBuilder(215, "glowstone", TypeRegistry.DUST, RagiColor.mixColor(RagiColor.GOLD, RagiColor.YELLOW), 112.2f, "Gl", 1122, 1194)

    val ENDER = ElementBuilder(219, "ender", TypeRegistry.DUST, RagiColor.mixColor(mapOf(RagiColor.DARK_GREEN to 1, RagiColor.BLUE to 1)), 112.2f, "En", 1122, 1194)

    //Integration
    val MITHRIL = ElementBuilder(600, "mithril", TypeRegistry.METAL, RagiColor.mixColor(mapOf(RagiColor.BLUE to 1, RagiColor.AQUA to 1)), 193.7f, "Mt", null, null)

}