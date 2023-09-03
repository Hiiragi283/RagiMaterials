package hiiragi283.material.api.capability.machine;

import hiiragi283.material.util.HiiragiUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;

public class ItemStackMachineProperty implements IMachineProperty, INBTSerializable<NBTTagCompound> {

    public static final String PROPERTY_KEY = "machineProperty";

    @NotNull
    protected final ItemStack container;

    public ItemStackMachineProperty(@NotNull ItemStack container) {
        this.container = container;
        this.deserializeNBT(container.getOrCreateSubCompound("machineProperty"));
    }

    protected NBTTagCompound getNBTTag() {
        return container.getOrCreateSubCompound(PROPERTY_KEY);
    }

    @Override
    public int getValue(Type type) {
        int currentValue = getNBTTag().getInteger(type.getName());
        return currentValue > 0 ? currentValue : getDefaultValue(type);
    }

    @Override
    public void setValue(Type type, int amount) {
        getNBTTag().setInteger(type.getName(), amount);
    }

    //    INBTSerializable    //

    @Override
    public NBTTagCompound serializeNBT() {
        return getNBTTag();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        for (IMachineProperty.Type type : IMachineProperty.Type.values()) {
            setValue(type, nbt.getInteger(type.getName()));
        }
    }

    public static class ContainerBlock extends ItemStackMachineProperty {

        public ContainerBlock(@NotNull ItemStack container) {
            super(container);
        }

        @Override
        protected NBTTagCompound getNBTTag() {
            return container.getOrCreateSubCompound(HiiragiUtil.BlockEntityTag)
                    .getCompoundTag(HiiragiUtil.ForgeCaps)
                    .getCompoundTag(HiiragiUtil.getLocation(HiiragiUtil.MACHINE_PROPERTY).toString());
        }

    }

}