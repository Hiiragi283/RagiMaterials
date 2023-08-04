package hiiragi283.integration

import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.material.MaterialIntegration
import hiiragi283.core.RagiMaterials

object EmbersIntegration : AbstractIntegration() {

    override fun onPreInit() {
    }

    override fun registerMaterial(registry: MutableList<HiiragiMaterial>) {
        RagiMaterials.LOGGER.info("Enabled integration: Embers")
        registry.add(MaterialIntegration.DAWNSTONE)
    }

    override fun onInit() {
    }

    override fun onPostInit() {
    }

    override fun onComplete() {
    }

}