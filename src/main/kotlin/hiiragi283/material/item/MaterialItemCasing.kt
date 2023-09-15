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

    override val processTime: (ItemStack) -> Int = { stack ->
        HiiragiRegistries.MATERIAL_INDEX.getValue(stack.metadata)
            ?.machineProperty
            ?.processTime
            ?: 100
    }

    override val energyRate: (ItemStack) -> Int = { stack ->
        HiiragiRegistries.MATERIAL_INDEX.getValue(stack.metadata)
            ?.machineProperty
            ?.energyRate
            ?: 32
    }

    override val itemSlots: (ItemStack) -> Int = { stack ->
        HiiragiRegistries.MATERIAL_INDEX.getValue(stack.metadata)
            ?.machineProperty
            ?.itemSlots
            ?: 1
    }

    override val fluidSlots: (ItemStack) -> Int = { stack ->
        HiiragiRegistries.MATERIAL_INDEX.getValue(stack.metadata)
            ?.machineProperty
            ?.fluidSlots
            ?: 0
    }

    override val moduleTraits: (ItemStack) -> Set<ModuleTrait> = { stack ->
        HiiragiRegistries.MATERIAL_INDEX.getValue(stack.metadata)
            ?.machineProperty
            ?.moduleTraits
            ?: setOf()
    }

}