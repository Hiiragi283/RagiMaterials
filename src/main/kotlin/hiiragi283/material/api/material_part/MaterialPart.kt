package hiiragi283.material.api.material_part

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart

data class MaterialPart(val part: HiiragiPart, val material: HiiragiMaterial) {

    companion object {
        @JvmField
        val EMPTY = MaterialPart(HiiragiPart.EMPTY, HiiragiMaterial.EMPTY)
    }

    fun isEmpty(): Boolean = this == EMPTY

    fun getOreDict(): String = StringBuilder().also {
        it.append(part.getOreDictPrefix())
        it.append(material.getOreDictName())
    }.toString()

    override fun toString(): String = "${part.name}:${material.name}"

}