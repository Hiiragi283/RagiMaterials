package hiiragi283.material.material.part

import hiiragi283.material.material.type.EnumMaterialType

data class MaterialPart private constructor(
    val name: String,
    val prefixOre: String,
    val type: EnumMaterialType,
    val scale: Float
) {

    class Builder(val name: String) {

        var prefixOre = ""
        var scale = 1.0f
        var type = EnumMaterialType.DUMMY

        fun build(): MaterialPart = MaterialPart(name, prefixOre, type, scale).also { PartRegistry.setPart(it) }
    }
}