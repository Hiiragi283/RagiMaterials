package hiiragi283.material.material

import hiiragi283.material.RagiMaterials

object MaterialRegistry {

    private val REGISTRY: LinkedHashMap<String, HiiragiMaterial> = linkedMapOf()
    private val INDEX_TO_MATERIAL: HashMap<Int, HiiragiMaterial> = hashMapOf()

    @JvmStatic
    fun getMaterials(): Collection<HiiragiMaterial> = REGISTRY.values

    @JvmStatic
    fun getMaterialFromName(name: String) = REGISTRY.getOrDefault(name, HiiragiMaterial.EMPTY)

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

        //同じ名前で登録されていた場合はパス
        val resultName = REGISTRY[name]
        if (resultName !== null) {
            RagiMaterials.LOGGER.warn("The name of $material was already registered by $resultName!")
            return
        }
        //どちらも重複しなかった場合のみ登録を行う
        REGISTRY[name] = material

        //番号が0 <= index <= 2767でない場合はパス
        val index = material.index
        if (index !in 0..32767) {
            RagiMaterials.LOGGER.warn("The index of $material is not in 0 to 32767!")
            return
        }

        //同じ番号で登録されていた場合はパス
        val resultIndex = INDEX_TO_MATERIAL[index]
        if (resultIndex !== null) {
            RagiMaterials.LOGGER.warn("The index of $material was already registered by $resultIndex!")
            return
        }
        //どちらも重複しなかった場合のみ登録を行う
        INDEX_TO_MATERIAL[index] = material
    }

    fun init() {
        MaterialElements.init()
        MaterialCommon.init()
    }
}