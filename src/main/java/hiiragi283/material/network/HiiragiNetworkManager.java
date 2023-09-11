package hiiragi283.material.network;

import hiiragi283.material.RMReference;
import hiiragi283.material.tile.TileEntityModuleMachine;
import hiiragi283.material.util.OptionalUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Consumer;

/**
 * Thanks to SkyTheory!
 * <a href="https://github.com/SkyTheory/SkyTheoryLib/blob/1.12.2/java/skytheory/lib/network/tile/TileSyncHandler.java">: Source</a>
 */

@ParametersAreNonnullByDefault
public abstract class HiiragiNetworkManager {

    public static SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(RMReference.MOD_ID);

    public static void register() {
        INSTANCE.registerMessage(
                (message, ctx) -> syncMessage(Minecraft.getMinecraft().world, message.pos, message),
                HiiragiMessage.Client.class,
                0,
                Side.CLIENT
        );
        INSTANCE.registerMessage(
                (message, ctx) -> syncMessage(ctx.getServerHandler().player.world, message.pos, message),
                HiiragiMessage.Server.class,
                1,
                Side.SERVER
        );
        INSTANCE.registerMessage(
                (message, ctx) -> {
                    OptionalUtil.getTile(Minecraft.getMinecraft().world, message.pos, TileEntityModuleMachine.class)
                            .ifPresent(tile -> {
                                switch (message.tankIndex) {
                                    case 1 -> tile.tankInput1.setFluid(message.getFluidStack().orElse(null));
                                    case 2 -> tile.tankInput2.setFluid(message.getFluidStack().orElse(null));
                                    case 3 -> tile.tankOutput0.setFluid(message.getFluidStack().orElse(null));
                                    case 4 -> tile.tankOutput1.setFluid(message.getFluidStack().orElse(null));
                                    case 5 -> tile.tankOutput2.setFluid(message.getFluidStack().orElse(null));
                                    default -> tile.tankInput0.setFluid(message.getFluidStack().orElse(null));
                                }
                            });
                    return null;
                },
                HiiragiFluidMessage.class,
                2,
                Side.CLIENT
        );
    }

    @Nullable
    private static IMessage syncMessage(World world, BlockPos pos, HiiragiMessage message) {
        return syncMessage(world, pos, message, tile -> tile.readFromNBT(message.tag));
    }

    @Nullable
    private static IMessage syncMessage(World world, BlockPos pos, HiiragiMessage message, Consumer<TileEntity> consumer) {
        if (world.isBlockLoaded(pos)) OptionalUtil.getTile(world, pos).ifPresent(consumer);
        return null;
    }

}