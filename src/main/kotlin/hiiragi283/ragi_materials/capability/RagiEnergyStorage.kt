package hiiragi283.ragi_materials.capability

import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.energy.CapabilityEnergy
import net.minecraftforge.energy.IEnergyStorage

class RagiEnergyStorage(private val capacity: Int, private val maxIn: Int = capacity, private val maxOut: Int = capacity, private var stored: Int = 0) : IEnergyStorage, INBTSerializable<NBTTagCompound> {

    //    IEnergyStorage    //

    override fun receiveEnergy(maxReceive: Int, simulate: Boolean): Int {
        if (!canReceive()) return 0
        val energyReceived = (capacity - energyStored).coerceAtMost(maxIn.coerceAtMost(maxReceive))
        if (!simulate) energyStored += energyReceived
        return energyReceived
    }

    fun receiveEnergyFrom(tileFrom: TileEntity, facingFrom: EnumFacing?, simulate: Boolean) {
        val energyStorageFrom = tileFrom.getCapability(CapabilityEnergy.ENERGY, facingFrom)
        if (energyStorageFrom !== null && energyStorageFrom.canExtract() && this.canReceive()) {
            receiveEnergy(energyStorageFrom.extractEnergy(getFreeCapacity(), simulate), simulate)
        }
    }

    override fun extractEnergy(maxExtract: Int, simulate: Boolean): Int {
        if (!canExtract()) return 0
        val energyExtracted = stored.coerceAtMost(maxOut.coerceAtMost(maxExtract))
        if (!simulate) stored -= energyExtracted
        return energyExtracted
    }

    fun extractEnergyTo(tileTo: TileEntity, facingTo: EnumFacing?, simulate: Boolean) {
        val energyStorageTo = tileTo.getCapability(CapabilityEnergy.ENERGY, facingTo)
        if (energyStorageTo !== null && energyStorageTo.canReceive() && this.canExtract()) {
            extractEnergy(energyStorageTo.receiveEnergy(capacity, simulate), simulate)
        }
    }

    override fun getEnergyStored() = stored

    fun setEnergyStored(energy: Int) {
        stored = energy.coerceAtMost(capacity)
    }

    override fun getMaxEnergyStored() = capacity

    override fun canExtract() = maxOut > 0 && stored in 1 .. capacity

    override fun canReceive() = maxIn > 0 && stored in 0 until capacity

    fun getFreeCapacity() = capacity - energyStored

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