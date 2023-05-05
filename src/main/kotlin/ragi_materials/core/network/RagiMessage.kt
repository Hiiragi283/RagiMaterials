package ragi_materials.core.network

import io.netty.buffer.ByteBuf
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.common.network.ByteBufUtils
import net.minecraftforge.fml.common.network.simpleimpl.IMessage

/**
 * Thanks to defeatedcrow!
 * Source: https://github.com/defeatedcrow/FluidTankTutorialMod/blob/master/src/main/java/defeatedcrow/tutorial/ibc/packet/MessageIBC.java
 */

sealed class RagiMessage(var x: Int = 0, var y: Int = 0, var z: Int = 0) : IMessage {

    override fun fromBytes(buf: ByteBuf?) {
        buf?.let {
            //座標を読み取る
            x = it.readInt()
            y = it.readInt()
            z = it.readInt()
        }
    }

    override fun toBytes(buf: ByteBuf?) {
        buf?.let {
            //座標を書き込む
            it.writeInt(this.x)
            it.writeInt(this.y)
            it.writeInt(this.z)
        }
    }

    class Sync(x: Int = 0, y: Int = 0, z: Int = 0, var data: NBTTagCompound = NBTTagCompound()) : RagiMessage(x, y, z) {

        constructor(pos: BlockPos, data: NBTTagCompound = NBTTagCompound()) : this(pos.x, pos.y, pos.z, data)

        override fun fromBytes(buf: ByteBuf?) {
            super.fromBytes(buf)
            buf?.let {
                //NBTTagを読み取る
                data = ByteBufUtils.readTag(it) ?: NBTTagCompound()
            }
        }

        override fun toBytes(buf: ByteBuf?) {
            super.toBytes(buf)
            buf?.let {
                //NBTTagを書き込む
                ByteBufUtils.writeTag(it, data)
            }
        }

    }
}