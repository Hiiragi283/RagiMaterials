package hiiragi283.material.api

import hiiragi283.material.api.material.MaterialRegistry
import hiiragi283.material.api.shape.ShapeRegistry
import hiiragi283.material.common.RagiMaterials

object RMInitializerImpl : RMInitializer {

    override fun onInitialize() {
        MaterialRegistry.init()
        RagiMaterials.LOGGER.info("HiiragiMaterial initialized!")

        ShapeRegistry.init()
        RagiMaterials.LOGGER.info("HiiragiShape initialized!")
    }

}