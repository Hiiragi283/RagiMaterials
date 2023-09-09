package hiiragi283.material.util;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class HiiragiIngredient extends Ingredient {

    public HiiragiIngredient(ItemStack... stacks) {
        super(stacks);
    }

    public HiiragiIngredient(String oreDict) {
        super(OreDictionary.getOres(oreDict).toArray(new ItemStack[0]));
    }

}