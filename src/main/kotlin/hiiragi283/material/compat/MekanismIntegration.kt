package hiiragi283.material.compat

import hiiragi283.api.material.MaterialIntegration
import hiiragi283.material.api.registry.HiiragiRegistry

object MekanismIntegration : hiiragi283.material.compat.AbstractIntegration() {

    override fun registerMaterial() {
        HiiragiRegistry.registerMaterial(MaterialIntegration.OBSIDIAN_REFINED)
        HiiragiRegistry.registerMaterial(MaterialIntegration.GLOWSTONE_REFINED)
    }

}