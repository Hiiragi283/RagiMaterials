package hiiragi283.material.tile

import hiiragi283.material.api.capability.energy.HiiragiEnergyStorage
import hiiragi283.material.api.tile.HiiragiTileEntity
import hiiragi283.material.api.transfer.TransferStation
import hiiragi283.material.util.HiiragiNBTUtil
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraft.util.text.TextComponentTranslation
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.energy.CapabilityEnergy
import net.minecraftforge.energy.IEnergyStorage

class TileTransferStationEnergy : HiiragiTileEntity.Tickable(100), TransferStation {

    override fun getDisplayName() = TextComponentTranslation("tile.ragi_materials.station_energy.name")

    //    NBT    //

    override fun readFromNBT(compound: NBTTagCompound) {
        battery.deserializeNBT(compound.getCompoundTag(HiiragiNBTUtil.BATTERY))
        super.readFromNBT(compound)
    }

    override fun writeToNBT(compound: NBTTagCompound): NBTTagCompound {
        compound.setTag(HiiragiNBTUtil.BATTERY, battery.serializeNBT())
        return super.writeToNBT(compound)
    }

    //    Capability    //

    val battery = object : HiiragiEnergyStorage(16000) {
        override fun onContentsChanged() {
            syncData()
        }
    }

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean =
        capability == CapabilityEnergy.ENERGY || super.hasCapability(capability, facing)

    override fun <T> getCapability(capability: Capability<T>, facing: EnumFacing?): T? =
        if (capability == CapabilityEnergy.ENERGY) CapabilityEnergy.ENERGY.cast(battery)
        else super.getCapability(capability, facing)

    //    Tickable    //

    override fun onUpdateServer() {
        val energyStorage: IEnergyStorage = getTerminalTile(world, pos)
            ?.getCapability(CapabilityEnergy.ENERGY, getFacing(getState()).opposite)
            ?: return
        battery.extractEnergyTo(energyStorage, false)
    }

}