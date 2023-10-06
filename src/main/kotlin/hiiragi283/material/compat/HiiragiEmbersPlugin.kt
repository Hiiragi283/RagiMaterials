package hiiragi283.material.compat

import hiiragi283.material.api.material.MaterialCompat
import hiiragi283.material.config.HiiragiConfigs

object HiiragiEmbersPlugin : HiiragiPluginBase("embers", "Embers Rekindled", HiiragiConfigs.INTEGRATION::embers) {

    override fun registerMaterial() {
        MaterialCompat.DAWNSTONE.register()
    }

}