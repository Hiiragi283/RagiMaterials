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
        val intArray = OreDictionary.getOreIDs(stack)
        return if (intArray.isNotEmpty())
            getMaterialFromOre(OreDictionary.getOreName(intArray[0]))
        else RagiMaterial.EMPTY
    }

    fun getMaterialFromOre(oredict: String): RagiMaterial = mapOredictMaterial.getOrDefault(oredict, RagiMaterial.EMPTY)

    fun getMaterialAll(): Collection<RagiMaterial> = mapMaterial.values

    fun setMaterial(material: RagiMaterial): RagiMaterial? = mapMaterial.putIfAbsent(material.name, material)

    fun setMaterialWithOre(oredict: String, material: RagiMaterial): RagiMaterial? =
        mapOredictMaterial.putIfAbsent(oredict, material)

    //    Materials    //

    private val listSiO2 = listOf(ElementRegistry.SILICON to 1, ElementRegistry.OXYGEN to 2)
    private val listAlSiO =
        listOf(ElementRegistry.ALUMINIUM to 1, ElementRegistry.SILICON to 1, ElementRegistry.OXYGEN to 1)
    private val listBeryl = listOf(
        ElementRegistry.BERYLLIUM to 3,
        ElementRegistry.ALUMINIUM to 2,
        ElementRegistry.SILICON to 6,
        ElementRegistry.OXYGEN to 18
    )

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
        .setComponents(listOf(ElementRegistry.CARBON to 1, ElementRegistry.HYDROGEN to 1, ElementRegistry.OXYGEN to 1))
        .setMixture()
        .setColor(ColorUtil.mixColor(RagiColor.DARK_GRAY to 2, RagiColor.RED to 1, RagiColor.YELLOW to 1))
        .buildAndRegister()

    val BEDROCK = RagiMaterial.Builder(6, "bedrock", TypeRegistry.INGOT)
        .setComponents(listSiO2)
        .setColor(RagiColor.DARK_GRAY)
        .setMixture()
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

    val NETHERRACK = RagiMaterial.Builder(17, "netherrack", TypeRegistry.STONE)
        .setComponents(listSiO2)
        .setMixture()
        .setColor(RagiColor.DARK_RED)
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

    val END_STONE = RagiMaterial.Builder(20, "end_stone", TypeRegistry.STONE)
        .setComponents(listSiO2)
        .setMixture()
        .setColor(ColorUtil.mixColor(RagiColor.YELLOW to 1, RagiColor.WHITE to 3))
        .buildAndRegister()

    val EMERALD = RagiMaterial.Builder(21, "emerald", TypeRegistry.CRYSTAL)
        .setComponents(listBeryl)
        .setColor(RagiColor.GREEN)
        .setCrystalType(EnumCrystalType.EMERALD)
        .buildAndRegister()

    val QUARTZ = RagiMaterial.Builder(22, "quartz", TypeRegistry.CRYSTAL)
        .setComponents(listSiO2)
        .setColor(RagiColor.WHITE)
        .setCrystalType(EnumCrystalType.QUARTZ)
        .buildAndRegister()

}