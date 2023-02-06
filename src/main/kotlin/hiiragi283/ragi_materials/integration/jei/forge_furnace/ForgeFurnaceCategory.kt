package hiiragi283.ragi_materials.integration.jei.forge_furnace

import hiiragi283.ragi_materials.Reference
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawable
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.recipe.IRecipeCategory
import net.minecraft.client.Minecraft
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

class ForgeFurnaceCategory(guiHelper: IGuiHelper) : IRecipeCategory<ForgeFurnaceWrapper> {

    private val background: IDrawableStatic

    //JEIタブの背景を設定するメソッド
    init {
        val location = ResourceLocation("ragi_materials", "textures/gui/c_mill_gui_jei.png")
        background = guiHelper.createDrawable(location, 0, 0, 54, 18)
    }

    //JEiタブのIDを取得するメソッド
    override fun getUid(): String {
        return "ragi_materials.forge_furnace"
    }

    //JEiタブの名前を取得するメソッド
    override fun getTitle(): String {
        return I18n.format("gui.$uid")
    }

    //JEiタブの背景を取得するメソッド
    override fun getBackground(): IDrawable {
        return background
    }

    //なんかエクストラするメソッド
    override fun drawExtras(mc: Minecraft) {}

    //JEiタブのアイコンを取得するメソッド?
    override fun getIcon(): IDrawable? {
        return null
    }

    //JEiタブにレシピを設定するメソッド
    override fun setRecipe(recipeLayout: IRecipeLayout, recipeWrapper: ForgeFurnaceWrapper, ingredients: IIngredients) {
        //変化前と変化後のアイテムをwrapperから取得
        val input: ItemStack = recipeWrapper.stackIn
        val output: ItemStack = recipeWrapper.stackOut
        //inputのスロットを登録
        recipeLayout.itemStacks.init(0, true, 0, 0)
        recipeLayout.itemStacks[0] = input
        //outputのスロットを登録
        recipeLayout.itemStacks.init(1, false, 36, 0)
        recipeLayout.itemStacks[1] = output
    }

    //JEiタブに紐づいたmod名を取得するメソッド
    override fun getModName(): String {
        return Reference.MOD_NAME
    }
}