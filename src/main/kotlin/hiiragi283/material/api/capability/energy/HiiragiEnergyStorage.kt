package hiiragi283.material.api.capability.energy

import hiiragi283.material.util.HiiragiNBTUtil
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.energy.IEnergyStorage
import kotlin.math.min

open class HiiragiEnergyStorage(
    var capacity: Int,
    var stored: Int = 0
) : IEnergyStorage, INBTSerializable<NBTTagCompound> {

    //    IEnergyStorage    //

    override fun receiveEnergy(maxReceive: Int, simulate: Boolean): Int {
        if (!canReceive()) return 0
        val energyReceived = min((capacity - energyStored), min(capacity, maxReceive))
        if (!simulate) {
            stored += energyReceived
            onContentsChanged()
        }
        return energyReceived
    }

    fun receiveEnergyFrom(storageFrom: IEnergyStorage, simulate: Boolean) {
        if (storageFrom.canExtract() && this.canReceive()) {
            receiveEnergy(storageFrom.extractEnergy(getFreeCapacity(), simulate), simulate)
        }
    }

    override fun extractEnergy(maxExtract: Int, simulate: Boolean): Int {
        if (!canExtract()) return 0
        val energyExtracted = min(stored, min(capacity, maxExtract))
        if (!simulate) {
            stored -= energyExtracted
            onContentsChanged()
        }
        return energyExtracted
    }

    fun extractEnergyTo(storageTo: IEnergyStorage, simulate: Boolean) {
        if (storageTo.canReceive() && this.canExtract()) {
            extractEnergy(storageTo.receiveEnergy(capacity, simulate), simulate)
        }
    }

    override fun getEnergyStored(): Int = stored

    override fun getMaxEnergyStored(): Int = capacity

    override fun canExtract(): Boolean = stored > 0

    override fun canReceive(): Boolean = getFreeCapacity() > 0

    open fun getFreeCapacity(): Int = capacity - stored

    open fun onContentsChanged() {}

    //    INBTSerializable    //

    override fun serializeNBT(): NBTTagCompound {
        return NBTTagCompound().also { tag ->
            tag.setInteger(HiiragiNBTUtil.AMOUNT, stored)
            tag.setInteger(HiiragiNBTUtil.CAPACITY, capacity)
        }
    }

    override fun deserializeNBT(tag: NBTTagCompound) {
        if (tag.hasKey(HiiragiNBTUtil.AMOUNT)) stored = tag.getInteger(HiiragiNBTUtil.AMOUNT)
        if (tag.hasKey(HiiragiNBTUtil.CAPACITY)) capacity = tag.getInteger(HiiragiNBTUtil.CAPACITY)
    }

}