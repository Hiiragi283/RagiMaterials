package hiiragi283.material.api.capability

import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilitySerializable
import net.minecraftforge.common.util.INBTSerializable

/**
 * Thanks to SkyTheory!
 * [: Source](https://github.com/SkyTheory/SkyTheoryLib/blob/1.12.2/java/skytheory/lib/capability/DataProvider.java)
 */

@Suppress("UNCHECKED_CAST")
class HiiragiCapabilityProvider<T>(
    private val capability: Capability<T>,
    private val instance: T,
    private val serializer: INBTSerializable<NBTTagCompound> = instance as INBTSerializable<NBTTagCompound>
) : ICapabilitySerializable<NBTTagCompound> {

    //    ICapability    //

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean = capability == this.capability

    override fun <T> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return if (hasCapability(capability, facing)) this.capability.cast(instance) else null
    }

    //    INBTSerializable<NBTTagCompound>    //

    override fun serializeNBT(): NBTTagCompound = serializer.serializeNBT()

    override fun deserializeNBT(nbt: NBTTagCompound) {
        serializer.deserializeNBT(nbt)
    }

}