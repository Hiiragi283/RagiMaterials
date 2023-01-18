package hiiragi283.ragi_materials.materials

import hiiragi283.ragi_materials.util.RagiColor
import hiiragi283.ragi_materials.util.RegexStatics.snakeToUpperCamelCase
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry

object MaterialRegistry {

    //0: DEBUG
    val DEBUG = MaterialBuilder(0, "hiiragi_tsubasa", MaterialBuilder.MaterialType.WILDCARD, RagiColor.RAGI_RED, 283, 1109, 283.0F)

    //1~99: Periodic Table
    val HYDROGEN = MaterialBuilder(1, "hydrogen", MaterialBuilder.MaterialType.GAS, RagiColor.BLUE, -259, -253, 1.0F)
    val HELIUM = MaterialBuilder(2, "helium", MaterialBuilder.MaterialType.GAS, RagiColor.YELLOW, -272, -269, 4.0F)

    val LITHIUM = MaterialBuilder(3, "lithium", MaterialBuilder.MaterialType.METAL, RagiColor.GRAY, 181, 1342, 7.0F)
    val BERYLLIUM = MaterialBuilder(4, "beryllium", MaterialBuilder.MaterialType.METAL, RagiColor.DARK_GREEN, 1287, 2469, 9.0F)
    val BORON = MaterialBuilder(5, "boron", MaterialBuilder.MaterialType.DUST, RagiColor.DARK_GRAY, 2075, 4000, 10.8F)
    val CARBON = MaterialBuilder(6, "carbon", MaterialBuilder.MaterialType.CARBON, RagiColor.mixColor(RagiColor.BLACK, RagiColor.DARK_GRAY), null, 4400, 12.0F)
    val NITROGEN = MaterialBuilder(7, "nitrogen", MaterialBuilder.MaterialType.GAS, RagiColor.AQUA, -210, -196, 14.0F)
    val OXYGEN = MaterialBuilder(8, "oxygen", MaterialBuilder.MaterialType.GAS, RagiColor.AQUA, -219, -183, 16.0F)
    val FLUORINE = MaterialBuilder(9, "fluorine", MaterialBuilder.MaterialType.GAS, RagiColor.GREEN, -220, -188, 19.0F)
    val NEON = MaterialBuilder(10, "neon", MaterialBuilder.MaterialType.GAS, RagiColor.LIGHT_PURPLE, -249, -246, 20.2F)

    val SODIUM = MaterialBuilder(11, "sodium", MaterialBuilder.MaterialType.INTERNAL, RagiColor.WHITE, 98, 883, 23.0F)
    val MAGNESIUM = MaterialBuilder(12, "magnesium", MaterialBuilder.MaterialType.METAL, RagiColor.mixColor(RagiColor.LIGHT_PURPLE, RagiColor.WHITE), 650, 1090, 24.3F)
    val ALUMINIUM = MaterialBuilder(13, "aluminium", MaterialBuilder.MaterialType.METAL, RagiColor.mixColor(RagiColor.AQUA, RagiColor.WHITE), 660, 2519, 27.0F)
    val SILICON = MaterialBuilder(14, "silicon", MaterialBuilder.MaterialType.METAL, RagiColor.DARK_GRAY, 1414, 3265, 28.1F)
    val PHOSPHORUS = MaterialBuilder(15, "phosphorus", MaterialBuilder.MaterialType.DUST, RagiColor.YELLOW, 44, 281, 31.0F)
    val SULFUR = MaterialBuilder(16, "sulfur", MaterialBuilder.MaterialType.DUST, RagiColor.mixColor(RagiColor.GOLD, RagiColor.YELLOW), 120, 445, 32.1F)
    val CHLORINE = MaterialBuilder(17, "chlorine", MaterialBuilder.MaterialType.GAS, RagiColor.YELLOW, -102, -34, 35.5F)
    val ARGON = MaterialBuilder(18, "argon", MaterialBuilder.MaterialType.GAS, RagiColor.LIGHT_PURPLE, -189,-186, 40.0F)

    val POTASSIUM = MaterialBuilder(19, "potassium", MaterialBuilder.MaterialType.INTERNAL, RagiColor.WHITE, 64, 759, 39.1F)
    val CALCIUM = MaterialBuilder(20, "calcium", MaterialBuilder.MaterialType.INTERNAL, RagiColor.WHITE, 842, 1484, 40.1F)
    val TITANIUM = MaterialBuilder(22, "titanium", MaterialBuilder.MaterialType.METAL, RagiColor.mixColor(mapOf(RagiColor.GOLD to 1, RagiColor.WHITE to 2)), 1668, 3287, 47.9F)
    val CHROMIUM = MaterialBuilder(24, "chromium", MaterialBuilder.MaterialType.METAL, RagiColor.GREEN, 1907, 2671, 52.0F)
    val MANGANESE = MaterialBuilder(25, "manganese", MaterialBuilder.MaterialType.METAL, RagiColor.GRAY, 1246, 2061, 54.9F)
    val IRON = MaterialBuilder(26, "iron", MaterialBuilder.MaterialType.METAL, RagiColor.WHITE, 1538, 2861, 55.8F)
    val COBALT = MaterialBuilder(27, "cobalt", MaterialBuilder.MaterialType.METAL, RagiColor.BLUE, 1495, 2927, 58.9F)
    val NICKEL = MaterialBuilder(28, "nickel", MaterialBuilder.MaterialType.METAL, RagiColor.mixColor(RagiColor.GOLD, RagiColor.WHITE), 1455, 2913, 58.7F)
    val COPPER = MaterialBuilder(29, "copper", MaterialBuilder.MaterialType.METAL, RagiColor.mixColor(RagiColor.GOLD, RagiColor.RED),1085, 2562, 63.5F)
    val ZINC = MaterialBuilder(30, "zinc", MaterialBuilder.MaterialType.METAL, RagiColor.WHITE, 420, 907, 65.4F)
    val GALLIUM = MaterialBuilder(31, "gallium", MaterialBuilder.MaterialType.METAL, RagiColor.WHITE, 30, 2204, 69.7F)
    val ARSENIC = MaterialBuilder(33, "arsenic", MaterialBuilder.MaterialType.CARBON, RagiColor.DARK_GRAY, null, 603, 74.9F)

    val STRONTIUM = MaterialBuilder(38, "strontium", MaterialBuilder.MaterialType.INTERNAL, RagiColor.WHITE, 777, 1377, 87.6F)
    val ZIRCONIUM = MaterialBuilder(40, "zirconium", MaterialBuilder.MaterialType.METAL, RagiColor.mixColor(RagiColor.BLUE, RagiColor.LIGHT_PURPLE), 1855, 4409, 91.2F)
    val NIOBIUM = MaterialBuilder(41, "niobium", MaterialBuilder.MaterialType.METAL, RagiColor.mixColor(RagiColor.DARK_BLUE, RagiColor.WHITE), 2477, 4744, 92.9F)
    val MOLYBDENUM = MaterialBuilder(42, "molybdenum", MaterialBuilder.MaterialType.METAL, RagiColor.mixColor(RagiColor.DARK_BLUE, RagiColor.WHITE), 2023, 4639, 96.0F)
    val RUTHENIUM = MaterialBuilder(44, "ruthenium", MaterialBuilder.MaterialType.METAL, RagiColor.WHITE, 2334, 4150, 101.1F)
    val RHODIUM = MaterialBuilder(45, "rhodium", MaterialBuilder.MaterialType.METAL, RagiColor.WHITE, 1964, 3695, 102.9F)
    val PALLADIUM = MaterialBuilder(46, "palladium", MaterialBuilder.MaterialType.METAL, RagiColor.WHITE,1555, 2963, 106.4F)
    val SILVER = MaterialBuilder(47, "silver", MaterialBuilder.MaterialType.METAL, RagiColor.mixColor(mapOf(RagiColor.AQUA to 1, RagiColor.WHITE to 2)), 962, 2162, 107.9F)
    val INDIUM = MaterialBuilder(49, "indium", MaterialBuilder.MaterialType.METAL, RagiColor.mixColor(RagiColor.DARK_BLUE, RagiColor.RED), 157, 2072, 114.8F)
    val TIN = MaterialBuilder(50, "tin", MaterialBuilder.MaterialType.METAL, RagiColor.WHITE, 232, 2602, 118.7F)
    val ANTIMONY = MaterialBuilder(51, "antimony", MaterialBuilder.MaterialType.METAL, RagiColor.DARK_GRAY, 631, 1587, 121.8F)
    val IODINE = MaterialBuilder(53, "iodine", MaterialBuilder.MaterialType.DUST, RagiColor.mixColor(RagiColor.DARK_BLUE, RagiColor.DARK_RED), 114, 184, 126.9F)

    val BARIUM = MaterialBuilder(56, "barium", MaterialBuilder.MaterialType.INTERNAL, RagiColor.WHITE, 727, 1845, 137.3F)
    val NEODYMIUM = MaterialBuilder(60, "neodymium", MaterialBuilder.MaterialType.METAL, RagiColor.GRAY, 1021, 3074, 144.2F)
    val SAMARIUM = MaterialBuilder(62, "samarium", MaterialBuilder.MaterialType.METAL, RagiColor.GRAY, 1074, 1794, 150.4F)
    val HAFNIUM = MaterialBuilder(72, "hafnium", MaterialBuilder.MaterialType.METAL, RagiColor.GRAY, 2233, 4603, 178.5F)
    val TANTALUM = MaterialBuilder(73, "tantalum", MaterialBuilder.MaterialType.METAL, RagiColor.GRAY, 3017, 5458, 180.9F)
    val TUNGSTEN = MaterialBuilder(74, "tungsten", MaterialBuilder.MaterialType.METAL, RagiColor.mixColor(RagiColor.BLACK, RagiColor.DARK_GRAY), 3422, 5555, 183.8F)
    val OSMIUM = MaterialBuilder(76, "osmium", MaterialBuilder.MaterialType.METAL, RagiColor.WHITE, 3033, 5012, 190.2F)
    val IRIDIUM = MaterialBuilder(77, "iridium", MaterialBuilder.MaterialType.METAL, RagiColor.WHITE, 2446, 4428, 192.2F)
    val PLATINUM = MaterialBuilder(78, "platinum", MaterialBuilder.MaterialType.METAL, RagiColor.WHITE, 1768, 3825, 195.1F)
    val GOLD = MaterialBuilder(79, "gold", MaterialBuilder.MaterialType.METAL, RagiColor.YELLOW, 1064, 2856, 197.0F)
    val MERCURY = MaterialBuilder(80, "mercury", MaterialBuilder.MaterialType.LIQUID, RagiColor.WHITE, -39, 357, 200.6F)
    val LEAD = MaterialBuilder(82, "lead", MaterialBuilder.MaterialType.METAL, RagiColor.mixColor(RagiColor.DARK_BLUE, RagiColor.DARK_GRAY, RagiColor.WHITE), 327, 1749, 207.2F)
    val BISMUTH = MaterialBuilder(83, "bismuth", MaterialBuilder.MaterialType.METAL, RagiColor.mixColor(RagiColor.BLUE, RagiColor.WHITE), 271, 1564, 209.0F)

    val THORIUM = MaterialBuilder(90, "thorium", MaterialBuilder.MaterialType.METAL, RagiColor.mixColor(RagiColor.BLACK, RagiColor.DARK_GREEN), 1750, 4788, 232.0F)
    val URANIUM = MaterialBuilder(92, "uranium", MaterialBuilder.MaterialType.METAL, RagiColor.GREEN, 1135, 4131, 238.0F)
    val PLUTONIUM = MaterialBuilder(94, "plutonium", MaterialBuilder.MaterialType.METAL, RagiColor.RED, 640, 3228, 239.0F)

    //100~199: Atomic Group

    //200~299: Alloy

    //300~: Non-organic Compound

    //32768: WILDCARD
    val WILDCARD = MaterialBuilder(32768, "wildcard", MaterialBuilder.MaterialType.WILDCARD, RagiColor.WHITE, 32768, 32768, 32768.0F)

    //listの定義
    val list = listOf(
        //DEBUG
        DEBUG,
        //Periodic Table
        HYDROGEN,
        HELIUM,
        LITHIUM,
        BERYLLIUM,
        BORON,
        CARBON,
        NITROGEN,
        OXYGEN,
        FLUORINE,
        NEON,
        SODIUM,
        MAGNESIUM,
        ALUMINIUM,
        SILICON,
        PHOSPHORUS,
        SULFUR,
        CHLORINE,
        ARGON,
        POTASSIUM,
        CALCIUM,
        TITANIUM,
        CHROMIUM,
        MANGANESE,
        IRON,
        COBALT,
        NICKEL,
        COPPER,
        ZINC,
        GALLIUM,
        ARSENIC,
        STRONTIUM,
        ZIRCONIUM,
        NIOBIUM,
        MOLYBDENUM,
        RUTHENIUM,
        RHODIUM,
        PALLADIUM,
        SILVER,
        INDIUM,
        TIN,
        ANTIMONY,
        IODINE,
        BARIUM,
        NEODYMIUM,
        SAMARIUM,
        HAFNIUM,
        TANTALUM,
        TUNGSTEN,
        OSMIUM,
        IRIDIUM,
        PLATINUM,
        GOLD,
        MERCURY,
        LEAD,
        BISMUTH,
        THORIUM,
        URANIUM,
        PLUTONIUM,
        //WILDCARD
        WILDCARD
    )

    //道具に使える素材と耐久値のMap
    val mapToolMaterial = mapOf(
        DEBUG to 1109,
        TITANIUM to 512,
        IRON to 64,
        COPPER to 32,
        TUNGSTEN to 512
    )

    //EnumMaterialもしくはindexから液体を取得するメソッド
    fun MaterialBuilder.getFluid(): Fluid {
        val fluid = FluidRegistry.getFluid(this.registryName)
        //fluidが存在しない場合は水を返す
        return if (fluid !== null) fluid else FluidRegistry.getFluid("water")
    }

    fun getFluid(index: Int): Fluid {
        return getMaterial(index).getFluid()
    }

    //代入したindexと一致するMaterialBuilderを返すメソッド
    fun getMaterial(index: Int): MaterialBuilder {
        //デフォルト値はWILDCARD
        var materialMatches = WILDCARD
        for (material in list) {
            //indexが一致する場合
            if (material.index == index) {
                //materialMatchesにmaterialを代入
                materialMatches = material
            }
        }
        //materialMatchesを返す
        return materialMatches
    }

    //代入したregistryNameと一致するEnumMaterialsを返すメソッド
    fun getMaterial(registryName: String): MaterialBuilder {
        //デフォルト値はWILDCARD
        var materialMatches = WILDCARD
        for (material in list) {
            //indexが一致する場合
            if (material.registryName == registryName) {
                //materialMatchesにmaterialを代入
                materialMatches = material
            }
        }
        //materialMatchesを返す
        return materialMatches
    }

    //registryNameからUCC型のStringを取得するメソッド
    fun MaterialBuilder.getOreDict(): String {
        return this.registryName.snakeToUpperCamelCase()
    }
}