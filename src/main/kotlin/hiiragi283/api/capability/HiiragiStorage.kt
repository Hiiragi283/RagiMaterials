package hiiragi283.api.capability

import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.Capability.IStorage
import net.minecraftforge.common.util.INBTSerializable

/**
 * Thanks to SkyTheory!
 * Source: https://github.com/SkyTheory/SkyTheoryLib/blob/1.12.2/java/skytheory/lib/capability/DataStorage.java
 */

class HiiragiStorage<T : Any> : IStorage<T> {

    override fun writeNBT(capability: Capability<T>, instance: T, side: EnumFacing): NBTBase =
        if (instance is INBTSerializable<*>) instance.serializeNBT() else NBTTagCompound()

    @Suppress("UNCHECKED_CAST")
    override fun readNBT(capability: Capability<T>, instance: T, side: EnumFacing, nbt: NBTBase) {
        (instance as INBTSerializable<NBTBase>).deserializeNBT(nbt)
    }
}