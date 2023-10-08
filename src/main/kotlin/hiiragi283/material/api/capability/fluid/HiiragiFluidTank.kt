package hiiragi283.material.api.capability.fluid

import hiiragi283.material.api.capability.IOControllable
import hiiragi283.material.util.HiiragiNBTUtil
import hiiragi283.material.util.getStringOrNull
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.FluidTank

open class HiiragiFluidTank(
    capacity: Int,
    override var ioType: IOControllable.Type = IOControllable.Type.GENERAL
) : FluidTank(capacity), IOControllable, INBTSerializable<NBTTagCompound> {

    override fun canFillFluidType(fluid: FluidStack?): Boolean =
        getFluid() == null || getFluid()!!.isFluidEqual(fluid)

    override fun canDrainFluidType(fluid: FluidStack?): Boolean =
        getFluid() !== null && getFluid()!!.isFluidEqual(fluid)

    //    INBTSerializable    //

    override fun serializeNBT(): NBTTagCompound {
        return NBTTagCompound().also { tag: NBTTagCompound ->
            tag.setInteger(HiiragiNBTUtil.CAPACITY, getCapacity())
            getFluid()?.writeToNBT(NBTTagCompound())?.let { tagFluid: NBTTagCompound ->
                tag.setTag(HiiragiNBTUtil.FLUID, tagFluid)
            }
            tag.setString(HiiragiNBTUtil.IO_TYPE, ioType.name)
        }
    }

    override fun deserializeNBT(nbt: NBTTagCompound) {
        setCapacity(nbt.getInteger(HiiragiNBTUtil.CAPACITY))
        setFluid(FluidStack.loadFluidStackFromNBT(nbt.getCompoundTag(HiiragiNBTUtil.FLUID)))
        nbt.getStringOrNull(HiiragiNBTUtil.IO_TYPE)?.let { name: String -> ioType = IOControllable.Type.valueOf(name) }
    }

}