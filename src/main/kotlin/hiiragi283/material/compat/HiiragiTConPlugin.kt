package hiiragi283.material.compat

import hiiragi283.material.api.material.MaterialCompat

object HiiragiTConPlugin : IHiiragiPlugin {

    override fun registerMaterial() {
        MaterialCompat.ARDITE.register()
        MaterialCompat.MANYULLYN.register()
        MaterialCompat.ALUMINIUM_BRASS.register()
    }

}