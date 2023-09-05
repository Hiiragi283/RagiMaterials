package hiiragi283.material.api.capability.fluid;

import hiiragi283.material.api.capability.IOControllable;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class HiiragiFluidTank extends FluidTank implements IOControllable {

    @NotNull
    private final IOControllable.Type ioType;

    public HiiragiFluidTank(int capacity, @NotNull IOControllable.Type ioType) {
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

    @Override
    public @NotNull Type getIOType() {
        return ioType;
    }

}