package hiiragi283.material.api.capability;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Thanks to SkyTheory!
 * <a href="https://github.com/SkyTheory/SkyTheoryLib/blob/1.12.2/java/skytheory/lib/capability/DataProvider.java">: Source</a>
 */

public class HiiragiCapabilityProvider<T> implements ICapabilitySerializable<NBTTagCompound> {

    private final Capability<T> capability;
    private final T instance;
    private final INBTSerializable<NBTTagCompound> serializer;

    @SuppressWarnings("unchecked")
    public HiiragiCapabilityProvider(Capability<T> capability, T instance) {
        this(capability, instance, (INBTSerializable<NBTTagCompound>) instance);
    }

    public HiiragiCapabilityProvider(Capability<T> capability, T instance, INBTSerializable<NBTTagCompound> serializer) {
        this.capability = capability;
        this.instance = instance;
        this.serializer = serializer;
    }

    //    ICapability    //

    @Override
    public boolean hasCapability(@NotNull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability.equals(this.capability);
    }

    @Nullable
    @Override
    public <U> U getCapability(@NotNull Capability<U> capability, @Nullable EnumFacing facing) {
        return hasCapability(capability, facing) ? this.capability.cast(this.instance) : null;
    }

    //    INBTSerializable    //

    @Override
    public NBTTagCompound serializeNBT() {
        return serializer.serializeNBT();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        serializer.deserializeNBT(nbt);
    }

}