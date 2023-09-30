package hiiragi283.material.tile

import hiiragi283.material.api.capability.IOControllable
import hiiragi283.material.api.capability.fluid.HiiragiFluidTank
import hiiragi283.material.api.capability.fluid.HiiragiFluidTankWrapper
import hiiragi283.material.api.tile.HiiragiTileEntity
import hiiragi283.material.api.tile.ITransferStation
import hiiragi283.material.util.HiiragiNBTUtil
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.text.TextComponentTranslation
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.FluidUtil
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.fluids.capability.IFluidHandler

class TileTransferStationFluid : HiiragiTileEntity.Tickable(100), ITransferStation {

    override fun getDisplayName() = TextComponentTranslation("tile.ragi_materials.station_fluid.name")

    //    NBT    //

    override fun readFromNBT(compound: NBTTagCompound) {
        tank.deserializeNBT(compound.getCompoundTag(HiiragiNBTUtil.TANK))
        super.readFromNBT(compound)
    }

    override fun writeToNBT(compound: NBTTagCompound): NBTTagCompound {
        compound.setTag(HiiragiNBTUtil.TANK, tank.serializeNBT())
        return super.writeToNBT(compound)
    }

    //    Capability    //

    val tank = object : HiiragiFluidTank(16000, IOControllable.Type.INPUT) {
        override fun onContentsChanged() {
            syncData()
        }
    }

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean =
        capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || super.hasCapability(capability, facing)

    override fun <T> getCapability(capability: Capability<T>, facing: EnumFacing?): T? =
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(
            HiiragiFluidTankWrapper(tank)
        ) else super.getCapability(capability, facing)

    //    Tickable    //

    override fun onUpdateServer() {
        val iFluidHandler: IFluidHandler = getTerminalTile(world, pos)
            ?.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, getFacing(getState()).opposite)
            ?: return
        val result: FluidStack? = FluidUtil.tryFluidTransfer(iFluidHandler, tank, tank.capacity, false)
        if (result !== null) {
            FluidUtil.tryFluidTransfer(iFluidHandler, tank, tank.capacity, true)
        }
    }

}