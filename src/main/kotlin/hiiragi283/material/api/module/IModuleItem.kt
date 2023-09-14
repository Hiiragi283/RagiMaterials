package hiiragi283.material.api.module

import hiiragi283.material.api.machine.IMachineProperty
import hiiragi283.material.api.machine.ModuleTrait

import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack

interface IModuleItem {

    fun addTooltip(stack: ItemStack, tooltip: MutableList<String>) {
        tooltip.add("§e=== Machine Property ===")
        tooltip.add(I18n.format("tips.ragi_materials.module.energy_capacity", getEnergyCapacity(stack)))
        tooltip.add(I18n.format("tips.ragi_materials.module.energy_rate", getEnergyRate(stack)))
        tooltip.add(I18n.format("tips.ragi_materials.module.fluid_slot", getFluidSlots(stack)))
        tooltip.add(I18n.format("tips.ragi_materials.module.item_slots", getItemSlots(stack)))
        tooltip.add(I18n.format("tips.ragi_materials.module.process_time", getProcessTime(stack)))
    }

    fun getProcessTime(stack: ItemStack): Int = 100

    fun getEnergyRate(stack: ItemStack): Int = 32

    fun getEnergyCapacity(stack: ItemStack): Int = getProcessTime(stack) * getEnergyRate(stack) * 5

    fun getItemSlots(stack: ItemStack): Int = 1

    fun getFluidSlots(stack: ItemStack): Int = 0

    fun getModuleTraits(stack: ItemStack): Set<ModuleTrait> = setOf()

    fun toMachineProperty(stack: ItemStack): IMachineProperty {
        return IMachineProperty.of {
            this.processTime = getProcessTime(stack)
            this.energyRate = getEnergyRate(stack)
            this.itemSlots = getItemSlots(stack)
            this.fluidSlots = getFluidSlots(stack)
        }
    }

}