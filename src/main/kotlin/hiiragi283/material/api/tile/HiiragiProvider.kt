package hiiragi283.material.api.tile

import hiiragi283.material.api.capability.HiiragiCapabilityProvider
import net.minecraftforge.energy.IEnergyStorage
import net.minecraftforge.fluids.capability.IFluidHandler
import net.minecraftforge.items.IItemHandler


object HiiragiProvider {

    interface Inventory {

        fun createInventory(): HiiragiCapabilityProvider<IItemHandler>

    }

    interface Tank {

        fun createTank(): HiiragiCapabilityProvider<IFluidHandler>

    }

    interface Energy {

        fun createBattery(): HiiragiCapabilityProvider<IEnergyStorage>

    }

}