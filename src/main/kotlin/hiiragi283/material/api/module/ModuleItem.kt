package hiiragi283.material.api.module

import hiiragi283.material.api.machine.MachineTrait
import net.minecraft.item.ItemStack

interface ModuleItem {

    val processTime: (stack: ItemStack, base: Int) -> Int
    val energyRate: (stack: ItemStack, base: Int) -> Int
    val itemSlots: (stack: ItemStack, base: Int) -> Int
    val fluidSlots: (stack: ItemStack, base: Int) -> Int
    val machineTraits: (ItemStack) -> Set<MachineTrait>

}