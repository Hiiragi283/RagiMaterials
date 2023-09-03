package hiiragi283.material.api.item;

import hiiragi283.material.api.block.MaterialBlockCasing;
import hiiragi283.material.api.capability.HiiragiCapability;
import hiiragi283.material.api.capability.HiiragiCapabilityProvider;
import hiiragi283.material.api.capability.machine.ItemStackMachineProperty;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MaterialItemBlockCasing extends MaterialItemBlock {

    public MaterialItemBlockCasing(MaterialBlockCasing block) {
        super(block);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(@NotNull ItemStack stack, @Nullable NBTTagCompound nbt) {
        return new HiiragiCapabilityProvider<>(HiiragiCapability.MACHINE_PROPERTY, new ItemStackMachineProperty.ContainerBlock(stack));
    }

}