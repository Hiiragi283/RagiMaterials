package hiiragi283.material.api.machine;

import hiiragi283.material.util.HiiragiUtil;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

@ParametersAreNonnullByDefault
public interface IMachineProperty extends INBTSerializable<NBTTagCompound> {

    default int getProcessTime() {
        return 100;
    }

    default int getEnergyRate() {
        return 32;
    }

    default int getEnergyCapacity() {
        return getProcessTime() * getEnergyRate() * 5;
    }

    default int getItemSlotCounts() {
        return 1;
    }

    default int getFluidSlotCounts() {
        return 0;
    }

    default Set<ModuleTrait> getModuleTraits() {
        return new HashSet<>();
    }

    String KEY_PROCESS = "ProcessTime";
    String KEY_RATE = "EnergyRate";
    String KEY_ITEM = "ItemSlots";
    String KEY_FLUID = "FluidSlots";

    static IMachineProperty of() {
        return of(HiiragiUtil.getEmptyConsumer());
    }

    static IMachineProperty of(NBTTagCompound nbt) {
        return of(property -> {
            if (nbt.hasKey(KEY_PROCESS)) property.processTime = nbt.getInteger(KEY_PROCESS);
            if (nbt.hasKey(KEY_RATE)) property.energyRate = nbt.getInteger(KEY_RATE);
            if (nbt.hasKey(KEY_ITEM)) property.itemSlots = nbt.getInteger(KEY_ITEM);
            if (nbt.hasKey(KEY_FLUID)) property.fluidSlots = nbt.getInteger(KEY_FLUID);
        });
    }

    static IMachineProperty of(Consumer<IMachineProperty.Impl> consumer) {
        var property = new IMachineProperty.Impl();
        consumer.accept(property);
        return property;
    }

    //    INBTSerializable    //

    @Override
    default NBTTagCompound serializeNBT() {
        var tag = new NBTTagCompound();
        tag.setInteger(KEY_PROCESS, getProcessTime());
        tag.setInteger(KEY_RATE, getEnergyRate());
        tag.setInteger(KEY_ITEM, getItemSlotCounts());
        tag.setInteger(KEY_FLUID, getFluidSlotCounts());
        return tag;
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

        //    INBTSerializable    //

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            if (nbt.hasKey(KEY_PROCESS)) processTime = nbt.getInteger(KEY_PROCESS);
            if (nbt.hasKey(KEY_RATE)) energyRate = nbt.getInteger(KEY_RATE);
            if (nbt.hasKey(KEY_ITEM)) itemSlots = nbt.getInteger(KEY_ITEM);
            if (nbt.hasKey(KEY_FLUID)) fluidSlots = nbt.getInteger(KEY_FLUID);
        }

    }

}