package hiiragi283.material.api.recipe;

import hiiragi283.material.api.machine.MachineContents;
import hiiragi283.material.api.machine.ModuleTrait;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Set;

@ParametersAreNonnullByDefault
public interface IMachineRecipe {

    @NotNull
    Type getType();

    @NotNull
    Set<ModuleTrait> getRequiredTraits();

    boolean matches(MachineContents machineContents);

    //    Inputs    //

    @NotNull
    List<List<ItemStack>> getInputItems();

    @NotNull
    List<FluidStack> getInputFluids();

    //    Outputs    //

    @NotNull
    List<List<ItemStack>> getOutputItems();

    @NotNull
    List<FluidStack> getOutputFluids();

    enum Type {
        BEND,
        CRUSH,
        CUT,
        PULVERISE,
        SMELT,
        WIRE
    }

}