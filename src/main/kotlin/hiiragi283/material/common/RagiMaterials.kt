package hiiragi283.material.common

import hiiragi283.material.api.part.PartRegistry
import hiiragi283.material.integration.RagiIntegrationCore
import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object RagiMaterials : ModInitializer {

    const val MODID = "ragi_materials"

    val LOGGER: Logger = LogManager.getLogger("RagiMaterials")

    override fun onInitialize() {

        loadRMInitializer()
        LOGGER.info("RMInitializer called!")

        PartRegistry.init()
        LOGGER.info("PartRegistry initialized!")

        RMRegistry.loadBlocks()
        LOGGER.info("Blocks and BlockItems registered!")

        //RMRegistry.loadFluids()
        LOGGER.info("Fluids and Buckets registered!")

        RMRegistry.loadItems()
        LOGGER.info("Items registered!")

        RMRegistry.loadMaterialTags()
        LOGGER.info("Material Tags are added to Material Items!")

        RagiIntegrationCore.init()
        LOGGER.info("Integrations initialized!")

        RMEventHandler.loadCommon()
        LOGGER.info("Common Events registered!")

        RMResourcePack.register()
        LOGGER.info("ResourcePack registered!")

    }
}