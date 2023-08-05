package hiiragi283.integration

import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.material.MaterialIntegration

object MekanismIntegration : AbstractIntegration() {

    override fun registerMaterial(registry: MutableList<HiiragiMaterial>) {
        registry.add(MaterialIntegration.OBSIDIAN_REFINED)
        registry.add(MaterialIntegration.GLOWSTONE_REFINED)
    }

}