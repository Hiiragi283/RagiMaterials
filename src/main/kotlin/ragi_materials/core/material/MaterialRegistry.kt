package ragi_materials.core.material

import ragi_materials.core.config.RagiConfig
import ragi_materials.core.material.materials.*

object MaterialRegistry {

    //Collection
    val mapIndex: LinkedHashMap<Int, RagiMaterial> = linkedMapOf()
    val mapName: LinkedHashMap<String, RagiMaterial> = linkedMapOf()
    val mapElement: LinkedHashMap<String, RagiMaterial> = linkedMapOf()

    fun getElement(name: String): RagiMaterial = mapElement[name] ?: RagiMaterial.EMPTY

    fun getElements() = mapElement.values

    fun getMaterial(index: Int): RagiMaterial = mapIndex[index] ?: RagiMaterial.EMPTY

    fun getMaterial(name: String): RagiMaterial = mapName[name] ?: RagiMaterial.EMPTY

    fun getMaterials() = mapIndex.values

    //Pre-registration
    lateinit var HYDROXIDE: RagiMaterial
    lateinit var CARBONATE: RagiMaterial
    lateinit var NITRATE: RagiMaterial
    lateinit var PHOSPHATE: RagiMaterial
    lateinit var SULFATE: RagiMaterial
    lateinit var TUNGSTATE: RagiMaterial

    //1st Period
    lateinit var HYDROGEN: RagiMaterial
    lateinit var WATER: RagiMaterial
    lateinit var DEUTERIUM: RagiMaterial
    lateinit var TRITIUM: RagiMaterial

    lateinit var HELIUM: RagiMaterial

    //2nd Period
    lateinit var LITHIUM: RagiMaterial

    lateinit var EMERALD: RagiMaterial
    lateinit var AQUAMARINE: RagiMaterial

    lateinit var BORAX: RagiMaterial

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

    lateinit var NITROGEN: RagiMaterial
    lateinit var NITRIC_ACID: RagiMaterial
    lateinit var NITER: RagiMaterial

    lateinit var OXYGEN: RagiMaterial

    lateinit var FLUORINE: RagiMaterial
    lateinit var FLUORITE: RagiMaterial
    lateinit var HYDROGEN_FLUORIDE: RagiMaterial
    lateinit var CRYOLITE: RagiMaterial

    lateinit var NEON: RagiMaterial

    //3rd Period
    lateinit var SODIUM_HYDROXIDE: RagiMaterial
    lateinit var SALT: RagiMaterial

    lateinit var MAGNESIUM: RagiMaterial
    lateinit var MAGNESITE: RagiMaterial
    lateinit var MAGNESIUM_CHLORIDE: RagiMaterial

    lateinit var ALUMINIUM: RagiMaterial
    lateinit var ALUMINA: RagiMaterial
    lateinit var ALUMINA_SOLUTION: RagiMaterial
    lateinit var BAUXITE: RagiMaterial
    lateinit var RUBY: RagiMaterial
    lateinit var SAPPHIRE: RagiMaterial

    lateinit var SILICON: RagiMaterial
    lateinit var SILICON_DIOXIDE: RagiMaterial
    lateinit var GLASS: RagiMaterial
    lateinit var QUARTZ: RagiMaterial

    lateinit var PHOSPHORUS: RagiMaterial

    lateinit var SULFUR: RagiMaterial
    lateinit var SULFURIC_ACID: RagiMaterial

    lateinit var CHLORINE: RagiMaterial
    lateinit var HYDROGEN_CHLORIDE: RagiMaterial


    lateinit var ARGON: RagiMaterial

    //4th Period
    lateinit var CALCIUM_HYDROXIDE: RagiMaterial
    lateinit var LIME: RagiMaterial
    lateinit var QUICK_LIME: RagiMaterial
    lateinit var APATITE: RagiMaterial
    lateinit var GYPSUM: RagiMaterial

    lateinit var TITANIUM: RagiMaterial
    lateinit var RUTILE: RagiMaterial
    lateinit var TITANIUM_TETRACHLORIDE: RagiMaterial

    lateinit var CHROMIUM: RagiMaterial
    lateinit var STAINLESS_STEEL: RagiMaterial

    lateinit var MANGANESE: RagiMaterial

    lateinit var IRON: RagiMaterial
    lateinit var HEMATITE: RagiMaterial
    lateinit var MAGNETITE: RagiMaterial
    lateinit var PYRITE: RagiMaterial
    lateinit var STEEL: RagiMaterial
    lateinit var WROUGHT_IRON: RagiMaterial

    lateinit var COBALT: RagiMaterial

    lateinit var NICKEL: RagiMaterial
    lateinit var INVAR: RagiMaterial
    lateinit var CONSTANTAN: RagiMaterial

    lateinit var COPPER: RagiMaterial

    lateinit var ZINC: RagiMaterial
    lateinit var BRASS: RagiMaterial

    lateinit var GALLIUM: RagiMaterial
    lateinit var ARSENIC: RagiMaterial

    //5th Period
    lateinit var MOLYBDENUM: RagiMaterial

    lateinit var OSMIUM: RagiMaterial
    lateinit var IRIDIUM: RagiMaterial
    lateinit var PLATINUM: RagiMaterial

    lateinit var SILVER: RagiMaterial

    lateinit var TIN: RagiMaterial

    lateinit var ANTIMONY: RagiMaterial

    //6th Period
    lateinit var TUNGSTEN: RagiMaterial

    lateinit var GOLD: RagiMaterial

    lateinit var MERCURY: RagiMaterial

    lateinit var LEAD: RagiMaterial

    lateinit var BISMUTH: RagiMaterial

    //7th Period
    lateinit var URANIUM_238: RagiMaterial
    lateinit var URANIUM_235: RagiMaterial

    lateinit var PLUTONIUM: RagiMaterial

    //Other fundamentals
    lateinit var STONE: RagiMaterial
    lateinit var LAVA: RagiMaterial
    lateinit var WOOD: RagiMaterial
    lateinit var LAPIS: RagiMaterial
    lateinit var OBSIDIAN: RagiMaterial
    lateinit var REDSTONE: RagiMaterial
    lateinit var CLAY: RagiMaterial
    lateinit var NETHERRACK: RagiMaterial
    lateinit var SOUL_SAND: RagiMaterial
    lateinit var GLOWSTONE: RagiMaterial
    lateinit var END_STONE: RagiMaterial
    lateinit var ENDER_PEARL: RagiMaterial

    //Magic
    lateinit var MITHRIL: RagiMaterial

    fun load() {

        PeriodFirst.load()
        PeriodSecond.load()
        PeriodThird.load()
        PeriodFourth.load()
        PeriodFifth.load()
        PeriodSixth.load()
        PeriodSeventh.load()
        OtherMaterials.load()
        MagicMaterials.load()

        if (RagiConfig.module.enableMain) {

            CARBON_MONOXIDE.register()
            CARBON_DIOXIDE.register()
            ANTHRACITE.register()
            LIGNITE.register()
            PEAT.register()

            SODIUM_HYDROXIDE.register()
            MAGNESIUM_CHLORIDE.register()
            ALUMINA.register()
            ALUMINA_SOLUTION.register()
            SILICON_DIOXIDE.register()

            TITANIUM_TETRACHLORIDE.register()
            GALLIUM.register()
            ARSENIC.register()

            URANIUM_235.register()
            PLUTONIUM.register()

        }
        if (RagiConfig.module.enableMagic) {
            MITHRIL.register()
        }
        if (RagiConfig.module.enableMetallurgy) {
            WROUGHT_IRON.register()
        }

    }
}