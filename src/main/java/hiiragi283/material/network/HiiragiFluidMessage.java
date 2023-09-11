package hiiragi283.material.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

@ParametersAreNonnullByDefault
public class HiiragiFluidMessage implements IMessage {

    public BlockPos pos = BlockPos.ORIGIN;
    public int tankIndex = 0;
    @Nullable
    private FluidStack fluidStack = null;

    public Optional<FluidStack> getFluidStack() {
        return Optional.ofNullable(fluidStack);
    }

    public HiiragiFluidMessage() {
    }

    public HiiragiFluidMessage(BlockPos pos, int tankIndex, @Nullable FluidStack fluidStack) {
        this.pos = pos;
        this.tankIndex = tankIndex;
        this.fluidStack = fluidStack;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        int x = buf.readInt();
        int y = buf.readInt();
        int z = buf.readInt();
        pos = new BlockPos(x, y, z);
        tankIndex = buf.readInt();
        String name = ByteBufUtils.readUTF8String(buf);
        int amount = buf.readInt();
        if (!name.isEmpty() && amount > 0) {
            fluidStack = FluidRegistry.getFluidStack(name, amount);
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(pos.getX());
        buf.writeInt(pos.getY());
        buf.writeInt(pos.getZ());
        buf.writeInt(tankIndex);
        if (fluidStack == null) {
            ByteBufUtils.writeUTF8String(buf, "");
            buf.writeInt(0);
        } else {
            ByteBufUtils.writeUTF8String(buf, fluidStack.getFluid().getName());
            buf.writeInt(fluidStack.amount);
        }
    }

}