package hiiragi283.ragi_materials.integration.jei.forge_furnace

import com.google.common.collect.Lists
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.client.Minecraft
import net.minecraft.item.ItemStack

class ForgeFurnaceWrapper(info: ForgeFurnaceRecipe) : IRecipeWrapper {

    //private変数の宣言
    val stackIn: ItemStack
    val stackOut: ItemStack

    //コンストラクタの宣言
    init {
        stackIn = info.stackIn
        stackOut = info.stackOut
    }

    //スロットにはめるIIngredientsを定義するメソッド
    override fun getIngredients(ing: IIngredients) {
        //各listの宣言
        val itemsBefore: MutableList<ItemStack> = Lists.newArrayList()
        val itemsAfter: MutableList<ItemStack> = Lists.newArrayList()
        //各listにアイテムを登録
        itemsBefore.addAll(beforeList)
        itemsAfter.addAll(afterList)
        //各listをIIngredientsに設定
        ing.setInputs(VanillaTypes.ITEM, itemsBefore)
        ing.setOutputs(VanillaTypes.ITEM, itemsAfter)
    }

    //変化前のアイテムのリストを取得するメソッド
    private val beforeList: List<ItemStack>
        get() {
            //retの宣言
            val ret: MutableList<ItemStack> = Lists.newArrayList()
            //retにbeforeを追加
            ret.add(stackIn)
            //retを返す
            return ret
        }

    //変化前のアイテムのリストを取得するメソッド
    private val afterList: List<ItemStack>
        get() {
            //retの宣言
            val ret: MutableList<ItemStack> = Lists.newArrayList()
            ret.add(stackOut)
            //retを返す
            return ret
        }

    override fun drawInfo(mc: Minecraft, wid: Int, hei: Int, mouseX: Int, mouseY: Int) {
        val baseY = 0
        //テクスチャをGUI上に乗せる
        //ResourceLocation res = new ResourceLocation("dcs_climate", "textures/gui/c_effective_gui.png");
        //mc.getTextureManager().bindTexture(res);
        //文字列をGUI上に描画する
        //mc.fontRenderer.drawString(recipe.nameBefore, 5, baseY, 0x000000, false);
    }

    //
    override fun handleClick(minecraft: Minecraft, mouseX: Int, mouseY: Int, mouseButton: Int): Boolean {
        return false
    }
}