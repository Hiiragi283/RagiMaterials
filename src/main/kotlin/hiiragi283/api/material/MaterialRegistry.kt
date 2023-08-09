package hiiragi283.api.material

import hiiragi283.api.event.RMEventFactory
import hiiragi283.material.RagiMaterials

object MaterialRegistry {

    private val REGISTRY: LinkedHashMap<String, HiiragiMaterial> = linkedMapOf()
    private val CACHE: HashMap<Int, HiiragiMaterial> = hashMapOf()
    private val INDEX_MAP: HashMap<Int, HiiragiMaterial> = hashMapOf()

    fun init() {
        val list: MutableList<HiiragiMaterial> = mutableListOf()
        RMEventFactory.registerMaterial(list)
        list.forEach { registerMaterial(it) }
        //CACHEを消す
        CACHE.clear()
        //indexを昇順に並べて代入する
        INDEX_MAP.putAll(REGISTRY.map { it.value.index to it.value }.toMap().toSortedMap())
    }

    /**
     * Returns a collection of [HiiragiMaterial] which registered in [REGISTRY]
     */
    @JvmStatic
    fun getMaterials(): Collection<HiiragiMaterial> = REGISTRY.values

    /**
     * Returns [HiiragiMaterial] with given name from [REGISTRY]
     * @return [HiiragiMaterial.EMPTY] if there is no material with given name
     */
    @JvmStatic
    fun getMaterial(name: String): HiiragiMaterial = REGISTRY.getOrDefault(name, HiiragiMaterial.EMPTY)

    /**
     * Returns [HiiragiMaterial] with given index from [INDEX_MAP]
     * @return [HiiragiMaterial.EMPTY] if there is no material with given index
     */
    @Deprecated("")
    @JvmStatic
    fun getMaterial(index: Int): HiiragiMaterial = INDEX_MAP.getOrDefault(index, HiiragiMaterial.EMPTY)

    private fun registerMaterial(material: HiiragiMaterial) {

        //EMPTYを渡された場合はパス
        if (material == HiiragiMaterial.EMPTY) return

        //名前が空の場合はパス
        val name = material.name
        if (name.isEmpty()) {
            RagiMaterials.LOGGER.warn("The name of $material is empty!")
            return
        }

        //同じ名前で登録されていた場合はパス
        if (REGISTRY[name] !== null) {
            RagiMaterials.LOGGER.warn("$material is already registered!")
            return
        }

        //登録を行う
        REGISTRY[name] = material

        //番号が0 <= index <= 32767でない場合はパス
        val index = material.index
        if (index !in 0..32767) {
            RagiMaterials.LOGGER.warn("The index of $material is not in 0 to 32767!")
            return
        }

        //同じ番号で登録されていた場合はパス
        if (CACHE[index] !== null) {
            RagiMaterials.LOGGER.warn("The index: $index is already registered by $material")
            return
        }

        //CACHEに保存する
        CACHE[index] = material

    }

}