package hiiragi283.ragi_materials.tile

import hiiragi283.ragi_materials.api.capability.heat.CapabilityHeat
import hiiragi283.ragi_materials.api.capability.heat.IHeatStorage
import net.minecraft.util.ITickable
import net.minecraftforge.common.capabilities.Capability

class TileTransferHeat : TileTransferBase<IHeatStorage>(108), ITickable {

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

    override fun getCapabilityType(): Capability<IHeatStorage> = CapabilityHeat.HEAT

}