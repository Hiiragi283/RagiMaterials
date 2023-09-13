package hiiragi283.material.api.machine

import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.util.INBTSerializable

interface IMachineProperty : INBTSerializable<NBTTagCompound> {

    fun getProcessTime(): Int = 100

    fun getEnergyRate(): Int = 32

    fun getEnergyCapacity(): Int = getProcessTime() * getEnergyRate() * 5

    fun getItemSlots(): Int = 1

    fun getFluidSlots(): Int = 0

    fun getModuleTraits(): Set<ModuleTrait> = setOf()

    //    INBTSerializable    //
    override fun serializeNBT(): NBTTagCompound {
        return NBTTagCompound().also { tag: NBTTagCompound ->
            tag.setInteger(KEY_PROCESS, getProcessTime())
            tag.setInteger(KEY_RATE, getEnergyRate())
            tag.setInteger(KEY_ITEM, getItemSlots())
            tag.setInteger(KEY_FLUID, getFluidSlots())
        }
    }

    class Impl : IMachineProperty {

        var processTime: Int = 100
        var energyRate: Int = 32
        var itemSlots: Int = 1
        var fluidSlots: Int = 0

        //    INBTSerializable    //
        override fun deserializeNBT(nbt: NBTTagCompound) {
            if (nbt.hasKey(KEY_PROCESS)) processTime = nbt.getInteger(KEY_PROCESS)
            if (nbt.hasKey(KEY_RATE)) energyRate = nbt.getInteger(KEY_RATE)
            if (nbt.hasKey(KEY_ITEM)) itemSlots = nbt.getInteger(KEY_ITEM)
            if (nbt.hasKey(KEY_FLUID)) fluidSlots = nbt.getInteger(KEY_FLUID)
        }
    }

    companion object {

        fun of(nbt: NBTTagCompound): IMachineProperty = of {
            if (nbt.hasKey(KEY_PROCESS))
                this.processTime = nbt.getInteger(KEY_PROCESS)
            if (nbt.hasKey(KEY_RATE))
                this.energyRate = nbt.getInteger(KEY_RATE)
            if (nbt.hasKey(KEY_ITEM))
                this.itemSlots = nbt.getInteger(KEY_ITEM)
            if (nbt.hasKey(KEY_FLUID))
                this.fluidSlots = nbt.getInteger(KEY_FLUID)
        }

        fun of(init: Impl.() -> Unit): IMachineProperty {
            val property = Impl()
            property.init()
            return property
        }

        const val KEY_PROCESS = "ProcessTime"
        const val KEY_RATE = "EnergyRate"
        const val KEY_ITEM = "ItemSlots"
        const val KEY_FLUID = "FluidSlots"

    }

}