package hiiragi283.material.network;

import hiiragi283.material.util.OptionalUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import org.jetbrains.annotations.Nullable;

/**
 * Thanks to SkyTheory!
 * <a href="https://github.com/SkyTheory/SkyTheoryLib/blob/1.12.2/java/skytheory/lib/network/tile/TileSyncHandler.java">: Source</a>
 */

public abstract class HiiragiMessageHandlers {

    //Server -> Client
    public static IMessageHandler<HiiragiMessage, IMessage> CLIENT_HANDLER = (message, ctx) -> syncMessage(Minecraft.getMinecraft().world, message.pos, message);

    //Client -> Server
    public static IMessageHandler<HiiragiMessage, IMessage> SERVER_HANDLER = (message, ctx) -> syncMessage(ctx.getServerHandler().player.world, message.pos, message);

    @Nullable
    private static IMessage syncMessage(World world, BlockPos pos, HiiragiMessage message) {
        if (world.isBlockLoaded(pos)) OptionalUtil.getTile(world, pos).ifPresent(tile -> tile.readFromNBT(message.tag));
        return null;
    }

}