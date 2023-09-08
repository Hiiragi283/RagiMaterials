package hiiragi283.material.api.machine;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.Set;

public interface IModuleItem extends IMachineProperty {

    default void addTooltip(ItemStack stack, List<String> tooltip) {
        tooltip.add("§e=== Machine Property ===");
        tooltip.add(I18n.format("tips.ragi_materials.module.energy_capacity", getEnergyCapacity()));
        tooltip.add(I18n.format("tips.ragi_materials.module.energy_rate", getEnergyRate(stack)));
        tooltip.add(I18n.format("tips.ragi_materials.module.fluid_slot", getFluidSlotCounts(stack)));
        tooltip.add(I18n.format("tips.ragi_materials.module.item_slots", getItemSlotCounts(stack)));
        tooltip.add(I18n.format("tips.ragi_materials.module.process_time", getProcessTime(stack)));
    }

    int getProcessTime(ItemStack stack);

    int getEnergyRate(ItemStack stack);

    int getItemSlotCounts(ItemStack stack);

    int getFluidSlotCounts(ItemStack stack);

    Set<ModuleTraits> getModuleTraits(ItemStack stack);

}