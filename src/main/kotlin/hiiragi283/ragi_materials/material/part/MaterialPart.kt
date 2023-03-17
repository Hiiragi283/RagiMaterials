package hiiragi283.ragi_materials.material.part

import hiiragi283.ragi_materials.material.type.EnumMaterialType

class MaterialPart private constructor(val name: String, val type: EnumMaterialType, val scale: Float) {

    class Builder(val name: String) {

        var scale = 1.0f
        var type = EnumMaterialType.DUMMY

        fun build(): MaterialPart {
            val part = MaterialPart(name, type, scale)
            PartRegistry.list.add(part)
            PartRegistry.map[part.name] = part
            return part
        }
    }
}