package hiiragi283.api.recipe

import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.part.HiiragiPart
import hiiragi283.api.part.PartRegistry
import hiiragi283.integration.jei.ingredients.HiiragiIngredientTypes
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack

class CrucibleRecipe(
    val input: HiiragiMaterial,
    val tempMin: Int,
    private val fluid: String
) : HiiragiRecipe<CrucibleRecipe>() {

    companion object {
        @JvmField
        val EMPTY = CrucibleRecipe(HiiragiMaterial.EMPTY, -1, "water")
    }

    fun isEmpty() = this == EMPTY

    fun getOutput(amount: Int = 1000): FluidStack = FluidStack(FluidRegistry.getFluid(fluid), amount)

    fun getOutput(stack: ItemStack): FluidStack {
        return getOutput((PartRegistry.getParts(stack)
            .firstOrNull { it.material == input }
            ?: HiiragiPart.EMPTY).shape.scale)
    }

    fun matches(stack: ItemStack): Boolean {
        if (stack.isEmpty) return false
        return input in PartRegistry.getParts(stack).map { it.material }
    }

    //    IRecipeWrapper    //

    override fun getIngredients(p0: IIngredients) {
        p0.setInput(HiiragiIngredientTypes.MATERIAL, input)
        p0.setOutput(VanillaTypes.FLUID, getOutput())
    }

}