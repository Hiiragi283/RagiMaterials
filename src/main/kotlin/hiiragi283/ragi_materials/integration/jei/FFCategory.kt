package hiiragi283.ragi_materials.integration.jei

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.recipe.FFRecipe
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawable
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import net.minecraft.util.ResourceLocation

class FFCategory(guiHelper: IGuiHelper) : JEICategoryBase<FFRecipe.Wrapper>(guiHelper) {

    var background: IDrawableStatic

    //JEIタブの背景を設定するメソッド
    init {
        val location = ResourceLocation(Reference.MOD_ID, "textures/gui/jei/forge_furnace.png")
        background = guiHelper.createDrawable(location, 1, 1, 54, 18)
    }

    //JEiタブのIDを取得するメソッド
    override fun getUid(): String = JEICore.ForgeFurnace

    //JEiタブの背景を取得するメソッド
    override fun getBackground(): IDrawable = background

    //JEiタブにレシピを設定するメソッド
    override fun setRecipe(layout: IRecipeLayout, wrapper: FFRecipe.Wrapper, ingredients: IIngredients) {
        //変化前と変化後のアイテムをwrapperから取得
        val inputs = wrapper.inputs
        val output = wrapper.output
        //inputのスロットを登録
        layout.itemStacks.init(0, true, 0, 0)
        layout.itemStacks[0] = inputs
        //outputのスロットを登録
        layout.itemStacks.init(1, false, 36, 0)
        layout.itemStacks[1] = output
    }
}