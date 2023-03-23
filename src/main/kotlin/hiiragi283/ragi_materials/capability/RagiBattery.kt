package hiiragi283.ragi_materials.capability

import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.energy.IEnergyStorage

class RagiBattery(val stack: ItemStack, private val capacity: Int, private val maxIn: Int = capacity, private val maxOut: Int = capacity): IEnergyStorage {

    //    IEnergyStorage    //

    private val keyEnergy = "energy"

    override fun receiveEnergy(maxReceive: Int, simulate: Boolean): Int {
        if (!canReceive()) return 0
        val energyReceived = (capacity - energyStored).coerceAtMost(maxIn.coerceAtMost(maxReceive))
        if (!simulate) energyStored += energyReceived
        return energyReceived
    }

    override fun extractEnergy(maxExtract: Int, simulate: Boolean): Int {
        TODO("Not yet implemented")
    }

    override fun getEnergyStored(): Int {
        val tag = stack.tagCompound?: NBTTagCompound()
        return if (tag.hasKey(keyEnergy)) tag.getInteger(keyEnergy) else 0
    }

    fun setEnergyStored(energy: Int) {
        if (stack.tagCompound == null) stack.tagCompound = NBTTagCompound()
        stack.tagCompound!!.setInteger(keyEnergy, energy)
    }

    override fun getMaxEnergyStored() = capacity

    override fun canExtract() = maxOut > 0

    override fun canReceive() = maxIn > 0
}