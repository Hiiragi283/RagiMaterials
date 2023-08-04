package hiiragi283.integration

import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.material.MaterialIntegration
import hiiragi283.core.RagiMaterials

object ThermalIntegration : AbstractIntegration() {

    override fun onPreInit() {
    }

    override fun registerMaterial(registry: MutableList<HiiragiMaterial>) {
        RagiMaterials.LOGGER.info("Enabled integration: Thermal Series")
        registry.add(MaterialIntegration.MITHRIL)
        registry.add(MaterialIntegration.SIGNALUM)
        registry.add(MaterialIntegration.LUMIUM)
        registry.add(MaterialIntegration.ENDERIUM)
        registry.add(MaterialIntegration.PYROTHEUM)
        registry.add(MaterialIntegration.CRYOTHEUM)
        registry.add(MaterialIntegration.AEROTHEUM)
        registry.add(MaterialIntegration.PETROTHEUM)
    }

    override fun onInit() {
    }

    override fun onPostInit() {
    }

    override fun onComplete() {
    }

}