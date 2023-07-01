package hiiragi283.material.common

import hiiragi283.material.pre.loadRMInitializer
import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object RagiMaterials : ModInitializer {

    const val MODID = "ragi_materials"

    val LOGGER: Logger = LogManager.getLogger("RagiMaterials")

    override fun onInitialize() {

        loadRMInitializer()
        LOGGER.info("RMInitializer called!")

        RagiRegistry.loadBlocks()
        LOGGER.info("Blocks registered!")

        RagiRegistry.loadBlockItems()
        LOGGER.info("BlockItems registered!")

        RagiRegistry.loadItems()
        LOGGER.info("Items registered!")

        RagiEventHandler.load()
        LOGGER.info("Events registered!")

        RagiResourcePack.register()
        LOGGER.info("ResourcePack registered!")

    }
}