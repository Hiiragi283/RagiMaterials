package hiiragi283.material.api.material

import hiiragi283.material.RagiMaterials
import net.minecraft.util.ResourceLocation
import net.minecraftforge.registries.IForgeRegistry
import net.minecraftforge.registries.IForgeRegistryInternal
import net.minecraftforge.registries.RegistryBuilder
import net.minecraftforge.registries.RegistryManager

object MaterialRegistry {

    fun init() {}

    private val MATERIAL = ResourceLocation(RagiMaterials.MODID, "material")
    private val INDEX = ResourceLocation(RagiMaterials.MODID, "index_to_material")

    private val REGISTRY: IForgeRegistry<HiiragiMaterial> = RegistryBuilder<HiiragiMaterial>()
        .addCallback(CallBacks)
        .disableOverrides()
        .disableSaving()
        .setDefaultKey(ResourceLocation(RagiMaterials.MODID, "empty"))
        .setName(MATERIAL)
        .setType(HiiragiMaterial::class.java)
        .create()

    @Suppress("UNCHECKED_CAST")
    private fun getIndexMap(owner: IForgeRegistry<HiiragiMaterial> = REGISTRY): HashMap<Int, HiiragiMaterial>? =
        owner.getSlaveMap(INDEX, HashMap::class.java) as? HashMap<Int, HiiragiMaterial>

    @JvmStatic
    fun getMaterials(): Collection<HiiragiMaterial> = REGISTRY.valuesCollection

    @JvmStatic
    fun getMaterial(name: String): HiiragiMaterial =
        REGISTRY.getValue(ResourceLocation(RagiMaterials.MODID, name)) ?: HiiragiMaterial.EMPTY

    @Deprecated("")
    @JvmStatic
    fun getMaterial(index: Int): HiiragiMaterial = getIndexMap()?.get(index) ?: HiiragiMaterial.EMPTY

    private object CallBacks : IForgeRegistry.AddCallback<HiiragiMaterial>,
        IForgeRegistry.CreateCallback<HiiragiMaterial> {

        //    AddCallback    //

        override fun onAdd(
            owner: IForgeRegistryInternal<HiiragiMaterial>,
            stage: RegistryManager,
            id: Int,
            obj: HiiragiMaterial,
            oldObj: HiiragiMaterial?
        ) {
            if (oldObj == null) {
                getIndexMap(owner)?.set(obj.index, obj)
            } else {
                throw IllegalArgumentException("The index: ${obj.index} of $obj is duplicated with $oldObj!")
            }
        }

        //    CreateCallback    //

        override fun onCreate(owner: IForgeRegistryInternal<HiiragiMaterial>, stage: RegistryManager) {
            owner.setSlaveMap(INDEX, hashMapOf<Int, HiiragiMaterial>())
        }

    }

}