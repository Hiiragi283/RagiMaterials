package hiiragi283.api.shape

import hiiragi283.api.event.RMEventFactory
import hiiragi283.material.RagiMaterials

object ShapeRegistry {

    private val REGISTRY: HashMap<String, HiiragiShape> = hashMapOf()

    fun init() {
        val list: MutableList<HiiragiShape> = mutableListOf()
        RMEventFactory.registerShape(list)
        list.forEach { registerShape(it) }
    }

    /**
     * Returns a collection of [HiiragiShape] which registered in [REGISTRY]
     */
    @JvmStatic
    fun getShapes(): Collection<HiiragiShape> = REGISTRY.values

    /**
     * Returns [HiiragiShape] with given name from [REGISTRY]
     * @return [HiiragiShape.EMPTY] if there is no material with given name
     */
    @JvmStatic
    fun getShape(name: String): HiiragiShape = REGISTRY.getOrDefault(name, HiiragiShape.EMPTY)

    private fun registerShape(shape: HiiragiShape) {

        val name = shape.name
        REGISTRY[name]?.let {
            RagiMaterials.LOGGER.warn("$shape is already registered!")
            return
        }
        REGISTRY[name] = shape

    }

}