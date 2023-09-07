package hiiragi283.material.util;

import com.github.bsideup.jabel.Desugar;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

import java.util.Optional;

public class OptionalFluidHandler {

    private final IFluidHandler handler;

    public OptionalFluidHandler(IFluidHandler handler) {
        this.handler = handler;
    }

    public OptionalFluidTankProperties[] getTankProperties() {
        return new OptionalFluidTankProperties[0];
    }

    public Optional<FluidStack> drain(FluidStack resource, boolean doDrain) {
        return Optional.ofNullable(handler.drain(resource, doDrain));
    }

    public Optional<FluidStack> drain(int maxDrain, boolean doDrain) {
        return Optional.ofNullable(handler.drain(maxDrain, doDrain));
    }

    @Desugar
    public record OptionalFluidTankProperties(IFluidTankProperties properties) {

        public Optional<FluidStack> getContents() {
            return Optional.ofNullable(properties.getContents());
        }

    }

}