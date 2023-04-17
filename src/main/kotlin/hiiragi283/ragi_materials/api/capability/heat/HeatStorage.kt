package hiiragi283.ragi_materials.api.capability.heat

import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.util.INBTSerializable

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

    override fun extractHeat(maxExtract: Int, simulate: Boolean): Int {
        if (!canExtract()) return 0
        val heatExtracted = stored.coerceAtMost(maxOut.coerceAtMost(maxExtract))
        if (!simulate) stored -= heatExtracted
        return heatExtracted
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