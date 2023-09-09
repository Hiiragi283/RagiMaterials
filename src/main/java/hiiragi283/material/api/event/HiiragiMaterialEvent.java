package hiiragi283.material.api.event;

import hiiragi283.material.api.material.HiiragiMaterial;
import net.minecraftforge.fml.common.eventhandler.Event;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public abstract class HiiragiMaterialEvent extends Event {

    private final HiiragiMaterial material;

    public HiiragiMaterialEvent(HiiragiMaterial material) {
        this.material = material;
    }

    public HiiragiMaterial getMaterial() {
        return material;
    }

}