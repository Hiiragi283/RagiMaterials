package hiiragi283.api.recipe;

import hiiragi283.material.util.HiiragiUtil;
import hiiragi283.material.util.MetaResourceLocation;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class HeatSourceRecipe extends HiiragiRecipe<HeatSourceRecipe> {

    private final int temp;
    private final ItemStack stack;

    public HeatSourceRecipe(MetaResourceLocation metaLocation, int temp) {
        this.temp = temp;
        this.stack = HiiragiUtil.toItemStack(metaLocation.toBlockState());
    }

    public ItemStack getHeatSource() {
        return this.stack.copy();
    }

    //    IRecipeWrapper    //

    @Override
    public void getIngredients(@NotNull IIngredients iIngredients) {
        iIngredients.setInput(VanillaTypes.ITEM, getHeatSource());
    }

    @Override
    public void drawInfo(@NotNull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        minecraft.fontRenderer.drawString(String.valueOf(temp), 24, 24, Color.gray.getRGB());
    }
}