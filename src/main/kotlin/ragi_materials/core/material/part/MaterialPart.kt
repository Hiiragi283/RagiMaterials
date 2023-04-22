package ragi_materials.core.material.part

import ragi_materials.core.material.type.EnumMaterialType

data class MaterialPart private constructor(val name: String, val prefixOre: String, val type: EnumMaterialType, val scale: Float) {

    class Builder(val name: String) {

        var prefixOre = ""
        var scale = 1.0f
        var type = EnumMaterialType.DUMMY

        fun build(): MaterialPart {
            val part = MaterialPart(name, prefixOre, type, scale)
            PartRegistry.list.add(part)
            PartRegistry.map[part.name] = part
            return part
        }
    }
}