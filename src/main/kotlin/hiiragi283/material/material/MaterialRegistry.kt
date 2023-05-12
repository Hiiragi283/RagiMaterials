package hiiragi283.material.material

import hiiragi283.material.material.element.ElementRegistry
import hiiragi283.material.material.type.TypeRegistry
import hiiragi283.material.util.ColorUtil
import hiiragi283.material.util.RagiColor

object MaterialRegistry {

    fun load() {}

    private val mapMaterial: LinkedHashMap<String, RagiMaterial> = linkedMapOf()
    private val mapIndex: LinkedHashMap<Int, RagiMaterial> = linkedMapOf()

    fun getMaterial(name: String): RagiMaterial = mapMaterial.getOrDefault(name, RagiMaterial.EMPTY)

    @Deprecated("Deprecated")
    fun getMaterial(index: Int): RagiMaterial = mapIndex.getOrDefault(index, RagiMaterial.EMPTY)

    fun getMaterialAll() = mapMaterial.values

    fun setMaterial(material: RagiMaterial) {
        mapMaterial.putIfAbsent(material.name, material)
        mapIndex.putIfAbsent(material.index, material)
    }

    //    Materials    //

    val STONE = RagiMaterial.Builder(0, "stone", TypeRegistry.STONE)
        .setComponents(listOf(ElementRegistry.SILICON to 1, ElementRegistry.OXYGEN to 1))
        .setColor(RagiColor.GRAY)
        .setMixture()
        .buildAndRegister()

    val GRANITE = RagiMaterial.Builder(1, "granite", TypeRegistry.DUST)
        .setComponents(listOf(ElementRegistry.SILICON to 1, ElementRegistry.OXYGEN to 1))
        .setColor(ColorUtil.mixColor(RagiColor.BLUE to 1, RagiColor.GREEN to 1, RagiColor.RED to 2))
        .setMixture()
        .buildAndRegister()

    val DIORITE = RagiMaterial.Builder(2, "diorite", TypeRegistry.DUST)
        .setComponents(listOf(ElementRegistry.SILICON to 1, ElementRegistry.OXYGEN to 1))
        .setColor(ColorUtil.mixColor(RagiColor.GRAY, RagiColor.WHITE))
        .setMixture()
        .buildAndRegister()

    val ANDESITE = RagiMaterial.Builder(3, "andesite", TypeRegistry.DUST)
        .setComponents(listOf(ElementRegistry.SILICON to 1, ElementRegistry.OXYGEN to 1))
        .setColor(RagiColor.GRAY)
        .setMixture()
        .buildAndRegister()

    val WOOD = RagiMaterial.Builder(4, "wood", TypeRegistry.WOOD)
        .setComponents(listOf(ElementRegistry.CARBON to 1, ElementRegistry.HYDROGEN to 1, ElementRegistry.OXYGEN to 1))
        .setColor(ColorUtil.mixColor(RagiColor.DARK_GRAY to 2, RagiColor.RED to 1, RagiColor.YELLOW to 1))
        .setMixture()
        .buildAndRegister()

}