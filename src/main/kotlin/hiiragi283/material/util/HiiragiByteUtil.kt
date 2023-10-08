@file:JvmName("HiiragiByteUtil")

package hiiragi283.material.util

import io.netty.buffer.ByteBuf
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.common.network.ByteBufUtils

//    Reader    //

fun ByteBuf.readBlockPos(): BlockPos = BlockPos(this.readInt(), this.readInt(), this.readInt())

fun ByteBuf.readString(): String = ByteBufUtils.readUTF8String(this)

fun ByteBuf.readNBTTag(): NBTTagCompound? = ByteBufUtils.readTag(this)

//    Writer    //

fun ByteBuf.writeBlockPos(pos: BlockPos) {
    this.writeInt(pos.x)
    this.writeInt(pos.y)
    this.writeInt(pos.z)
}

fun ByteBuf.writeString(string: String) {
    ByteBufUtils.writeUTF8String(this, string)
}

fun ByteBuf.writeNBTTag(tag: NBTTagCompound) {
    ByteBufUtils.writeTag(this, tag)
}