package hiiragi283.material.compat

import hiiragi283.api.material.MaterialIntegration
import hiiragi283.material.api.registry.HiiragiRegistry

object ThermalIntegration : hiiragi283.material.compat.AbstractIntegration() {

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