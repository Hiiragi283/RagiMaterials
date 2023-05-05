package ragi_materials.core.capability.heat

import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.util.INBTSerializable
import ragi_materials.core.RagiRegistry

open class HeatStorage(var capacity: Int, private val maxIn: Int = capacity, private val maxOut: Int = capacity, var stored: Int = 0) : IHeatStorage, INBTSerializable<NBTTagCompound> {

    override fun receiveHeat(maxReceive: Int, simulate: Boolean): Int {
        if (!canReceive()) return 0
        val heatReceived = (capacity - stored).coerceAtMost(maxIn.coerceAtMost(maxReceive))
        if (!simulate) {
            stored += heatReceived
            if (stored + maxReceive > capacity) onOverheated()
        }
        return heatReceived
    }

    fun receiveHeatFrom(storageFrom: IHeatStorage, simulate: Boolean) {
        if (storageFrom.canExtract() && this.canReceive()) {
            receiveHeat(storageFrom.extractHeat(getFreeCapacity(), simulate), simulate)
        }
    }

    fun receiveHeatFrom(tileFrom: TileEntity, facingFrom: EnumFacing?, simulate: Boolean) {
        tileFrom.getCapability(RagiRegistry.HEAT, facingFrom)?.let {
            receiveHeatFrom(it, simulate)
        }
    }

    override fun extractHeat(maxExtract: Int, simulate: Boolean): Int {
        if (!canExtract()) return 0
        val heatExtracted = stored.coerceAtMost(maxOut.coerceAtMost(maxExtract))
        if (!simulate) stored -= heatExtracted
        return heatExtracted
    }

    fun extractHeatTo(storageTo: IHeatStorage, simulate: Boolean) {
        if (storageTo.canReceive() && this.canExtract()) {
            extractHeat(storageTo.receiveHeat(capacity, simulate), simulate)
        }
    }

    fun extractHeatTo(tileTo: TileEntity, facingTo: EnumFacing?, simulate: Boolean) {
        tileTo.getCapability(RagiRegistry.HEAT, facingTo)?.let {
            extractHeatTo(it, simulate)
        }
    }

    override fun getHeatStored() = stored

    override fun getMaxHeatStored() = capacity

    override fun canExtract() = maxOut > 0 && stored in 1..capacity

    override fun canReceive() = maxIn > 0 && stored in 0 until capacity

    override fun onOverheated() {}

    fun getFreeCapacity() = capacity - stored

    //    INBTSerializable    //

    private val keyHeat = "heat"

    override fun serializeNBT(): NBTTagCompound {
        val tag = NBTTagCompound()
        tag.setInteger(keyHeat, stored)
        return tag
    }

    override fun deserializeNBT(tag: NBTTagCompound?) {
        stored = if (tag !== null && tag.hasKey(keyHeat)) tag.getInteger(keyHeat) else 0
    }

}