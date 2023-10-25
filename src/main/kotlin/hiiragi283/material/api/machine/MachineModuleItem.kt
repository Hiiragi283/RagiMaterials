package hiiragi283.material.api.machine

import net.minecraft.item.ItemStack

interface MachineModuleItem {

    fun getProcessTime(stack: ItemStack, base: Int): Int = base

    fun getEnergyRate(stack: ItemStack, base: Int): Int = base

    fun getItemSlots(stack: ItemStack, base: Int): Int = base

    fun getFluidSlots(stack: ItemStack, base: Int): Int = base

    fun getMachineTraits(stack: ItemStack): Set<MachineTrait> = setOf()

}