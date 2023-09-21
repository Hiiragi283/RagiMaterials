package hiiragi283.material.network

import hiiragi283.material.util.readBlockPos
import hiiragi283.material.util.readNBTTag
import hiiragi283.material.util.writeBlockPos
import hiiragi283.material.util.writeNBTTag
import io.netty.buffer.ByteBuf
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.math.BlockPos

sealed class HiiragiMessage(
    override var pos: BlockPos = BlockPos.ORIGIN,
    override var tag: NBTTagCompound = NBTTagCompound()
) : IHiiragiMessage {

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

    class Primitive(pos: BlockPos = BlockPos.ORIGIN) : HiiragiMessage(pos, NBTTagCompound())

    class SyncCount(pos: BlockPos = BlockPos.ORIGIN, var count: Int = 0): HiiragiMessage(pos, NBTTagCompound()) {

        override fun fromBytes(buf: ByteBuf) {
            super.fromBytes(buf)
            count = buf.readInt()
        }

        override fun toBytes(buf: ByteBuf) {
            super.toBytes(buf)
            buf.writeInt(count)
        }

    }

    class Player : HiiragiMessage(BlockPos.ORIGIN, NBTTagCompound())

    class Entity(
        pos: BlockPos = BlockPos.ORIGIN,
        tag: NBTTagCompound = NBTTagCompound(),
        var entityId: Int = 0
    ) : HiiragiMessage(pos, tag) {

        constructor(pos: BlockPos = BlockPos.ORIGIN, tag: NBTTagCompound = NBTTagCompound(), entity: Entity) : this(
            pos,
            tag,
            entity.entityId
        )

        override fun fromBytes(buf: ByteBuf) {
            super.fromBytes(buf)
            buf.writeInt(entityId)
        }

        override fun toBytes(buf: ByteBuf) {
            super.toBytes(buf)
            entityId = buf.readInt()
        }

    }

}