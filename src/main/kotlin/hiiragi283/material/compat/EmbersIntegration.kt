package hiiragi283.material.compat

import hiiragi283.api.material.MaterialIntegration
import hiiragi283.material.api.registry.HiiragiRegistry

object EmbersIntegration : hiiragi283.material.compat.AbstractIntegration() {

    override fun registerMaterial() {
        HiiragiRegistry.registerMaterial(MaterialIntegration.DAWNSTONE)
    }

}