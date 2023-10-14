package hiiragi283.material.compat

import hiiragi283.material.config.HiiragiConfigs
import hiiragi283.material.init.materials.MaterialCompats

object HiiragiMekanismPlugin : HiiragiPluginBase("mekanism", "Mekanism", { HiiragiConfigs.INTEGRATION.mekanism }) {

    override fun registerMaterial() {
        MaterialCompats.OBSIDIAN_REFINED.register()
        MaterialCompats.GLOWSTONE_REFINED.register()
    }

}