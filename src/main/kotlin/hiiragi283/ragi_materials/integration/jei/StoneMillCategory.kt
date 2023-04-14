package hiiragi283.ragi_materials.integration.jei

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.recipe.MillRecipe
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawable
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import net.minecraft.util.ResourceLocation

class StoneMillCategory(guiHelper: IGuiHelper) : JEICategoryBase<MillRecipe>() {

    var background: IDrawableStatic = guiHelper.createDrawable(ResourceLocation(RagiMaterials.MOD_ID, "textures/gui/jei/forge_furnace.png"), 1, 1, 54, 18)

    override fun getUid(): String = JEICore.StoneMill

    override fun getBackground(): IDrawable = background

    //JEiタブにレシピを設定するメソッド
    override fun setRecipe(layout: IRecipeLayout, wrapper: MillRecipe, ingredients: IIngredients) {
        //inputのスロットを登録
        layout.itemStacks.init(0, true, 0, 0)
        layout.itemStacks[0] = wrapper.getInput()
        //outputのスロットを登録
        layout.itemStacks.init(1, false, 36, 0)
        layout.itemStacks[1] = wrapper.getOutput()
    }

}