package hiiragi283.material.network

import hiiragi283.material.util.readBlockPos
import hiiragi283.material.util.writeBlockPos
import io.netty.buffer.ByteBuf
import net.minecraft.util.math.BlockPos
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fml.common.network.ByteBufUtils
import net.minecraftforge.fml.common.network.simpleimpl.IMessage

class HiiragiFluidMessage(
    var pos: BlockPos = BlockPos.ORIGIN,
    var index: Int = 0,
    var fluidStack: FluidStack? = null
) : IMessage {


    override fun fromBytes(buf: ByteBuf) {
        pos = buf.readBlockPos()
        index = buf.readInt()
        val name: String = ByteBufUtils.readUTF8String(buf)
        val amount: Int = buf.readInt()
        if (name.isNotEmpty() && amount > 0) {
            fluidStack = FluidRegistry.getFluidStack(name, amount)
        }
    }

    override fun toBytes(buf: ByteBuf) {
        buf.writeBlockPos(pos)
        buf.writeInt(index)
        if (fluidStack == null) {
            ByteBufUtils.writeUTF8String(buf, "")
            buf.writeInt(0)
        } else {
            ByteBufUtils.writeUTF8String(buf, fluidStack!!.fluid.name)
            buf.writeInt(fluidStack!!.amount)
        }
    }

}