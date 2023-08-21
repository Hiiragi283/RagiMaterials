package hiiragi283.integration.jei;

import hiiragi283.api.recipe.HiiragiMaterialRecipe;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import org.jetbrains.annotations.NotNull;

import static hiiragi283.material.util.HiiragiUtil.hiiragiLocation;

public class HiiragiMaterialCategory extends HiiragiRecipeCategory<HiiragiMaterialRecipe> {

    public HiiragiMaterialCategory(@NotNull IGuiHelper guiHelper) {
        super(JEIIntegrationKt.MATERIAL, guiHelper);
    }

    @NotNull
    @Override
    public IDrawableStatic getBackGround() {
        return getGuiHelper().createDrawable(hiiragiLocation("textures/gui/jei/material_info.png"), 0, 0, 170, 116);
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayout iRecipeLayout, @NotNull HiiragiMaterialRecipe hiiragiMaterialRecipe, @NotNull IIngredients iIngredients) {
        //MaterialStack
        getMaterialStacks(iRecipeLayout).init(0, false, 4 + 1, 4 + 1);
        getMaterialStacks(iRecipeLayout).set(0, hiiragiMaterialRecipe.getStack());
        //ItemStack
        for (int i = 0; i < hiiragiMaterialRecipe.getItems().size(); i++) {
            iRecipeLayout.getItemStacks().init(i, true, 18 * (i % 9) + 4, 18 * (i / 9) + 18 + 4);
            iRecipeLayout.getItemStacks().set(i, hiiragiMaterialRecipe.getItems().get(i));
        }
    }
}