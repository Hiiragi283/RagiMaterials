package hiiragi283.ragi_materials.material

import hiiragi283.ragi_materials.material.type.TypeRegistry
import hiiragi283.ragi_materials.client.render.color.RagiColorManager
import hiiragi283.ragi_materials.material.builder.ElementBuilder
import hiiragi283.ragi_materials.material.builder.IsotopeBuilder

object ElementRegistry {

    //Periodic Table
    //1st Period
    val HYDROGEN = ElementBuilder(10, "hydrogen", TypeRegistry.GAS, RagiColorManager.BLUE, 1.0f, "H", -259, -253)

    val HELIUM = ElementBuilder(20, "helium", TypeRegistry.GAS, RagiColorManager.YELLOW, 4.0f, "He", -272, -269)

    //2nd Period
    val LITHIUM = ElementBuilder(30, "lithium", TypeRegistry.METAL, RagiColorManager.GRAY, 7.0f, "Li", 181, 1342)

    val BERYLLIUM = ElementBuilder(40, "beryllium", TypeRegistry.METAL, RagiColorManager.DARK_GREEN, 9.0f, "Be", 1287, 2469)

    val BORON = ElementBuilder(50, "boron", TypeRegistry.DUST, RagiColorManager.DARK_GRAY, 10.8f, "B", 2075, 4000)

    val CARBON = ElementBuilder(60, "carbon", TypeRegistry.METALLOID, RagiColorManager.mixColor(RagiColorManager.BLACK, RagiColorManager.DARK_GRAY), 12.0f, "C", null, null).apply { tempSubl = 4400 }

    val NITROGEN = ElementBuilder(70, "nitrogen", TypeRegistry.GAS, RagiColorManager.AQUA, 14.0f, "N", -210, -196)

    val OXYGEN = ElementBuilder(80, "oxygen", TypeRegistry.GAS, RagiColorManager.WHITE, 16.0f, "O", -219, -183)

    val FLUORINE = ElementBuilder(90, "fluorine", TypeRegistry.GAS, RagiColorManager.GREEN, 19.0f, "F", -220, -188)

    val NEON = ElementBuilder(21, "neon", TypeRegistry.GAS, RagiColorManager.LIGHT_PURPLE, 20.2f, "Ne", -249, -246)

    //3rd Period
    val SODIUM = ElementBuilder(110, "sodium", TypeRegistry.INTERNAL, RagiColorManager.WHITE, 23.0f, "Na", 98, 883)

    val MAGNESIUM = ElementBuilder(120, "magnesium", TypeRegistry.METAL, RagiColorManager.mixColor(RagiColorManager.LIGHT_PURPLE, RagiColorManager.WHITE), 24.3f, "Mg", 650, 1090)

    val ALUMINIUM = ElementBuilder(130, "aluminium", TypeRegistry.METAL, RagiColorManager.mixColor(RagiColorManager.AQUA, RagiColorManager.WHITE), 27.0f, "Al", 660, 2519)

    val SILICON = ElementBuilder(140, "silicon", TypeRegistry.METAL, RagiColorManager.DARK_GRAY, 28.1f, "Si", 1414, 3265)

    val PHOSPHORUS = ElementBuilder(150, "phosphorus", TypeRegistry.DUST, RagiColorManager.YELLOW, 31.0f, "P", 44, 281)

    val SULFUR = ElementBuilder(160, "sulfur", TypeRegistry.DUST, RagiColorManager.mixColor(RagiColorManager.GOLD, RagiColorManager.YELLOW), 32.1f, "S", 120, 445)

    val CHLORINE = ElementBuilder(170, "chlorine", TypeRegistry.GAS, RagiColorManager.YELLOW, 35.5f, "Cl", -102, -34)

    val ARGON = ElementBuilder(22, "argon", TypeRegistry.GAS, RagiColorManager.LIGHT_PURPLE, 40.0f, "Ar", -189, -186)

    //4th Period
    val POTASSIUM = ElementBuilder(190, "potassium", TypeRegistry.INTERNAL, RagiColorManager.WHITE, 39.1f, "K", 64, 759)

    val CALCIUM = ElementBuilder(200, "calcium", TypeRegistry.INTERNAL, RagiColorManager.WHITE, 40.1f, "Ca", 842, 1484)

    val TITANIUM = ElementBuilder(220, "titanium", TypeRegistry.METAL, RagiColorManager.mixColor(mapOf(RagiColorManager.GOLD to 1, RagiColorManager.WHITE to 2)), 47.9f, "Ti", 1668, 3287)

    val CHROMIUM = ElementBuilder(240, "chromium", TypeRegistry.METAL, RagiColorManager.GREEN, 52.0f, "Cr", 1907, 2671)

    val MANGANESE = ElementBuilder(250, "manganese", TypeRegistry.METAL, RagiColorManager.mixColor(RagiColorManager.RED, RagiColorManager.WHITE), 54.9f, "Mn", 1246, 2061)

    val IRON = ElementBuilder(260, "iron", TypeRegistry.METAL, RagiColorManager.WHITE, 55.8f, "Fe", 1538, 2861)

    val COBALT = ElementBuilder(270, "cobalt", TypeRegistry.METAL, RagiColorManager.BLUE, 58.9f, "Co", 1495, 2927)

    val NICKEL = ElementBuilder(280, "nickel", TypeRegistry.METAL, RagiColorManager.mixColor(RagiColorManager.GOLD, RagiColorManager.WHITE), 58.7f, "Ni", 1455, 2913)

    val COPPER = ElementBuilder(290, "copper", TypeRegistry.METAL, RagiColorManager.mixColor(RagiColorManager.GOLD, RagiColorManager.RED), 63.5f, "Cu", 1085, 2562)

    val ZINC = ElementBuilder(300, "zinc", TypeRegistry.METAL, RagiColorManager.WHITE, 65.4f, "Zn", 420, 907)

    val GALLIUM = ElementBuilder(310, "gallium", TypeRegistry.METAL, RagiColorManager.WHITE, 69.7f, "Ga", 30, 2204)

    val ARSENIC = ElementBuilder(311, "arsenic", TypeRegistry.METALLOID, RagiColorManager.DARK_GRAY, 74.9f, "As", null, null).setTempSubl(603) as ElementBuilder

    //5th Period
    val STRONTIUM = ElementBuilder(380, "strontium", TypeRegistry.INTERNAL, RagiColorManager.WHITE, 87.6f, "Sr", 777, 1377)

    val ZIRCONIUM = ElementBuilder(400, "zirconium", TypeRegistry.METAL, RagiColorManager.mixColor(RagiColorManager.BLUE, RagiColorManager.LIGHT_PURPLE), 91.2f, "Zr", 1855, 4409)

    val NIOBIUM = ElementBuilder(410, "niobium", TypeRegistry.METAL, RagiColorManager.mixColor(RagiColorManager.DARK_BLUE, RagiColorManager.WHITE), 92.9f, "Nb", 2477, 4744)

    val MOLYBDENUM = ElementBuilder(420, "molybdenum", TypeRegistry.METAL, RagiColorManager.mixColor(RagiColorManager.DARK_BLUE, RagiColorManager.WHITE), 96.0f, "Mo", 2023, 4639)

    val RUTHENIUM = ElementBuilder(440, "ruthenium", TypeRegistry.METAL, RagiColorManager.mixColor(mapOf(RagiColorManager.LIGHT_PURPLE to 1, RagiColorManager.WHITE to 3)), 101.1f, "Ru", 2334, 4150)

    val RHODIUM = ElementBuilder(450, "rhodium", TypeRegistry.METAL, RagiColorManager.mixColor(mapOf(RagiColorManager.RED to 1, RagiColorManager.WHITE to 3)), 102.9f, "Rh", 1964, 3695)

    val PALLADIUM = ElementBuilder(460, "palladium", TypeRegistry.METAL, RagiColorManager.mixColor(mapOf(RagiColorManager.YELLOW to 1, RagiColorManager.WHITE to 3)), 106.4f, "Pa", 1555, 2963)

    val SILVER = ElementBuilder(470, "silver", TypeRegistry.METAL, RagiColorManager.mixColor(mapOf(RagiColorManager.AQUA to 1, RagiColorManager.WHITE to 2)), 107.9f, "Ag", 962, 2162)

    val INDIUM = ElementBuilder(490, "indium", TypeRegistry.METAL, RagiColorManager.mixColor(RagiColorManager.DARK_BLUE, RagiColorManager.RED), 114.8f, "In", 157, 2072)

    val TIN = ElementBuilder(500, "tin", TypeRegistry.METAL, RagiColorManager.WHITE, 118.7f, "Sn", 232, 2602)

    val ANTIMONY = ElementBuilder(510, "antimony", TypeRegistry.METAL, RagiColorManager.DARK_GRAY, 121.8f, "Sb", 631, 1587)

    val IODINE = ElementBuilder(530, "iodine", TypeRegistry.DUST, RagiColorManager.mixColor(RagiColorManager.DARK_BLUE, RagiColorManager.DARK_RED), 126.9f, "I", 114, 184)

    //6th Period
    val BARIUM = ElementBuilder(560, "barium", TypeRegistry.INTERNAL, RagiColorManager.WHITE, 137.3f, "Ba", 727, 1845)

    val NEODYMIUM = ElementBuilder(600, "neodymium", TypeRegistry.METAL, RagiColorManager.GRAY, 144.2f, "Nd", 1021, 3074)

    val SAMARIUM = ElementBuilder(620, "samarium", TypeRegistry.METAL, RagiColorManager.GRAY, 150.4f, "Sa", 1074, 1794)

    val HAFNIUM = ElementBuilder(720, "hafnium", TypeRegistry.METAL, RagiColorManager.GRAY, 178.5f, "Hf", 2233, 4603)

    val TANTALUM = ElementBuilder(730, "tantalum", TypeRegistry.METAL, RagiColorManager.GRAY, 180.9f, "Ta", 3017, 5458)

    val TUNGSTEN = ElementBuilder(740, "tungsten", TypeRegistry.METAL, RagiColorManager.mixColor(RagiColorManager.BLACK, RagiColorManager.DARK_GRAY), 183.8f, "W", 3422, 5555)

    val OSMIUM = ElementBuilder(760, "osmium", TypeRegistry.METAL, RagiColorManager.mixColor(mapOf(RagiColorManager.BLUE to 1, RagiColorManager.WHITE to 3)), 190.2f, "Os", 3033, 5012)

    val IRIDIUM = ElementBuilder(770, "iridium", TypeRegistry.METAL, RagiColorManager.mixColor(mapOf(RagiColorManager.AQUA to 1, RagiColorManager.WHITE to 3)), 192.2f, "Ir", 2446, 4428)

    val PLATINUM = ElementBuilder(780, "platinum", TypeRegistry.METAL, RagiColorManager.mixColor(mapOf(RagiColorManager.GREEN to 1, RagiColorManager.WHITE to 3)), 195.1f, "Pt", 1768, 3825)

    val GOLD = ElementBuilder(790, "gold", TypeRegistry.METAL, RagiColorManager.YELLOW, 197.0f, "Au", 1064, 2856)

    val MERCURY = ElementBuilder(800, "mercury", TypeRegistry.LIQUID, RagiColorManager.WHITE, 200.6f, "Hg", -39, 357)

    val LEAD = ElementBuilder(820, "lead", TypeRegistry.METAL, RagiColorManager.mixColor(RagiColorManager.DARK_BLUE, RagiColorManager.DARK_GRAY, RagiColorManager.WHITE), 207.2f, "Pb", 327, 1749)

    val BISMUTH = ElementBuilder(830, "bismuth", TypeRegistry.METAL, RagiColorManager.DARK_AQUA, 209.0f, "Bi", 271, 1564)

    //7th Period
    val THORIUM = ElementBuilder(900, "thorium", TypeRegistry.METAL_RADIO, RagiColorManager.mixColor(RagiColorManager.BLACK, RagiColorManager.DARK_GREEN), 232.0f, "Th", 1750, 4788)

    val URANIUM_238 = ElementBuilder(920, "uranium", TypeRegistry.METAL_RADIO, RagiColorManager.GREEN, 238.0f, "U", 1135, 4131)

    val PLUTONIUM_244 = ElementBuilder(940, "plutonium", TypeRegistry.METAL_RADIO, RagiColorManager.RED, 244.0f, "Pu", 640, 3228)

    //Isotope
    val DEUTERIUM = IsotopeBuilder(14, "deuterium", HYDROGEN, 2.0f, "D")

    val TRITIUM = IsotopeBuilder(15, "tritium", HYDROGEN, 3.0f, "T")

    val URANIUM_235 = IsotopeBuilder(97, "uranium235", URANIUM_238, 235.0f, "U-235")

    val PLUTONIUM_239 = IsotopeBuilder(98, "plutonium239", PLUTONIUM_244, 239.0f, "Pu-239")

    //Imaginary Elements
    val REDSTONE = ElementBuilder(209, "redstone", TypeRegistry.DUST, RagiColorManager.DARK_RED, 112.2f, "Rs", 1122, 1194)

    val GLOWSTONE = ElementBuilder(215, "glowstone", TypeRegistry.DUST, RagiColorManager.mixColor(RagiColorManager.GOLD, RagiColorManager.YELLOW), 112.2f, "Gl", 1122, 1194)

    val ENDER = ElementBuilder(219, "ender", TypeRegistry.DUST, RagiColorManager.mixColor(mapOf(RagiColorManager.DARK_GREEN to 1, RagiColorManager.BLUE to 1)), 112.2f, "En", 1122, 1194)

    //Integration
    val MITHRIL = ElementBuilder(600, "mithril", TypeRegistry.METAL, RagiColorManager.mixColor(mapOf(RagiColorManager.BLUE to 1, RagiColorManager.AQUA to 1)), 193.7f, "Mt", null, null)
}