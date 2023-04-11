package hiiragi283.ragi_materials.tile

import net.minecraft.util.ITickable
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.energy.CapabilityEnergy
import net.minecraftforge.energy.IEnergyStorage

class TileTransferEnergy : TileTransferBase<IEnergyStorage>(108), ITickable {

    override var storageFrom: IEnergyStorage? = null
    override var storageTo: IEnergyStorage? = null

    //    TileTransferBase    //

    override fun doProcess() {
        if (storageFrom !== null && storageTo !== null) {
            if (storageFrom!!.canExtract() && storageTo!!.canReceive()) {
                if (storageTo!!.receiveEnergy(1, true) > 0 && storageFrom!!.extractEnergy(1, true) > 0)
                    storageFrom!!.extractEnergy(storageTo!!.receiveEnergy(storageFrom!!.maxEnergyStored, false), false)
            }
        }
    }

    override fun getCapabilityType(): Capability<IEnergyStorage> = CapabilityEnergy.ENERGY

}