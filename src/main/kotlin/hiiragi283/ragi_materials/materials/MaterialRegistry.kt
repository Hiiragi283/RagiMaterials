package hiiragi283.ragi_materials.materials

import hiiragi283.ragi_materials.util.RagiColor
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidRegistry
import hiiragi283.ragi_materials.materials.MaterialBuilder.MaterialType

object MaterialRegistry {

    //0: DEBUG
    val DEBUG = MaterialBuilder(0, "hiiragi_tsubasa", MaterialType.WILDCARD)
        .setColor(RagiColor.RAGI_RED).setMolarMass(283.0f)
        .setTempMelt(283).setTempBoil(1109)

    //1~94: Periodic Table
    //1st Period
    val HYDROGEN = MaterialBuilder(1, "hydrogen", MaterialType.GAS)
        .setColor(RagiColor.BLUE).setMolarMass(1.0f)
        .setTempMelt(-259).setTempBoil(-253)

    val HELIUM = MaterialBuilder(2, "helium", MaterialType.GAS)
        .setColor(RagiColor.YELLOW).setMolarMass(4.0f)
        .setTempMelt(-272).setTempBoil(-269)

    //2nd Period
    val LITHIUM = MaterialBuilder(3, "lithium", MaterialType.METAL)
        .setColor(RagiColor.GRAY).setMolarMass(7.0f)
        .setTempMelt(181).setTempBoil(1342)

    val BERYLLIUM = MaterialBuilder(4, "beryllium", MaterialType.METAL)
        .setColor(RagiColor.DARK_GREEN).setMolarMass(9.0f)
        .setTempMelt(1287).setTempBoil(2469)

    val BORON = MaterialBuilder(5, "boron", MaterialType.DUST)
        .setColor(RagiColor.DARK_GRAY).setMolarMass(10.8f)
        .setTempMelt(2075).setTempBoil(4000)

    val CARBON = MaterialBuilder(6, "carbon", MaterialType.CARBON)
        .setColor(RagiColor.mixColor(RagiColor.BLACK, RagiColor.DARK_GRAY)).setMolarMass(12.0f)
        .setTempSubl(4400)

    val NITROGEN = MaterialBuilder(7, "nitrogen", MaterialType.GAS)
        .setColor(RagiColor.AQUA).setMolarMass(14.0f)
        .setTempMelt(-210).setTempBoil(-196)

    val OXYGEN = MaterialBuilder(8, "oxygen", MaterialType.GAS)
        .setColor(RagiColor.AQUA).setMolarMass(16.0f)
        .setTempMelt(-219).setTempBoil(-183)

    val FLUORINE = MaterialBuilder(9, "fluorine", MaterialType.GAS)
        .setColor(RagiColor.GREEN).setMolarMass(19.0f)
        .setTempMelt(-220).setTempBoil(-188)

    val NEON = MaterialBuilder(10, "neon", MaterialType.GAS)
        .setColor(RagiColor.LIGHT_PURPLE).setMolarMass(20.2f)
        .setTempMelt(-249).setTempBoil(-246)

    //3rd Period
    val SODIUM = MaterialBuilder(11, "sodium", MaterialType.INTERNAL)
        .setColor(RagiColor.DARK_BLUE).setMolarMass(23.0f)
        .setTempMelt(98).setTempBoil(883)

    val MAGNESIUM = MaterialBuilder(12, "magnesium", MaterialType.METAL)
        .setColor(RagiColor.mixColor(RagiColor.LIGHT_PURPLE, RagiColor.WHITE)).setMolarMass(24.3f)
        .setTempMelt(650).setTempBoil(1090)

    val ALUMINIUM = MaterialBuilder(13, "aluminium", MaterialType.METAL)
        .setColor(RagiColor.mixColor(RagiColor.AQUA, RagiColor.WHITE)).setMolarMass(27.0f)
        .setTempMelt(660).setTempBoil(2519)

    val SILICON = MaterialBuilder(14, "silicon", MaterialType.METAL)
        .setColor(RagiColor.DARK_GRAY).setMolarMass(28.1f)
        .setTempMelt(1414).setTempBoil(3265)

    val PHOSPHORUS = MaterialBuilder(15, "phosphorus", MaterialType.DUST)
        .setColor(RagiColor.YELLOW).setMolarMass(31.0f)
        .setTempMelt(44).setTempBoil(281)

    val SULFUR = MaterialBuilder(16, "sulfur", MaterialType.DUST)
        .setColor(RagiColor.mixColor(RagiColor.GOLD, RagiColor.YELLOW)).setMolarMass(32.1f)
        .setTempMelt(120).setTempBoil(445)

    val CHLORINE = MaterialBuilder(17, "chlorine", MaterialType.GAS)
        .setColor(RagiColor.YELLOW).setMolarMass(35.5f)
        .setTempMelt(-102).setTempBoil(-34)

    val ARGON = MaterialBuilder(18, "argon", MaterialType.GAS)
        .setColor(RagiColor.LIGHT_PURPLE).setMolarMass(40.0f)
        .setTempMelt(-189).setTempBoil(-186)

    //4th Period
    val POTASSIUM = MaterialBuilder(19, "potassium", MaterialType.INTERNAL)
        .setColor(RagiColor.DARK_BLUE).setMolarMass(39.1f)
        .setTempMelt(64).setTempBoil(759)

    val CALCIUM = MaterialBuilder(20, "calcium", MaterialType.INTERNAL)
        .setColor(RagiColor.mixColor(RagiColor.LIGHT_PURPLE, RagiColor.WHITE)).setMolarMass(40.1f)
        .setTempMelt(842).setTempBoil(1484)

    val TITANIUM = MaterialBuilder(22, "titanium", MaterialType.METAL)
        .setColor(RagiColor.mixColor(mapOf(RagiColor.GOLD to 1, RagiColor.WHITE to 2))).setMolarMass(47.9f)
        .setTempMelt(1668).setTempBoil(3287)

    val CHROMIUM = MaterialBuilder(24, "chromium", MaterialType.METAL)
        .setColor(RagiColor.GREEN).setMolarMass(52.0f)
        .setTempMelt(1907).setTempBoil(2671)

    val MANGANESE = MaterialBuilder(25, "manganese", MaterialType.METAL)
        .setColor(RagiColor.GRAY).setMolarMass(54.9f)
        .setTempMelt(1246).setTempBoil(2061)

    val IRON = MaterialBuilder(26, "iron", MaterialType.METAL)
        .setMolarMass(55.8f)
        .setTempMelt(1538).setTempBoil(2861)

    val COBALT = MaterialBuilder(27, "cobalt", MaterialType.METAL)
        .setColor(RagiColor.BLUE).setMolarMass(58.9f)
        .setTempMelt(1495).setTempBoil(2927)

    val NICKEL = MaterialBuilder(28, "nickel", MaterialType.METAL)
        .setColor(RagiColor.mixColor(RagiColor.GOLD, RagiColor.WHITE)).setMolarMass(58.7f)
        .setTempMelt(1455).setTempBoil(2913)

    val COPPER = MaterialBuilder(29, "copper", MaterialType.METAL)
        .setColor(RagiColor.mixColor(RagiColor.GOLD, RagiColor.RED)).setMolarMass(63.5f)
        .setTempMelt(1085).setTempBoil(2562)

    val ZINC = MaterialBuilder(30, "zinc", MaterialType.METAL)
        .setMolarMass(65.4f)
        .setTempMelt(420).setTempBoil(907)

    val GALLIUM = MaterialBuilder(31, "gallium", MaterialType.METAL)
        .setMolarMass(69.7f)
        .setTempMelt(30).setTempBoil(2204)

    val ARSENIC = MaterialBuilder(33, "arsenic", MaterialType.CARBON)
        .setColor(RagiColor.DARK_GRAY).setMolarMass(74.9f)
        .setTempSubl(603)

    //5th Period
    val STRONTIUM = MaterialBuilder(38, "strontium", MaterialType.INTERNAL)
        .setColor(RagiColor.mixColor(RagiColor.LIGHT_PURPLE, RagiColor.WHITE)).setMolarMass(87.6f)
        .setTempMelt(777).setTempBoil(1377)

    val ZIRCONIUM = MaterialBuilder(40, "zirconium", MaterialType.METAL)
        .setColor(RagiColor.mixColor(RagiColor.BLUE, RagiColor.LIGHT_PURPLE)).setMolarMass(91.2f)
        .setTempMelt(1855).setTempBoil(4409)

    val NIOBIUM = MaterialBuilder(41, "niobium", MaterialType.METAL)
        .setColor(RagiColor.mixColor(RagiColor.DARK_BLUE, RagiColor.WHITE)).setMolarMass(92.9f)
        .setTempMelt(2477).setTempBoil(4744)

    val MOLYBDENUM = MaterialBuilder(42, "molybdenum", MaterialType.METAL)
        .setColor(RagiColor.mixColor(RagiColor.DARK_BLUE, RagiColor.WHITE)).setMolarMass(96.0f)
        .setTempMelt(2023).setTempBoil(4639)

    val RUTHENIUM = MaterialBuilder(44, "ruthenium", MaterialType.METAL)
        .setColor(RagiColor.mixColor(mapOf(RagiColor.LIGHT_PURPLE to 1, RagiColor.WHITE to 3))).setMolarMass(101.1f)
        .setTempMelt(2334).setTempBoil(4150)

    val RHODIUM = MaterialBuilder(45, "rhodium", MaterialType.METAL)
        .setColor(RagiColor.mixColor(mapOf(RagiColor.RED to 1, RagiColor.WHITE to 3))).setMolarMass(102.9f)
        .setTempMelt(1964).setTempBoil(3695)

    val PALLADIUM = MaterialBuilder(46, "palladium", MaterialType.METAL)
        .setColor(RagiColor.mixColor(mapOf(RagiColor.YELLOW to 1, RagiColor.WHITE to 3))).setMolarMass(106.4f)
        .setTempMelt(1555).setTempBoil(2963)

    val SILVER = MaterialBuilder(47, "silver", MaterialType.METAL)
        .setColor(RagiColor.mixColor(mapOf(RagiColor.AQUA to 1, RagiColor.WHITE to 2))).setMolarMass(107.9f)
        .setTempMelt(962).setTempBoil(2162)

    val INDIUM = MaterialBuilder(49, "indium", MaterialType.METAL)
        .setColor(RagiColor.mixColor(RagiColor.DARK_BLUE, RagiColor.RED)).setMolarMass(114.8f)
        .setTempMelt(157).setTempBoil(2072)

    val TIN = MaterialBuilder(50, "tin", MaterialType.METAL)
        .setMolarMass(118.7f)
        .setTempMelt(232).setTempBoil(2602)

    val ANTIMONY = MaterialBuilder(51, "antimony", MaterialType.METAL)
        .setColor(RagiColor.DARK_GRAY).setMolarMass(121.8f)
        .setTempMelt(631).setTempBoil(1587)

    val IODINE = MaterialBuilder(53, "iodine", MaterialType.DUST)
        .setColor(RagiColor.mixColor(RagiColor.DARK_BLUE, RagiColor.DARK_RED)).setMolarMass(126.9f)
        .setTempMelt(114).setTempBoil(184)

    //6th Period
    val BARIUM = MaterialBuilder(56, "barium", MaterialType.INTERNAL)
        .setMolarMass(137.3f)
        .setTempMelt(727).setTempBoil(1845)

    val NEODYMIUM = MaterialBuilder(60, "neodymium", MaterialType.METAL)
        .setColor(RagiColor.GRAY).setMolarMass(144.2f)
        .setTempMelt(1021).setTempBoil(3074)

    val SAMARIUM = MaterialBuilder(62, "samarium", MaterialType.METAL)
        .setColor(RagiColor.GRAY).setMolarMass(150.4f)
        .setTempMelt(1074).setTempBoil(1794)

    val HAFNIUM = MaterialBuilder(72, "hafnium", MaterialType.METAL)
        .setColor(RagiColor.GRAY).setMolarMass(178.5f)
        .setTempMelt(2233).setTempBoil(4603)

    val TANTALUM = MaterialBuilder(73, "tantalum", MaterialType.METAL)
        .setColor(RagiColor.GRAY).setMolarMass(180.9f)
        .setTempMelt(3017).setTempBoil(5458)

    val TUNGSTEN = MaterialBuilder(74, "tungsten", MaterialType.METAL)
        .setColor(RagiColor.mixColor(RagiColor.BLACK, RagiColor.DARK_GRAY)).setMolarMass(183.8f)
        .setTempMelt(3422).setTempBoil(5555)

    val OSMIUM = MaterialBuilder(76, "osmium", MaterialType.METAL)
        .setColor(RagiColor.mixColor(mapOf(RagiColor.BLUE to 1, RagiColor.WHITE to 3))).setMolarMass(190.2f)
        .setTempMelt(3033).setTempBoil(5012)

    val IRIDIUM = MaterialBuilder(77, "iridium", MaterialType.METAL)
        .setColor(RagiColor.mixColor(mapOf(RagiColor.AQUA to 1, RagiColor.WHITE to 3))).setMolarMass(192.2f)
        .setTempMelt(2446).setTempBoil(4428)

    val PLATINUM = MaterialBuilder(78, "platinum", MaterialType.METAL)
        .setColor(RagiColor.mixColor(mapOf(RagiColor.GREEN to 1, RagiColor.WHITE to 3))).setMolarMass(195.1f)
        .setTempMelt(1768).setTempBoil(3825)

    val GOLD = MaterialBuilder(79, "gold", MaterialType.METAL)
        .setColor(RagiColor.YELLOW).setMolarMass(197.0f)
        .setTempMelt(1064).setTempBoil(2856)

    val MERCURY = MaterialBuilder(80, "mercury", MaterialType.LIQUID)
        .setMolarMass(200.6f)
        .setTempMelt(-39).setTempBoil(357)

    val LEAD = MaterialBuilder(82, "lead", MaterialType.METAL)
        .setColor(RagiColor.mixColor(RagiColor.DARK_BLUE, RagiColor.DARK_GRAY, RagiColor.WHITE)).setMolarMass(207.2f)
        .setTempMelt(327).setTempBoil(1749)

    val BISMUTH = MaterialBuilder(83, "bismuth", MaterialType.METAL)
        .setColor(RagiColor.mixColor(RagiColor.BLUE, RagiColor.WHITE)).setMolarMass(209.0f)
        .setTempMelt(271).setTempBoil(1564)

    //7th Period
    val THORIUM = MaterialBuilder(90, "thorium", MaterialType.METAL)
        .setColor(RagiColor.mixColor(RagiColor.BLACK, RagiColor.DARK_GREEN)).setMolarMass(232.0f)
        .setTempMelt(1750).setTempBoil(4788)

    val URANIUM_238 = MaterialBuilder(92, "uranium", MaterialType.METAL)
        .setColor(RagiColor.GREEN).setMolarMass(238.0f)
        .setTempMelt(1135).setTempBoil(4131)

    val PLUTONIUM_239 = MaterialBuilder(94, "plutonium", MaterialType.METAL)
        .setColor(RagiColor.RED).setMolarMass(239.0f)
        .setTempMelt(640).setTempBoil(3228)

    //95~99: Isotope
    val DEUTERIUM = MaterialBuilder(95, "deuterium", MaterialType.GAS)
        .setColor(HYDROGEN.getColor()).setMolarMass(2.0f)
        .setTempMelt(HYDROGEN.getTempMelt()).setTempBoil(HYDROGEN.getTempBoil())

    val TRITIUM = MaterialBuilder(96, "tritium", MaterialType.GAS)
        .setColor(HYDROGEN.getColor()).setMolarMass(3.0f)
        .setTempMelt(HYDROGEN.getTempMelt()).setTempBoil(HYDROGEN.getTempBoil())

    val URANIUM_235  = MaterialBuilder(97, "uranium_235", MaterialType.METAL)
        .setColor(URANIUM_238.getColor()).setMolarMass(235.0f)
        .setTempMelt(URANIUM_238.getTempMelt()).setTempBoil(URANIUM_238.getTempBoil())

    val PLUTONIUM_241 = MaterialBuilder(98, "plutonium_241", MaterialType.METAL)
        .setColor(PLUTONIUM_239.getColor()).setMolarMass(241.0f)
        .setTempMelt(PLUTONIUM_239.getTempMelt()).setTempBoil(PLUTONIUM_239.getTempBoil())

    //100~124: Atomic Group

    //125~199: Alloy

    //200~599: Non-organic Compound

    //600~999: Organic Compound

    //1000~1023: Mixture

    //1024: WILDCARD
    val WILDCARD =
        MaterialBuilder(1024, "wildcard", MaterialType.WILDCARD)
            .setMolarMass(1024.0f)
            .setTempMelt(1024).setTempBoil(1024)


        //listの定義
        val list = mutableListOf(
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
            URANIUM_238,
            PLUTONIUM_239,
            //Isotope
            DEUTERIUM,
            TRITIUM,
            URANIUM_235,
            PLUTONIUM_241,
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
        val fluid = FluidRegistry.getFluid(this.name)
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
            if (material.name == registryName) {
                //materialMatchesにmaterialを代入
                materialMatches = material
            }
        }
        //materialMatchesを返す
        return materialMatches
    }

}