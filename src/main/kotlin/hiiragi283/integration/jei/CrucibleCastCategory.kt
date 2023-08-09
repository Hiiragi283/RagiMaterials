package hiiragi283.integration.jei

import hiiragi283.material.util.hiiragiLocation
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients

class CrucibleCastCategory(guiHelper: IGuiHelper) :
    HiiragiRecipeCategory<CrucibleCastRecipeJEI>(CRUCIBLE_CAST, guiHelper) {

    override val backGround: IDrawableStatic =
        guiHelper.createDrawable(hiiragiLocation("textures/gui/jei/process1_1_1_jei.png"), 1, 1, 18 * 5, 18)

    override fun setRecipe(layout: IRecipeLayout, wrapper: CrucibleCastRecipeJEI, p2: IIngredients) {
        //inputのスロットを登録
        layout.fluidStacks.init(0, true, 0 + 1, 0 + 1, 16, 16, 144 * 9, true, null)
        layout.fluidStacks[0] = wrapper.getInput()
        //castのスロットを登録
        layout.itemStacks.init(0, true, 18 * 2, 0)
        layout.itemStacks[0] = wrapper.getCast()
        //outputのスロットを登録
        layout.itemStacks.init(1, true, 18 * 4, 0)
        layout.itemStacks[1] = wrapper.getOutput()
    }

}