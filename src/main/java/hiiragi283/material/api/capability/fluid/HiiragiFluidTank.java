package hiiragi283.material.api.capability.fluid;

import hiiragi283.material.api.capability.IOControllable;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

@ParametersAreNonnullByDefault
public class HiiragiFluidTank extends FluidTank implements IOControllable {

    @NotNull
    private final IOControllable.Type ioType;

    public HiiragiFluidTank(int capacity, IOControllable.Type ioType) {
        super(capacity);
        this.ioType = ioType;
    }

    public void clear() {
        this.fluid = null;
    }

    public Optional<FluidStack> getFluidOptional() {
        return this.fluid != null ? Optional.of(this.fluid) : Optional.empty();
    }

    public boolean isEmpty() {
        return this.fluid == null;
    }

    //    IOControllable    //

    @NotNull
    @Override
    public Type getIOType() {
        return ioType;
    }

}