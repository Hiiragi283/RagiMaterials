package hiiragi283.material.item

import hiiragi283.material.HiiragiItems
import hiiragi283.material.api.item.MaterialItem
import hiiragi283.material.api.machine.IMachinePropertyItem
import hiiragi283.material.api.machine.MachineTrait
import hiiragi283.material.api.machine.MachineType
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.itemStack
import net.minecraft.client.util.ITooltipFlag
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object MaterialItemCasing : MaterialItem(
    HiiragiShapes.CASING,
    recipe = { item: MaterialItem, material: HiiragiMaterial ->
        if (HiiragiShapes.PLATE.isValid(material)) {
            CraftingBuilder(item.itemStack(material))
                .setPattern("AAA", "ABA", "AAA")
                .setIngredient('A', HiiragiShapes.PLATE.getOreDict(material))
                .setIngredient('B', HiiragiItems.SMITHING_HAMMER, true)
                .build()
        }
    }
), IMachinePropertyItem {

    //    Client    //

    @SideOnly(Side.CLIENT)
    override fun addInformation(stack: ItemStack, worldIn: World?, tooltip: MutableList<String>, flagIn: ITooltipFlag) {
        this.addTooltip(stack, tooltip)
    }

    //    IMachinePropertyItem    //

    override val recipeType: (ItemStack) -> MachineType = { _ -> MachineType.NONE }

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

    override val machineTraits: (ItemStack) -> Set<MachineTrait> = { stack ->
        HiiragiRegistries.MATERIAL_INDEX.getValue(stack.metadata)
            ?.machineProperty
            ?.machineTraits
            ?: setOf()
    }

}