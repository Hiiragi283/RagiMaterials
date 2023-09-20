package hiiragi283.material.api.machine

import hiiragi283.material.api.recipe.IMachineRecipe
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraftforge.event.entity.player.ItemTooltipEvent

interface IMachinePropertyItem {

    val recipeType: (ItemStack) -> IMachineRecipe.Type
    val processTime: (ItemStack) -> Int
    val energyRate: (ItemStack) -> Int
    val itemSlots: (ItemStack) -> Int
    val fluidSlots: (ItemStack) -> Int
    val machineTraits: (ItemStack) -> Set<MachineTrait>

    fun getEnergyRequired(stack: ItemStack): Int = processTime(stack) * energyRate(stack)

    fun getEnergyCapacity(stack: ItemStack): Int = getEnergyRequired(stack) * 5

    fun addTooltip(event: ItemTooltipEvent) {
        val stack: ItemStack = event.itemStack
        val tooltip: MutableList<String> = event.toolTip
        tooltip.add("§e=== Machine Property ===")
        //tooltip.add(I18n.format("tips.ragi_materials.machine.recipe_type", recipeType(stack)))
        tooltip.add(I18n.format("tips.ragi_materials.machine.process_time", processTime(stack)))
        tooltip.add(I18n.format("tips.ragi_materials.machine.energy_rate", energyRate(stack)))
        tooltip.add(I18n.format("tips.ragi_materials.machine.required_energy", getEnergyRequired(stack)))
        tooltip.add(I18n.format("tips.ragi_materials.machine.energy_capacity", getEnergyCapacity(stack)))
        tooltip.add(I18n.format("tips.ragi_materials.machine.item_slots", itemSlots(stack)))
        tooltip.add(I18n.format("tips.ragi_materials.machine.fluid_slot", fluidSlots(stack)))
    }

    fun toMachineProperty(stack: ItemStack): IMachineProperty {
        return IMachineProperty.of(
            recipeType(stack),
            processTime(stack),
            energyRate(stack),
            itemSlots(stack),
            fluidSlots(stack),
            machineTraits(stack).toMutableSet()
        )
    }

}