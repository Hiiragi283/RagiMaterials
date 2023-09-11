package hiiragi283.material.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public abstract class HiiragiMessage implements IMessage {

    public BlockPos pos = BlockPos.ORIGIN;
    public NBTTagCompound tag = new NBTTagCompound();

    public HiiragiMessage() {
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

    public static class Client extends HiiragiMessage {

        public Client() {
            super();
        }

        public Client(BlockPos pos, NBTTagCompound tag) {
            super(pos, tag);
        }

    }

    public static class Server extends HiiragiMessage {

        public Server() {
            super();
        }

        public Server(BlockPos pos, NBTTagCompound tag) {
            super(pos, tag);
        }

    }

}