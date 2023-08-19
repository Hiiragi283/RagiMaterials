package hiiragi283.integration

import hiiragi283.api.HiiragiRegistry
import hiiragi283.api.material.MaterialIntegration

object MekanismIntegration : AbstractIntegration() {

    override fun registerMaterial() {
        HiiragiRegistry.registerMaterial(MaterialIntegration.OBSIDIAN_REFINED)
        HiiragiRegistry.registerMaterial(MaterialIntegration.GLOWSTONE_REFINED)
    }

}