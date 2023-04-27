package ragi_materials.main.tile

import net.minecraft.util.ITickable
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.capability.CapabilityFluidHandler
import net.minecraftforge.fluids.capability.IFluidHandler
import net.minecraftforge.fluids.capability.IFluidTankProperties

class TileTransferFluid : TileTransferBase<IFluidHandler>(), ITickable {

    override var storageFrom: IFluidHandler? = null
    override var storageTo: IFluidHandler? = null

    //    TileTransferBase    //

    override fun doProcess() {
        if (storageFrom !== null && storageTo !== null) {
            for (propertyFrom in storageFrom!!.tankProperties) {
                for (propertyTo in storageTo!!.tankProperties) {
                    val contentFrom = propertyFrom.contents
                    val contentTo = propertyTo.contents
                    //送り元に液体がある場合のみ動作する
                    if (contentFrom != null) {
                        //送り先に液体がない場合，最大限まで搬入する
                        if (contentTo == null) transferFluid(propertyFrom, propertyTo, contentFrom)
                        //双方に液体がある場合
                        else {
                            //液体が同じ場合
                            if (contentFrom.fluid.name == contentTo.fluid.name) transferFluid(propertyFrom, propertyTo, contentFrom)
                        }
                    }
                }
            }
        }
    }

    private fun transferFluid(propertyFrom: IFluidTankProperties, propertyTo: IFluidTankProperties, contentFrom: FluidStack) {
        if (propertyFrom.canDrain() && propertyTo.canFill()) {
            if (storageFrom!!.drain(1, false) != null && storageTo!!.fill(contentFrom, false) > 0) {
                storageFrom!!.drain(storageTo!!.fill(contentFrom, true), true)
            }
        }
    }

    override fun getCapabilityType(): Capability<IFluidHandler> = CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY

}