package hiiragi283.integration.jei;

import hiiragi283.api.recipe.CrucibleRecipe;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import org.jetbrains.annotations.NotNull;

import static hiiragi283.material.util.HiiragiUtil.hiiragiLocation;

public class CrucibleCategory extends HiiragiRecipeCategory<CrucibleRecipe> {

    public CrucibleCategory(@NotNull IGuiHelper guiHelper) {
        super(JEIIntegrationKt.CRUCIBLE, guiHelper);
    }

    @NotNull
    @Override
    public IDrawableStatic getBackGround() {
        return getGuiHelper().createDrawable(hiiragiLocation("textures/gui/jei/process1_1_1.png"), 1, 1, 18 * 5, 18);
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayout iRecipeLayout, @NotNull CrucibleRecipe crucibleRecipe, @NotNull IIngredients iIngredients) {
        //input
        getMaterialStacks(iRecipeLayout).init(0, true, 1, 1);
        getMaterialStacks(iRecipeLayout).set(0, crucibleRecipe.getInput());
        //cast
        iRecipeLayout.getItemStacks().init(0, true, 18 * 2, 0);
        iRecipeLayout.getItemStacks().set(0, crucibleRecipe.getCast());
        //output
        iRecipeLayout.getItemStacks().init(1, true, 18 * 4, 0);
        iRecipeLayout.getItemStacks().set(1, crucibleRecipe.getOutput());
    }

}