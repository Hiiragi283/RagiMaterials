package ragi_materials.core.tile

import net.minecraftforge.energy.IEnergyStorage
import net.minecraftforge.fluids.capability.IFluidHandler
import net.minecraftforge.items.IItemHandler
import ragi_materials.core.capability.RagiCapabilityProvider

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