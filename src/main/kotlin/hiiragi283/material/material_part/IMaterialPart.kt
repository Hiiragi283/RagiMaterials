package hiiragi283.material.material_part

import hiiragi283.material.material.HiiragiMaterial
import hiiragi283.material.part.HiiragiPart

interface IMaterialPart<T : Any> {

    fun getMaterialPart(obj: T): MaterialPart = MaterialPart.EMPTY

    fun getPart(obj: T): HiiragiPart = getMaterialPart(obj).part

    fun getMaterial(obj: T): HiiragiMaterial = getMaterialPart(obj).material

}