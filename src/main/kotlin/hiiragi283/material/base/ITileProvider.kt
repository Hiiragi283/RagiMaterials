package hiiragi283.material.base

import hiiragi283.material.capability.RagiCapabilityProvider
import net.minecraftforge.energy.IEnergyStorage
import net.minecraftforge.fluids.capability.IFluidHandler
import net.minecraftforge.items.IItemHandler

object ITileProvider {

    interface Inventory {

        fun createInventory(): RagiCapabilityProvider<IItemHandler>

    }

    interface Tank {

        fun createTank(): RagiCapabilityProvider<IFluidHandler>

    }

    interface Energy {

        fun createBattery(): RagiCapabilityProvider<IEnergyStorage>

    }
}