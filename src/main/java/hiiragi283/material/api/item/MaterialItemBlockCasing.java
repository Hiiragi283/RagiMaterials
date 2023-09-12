package hiiragi283.material.api.item;

import hiiragi283.material.api.block.MaterialBlockCasing;
import hiiragi283.material.api.machine.IMachineProperty;
import hiiragi283.material.api.module.IModuleItem;
import hiiragi283.material.api.machine.ModuleTrait;
import hiiragi283.material.api.material.HiiragiMaterial;
import net.minecraft.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Set;

@ParametersAreNonnullByDefault
public class MaterialItemBlockCasing extends MaterialItemBlock implements IModuleItem {

    public MaterialItemBlockCasing(MaterialBlockCasing block) {
        super(block);
    }

    //    IModuleItem    //

    @Override
    public int getProcessTime(ItemStack stack) {
        return HiiragiMaterial.REGISTRY_INDEX.getValue(stack.getMetadata())
                .flatMap(HiiragiMaterial::getMachineProperty)
                .map(IMachineProperty::getProcessTime)
                .orElse(IModuleItem.super.getProcessTime(stack));
    }

    @Override
    public int getEnergyRate(ItemStack stack) {
        return HiiragiMaterial.REGISTRY_INDEX.getValue(stack.getMetadata())
                .flatMap(HiiragiMaterial::getMachineProperty)
                .map(IMachineProperty::getEnergyRate)
                .orElse(IModuleItem.super.getEnergyRate(stack));
    }

    @Override
    public int getItemSlotCounts(ItemStack stack) {
        return HiiragiMaterial.REGISTRY_INDEX.getValue(stack.getMetadata())
                .flatMap(HiiragiMaterial::getMachineProperty)
                .map(IMachineProperty::getItemSlotCounts)
                .orElse(IModuleItem.super.getItemSlotCounts(stack));
    }

    @Override
    public int getFluidSlotCounts(ItemStack stack) {
        return HiiragiMaterial.REGISTRY_INDEX.getValue(stack.getMetadata())
                .flatMap(HiiragiMaterial::getMachineProperty)
                .map(IMachineProperty::getFluidSlotCounts)
                .orElse(IModuleItem.super.getFluidSlotCounts(stack));
    }

    @Override
    public Set<ModuleTrait> getModuleTraits(ItemStack stack) {
        return HiiragiMaterial.REGISTRY_INDEX.getValue(stack.getMetadata())
                .flatMap(HiiragiMaterial::getMachineProperty)
                .map(IMachineProperty::getModuleTraits)
                .orElse(IModuleItem.super.getModuleTraits(stack));
    }

}