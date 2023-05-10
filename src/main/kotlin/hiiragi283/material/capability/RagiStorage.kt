package hiiragi283.material.capability

import net.minecraft.nbt.NBTBase
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.Capability.IStorage
import net.minecraftforge.common.util.INBTSerializable

/**
 * Thanks to SkyTheory!
 * Source: https://github.com/SkyTheory/SkyTheoryLib/blob/1.12.2/java/skytheory/lib/capability/DataStorage.java
 */

class RagiStorage<T : Any> : IStorage<T> {

    override fun writeNBT(capability: Capability<T>, instance: T, side: EnumFacing): NBTBase? {
        return if (instance is INBTSerializable<*>) instance.serializeNBT() else null
    }

    override fun readNBT(capability: Capability<T>, instance: T, side: EnumFacing, nbt: NBTBase) {
        if (instance is INBTSerializable<*>) {
            try {
                (instance as INBTSerializable<NBTBase>).deserializeNBT(nbt)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}