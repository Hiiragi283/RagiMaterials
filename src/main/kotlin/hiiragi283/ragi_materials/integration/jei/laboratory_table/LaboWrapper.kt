package hiiragi283.ragi_materials.integration.jei.laboratory_table

import hiiragi283.ragi_materials.recipe.laboratory.LaboRecipeBuilder
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper

class LaboWrapper(info: LaboRecipeBuilder) : IRecipeWrapper {

    val inputs = listOf(info.input1, info.input2, info.input3, info.input4, info.input5)
    val output = info.outputs

    override fun getIngredients(ing: IIngredients) {
        ing.setInputLists(VanillaTypes.ITEM, mutableListOf(inputs))
        ing.setOutputLists(VanillaTypes.ITEM, mutableListOf(output))
    }
}