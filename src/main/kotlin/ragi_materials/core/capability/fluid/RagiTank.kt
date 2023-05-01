package ragi_materials.core.capability.fluid

import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.FluidTank
import ragi_materials.core.capability.EnumIOType
import ragi_materials.core.capability.ICapabilityIO
import ragi_materials.core.util.getMaterialFromName

open class RagiTank(cap: Int) : FluidTank(cap), ICapabilityIO<RagiTank>, INBTSerializable<NBTTagCompound> {

    //    FluidTank    //

    //液体の名前から取得した素材が空でないならtrue
    override fun canFillFluidType(fluid: FluidStack?): Boolean = if (fluid !== null) !getMaterialFromName(fluid.fluid.name).isEmpty() else false

    //    ICapabilityIO    //

    override var ioType = EnumIOType.INPUT

    override fun getIOType() = ioType

    override fun setIOType(type: EnumIOType): RagiTank = also { ioType = type }

    //    INBTSerializable    //

    override fun serializeNBT() = NBTTagCompound().also { fluid?.writeToNBT(it) }

    override fun deserializeNBT(tag: NBTTagCompound?) {
        fluid = FluidStack.loadFluidStackFromNBT(tag ?: NBTTagCompound())
    }

}