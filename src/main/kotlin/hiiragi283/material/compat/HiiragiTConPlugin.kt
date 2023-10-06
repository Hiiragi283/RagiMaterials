package hiiragi283.material.compat

import hiiragi283.material.api.material.MaterialCompat
import hiiragi283.material.config.HiiragiConfigs

object HiiragiTConPlugin : HiiragiPluginBase("tconstruct", "Tinker's Construct", HiiragiConfigs.INTEGRATION::tCon) {

    override fun registerMaterial() {
        MaterialCompat.ARDITE.register()
        MaterialCompat.MANYULLYN.register()
        MaterialCompat.ALUMINIUM_BRASS.register()
    }

}