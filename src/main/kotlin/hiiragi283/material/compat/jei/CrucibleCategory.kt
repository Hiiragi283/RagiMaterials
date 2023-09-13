package hiiragi283.material.compat.jei

import hiiragi283.api.recipe.CrucibleRecipe
import hiiragi283.material.util.hiiragiLocation
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients

class CrucibleCategory(guiHelper: IGuiHelper) : hiiragi283.material.compat.jei.HiiragiRecipeCategory<CrucibleRecipe>(
    hiiragi283.material.compat.jei.CRUCIBLE, guiHelper) {

    override val backGround: IDrawableStatic =
        guiHelper.createDrawable(hiiragiLocation("textures/gui/jei/process1_1_1.png"), 1, 1, 18 * 5, 18)

    override fun setRecipe(layout: IRecipeLayout, recipe: CrucibleRecipe, iIngredients: IIngredients) {
        //input
        getMaterialStacks(layout).init(0, true, 1, 1)
        getMaterialStacks(layout)[0] = recipe.getInput()
        //cast
        layout.itemStacks.init(0, true, 18 * 2, 0)
        layout.itemStacks[0] = recipe.getCast()
        //output
        layout.itemStacks.init(1, true, 18 * 4, 0)
        layout.itemStacks[1] = recipe.getOutput()
    }

}