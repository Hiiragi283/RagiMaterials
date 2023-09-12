package hiiragi283.material.util;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntComparators;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@ParametersAreNonnullByDefault
public class HiiragiIngredientNew extends Ingredient {

    private final List<ItemStack> stacks = new ArrayList<>();

    private HiiragiIngredientNew(ItemStack... stack) {
        this.stacks.addAll(Arrays.asList(stack));
    }

    private HiiragiIngredientNew(String... oreDicts) {
        this.stacks.addAll(Arrays.stream(oreDicts).map(OreDictionary::getOres).collect(HiiragiCollectors.getNonNullListCollector()));
    }

    public static HiiragiIngredientNew ofItemStack(Collection<ItemStack> stacks) {
        return new HiiragiIngredientNew(stacks.toArray(new ItemStack[0]));
    }

    public static HiiragiIngredientNew ofOreDict(Collection<String> oreDicts) {
        return new HiiragiIngredientNew(oreDicts.toArray(new String[0]));
    }

    @Override
    public ItemStack @NotNull [] getMatchingStacks() {
        return stacks.toArray(new ItemStack[0]);
    }

    @NotNull
    @Override
    public IntList getValidItemStacksPacked() {
        IntList list = new IntArrayList(stacks.size());
        for (ItemStack stackIn : stacks) {
            if (stackIn.getMetadata() == OreDictionary.WILDCARD_VALUE) {
                NonNullList<ItemStack> nonNullList = NonNullList.create();
                stackIn.getItem().getSubItems(CreativeTabs.SEARCH, nonNullList);
                for (ItemStack item : nonNullList)
                    list.add(RecipeItemHelper.pack(item));
            } else {
                list.add(RecipeItemHelper.pack(stackIn));
            }
        }
        list.sort(IntComparators.NATURAL_COMPARATOR);
        return list;
    }

    @Override
    public boolean apply(@Nullable ItemStack stack) {
        if (stack == null) return false;
        for (ItemStack stackIn : this.stacks) {
            if (OreDictionary.itemMatches(stack, stackIn, false)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void invalidate() {
        stacks.clear();
    }

    @Override
    public boolean isSimple() {
        return true;
    }

}