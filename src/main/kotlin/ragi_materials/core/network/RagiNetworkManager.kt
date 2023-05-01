package ragi_materials.core.network

import net.minecraftforge.fml.common.network.NetworkRegistry
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
import net.minecraftforge.fml.relauncher.Side
import ragi_materials.core.RagiMaterials

val RagiNetworkWrapper: SimpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(RagiMaterials.MOD_ID)

object RagiNetworkManager {

    fun load() {
        //パケットの登録
        RagiNetworkWrapper.registerMessage(MessageHandler.Sync::class.java, RagiMessage.Sync::class.java, 0, Side.CLIENT)
        RagiNetworkWrapper.registerMessage(MessageHandler.Tile::class.java, RagiMessage.Tile::class.java, 1, Side.CLIENT)
        RagiNetworkWrapper.registerMessage(MessageHandler.Fluid::class.java, RagiMessage.Fluid::class.java, 2, Side.CLIENT)
    }
}