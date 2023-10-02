package hiiragi283.material.recipe

import hiiragi283.material.HiiragiItems
import hiiragi283.material.api.ingredient.FluidIngredient
import hiiragi283.material.api.ingredient.ItemIngredient
import hiiragi283.material.api.machine.IMachineRecipe
import hiiragi283.material.api.machine.MachineTrait
import hiiragi283.material.api.machine.MachineType
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.item.ItemShapePattern
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack

class MaterialCastingRecipe(val shape: HiiragiShape, val material: HiiragiMaterial) : IMachineRecipe {

    constructor(part: HiiragiPart) : this(part.shape, part.material)

    override fun getInputItems(): List<ItemIngredient> = listOf(
        ItemIngredient.Custom(
            stacks = { listOf(HiiragiItems.SHAPE_PATTERN.getItemStack(shape)) },
            predicate = { stack -> stack.item is ItemShapePattern },
            process = ItemIngredient.CATALYST_PROCESS
        )
    )

    override fun getInputFluids(): List<FluidIngredient> = listOf(
        FluidIngredient.Materials(material, amount = shape.scale)
    )

    override fun getRequiredTraits(): Set<MachineTrait> = setOf()

    override fun getRequiredType(): MachineType = MachineType.FREEZER

    override fun getOutputItems(): List<ItemStack> = listOfNotNull(shape.getItemStack(material))

    override fun getOutputFluids(): List<FluidStack> = listOf()

}