package hiiragi283.material.api.item;

import hiiragi283.material.api.capability.HiiragiCapability;
import hiiragi283.material.api.capability.HiiragiCapabilityProvider;
import hiiragi283.material.api.capability.machine.ItemStackMachineProperty;
import hiiragi283.material.api.material.HiiragiMaterial;
import hiiragi283.material.api.shape.HiiragiShapes;
import hiiragi283.material.util.CraftingBuilder;
import hiiragi283.material.util.HiiragiUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

public class MaterialItemCasing extends MaterialItem {

    public MaterialItemCasing() {
        super(HiiragiShapes.CASING);
    }

    @Nullable
    @Override
    @ParametersAreNonnullByDefault
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        return new HiiragiCapabilityProvider<>(HiiragiCapability.MACHINE_PROPERTY, new ItemStackMachineProperty(stack));
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

}