package hiiragi283.material.api.capability.fluid;

import hiiragi283.material.api.capability.FaceControllable;
import hiiragi283.material.api.capability.IOControllable;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class HiiragiFluidTank extends FluidTank implements FaceControllable, IOControllable {

    @NotNull
    private final IOControllable.Type ioType;
    @Nullable
    private final EnumFacing facing;

    public HiiragiFluidTank(int capacity, @NotNull IOControllable.Type ioType, @Nullable EnumFacing facing) {
        super(capacity);
        this.ioType = ioType;
        this.facing = facing;
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

    //    FaceControllable    //

    @Override
    public @Nullable EnumFacing getFacing() {
        return facing;
    }

    //    IOControllable    //

    @Override
    public @NotNull Type getIOType() {
        return ioType;
    }

}