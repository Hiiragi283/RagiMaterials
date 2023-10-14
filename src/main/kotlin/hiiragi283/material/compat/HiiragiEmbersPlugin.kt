package hiiragi283.material.compat

import hiiragi283.material.config.HiiragiConfigs
import hiiragi283.material.init.materials.MaterialCompats

object HiiragiEmbersPlugin : HiiragiPluginBase("embers", "Embers Rekindled", { HiiragiConfigs.INTEGRATION.embers }) {

    override fun registerMaterial() {
        MaterialCompats.DAWNSTONE.register()
    }

}