package hiiragi283.material.compat

import hiiragi283.material.api.material.MaterialCompat
import hiiragi283.material.config.HiiragiConfigs

object HiiragiMekanismPlugin : HiiragiPluginBase("mekanism", "Mekanism", HiiragiConfigs.INTEGRATION::mekanism) {

    override fun registerMaterial() {
        MaterialCompat.OBSIDIAN_REFINED.register()
        MaterialCompat.GLOWSTONE_REFINED.register()
    }

}