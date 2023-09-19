package hiiragi283.material.compat

import hiiragi283.material.api.material.MaterialCompat

object HiiragiThermalPlugin : IHiiragiPlugin {

    override fun registerMaterial() {
        MaterialCompat.MITHRIL.register()
        MaterialCompat.SIGNALUM.register()
        MaterialCompat.LUMIUM.register()
        MaterialCompat.ENDERIUM.register()
        MaterialCompat.PYROTHEUM.register()
        MaterialCompat.CRYOTHEUM.register()
        MaterialCompat.AEROTHEUM.register()
        MaterialCompat.PETROTHEUM.register()
    }

}