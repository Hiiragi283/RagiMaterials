package hiiragi283.material.api.material

import hiiragi283.material.RagiMaterials

object MaterialRegistry {

    private val REGISTRY: LinkedHashMap<String, HiiragiMaterial> = linkedMapOf()
    private val CACHE: HashMap<Int, HiiragiMaterial> = hashMapOf()
    private val INDEX_TO_MATERIAL: HashMap<Int, HiiragiMaterial> = hashMapOf()

    private var isLocked: Boolean = false

    @JvmStatic
    fun getMaterials(): Collection<HiiragiMaterial> = REGISTRY.values

    @JvmStatic
    fun getMaterial(name: String) = REGISTRY.getOrDefault(name, HiiragiMaterial.EMPTY)

    @Deprecated("")
    @JvmStatic
    fun getMaterial(index: Int) = INDEX_TO_MATERIAL.getOrDefault(index, HiiragiMaterial.EMPTY)

    @JvmStatic
    fun registerMaterial(material: HiiragiMaterial) {

        if (isLocked) {
            RagiMaterials.LOGGER.warn("MaterialRegistry is already locked!")
            return
        }

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

    fun init() {
        MaterialElements.register()
        MaterialCommon.register()
    }

    fun lock() {
        //CACHEを消す
        CACHE.clear()
        //indexを昇順に並べて代入する
        INDEX_TO_MATERIAL.putAll(REGISTRY.map { it.value.index to it.value }.toMap().toSortedMap())
        //レジストリへの登録を停止する
        isLocked = true
    }

}