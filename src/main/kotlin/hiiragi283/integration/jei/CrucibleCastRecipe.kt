package hiiragi283.integration.jei

import hiiragi283.api.item.ICastItem
import hiiragi283.api.recipe.HiiragiRecipe
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack

class CrucibleCastRecipe private constructor(
    private val input: FluidStack,
    private val cast: ItemStack,
    private val output: ItemStack
) : HiiragiRecipe<CrucibleCastRecipe>() {

    companion object {
        @JvmStatic
        fun of(cast: ItemStack, input: FluidStack): CrucibleCastRecipe {
            val item = cast.item
            return if (item is ICastItem) CrucibleCastRecipe(
                input,
                cast,
                item.getResult(cast, input)
            ) else CrucibleCastRecipe(input, ItemStack.EMPTY, ItemStack.EMPTY)
        }
    }

    fun getInput(): FluidStack = input.copy()

    fun getCast(): ItemStack = cast.copy()

    fun getOutput(): ItemStack = output.copy()

    //    IRecipeWrapper    //

    override fun getIngredients(p0: IIngredients) {
        p0.setInput(VanillaTypes.FLUID, getInput())
        p0.setInput(VanillaTypes.ITEM, getCast())
        p0.setOutput(VanillaTypes.ITEM, getOutput())
    }

}