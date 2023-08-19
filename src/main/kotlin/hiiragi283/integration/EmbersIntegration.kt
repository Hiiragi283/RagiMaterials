package hiiragi283.integration

import hiiragi283.api.HiiragiRegistry
import hiiragi283.api.material.MaterialIntegration

object EmbersIntegration : AbstractIntegration() {

    override fun registerMaterial() {
        HiiragiRegistry.registerMaterial(MaterialIntegration.DAWNSTONE)
    }

}