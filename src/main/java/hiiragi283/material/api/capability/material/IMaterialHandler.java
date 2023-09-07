package hiiragi283.material.api.capability.material;

import hiiragi283.material.api.material.MaterialStack;
import org.jetbrains.annotations.NotNull;

public interface IMaterialHandler {

    MaterialStack @NotNull [] getContents();

    int getCapacity();

    boolean canFill();

    boolean canDrain();

    boolean canFillMaterialType(@NotNull MaterialStack materialStack);

    boolean canDrainMaterialType(@NotNull MaterialStack materialStack);

    int fill(@NotNull MaterialStack resource, boolean doFill);

    int drain(@NotNull MaterialStack resource, boolean doDrain);

}