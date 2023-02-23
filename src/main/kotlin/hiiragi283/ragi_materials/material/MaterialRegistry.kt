package hiiragi283.ragi_materials.material

import hiiragi283.ragi_materials.config.RagiConfig
import hiiragi283.ragi_materials.material.element.ElementRegistry
import hiiragi283.ragi_materials.render.color.RagiColor

object MaterialRegistry {

    val mapIndex: HashMap<Int, MaterialBuilder> = hashMapOf()
    val mapName: HashMap<String, MaterialBuilder> = hashMapOf()

    //1 ~ 94: Periodic Table
    //1st Period
    val HYDROGEN = CompoundBuilder(1, ElementRegistry.HYDROGEN, 2)

    val HELIUM = CompoundBuilder(2, ElementRegistry.HELIUM, 1)

    //2nd Period
    val LITHIUM = CompoundBuilder(3, ElementRegistry.LITHIUM, 1)

    val BERYLLIUM = CompoundBuilder(4, ElementRegistry.BERYLLIUM, 1)

    val BORON = CompoundBuilder(5, ElementRegistry.BORON, 1)

    val CARBON = CompoundBuilder(6, ElementRegistry.CARBON, 1)

    val NITROGEN = CompoundBuilder(7, ElementRegistry.NITROGEN, 2)

    val OXYGEN = CompoundBuilder(8, ElementRegistry.OXYGEN, 2)

    val FLUORINE = CompoundBuilder(9, ElementRegistry.FLUORINE, 2)

    val NEON = CompoundBuilder(10, ElementRegistry.NEON, 1)

    //3rd Period
    val SODIUM = CompoundBuilder(11, ElementRegistry.SODIUM, 1)

    val MAGNESIUM = CompoundBuilder(12, ElementRegistry.MAGNESIUM, 1)

    val ALUMINIUM = CompoundBuilder(13, ElementRegistry.ALUMINIUM, 1)

    val SILICON = CompoundBuilder(14, ElementRegistry.SILICON, 1)

    val PHOSPHORUS = CompoundBuilder(15, ElementRegistry.PHOSPHORUS, 1)

    val SULFUR = CompoundBuilder(16, ElementRegistry.SULFUR, 8)

    val CHLORINE = CompoundBuilder(17, ElementRegistry.CHLORINE, 2)

    val ARGON = CompoundBuilder(18, ElementRegistry.ARGON, 1)

    //4th Period
    val POTASSIUM = CompoundBuilder(19, ElementRegistry.POTASSIUM, 1)

    val CALCIUM = CompoundBuilder(20, ElementRegistry.CALCIUM, 1)

    val TITANIUM = CompoundBuilder(22, ElementRegistry.TITANIUM, 1)

    val CHROMIUM = CompoundBuilder(24, ElementRegistry.CHROMIUM, 1)

    val MANGANESE = CompoundBuilder(25, ElementRegistry.MANGANESE, 1)

    val IRON = CompoundBuilder(26, ElementRegistry.IRON, 1)

    val COBALT = CompoundBuilder(27, ElementRegistry.COBALT, 1)

    val NICKEL = CompoundBuilder(28, ElementRegistry.NICKEL, 1)

    val COPPER = CompoundBuilder(29, ElementRegistry.COPPER, 1)

    val ZINC = CompoundBuilder(30, ElementRegistry.ZINC, 1)

    val GALLIUM = CompoundBuilder(31, ElementRegistry.GALLIUM, 1)

    val ARSENIC = CompoundBuilder(33, ElementRegistry.ARSENIC, 1)

    //5th Period
    val STRONTIUM = CompoundBuilder(38, ElementRegistry.STRONTIUM, 1)

    val ZIRCONIUM = CompoundBuilder(40, ElementRegistry.ZIRCONIUM, 1)

    val NIOBIUM = CompoundBuilder(41, ElementRegistry.NIOBIUM, 1)

    val MOLYBDENUM = CompoundBuilder(42, ElementRegistry.MOLYBDENUM, 1)

    val RUTHENIUM = CompoundBuilder(44, ElementRegistry.RUTHENIUM, 1)

    val RHODIUM = CompoundBuilder(45, ElementRegistry.RHODIUM, 1)

    val PALLADIUM = CompoundBuilder(46, ElementRegistry.PALLADIUM, 1)

    val SILVER = CompoundBuilder(47, ElementRegistry.SILVER, 1)

    val INDIUM = CompoundBuilder(49, ElementRegistry.INDIUM, 1)

    val TIN = CompoundBuilder(50, ElementRegistry.TIN, 1)

    val ANTIMONY = CompoundBuilder(51, ElementRegistry.ANTIMONY, 1)

    val IODINE = CompoundBuilder(53, ElementRegistry.IODINE, 2)

    //6th Period
    val BARIUM = CompoundBuilder(56, ElementRegistry.BARIUM, 1)

    val NEODYMIUM = CompoundBuilder(60, ElementRegistry.NEODYMIUM, 1)

    val SAMARIUM = CompoundBuilder(62, ElementRegistry.SAMARIUM, 1)

    val HAFNIUM = CompoundBuilder(72, ElementRegistry.HAFNIUM, 1)

    val TANTALUM = CompoundBuilder(73, ElementRegistry.TANTALUM, 1)

    val TUNGSTEN = CompoundBuilder(74, ElementRegistry.TUNGSTEN, 1)

    val OSMIUM = CompoundBuilder(76, ElementRegistry.OSMIUM, 1)

    val IRIDIUM = CompoundBuilder(77, ElementRegistry.IRIDIUM, 1)

    val PLATINUM = CompoundBuilder(78, ElementRegistry.PLATINUM, 1)

    val GOLD = CompoundBuilder(79, ElementRegistry.GOLD, 1)

    val MERCURY = CompoundBuilder(80, ElementRegistry.MERCURY, 1)

    val LEAD = CompoundBuilder(82, ElementRegistry.LEAD, 1)

    val BISMUTH = CompoundBuilder(83, ElementRegistry.BISMUTH, 1)

    //7th Period
    val THORIUM = CompoundBuilder(90, ElementRegistry.THORIUM, 1)

    val URANIUM_238 = CompoundBuilder(92, ElementRegistry.URANIUM_238, 1)

    val PLUTONIUM_239 = CompoundBuilder(94, ElementRegistry.PLUTONIUM_239, 1)

    //95 ~ 99: Isotope
    val DEUTERIUM = CompoundBuilder(95, ElementRegistry.DEUTERIUM, 2)

    val TRITIUM = CompoundBuilder(96, ElementRegistry.TRITIUM, 2)

    val URANIUM_235 = CompoundBuilder(97, ElementRegistry.URANIUM_235, 1)

    val PLUTONIUM_241 = CompoundBuilder(98, ElementRegistry.PLUTONIUM_241, 1)

    //100 ~ 199: Atomic Group
    val HYDROXIDE = CompoundBuilder(100, "hydroxide", MaterialType.INTERNAL, mapOf(ElementRegistry.OXYGEN to 1, ElementRegistry.HYDROGEN to 1))

    val BORATE = CompoundBuilder(101, "borate", MaterialType.INTERNAL, mapOf(ElementRegistry.BORON to 1, ElementRegistry.OXYGEN to 3))

    val CARBONATE = CompoundBuilder(102, "carbonate", MaterialType.INTERNAL, mapOf(ElementRegistry.CARBON to 1, ElementRegistry.OXYGEN to 3))

    val NITRATE = CompoundBuilder(103, "nitrate", MaterialType.INTERNAL, mapOf(ElementRegistry.NITROGEN to 1, ElementRegistry.OXYGEN to 3))

    val SILICATE = CompoundBuilder(104, "silicate", MaterialType.INTERNAL, mapOf(ElementRegistry.SILICON to 1, ElementRegistry.OXYGEN to 3))

    val PHOSPHATE = CompoundBuilder(105, "phosphate", MaterialType.INTERNAL, mapOf(ElementRegistry.PHOSPHORUS to 1, ElementRegistry.OXYGEN to 4))

    val SULFATE = CompoundBuilder(106, "sulfate", MaterialType.INTERNAL, mapOf(ElementRegistry.SULFUR to 1, ElementRegistry.OXYGEN to 4))

    //200 ~ 299: Vanilla
    val STONE = CompoundBuilder(200, "stone", MaterialType.DUST, mapOf(ElementRegistry.SILICON to 1, ElementRegistry.OXYGEN to 2))
        .setColor(RagiColor.GRAY)

    val WOOD = MixtureBuilder(201, "wood", MaterialType.DUST, listOf(ElementRegistry.CARBON, ElementRegistry.HYDROGEN, ElementRegistry.OXYGEN))
        .setColor(RagiColor.mixColor(mapOf(RagiColor.DARK_GRAY to 2, RagiColor.RED to 1, RagiColor.YELLOW to 1))).setBurnTime(200 * 1)

    val WATER = CompoundBuilder(202, "water", MaterialType.INTERNAL, mapOf(ElementRegistry.HYDROGEN to 2, ElementRegistry.OXYGEN to 1))
        .setColor(RagiColor.BLUE)

    val LAVA = CompoundBuilder(203, "lava", MaterialType.INTERNAL, mapOf(STONE to 1))
        .setColor(RagiColor.mixColor(RagiColor.DARK_RED, RagiColor.GOLD))

    val GLASS = CompoundBuilder(204, "glass", MaterialType.DUST, mapOf(STONE to 1))
        .setColor(RagiColor.WHITE)

    val LAPIS = MixtureBuilder(205, "lapis", MaterialType.DUST, listOf("?"))
        .setColor(RagiColor.BLUE)

    val CLAY = MixtureBuilder(206, "clay", MaterialType.DUST, listOf("?"))
        .setColor(
            RagiColor.mixColor(
                mapOf(
                    RagiColor.BLUE to 1,
                    RagiColor.AQUA to 1,
                    RagiColor.WHITE to 2
                )
            )
        )

    val BRICK = CompoundBuilder(207, "brick", MaterialType.INGOT, mapOf(CLAY to 1))
        .setColor(
            RagiColor.mixColor(
                mapOf(
                    RagiColor.DARK_RED to 4,
                    RagiColor.DARK_PURPLE to 1,
                    RagiColor.GOLD to 2,
                    RagiColor.WHITE to 3
                )
            )
        )

    val OBSIDIAN = CompoundBuilder(208, "obsidian", MaterialType.DUST, mapOf(STONE to 1))
        .setColor(
            RagiColor.mixColor(
                mapOf(
                    RagiColor.BLACK to 2,
                    RagiColor.BLUE to 1,
                    RagiColor.RED to 1
                )
            )
        )

    val REDSTONE = MaterialBuilder(209, "redstone", MaterialType.DUST)
        .setColor(RagiColor.DARK_RED).setFormula("Rs").register()

    val DIAMOND = CrystalBuilder(210, "diamond", mapOf(ElementRegistry.CARBON to 1), "diamond")
        .setColor(ElementRegistry.ALUMINIUM.color!!)

    val SNOW = CompoundBuilder(211, "snow", MaterialType.INGOT, mapOf(WATER to 1))
        .setColor(RagiColor.WHITE)

    val ICE = CompoundBuilder(212, "ice", MaterialType.INGOT, mapOf(WATER to 1))
        .setColor(ElementRegistry.ALUMINIUM.color!!)

    val NETHERRACK = CompoundBuilder(
        213,
        "netherrack",
        MaterialType.DUST,
        mapOf(STONE.addBracket() to 1, ElementRegistry.SULFUR to 1, ElementRegistry.PHOSPHORUS to 1)
    )
        .setColor(RagiColor.DARK_RED)

    val SOUL_SAND = MaterialBuilder(214, "soul_sand", MaterialType.DUST)

    val GLOWSTONE = CompoundBuilder(215, "glowstone", MaterialType.DUST, mapOf(ElementRegistry.GOLD to 4, REDSTONE to 5))
        .setColor(RagiColor.mixColor(RagiColor.GOLD, RagiColor.YELLOW))

    val NETHER_BRICK = CompoundBuilder(216, "nether_brick", MaterialType.INGOT, mapOf(NETHERRACK to 1))
        .setColor(
            RagiColor.mixColor(
                mapOf(
                    RagiColor.BLACK to 3,
                    RagiColor.DARK_BLUE to 1,
                    RagiColor.DARK_RED to 3
                )
            )
        )

    val END_STONE =
        CompoundBuilder(217, "end_stone", MaterialType.DUST, mapOf(STONE.addBracket() to 1, ElementRegistry.HELIUM to 1, ElementRegistry.TUNGSTEN to 1))
            .setColor(ElementRegistry.PALLADIUM.color!!)

    val EMERALD = CrystalBuilder(
        218, "emerald", mapOf(
            ElementRegistry.BERYLLIUM to 3,
            ElementRegistry.ALUMINIUM to 2,
            ElementRegistry.SILICON to 6,
            ElementRegistry.OXYGEN to 18
        ), "hexagonal"
    ).setColor(RagiColor.GREEN)

    val QUARTZ = CrystalBuilder(219, "quartz", mapOf(STONE to 1), "trigonal")
        .setColor(RagiColor.WHITE)

    val PRISMARINE = CrystalBuilder(220, "prismarine", mapOf(QUARTZ to 1, WATER.addBracket() to 1), "trigonal")
        .setColor(ElementRegistry.ALUMINIUM.color!!)

    val BONE = CompoundBuilder(221, "bone", MaterialType.DUST, mapOf(ElementRegistry.CALCIUM to 3, PHOSPHATE.addBracket() to 2))
        .setColor(RagiColor.WHITE)

    val NITER = CrystalBuilder(222, "niter", mapOf(ElementRegistry.POTASSIUM to 1, NITRATE to 1), "orthorhombic")
        .setColor(RagiColor.WHITE)

    val GUNPOWDER =
        CompoundBuilder(223, "gunpowder", MaterialType.DUST, mapOf(NITER.addBracket() to 2, ElementRegistry.CARBON to 1, ElementRegistry.SULFUR to 1))
            .setColor(RagiColor.DARK_GRAY)

    val SUGAR = CompoundBuilder(224, "sugar", MaterialType.DUST, mapOf(ElementRegistry.CARBON to 6, ElementRegistry.HYDROGEN to 12, ElementRegistry.OXYGEN to 6))
        .setColor(RagiColor.WHITE).setBurnTime(200 * 1)

    //Fossil Fuels
    val COAL = CrystalBuilder(225, "coal", mapOf(ElementRegistry.CARBON to 1), "coal").setBurnTime(200 * 8)

    val CHARCOAL = CrystalBuilder(226, "charcoal", mapOf(ElementRegistry.CARBON to 1), "coal").setBurnTime(200 * 8)

    val COKE = CrystalBuilder(227, "coke", mapOf(ElementRegistry.CARBON to 1), "coal")
        .setColor(RagiColor.DARK_GRAY).setBurnTime(200 * 16)

    val ANTHRACITE = CrystalBuilder(228, "anthracite", mapOf(ElementRegistry.CARBON to 1), "coal")
        .setColor(
            RagiColor.mixColor(
                mapOf(
                    ElementRegistry.CARBON.color!! to 5,
                    RagiColor.DARK_BLUE to 1
                )
            )
        ).setBurnTime(200 * 24)

    val LIGNITE = CrystalBuilder(229, "lignite", mapOf(ElementRegistry.CARBON to 1), "coal")
        .setColor(
            RagiColor.mixColor(
                mapOf(
                    ElementRegistry.CARBON.color!! to 5,
                    RagiColor.DARK_RED to 1
                )
            )
        ).setBurnTime(200 * 4)

    val PEAT = CrystalBuilder(230, "peat", mapOf(ElementRegistry.CARBON to 1), "coal")
        .setColor(
            RagiColor.mixColor(
                mapOf(
                    ElementRegistry.CARBON.color!! to 5,
                    RagiColor.DARK_GREEN to 1,
                    RagiColor.DARK_RED to 1
                )
            )
        ).setBurnTime(200 * 2)

    /*
    //200 ~ 299: Gem
    val ALMANDINE = CrystalBuilder(200, "almandine", mapOf(IRON to 3, ALUMINIUM to 2, SILICON to 3, OXYGEN to 12), "cubic")
        .setColor(RagiColor.DARK_RED)

    //val AMBER = CrystalBuilder(201, "amber", mapOf("" to 1), "amorphous")

    val AMETHYST = CrystalBuilder(202, "amethyst", mapOf(SILICON to 1, OXYGEN to 2), "trigonal")
        .setColor(ZIRCONIUM.color)

    val APATITE = CrystalBuilder(203, "apatite", mapOf(CALCIUM to 5, "(PO\u2084)" to 3, "OH" to 1), "hexagonal")
        .setColor(RagiColor.mixColor(RagiColor.YELLOW, RagiColor.WHITE))

    val AQUAMARINE = CrystalBuilder(204, "aquamarine", mapOf(
        BERYLLIUM to 3,
        ALUMINIUM to 2,
        SILICON to 6,
        OXYGEN to 18
    ), "hexagonal").setColor(RagiColor.AQUA)

    val CELESTITE = CrystalBuilder(205, "celestite", mapOf(
        STRONTIUM to 1,
        SULFUR to 1,
        OXYGEN to 4
    ), "orthorhombic").setColor(ALUMINIUM.color)

    val FLUORITE = CrystalBuilder(208, "fluorite", mapOf(CALCIUM to 1, FLUORINE to 2), "diamond")
        .setColor(RagiColor.mixColor(RagiColor.GREEN, RagiColor.AQUA))

    val GYPSUM = CrystalBuilder(209, "gypsum", mapOf(CALCIUM to 1, SULFUR to 1, OXYGEN to 4), "monoclinic")
        .setColor(RHODIUM.color)

    val MALACHITE = CrystalBuilder(210, "malachite", mapOf(
        COPPER to 2,
        "(CO\u2083)" to 1,
        "(OH)" to 2
    ), "monoclinic").setColor(RagiColor.DARK_GREEN)

    val OLIVINE = CrystalBuilder(211, "olivine", mapOf(MAGNESIUM to 2, SILICON to 1, OXYGEN to 4), "monoclinic")
        .setColor(RagiColor.mixColor(RagiColor.GREEN, RagiColor.WHITE))

    val OPAL = CrystalBuilder(212, "opal", mapOf(SILICON to 1, OXYGEN to 2), "amorphous")

    val SALT = CrystalBuilder(213, "salt", mapOf(SODIUM to 1, CHLORINE to 1), "cubic")
        .setColor(RagiColor.WHITE)

    val RUBY = CrystalBuilder(214, "ruby", mapOf(CHROMIUM to 1, ALUMINIUM to 2, OXYGEN to 3), "trigonal")
        .setColor(RagiColor.RED)

    val RUTILE = CrystalBuilder(215, "rutile", mapOf(TITANIUM to 1, OXYGEN to 2), "tetragonal")
        .setColor(TITANIUM.color)

    val SAPPHIRE = CrystalBuilder(216, "sapphire", mapOf(IRON to 1, ALUMINIUM to 2, OXYGEN to 3), "trigonal")
        .setColor(RagiColor.BLUE)

    val TANZANITE = CrystalBuilder(217, "tanzanite", mapOf(
        CALCIUM to 2,
        ALUMINIUM to 2,
        "(SiO\u2084)" to 1,
        "(Si\u2082O\u2087)" to 1,
        OXYGEN to 1,
        "(OH)" to 1
    ), "orthorhombic").setColor(LEAD.color)

    val TAUSONITE = CrystalBuilder(218, "tausonite", mapOf(STRONTIUM to 1, TITANIUM to 1, OXYGEN to 3), "diamond")
        .setColor(RagiColor.WHITE)

    val TOPAZ = CrystalBuilder(219, "topaz", mapOf(ALUMINIUM to 2, SILICON to 1, OXYGEN to 4, FLUORINE to 2), "orthorhombic")
        .setColor(COPPER.color)

    val ZIRCON = CrystalBuilder(220, "zircon", mapOf(ZIRCONIUM to 1, SILICON to 1, OXYGEN to 4), "tetragonal")
        .setColor(ZIRCONIUM.color)

    //300 ~ 399: Alloy
    val TITANIUM_ALLOY = AlloyBuilder(300, "titanium_alloy", mapOf(TITANIUM to 7, ALUMINIUM to 1, CHROMIUM to 1))
        .setColor(TITANIUM.color)

    val ADVANCED_ALLOY = AlloyBuilder(301, "advanced_alloy", mapOf(IRON to 3, COPPER to 3, TIN to 3))

    val INVAR = AlloyBuilder(302, "invar", mapOf(IRON to 2, NICKEL to 1))

    val KANTHAL = AlloyBuilder(303, "kanthal", mapOf(IRON to 6, CHROMIUM to 2, ALUMINIUM to 1))

    val MAGNET = AlloyBuilder(304, "magnet", mapOf(IRON to 7, CELESTITE to 1, "(CaCO\u2083)" to 1))
        .setColor(CARBON.color)

    val MANGALLOY = AlloyBuilder(305, "mangalloy", mapOf(IRON to 6, MANGANESE to 2, CARBON to 1))
        .setColor(RagiColor.mixColor(RagiColor.DARK_RED, RagiColor.WHITE))

    val STAINLESS_STEEL = AlloyBuilder(306, "stainless_steel", mapOf(IRON to 6, CHROMIUM to 2, NICKEL to 1))
        .setColor(RagiColor.mixColor(RagiColor.GRAY, RagiColor.WHITE))

    val STEEL = AlloyBuilder(307, "steel", mapOf(IRON to 1, CARBON to 1))

    val TOOL_STEEL = AlloyBuilder(308, "tool_steel", mapOf(IRON to 6, CHROMIUM to 1, MANGANESE to 1, MOLYBDENUM to 1))

    val CONSTANTAN = AlloyBuilder(309, "constantan", mapOf(NICKEL to 1, COPPER to 1))

    val NICHROME = AlloyBuilder(310, "nichrome", mapOf(NICKEL to 4, CHROMIUM to 1))

    val BRASS = AlloyBuilder(311, "brass", mapOf(COPPER to 3, ZINC to 1))
        .setColor(RagiColor.GOLD)

    val BRONZE = AlloyBuilder(312, "bronze", mapOf(COPPER to 3, TIN to 1))

    val NICKELSILVER = AlloyBuilder(313, "nickelsilver", mapOf(COPPER to 6, NICKEL to 2, ZINC to 1))
        .setColor(SILVER.color)

    val ELECTRUM = AlloyBuilder(314, "electrum", mapOf(SILVER to 1, GOLD to 1))
        .setColor(RagiColor.mixColor(RagiColor.YELLOW, RagiColor.WHITE))

    val TUNGSTEN_STEEL = AlloyBuilder(315, "tungsten_steel", mapOf(TUNGSTEN to 1, STEEL to 1))

    val IRIDIUM_ALLOY = AlloyBuilder(316, "iridium_alloy", mapOf(IRIDIUM to 4, ADVANCED_ALLOY to 4, CARBON to 1))
        .setColor(RagiColor.WHITE)
    */

    fun addExtra() {
        //追加の登録
        mapName["aluminum"] = ALUMINIUM
        mapName["chrome"] = CHROMIUM
        //configからmaterialを追加
        RagiConfig.registerMaterial()
    }
}