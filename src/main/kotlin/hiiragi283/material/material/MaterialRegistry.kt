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

    private val mapMaterial: HashMap<String, RagiMaterial> = hashMapOf()
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

    private val listSiO2 = listOf(ElementRegistry.SILICON to 1, ElementRegistry.OXYGEN to 1)

    val STONE = RagiMaterial.Builder(0, "stone", TypeRegistry.STONE)
        .setComponents(listSiO2)
        .setColor(RagiColor.GRAY)
        .setMixture()
        .buildAndRegister()

    val GRANITE = RagiMaterial.Builder(1, "granite", TypeRegistry.DUST)
        .setComponents(listSiO2)
        .setColor(ColorUtil.mixColor(RagiColor.BLUE to 1, RagiColor.GREEN to 1, RagiColor.RED to 2))
        .setMixture()
        .buildAndRegister()

    val DIORITE = RagiMaterial.Builder(2, "diorite", TypeRegistry.DUST)
        .setComponents(listSiO2)
        .setColor(ColorUtil.mixColor(RagiColor.GRAY, RagiColor.WHITE))
        .setMixture()
        .buildAndRegister()

    val ANDESITE = RagiMaterial.Builder(3, "andesite", TypeRegistry.DUST)
        .setComponents(listSiO2)
        .setColor(RagiColor.GRAY)
        .setMixture()
        .buildAndRegister()

    val DIRT = RagiMaterial.Builder(4, "dirt", TypeRegistry.INTERNAL)
        .setColor(ColorUtil.mixColor(RagiColor.BLACK to 2, RagiColor.GOLD to 1, RagiColor.DARK_RED to 1))
        .buildAndRegister()

    val WOOD = RagiMaterial.Builder(5, "wood", TypeRegistry.WOOD)
        .setComponents(listOf(ElementRegistry.CARBON to 1, ElementRegistry.HYDROGEN to 1, ElementRegistry.OXYGEN to 1))
        .setColor(ColorUtil.mixColor(RagiColor.DARK_GRAY to 2, RagiColor.RED to 1, RagiColor.YELLOW to 1))
        .setMixture()
        .buildAndRegister()

    val BEDROCK = RagiMaterial.Builder(6, "bedrock", TypeRegistry.INGOT)
        .setComponents(listSiO2)
        .setColor(RagiColor.DARK_GRAY)
        .setMixture()
        .buildAndRegister()

    val SAND = RagiMaterial.Builder(7, "sand", TypeRegistry.DUST)
        .setComponents(listSiO2)
        .setColor(ColorUtil.mixColor(RagiColor.GOLD to 1, RagiColor.WHITE to 2))
        .setMixture()
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

}