package hiiragi283.material.api.event;

import hiiragi283.material.api.material.HiiragiMaterial;
import hiiragi283.material.api.registry.HiiragiRegistry;
import hiiragi283.material.api.shape.HiiragiShape;
import net.minecraftforge.fml.common.eventhandler.Event;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public abstract class HiiragiRegistryEvent<T, U> extends Event {

    private final HiiragiRegistry<T, U> registry;

    public HiiragiRegistryEvent(HiiragiRegistry<T, U> registry) {
        this.registry = registry;
    }

    public HiiragiRegistry<T, U> getRegistry() {
        return registry;
    }

    //    Material    //

    public static class Material extends HiiragiRegistryEvent<String, HiiragiMaterial> {

        public Material(HiiragiRegistry<String, HiiragiMaterial> registry) {
            super(registry);
        }

    }

    //    Shape    //

    public static class Shape extends HiiragiRegistryEvent<String, HiiragiShape> {

        public Shape(HiiragiRegistry<String, HiiragiShape> registry) {
            super(registry);
        }

    }

}