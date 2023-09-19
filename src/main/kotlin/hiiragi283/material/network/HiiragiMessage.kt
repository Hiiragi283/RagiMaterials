package hiiragi283.material.network

import hiiragi283.material.util.readBlockPos
import hiiragi283.material.util.readNBTTag
import hiiragi283.material.util.writeBlockPos
import hiiragi283.material.util.writeNBTTag
import io.netty.buffer.ByteBuf
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.common.network.simpleimpl.IMessage

sealed class HiiragiMessage(var pos: BlockPos = BlockPos.ORIGIN, var tag: NBTTagCompound = NBTTagCompound()) :
    IMessage {

    override fun fromBytes(buf: ByteBuf) {
        pos = buf.readBlockPos()
        tag = buf.readNBTTag() ?: NBTTagCompound()
    }

    override fun toBytes(buf: ByteBuf) {
        buf.writeBlockPos(pos)
        buf.writeNBTTag(tag)
    }

    class Client(pos: BlockPos = BlockPos.ORIGIN, tag: NBTTagCompound = NBTTagCompound()) : HiiragiMessage(pos, tag)

    class Server(pos: BlockPos = BlockPos.ORIGIN, tag: NBTTagCompound = NBTTagCompound()) : HiiragiMessage(pos, tag)

}