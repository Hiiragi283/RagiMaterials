package hiiragi283.ragi_materials.materials

import hiiragi283.ragi_materials.util.RagiColor
import hiiragi283.ragi_materials.util.RegexStatics.snakeToUpperCamelCase
import java.awt.Color

enum class EnumMaterials(val index: Int, val registryName: String, val type: MaterialType, val color: Color, val melting: Int?, val boiling: Int, val mol: Float) {

    //0: DEBUG
    DEBUG(0, "hiiragi_tsubasa", MaterialType.WILDCARD, RagiColor.RAGI_RED, 283, 1109, 283.0F),

    //1~99: Periodic Table
    HYDROGEN(1, "hydrogen", MaterialType.GAS, RagiColor.BLUE, -259, -253, 1.0F),
    HELIUM(2, "helium", MaterialType.GAS, RagiColor.YELLOW, -272, -269, 4.0F),

    LITHIUM(3, "lithium", MaterialType.METAL, RagiColor.GRAY, 181, 1342, 7.0F),
    BERYLLIUM(4, "beryllium", MaterialType.METAL, RagiColor.DARK_GREEN, 1287, 2469, 9.0F),
    BORON(5, "boron", MaterialType.DUST, RagiColor.DARK_GRAY, 2075, 4000, 10.8F),
    CARBON(6, "carbon", MaterialType.CARBON, RagiColor.mixColor(RagiColor.BLACK, RagiColor.DARK_GRAY), null, 4400, 12.0F),
    NITROGEN(7, "nitrogen", MaterialType.GAS, RagiColor.AQUA, -210, -196, 14.0F),
    OXYGEN(8, "oxygen", MaterialType.GAS, RagiColor.AQUA, -219, -183, 16.0F),
    FLUORINE(9, "fluorine", MaterialType.GAS, RagiColor.GREEN, -220, -188, 19.0F),
    NEON(10, "neon", MaterialType.GAS, RagiColor.LIGHT_PURPLE, -249, -246, 20.2F),

    SODIUM(11, "sodium", MaterialType.INTERNAL, RagiColor.WHITE, 98, 883, 23.0F),
    MAGNESIUM(12, "magnesium", MaterialType.METAL, RagiColor.mixColor(RagiColor.LIGHT_PURPLE, RagiColor.WHITE), 650, 1090, 24.3F),
    ALUMINIUM(13, "aluminium", MaterialType.METAL, RagiColor.mixColor(RagiColor.AQUA, RagiColor.WHITE), 660, 2519, 27.0F),
    SILICON(14, "silicon", MaterialType.METAL, RagiColor.DARK_GRAY, 1414, 3265, 28.1F),
    PHOSPHORUS(15, "phosphorus", MaterialType.DUST, RagiColor.YELLOW, 44, 281, 31.0F),
    SULFUR(16, "sulfur", MaterialType.DUST, RagiColor.mixColor(RagiColor.GOLD, RagiColor.YELLOW), 120, 445, 32.1F),
    CHLORINE(17, "chlorine", MaterialType.GAS, RagiColor.YELLOW, -102, -34, 35.5F),
    ARGON(18, "argon", MaterialType.GAS, RagiColor.LIGHT_PURPLE, -189,-186, 40.0F),

    POTASSIUM(19, "potassium", MaterialType.INTERNAL, RagiColor.WHITE, 64, 759, 39.1F),
    CALCIUM(20, "calcium", MaterialType.INTERNAL, RagiColor.WHITE, 842, 1484, 40.1F),
    TITANIUM(22, "titanium", MaterialType.METAL, RagiColor.mixColor(mapOf(RagiColor.GOLD to 1, RagiColor.WHITE to 2)), 1668, 3287, 47.9F),
    CHROMIUM(24, "chromium", MaterialType.METAL, RagiColor.GREEN, 1907, 2671, 52.0F),
    MANGANESE(25, "manganese", MaterialType.METAL, RagiColor.GRAY, 1246, 2061, 54.9F),
    IRON(26, "iron", MaterialType.METAL, RagiColor.WHITE, 1538, 2861, 55.8F),
    COBALT(27, "cobalt", MaterialType.METAL, RagiColor.BLUE, 1495, 2927, 58.9F),
    NICKEL(28, "nickel", MaterialType.METAL, RagiColor.mixColor(RagiColor.GOLD, RagiColor.WHITE), 1455, 2913, 58.7F),
    COPPER(29, "copper", MaterialType.METAL, RagiColor.mixColor(RagiColor.GOLD, RagiColor.RED),1085, 2562, 63.5F),
    ZINC(30, "zinc", MaterialType.METAL, RagiColor.WHITE, 420, 907, 65.4F),
    GALLIUM(31, "gallium", MaterialType.METAL, RagiColor.WHITE, 30, 2204, 69.7F),
    ARSENIC(33, "arsenic", MaterialType.CARBON, RagiColor.DARK_GRAY, null, 603, 74.9F),

    STRONTIUM(38, "strontium", MaterialType.INTERNAL, RagiColor.WHITE, 777, 1377, 87.6F),
    ZIRCONIUM(40, "zirconium", MaterialType.METAL, RagiColor.mixColor(RagiColor.BLUE, RagiColor.LIGHT_PURPLE), 1855, 4409, 91.2F),
    NIOBIUM(41, "niobium", MaterialType.METAL, RagiColor.mixColor(RagiColor.DARK_BLUE, RagiColor.WHITE), 2477, 4744, 92.9F),
    MOLYBDENUM(42, "molybdenum", MaterialType.METAL, RagiColor.mixColor(RagiColor.DARK_BLUE, RagiColor.WHITE), 2023, 4639, 96.0F),
    RUTHENIUM(44, "ruthenium", MaterialType.METAL, RagiColor.WHITE, 2334, 4150, 101.1F),
    RHODIUM(45, "rhodium", MaterialType.METAL, RagiColor.WHITE, 1964, 3695, 102.9F),
    PALLADIUM(46, "palladium", MaterialType.METAL, RagiColor.WHITE,1555, 2963, 106.4F),
    SILVER(47, "silver", MaterialType.METAL, RagiColor.mixColor(mapOf(RagiColor.AQUA to 1, RagiColor.WHITE to 2)), 962, 2162, 107.9F),
    INDIUM(49, "indium", MaterialType.METAL, RagiColor.mixColor(RagiColor.DARK_BLUE, RagiColor.RED), 157, 2072, 114.8F),
    TIN(50, "tin", MaterialType.METAL, RagiColor.WHITE, 232, 2602, 118.7F),
    ANTIMONY(51, "antimony", MaterialType.METAL, RagiColor.DARK_GRAY, 631, 1587, 121.8F),
    IODINE(53, "iodine", MaterialType.DUST, RagiColor.mixColor(RagiColor.DARK_BLUE, RagiColor.DARK_RED), 114, 184, 126.9F),

    BARIUM(56, "barium", MaterialType.INTERNAL, RagiColor.WHITE, 727, 1845, 137.3F),
    NEODYMIUM(60, "neodymium", MaterialType.METAL, RagiColor.GRAY, 1021, 3074, 144.2F),
    SAMARIUM(62, "samarium", MaterialType.METAL, RagiColor.GRAY, 1074, 1794, 150.4F),
    HAFNIUM(72, "hafnium", MaterialType.METAL, RagiColor.GRAY, 2233, 4603, 178.5F),
    TANTALUM(73, "tantalum", MaterialType.METAL, RagiColor.GRAY, 3017, 5458, 180.9F),
    TUNGSTEN(74, "tungsten", MaterialType.METAL, RagiColor.mixColor(RagiColor.BLACK, RagiColor.DARK_GRAY), 3422, 5555, 183.8F),
    OSMIUM(76, "osmium", MaterialType.METAL, RagiColor.WHITE, 3033, 5012, 190.2F),
    IRIDIUM(77, "iridium", MaterialType.METAL, RagiColor.WHITE, 2446, 4428, 192.2F),
    PLATINUM(78, "platinum", MaterialType.METAL, RagiColor.WHITE, 1768, 3825, 195.1F),
    GOLD(79, "gold", MaterialType.METAL, RagiColor.YELLOW, 1064, 2856, 197.0F),
    MERCURY(80, "mercury", MaterialType.LIQUID, RagiColor.WHITE, -39, 357, 200.6F),
    LEAD(82, "lead", MaterialType.METAL, RagiColor.mixColor(RagiColor.DARK_BLUE, RagiColor.DARK_GRAY, RagiColor.WHITE), 327, 1749, 207.2F),
    BISMUTH(83, "bismuth", MaterialType.METAL, RagiColor.mixColor(RagiColor.BLUE, RagiColor.WHITE), 271, 1564, 209.0F),

    THORIUM(90, "thorium", MaterialType.METAL, RagiColor.mixColor(RagiColor.BLACK, RagiColor.DARK_GREEN), 1750, 4788, 232.0F),
    URANIUM(92, "uranium", MaterialType.METAL, RagiColor.GREEN, 1135, 4131, 238.0F),
    PLUTONIUM(94, "plutonium", MaterialType.METAL, RagiColor.RED, 640, 3228, 239.0F),

    //100~199: Atomic Group

    //200~299: Alloy

    //300~: Non-organic Compound

    //32768: WILDCARD
    WILDCARD(32768, "wildcard", MaterialType.WILDCARD, RagiColor.WHITE, 32768, 32768, 32768.0F);

    //registryNameからUCC型のStringを取得するメソッド
    fun getOreDict(): String {
        return this.registryName.snakeToUpperCamelCase()
    }

    enum class MaterialType(val hasDust: Boolean, val hasIngot: Boolean, val hasFluid: Boolean, val hasFluidBlock: Boolean) {
        CARBON(true, true, false, false), //昇華する半金属
        DUST(true, false, false, false), //粉末
        GAS(false, false, true, true), //気体
        INTERNAL(false, false, false, false), //内部データ用
        LIQUID(false, false, true, true), //液体
        METAL(true, true, true, false), //金属
        WILDCARD(true, true, true, true) //すべての部品を追加
    }
}