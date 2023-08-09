package hiiragi283.material.network

import net.minecraft.client.Minecraft
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext

object HiiragiMessageHandler {

    class Sync : IMessageHandler<HiiragiMessage.Sync, IMessage> {

        override fun onMessage(message: HiiragiMessage.Sync, ctx: MessageContext): IMessage? {
            Minecraft.getMinecraft().world.getTileEntity(message.pos)?.readFromNBT(message.tag)
            return null
        }

    }

}