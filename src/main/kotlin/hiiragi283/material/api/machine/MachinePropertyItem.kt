package hiiragi283.material.api.machine

import hiiragi283.material.util.isShiftPressed
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack

interface MachinePropertyItem {

    fun getRecipeType(stack: ItemStack): MachineType = MachineType.NONE

    fun getProcessTime(stack: ItemStack): Int = 100

    fun getEnergyRate(stack: ItemStack): Int = 32

    fun getItemSlots(stack: ItemStack): Int = 1

    fun getFluidSlots(stack: ItemStack): Int = 0

    fun getMachineTraits(stack: ItemStack): Set<MachineTrait> = setOf()

    fun getEnergyRequired(stack: ItemStack): Int = getProcessTime(stack) * getEnergyRate(stack)

    fun getEnergyCapacity(stack: ItemStack): Int = getEnergyRequired(stack) * 5

    fun getMachineTraitsString(stack: ItemStack): String =
        getMachineTraits(stack).toString().let { it.substring(1, it.length - 1) }

    fun addTooltip(stack: ItemStack, tooltip: MutableList<String>) {
        tooltip.add("§e=== Machine Property ===")
        if (isShiftPressed()) {
            //tooltip.add(I18n.format("tips.ragi_materials.machine.recipe_type", recipeType(stack)))
            tooltip.add(I18n.format("tips.ragi_materials.machine.process_time", getProcessTime(stack)))
            tooltip.add(I18n.format("tips.ragi_materials.machine.energy_rate", getEnergyRate(stack)))
            tooltip.add(I18n.format("tips.ragi_materials.machine.required_energy", getEnergyRequired(stack)))
            tooltip.add(I18n.format("tips.ragi_materials.machine.energy_capacity", getEnergyCapacity(stack)))
            tooltip.add(I18n.format("tips.ragi_materials.machine.item_slots", getItemSlots(stack)))
            tooltip.add(I18n.format("tips.ragi_materials.machine.fluid_slot", getFluidSlots(stack)))
            tooltip.add(I18n.format("tips.ragi_materials.machine.machine_traits", getMachineTraitsString(stack)))
        } else {
            tooltip.add("Hold §bShift§r for Property")
        }
    }

    fun toMachineProperty(stack: ItemStack): MachineProperty {
        return MachineProperty.of(
            getRecipeType(stack),
            getProcessTime(stack),
            getEnergyRate(stack),
            getItemSlots(stack),
            getFluidSlots(stack),
            getMachineTraits(stack).toSet()
        )
    }

}