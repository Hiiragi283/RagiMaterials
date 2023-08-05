@file:JvmName("HiiragiByteUtil")

package hiiragi283.core.util

import io.netty.buffer.ByteBuf
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fml.common.network.ByteBufUtils

//    Reader    //

fun ByteBuf.readBlockPos(): BlockPos = BlockPos(this.readInt(), this.readInt(), this.readInt())

fun ByteBuf.readNBTTag(): NBTTagCompound? = ByteBufUtils.readTag(this)

//    Writer    //

fun ByteBuf.writeBlockPos(pos: BlockPos) = also {
    it.writeInt(pos.x)
    it.writeInt(pos.y)
    it.writeInt(pos.z)
}

fun ByteBuf.writeNBTTag(tag: NBTTagCompound) = also { ByteBufUtils.writeTag(this, tag) }