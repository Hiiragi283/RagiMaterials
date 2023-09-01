package hiiragi283.material.api.tile;

import hiiragi283.material.api.capability.HiiragiCapabilityProvider;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

public abstract class HiiragiProvider {

    public interface Inventory {

        @NotNull
        HiiragiCapabilityProvider<IItemHandler> createInventory();

    }

    public interface Tank {

        @NotNull
        HiiragiCapabilityProvider<IFluidHandler> createTank();

    }

    public interface Battery {

        @NotNull
        HiiragiCapabilityProvider<IEnergyStorage> createBattery();

    }

}