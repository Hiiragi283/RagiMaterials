package hiiragi283.material.api.item;

import hiiragi283.material.api.block.MaterialBlockCasing;
import hiiragi283.material.api.machine.IMachineProperty;
import hiiragi283.material.api.machine.IModuleItem;
import hiiragi283.material.api.machine.ModuleTraits;
import hiiragi283.material.api.material.HiiragiMaterial;
import net.minecraft.item.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class MaterialItemBlockCasing extends MaterialItemBlock implements IModuleItem {

    public MaterialItemBlockCasing(MaterialBlockCasing block) {
        super(block);
    }

    //    IModuleItem    //

    @Override
    public int getProcessTime(ItemStack stack) {
        return HiiragiMaterial.REGISTRY_INDEX.getValue(stack.getMetadata())
                .flatMap(HiiragiMaterial::getMachineProperty)
                .map(IMachineProperty::getProcessTime).orElse(IMachineProperty.DEFAULT_PROCESS);
    }

    @Override
    public int getEnergyRate(ItemStack stack) {
        return HiiragiMaterial.REGISTRY_INDEX.getValue(stack.getMetadata())
                .flatMap(HiiragiMaterial::getMachineProperty)
                .map(IMachineProperty::getEnergyRate).orElse(IMachineProperty.DEFAULT_RATE);
    }

    @Override
    public int getItemSlotCounts(ItemStack stack) {
        return HiiragiMaterial.REGISTRY_INDEX.getValue(stack.getMetadata())
                .flatMap(HiiragiMaterial::getMachineProperty)
                .map(IMachineProperty::getItemSlotCounts).orElse(IMachineProperty.DEFAULT_ITEM);
    }

    @Override
    public int getFluidSlotCounts(ItemStack stack) {
        return HiiragiMaterial.REGISTRY_INDEX.getValue(stack.getMetadata())
                .flatMap(HiiragiMaterial::getMachineProperty)
                .map(IMachineProperty::getFluidSlotCounts).orElse(IMachineProperty.DEFAULT_FLUID);
    }

    @Override
    public Set<ModuleTraits> getModuleTraits(ItemStack stack) {
        return HiiragiMaterial.REGISTRY_INDEX.getValue(stack.getMetadata())
                .flatMap(HiiragiMaterial::getMachineProperty)
                .map(IMachineProperty::getModuleTraits).orElse(new HashSet<>());
    }

}