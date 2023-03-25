package hiiragi283.ragi_materials.capability

import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.energy.IEnergyStorage

class RagiEnergyStorage(private val capacity: Int, private val maxIn: Int = capacity, private val maxOut: Int = capacity, private var stored: Int = 0) : IEnergyStorage, INBTSerializable<NBTTagCompound> {

    //    IEnergyStorage    //

    override fun receiveEnergy(maxReceive: Int, simulate: Boolean): Int {
        if (!canReceive()) return 0
        val energyReceived = (capacity - energyStored).coerceAtMost(maxIn.coerceAtMost(maxReceive))
        if (!simulate) energyStored += energyReceived
        return energyReceived
    }

    override fun extractEnergy(maxExtract: Int, simulate: Boolean): Int {
        if (!canExtract()) return 0
        val energyExtracted = stored.coerceAtMost(maxOut.coerceAtMost(maxExtract))
        if (!simulate) stored -= energyExtracted
        return energyExtracted
    }

    override fun getEnergyStored() = stored

    fun setEnergyStored(energy: Int) {
        stored = energy.coerceAtMost(capacity)
    }

    override fun getMaxEnergyStored() = capacity

    override fun canExtract() = maxOut > 0

    override fun canReceive() = maxIn > 0

    //    INBTSerializable    //

    private val keyEnergy = "energy"

    override fun serializeNBT(): NBTTagCompound {
        val tag = NBTTagCompound()
        tag.setInteger(keyEnergy, stored)
        return tag
    }

    override fun deserializeNBT(tag: NBTTagCompound?) {
        stored = if (tag !== null && tag.hasKey(keyEnergy)) tag.getInteger(keyEnergy) else 0
    }
}