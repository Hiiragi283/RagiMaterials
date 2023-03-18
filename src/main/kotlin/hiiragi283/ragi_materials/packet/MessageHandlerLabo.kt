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
            val tile = Reference.PLAYER_CLIENT.world.getTileEntity(BlockPos(it.x, it.y, it.z))
            if (tile !== null && tile is TileLaboTable) {
                //tagから読み込んだインベントリを書き込む
                tile.readFromNBT(it.tag)
            }
        }
        return null
    }
}