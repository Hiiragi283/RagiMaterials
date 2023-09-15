package hiiragi283.material.api.capability.energy

import hiiragi283.material.util.HiiragiNBTKey
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.energy.CapabilityEnergy
import net.minecraftforge.energy.IEnergyStorage

class HiiragiEnergyStorage(
    var capacity: Int,
    val maxIn: Int = capacity,
    val maxOut: Int = capacity,
    var stored: Int = 0
) : IEnergyStorage, INBTSerializable<NBTTagCompound> {

    //    IEnergyStorage    //

    override fun receiveEnergy(maxReceive: Int, simulate: Boolean): Int {
        if (!canReceive()) return 0
        val energyReceived = (capacity - energyStored).coerceAtMost(maxIn.coerceAtMost(maxReceive))
        if (!simulate) stored += energyReceived
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

    override fun getMaxEnergyStored(): Int = capacity

    override fun canExtract(): Boolean = maxOut > 0 && stored in 1..capacity

    override fun canReceive(): Boolean = maxIn > 0 && stored in 0 until capacity

    fun getFreeCapacity(): Int = capacity - stored

    //    INBTSerializable    //

    override fun serializeNBT(): NBTTagCompound {
        return NBTTagCompound().also { tag ->
            tag.setInteger(HiiragiNBTKey.AMOUNT, stored)
            tag.setInteger(HiiragiNBTKey.CAPACITY, capacity)
        }
    }

    override fun deserializeNBT(tag: NBTTagCompound) {
        if (tag.hasKey(HiiragiNBTKey.AMOUNT)) stored = tag.getInteger(HiiragiNBTKey.AMOUNT)
        if (tag.hasKey(HiiragiNBTKey.CAPACITY)) stored = tag.getInteger(HiiragiNBTKey.CAPACITY)
    }

}