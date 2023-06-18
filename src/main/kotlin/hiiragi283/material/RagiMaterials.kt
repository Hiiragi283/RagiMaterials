package hiiragi283.material

import hiiragi283.material.util.hiiragiId
import net.devtech.arrp.api.RuntimeResourcePack
import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object RagiMaterials : ModInitializer {

    const val MODID = "ragi_materials"

    val LOGGER: Logger = LogManager.getLogger("RagiMaterials")
    val RESOURCE_PACK: RuntimeResourcePack = RuntimeResourcePack.create(hiiragiId("runtime"))

    override fun onInitialize() {

    }
}