package hiiragi283.material.material

import hiiragi283.material.material.type.TypeRegistry
import hiiragi283.material.util.RagiColor

object MaterialRegistry {

    fun load() {}

    private val mapMaterial: LinkedHashMap<String, RagiMaterial> = linkedMapOf()

    fun getMaterial(name: String): RagiMaterial = mapMaterial.getOrDefault(name, RagiMaterial.EMPTY)

    fun getMaterialAll() = mapMaterial.values

    fun setMaterial(material: RagiMaterial) {
        mapMaterial.putIfAbsent(material.name, material)
    }

    val STONE = RagiMaterial.Builder(0, "stone", TypeRegistry.STONE)
        .setColor(RagiColor.GRAY)
        .buildAndRegister()


}