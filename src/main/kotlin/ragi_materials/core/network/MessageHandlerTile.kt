package ragi_materials.core.network

import net.minecraft.client.Minecraft
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext
import ragi_materials.core.tile.ITileSyncable

class MessageHandlerTile : IMessageHandler<MessageTile, IMessage> {

    override fun onMessage(message: MessageTile?, ctx: MessageContext?): IMessage? {
        //messageがnullでない場合，座標を取得する
        message?.let {
            val tile = Minecraft.getMinecraft().world.getTileEntity(BlockPos(it.x, it.y, it.z))
            if (tile !== null && tile is ITileSyncable) tile.sync()
        }
        return null
    }
}