package hiiragi283.material.pre

import hiiragi283.material.api.material.MaterialElements
import hiiragi283.material.api.part.PartRegistry
import hiiragi283.material.common.RagiMaterials
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint

object RagiMaterialsPre : PreLaunchEntrypoint {

    override fun onPreLaunch() {

        MaterialElements.load()
        RagiMaterials.LOGGER.info("Elemental materials registered!")

        PartRegistry.load()
        RagiMaterials.LOGGER.info("Material parts registered!")

    }
}