package hiiragi283.ragi_materials.material

import hiiragi283.ragi_materials.config.RagiConfig
import hiiragi283.ragi_materials.integration.IntegrationCore
import hiiragi283.ragi_materials.material.element.ElementRegistry
import hiiragi283.ragi_materials.material.type.TypeRegistry
import hiiragi283.ragi_materials.render.color.RagiColor
import net.minecraftforge.fluids.FluidRegistry

object MaterialRegistry {

    val mapIndex: HashMap<Int, MaterialBuilder> = hashMapOf()
    val mapName: HashMap<String, MaterialBuilder> = hashMapOf()

    //1 ~ 94: Periodic Table
    //1st Period
    val HYDROGEN = SimpleBuilder(ElementRegistry.HYDROGEN, 2)

    val HELIUM = SimpleBuilder(ElementRegistry.HELIUM, 1)

    //2nd Period
    val LITHIUM = SimpleBuilder(ElementRegistry.LITHIUM, 1)

    val BERYLLIUM = SimpleBuilder(ElementRegistry.BERYLLIUM, 1)

    val BORON = SimpleBuilder(ElementRegistry.BORON, 1)

    val CARBON = SimpleBuilder(ElementRegistry.CARBON, 1)

    val NITROGEN = SimpleBuilder(ElementRegistry.NITROGEN, 2)

    val OXYGEN = SimpleBuilder(ElementRegistry.OXYGEN, 2)

    val FLUORINE = SimpleBuilder(ElementRegistry.FLUORINE, 2)

    val NEON = SimpleBuilder(ElementRegistry.NEON, 1)

    //3rd Period
    val SODIUM = SimpleBuilder(ElementRegistry.SODIUM, 1)

    val MAGNESIUM = SimpleBuilder(ElementRegistry.MAGNESIUM, 1)

    val ALUMINIUM = SimpleBuilder(ElementRegistry.ALUMINIUM, 1).setOreDictAlias("Aluminum")

    val SILICON = SimpleBuilder(ElementRegistry.SILICON, 1)

    val PHOSPHORUS = SimpleBuilder(ElementRegistry.PHOSPHORUS, 1)

    val SULFUR = SimpleBuilder(ElementRegistry.SULFUR, 8)

    val CHLORINE = SimpleBuilder(ElementRegistry.CHLORINE, 2)

    val ARGON = SimpleBuilder(ElementRegistry.ARGON, 1)

    //4th Period
    val POTASSIUM = SimpleBuilder(ElementRegistry.POTASSIUM, 1)

    val CALCIUM = SimpleBuilder(ElementRegistry.CALCIUM, 1)

    val TITANIUM = SimpleBuilder(ElementRegistry.TITANIUM, 1)

    val CHROMIUM = SimpleBuilder(ElementRegistry.CHROMIUM, 1).setOreDictAlias("Chrome")

    val MANGANESE = SimpleBuilder(ElementRegistry.MANGANESE, 1)

    val IRON = SimpleBuilder(ElementRegistry.IRON, 1)

    val COBALT = SimpleBuilder(ElementRegistry.COBALT, 1)

    val NICKEL = SimpleBuilder(ElementRegistry.NICKEL, 1)

    val COPPER = SimpleBuilder(ElementRegistry.COPPER, 1)

    val ZINC = SimpleBuilder(ElementRegistry.ZINC, 1)

    val GALLIUM = SimpleBuilder(ElementRegistry.GALLIUM, 1)

    val ARSENIC = SimpleBuilder(ElementRegistry.ARSENIC, 1)

    //5th Period
    val STRONTIUM = SimpleBuilder(ElementRegistry.STRONTIUM, 1)

    val ZIRCONIUM = SimpleBuilder(ElementRegistry.ZIRCONIUM, 1)

    val NIOBIUM = SimpleBuilder(ElementRegistry.NIOBIUM, 1)

    val MOLYBDENUM = SimpleBuilder(ElementRegistry.MOLYBDENUM, 1)

    val RUTHENIUM = SimpleBuilder(ElementRegistry.RUTHENIUM, 1)

    val RHODIUM = SimpleBuilder(ElementRegistry.RHODIUM, 1)

    val PALLADIUM = SimpleBuilder(ElementRegistry.PALLADIUM, 1)

    val SILVER = SimpleBuilder(ElementRegistry.SILVER, 1)

    val INDIUM = SimpleBuilder(ElementRegistry.INDIUM, 1)

    val TIN = SimpleBuilder(ElementRegistry.TIN, 1)

    val ANTIMONY = SimpleBuilder(ElementRegistry.ANTIMONY, 1)

    val IODINE = SimpleBuilder(ElementRegistry.IODINE, 2)

    //6th Period
    val BARIUM = SimpleBuilder(ElementRegistry.BARIUM, 1)

    val NEODYMIUM = SimpleBuilder(ElementRegistry.NEODYMIUM, 1)

    val SAMARIUM = SimpleBuilder(ElementRegistry.SAMARIUM, 1)

    val HAFNIUM = SimpleBuilder(ElementRegistry.HAFNIUM, 1)

    val TANTALUM = SimpleBuilder(ElementRegistry.TANTALUM, 1)

    val TUNGSTEN = SimpleBuilder(ElementRegistry.TUNGSTEN, 1)

    val OSMIUM = SimpleBuilder(ElementRegistry.OSMIUM, 1)

    val IRIDIUM = SimpleBuilder(ElementRegistry.IRIDIUM, 1)

    val PLATINUM = SimpleBuilder(ElementRegistry.PLATINUM, 1)

    val GOLD = SimpleBuilder(ElementRegistry.GOLD, 1)

    val MERCURY = SimpleBuilder(ElementRegistry.MERCURY, 1)

    val LEAD = SimpleBuilder(ElementRegistry.LEAD, 1)

    val BISMUTH = SimpleBuilder(ElementRegistry.BISMUTH, 1)

    //7th Period
    val THORIUM = SimpleBuilder(ElementRegistry.THORIUM, 1).setDecayed(LEAD)

    val URANIUM_238 = SimpleBuilder(ElementRegistry.URANIUM_238, 1).setDecayed(LEAD)

    val PLUTONIUM_244 = SimpleBuilder(ElementRegistry.PLUTONIUM_244, 1).setDecayed(LEAD)

    //95 ~ 99: Isotope
    val DEUTERIUM = SimpleBuilder(ElementRegistry.DEUTERIUM, 2)

    val TRITIUM = SimpleBuilder(ElementRegistry.TRITIUM, 2)

    val URANIUM_235 = SimpleBuilder(ElementRegistry.URANIUM_235, 1).setDecayed(LEAD)

    val PLUTONIUM_239 = SimpleBuilder(ElementRegistry.PLUTONIUM_239, 1).setDecayed(URANIUM_235)

    //100 ~ 199: Fundamental Compounds
    val HYDROXIDE = CompoundBuilder(100, "hydroxide", TypeRegistry.INTERNAL, mapOf(ElementRegistry.OXYGEN to 1, ElementRegistry.HYDROGEN to 1))

    val BORATE = CompoundBuilder(101, "borate", TypeRegistry.INTERNAL, mapOf(ElementRegistry.BORON to 1, ElementRegistry.OXYGEN to 3))

    val CARBONATE = CompoundBuilder(102, "carbonate", TypeRegistry.INTERNAL, mapOf(ElementRegistry.CARBON to 1, ElementRegistry.OXYGEN to 3))

    val NITRATE = CompoundBuilder(103, "nitrate", TypeRegistry.INTERNAL, mapOf(ElementRegistry.NITROGEN to 1, ElementRegistry.OXYGEN to 3))

    val SILICATE = CompoundBuilder(104, "silicate", TypeRegistry.INTERNAL, mapOf(ElementRegistry.SILICON to 1, ElementRegistry.OXYGEN to 3))

    val PHOSPHATE = CompoundBuilder(105, "phosphate", TypeRegistry.INTERNAL, mapOf(ElementRegistry.PHOSPHORUS to 1, ElementRegistry.OXYGEN to 4))

    val SULFATE = CompoundBuilder(106, "sulfate", TypeRegistry.INTERNAL, mapOf(ElementRegistry.SULFUR to 1, ElementRegistry.OXYGEN to 4))

    val QUARTZ = CrystalBuilder(219, "quartz", mapOf(ElementRegistry.SILICON to 1, ElementRegistry.OXYGEN to 2), "trigonal")
        .setColor(RagiColor.WHITE)

    //200 ~ 299: Vanilla
    val STONE = CompoundBuilder(200, "stone", TypeRegistry.STONE, mapOf(QUARTZ to 1))
        .setColor(RagiColor.GRAY)

    val WOOD = MixtureBuilder(201, "wood", TypeRegistry.WOOD, listOf(ElementRegistry.CARBON, ElementRegistry.HYDROGEN, ElementRegistry.OXYGEN))
        .setColor(RagiColor.mixColor(mapOf(RagiColor.DARK_GRAY to 2, RagiColor.RED to 1, RagiColor.YELLOW to 1))).setBurnTime(200 * 1)

    val WATER = CompoundBuilder(202, "water", TypeRegistry.INTERNAL, mapOf(ElementRegistry.HYDROGEN to 2, ElementRegistry.OXYGEN to 1))
        .setColor(RagiColor.BLUE)
        .setTempMelt(0).setTempBoil(100)

    val LAVA = CompoundBuilder(203, "lava", TypeRegistry.INTERNAL, mapOf(QUARTZ to 1))
        .setColor(RagiColor.mixColor(RagiColor.DARK_RED, RagiColor.GOLD))
        .setTempMelt(FluidRegistry.LAVA.temperature)

    val GLASS = CompoundBuilder(204, "glass", TypeRegistry.DUST, mapOf(QUARTZ to 1))
        .setColor(RagiColor.WHITE)

    val LAPIS = MixtureBuilder(205, "lapis", TypeRegistry.DUST, listOf("?"))
        .setColor(RagiColor.BLUE)

    val CLAY = MixtureBuilder(206, "clay", TypeRegistry.DUST, listOf("?"))
        .setColor(
            RagiColor.mixColor(
                mapOf(
                    RagiColor.BLUE to 1,
                    RagiColor.AQUA to 1,
                    RagiColor.WHITE to 2
                )
            )
        )

    val BRICK = CompoundBuilder(207, "brick", TypeRegistry.INGOT, mapOf(CLAY to 1))
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

    val OBSIDIAN = CompoundBuilder(208, "obsidian", TypeRegistry.DUST, mapOf(QUARTZ to 1))
        .setColor(
            RagiColor.mixColor(
                mapOf(
                    RagiColor.BLACK to 2,
                    RagiColor.BLUE to 1,
                    RagiColor.RED to 1
                )
            )
        )

    val REDSTONE = SimpleBuilder(ElementRegistry.REDSTONE, 1)

    val DIAMOND = CrystalBuilder(210, "diamond", mapOf(ElementRegistry.CARBON to 1), "diamond")
        .setColor(ElementRegistry.ALUMINIUM.color!!)

    val SNOW = CompoundBuilder(211, "snow", TypeRegistry.INGOT, mapOf(WATER to 1))
        .setColor(RagiColor.WHITE)

    val ICE = CompoundBuilder(212, "ice", TypeRegistry.INGOT, mapOf(WATER to 1))
        .setColor(ElementRegistry.ALUMINIUM.color!!)

    val NETHERRACK = CompoundBuilder(
        213,
        "netherrack",
        TypeRegistry.DUST,
        mapOf(STONE.addBracket() to 1, ElementRegistry.SULFUR to 1, ElementRegistry.PHOSPHORUS to 1)
    )
        .setColor(RagiColor.DARK_RED)

    val SOUL_SAND = MaterialBuilder(214, "soul_sand", TypeRegistry.DUST)

    val GLOWSTONE = SimpleBuilder(ElementRegistry.GLOWSTONE, 1)

    val NETHER_BRICK = CompoundBuilder(216, "nether_brick", TypeRegistry.INGOT, mapOf(NETHERRACK to 1))
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
        CompoundBuilder(217, "end_stone", TypeRegistry.DUST, mapOf(STONE.addBracket() to 1, ElementRegistry.HELIUM to 1, ElementRegistry.TUNGSTEN to 1))
            .setColor(ElementRegistry.PALLADIUM.color!!)

    val EMERALD = CrystalBuilder(
        218, "emerald", mapOf(
            ElementRegistry.BERYLLIUM to 3,
            ElementRegistry.ALUMINIUM to 2,
            ElementRegistry.SILICON to 6,
            ElementRegistry.OXYGEN to 18
        ), "hexagonal"
    ).setColor(RagiColor.GREEN)

    val ENDER = SimpleBuilder(ElementRegistry.ENDER,1).setOreDictAlias("EnderPearl")

    val PRISMARINE = CrystalBuilder(220, "prismarine", mapOf(QUARTZ to 1, WATER.addBracket() to 1), "trigonal")
        .setColor(ElementRegistry.ALUMINIUM.color!!)

    val BONE = CompoundBuilder(221, "bone", TypeRegistry.DUST, mapOf(ElementRegistry.CALCIUM to 3, PHOSPHATE.addBracket() to 2))
        .setColor(RagiColor.WHITE)

    val NITER = CrystalBuilder(222, "niter", mapOf(ElementRegistry.POTASSIUM to 1, NITRATE to 1), "orthorhombic")
        .setColor(RagiColor.WHITE).setOreDictAlias("Saltpeter")

    val GUNPOWDER =
        CompoundBuilder(223, "gunpowder", TypeRegistry.DUST, mapOf(NITER.addBracket() to 2, ElementRegistry.CARBON to 1, ElementRegistry.SULFUR to 1))
            .setColor(RagiColor.DARK_GRAY)

    val SUGAR = CompoundBuilder(224, "sugar", TypeRegistry.DUST, mapOf(ElementRegistry.CARBON to 6, ElementRegistry.HYDROGEN to 12, ElementRegistry.OXYGEN to 6))
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

    //300 ~ 399: Alloy
    val INVAR = AlloyBuilder(300, "invar", mapOf(ElementRegistry.IRON to 2, ElementRegistry.NICKEL to 1))

    val MAGNET = AlloyBuilder(301, "magnet", mapOf(ElementRegistry.IRON to 1))
        .setColor(ElementRegistry.CARBON.color!!)

    val STAINLESS_STEEL = AlloyBuilder(302, "stainless_steel", mapOf(
        ElementRegistry.IRON to 6,
        ElementRegistry.CHROMIUM to 1,
        ElementRegistry.MANGANESE to 1,
        ElementRegistry.NICKEL to 1)
    )
        .setColor(RagiColor.mixColor(RagiColor.GRAY, RagiColor.WHITE))

    val STEEL = AlloyBuilder(303, "steel", mapOf(ElementRegistry.IRON to 1, ElementRegistry.CARBON to 1))

    val TOOL_STEEL = AlloyBuilder(304, "tool_steel", mapOf(
        ElementRegistry.IRON to 6,
        ElementRegistry.CHROMIUM to 1,
        ElementRegistry.MANGANESE to 1,
        ElementRegistry.MOLYBDENUM to 1
    ))

    val CONSTANTAN = AlloyBuilder(305, "constantan", mapOf(ElementRegistry.NICKEL to 1, ElementRegistry.COPPER to 1))

    val BRASS = AlloyBuilder(306, "brass", mapOf(ElementRegistry.COPPER to 3, ElementRegistry.ZINC to 1))
        .setColor(RagiColor.GOLD)

    val BRONZE = AlloyBuilder(307, "bronze", mapOf(ElementRegistry.COPPER to 3, ElementRegistry.TIN to 1))

    val ELECTRUM = AlloyBuilder(308, "electrum", mapOf(ElementRegistry.SILVER to 1, ElementRegistry.GOLD to 1))
        .setColor(RagiColor.mixColor(RagiColor.YELLOW, RagiColor.WHITE))

    val TUNGSTEN_STEEL = AlloyBuilder(309, "tungsten_steel", mapOf(ElementRegistry.TUNGSTEN to 1, STEEL to 1))

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

    */

    fun init() {

        //mapへの追加の登録
        mapName["aluminum"] = ALUMINIUM
        mapName["chrome"] = CHROMIUM
        mapName["saltpeter"] = NITER
        //既存のオブジェクトへの追加の登録

        //configからmaterialを追加
        RagiConfig.registerMaterial()

        //600 ~ :Integration Materials
        if (IntegrationCore.enableTF) {
            val MITHRIL = SimpleBuilder(ElementRegistry.MITHRIL, 1)

            val SIGNALUM = AlloyBuilder(601, "signalum", mapOf(ElementRegistry.COPPER to 3, ElementRegistry.SILVER to 1, ElementRegistry.REDSTONE to 10))
                .setColor(RagiColor.mixColor(ElementRegistry.COPPER.color!!, RagiColor.DARK_RED))

            val LUMIUM = AlloyBuilder(602, "lumium", mapOf(ElementRegistry.TIN to 3, ElementRegistry.SILVER to 1, ElementRegistry.GLOWSTONE to 4))

            val ENDERIUM = AlloyBuilder(603, "enderium", mapOf(ElementRegistry.LEAD to 3, ElementRegistry.PLATINUM to 1, ElementRegistry.ENDER to 4))
                .setColor(ElementRegistry.ENDER.color)
        }
    }
}