package hiiragi283.material.api

import hiiragi283.material.api.material.MaterialCommon
import hiiragi283.material.api.material.MaterialElements
import hiiragi283.material.api.part.PartRegistry
import hiiragi283.material.common.RagiDataPackHandler
import hiiragi283.material.common.RagiMaterials

object RMInitializerImpl : RMInitializer {

    override fun onInitialize() {
        MaterialElements.load()
        RagiMaterials.LOGGER.info("Elemental Materials registered!")

        MaterialCommon.load()
        RagiMaterials.LOGGER.info("Common Materials registered!")

        RagiDataPackHandler.load()
        RagiMaterials.LOGGER.info("Materials from data packs registered!")

        PartRegistry.load()
        RagiMaterials.LOGGER.info("Material parts registered!")
    }

}