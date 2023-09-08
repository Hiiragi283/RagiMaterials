package hiiragi283.material.api.machine;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public interface IMachineProperty {

    default int getProcessTime() {
        return DEFAULT_PROCESS;
    }

    default int getEnergyRate() {
        return DEFAULT_RATE;
    }

    default int getEnergyCapacity() {
        return getProcessTime() * getEnergyRate() * 5;
    }

    default int getItemSlotCounts() {
        return DEFAULT_ITEM;
    }

    default int getFluidSlotCounts() {
        return DEFAULT_FLUID;
    }

    default Set<ModuleTraits> getModuleTraits() {
        return new HashSet<>();
    }

    int DEFAULT_PROCESS = 100;
    int DEFAULT_RATE = 32;
    int DEFAULT_ITEM = 1;
    int DEFAULT_FLUID = 0;

    String KEY_PROCESS = "ProcessTime";
    String KEY_RATE = "EnergyRate";
    String KEY_ITEM = "ItemSlots";
    String KEY_FLUID = "FluidSlots";

    static IMachineProperty of(Consumer<IMachineProperty.Impl> consumer) {
        var property = new IMachineProperty.Impl();
        consumer.accept(property);
        return property;
    }

    class Impl implements IMachineProperty {

        public int processTime = 20 * 5;
        public int energyRate = 32;
        public int itemSlots = 1;
        public int fluidSlots = 0;

        @Override
        public int getProcessTime() {
            return processTime;
        }

        @Override
        public int getEnergyRate() {
            return energyRate;
        }

        @Override
        public int getItemSlotCounts() {
            return itemSlots;
        }

        @Override
        public int getFluidSlotCounts() {
            return fluidSlots;
        }

    }

}