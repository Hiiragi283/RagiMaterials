package hiiragi283.material.compat

import hiiragi283.material.api.material.MaterialCompat

object HiiragiProjectRedPlugin : IHiiragiPlugin {

    override fun registerMaterial() {
        MaterialCompat.ELECTROTINE.register()
        MaterialCompat.RED_ALLOY.register()
        MaterialCompat.ELECTROTINE_ALLOY.register()
    }

}