package hiiragi283.ragi_materials.integration.jei.stone_mill

import hiiragi283.ragi_materials.recipe.MillRecipe
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper

class StoneMillWrapper(info: MillRecipe): IRecipeWrapper {

    //private変数の宣言
    val input = info.input
    val output = info.output

    //スロットにはめるIIngredientsを定義するメソッド
    override fun getIngredients(ing: IIngredients) {
        //各listをIIngredientsに設定
        ing.setInputs(VanillaTypes.ITEM, mutableListOf(input))
        ing.setOutputs(VanillaTypes.ITEM, mutableListOf(output))
    }

}