package hiiragi283.ragi_materials.material

import hiiragi283.ragi_materials.client.render.color.RagiColorManager
import hiiragi283.ragi_materials.material.type.TypeRegistry

object ElementRegistryNew {

    //Periodic Table
    //1st Period
    val HYDROGEN = RagiMaterial.Element("hydrogen", TypeRegistry.GAS, RagiColorManager.BLUE, 1.0f, "H", -259, -253).build()

    val HELIUM = RagiMaterial.Element("helium", TypeRegistry.GAS, RagiColorManager.YELLOW, 4.0f, "He", -272, -269).build()

    //2nd Period
    val LITHIUM = RagiMaterial.Element("lithium", TypeRegistry.METAL, RagiColorManager.GRAY, 7.0f, "Li", 181, 1342).build()

    val BERYLLIUM = RagiMaterial.Element("beryllium", TypeRegistry.METAL, RagiColorManager.DARK_GREEN, 9.0f, "Be", 1287, 2469).build()

    val BORON = RagiMaterial.Element("boron", TypeRegistry.DUST, RagiColorManager.DARK_GRAY, 10.8f, "B", 2075, 4000).build()

    val CARBON = RagiMaterial.Element("carbon", TypeRegistry.METALLOID, RagiColorManager.mixColor(RagiColorManager.BLACK, RagiColorManager.DARK_GRAY), 12.0f, "C", 4400, 4400).build()

    val NITROGEN = RagiMaterial.Element("nitrogen", TypeRegistry.GAS, RagiColorManager.AQUA, 14.0f, "N", -210, -196).build()

    val OXYGEN = RagiMaterial.Element("oxygen", TypeRegistry.GAS, RagiColorManager.WHITE, 16.0f, "O", -219, -183).build()

    val FLUORINE = RagiMaterial.Element("fluorine", TypeRegistry.GAS, RagiColorManager.GREEN, 19.0f, "F", -220, -188).build()

    val NEON = RagiMaterial.Element("neon", TypeRegistry.GAS, RagiColorManager.LIGHT_PURPLE, 20.2f, "Ne", -249, -246).build()

    //3rd Period
    val SODIUM = RagiMaterial.Element("sodium", TypeRegistry.INTERNAL, RagiColorManager.WHITE, 23.0f, "Na", 98, 883).build()

    val MAGNESIUM = RagiMaterial.Element("magnesium", TypeRegistry.METAL, RagiColorManager.mixColor(RagiColorManager.LIGHT_PURPLE, RagiColorManager.WHITE), 24.3f, "Mg", 650, 1090).build()

    val ALUMINIUM = RagiMaterial.Element("aluminium", TypeRegistry.METAL, RagiColorManager.mixColor(RagiColorManager.AQUA, RagiColorManager.WHITE), 27.0f, "Al", 660, 2519).build()

    val SILICON = RagiMaterial.Element("silicon", TypeRegistry.METAL, RagiColorManager.DARK_GRAY, 28.1f, "Si", 1414, 3265).build()

    val PHOSPHORUS = RagiMaterial.Element("phosphorus", TypeRegistry.DUST, RagiColorManager.YELLOW, 31.0f, "P", 44, 281).build()

    val SULFUR = RagiMaterial.Element("sulfur", TypeRegistry.DUST, RagiColorManager.mixColor(RagiColorManager.GOLD, RagiColorManager.YELLOW), 32.1f, "S", 120, 445).build()

    val CHLORINE = RagiMaterial.Element("chlorine", TypeRegistry.GAS, RagiColorManager.YELLOW, 35.5f, "Cl", -102, -34).build()

    val ARGON = RagiMaterial.Element("argon", TypeRegistry.GAS, RagiColorManager.LIGHT_PURPLE, 40.0f, "Ar", -189, -186).build()

    //4th Period
    val POTASSIUM = RagiMaterial.Element("potassium", TypeRegistry.INTERNAL, RagiColorManager.WHITE, 39.1f, "K", 64, 759).build()

    val CALCIUM = RagiMaterial.Element("calcium", TypeRegistry.INTERNAL, RagiColorManager.WHITE, 40.1f, "Ca", 842, 1484).build()

    val TITANIUM = RagiMaterial.Element("titanium", TypeRegistry.METAL, RagiColorManager.mixColor(mapOf(RagiColorManager.GOLD to 1, RagiColorManager.WHITE to 2)), 47.9f, "Ti", 1668, 3287).build()

    val CHROMIUM = RagiMaterial.Element("chromium", TypeRegistry.METAL, RagiColorManager.GREEN, 52.0f, "Cr", 1907, 2671).build()

    val MANGANESE = RagiMaterial.Element("manganese", TypeRegistry.METAL, RagiColorManager.mixColor(RagiColorManager.RED, RagiColorManager.WHITE), 54.9f, "Mn", 1246, 2061).build()

    val IRON = RagiMaterial.Element("iron", TypeRegistry.METAL, RagiColorManager.WHITE, 55.8f, "Fe", 1538, 2861).build()

    val COBALT = RagiMaterial.Element("cobalt", TypeRegistry.METAL, RagiColorManager.BLUE, 58.9f, "Co", 1495, 2927).build()

    val NICKEL = RagiMaterial.Element("nickel", TypeRegistry.METAL, RagiColorManager.mixColor(RagiColorManager.GOLD, RagiColorManager.WHITE), 58.7f, "Ni", 1455, 2913).build()

    val COPPER = RagiMaterial.Element("copper", TypeRegistry.METAL, RagiColorManager.mixColor(RagiColorManager.GOLD, RagiColorManager.RED), 63.5f, "Cu", 1085, 2562).build()

    val ZINC = RagiMaterial.Element("zinc", TypeRegistry.METAL, RagiColorManager.WHITE, 65.4f, "Zn", 420, 907).build()

    val GALLIUM = RagiMaterial.Element("gallium", TypeRegistry.METAL, RagiColorManager.WHITE, 69.7f, "Ga", 30, 2204).build()

    val ARSENIC = RagiMaterial.Element("arsenic", TypeRegistry.METALLOID, RagiColorManager.DARK_GRAY, 74.9f, "As", 603, 603).build()

    //5th Period
    val STRONTIUM = RagiMaterial.Element("strontium", TypeRegistry.INTERNAL, RagiColorManager.WHITE, 87.6f, "Sr", 777, 1377).build()

    val ZIRCONIUM = RagiMaterial.Element("zirconium", TypeRegistry.METAL, RagiColorManager.mixColor(RagiColorManager.BLUE, RagiColorManager.LIGHT_PURPLE), 91.2f, "Zr", 1855, 4409).build()

    val NIOBIUM = RagiMaterial.Element("niobium", TypeRegistry.METAL, RagiColorManager.mixColor(RagiColorManager.DARK_BLUE, RagiColorManager.WHITE), 92.9f, "Nb", 2477, 4744).build()

    val MOLYBDENUM = RagiMaterial.Element("molybdenum", TypeRegistry.METAL, RagiColorManager.mixColor(RagiColorManager.DARK_BLUE, RagiColorManager.WHITE), 96.0f, "Mo", 2023, 4639).build()

    val RUTHENIUM = RagiMaterial.Element("ruthenium", TypeRegistry.METAL, RagiColorManager.mixColor(mapOf(RagiColorManager.LIGHT_PURPLE to 1, RagiColorManager.WHITE to 3)), 101.1f, "Ru", 2334, 4150).build()

    val RHODIUM = RagiMaterial.Element("rhodium", TypeRegistry.METAL, RagiColorManager.mixColor(mapOf(RagiColorManager.RED to 1, RagiColorManager.WHITE to 3)), 102.9f, "Rh", 1964, 3695).build()

    val PALLADIUM = RagiMaterial.Element("palladium", TypeRegistry.METAL, RagiColorManager.mixColor(mapOf(RagiColorManager.YELLOW to 1, RagiColorManager.WHITE to 3)), 106.4f, "Pa", 1555, 2963).build()

    val SILVER = RagiMaterial.Element("silver", TypeRegistry.METAL, RagiColorManager.mixColor(mapOf(RagiColorManager.AQUA to 1, RagiColorManager.WHITE to 2)), 107.9f, "Ag", 962, 2162).build()

    val INDIUM = RagiMaterial.Element("indium", TypeRegistry.METAL, RagiColorManager.mixColor(RagiColorManager.DARK_BLUE, RagiColorManager.RED), 114.8f, "In", 157, 2072).build()

    val TIN = RagiMaterial.Element("tin", TypeRegistry.METAL, RagiColorManager.WHITE, 118.7f, "Sn", 232, 2602).build()

    val ANTIMONY = RagiMaterial.Element("antimony", TypeRegistry.METAL, RagiColorManager.DARK_GRAY, 121.8f, "Sb", 631, 1587).build()

    val IODINE = RagiMaterial.Element("iodine", TypeRegistry.DUST, RagiColorManager.mixColor(RagiColorManager.DARK_BLUE, RagiColorManager.DARK_RED), 126.9f, "I", 114, 184).build()

    //6th Period
    val BARIUM = RagiMaterial.Element("barium", TypeRegistry.INTERNAL, RagiColorManager.WHITE, 137.3f, "Ba", 727, 1845).build()

    val NEODYMIUM = RagiMaterial.Element("neodymium", TypeRegistry.METAL, RagiColorManager.GRAY, 144.2f, "Nd", 1021, 3074).build()

    val SAMARIUM = RagiMaterial.Element("samarium", TypeRegistry.METAL, RagiColorManager.GRAY, 150.4f, "Sa", 1074, 1794).build()

    val HAFNIUM = RagiMaterial.Element("hafnium", TypeRegistry.METAL, RagiColorManager.GRAY, 178.5f, "Hf", 2233, 4603).build()

    val TANTALUM = RagiMaterial.Element("tantalum", TypeRegistry.METAL, RagiColorManager.GRAY, 180.9f, "Ta", 3017, 5458).build()

    val TUNGSTEN = RagiMaterial.Element("tungsten", TypeRegistry.METAL, RagiColorManager.mixColor(RagiColorManager.BLACK, RagiColorManager.DARK_GRAY), 183.8f, "W", 3422, 5555).build()

    val OSMIUM = RagiMaterial.Element("osmium", TypeRegistry.METAL, RagiColorManager.mixColor(mapOf(RagiColorManager.BLUE to 1, RagiColorManager.WHITE to 3)), 190.2f, "Os", 3033, 5012).build()

    val IRIDIUM = RagiMaterial.Element("iridium", TypeRegistry.METAL, RagiColorManager.mixColor(mapOf(RagiColorManager.AQUA to 1, RagiColorManager.WHITE to 3)), 192.2f, "Ir", 2446, 4428).build()

    val PLATINUM = RagiMaterial.Element("platinum", TypeRegistry.METAL, RagiColorManager.mixColor(mapOf(RagiColorManager.GREEN to 1, RagiColorManager.WHITE to 3)), 195.1f, "Pt", 1768, 3825).build()

    val GOLD = RagiMaterial.Element("gold", TypeRegistry.METAL, RagiColorManager.YELLOW, 197.0f, "Au", 1064, 2856).build()

    val MERCURY = RagiMaterial.Element("mercury", TypeRegistry.LIQUID, RagiColorManager.WHITE, 200.6f, "Hg", -39, 357).build()

    val LEAD = RagiMaterial.Element("lead", TypeRegistry.METAL, RagiColorManager.mixColor(RagiColorManager.DARK_BLUE, RagiColorManager.DARK_GRAY, RagiColorManager.WHITE), 207.2f, "Pb", 327, 1749).build()

    val BISMUTH = RagiMaterial.Element("bismuth", TypeRegistry.METAL, RagiColorManager.DARK_AQUA, 209.0f, "Bi", 271, 1564).build()

    //7th Period
    val THORIUM = RagiMaterial.Element("thorium", TypeRegistry.METAL_RADIO, RagiColorManager.mixColor(RagiColorManager.BLACK, RagiColorManager.DARK_GREEN), 232.0f, "Th", 1750, 4788).build()

    val URANIUM_238 = RagiMaterial.Element("uranium", TypeRegistry.METAL_RADIO, RagiColorManager.GREEN, 238.0f, "U", 1135, 4131).build()

    val PLUTONIUM_244 = RagiMaterial.Element("plutonium", TypeRegistry.METAL_RADIO, RagiColorManager.RED, 244.0f, "Pu", 640, 3228).build()

    //Isotope
    /*val DEUTERIUM = IsotopeBuilder( "deuterium", HYDROGEN, 2.0f, "D").build()

    val TRITIUM = IsotopeBuilder( "tritium", HYDROGEN, 3.0f, "T").build()

    val URANIUM_235 = IsotopeBuilder( "uranium235", URANIUM_238, 235.0f, "U-235").build()

    val PLUTONIUM_239 = IsotopeBuilder( "plutonium239", PLUTONIUM_244, 239.0f, "Pu-239").build()*/

    //Imaginary Elements
    val REDSTONE = RagiMaterial.Element("redstone", TypeRegistry.DUST, RagiColorManager.DARK_RED, 112.2f, "Rs", 1122, 1194).build()

    val GLOWSTONE = RagiMaterial.Element("glowstone", TypeRegistry.DUST, RagiColorManager.mixColor(RagiColorManager.GOLD, RagiColorManager.YELLOW), 112.2f, "Gl", 1122, 1194).build()

    val ENDER = RagiMaterial.Element("ender", TypeRegistry.DUST, RagiColorManager.mixColor(mapOf(RagiColorManager.DARK_GREEN to 1, RagiColorManager.BLUE to 1)), 112.2f, "En", 1122, 1194).build()

    //Integration
    val MITHRIL = RagiMaterial.Element("mithril", TypeRegistry.METAL, RagiColorManager.mixColor(mapOf(RagiColorManager.BLUE to 1, RagiColorManager.AQUA to 1)), 193.7f, "Mt", 1937, 1937).build()

}