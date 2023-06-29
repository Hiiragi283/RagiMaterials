package hiiragi283.material.api.material

import hiiragi283.material.api.part.HiiragiPart

interface MaterialPart<T : Any> {

    fun getMaterial(obj: T): HiiragiMaterial

    fun getPart(obj: T): HiiragiPart

}