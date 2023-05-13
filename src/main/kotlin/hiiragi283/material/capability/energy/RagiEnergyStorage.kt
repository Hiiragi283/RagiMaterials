package hiiragi283.material.capability.energy

import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.energy.CapabilityEnergy
import net.minecraftforge.energy.IEnergyStorage

class RagiEnergyStorage(
    private val capacity: Int,
    private val maxIn: Int = capacity,
    private val maxOut: Int = capacity,
    private var stored: Int = 0
) : IEnergyStorage, INBTSerializable<NBTTagCompound> {

    //    IEnergyStorage    //

    override fun receiveEnergy(maxReceive: Int, simulate: Boolean): Int {
        if (!canReceive()) return 0
        val energyReceived = (capacity - energyStored).coerceAtMost(maxIn.coerceAtMost(maxReceive))
        if (!simulate) energyStored += energyReceived
        return energyReceived
    }

    fun receiveEnergyFrom(storageFrom: IEnergyStorage, simulate: Boolean) {
        if (storageFrom.canExtract() && this.canReceive()) {
            receiveEnergy(storageFrom.extractEnergy(getFreeCapacity(), simulate), simulate)
        }
    }

    fun receiveEnergyFrom(tileFrom: TileEntity, facingFrom: EnumFacing?, simulate: Boolean) {
        tileFrom.getCapability(CapabilityEnergy.ENERGY, facingFrom)?.let {
            receiveEnergyFrom(it, simulate)
        }
    }

    override fun extractEnergy(maxExtract: Int, simulate: Boolean): Int {
        if (!canExtract()) return 0
        val energyExtracted = stored.coerceAtMost(maxOut.coerceAtMost(maxExtract))
        if (!simulate) stored -= energyExtracted
        return energyExtracted
    }

    fun extractEnergyTo(storageTo: IEnergyStorage, simulate: Boolean) {
        if (storageTo.canReceive() && this.canExtract()) {
            extractEnergy(storageTo.receiveEnergy(capacity, simulate), simulate)
        }
    }

    fun extractEnergyTo(tileTo: TileEntity, facingTo: EnumFacing?, simulate: Boolean) {
        tileTo.getCapability(CapabilityEnergy.ENERGY, facingTo)?.let {
            extractEnergyTo(it, simulate)
        }
    }

    override fun getEnergyStored(): Int = stored

    fun setEnergyStored(energy: Int) {
        stored = energy.coerceAtMost(capacity)
    }

    override fun getMaxEnergyStored(): Int = capacity

    override fun canExtract(): Boolean = maxOut > 0 && stored in 1..capacity

    override fun canReceive(): Boolean = maxIn > 0 && stored in 0 until capacity

    fun getFreeCapacity(): Int = capacity - energyStored

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