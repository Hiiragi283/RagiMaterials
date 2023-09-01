package hiiragi283.material.api.event;

import hiiragi283.material.api.material.HiiragiMaterial;
import net.minecraftforge.fml.common.eventhandler.Event;

public abstract class MaterialBuilderEvent extends Event {

    private final HiiragiMaterial.Builder builder;

    public MaterialBuilderEvent(HiiragiMaterial.Builder builder) {
        this.builder = builder;
    }

    public HiiragiMaterial.Builder getBuilder() {
        return builder;
    }

    public static class Create extends MaterialBuilderEvent {

        public Create(HiiragiMaterial.Builder builder) {
            super(builder);
        }

    }

}