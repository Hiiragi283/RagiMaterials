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

        RagiRegistry.loadBlocks()
        LOGGER.info("Blocks and BlockItems registered!")

        //RagiRegistry.loadFluids()
        LOGGER.info("Fluids and Buckets registered!")

        RagiRegistry.loadItems()
        LOGGER.info("Items registered!")

        RagiIntegrationCore.init()
        LOGGER.info("Integrations initialized!")

        RagiEventHandler.loadCommon()
        LOGGER.info("Common Events registered!")

        RagiResourcePack.register()
        LOGGER.info("ResourcePack registered!")

    }
}