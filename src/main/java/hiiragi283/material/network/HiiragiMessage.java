package hiiragi283.material.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class HiiragiMessage implements IMessage {

    public BlockPos pos;
    public NBTTagCompound tag;

    public HiiragiMessage() {
        this(BlockPos.ORIGIN, new NBTTagCompound());
    }

    public HiiragiMessage(BlockPos pos, NBTTagCompound tag) {
        this.pos = pos;
        this.tag = tag;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        int x = buf.readInt();
        int y = buf.readInt();
        int z = buf.readInt();
        pos = new BlockPos(x, y, z);
        tag = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeByte(pos.getX());
        buf.writeByte(pos.getY());
        buf.writeByte(pos.getZ());
        ByteBufUtils.writeTag(buf, tag);
    }

}