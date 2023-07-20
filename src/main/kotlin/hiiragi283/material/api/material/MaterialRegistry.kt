package hiiragi283.material.api.material

import hiiragi283.material.common.RagiDataPackHandler
import hiiragi283.material.common.RagiMaterials
import hiiragi283.material.common.util.hiiragiId
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder
import net.minecraft.tag.TagKey
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey
import net.minecraft.util.registry.SimpleRegistry

object MaterialRegistry {

    private val REGISTRY: SimpleRegistry<HiiragiMaterial> = FabricRegistryBuilder.createSimple(
        HiiragiMaterial::class.java,
        hiiragiId("material")
    ).buildAndRegister()

    val KEY: RegistryKey<out Registry<HiiragiMaterial>>? = REGISTRY.key

    init {
        TagKey.of(KEY, hiiragiId("test"))
    }

    @JvmStatic
    fun getMaterials(): Collection<HiiragiMaterial> = REGISTRY.entrySet.map { it.value }

    @JvmStatic
    fun getMaterial(name: String) = REGISTRY.get(hiiragiId(name)) ?: HiiragiMaterial.EMPTY

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

        //同じ名前で登録されていた場合，上書きの警告を表示する
        if (getMaterial(material.name) !== HiiragiMaterial.EMPTY) {
            RagiMaterials.LOGGER.warn("$material has been registered!")
            return
        }

        Registry.register(REGISTRY, hiiragiId(material.name), material)
    }

    fun init() {
        MaterialElements.register()
        RagiMaterials.LOGGER.info("Elemental Materials registered!")

        MaterialCommon.register()
        RagiMaterials.LOGGER.info("Common Materials registered!")

        RagiDataPackHandler.load()
        RagiMaterials.LOGGER.info("Materials from data packs loaded!")
    }

}