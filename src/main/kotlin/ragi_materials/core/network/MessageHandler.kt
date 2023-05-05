package ragi_materials.core.network

import net.minecraft.client.Minecraft
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext
import ragi_materials.core.RagiMaterials

class MessageHandler {

    class Sync : IMessageHandler<RagiMessage.Sync, IMessage> {

        override fun onMessage(message: RagiMessage.Sync, ctx: MessageContext?): IMessage? {
            val tile = Minecraft.getMinecraft().world.getTileEntity(BlockPos(message.x, message.y, message.z))
            if (tile !== null) {
                tile.readFromNBT(message.data)
                RagiMaterials.LOGGER.debug("[${tile.displayName?.unformattedText}]The data was synced!")
            }
            return null
        }
    }
}