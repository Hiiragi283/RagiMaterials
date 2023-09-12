package hiiragi283.material.api.item;

import hiiragi283.material.api.machine.IMachineProperty;
import hiiragi283.material.api.machine.ModuleTrait;
import hiiragi283.material.api.material.HiiragiMaterial;
import hiiragi283.material.api.module.IModuleItem;
import hiiragi283.material.api.shape.HiiragiShapes;
import hiiragi283.material.util.CraftingBuilder;
import hiiragi283.material.util.HiiragiUtil;
import net.minecraft.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Set;

@ParametersAreNonnullByDefault
public class MaterialItemCasing extends MaterialItem implements IModuleItem {

    public MaterialItemCasing() {
        super(HiiragiShapes.CASING);
    }

    //    MaterialItem    //

    @Override
    protected void getRecipe(ITEM item, HiiragiMaterial material) {
        new CraftingBuilder(item.asItemStack(material))
                .setPattern("A A", "ABA", "A A")
                .setIngredient('A', HiiragiShapes.PLATE.getOreDict(material))
                .setIngredient('B', HiiragiShapes.FRAME.getOreDict(material))
                .build();
    }

    @Override
    protected void getModel(ITEM item) {
        HiiragiUtil.setModelSame(item.getObject());
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