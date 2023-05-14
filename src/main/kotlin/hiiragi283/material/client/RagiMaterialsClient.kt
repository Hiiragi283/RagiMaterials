package hiiragi283.material.client

import hiiragi283.material.RagiRegistry
import hiiragi283.material.client.color.RagiColorProvider
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry

object RagiMaterialsClient : ClientModInitializer {

    override fun onInitializeClient() {

        //着色システムの登録
        ColorProviderRegistry.BLOCK.register(RagiColorProvider, *RagiRegistry.getBlocksColored().toTypedArray())
        ColorProviderRegistry.ITEM.register(RagiColorProvider, *RagiRegistry.getItemsColored().toTypedArray())

    }
}