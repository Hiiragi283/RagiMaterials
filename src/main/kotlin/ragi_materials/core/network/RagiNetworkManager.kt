package ragi_materials.core.network

import ragi_materials.core.RagiMaterials
import net.minecraftforge.fml.common.network.NetworkRegistry
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
import net.minecraftforge.fml.relauncher.Side

val RagiNetworkWrapper: SimpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(RagiMaterials.MOD_ID)

object RagiNetworkManager {

    fun load() {
        //パケットの登録
        RagiNetworkWrapper.registerMessage(MessageHandlerTile::class.java, MessageTile::class.java, 0, Side.CLIENT)
    }

}