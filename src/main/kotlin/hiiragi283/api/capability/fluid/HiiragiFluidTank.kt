package hiiragi283.api.capability.fluid

import hiiragi283.api.capability.CapabilityIO
import hiiragi283.api.capability.IOType
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.FluidTank

open class HiiragiFluidTank(cap: Int) : FluidTank(cap), CapabilityIO<HiiragiFluidTank>, INBTSerializable<NBTTagCompound> {

    //    ICapabilityIO    //

    override var ioType: IOType = IOType.INPUT

    override fun getIOType(): IOType = ioType

    override fun setIOType(type: IOType): HiiragiFluidTank = also { ioType = type }

    //    INBTSerializable    //

    override fun serializeNBT() = NBTTagCompound().also { fluid?.writeToNBT(it) }

    override fun deserializeNBT(tag: NBTTagCompound?) {
        fluid = FluidStack.loadFluidStackFromNBT(tag ?: NBTTagCompound())
    }

}