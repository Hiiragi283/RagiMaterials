package hiiragi283.material.compat

import hiiragi283.material.api.material.MaterialCompat
import hiiragi283.material.config.HiiragiConfigs

object HiiragiThermalPlugin : HiiragiPluginBase("thermalfoundation", "Thermal", HiiragiConfigs.INTEGRATION::thermal) {

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