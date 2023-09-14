package hiiragi283.material.compat

import hiiragi283.material.api.material.MaterialCompat

object EmbersIntegration : AbstractIntegration() {

    override fun registerMaterial() {
        MaterialCompat.DAWNSTONE.register()
    }

}