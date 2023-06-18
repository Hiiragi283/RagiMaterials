@file:JvmName("HiiragiUtil")

package hiiragi283.material.util

import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import net.minecraft.util.Identifier

//    Identifier    //

fun commonId(path: String): Identifier = Identifier("c", path)

fun hiiragiId(path: String): Identifier = Identifier(RagiMaterials.MODID, path)

fun materialPartId(material: HiiragiMaterial, part: HiiragiPart): Identifier =
    hiiragiId("${material.name}.${part.name}")