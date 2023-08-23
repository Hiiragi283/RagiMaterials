package hiiragi283.api.tile

import hiiragi283.api.capability.HiiragiCapabilityProvider
import hiiragi283.api.capability.material.IMaterialHandler
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

    interface Material {

        fun createHandler(): HiiragiCapabilityProvider<IMaterialHandler>

    }

}