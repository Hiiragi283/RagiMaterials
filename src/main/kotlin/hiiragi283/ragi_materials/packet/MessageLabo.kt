package hiiragi283.ragi_materials.packet

import io.netty.buffer.ByteBuf
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.common.network.ByteBufUtils
import net.minecraftforge.fml.common.network.simpleimpl.IMessage

/*
  Thanks to defeatedcrow!
  Source: https://github.com/defeatedcrow/FluidTankTutorialMod/blob/master/src/main/java/defeatedcrow/tutorial/ibc/packet/MessageIBC.java
*/

class MessageLabo(var x: Int = 0, var y: Int = 0, var z: Int = 0, var tag: NBTTagCompound = NBTTagCompound()) : IMessage {

    constructor(pos: BlockPos, tag: NBTTagCompound = NBTTagCompound()) : this(pos.x, pos.y, pos.z, tag)

    override fun fromBytes(buf: ByteBuf?) {
        buf?.let {
            //座標を読み取る
            x = it.readInt()
            y = it.readInt()
            z = it.readInt()
            //インベントリを読み込む
            ByteBufUtils.readTag(it)
        }
    }

    override fun toBytes(buf: ByteBuf?) {
        buf?.let {
            //座標を書き込む
            it.writeInt(this.x)
            it.writeInt(this.y)
            it.writeInt(this.z)
            //インベントリを書き込む
            ByteBufUtils.writeTag(it, tag)
        }
    }
}