package hiiragi283.ragi_materials.material

object MaterialRegistryOlder {

    //1 ~ 94: Periodic Table
    //1st Period
    /*val HYDROGEN = SimpleBuilder(ElementRegistry.HYDROGEN, 2)

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

    val ALUMINIUM = SimpleBuilder(ElementRegistry.ALUMINIUM, 1).setOreDictAlias("Aluminum").setOre()

    val SILICON = SimpleBuilder(ElementRegistry.SILICON, 1)

    val PHOSPHORUS = SimpleBuilder(ElementRegistry.PHOSPHORUS, 1).setOre()

    val SULFUR = SimpleBuilder(ElementRegistry.SULFUR, 8).setOre()

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

    val COPPER = SimpleBuilder(ElementRegistry.COPPER, 1).setOre()

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

    val SILVER = SimpleBuilder(ElementRegistry.SILVER, 1).setOre()

    val INDIUM = SimpleBuilder(ElementRegistry.INDIUM, 1)

    val TIN = SimpleBuilder(ElementRegistry.TIN, 1).setOre()

    val ANTIMONY = SimpleBuilder(ElementRegistry.ANTIMONY, 1)

    val IODINE = SimpleBuilder(ElementRegistry.IODINE, 2)

    //6th Period
    val BARIUM = SimpleBuilder(ElementRegistry.BARIUM, 1)

    val NEODYMIUM = SimpleBuilder(ElementRegistry.NEODYMIUM, 1)

    val SAMARIUM = SimpleBuilder(ElementRegistry.SAMARIUM, 1)

    val HAFNIUM = SimpleBuilder(ElementRegistry.HAFNIUM, 1)

    val TANTALUM = SimpleBuilder(ElementRegistry.TANTALUM, 1)

    val TUNGSTEN = SimpleBuilder(ElementRegistry.TUNGSTEN, 1).setOre()

    val OSMIUM = SimpleBuilder(ElementRegistry.OSMIUM, 1)

    val IRIDIUM = SimpleBuilder(ElementRegistry.IRIDIUM, 1)

    val PLATINUM = SimpleBuilder(ElementRegistry.PLATINUM, 1).setOre()

    val GOLD = SimpleBuilder(ElementRegistry.GOLD, 1).setOre()

    val MERCURY = SimpleBuilder(ElementRegistry.MERCURY, 1)

    val LEAD = SimpleBuilder(ElementRegistry.LEAD, 1).setOre()

    val BISMUTH = SimpleBuilder(ElementRegistry.BISMUTH, 1)

    //7th Period
    val THORIUM = SimpleBuilder(ElementRegistry.THORIUM, 1).setDecayed(LEAD).setOre()

    val URANIUM_238 = SimpleBuilder(ElementRegistry.URANIUM_238, 1).setDecayed(LEAD).setOre()

    val PLUTONIUM_244 = SimpleBuilder(ElementRegistry.PLUTONIUM_244, 1).setDecayed(LEAD)

    //95 ~ 99: Isotope

    val URANIUM_235 = SimpleBuilder(ElementRegistry.URANIUM_235, 1).setDecayed(LEAD)

    val PLUTONIUM_239 = SimpleBuilder(ElementRegistry.PLUTONIUM_239, 1).setDecayed(URANIUM_235)

    //200 ~ 299: Vanilla
    val WOOD = MixtureBuilder(201, "wood", TypeRegistry.WOOD, listOf(ElementRegistry.CARBON, ElementRegistry.HYDROGEN, ElementRegistry.OXYGEN)).setColor(RagiColorManager.mixColor(mapOf(RagiColor.DARK_GRAY to 2, RagiColor.RED to 1, RagiColor.YELLOW to 1))).setBurnTime(200 * 1)

    val LAPIS = MixtureBuilder(205, "lapis", TypeRegistry.DUST, listOf("?")).setColor(RagiColor.BLUE).setOre()

    val CLAY = MixtureBuilder(206, "clay", TypeRegistry.DUST, listOf("?")).setColor(RagiColorManager.mixColor(mapOf(RagiColor.BLUE to 1, RagiColor.AQUA to 1, RagiColor.WHITE to 2)))

    val BRICK = CompoundBuilder(207, "brick", TypeRegistry.INGOT, mapOf(CLAY to 1)).setColor(RagiColorManager.mixColor(mapOf(RagiColor.DARK_RED to 4, RagiColor.DARK_PURPLE to 1, RagiColor.GOLD to 2, RagiColor.WHITE to 3)))

    val REDSTONE = SimpleBuilder(ElementRegistry.REDSTONE, 1).setOre()

    val GLOWSTONE = SimpleBuilder(ElementRegistry.GLOWSTONE, 1)

    val NETHER_BRICK = CompoundBuilder(216, "nether_brick", TypeRegistry.INGOT, mapOf(NETHERRACK to 1)).setColor(RagiColorManager.mixColor(mapOf(RagiColor.BLACK to 3, RagiColor.DARK_BLUE to 1, RagiColor.DARK_RED to 3)))

    val EMERALD = CrystalBuilder(218, "emerald", mapOf(ElementRegistry.BERYLLIUM to 3, ElementRegistry.ALUMINIUM to 2, ElementRegistry.SILICON to 6, ElementRegistry.OXYGEN to 18), "hexagonal").setColor(RagiColor.GREEN).setOre()

    val ENDER = SimpleBuilder(ElementRegistry.ENDER, 1).setOreDictAlias("EnderPearl")

    val PRISMARINE = CrystalBuilder(220, "prismarine", mapOf(QUARTZ to 1, WATER.addBracket() to 1), "trigonal").setColor(ElementRegistry.ALUMINIUM.color!!)

    val BONE = CompoundBuilder(221, "bone", TypeRegistry.DUST, mapOf(ElementRegistry.CALCIUM to 3, PHOSPHATE.addBracket() to 2)).setColor(RagiColor.WHITE).setOre()

    val GUNPOWDER = CompoundBuilder(223, "gunpowder", TypeRegistry.DUST, mapOf(NITER.addBracket() to 2, ElementRegistry.CARBON to 1, ElementRegistry.SULFUR to 1)).setColor(RagiColor.DARK_GRAY)

    val SUGAR = CompoundBuilder(224, "sugar", TypeRegistry.DUST, mapOf(ElementRegistry.CARBON to 6, ElementRegistry.HYDROGEN to 12, ElementRegistry.OXYGEN to 6)).setColor(RagiColor.WHITE).setBurnTime(200 * 1)

    //300 ~ 399: Alloy
    val INVAR = AlloyBuilder(300, "invar", mapOf(ElementRegistry.IRON to 2, ElementRegistry.NICKEL to 1))

    val MAGNET = AlloyBuilder(301, "magnet", mapOf(ElementRegistry.IRON to 1)).setColor(ElementRegistry.CARBON.color!!)

    val STAINLESS_STEEL = AlloyBuilder(302, "stainless_steel", mapOf(ElementRegistry.IRON to 6, ElementRegistry.CHROMIUM to 1, ElementRegistry.MANGANESE to 1, ElementRegistry.NICKEL to 1)).setColor(RagiColorManager.mixColor(RagiColor.GRAY, RagiColor.WHITE))

    val STEEL = AlloyBuilder(303, "steel", mapOf(ElementRegistry.IRON to 1, ElementRegistry.CARBON to 1))

    val TOOL_STEEL = AlloyBuilder(304, "tool_steel", mapOf(ElementRegistry.IRON to 6, ElementRegistry.CHROMIUM to 1, ElementRegistry.MANGANESE to 1, ElementRegistry.MOLYBDENUM to 1))

    val CONSTANTAN = AlloyBuilder(305, "constantan", mapOf(ElementRegistry.NICKEL to 1, ElementRegistry.COPPER to 1))

    val BRASS = AlloyBuilder(306, "brass", mapOf(ElementRegistry.COPPER to 3, ElementRegistry.ZINC to 1)).setColor(RagiColor.GOLD)

    val BRONZE = AlloyBuilder(307, "bronze", mapOf(ElementRegistry.COPPER to 3, ElementRegistry.TIN to 1))

    val ELECTRUM = AlloyBuilder(308, "electrum", mapOf(ElementRegistry.SILVER to 1, ElementRegistry.GOLD to 1)).setColor(RagiColorManager.mixColor(RagiColor.YELLOW, RagiColor.WHITE))

    val TUNGSTEN_STEEL = AlloyBuilder(309, "tungsten_steel", mapOf(ElementRegistry.TUNGSTEN to 1, STEEL to 1))


    //200 ~ 299: Gem
    val ALMANDINE = CrystalBuilder(200, "almandine", mapOf(IRON to 3, ALUMINIUM to 2, SILICON to 3, OXYGEN to 12), "cubic")
        .setColor(RagiColor.DARK_RED)

    //val AMBER = CrystalBuilder(201, "amber", mapOf("" to 1), "amorphous")

    val AMETHYST = CrystalBuilder(202, "amethyst", mapOf(SILICON to 1, OXYGEN to 2), "trigonal")
        .setColor(ZIRCONIUM.color)

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

    val MALACHITE = CrystalBuilder(210, "malachite", mapOf(
        COPPER to 2,
        "(CO\u2083)" to 1,
        "(OH)" to 2
    ), "monoclinic").setColor(RagiColor.DARK_GREEN)

    val OLIVINE = CrystalBuilder(211, "olivine", mapOf(MAGNESIUM to 2, SILICON to 1, OXYGEN to 4), "monoclinic")
        .setColor(RagiColorManager.mixColor(RagiColor.GREEN, RagiColor.WHITE))

    val OPAL = CrystalBuilder(212, "opal", mapOf(SILICON to 1, OXYGEN to 2), "amorphous")



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


    fun init() {

        //600 ~ :Integration MaterialRegistry
        if (IntegrationCore.enableTF) {
            val MITHRIL = SimpleBuilder(ElementRegistry.MITHRIL, 1)

            val SIGNALUM = AlloyBuilder(601, "signalum", mapOf(ElementRegistry.COPPER to 3, ElementRegistry.SILVER to 1, ElementRegistry.REDSTONE to 10)).setColor(RagiColorManager.mixColor(ElementRegistry.COPPER.color, RagiColor.DARK_RED))

            val LUMIUM = AlloyBuilder(602, "lumium", mapOf(ElementRegistry.TIN to 3, ElementRegistry.SILVER to 1, ElementRegistry.GLOWSTONE to 4))

            val ENDERIUM = AlloyBuilder(603, "enderium", mapOf(ElementRegistry.LEAD to 3, ElementRegistry.PLATINUM to 1, ElementRegistry.ENDER to 4)).setColor(ElementRegistry.ENDER.color)
        }

        //configからmaterialを追加
        //RagiConfig.registerMaterial()
    }*/
}