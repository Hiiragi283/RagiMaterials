package hiiragi283.material.compat.jei

import hiiragi283.material.api.recipe.IMachineRecipe
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper

class MachineRecipeWrapper(val recipe: IMachineRecipe) : IRecipeWrapper {

    override fun getIngredients(iIngredients: IIngredients) {
        iIngredients.setInputLists(VanillaTypes.ITEM, recipe.inputItems)
        iIngredients.setInputs(VanillaTypes.FLUID, recipe.inputFluids)
        iIngredients.setOutputLists(VanillaTypes.ITEM, recipe.outputItems)
        iIngredients.setOutputs(VanillaTypes.FLUID, recipe.outputFluids)
    }

}