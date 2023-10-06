package hiiragi283.material.compat

import hiiragi283.material.api.material.MaterialCompat
import hiiragi283.material.config.HiiragiConfigs

object HiiragiProjectRedPlugin : HiiragiPluginBase(
    "projectred-core",
    "Project Red",
    { HiiragiConfigs.INTEGRATION.projectRed }
) {

    override fun registerMaterial() {
        MaterialCompat.ELECTROTINE.register()
        MaterialCompat.RED_ALLOY.register()
        MaterialCompat.ELECTROTINE_ALLOY.register()
    }

}