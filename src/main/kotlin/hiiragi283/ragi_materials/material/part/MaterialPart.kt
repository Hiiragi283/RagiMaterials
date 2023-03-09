package hiiragi283.ragi_materials.material.part

import hiiragi283.ragi_materials.material.type.EnumMaterialType

class MaterialPart(val name: String, val type: EnumMaterialType, val scale: Float) {

    init {
        register()
    }

    fun register(): MaterialPart = also{ PartRegistry.map[it.name] = it }

}