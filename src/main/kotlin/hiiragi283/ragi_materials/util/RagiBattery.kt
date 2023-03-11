package hiiragi283.ragi_materials.util

import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.energy.EnergyStorage

class RagiBattery(capacity: Int, maxReceive: Int, maxExtract: Int, var energyNow: Int = 0) : EnergyStorage(capacity, maxReceive, maxExtract, energyNow), INBTSerializable<NBTTagCompound> {

    private val keyEnergy = "energy"

    //    INBTSerializable    //

    override fun serializeNBT(): NBTTagCompound {
        val tag = NBTTagCompound()
        tag.setInteger(keyEnergy, this.energyNow)
        return tag
    }

    override fun deserializeNBT(tag: NBTTagCompound?) {
        this.energyNow = if (tag !== null && tag.hasKey(keyEnergy)) tag.getInteger(keyEnergy) else 0
    }
}