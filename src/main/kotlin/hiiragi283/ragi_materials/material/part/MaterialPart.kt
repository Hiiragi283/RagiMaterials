package hiiragi283.ragi_materials.material.part

class MaterialPart(val name: String, val part: EnumMaterialPart, val scale: Float) {

    init {
        register()
    }

    fun register(): MaterialPart = also{ PartRegistry.map[it.name] = it }

}