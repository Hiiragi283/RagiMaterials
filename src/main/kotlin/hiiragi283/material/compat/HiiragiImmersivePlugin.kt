package hiiragi283.material.compat

import hiiragi283.material.config.HiiragiConfigs
import hiiragi283.material.init.materials.MaterialCompats

object HiiragiImmersivePlugin : HiiragiPluginBase(
    "immersiveengineering",
    "Immersive Engineering",
    { HiiragiConfigs.INTEGRATION.embers }
) {

    override fun registerMaterial() {
        MaterialCompats.TREATED_WOOD.register()
        MaterialCompats.HOP_GRAPHITE.register()
    }

}