package hiiragi283.material.api.material

import hiiragi283.material.common.RagiDataPackHandler
import hiiragi283.material.common.RagiMaterials

object MaterialRegistry {

    private val REGISTRY: LinkedHashMap<String, HiiragiMaterial> = linkedMapOf()

    @JvmStatic
    fun getMaterials(): Collection<HiiragiMaterial> = REGISTRY.values

    @JvmStatic
    fun getMaterial(name: String) = REGISTRY.getOrDefault(name, HiiragiMaterial.EMPTY)

    @JvmStatic
    fun registerMaterial(material: HiiragiMaterial) {

        //EMPTYを渡された場合はパス
        if (material == HiiragiMaterial.EMPTY) {
            RagiMaterials.LOGGER.error("$material is empty!")
            return
        }

        val name = material.name
        //名前が空の場合はパス
        if (name.isEmpty()) {
            RagiMaterials.LOGGER.error("The name of $material is empty!")
            return
        }

        val resultName = REGISTRY[name]
        //同じ名前で登録されていた場合，上書きの警告を表示する
        if (resultName !== null) RagiMaterials.LOGGER.warn("$resultName will be replaced by $material!")
        REGISTRY[name] = material
    }

    fun init() {
        REGISTRY.clear()
        MaterialElements.init()
        RagiMaterials.LOGGER.info("Elemental Materials registered!")

        MaterialCommon.init()
        RagiMaterials.LOGGER.info("Common Materials registered!")

        RagiDataPackHandler.load()
        RagiMaterials.LOGGER.info("Materials from data packs registered!")
    }

}