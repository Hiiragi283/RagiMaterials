package hiiragi283.integration

import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.material.MaterialIntegration

object ProjectRedIntegration : AbstractIntegration() {

    override fun registerMaterial(registry: MutableList<HiiragiMaterial>) {
        registry.add(MaterialIntegration.ELECTROTINE)
        registry.add(MaterialIntegration.RED_ALLOY)
        registry.add(MaterialIntegration.ELECTROTINE_ALLOY)
    }

}