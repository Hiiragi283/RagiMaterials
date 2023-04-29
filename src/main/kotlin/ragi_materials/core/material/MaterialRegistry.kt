package ragi_materials.core.material

import ragi_materials.core.material.materials.*
import ragi_materials.core.material.part.MaterialPart

object MaterialRegistry {

    //Collection
    val list: MutableList<RagiMaterial> = mutableListOf()
    val mapIndex: LinkedHashMap<Int, RagiMaterial> = linkedMapOf()
    val mapName: LinkedHashMap<String, RagiMaterial> = linkedMapOf()
    val mapElement: LinkedHashMap<String, RagiMaterial> = linkedMapOf()
    val validPair: MutableList<Pair<MaterialPart, RagiMaterial>> = mutableListOf()

    //Pre-registration
    lateinit var HYDROXIDE: RagiMaterial
    lateinit var CARBONATE: RagiMaterial
    lateinit var NITRATE: RagiMaterial
    lateinit var PHOSPHATE: RagiMaterial
    lateinit var SULFATE: RagiMaterial
    lateinit var TUNGSTATE: RagiMaterial

    //10 ~ 19: Hydrogen
    lateinit var HYDROGEN: RagiMaterial
    lateinit var WATER: RagiMaterial
    lateinit var SNOW: RagiMaterial
    lateinit var ICE: RagiMaterial
    lateinit var DEUTERIUM: RagiMaterial
    lateinit var TRITIUM: RagiMaterial

    //20 ~ 29: Helium, Neon, Argon
    lateinit var HELIUM: RagiMaterial
    lateinit var NEON: RagiMaterial
    lateinit var ARGON: RagiMaterial

    //30 ~ 39: Lithium
    lateinit var LITHIUM: RagiMaterial
    lateinit var SPODUMENE: RagiMaterial

    //40 ~ 49: Beryllium
    lateinit var BERYLLIUM: RagiMaterial
    lateinit var EMERALD: RagiMaterial
    lateinit var AQUAMARINE: RagiMaterial

    //50 ~ 59: Boron
    lateinit var BORON: RagiMaterial
    lateinit var BORAX: RagiMaterial
    lateinit var BORON_OXIDE: RagiMaterial

    //60 ~ 69: Carbon
    lateinit var CARBON: RagiMaterial
    lateinit var CARBON_MONOXIDE: RagiMaterial
    lateinit var CARBON_DIOXIDE: RagiMaterial
    lateinit var COAL: RagiMaterial
    lateinit var CHARCOAL: RagiMaterial
    lateinit var COKE: RagiMaterial
    lateinit var ANTHRACITE: RagiMaterial
    lateinit var LIGNITE: RagiMaterial
    lateinit var PEAT: RagiMaterial
    lateinit var DIAMOND: RagiMaterial

    //70 ~ 79: Nitrogen
    lateinit var NITROGEN: RagiMaterial
    lateinit var NITRIC_ACID: RagiMaterial
    lateinit var NITER: RagiMaterial

    //80 ~ 89: Oxygen
    lateinit var OXYGEN: RagiMaterial

    //90 ~ 99: Fluorite
    lateinit var FLUORINE: RagiMaterial
    lateinit var FLUORITE: RagiMaterial
    lateinit var HYDROGEN_FLUORIDE: RagiMaterial
    lateinit var CRYOLITE: RagiMaterial

    //100 ~ 109: Misc (1)
    lateinit var WOOD: RagiMaterial
    lateinit var LAPIS: RagiMaterial
    lateinit var REDSTONE: RagiMaterial
    lateinit var GLOWSTONE: RagiMaterial
    lateinit var ENDER_PEARL: RagiMaterial

    //110 ~ 119: Sodium
    lateinit var SODIUM: RagiMaterial
    lateinit var SODIUM_HYDROXIDE: RagiMaterial
    lateinit var SALT: RagiMaterial
    lateinit var SODIUM_SULFATE: RagiMaterial

    //120 ~ 129: Magnesium
    lateinit var MAGNESIUM: RagiMaterial
    lateinit var MAGNESITE: RagiMaterial
    lateinit var MAGNESIUM_CHLORIDE: RagiMaterial

    //130 ~ 139: Aluminium
    lateinit var ALUMINIUM: RagiMaterial
    lateinit var ALUMINA: RagiMaterial
    lateinit var ALUMINA_SOLUTION: RagiMaterial
    lateinit var BAUXITE: RagiMaterial
    lateinit var RUBY: RagiMaterial
    lateinit var SAPPHIRE: RagiMaterial

    //140 ~ 149: Silicon
    lateinit var SILICON: RagiMaterial
    lateinit var SILICON_DIOXIDE: RagiMaterial
    lateinit var GLASS: RagiMaterial
    lateinit var QUARTZ: RagiMaterial

    //150 ~ 159: Phosphorus
    lateinit var PHOSPHORUS: RagiMaterial

    //160 ~ 169: Sulfur
    lateinit var SULFUR: RagiMaterial
    lateinit var SULFURIC_ACID: RagiMaterial

    //170 ~ 179: Chlorine
    lateinit var CHLORINE: RagiMaterial
    lateinit var HYDROGEN_CHLORIDE: RagiMaterial

    //180 ~ 189: Stone
    lateinit var STONE: RagiMaterial
    lateinit var LAVA: RagiMaterial
    lateinit var OBSIDIAN: RagiMaterial
    lateinit var NETHERRACK: RagiMaterial
    lateinit var SOUL_SAND: RagiMaterial
    lateinit var END_STONE: RagiMaterial

    //190 ~ 199: Potassium
    lateinit var POTASSIUM: RagiMaterial

    //200 ~ 209: Calcium
    lateinit var CALCIUM: RagiMaterial
    lateinit var CALCIUM_HYDROXIDE: RagiMaterial
    lateinit var LIME: RagiMaterial
    lateinit var QUICK_LIME: RagiMaterial
    lateinit var APATITE: RagiMaterial
    lateinit var GYPSUM: RagiMaterial

    //220 ~ 229: Titanium
    lateinit var TITANIUM: RagiMaterial
    lateinit var RUTILE: RagiMaterial
    lateinit var TITANIUM_TETRACHLORIDE: RagiMaterial

    //240 ~ 249: Chromium
    lateinit var CHROMIUM: RagiMaterial
    lateinit var STAINLESS_STEEL: RagiMaterial

    //250 ~ 259: Manganese
    lateinit var MANGANESE: RagiMaterial

    //260 ~ 269: Iron
    lateinit var IRON: RagiMaterial
    lateinit var HEMATITE: RagiMaterial
    lateinit var MAGNETITE: RagiMaterial
    lateinit var PYRITE: RagiMaterial
    lateinit var ARSENOPYRITE: RagiMaterial
    lateinit var STEEL: RagiMaterial
    lateinit var WROUGHT_IRON: RagiMaterial

    //270 ~ 279: Cobalt
    lateinit var COBALT: RagiMaterial

    //280 ~ 289: Nickel
    lateinit var NICKEL: RagiMaterial
    lateinit var INVAR: RagiMaterial
    lateinit var CONSTANTAN: RagiMaterial

    //290 ~ 299: Copper
    lateinit var COPPER: RagiMaterial

    //300 ~ 309: Zinc
    lateinit var ZINC: RagiMaterial
    lateinit var BRASS: RagiMaterial

    //310 ~ 319: Gallium, Arsenic
    lateinit var GALLIUM: RagiMaterial
    lateinit var ARSENIC: RagiMaterial

    //380 ~ 389: Strontium
    lateinit var STRONTIUM: RagiMaterial

    //400 ~ 409: Zirconium
    lateinit var ZIRCONIUM: RagiMaterial

    //410 ~ 419: Niobium
    lateinit var NIOBIUM: RagiMaterial

    //420 ~ 429: Molybdenum
    lateinit var MOLYBDENUM: RagiMaterial

    //440 ~ 449: Platinum Group Metal
    lateinit var RUTHENIUM: RagiMaterial
    lateinit var RHODIUM: RagiMaterial
    lateinit var PALLADIUM: RagiMaterial
    lateinit var OSMIUM: RagiMaterial
    lateinit var IRIDIUM: RagiMaterial
    lateinit var PLATINUM: RagiMaterial

    //470 ~ 479: Silver
    lateinit var SILVER: RagiMaterial

    //490 ~ 499: Indium
    lateinit var INDIUM: RagiMaterial

    //500 ~ 509: Tin
    lateinit var TIN: RagiMaterial

    //510 ~ 519: Antimony
    lateinit var ANTIMONY: RagiMaterial

    //530 ~ 539: Iodine
    lateinit var IODINE: RagiMaterial

    //560 ~ 569: Barium
    lateinit var BARIUM: RagiMaterial

    //570 ~ 579: Rare Earth
    lateinit var NEODYMIUM: RagiMaterial

    lateinit var SAMARIUM: RagiMaterial

    //720 ~ 729: Hafnium
    lateinit var HAFNIUM: RagiMaterial

    //730 ~ 739: Tantalum
    lateinit var TANTALUM: RagiMaterial

    //740 ~ 749: Tungsten
    lateinit var TUNGSTEN: RagiMaterial

    //790 ~ 799: Gold
    lateinit var GOLD: RagiMaterial

    //800 ~ 809: Mercury
    lateinit var MERCURY: RagiMaterial

    //820 ~ 829: Lead
    lateinit var LEAD: RagiMaterial

    //830 ~ 839: Bismuth
    lateinit var BISMUTH: RagiMaterial

    //900 ~ 909: Thorium
    lateinit var THORIUM: RagiMaterial

    //920 ~ 929: Uranium
    lateinit var URANIUM_238: RagiMaterial
    lateinit var URANIUM_235: RagiMaterial

    //940 ~ 949: Plutonium
    lateinit var PLUTONIUM_244: RagiMaterial
    lateinit var PLUTONIUM_239: RagiMaterial

    fun registerMaterials() {

        PeriodFirst.load()
        PeriodSecond.load()
        PeriodThird.load()
        PeriodFourth.load()
        PeriodFifth.load()
        PeriodSixth.load()
        PeriodSeventh.load()

    }
}