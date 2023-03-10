package hiiragi283.ragi_materials.packet

import io.netty.buffer.ByteBuf
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.common.network.simpleimpl.IMessage

/*
  Thanks to defeatedcrow!
  Source: https://github.com/defeatedcrow/FluidTankTutorialMod/blob/master/src/main/java/defeatedcrow/tutorial/ibc/packet/MessageIBC.java
*/

class MessageLabo(var x: Int = 0, var y: Int = 0, var z: Int = 0) : IMessage {

    constructor(pos: BlockPos) : this(pos.x, pos.y, pos.z)

    override fun fromBytes(buf: ByteBuf?) {
        buf?.let {
            //座標を読み取る
            this.x = it.readInt()
            this.y = it.readInt()
            this.z = it.readInt()
        }
    }

    override fun toBytes(buf: ByteBuf?) {
        //座標を書き込む
        buf?.let {
            it.writeInt(this.x)
            it.writeInt(this.y)
            it.writeInt(this.z)
        }
    }
}