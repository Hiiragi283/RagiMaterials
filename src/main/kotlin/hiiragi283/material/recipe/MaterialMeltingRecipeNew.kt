package hiiragi283.material.recipe

import hiiragi283.material.api.ingredient.FluidIngredient
import hiiragi283.material.api.ingredient.ItemIngredient
import hiiragi283.material.api.machine.IMachineRecipe
import hiiragi283.material.api.machine.MachineTrait
import hiiragi283.material.api.machine.MachineType
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.shape.HiiragiShape
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack

class MaterialMeltingRecipeNew(val shape: HiiragiShape, val material: HiiragiMaterial) : IMachineRecipe {

    constructor(part: HiiragiPart) : this(part.shape, part.material)

    override fun getInputItems(): List<ItemIngredient> = listOf(ItemIngredient.Parts(shape, material))

    override fun getInputFluids(): List<FluidIngredient> = listOf()

    override fun getRequiredTraits(): Set<MachineTrait> = setOf(MachineTrait.MELT)

    override fun getRequiredType(): MachineType = MachineType.SMELTER

    override fun getOutputItems(): List<ItemStack> = listOf()

    override fun getOutputFluids(): List<FluidStack> = listOfNotNull(material.getFluidStack(shape.getScale(material)))

}