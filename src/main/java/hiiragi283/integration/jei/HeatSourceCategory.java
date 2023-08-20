package hiiragi283.integration.jei;

import hiiragi283.api.recipe.HeatSourceRecipe;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.config.Constants;
import org.jetbrains.annotations.NotNull;

public class HeatSourceCategory extends HiiragiRecipeCategory<HeatSourceRecipe> {


    public HeatSourceCategory(@NotNull IGuiHelper guiHelper) {
        super(JEIIntegrationKt.CRUCIBLE_HEAT, guiHelper);
    }

    @NotNull
    @Override
    public IDrawableStatic getBackGround() {
        return getGuiHelper().drawableBuilder(Constants.RECIPE_GUI_VANILLA, 0, 134, 18, 34).build();
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayout iRecipeLayout, @NotNull HeatSourceRecipe heatSourceRecipe, @NotNull IIngredients iIngredients) {
        iRecipeLayout.getItemStacks().init(1, true, 0, 16);
        iRecipeLayout.getItemStacks().set(iIngredients);
    }
}