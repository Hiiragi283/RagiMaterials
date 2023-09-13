package hiiragi283.material.compat

import hiiragi283.api.material.MaterialIntegration
import hiiragi283.material.api.registry.HiiragiRegistry

object ProjectRedIntegration : hiiragi283.material.compat.AbstractIntegration() {

    override fun registerMaterial() {
        HiiragiRegistry.registerMaterial(MaterialIntegration.ELECTROTINE)
        HiiragiRegistry.registerMaterial(MaterialIntegration.RED_ALLOY)
        HiiragiRegistry.registerMaterial(MaterialIntegration.ELECTROTINE_ALLOY)
    }

}