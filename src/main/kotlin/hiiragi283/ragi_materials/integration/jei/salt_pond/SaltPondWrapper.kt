package hiiragi283.ragi_materials.integration.jei.salt_pond

import com.google.common.collect.Lists
import hiiragi283.ragi_materials.util.RagiUtil
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.item.ItemStack

class SaltPondWrapper(info: SaltPondRecipe) : IRecipeWrapper {

    //private変数の宣言
    val stackIn: ItemStack
    val stackOut: ItemStack

    //コンストラクタの宣言
    init {
        stackIn = RagiUtil.getFilledBottle(1, info.stringIn, 1000)
        stackOut = info.stackOut
    }

    //スロットにはめるIIngredientsを定義するメソッド
    override fun getIngredients(ing: IIngredients) {
        //各listの宣言
        val inputList: MutableList<ItemStack> = Lists.newArrayList()
        val outputList: MutableList<ItemStack> = Lists.newArrayList()
        //listにinputを追加
        inputList.add(stackIn)
        outputList.add(stackOut)
        //各listをIIngredientsに設定
        ing.setInputs(VanillaTypes.ITEM, inputList)
        ing.setOutputs(VanillaTypes.ITEM, outputList)
    }
}