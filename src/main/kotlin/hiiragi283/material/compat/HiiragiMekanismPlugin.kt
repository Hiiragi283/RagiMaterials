package hiiragi283.material.compat

import hiiragi283.material.api.material.MaterialCompat

object HiiragiMekanismPlugin : IHiiragiPlugin {

    override fun registerMaterial() {
        MaterialCompat.OBSIDIAN_REFINED.register()
        MaterialCompat.GLOWSTONE_REFINED.register()
    }

}