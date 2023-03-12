package hiiragi283.ragi_materials.packet

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.tile.TileLaboTable
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext

/*
  Thanks to defeatedcrow!
  Source: https://github.com/defeatedcrow/FluidTankTutorialMod/blob/master/src/main/java/defeatedcrow/tutorial/ibc/packet/MessageHandlerIBC.java
*/

class MessageHandlerLabo : IMessageHandler<MessageLabo, IMessage> {

    override fun onMessage(message: MessageLabo?, ctx: MessageContext?): IMessage? {
        //messageがnullでない場合，座標を取得する
        message?.let {
            val pos = BlockPos(it.x, it.y, it.z)
            val tile = Reference.PLAYER_CLIENT.world.getTileEntity(pos)
            if (tile !== null && tile is TileLaboTable) {
                tile.inventory.clear() //インベントリを空にする
            }
        }
        return null
    }
}