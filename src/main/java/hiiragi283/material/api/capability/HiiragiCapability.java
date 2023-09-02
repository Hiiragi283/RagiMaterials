package hiiragi283.material.api.capability;

import hiiragi283.material.api.capability.machine.IMachineProperty;
import hiiragi283.material.api.capability.machine.MachineProperty;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public abstract class HiiragiCapability {

    @CapabilityInject(IMachineProperty.class)
    public static Capability<IMachineProperty> MACHINE_PROPERTY;

    public static void register() {

        CapabilityManager.INSTANCE.register(IMachineProperty.class, new HiiragiStorage<>(), MachineProperty::new);

    }

}