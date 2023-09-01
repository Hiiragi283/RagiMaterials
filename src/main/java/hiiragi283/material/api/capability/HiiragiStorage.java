package hiiragi283.material.api.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import org.jetbrains.annotations.Nullable;

/**
 * Thanks to SkyTheory!
 * <a href="https://github.com/SkyTheory/SkyTheoryLib/blob/1.12.2/java/skytheory/lib/capability/DataStorage.java">: Source</a>
 */

public class HiiragiStorage<T> implements Capability.IStorage<T> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<T> capability, T instance, EnumFacing side) {
        return instance instanceof INBTSerializable<?> ? ((INBTSerializable<?>) instance).serializeNBT() : new NBTTagCompound();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void readNBT(Capability<T> capability, T instance, EnumFacing side, NBTBase nbt) {
        ((INBTSerializable<NBTBase>) instance).deserializeNBT(nbt);
    }

}