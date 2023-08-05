package hiiragi283.integration.jei

import hiiragi283.api.recipe.CrucibleRecipe
import hiiragi283.core.util.hiiragiLocation
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients

class CrucibleMeltCategory(guiHelper: IGuiHelper) : HiiragiRecipeCategory<CrucibleRecipe>(CRUCIBLE_MELT, guiHelper) {

    override val backGround: IDrawableStatic =
        guiHelper.createDrawable(hiiragiLocation("textures/gui/jei/process1_1_jei.png"), 1, 1, 54, 18)

    override fun setRecipe(layout: IRecipeLayout, wrapper: CrucibleRecipe, p2: IIngredients) {
        //inputのスロットを登録
        layout.itemStacks.init(0, true, 0, 0)
        layout.itemStacks[0] = wrapper.input.getAllItemStack()
        //outputのスロットを登録
        layout.fluidStacks.init(0, true, 36 + 1, 1, 16, 16, 144 * 9, true, null)
        layout.fluidStacks[0] = wrapper.output
    }

}