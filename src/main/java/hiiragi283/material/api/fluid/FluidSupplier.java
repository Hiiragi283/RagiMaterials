package hiiragi283.material.api.fluid;

import hiiragi283.material.util.HiiragiUtil;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Supplier;

public class FluidSupplier implements Supplier<Fluid> {

    private final Supplier<Fluid> fluidSupplier;
    private final Supplier<Optional<Block>> blockSupplier;
    private Fluid value;

    public FluidSupplier(String fluid, String block) {
        this(() -> FluidRegistry.getFluid(fluid), () -> HiiragiUtil.getEntry(ForgeRegistries.BLOCKS, new ResourceLocation(block)));
    }

    public FluidSupplier(Supplier<Fluid> fluidSupplier) {
        this(fluidSupplier, Optional::empty);
    }

    public FluidSupplier(Supplier<Fluid> fluidSupplier, Supplier<Optional<Block>> blockSupplier) {
        this.fluidSupplier = fluidSupplier;
        this.blockSupplier = blockSupplier;
    }

    @Override
    public @NotNull Fluid get() {
        if (value == null) {
            Fluid fluid = fluidSupplier.get();
            blockSupplier.get().ifPresent(fluid::setBlock);
            value = fluid;
        }
        return value;
    }

}