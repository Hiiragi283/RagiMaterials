package hiiragi283.material.base

import hiiragi283.material.capability.RagiCapabilityProvider
import hiiragi283.material.capability.energy.RagiEnergyStorage
import hiiragi283.material.capability.fluid.RagiTank
import hiiragi283.material.capability.item.RagiItemHandler

object ITileProvider {

    interface Inventory {

        fun createInventory(): RagiCapabilityProvider<RagiItemHandler<*>>

    }

    interface Tank {

        fun createTank(): RagiCapabilityProvider<RagiTank>

    }

    interface Energy {

        fun createBattery(): RagiCapabilityProvider<RagiEnergyStorage>

    }
}