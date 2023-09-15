package hiiragi283.material.item

import hiiragi283.material.api.item.MaterialItemBlock
import hiiragi283.material.api.machine.ModuleTrait
import hiiragi283.material.api.module.IModuleItem
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.block.MaterialBlockCasing
import net.minecraft.item.ItemStack

class MaterialItemBlockCasing(block: MaterialBlockCasing) : MaterialItemBlock(block), IModuleItem {

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