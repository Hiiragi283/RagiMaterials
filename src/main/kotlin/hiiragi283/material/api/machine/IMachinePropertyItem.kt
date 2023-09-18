package hiiragi283.material.api.machine

import hiiragi283.material.api.recipe.IMachineRecipe
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack

interface IMachinePropertyItem {

    val recipeType: (ItemStack) -> IMachineRecipe.Type
    val processTime: (ItemStack) -> Int
    val energyRate: (ItemStack) -> Int
    val itemSlots: (ItemStack) -> Int
    val fluidSlots: (ItemStack) -> Int
    val moduleTraits: (ItemStack) -> Set<ModuleTrait>

    fun addTooltip(stack: ItemStack, tooltip: MutableList<String>) {
        tooltip.add("§e=== Machine Property ===")
        tooltip.add(I18n.format("tips.ragi_materials.module.process_time", processTime(stack)))
        tooltip.add(I18n.format("tips.ragi_materials.module.energy_rate", energyRate(stack)))
        tooltip.add(I18n.format("tips.ragi_materials.module.item_slots", itemSlots(stack)))
        tooltip.add(I18n.format("tips.ragi_materials.module.fluid_slot", fluidSlots(stack)))
    }

    fun toMachineProperty(stack: ItemStack): IMachineProperty {
        return IMachineProperty.of(
            recipeType(stack),
            processTime(stack),
            energyRate(stack),
            itemSlots(stack),
            fluidSlots(stack),
            moduleTraits(stack).toMutableSet()
        )
    }

}