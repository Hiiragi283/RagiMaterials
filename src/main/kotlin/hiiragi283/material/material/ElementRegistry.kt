package hiiragi283.material.material

object ElementRegistry {

    private val mapElement: LinkedHashMap<String, RagiElement> = linkedMapOf()

    fun getElement(name: String): RagiElement = mapElement.getOrDefault(name, RagiElement.EMPTY)

    fun getElementAll() = mapElement.values

    fun setElement(material: RagiElement) {
        mapElement.putIfAbsent(material.name, material)
    }

}