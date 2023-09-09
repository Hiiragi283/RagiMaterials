package hiiragi283.material.api.module;

import hiiragi283.material.api.machine.IMachineProperty;
import hiiragi283.material.api.machine.ModuleTraits;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ParametersAreNonnullByDefault
public interface IModuleItem {

    default void addTooltip(ItemStack stack, List<String> tooltip) {
        tooltip.add("§e=== Machine Property ===");
        tooltip.add(I18n.format("tips.ragi_materials.module.energy_capacity", getEnergyCapacity(stack)));
        tooltip.add(I18n.format("tips.ragi_materials.module.energy_rate", getEnergyRate(stack)));
        tooltip.add(I18n.format("tips.ragi_materials.module.fluid_slot", getFluidSlotCounts(stack)));
        tooltip.add(I18n.format("tips.ragi_materials.module.item_slots", getItemSlotCounts(stack)));
        tooltip.add(I18n.format("tips.ragi_materials.module.process_time", getProcessTime(stack)));
    }

    default int getProcessTime(ItemStack stack) {
        return 100;
    }

    default int getEnergyRate(ItemStack stack) {
        return 32;
    }

    default int getEnergyCapacity(ItemStack stack) {
        return getProcessTime(stack) * getEnergyRate(stack) * 5;
    }

    default int getItemSlotCounts(ItemStack stack) {
        return 1;
    }

    default int getFluidSlotCounts(ItemStack stack) {
        return 0;
    }

    default Set<ModuleTraits> getModuleTraits(ItemStack stack) {
        return new HashSet<>();
    }

    default IMachineProperty toMachineProperty(ItemStack stack) {
        return IMachineProperty.of(property -> {
            property.processTime = this.getProcessTime(stack);
            property.energyRate = this.getEnergyRate(stack);
            property.itemSlots = this.getItemSlotCounts(stack);
            property.fluidSlots = this.getFluidSlotCounts(stack);
        });
    }

}