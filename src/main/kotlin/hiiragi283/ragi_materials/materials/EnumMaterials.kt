package hiiragi283.ragi_materials.materials

import hiiragi283.ragi_materials.util.RagiColor
import hiiragi283.ragi_materials.util.RegexStatics.snakeToUpperCamelCase
import java.awt.Color

enum class EnumMaterials(val index: Int, val registryName: String, val type: MaterialType, val color: Color) {

    //0: FOR DEBUG
    DEBUG(0, "hiiragi_tsubasa", MaterialType.WILDCARD, RagiColor.RAGI_RED),

    //1~100: Periodic Table
    HYDROGEN(1, "hydrogen", MaterialType.GAS, RagiColor.BLUE),
    HELIUM(2, "helium", MaterialType.GAS, RagiColor.YELLOW),

    LITHIUM(3, "lithium", MaterialType.METAL, RagiColor.GRAY),
    BERYLLIUM(4, "beryllium", MaterialType.METAL, RagiColor.DARK_GREEN),
    BORON(5, "boron", MaterialType.DUST, RagiColor.DARK_GRAY),
    CARBON(6, "carbon", MaterialType.METAL, RagiColor.mixColor(RagiColor.BLACK, RagiColor.DARK_GRAY)),
    NITROGEN(7, "nitrogen", MaterialType.GAS, RagiColor.AQUA),
    OXYGEN(8, "oxygen", MaterialType.GAS, RagiColor.AQUA),
    FLUORINE(9, "fluorine", MaterialType.GAS, RagiColor.GREEN),
    NEON(10, "neon", MaterialType.GAS, RagiColor.LIGHT_PURPLE),

    SODIUM(11, "sodium", MaterialType.INTERNAL, RagiColor.WHITE),
    MAGNESIUM(12, "magnesium", MaterialType.METAL, RagiColor.mixColor(RagiColor.LIGHT_PURPLE, RagiColor.WHITE)),
    ALUMINIUM(13, "aluminium", MaterialType.METAL, RagiColor.mixColor(RagiColor.AQUA, RagiColor.WHITE)),
    SILICON(14, "silicon", MaterialType.METAL, RagiColor.DARK_GRAY),
    PHOSPHORUS(15, "phosphorus", MaterialType.DUST, RagiColor.YELLOW),
    SULFUR(16, "sulfur", MaterialType.DUST, RagiColor.mixColor(RagiColor.GOLD, RagiColor.YELLOW)),
    CHLORINE(17, "chlorine", MaterialType.GAS, RagiColor.YELLOW),
    ARGON(18, "argon", MaterialType.GAS, RagiColor.LIGHT_PURPLE),

    POTASSIUM(19, "potassium", MaterialType.INTERNAL, RagiColor.WHITE),
    CALCIUM(20, "calcium", MaterialType.INTERNAL, RagiColor.WHITE),
    TITANIUM(22, "titanium", MaterialType.METAL, RagiColor.mixColor(mapOf(RagiColor.GOLD to 1, RagiColor.WHITE to 2))),
    CHROMIUM(24, "chromium", MaterialType.METAL, RagiColor.GREEN),
    MANGANESE(25, "manganese", MaterialType.METAL, RagiColor.GRAY),
    IRON(26, "iron", MaterialType.METAL, RagiColor.WHITE),
    COBALT(27, "cobalt", MaterialType.METAL, RagiColor.BLUE),
    NICKEL(28, "nickel", MaterialType.METAL, RagiColor.mixColor(RagiColor.GOLD, RagiColor.WHITE)),
    COPPER(29, "copper", MaterialType.METAL, RagiColor.mixColor(RagiColor.GOLD, RagiColor.RED)),
    ZINC(30, "zinc", MaterialType.METAL, RagiColor.WHITE),
    GALLIUM(31, "gallium", MaterialType.METAL, RagiColor.WHITE),
    ARSENIC(33, "arsenic", MaterialType.DUST, RagiColor.DARK_GRAY),

    STRONTIUM(38, "strontium", MaterialType.INTERNAL, RagiColor.WHITE),
    ZIRCONIUM(40, "zirconium", MaterialType.METAL, RagiColor.mixColor(RagiColor.BLUE, RagiColor.LIGHT_PURPLE)),
    NIOBIUM(41, "niobium", MaterialType.METAL, RagiColor.mixColor(RagiColor.DARK_BLUE, RagiColor.WHITE)),
    MOLYBDENUM(42, "molybdenum", MaterialType.METAL, RagiColor.mixColor(RagiColor.DARK_BLUE, RagiColor.WHITE)),
    RUTHENIUM(44, "ruthenium", MaterialType.METAL, RagiColor.mixColor(mapOf(RagiColor.AQUA to 1, RagiColor.WHITE to 3))),
    RHODIUM(45, "rhodium", MaterialType.METAL, RagiColor.mixColor(mapOf(RagiColor.AQUA to 1, RagiColor.WHITE to 3))),
    PALLADIUM(46, "palladium", MaterialType.METAL, RagiColor.mixColor(mapOf(RagiColor.AQUA to 1, RagiColor.WHITE to 3))),
    SILVER(47, "silver", MaterialType.METAL, RagiColor.mixColor(mapOf(RagiColor.AQUA to 1, RagiColor.WHITE to 2))),
    INDIUM(49, "indium", MaterialType.METAL, RagiColor.mixColor(RagiColor.DARK_BLUE, RagiColor.RED)),
    TIN(50, "tin", MaterialType.METAL, RagiColor.WHITE),
    ANTIMONY(51, "antimony", MaterialType.METAL, RagiColor.DARK_GRAY),
    IODINE(53, "iodine", MaterialType.DUST, RagiColor.mixColor(RagiColor.DARK_BLUE, RagiColor.DARK_RED)),

    BARIUM(56, "barium", MaterialType.INTERNAL, RagiColor.WHITE),
    NEODYMIUM(60, "neodymium", MaterialType.METAL, RagiColor.GRAY),
    SAMARIUM(62, "samarium", MaterialType.METAL, RagiColor.GRAY),
    HAFNIUM(72, "hafnium", MaterialType.METAL, RagiColor.GRAY),
    TANTALUM(73, "tantalum", MaterialType.METAL, RagiColor.GRAY),
    TUNGSTEN(74, "tungsten", MaterialType.METAL, RagiColor.mixColor(RagiColor.BLACK, RagiColor.DARK_GRAY)),
    OSMIUM(76, "osmium", MaterialType.METAL, RagiColor.mixColor(RagiColor.AQUA, RagiColor.WHITE)),
    IRIDIUM(77, "iridium", MaterialType.METAL, RagiColor.mixColor(RagiColor.GREEN, RagiColor.WHITE)),
    PLATINUM(78, "platinum", MaterialType.METAL, RagiColor.AQUA),
    GOLD(79, "gold", MaterialType.METAL, RagiColor.mixColor(RagiColor.GOLD, RagiColor.YELLOW)),
    MERCURY(80, "mercury", MaterialType.LIQUID, RagiColor.WHITE),
    LEAD(82, "lead", MaterialType.METAL, RagiColor.mixColor(RagiColor.DARK_BLUE, RagiColor.DARK_GRAY, RagiColor.WHITE)),
    BISMUTH(83, "bismuth", MaterialType.METAL, RagiColor.mixColor(RagiColor.BLUE, RagiColor.WHITE)),

    THORIUM(90, "thorium", MaterialType.METAL, RagiColor.mixColor(RagiColor.BLACK, RagiColor.DARK_GREEN)),
    URANIUM(92, "uranium", MaterialType.METAL, RagiColor.GREEN),
    PLUTONIUM(94, "plutonium", MaterialType.METAL, RagiColor.RED),

    //32768: WILDCARD
    WILDCARD(32768, "wildcard", MaterialType.WILDCARD, RagiColor.WHITE);

    //registryNameからUCC型のStringを取得するメソッド
    fun getOreDict(): String {
        return this.registryName.snakeToUpperCamelCase()
    }
}