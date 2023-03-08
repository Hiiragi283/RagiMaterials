package hiiragi283.ragi_materials.integration.jei.forge_furnace

import com.google.common.collect.Lists
import hiiragi283.ragi_materials.recipe.forge_furnace.FFRecipe
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.client.Minecraft
import net.minecraft.item.ItemStack

class FFWrapper(info: FFRecipe) : IRecipeWrapper {

    //private変数の宣言
    val input: ItemStack
    val output: ItemStack
    val type: FFRecipe.EnumFire

    //コンストラクタの宣言
    init {
        input = info.input
        output = info.output
        type = info.type
    }

    //スロットにはめるIIngredientsを定義するメソッド
    override fun getIngredients(ing: IIngredients) {
        //各listの宣言
        val inputList: MutableList<ItemStack> = Lists.newArrayList()
        val outputList: MutableList<ItemStack> = Lists.newArrayList()
        //listにinputを追加
        inputList.add(input)
        outputList.add(output)
        //各listをIIngredientsに設定
        ing.setInputs(VanillaTypes.ITEM, inputList)
        ing.setOutputs(VanillaTypes.ITEM, outputList)
    }

    override fun drawInfo(mc: Minecraft, wid: Int, hei: Int, mouseX: Int, mouseY: Int) {
        //テクスチャをGUI上に乗せる
        //ResourceLocation res = new ResourceLocation(domain, path);
        //mc.getTextureManager().bindTexture(res);
        //文字列をGUI上に描画する
        mc.fontRenderer.drawString(type.display, 20.0f, -10.0f, 0x000000, false)
    }

    //
    override fun handleClick(minecraft: Minecraft, mouseX: Int, mouseY: Int, mouseButton: Int): Boolean = false
}