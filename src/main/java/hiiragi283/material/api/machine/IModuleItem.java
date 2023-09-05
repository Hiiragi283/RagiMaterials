package hiiragi283.material.api.machine;

import net.minecraft.item.ItemStack;

public interface IModuleItem extends IModuleBase {

    int getProcessTime(ItemStack stack);

    int getEnergyRate(ItemStack stack);

    int getEnergyCapacity(ItemStack stack);

    int getItemSlotCounts(ItemStack stack);

    int getFluidSlotCounts(ItemStack stack);

}