package hiiragi283.integration

import hiiragi283.api.HiiragiRegistry
import hiiragi283.api.material.MaterialIntegration

object ProjectRedIntegration : AbstractIntegration() {

    override fun registerMaterial() {
        HiiragiRegistry.registerMaterial(MaterialIntegration.ELECTROTINE)
        HiiragiRegistry.registerMaterial(MaterialIntegration.RED_ALLOY)
        HiiragiRegistry.registerMaterial(MaterialIntegration.ELECTROTINE_ALLOY)
    }

}