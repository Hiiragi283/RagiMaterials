package hiiragi283.material.api.material

import hiiragi283.material.RagiMaterials

object MaterialRegistry {

    private val REGISTRY: LinkedHashMap<String, HiiragiMaterial> = linkedMapOf()
    private val INDEX_TO_MATERIAL: HashMap<Int, HiiragiMaterial> = hashMapOf()

    @JvmStatic
    fun getMaterials(): Collection<HiiragiMaterial> = REGISTRY.values

    @JvmStatic
    fun getMaterial(name: String) = REGISTRY.getOrDefault(name, HiiragiMaterial.EMPTY)

    @Deprecated("")
    @JvmStatic
    fun getMaterial(index: Int) = INDEX_TO_MATERIAL.getOrDefault(index, HiiragiMaterial.EMPTY)

    @JvmStatic
    fun registerMaterial(material: HiiragiMaterial) {

        //EMPTYを渡された場合はパス
        if (material == HiiragiMaterial.EMPTY) return

        //名前が空の場合はパス
        val name = material.name
        if (name.isEmpty()) {
            RagiMaterials.LOGGER.warn("The name of $material is empty!")
            return
        }

        //同じ名前で登録されていた場合は警告する
        val resultName = REGISTRY[name]
        if (resultName !== null) RagiMaterials.LOGGER.warn("$resultName will be overrided!")

        //番号が0 <= index <= 2767でない場合はパス
        val index = material.index
        if (index !in 0..32767) {
            RagiMaterials.LOGGER.warn("The index of $material is not in 0 to 32767!")
            return
        }

        //同じ番号で登録されていた場合は警告
        val resultIndex = INDEX_TO_MATERIAL[index]
        if (resultIndex !== null) RagiMaterials.LOGGER.warn("The index: $index will be overrided by $material")

        //登録を行う
        REGISTRY[name] = material
        INDEX_TO_MATERIAL[index] = material
    }

    fun init() {
        MaterialElements.init()
        MaterialCommon.init()
        MaterialIntegration.init()
    }
}