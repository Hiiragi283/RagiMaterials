package hiiragi283.material.api.machine

import hiiragi283.material.util.isShiftPressed
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack

interface IMachinePropertyItem {

    val recipeType: (ItemStack) -> MachineType
    val processTime: (ItemStack) -> Int
    val energyRate: (ItemStack) -> Int
    val itemSlots: (ItemStack) -> Int
    val fluidSlots: (ItemStack) -> Int
    val machineTraits: (ItemStack) -> Set<MachineTrait>

    fun getEnergyRequired(stack: ItemStack): Int = processTime(stack) * energyRate(stack)

    fun getEnergyCapacity(stack: ItemStack): Int = getEnergyRequired(stack) * 5

    fun getMachineTraitsString(stack: ItemStack): String =
        machineTraits(stack).toString().let { it.substring(1, it.length - 1) }

    fun addTooltip(stack: ItemStack, tooltip: MutableList<String>) {
        tooltip.add("§e=== Machine Property ===")
        if (isShiftPressed()) {
            //tooltip.add(I18n.format("tips.ragi_materials.machine.recipe_type", recipeType(stack)))
            tooltip.add(I18n.format("tips.ragi_materials.machine.process_time", processTime(stack)))
            tooltip.add(I18n.format("tips.ragi_materials.machine.energy_rate", energyRate(stack)))
            tooltip.add(I18n.format("tips.ragi_materials.machine.required_energy", getEnergyRequired(stack)))
            tooltip.add(I18n.format("tips.ragi_materials.machine.energy_capacity", getEnergyCapacity(stack)))
            tooltip.add(I18n.format("tips.ragi_materials.machine.item_slots", itemSlots(stack)))
            tooltip.add(I18n.format("tips.ragi_materials.machine.fluid_slot", fluidSlots(stack)))
            tooltip.add(I18n.format("tips.ragi_materials.machine.machine_traits", getMachineTraitsString(stack)))
        } else {
            tooltip.add("Hold §bShift§r for Property")
        }
    }

    fun toMachineProperty(stack: ItemStack): IMachineProperty {
        return IMachineProperty.of(
            recipeType(stack),
            processTime(stack),
            energyRate(stack),
            itemSlots(stack),
            fluidSlots(stack),
            machineTraits(stack).toSet()
        )
    }

}