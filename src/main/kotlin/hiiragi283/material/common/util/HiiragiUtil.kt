@file:JvmName("HiiragiUtil")

package hiiragi283.material.common.util

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.common.RagiMaterials
import net.minecraft.util.Identifier

//    Identifier    //

fun commonId(path: String): Identifier = Identifier("c", path)

fun hiiragiId(path: String): Identifier = Identifier(RagiMaterials.MODID, path)

fun materialPartId(material: HiiragiMaterial, part: HiiragiPart): Identifier =
    hiiragiId("${material.name}.${part.name}")