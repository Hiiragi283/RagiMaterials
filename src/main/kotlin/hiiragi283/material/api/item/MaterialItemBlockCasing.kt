package hiiragi283.material.api.item

import hiiragi283.material.api.block.MaterialBlockCasing
import hiiragi283.material.api.machine.ModuleTrait
import hiiragi283.material.api.module.IModuleItem
import hiiragi283.material.api.registry.HiiragiRegistries
import net.minecraft.item.ItemStack

class MaterialItemBlockCasing(block: MaterialBlockCasing) : MaterialItemBlock(block), IModuleItem {

    //    IModuleItem    //

    override fun getProcessTime(stack: ItemStack): Int =
        HiiragiRegistries.MATERIAL_INDEX.getValue(stack.metadata)
            ?.machineProperty
            ?.getProcessTime()
            ?: super.getProcessTime(stack)

    override fun getEnergyRate(stack: ItemStack): Int =
        HiiragiRegistries.MATERIAL_INDEX.getValue(stack.metadata)
            ?.machineProperty
            ?.getEnergyRate()
            ?: super.getEnergyRate(stack)

    override fun getItemSlots(stack: ItemStack): Int =
        HiiragiRegistries.MATERIAL_INDEX.getValue(stack.metadata)
            ?.machineProperty
            ?.getItemSlots()
            ?: super.getItemSlots(stack)

    override fun getFluidSlots(stack: ItemStack): Int =
        HiiragiRegistries.MATERIAL_INDEX.getValue(stack.metadata)
            ?.machineProperty
            ?.getFluidSlots()
            ?: super.getFluidSlots(stack)

    override fun getModuleTraits(stack: ItemStack): Set<ModuleTrait> =
        HiiragiRegistries.MATERIAL_INDEX.getValue(stack.metadata)
            ?.machineProperty
            ?.getModuleTraits()
            ?: super.getModuleTraits(stack)

}