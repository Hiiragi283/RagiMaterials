package hiiragi283.ragi_materials.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.jetbrains.annotations.NotNull;

public class RecipeEmpty extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {

    public RecipeEmpty(ResourceLocation location) {
        this.setRegistryName(location);
    }

    public RecipeEmpty(String registryName) {
        this.setRegistryName(registryName);
    }

    @Override
    public boolean matches(@NotNull InventoryCrafting inv, World world) {
        return false;
    }

    @Override
    public @NotNull ItemStack getCraftingResult(@NotNull InventoryCrafting inv) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canFit(int width, int height) {
        return false;
    }

    @Override
    public @NotNull ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }
}