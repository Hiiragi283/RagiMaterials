package hiiragi283.material.compat.jei;

import hiiragi283.material.api.recipe.IMachineRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import org.jetbrains.annotations.NotNull;

public class MachineRecipeWrapper implements IRecipeWrapper {

    private final IMachineRecipe recipe;

    public MachineRecipeWrapper(IMachineRecipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public void getIngredients(@NotNull IIngredients iIngredients) {
        iIngredients.setInputLists(VanillaTypes.ITEM, recipe.getInputItems());
        iIngredients.setInputs(VanillaTypes.FLUID, recipe.getInputFluids());
        iIngredients.setOutputLists(VanillaTypes.ITEM, recipe.getOutputItems());
        iIngredients.setOutputs(VanillaTypes.FLUID, recipe.getOutputFluids());
    }

}