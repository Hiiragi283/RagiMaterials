package hiiragi283.integration.jei

import hiiragi283.api.recipe.CrucibleRecipe
import hiiragi283.integration.jei.ingredients.HiiragiIngredientTypes
import hiiragi283.material.util.hiiragiLocation
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients

class CrucibleMeltCategory(guiHelper: IGuiHelper) :
    HiiragiRecipeCategory<CrucibleRecipe>(CRUCIBLE_MELT, guiHelper) {

    override val backGround: IDrawableStatic =
        guiHelper.createDrawable(hiiragiLocation("textures/gui/jei/process1_1_jei.png"), 1, 1, 54, 18)

    override fun setRecipe(layout: IRecipeLayout, wrapper: CrucibleRecipe, p2: IIngredients) {
        //inputのスロットを登録
        layout.getIngredientsGroup(HiiragiIngredientTypes.MATERIAL).init(0, true, 0 + 1, 0 + 1)
        layout.getIngredientsGroup(HiiragiIngredientTypes.MATERIAL).set(0, wrapper.input)
        //outputのスロットを登録
        layout.fluidStacks.init(0, false, 36 + 1, 1, 16, 16, 1000, false, null)
        layout.fluidStacks[0] = wrapper.getOutput()
    }

}