package hiiragi283.integration.jei

import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.config.Constants

class HeatSourceCategory(guiHelper: IGuiHelper) : HiiragiRecipeCategory<HeatSourceRecipe>(CRUCIBLE_HEAT, guiHelper) {

    override val backGround: IDrawableStatic = guiHelper.drawableBuilder(Constants.RECIPE_GUI_VANILLA, 0, 134, 18, 34).build();

    override fun setRecipe(layout: IRecipeLayout, wrapper: HeatSourceRecipe, p2: IIngredients) {
        layout.itemStacks.init(1, true, 0, 16)
        layout.itemStacks.set(p2)
    }

}