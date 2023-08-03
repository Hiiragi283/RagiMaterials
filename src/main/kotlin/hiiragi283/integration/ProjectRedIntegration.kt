package hiiragi283.integration

import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.material.MaterialIntegration
import hiiragi283.core.RagiMaterials

object ProjectRedIntegration : AbstractIntegration() {

    override fun onPreInit() {
    }

    override fun registerMaterial(registry: MutableList<HiiragiMaterial>) {
        RagiMaterials.LOGGER.info("Enabled integration: ProjectRed")
        registry.add(MaterialIntegration.ELECTROTINE)
        registry.add(MaterialIntegration.RED_ALLOY)
        registry.add(MaterialIntegration.ELECTROTINE_ALLOY)
    }

    override fun onInit() {
    }

    override fun onPostInit() {
    }

    override fun onComplete() {
    }

}