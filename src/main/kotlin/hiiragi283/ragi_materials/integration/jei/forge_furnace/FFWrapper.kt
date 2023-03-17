package hiiragi283.ragi_materials.integration.jei.forge_furnace

import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.client.Minecraft

class FFWrapper(info: FFMaker.Recipe) : IRecipeWrapper {

    //private変数の宣言
    val inputs = info.input
    val output = info.output
    private val fuel = info.fuel

    //スロットにはめるIIngredientsを定義するメソッド
    override fun getIngredients(ingredients: IIngredients) {
        //各listをIIngredientsに設定
        ingredients.setInputs(VanillaTypes.ITEM, mutableListOf(inputs))
        ingredients.setOutputs(VanillaTypes.ITEM, mutableListOf(output))
    }

    override fun drawInfo(mc: Minecraft, wid: Int, hei: Int, mouseX: Int, mouseY: Int) {
        //テクスチャをGUI上に乗せる
        //ResourceLocation res = new ResourceLocation(domain, path);
        //mc.getTextureManager().bindTexture(res);
        //文字列をGUI上に描画する
        mc.fontRenderer.drawString("§7Fuel: $fuel", 60.0f, 4.5f, 0x000000, false)
    }
}