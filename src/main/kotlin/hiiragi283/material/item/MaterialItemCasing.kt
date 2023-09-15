package hiiragi283.material.item

import hiiragi283.material.api.item.MaterialItem
import hiiragi283.material.api.machine.ModuleTrait
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.module.IModuleItem
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.setModelSame
import net.minecraft.item.ItemStack

object MaterialItemCasing : MaterialItem(HiiragiShapes.CASING), IModuleItem {

    override fun getRecipe(item: MaterialItem, material: HiiragiMaterial) {
        CraftingBuilder(item.getItemStack(material))
            .setPattern("A A", "ABA", "A A")
            .setIngredient('A', HiiragiShapes.PLATE.getOreDict(material))
            .setIngredient('B', HiiragiShapes.FRAME.getOreDict(material))
            .build()
    }

    override fun getModel(item: MaterialItem) {
        this.setModelSame()
    }

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