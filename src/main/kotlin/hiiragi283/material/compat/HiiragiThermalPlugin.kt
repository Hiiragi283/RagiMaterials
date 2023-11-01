package hiiragi283.material.compat

import hiiragi283.material.config.HiiragiConfigs
import hiiragi283.material.init.materials.MaterialCompats

object HiiragiThermalPlugin : HiiragiPluginBase("thermalfoundation", "Thermal", { HiiragiConfigs.INTEGRATION.thermal }) {

    override fun registerMaterial() {
        MaterialCompats.MITHRIL.register()
        MaterialCompats.SIGNALUM.register()
        MaterialCompats.LUMIUM.register()
        MaterialCompats.ENDERIUM.register()
        MaterialCompats.PYROTHEUM.register()
        MaterialCompats.CRYOTHEUM.register()
        MaterialCompats.AEROTHEUM.register()
        MaterialCompats.PETROTHEUM.register()
        MaterialCompats.ROCKWOOL.register()
    }

}