package hiiragi283.ragi_materials.packet

import io.netty.buffer.ByteBuf
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.common.network.simpleimpl.IMessage

/*
  Thanks to defeatedcrow!
  Source: https://github.com/defeatedcrow/FluidTankTutorialMod/blob/master/src/main/java/defeatedcrow/tutorial/ibc/packet/MessageIBC.java
*/

class MessageLabo: IMessage {

    var x = 0
    var y = 0
    var z = 0

    constructor()

    constructor(pos: BlockPos) {
        this.x = pos.x
        this.y = pos.y
        this.z = pos.z
    }


    override fun fromBytes(buf: ByteBuf?) {
        if (buf !== null) {
            //座標を読み取る
            this.x = buf.readInt()
            this.y = buf.readInt()
            this.z = buf.readInt()
        }
    }

    override fun toBytes(buf: ByteBuf?) {
        //座標を書き込む
        if (buf !== null) {
            buf.writeInt(this.x)
            buf.writeInt(this.y)
            buf.writeInt(this.z)
        }
    }
}