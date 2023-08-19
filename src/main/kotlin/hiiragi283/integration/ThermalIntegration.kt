package hiiragi283.integration

import hiiragi283.api.HiiragiRegistry
import hiiragi283.api.material.MaterialIntegration

object ThermalIntegration : AbstractIntegration() {

    override fun registerMaterial() {
        HiiragiRegistry.registerMaterial(MaterialIntegration.MITHRIL)
        HiiragiRegistry.registerMaterial(MaterialIntegration.SIGNALUM)
        HiiragiRegistry.registerMaterial(MaterialIntegration.LUMIUM)
        HiiragiRegistry.registerMaterial(MaterialIntegration.ENDERIUM)
        HiiragiRegistry.registerMaterial(MaterialIntegration.PYROTHEUM)
        HiiragiRegistry.registerMaterial(MaterialIntegration.CRYOTHEUM)
        HiiragiRegistry.registerMaterial(MaterialIntegration.AEROTHEUM)
        HiiragiRegistry.registerMaterial(MaterialIntegration.PETROTHEUM)
    }

}