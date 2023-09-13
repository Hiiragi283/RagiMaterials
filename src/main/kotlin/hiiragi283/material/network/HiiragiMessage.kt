package hiiragi283.material.network

import io.netty.buffer.ByteBuf
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.common.network.ByteBufUtils
import net.minecraftforge.fml.common.network.simpleimpl.IMessage

sealed class HiiragiMessage(var pos: BlockPos = BlockPos.ORIGIN, var tag: NBTTagCompound = NBTTagCompound()) :
    IMessage {

    override fun fromBytes(buf: ByteBuf) {
        val x: Int = buf.readInt()
        val y: Int = buf.readInt()
        val z: Int = buf.readInt()
        pos = BlockPos(x, y, z)
        tag = ByteBufUtils.readTag(buf) ?: NBTTagCompound()
    }

    override fun toBytes(buf: ByteBuf) {
        buf.writeByte(pos.x)
        buf.writeByte(pos.y)
        buf.writeByte(pos.z)
        ByteBufUtils.writeTag(buf, tag)
    }

    class Client(pos: BlockPos, tag: NBTTagCompound) : HiiragiMessage(pos, tag)

    class Server(pos: BlockPos, tag: NBTTagCompound) : HiiragiMessage(pos, tag)

}