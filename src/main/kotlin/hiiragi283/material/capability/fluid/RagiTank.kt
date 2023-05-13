package hiiragi283.material.capability.fluid

import hiiragi283.material.capability.EnumIOType
import hiiragi283.material.capability.ICapabilityIO
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.FluidTank

open class RagiTank(cap: Int) : FluidTank(cap), ICapabilityIO<RagiTank>, INBTSerializable<NBTTagCompound> {

    //    ICapabilityIO    //

    override var ioType = EnumIOType.INPUT

    override fun getIOType(): EnumIOType = ioType

    override fun setIOType(type: EnumIOType): RagiTank = also { ioType = type }

    //    INBTSerializable    //

    override fun serializeNBT() = NBTTagCompound().also { fluid?.writeToNBT(it) }

    override fun deserializeNBT(tag: NBTTagCompound?) {
        fluid = FluidStack.loadFluidStackFromNBT(tag ?: NBTTagCompound())
    }

}