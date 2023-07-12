package hiiragi283.material.client

import hiiragi283.material.common.RagiEventHandler
import hiiragi283.material.common.RagiMaterials
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment


object RagiMaterialsClient : ClientModInitializer {

    @Environment(EnvType.CLIENT)
    override fun onInitializeClient() {

        RagiColorProvider.load()
        RagiMaterials.LOGGER.info("Color Provider registered!")

        RagiEventHandler.loadClient()
        RagiMaterials.LOGGER.info("Client Events registered!")

    }
}