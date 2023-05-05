package ragi_materials.main.tile

import mekanism.api.gas.GasStack
import mekanism.api.gas.IGasHandler
import mekanism.common.capabilities.Capabilities
import net.minecraft.util.ITickable
import net.minecraftforge.common.capabilities.Capability

class TileTransferGas : TileTransferBase<IGasHandler>(), ITickable {

    override var storageFrom: IGasHandler? = null
    override var storageTo: IGasHandler? = null

    //    TileTransferBase    //

    override fun doProcess() {
        if (storageFrom !== null && storageTo !== null) {
            for (tankInfoFrom in storageFrom!!.tankInfo) {
                for (tankInfoTo in storageTo!!.tankInfo) {
                    val stackFrom = tankInfoFrom.gas
                    val stackTo = tankInfoTo.gas
                    //送り元に気体がある場合のみ動作する
                    if (stackFrom != null) {
                        //送り先に気体がない場合，最大限まで搬入する
                        if (stackTo == null) transferGas(stackFrom)
                        //双方に気体がある場合
                        else {
                            //気体が同じ場合
                            if (stackFrom.gas.name == stackTo.gas.name) transferGas(stackFrom)
                        }
                    }
                }
            }
        }
    }

    private fun transferGas(stackFrom: GasStack) {
        if (storageFrom!!.canDrawGas(getFacing(), stackFrom.gas) && storageTo!!.canReceiveGas(getFacing().opposite, stackFrom.gas)) {
            if (storageFrom!!.drawGas(getFacing(), 1, false) != null && storageTo!!.receiveGas(getFacing().opposite, stackFrom, false) > 0) {
                storageFrom!!.drawGas(getFacing(), storageTo!!.receiveGas(getFacing().opposite, stackFrom, true), true)
            }
        }
    }

    override fun getCapabilityType(): Capability<IGasHandler> = Capabilities.GAS_HANDLER_CAPABILITY

}