package hiiragi283.ragi_materials.integration.jei.laboratory_table

import hiiragi283.ragi_materials.recipe.LaboRecipe
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper

class LaboWrapper(info: LaboRecipe) : IRecipeWrapper {

    val inputs = info.inputs
    val output = info.outputs

    override fun getIngredients(ingredients: IIngredients) {
        ingredients.setInputLists(VanillaTypes.ITEM, mutableListOf(inputs))
        ingredients.setOutputLists(VanillaTypes.ITEM, mutableListOf(output))
    }
}