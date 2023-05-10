package hiiragi283.material.material

object MaterialRegistry {

    private val mapMaterial: LinkedHashMap<String, RagiMaterial> = linkedMapOf()

    fun getMaterial(name: String): RagiMaterial = mapMaterial.getOrDefault(name, RagiMaterial.EMPTY)

    fun getMaterialAll() = mapMaterial.values

    fun setMaterial(material: RagiMaterial) {
        mapMaterial.putIfAbsent(material.name, material)
    }

}