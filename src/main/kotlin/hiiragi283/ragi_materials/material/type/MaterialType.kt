package hiiragi283.ragi_materials.material.type

class MaterialType(val name: String, val parts: List<EnumMaterialType>) {

    init {
        register()
    }

    fun register(): MaterialType {
        TypeRegistry.map[this.name] = this
        return this
    }
}