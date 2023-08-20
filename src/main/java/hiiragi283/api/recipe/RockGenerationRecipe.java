package hiiragi283.api.recipe;

import hiiragi283.material.util.HiiragiUtil;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class RockGenerationRecipe extends HiiragiRecipe<RockGenerationRecipe> {

    private final ItemStack input;
    private final ItemStack output;

    public RockGenerationRecipe(ItemStack stack) {
        this(stack, stack);
    }

    public RockGenerationRecipe(ItemStack input, ItemStack output) {
        this.input = new ItemStack(input.getItem(), 1, input.getMetadata());
        this.output = new ItemStack(output.getItem(), 1, output.getMetadata());
        this.setRegistryName(HiiragiUtil.toLocation(input));
    }

    public ItemStack getInput() {
        return this.input.copy();
    }

    public ItemStack getOutput() {
        return this.output.copy();
    }

    public Boolean matches(ItemStack stack) {
        return HiiragiUtil.isSameWithoutCount(this.input, stack);
    }

    //    IRecipeWrapper    //

    @Override
    public void getIngredients(@NotNull IIngredients iIngredients) {
        iIngredients.setInput(VanillaTypes.ITEM, getInput());
        iIngredients.setOutput(VanillaTypes.ITEM, getOutput());
    }

}