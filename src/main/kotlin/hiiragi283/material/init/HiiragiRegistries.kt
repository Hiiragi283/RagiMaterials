package hiiragi283.material.init

import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.registry.HiiragiRegistry

object HiiragiRegistries {

    //    Material    //

    @JvmField
    val MATERIAL_SMELTED: HiiragiRegistry<HiiragiMaterial, Pair<HiiragiMaterial, Int>> =
        HiiragiRegistry("Material Smelted", true)

}