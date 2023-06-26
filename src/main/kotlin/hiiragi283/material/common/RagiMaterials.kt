package hiiragi283.material.common

import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object RagiMaterials : ModInitializer {

    const val MODID = "ragi_materials"

    val LOGGER: Logger = LogManager.getLogger("RagiMaterials")

    override fun onInitialize() {

        RagiDataPackHandler.load()
        LOGGER.info("Materials from data packs registered!")

        RagiRegistry.loadBlocks()
        LOGGER.info("Blocks registered!")

        RagiRegistry.loadBlockItems()
        LOGGER.info("BlockItems registered!")

        RagiRegistry.loadItems()
        LOGGER.info("Items registered!")

        EventHandler.load()
        LOGGER.info("Events registered!")

        RagiRegistry.initTranslation()
        LOGGER.info("Translation registered!")

        RagiResourcePack.register()
        LOGGER.info("ResourcePack registered!")

    }
}