package hiiragi283.ragi_materials.packet

import net.minecraftforge.fml.common.network.NetworkRegistry
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper
import net.minecraftforge.fml.relauncher.Side

/*
  Thanks to defeatedcrow!
  Source: https://github.com/defeatedcrow/FluidTankTutorialMod/blob/master/src/main/java/defeatedcrow/tutorial/ibc/packet/TutorialPacket.java
*/

class RagiPacket {

    companion object {
        val wrapper: SimpleNetworkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("ragi_materials:laboratory_table")

        fun init() {
            wrapper.registerMessage(MessageHandlerLabo::class.java, MessageLabo::class.java, 0, Side.CLIENT)
        }
    }

}