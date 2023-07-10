package hiiragi283.material.api.part

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.shape.HiiragiShape

data class HiiragiPart(val shape: HiiragiShape, val material: HiiragiMaterial) {

    companion object {
        @JvmField
        val EMPTY = HiiragiPart(HiiragiShape.EMPTY, HiiragiMaterial.EMPTY)
    }

    fun isEmpty(): Boolean = this == EMPTY

    fun getOreDict(): String = StringBuilder().also {
        it.append(shape.getOreDictPrefix())
        it.append(material.getOreDictName())
    }.toString()

    override fun toString(): String = "${shape.name}:${material.name}"

}