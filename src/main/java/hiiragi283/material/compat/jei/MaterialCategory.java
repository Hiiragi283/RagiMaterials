package hiiragi283.material.compat.jei;

import hiiragi283.material.api.material.HiiragiMaterial;
import hiiragi283.material.api.material.MaterialStack;
import hiiragi283.material.compat.jei.ingredient.HiiragiIngredientTypes;
import hiiragi283.material.util.HiiragiColor;
import hiiragi283.material.util.HiiragiUtil;
import hiiragi283.material.util.ItemStackComparable;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MaterialCategory extends HiiragiRecipeCategory<MaterialCategory.Recipe> {

    private MaterialCategory(IGuiHelper guiHelper) {
        super(HiiragiJEIPlugin.MATERIAL, guiHelper);
    }

    public static void register(@NotNull IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new MaterialCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @NotNull
    @Override
    public IDrawable getBackground() {
        return guiHelper.createDrawable(HiiragiUtil.getLocation("textures/gui/jei/material_info.png"), 0, 0, 170, 116);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void setRecipe(IRecipeLayout iRecipeLayout, Recipe recipe, IIngredients iIngredients) {
        //MaterialStack
        getMaterialStacks(iRecipeLayout).init(0, false, 4 + 1, 4 + 1);
        getMaterialStacks(iRecipeLayout).set(0, recipe.getMaterialStack());
        //ItemStack
        for (int i = 0; i < recipe.getStacks().size(); i++) {
            iRecipeLayout.getItemStacks().init(i, true, 18 * (i % 9) + 4, 18 * (i / 9) + 18 + 4);
            iRecipeLayout.getItemStacks().set(i, recipe.getStacks().get(i));
        }
    }

    @Optional.Interface(iface = "mezz.jei.api.recipe.IRecipeWrapper", modid = "jei")
    public static class Recipe implements IRecipeWrapper {

        private final MaterialStack materialStack;
        private final Set<ItemStackComparable> stacks;

        public Recipe(HiiragiMaterial material) {
            this.materialStack = material.toMaterialStack();
            this.stacks = material.getAllItemStack().stream()
                    .map(ItemStackComparable::new)
                    .collect(Collectors.toSet());
        }

        public MaterialStack getMaterialStack() {
            return new MaterialStack(materialStack, materialStack.amount);
        }

        public List<ItemStack> getStacks() {
            return stacks.stream().map(ItemStackComparable::toStack).collect(Collectors.toList());
        }

        @Override
        public void getIngredients(@NotNull IIngredients iIngredients) {
            iIngredients.setInputs(VanillaTypes.ITEM, getStacks());
            iIngredients.setOutput(HiiragiIngredientTypes.MATERIAL, materialStack);
        }

        @Override
        public void drawInfo(@NotNull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
            minecraft.fontRenderer.drawString(materialStack.getTranslatedName(), 24, 10, HiiragiColor.WHITE.getRGB());
        }

    }

}