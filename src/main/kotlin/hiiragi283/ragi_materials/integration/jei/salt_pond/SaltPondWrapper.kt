package hiiragi283.ragi_materials.integration.jei.salt_pond

import hiiragi283.ragi_materials.util.RagiFluidUtil
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper

class SaltPondWrapper(info: SaltPondMaker.Recipe) : IRecipeWrapper {

    //private変数の宣言
    val input = RagiFluidUtil.getBottle(name = info.stringIn)
    val output = info.stackOut

    //スロットにはめるIIngredientsを定義するメソッド
    override fun getIngredients(ingredients: IIngredients) {
        //各listをIIngredientsに設定
        ingredients.setInputs(VanillaTypes.ITEM, mutableListOf(input))
        ingredients.setOutputs(VanillaTypes.ITEM, mutableListOf(output))
    }
}