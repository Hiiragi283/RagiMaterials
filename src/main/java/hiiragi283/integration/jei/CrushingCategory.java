package hiiragi283.integration.jei;

import hiiragi283.api.recipe.CrushingRecipe;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import static hiiragi283.material.util.HiiragiUtil.hiiragiLocation;

public class CrushingCategory extends HiiragiRecipeCategory<CrushingRecipe> {


    public CrushingCategory(@NotNull IGuiHelper guiHelper) {
        super(JEIIntegrationKt.CRUSHING, guiHelper);
    }

    @NotNull
    @Override
    public IDrawableStatic getBackGround() {
        return getGuiHelper().createDrawable(hiiragiLocation("textures/gui/jei/process1_7.png"), 0, 0, 18 * 9, 18);
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayout iRecipeLayout, @NotNull CrushingRecipe crushingRecipe, @NotNull IIngredients iIngredients) {
        //input
        iRecipeLayout.getItemStacks().init(0, true, 0, 0);
        iRecipeLayout.getItemStacks().set(0, crushingRecipe.getInput().getAllItemStack());
        //output
        for (int i = 0; i <= 6; i++) {
            iRecipeLayout.getItemStacks().init(i + 1, false, 18 * (i + 2), 0);
            iRecipeLayout.getItemStacks().set(i + 1, getOutput(crushingRecipe, i));
        }
    }

    private ItemStack getOutput(CrushingRecipe crushingRecipe, int index) {
        return index < 0 || index >= crushingRecipe.getAllOutputs().size() ? ItemStack.EMPTY : crushingRecipe.getAllOutputs().get(index);
    }

}