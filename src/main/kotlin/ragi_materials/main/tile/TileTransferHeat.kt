package ragi_materials.main.tile

import net.minecraft.util.ITickable
import net.minecraftforge.common.capabilities.Capability
import ragi_materials.core.RagiRegistry
import ragi_materials.core.capability.heat.IHeatStorage

class TileTransferHeat : TileTransferBase<IHeatStorage>(), ITickable {

    override var storageFrom: IHeatStorage? = null
    override var storageTo: IHeatStorage? = null

    //    TileTransferBase    //

    override fun doProcess() {
        if (storageFrom !== null && storageTo !== null) {
            if (storageFrom!!.canExtract() && storageTo!!.canReceive()) {
                if (storageTo!!.receiveHeat(1, true) > 0 && storageFrom!!.extractHeat(1, true) > 0)
                    storageFrom!!.extractHeat(storageTo!!.receiveHeat(storageFrom!!.getMaxHeatStored(), false), false)
            }
        }
    }

    override fun getCapabilityType(): Capability<IHeatStorage> = RagiRegistry.HEAT

}