package hiiragi283.ragi_materials.integration.jei.laboratory_table

import hiiragi283.ragi_materials.recipe.laboratory.LaboRecipe
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper

class LaboWrapper(info: LaboRecipe) : IRecipeWrapper {

    val inputs = info.inputs
    val output = info.outputs

    override fun getIngredients(ing: IIngredients) {
        ing.setInputLists(VanillaTypes.ITEM, mutableListOf(inputs))
        ing.setOutputLists(VanillaTypes.ITEM, mutableListOf(output))
    }
}