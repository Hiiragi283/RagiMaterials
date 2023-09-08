package hiiragi283.material.network;

import hiiragi283.material.RMReference;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public abstract class HiiragiNetworkManager {

    public static SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(RMReference.MOD_ID);

    public static void register() {
        INSTANCE.registerMessage(HiiragiMessageHandlers.CLIENT_HANDLER, HiiragiMessage.class, 0, Side.CLIENT);
        INSTANCE.registerMessage(HiiragiMessageHandlers.SERVER_HANDLER, HiiragiMessage.class, 1, Side.SERVER);
    }

}