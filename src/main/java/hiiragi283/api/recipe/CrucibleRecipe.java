package hiiragi283.api.recipe;

import hiiragi283.api.material.MaterialStack;
import hiiragi283.integration.jei.ingredients.HiiragiIngredientTypes;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CrucibleRecipe extends HiiragiRecipe<CrucibleRecipe> {

    private final MaterialStack input;
    private final ItemStack cast;
    private final ItemStack output;

    public CrucibleRecipe(MaterialStack input, ItemStack cast, ItemStack output) {
        this.input = input;
        this.cast = cast;
        this.output = output;
    }

    public MaterialStack getInput() {
        return this.input;
    }

    public ItemStack getCast() {
        return this.cast.copy();
    }

    public ItemStack getOutput() {
        return this.output.copy();
    }

    //    IRecipeWrapper    //


    @Override
    public void getIngredients(@NotNull IIngredients iIngredients) {
        iIngredients.setInput(HiiragiIngredientTypes.MATERIAL, getInput());
        iIngredients.setInput(VanillaTypes.ITEM, getCast());
        iIngredients.setOutput(VanillaTypes.ITEM, getOutput());
    }

}