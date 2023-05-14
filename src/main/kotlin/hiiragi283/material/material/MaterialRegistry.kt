package hiiragi283.material.material

import hiiragi283.material.material.element.ElementRegistry
import hiiragi283.material.material.type.EnumCrystalType
import hiiragi283.material.material.type.TypeRegistry
import hiiragi283.material.util.ColorUtil
import hiiragi283.material.util.RagiColor
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary

object MaterialRegistry {

    fun load() {}

    //    Registry    //

    private val mapMaterial: LinkedHashMap<String, RagiMaterial> = linkedMapOf()
    private val mapOredictMaterial: HashMap<String, RagiMaterial> = hashMapOf()

    fun getMaterial(name: String): RagiMaterial = mapMaterial.getOrDefault(name, RagiMaterial.EMPTY)

    fun getMaterial(stack: ItemStack): RagiMaterial {
        var result = RagiMaterial.EMPTY
        for (id in OreDictionary.getOreIDs(stack)) {
            val material = getMaterialFromOre(OreDictionary.getOreName(id))
            if (!material.isEmpty()) {
                result = material
                break
            }
        }
        return result
    }

    fun getMaterialFromOre(oredict: String): RagiMaterial = mapOredictMaterial.getOrDefault(oredict, RagiMaterial.EMPTY)

    fun getMaterialAll(): Collection<RagiMaterial> = mapMaterial.values

    fun setMaterial(material: RagiMaterial): RagiMaterial? = mapMaterial.putIfAbsent(material.name, material)

    fun setMaterialWithOre(oredict: String, material: RagiMaterial): RagiMaterial? =
        mapOredictMaterial.putIfAbsent(oredict, material)

    //    Materials - Bases    //

    val HYDROXIDE = RagiMaterial.Builder(-1, "hydroxide", TypeRegistry.INTERNAL)
        .setComponents(listOf(ElementRegistry.OXYGEN to 1, ElementRegistry.HYDROGEN to 1))
        .build()

    val CARBONATE = RagiMaterial.Builder(-1, "carbonate", TypeRegistry.INTERNAL)
        .setComponents(listOf(ElementRegistry.CARBON to 1, ElementRegistry.OXYGEN to 3))
        .build()

    val NITRATE = RagiMaterial.Builder(-1, "nitrate", TypeRegistry.INTERNAL)
        .setComponents(listOf(ElementRegistry.NITROGEN to 1, ElementRegistry.OXYGEN to 3))
        .build()

    val PHOSPHATE = RagiMaterial.Builder(-1, "phosphate", TypeRegistry.INTERNAL)
        .setComponents(listOf(ElementRegistry.PHOSPHORUS to 1, ElementRegistry.OXYGEN to 4))
        .build()

    val SULFATE = RagiMaterial.Builder(-1, "sulfate", TypeRegistry.INTERNAL)
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

    val STONE = RagiMaterial.Builder(0, "stone", TypeRegistry.STONE)
        .setComponents(listSiO2)
        .setMixture()
        .setColor(RagiColor.GRAY)
        .buildAndRegister()

    val GRANITE = RagiMaterial.Builder(1, "granite", TypeRegistry.DUST)
        .setComponents(listSiO2)
        .setMixture()
        .setColor(ColorUtil.mixColor(RagiColor.BLUE to 1, RagiColor.GREEN to 1, RagiColor.RED to 2))
        .buildAndRegister()

    val DIORITE = RagiMaterial.Builder(2, "diorite", TypeRegistry.DUST)
        .setComponents(listSiO2)
        .setMixture()
        .setColor(ColorUtil.mixColor(RagiColor.GRAY, RagiColor.WHITE))
        .buildAndRegister()

    val ANDESITE = RagiMaterial.Builder(3, "andesite", TypeRegistry.DUST)
        .setComponents(listSiO2)
        .setMixture()
        .setColor(RagiColor.GRAY)
        .buildAndRegister()

    val DIRT = RagiMaterial.Builder(4, "dirt", TypeRegistry.INTERNAL)
        .setColor(ColorUtil.mixColor(RagiColor.BLACK to 2, RagiColor.GOLD to 1, RagiColor.DARK_RED to 1))
        .buildAndRegister()

    val WOOD = RagiMaterial.Builder(5, "wood", TypeRegistry.WOOD)
        .setComponents(listCnHm)
        .setMixture()
        .setColor(ColorUtil.mixColor(RagiColor.DARK_GRAY to 2, RagiColor.RED to 1, RagiColor.YELLOW to 1))
        .buildAndRegister()

    val BEDROCK = RagiMaterial.Builder(6, "bedrock", TypeRegistry.INGOT)
        .setComponents(listSiO2)
        .setMixture()
        .setColor(RagiColor.DARK_GRAY)
        .buildAndRegister()

    val SAND = RagiMaterial.Builder(7, "sand", TypeRegistry.DUST)
        .setComponents(listSiO2)
        .setMixture()
        .setColor(ColorUtil.mixColor(RagiColor.GOLD to 1, RagiColor.WHITE to 2))
        .buildAndRegister()

    val GOLD = RagiMaterial.Builder(8, "gold", TypeRegistry.METAL)
        .setSimple(ElementRegistry.GOLD, 1)
        .buildAndRegister()

    val IRON = RagiMaterial.Builder(9, "iron", TypeRegistry.METAL)
        .setSimple(ElementRegistry.IRON, 1)
        .buildAndRegister()

    val COAL = RagiMaterial.Builder(10, "coal", TypeRegistry.FUEL)
        .setSimple(ElementRegistry.CARBON, 1)
        .setBurnTime(200 * 8)
        .setCrystalType(EnumCrystalType.COAL)
        .buildAndRegister()

    val GLASS = RagiMaterial.Builder(11, "glass", TypeRegistry.DUST)
        .setComponents(listSiO2)
        .setMixture()
        .setColor(RagiColor.WHITE)
        .buildAndRegister()

    val LAPIS = RagiMaterial.Builder(12, "lapis", TypeRegistry.CRYSTAL)
        .setCrystalType(EnumCrystalType.LAPIS)
        .setColor(RagiColor.BLUE)
        .buildAndRegister()

    val OBSIDIAN = RagiMaterial.Builder(13, "obsidian", TypeRegistry.DUST)
        .setComponents(listSiO2)
        .setMixture()
        .setColor(ColorUtil.mixColor(RagiColor.DARK_BLUE, RagiColor.DARK_RED))
        .buildAndRegister()

    val DIAMOND = RagiMaterial.Builder(14, "diamond", TypeRegistry.CRYSTAL)
        .setSimple(ElementRegistry.CARBON, 1)
        .setCrystalType(EnumCrystalType.DIAMOND)
        .setColor(RagiColor.AQUA)
        .buildAndRegister()

    val REDSTONE = RagiMaterial.Builder(15, "redstone", TypeRegistry.CRYSTAL)
        .setSimple(ElementRegistry.REDSTONE, 1)
        .setCrystalType(EnumCrystalType.EMERALD)
        .buildAndRegister()

    val CLAY = RagiMaterial.Builder(16, "clay", TypeRegistry.DUST)
        .setComponents(listAlSiO)
        .setMixture()
        .buildAndRegister()

    val NETHERRACK = RagiMaterial.Builder(17, "netherrack", TypeRegistry.DUST)
        .setComponents(listSiO2)
        .setMixture()
        .setColor(ColorUtil.mixColor(RagiColor.BLACK, RagiColor.DARK_RED))
        .buildAndRegister()

    val SOUL_SAND = RagiMaterial.Builder(18, "soul_sand", TypeRegistry.DUST)
        .setComponents(listSiO2)
        .setMixture()
        .setColor(ColorUtil.mixColor(RagiColor.BLACK to 5, RagiColor.GOLD to 1))
        .buildAndRegister()

    val GLOWSTONE = RagiMaterial.Builder(19, "glowstone", TypeRegistry.CRYSTAL)
        .setSimple(ElementRegistry.GLOWSTONE, 1)
        .setCrystalType(EnumCrystalType.EMERALD)
        .buildAndRegister()

    val END_STONE = RagiMaterial.Builder(20, "end_stone", TypeRegistry.DUST)
        .setComponents(listSiO2)
        .setMixture()
        .setColor(ColorUtil.mixColor(RagiColor.YELLOW to 1, RagiColor.WHITE to 3))
        .buildAndRegister()

    val EMERALD = RagiMaterial.Builder(21, "emerald", TypeRegistry.CRYSTAL)
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

    val QUARTZ = RagiMaterial.Builder(22, "quartz", TypeRegistry.CRYSTAL)
        .setComponents(listSiO2)
        .setColor(RagiColor.WHITE)
        .setCrystalType(EnumCrystalType.QUARTZ)
        .buildAndRegister()

    val CHARCOAL = RagiMaterial.Builder(23, "charcoal", TypeRegistry.FUEL)
        .setSimple(ElementRegistry.CARBON, 1)
        .setBurnTime(200 * 8)
        .setColor(ColorUtil.mixColor(RagiColor.BLACK to 7, RagiColor.GOLD to 1))
        .setCrystalType(EnumCrystalType.COAL)
        .buildAndRegister()

    val GUNPOWDER = RagiMaterial.Builder(24, "gunpowder", TypeRegistry.DUST)
        .setComponents(listOf(ElementRegistry.CARBON to 2, ElementRegistry.SULFUR to 1))
        .setColor(RagiColor.GRAY)
        .buildAndRegister()

    val BRICK = RagiMaterial.Builder(25, "brick", TypeRegistry.INGOT)
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

    val SUGAR = RagiMaterial.Builder(26, "sugar", TypeRegistry.DUST)
        .setComponents(listCnHm)
        .setMixture()
        .setColor(RagiColor.WHITE)
        .buildAndRegister()

    val BONE = RagiMaterial.Builder(27, "bone", TypeRegistry.DUST)
        .setComponents(listBone)
        .setColor(RagiColor.WHITE)
        .buildAndRegister()

    val ENDER = RagiMaterial.Builder(28, "ender_pearl", TypeRegistry.CRYSTAL)
        .setSimple(ElementRegistry.ENDER, 1)
        .setCrystalType(EnumCrystalType.EMERALD)
        .buildAndRegister()

    val BLAZE = RagiMaterial.Builder(29, "blaze", TypeRegistry.DUST)
        .setComponents(listOf(ElementRegistry.CARBON to 1, ElementRegistry.SULFUR to 1))
        .setColor(ColorUtil.mixColor(RagiColor.DARK_RED to 1, RagiColor.GOLD to 2))
        .buildAndRegister()

    //    Materials - Common Metal    //

    val COPPER = RagiMaterial.Builder(32, "copper", TypeRegistry.METAL)
        .setSimple(ElementRegistry.COPPER, 1)
        .buildAndRegister()

    val TIN = RagiMaterial.Builder(33, "tin", TypeRegistry.METAL)
        .setSimple(ElementRegistry.TIN, 1)
        .buildAndRegister()

    val SILVER = RagiMaterial.Builder(34, "silver", TypeRegistry.METAL)
        .setSimple(ElementRegistry.SILVER, 1)
        .buildAndRegister()

    val LEAD = RagiMaterial.Builder(35, "lead", TypeRegistry.METAL)
        .setSimple(ElementRegistry.LEAD, 1)
        .buildAndRegister()

    val ZINC = RagiMaterial.Builder(36, "zinc", TypeRegistry.METAL)
        .setSimple(ElementRegistry.ZINC, 1)
        .buildAndRegister()

    val ALUMINIUM = RagiMaterial.Builder(37, "aluminium", TypeRegistry.METAL)
        .setSimple(ElementRegistry.ALUMINIUM, 1)
        .buildAndRegister()

    val NICKEL = RagiMaterial.Builder(38, "nickel", TypeRegistry.METAL)
        .setSimple(ElementRegistry.NICKEL, 1)
        .buildAndRegister()

    val PLATINUM = RagiMaterial.Builder(39, "platinum", TypeRegistry.METAL)
        .setSimple(ElementRegistry.PLATINUM, 1)
        .buildAndRegister()

    val IRIDIUM = RagiMaterial.Builder(40, "iridium", TypeRegistry.METAL)
        .setSimple(ElementRegistry.IRIDIUM, 1)
        .buildAndRegister()

    val OSMIUM = RagiMaterial.Builder(41, "osmium", TypeRegistry.METAL)
        .setSimple(ElementRegistry.OSMIUM, 1)
        .buildAndRegister()

    val MITHRIL = RagiMaterial.Builder(42, "mithril", TypeRegistry.METAL)
        .setSimple(ElementRegistry.MITHRIL, 1)
        .buildAndRegister()

    //    Materials - Alloy Metal    //

    val STEEL = RagiMaterial.Builder(48, "steel", TypeRegistry.METAL)
        .setComponents(listOf(ElementRegistry.IRON to 1, ElementRegistry.CARBON to 1))
        .setMixture()
        .setColor(RagiColor.GRAY)
        .buildAndRegister()

    val ELECTRUM = RagiMaterial.Builder(49, "electrum", TypeRegistry.METAL)
        .setComponents(listOf(ElementRegistry.SILVER to 1, ElementRegistry.GOLD to 1))
        .setColor(ColorUtil.mixColor(RagiColor.GOLD, RagiColor.YELLOW, RagiColor.WHITE))
        .buildAndRegister()

    val INVAR = RagiMaterial.Builder(50, "invar", TypeRegistry.METAL)
        .setComponents(listOf(ElementRegistry.IRON to 2, ElementRegistry.NICKEL to 1))
        .setColor(ColorUtil.mixColor(RagiColor.GRAY to 1, RagiColor.GREEN to 1, RagiColor.WHITE to 2))
        .buildAndRegister()

    val BRONZE = RagiMaterial.Builder(51, "bronze", TypeRegistry.METAL)
        .setComponents(listOf(ElementRegistry.COPPER to 3, ElementRegistry.TIN to 1))
        .buildAndRegister()

    val BRASS = RagiMaterial.Builder(52, "brass", TypeRegistry.METAL)
        .setComponents(listOf(ElementRegistry.COPPER to 3, ElementRegistry.ZINC to 1))
        .setColor(RagiColor.GOLD)
        .buildAndRegister()

    val CONSTANTAN = RagiMaterial.Builder(53, "constantan", TypeRegistry.METAL)
        .setComponents(listOf(ElementRegistry.NICKEL to 1, ElementRegistry.COPPER to 1))
        .buildAndRegister()

}