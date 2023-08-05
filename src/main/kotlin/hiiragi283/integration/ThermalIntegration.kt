package hiiragi283.integration

import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.material.MaterialIntegration

object ThermalIntegration : AbstractIntegration() {

    override fun registerMaterial(registry: MutableList<HiiragiMaterial>) {
        registry.add(MaterialIntegration.MITHRIL)
        registry.add(MaterialIntegration.SIGNALUM)
        registry.add(MaterialIntegration.LUMIUM)
        registry.add(MaterialIntegration.ENDERIUM)
        registry.add(MaterialIntegration.PYROTHEUM)
        registry.add(MaterialIntegration.CRYOTHEUM)
        registry.add(MaterialIntegration.AEROTHEUM)
        registry.add(MaterialIntegration.PETROTHEUM)
    }

}