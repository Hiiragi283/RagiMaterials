package hiiragi283.ragi_materials.integration.jei.salt_pond

import hiiragi283.ragi_materials.util.RagiUtil
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper

class SaltPondWrapper(info: SaltPondRecipe) : IRecipeWrapper {

    //private変数の宣言
    val stackIn = RagiUtil.getFilledBottle(name = info.stringIn)
    val stackOut = info.stackOut

    //スロットにはめるIIngredientsを定義するメソッド
    override fun getIngredients(ing: IIngredients) {
        //各listをIIngredientsに設定
        ing.setInputs(VanillaTypes.ITEM, mutableListOf(stackIn))
        ing.setOutputs(VanillaTypes.ITEM, mutableListOf(stackOut))
    }
}