package hiiragi283.material.api.capability;

import hiiragi283.material.api.capability.material.IMaterialHandler;
import hiiragi283.material.api.capability.material.MaterialHandler;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public abstract class HiiragiCapability {

    @CapabilityInject(IMaterialHandler.class)
    public static Capability<IMaterialHandler> MATERIAL_HANDLER_CAPABILITY;

    public static void register() {
        CapabilityManager.INSTANCE.register(IMaterialHandler.class, new HiiragiStorage<>(), () -> new MaterialHandler(1000, 1));
    }

}