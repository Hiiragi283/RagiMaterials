package hiiragi283.material.api.machine

import net.minecraft.item.ItemStack

interface MachineModuleItem {

    val processTime: (stack: ItemStack, base: Int) -> Int
    val energyRate: (stack: ItemStack, base: Int) -> Int
    val itemSlots: (stack: ItemStack, base: Int) -> Int
    val fluidSlots: (stack: ItemStack, base: Int) -> Int
    val machineTraits: (ItemStack) -> Set<MachineTrait>

}