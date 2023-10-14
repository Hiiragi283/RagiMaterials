package hiiragi283.material.compat

import hiiragi283.material.config.HiiragiConfigs
import hiiragi283.material.init.materials.MaterialCompats

object HiiragiTConPlugin : HiiragiPluginBase("tconstruct", "Tinker's Construct", { HiiragiConfigs.INTEGRATION.tCon }) {

    override fun registerMaterial() {
        MaterialCompats.ARDITE.register()
        MaterialCompats.MANYULLYN.register()
        MaterialCompats.ALUMINIUM_BRASS.register()
    }

}