package hiiragi283.api.recipe;

import hiiragi283.api.material.MaterialStack;
import hiiragi283.integration.jei.ingredients.HiiragiIngredientTypes;
import hiiragi283.material.util.HiiragiColor;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HiiragiMaterialRecipe extends HiiragiRecipe<HiiragiMaterialRecipe> {

    private final MaterialStack stack;
    private final List<ItemStack> items;

    public HiiragiMaterialRecipe(MaterialStack stack, List<ItemStack> items) {
        this.stack = stack;
        this.items = items;
    }

    public MaterialStack getStack() {
        return this.stack;
    }

    public List<ItemStack> getItems() {
        return new ArrayList<>(this.items);
    }

    //    IRecipeWrapper    //

    @Override
    public void getIngredients(@NotNull IIngredients iIngredients) {
        iIngredients.setInputs(VanillaTypes.ITEM, items);
        iIngredients.setOutput(HiiragiIngredientTypes.MATERIAL, stack);
    }

    @Override
    public void drawInfo(@NotNull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        minecraft.fontRenderer.drawString(stack.getMaterial().getTranslatedName(), 24, 10, HiiragiColor.WHITE.getRGB());
    }
}