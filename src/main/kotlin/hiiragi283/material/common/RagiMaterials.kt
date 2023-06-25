package hiiragi283.material.common

import hiiragi283.material.common.util.hiiragiId
import net.devtech.arrp.api.RRPCallback
import net.devtech.arrp.api.RuntimeResourcePack
import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object RagiMaterials : ModInitializer {

    const val MODID = "ragi_materials"

    val LOGGER: Logger = LogManager.getLogger("RagiMaterials")
    val RESOURCE_PACK: RuntimeResourcePack = RuntimeResourcePack.create(hiiragiId("runtime"))

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

        RRPCallback.BEFORE_VANILLA.register { it.add(RESOURCE_PACK) }
        LOGGER.info("The resource pack registered!")

    }
}