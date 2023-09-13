package hiiragi283.material.api.recipe

import hiiragi283.api.material.MaterialStack
import hiiragi283.compat.jei.ingredients.HiiragiIngredientTypes
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import net.minecraft.item.ItemStack

class CrucibleRecipe(
    private val input: MaterialStack,
    private val cast: ItemStack,
    private val output: ItemStack
) : HiiragiRecipe<CrucibleRecipe>() {

    fun getInput(): MaterialStack = input.copy()

    fun getCast(): ItemStack = cast.copy()

    fun getOutput(): ItemStack = output.copy()

    //    IRecipeWrapper    //
    override fun getIngredients(iIngredients: IIngredients) {
        iIngredients.setInput(HiiragiIngredientTypes.MATERIAL, getInput())
        iIngredients.setInput(VanillaTypes.ITEM, getCast())
        iIngredients.setOutput(VanillaTypes.ITEM, getOutput())
    }
}