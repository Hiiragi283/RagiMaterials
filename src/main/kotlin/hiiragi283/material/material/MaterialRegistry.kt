package hiiragi283.material.material

import hiiragi283.material.material.element.ElementRegistry
import hiiragi283.material.util.ColorUtil
import hiiragi283.material.util.RagiColor

object MaterialRegistry {

    fun load() {}

    //    Registry    //

    private val mapMaterial: LinkedHashMap<String, RagiMaterial> = linkedMapOf()

    fun getMaterial(name: String): RagiMaterial = mapMaterial.getOrDefault(name, RagiMaterial.EMPTY)

    fun getMaterialAll(): Collection<RagiMaterial> = mapMaterial.values

    fun setMaterial(material: RagiMaterial): RagiMaterial? = mapMaterial.putIfAbsent(material.name, material)

    //    Materials - Bases    //

    val HYDROXIDE = RagiMaterial.Builder("hydroxide")
        .setComponents(listOf(ElementRegistry.OXYGEN to 1, ElementRegistry.HYDROGEN to 1))
        .build()

    val CARBONATE = RagiMaterial.Builder("carbonate")
        .setComponents(listOf(ElementRegistry.CARBON to 1, ElementRegistry.OXYGEN to 3))
        .build()

    val NITRATE = RagiMaterial.Builder("nitrate")
        .setComponents(listOf(ElementRegistry.NITROGEN to 1, ElementRegistry.OXYGEN to 3))
        .build()

    val PHOSPHATE = RagiMaterial.Builder("phosphate")
        .setComponents(listOf(ElementRegistry.PHOSPHORUS to 1, ElementRegistry.OXYGEN to 4))
        .build()

    val SULFATE = RagiMaterial.Builder("sulfate")
        .setComponents(listOf(ElementRegistry.SULFUR to 1, ElementRegistry.OXYGEN to 4))
        .build()

    //    Components    //

    private val listAlSiO =
        listOf(ElementRegistry.ALUMINIUM to 1, ElementRegistry.SILICON to 1, ElementRegistry.OXYGEN to 1)
    private val listBone = listOf(ElementRegistry.CALCIUM to 5, PHOSPHATE.setBracket() to 3, HYDROXIDE to 1)
    private val listCnHm =
        listOf(ElementRegistry.CARBON to 1, ElementRegistry.HYDROGEN to 1, ElementRegistry.OXYGEN to 1)
    private val listSiO2 = listOf(ElementRegistry.SILICON to 1, ElementRegistry.OXYGEN to 2)

    //    Materials - Vanilla    //

    val STONE = RagiMaterial.Builder("stone")
        .setComponents(listSiO2)
        .setMixture()
        .setColor(RagiColor.GRAY)
        .buildAndRegister()

    val GRANITE = RagiMaterial.Builder("granite")
        .setComponents(listSiO2)
        .setMixture()
        .setColor(ColorUtil.mixColor(RagiColor.BLUE to 1, RagiColor.GREEN to 1, RagiColor.RED to 2))
        .buildAndRegister()

    val DIORITE = RagiMaterial.Builder("diorite")
        .setComponents(listSiO2)
        .setMixture()
        .setColor(ColorUtil.mixColor(RagiColor.GRAY, RagiColor.WHITE))
        .buildAndRegister()

    val ANDESITE = RagiMaterial.Builder("andesite")
        .setComponents(listSiO2)
        .setMixture()
        .setColor(RagiColor.GRAY)
        .buildAndRegister()

    val DIRT = RagiMaterial.Builder("dirt")
        .setColor(ColorUtil.mixColor(RagiColor.BLACK to 2, RagiColor.GOLD to 1, RagiColor.DARK_RED to 1))
        .buildAndRegister()

    val WOOD = RagiMaterial.Builder("wood")
        .setComponents(listCnHm)
        .setMixture()
        .setColor(ColorUtil.mixColor(RagiColor.DARK_GRAY to 2, RagiColor.RED to 1, RagiColor.YELLOW to 1))
        .buildAndRegister()

    val BEDROCK = RagiMaterial.Builder("bedrock")
        .setComponents(listSiO2)
        .setMixture()
        .setColor(RagiColor.DARK_GRAY)
        .buildAndRegister()

    val SAND = RagiMaterial.Builder("sand")
        .setComponents(listSiO2)
        .setMixture()
        .setColor(ColorUtil.mixColor(RagiColor.GOLD to 1, RagiColor.WHITE to 2))
        .buildAndRegister()

    val GOLD = RagiMaterial.Builder("gold")
        .setSimple(ElementRegistry.GOLD, 1)
        .buildAndRegister()

    val IRON = RagiMaterial.Builder("iron")
        .setSimple(ElementRegistry.IRON, 1)
        .buildAndRegister()

    val COAL = RagiMaterial.Builder("coal")
        .setSimple(ElementRegistry.CARBON, 1)
        .setBurnTime(200 * 8)
        .setCrystalType(EnumCrystalType.COAL)
        .buildAndRegister()

    val GLASS = RagiMaterial.Builder("glass")
        .setComponents(listSiO2)
        .setMixture()
        .setColor(RagiColor.WHITE)
        .buildAndRegister()

    val LAPIS = RagiMaterial.Builder("lapis")
        .setCrystalType(EnumCrystalType.LAPIS)
        .setColor(RagiColor.BLUE)
        .buildAndRegister()

    val OBSIDIAN = RagiMaterial.Builder("obsidian")
        .setComponents(listSiO2)
        .setMixture()
        .setColor(ColorUtil.mixColor(RagiColor.DARK_BLUE, RagiColor.DARK_RED))
        .buildAndRegister()

    val DIAMOND = RagiMaterial.Builder("diamond")
        .setSimple(ElementRegistry.CARBON, 1)
        .setCrystalType(EnumCrystalType.DIAMOND)
        .setColor(RagiColor.AQUA)
        .buildAndRegister()

    val REDSTONE = RagiMaterial.Builder("redstone")
        .setSimple(ElementRegistry.REDSTONE, 1)
        .setCrystalType(EnumCrystalType.EMERALD)
        .buildAndRegister()

    val CLAY = RagiMaterial.Builder("clay")
        .setComponents(listAlSiO)
        .setMixture()
        .buildAndRegister()

    val NETHERRACK = RagiMaterial.Builder("netherrack")
        .setComponents(listSiO2)
        .setMixture()
        .setColor(ColorUtil.mixColor(RagiColor.BLACK, RagiColor.DARK_RED))
        .buildAndRegister()

    val SOUL_SAND = RagiMaterial.Builder("soul_sand")
        .setComponents(listSiO2)
        .setMixture()
        .setColor(ColorUtil.mixColor(RagiColor.BLACK to 5, RagiColor.GOLD to 1))
        .buildAndRegister()

    val GLOWSTONE = RagiMaterial.Builder("glowstone")
        .setSimple(ElementRegistry.GLOWSTONE, 1)
        .setCrystalType(EnumCrystalType.EMERALD)
        .buildAndRegister()

    val END_STONE = RagiMaterial.Builder("end_stone")
        .setComponents(listSiO2)
        .setMixture()
        .setColor(ColorUtil.mixColor(RagiColor.YELLOW to 1, RagiColor.WHITE to 3))
        .buildAndRegister()

    val EMERALD = RagiMaterial.Builder("emerald")
        .setComponents(
            listOf(
                ElementRegistry.BERYLLIUM to 3,
                ElementRegistry.ALUMINIUM to 2,
                ElementRegistry.SILICON to 6,
                ElementRegistry.OXYGEN to 18
            )
        )
        .setColor(RagiColor.GREEN)
        .setCrystalType(EnumCrystalType.EMERALD)
        .buildAndRegister()

    val QUARTZ = RagiMaterial.Builder("quartz")
        .setComponents(listSiO2)
        .setColor(RagiColor.WHITE)
        .setCrystalType(EnumCrystalType.QUARTZ)
        .buildAndRegister()

    val CHARCOAL = RagiMaterial.Builder("charcoal")
        .setSimple(ElementRegistry.CARBON, 1)
        .setBurnTime(200 * 8)
        .setColor(ColorUtil.mixColor(RagiColor.BLACK to 7, RagiColor.GOLD to 1))
        .setCrystalType(EnumCrystalType.COAL)
        .buildAndRegister()

    val GUNPOWDER = RagiMaterial.Builder("gunpowder")
        .setComponents(listOf(ElementRegistry.CARBON to 2, ElementRegistry.SULFUR to 1))
        .setColor(RagiColor.GRAY)
        .buildAndRegister()

    val BRICK = RagiMaterial.Builder("brick")
        .setComponents(listAlSiO)
        .setMixture()
        .setColor(
            ColorUtil.mixColor(
                RagiColor.BLACK to 1,
                RagiColor.DARK_RED to 2,
                RagiColor.GOLD to 1,
                RagiColor.WHITE to 1
            )
        )
        .buildAndRegister()

    val SUGAR = RagiMaterial.Builder("sugar")
        .setComponents(listCnHm)
        .setMixture()
        .setColor(RagiColor.WHITE)
        .buildAndRegister()

    val BONE = RagiMaterial.Builder("bone")
        .setComponents(listBone)
        .setColor(RagiColor.WHITE)
        .buildAndRegister()

    val ENDER = RagiMaterial.Builder("ender_pearl")
        .setSimple(ElementRegistry.ENDER, 1)
        .setCrystalType(EnumCrystalType.EMERALD)
        .buildAndRegister()

    val BLAZE = RagiMaterial.Builder("blaze")
        .setComponents(listOf(ElementRegistry.CARBON to 1, ElementRegistry.SULFUR to 1))
        .setColor(ColorUtil.mixColor(RagiColor.DARK_RED to 1, RagiColor.GOLD to 2))
        .buildAndRegister()

    //    Materials - Common Metal    //

    val COPPER = RagiMaterial.Builder("copper")
        .setSimple(ElementRegistry.COPPER, 1)
        .buildAndRegister()

    val TIN = RagiMaterial.Builder("tin")
        .setSimple(ElementRegistry.TIN, 1)
        .buildAndRegister()

    val SILVER = RagiMaterial.Builder("silver")
        .setSimple(ElementRegistry.SILVER, 1)
        .buildAndRegister()

    val LEAD = RagiMaterial.Builder("lead")
        .setSimple(ElementRegistry.LEAD, 1)
        .buildAndRegister()

    val ZINC = RagiMaterial.Builder("zinc")
        .setSimple(ElementRegistry.ZINC, 1)
        .buildAndRegister()

    val ALUMINIUM = RagiMaterial.Builder("aluminium")
        .setSimple(ElementRegistry.ALUMINIUM, 1)
        .buildAndRegister()

    val NICKEL = RagiMaterial.Builder("nickel")
        .setSimple(ElementRegistry.NICKEL, 1)
        .buildAndRegister()

    val PLATINUM = RagiMaterial.Builder("platinum")
        .setSimple(ElementRegistry.PLATINUM, 1)
        .buildAndRegister()

    val IRIDIUM = RagiMaterial.Builder("iridium")
        .setSimple(ElementRegistry.IRIDIUM, 1)
        .buildAndRegister()

    val OSMIUM = RagiMaterial.Builder("osmium")
        .setSimple(ElementRegistry.OSMIUM, 1)
        .buildAndRegister()

    val MITHRIL = RagiMaterial.Builder("mithril")
        .setSimple(ElementRegistry.MITHRIL, 1)
        .buildAndRegister()

    //    Materials - Alloy Metal    //

    val STEEL = RagiMaterial.Builder("steel")
        .setComponents(listOf(ElementRegistry.IRON to 1, ElementRegistry.CARBON to 1))
        .setMixture()
        .setColor(RagiColor.GRAY)
        .buildAndRegister()

    val ELECTRUM = RagiMaterial.Builder("electrum")
        .setComponents(listOf(ElementRegistry.SILVER to 1, ElementRegistry.GOLD to 1))
        .setColor(ColorUtil.mixColor(RagiColor.GOLD, RagiColor.YELLOW, RagiColor.WHITE))
        .buildAndRegister()

    val INVAR = RagiMaterial.Builder("invar")
        .setComponents(listOf(ElementRegistry.IRON to 2, ElementRegistry.NICKEL to 1))
        .setColor(ColorUtil.mixColor(RagiColor.GRAY to 1, RagiColor.GREEN to 1, RagiColor.WHITE to 2))
        .buildAndRegister()

    val BRONZE = RagiMaterial.Builder("bronze")
        .setComponents(listOf(ElementRegistry.COPPER to 3, ElementRegistry.TIN to 1))
        .buildAndRegister()

    val BRASS = RagiMaterial.Builder("brass")
        .setComponents(listOf(ElementRegistry.COPPER to 3, ElementRegistry.ZINC to 1))
        .setColor(RagiColor.GOLD)
        .buildAndRegister()

    val CONSTANTAN = RagiMaterial.Builder("constantan")
        .setComponents(listOf(ElementRegistry.NICKEL to 1, ElementRegistry.COPPER to 1))
        .buildAndRegister()

}