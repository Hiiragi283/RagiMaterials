package hiiragi283.integration

import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.material.MaterialIntegration
import hiiragi283.core.RagiMaterials

object MekanismIntegration : AbstractIntegration() {

    override fun onPreInit() {
    }

    override fun registerMaterial(registry: MutableList<HiiragiMaterial>) {
        RagiMaterials.LOGGER.info("Enabled integration: Mekanism")
        registry.add(MaterialIntegration.OBSIDIAN_REFINED)
        registry.add(MaterialIntegration.GLOWSTONE_REFINED)
    }

    override fun onInit() {
    }

    override fun onPostInit() {
    }

    override fun onComplete() {
    }

}