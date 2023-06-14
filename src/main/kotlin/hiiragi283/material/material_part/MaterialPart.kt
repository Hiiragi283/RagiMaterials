package hiiragi283.material.material_part

import hiiragi283.material.material.HiiragiMaterial
import hiiragi283.material.part.HiiragiPart

data class MaterialPart(val part: HiiragiPart, val material: HiiragiMaterial) : IMaterialPart<MaterialPart> {

    companion object {
        @JvmField
        val EMPTY = MaterialPart(HiiragiPart.EMPTY, HiiragiMaterial.EMPTY)
    }

    fun isEmpty(): Boolean = this == EMPTY

    fun getOreDictName(): String = StringBuilder().also {
        it.append(part.getOreDictPrefix())
        it.append(material.getOreDictName())
    }.toString()

    //    IMaterialPart    //

    override fun getMaterialPart(obj: MaterialPart): MaterialPart = this

}