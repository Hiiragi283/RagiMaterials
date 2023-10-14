package hiiragi283.material.compat

import hiiragi283.material.config.HiiragiConfigs
import hiiragi283.material.init.materials.MaterialCompats

object HiiragiProjectRedPlugin : HiiragiPluginBase(
    "projectred-core",
    "Project Red",
    { HiiragiConfigs.INTEGRATION.projectRed }
) {

    override fun registerMaterial() {
        MaterialCompats.ELECTROTINE.register()
        MaterialCompats.RED_ALLOY.register()
        MaterialCompats.ELECTROTINE_ALLOY.register()
    }

}