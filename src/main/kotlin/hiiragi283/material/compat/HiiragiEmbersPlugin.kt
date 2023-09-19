package hiiragi283.material.compat

import hiiragi283.material.api.material.MaterialCompat

object HiiragiEmbersPlugin : IHiiragiPlugin {

    override fun registerMaterial() {
        MaterialCompat.DAWNSTONE.register()
    }

}