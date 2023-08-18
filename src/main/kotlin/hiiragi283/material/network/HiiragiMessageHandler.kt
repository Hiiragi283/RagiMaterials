package hiiragi283.material.network

import net.minecraft.client.Minecraft
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext

object HiiragiMessageHandler {

    class ToClient : IMessageHandler<HiiragiMessage.ToClient, IMessage> {

        override fun onMessage(message: HiiragiMessage.ToClient, ctx: MessageContext): IMessage? {
            Minecraft.getMinecraft().world.getTileEntity(message.pos)?.readFromNBT(message.tag)
            return null
        }

    }

    class ToServer : IMessageHandler<HiiragiMessage.ToClient, IMessage> {

        override fun onMessage(message: HiiragiMessage.ToClient, ctx: MessageContext): IMessage? {
            return null
        }

    }

}