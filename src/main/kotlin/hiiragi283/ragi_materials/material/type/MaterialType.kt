package hiiragi283.ragi_materials.material.type

class MaterialType(val name: String, val list: List<EnumMaterialType>) {

    init {
        register()
    }

    fun register(): MaterialType = also{ TypeRegistry.map[it.name] = it }
}