package hiiragi283.material.util;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

@ParametersAreNonnullByDefault
public class CraftingBuilder {

    private final ResourceLocation location;
    private final ItemStack output;

    public CraftingBuilder(ItemStack output) {
        this(HiiragiUtil.toLocation(output, '_'), output);
    }

    public CraftingBuilder(String location, ItemStack output) {
        this(new ResourceLocation(location), output);
    }

    public CraftingBuilder(ResourceLocation location, ItemStack output) {
        this.location = location;
        this.output = output;
    }


    //    Shaped    //

    private final List<Object> PARAM_SHAPED = new ArrayList<>();

    public CraftingBuilder setPattern(String... patterns) {
        PARAM_SHAPED.add(patterns[0]);
        PARAM_SHAPED.add(patterns[1]);
        PARAM_SHAPED.add(patterns[2]);
        return this;
    }

    public CraftingBuilder setIngredient(Character mark, ItemStack input) {
        PARAM_SHAPED.add(mark);
        PARAM_SHAPED.add(input);
        return this;
    }

    public CraftingBuilder setIngredient(Character mark, String input) {
        PARAM_SHAPED.add(mark);
        PARAM_SHAPED.add(input);
        return this;
    }

    //    Shapeless    //

    private final List<Ingredient> PARAM_SHAPELESS = new ArrayList<>();

    public CraftingBuilder addIngredient(Ingredient ingredient) {
        PARAM_SHAPELESS.add(ingredient);
        return this;
    }

    //    Build    //

    public void build() {
        if (!PARAM_SHAPED.isEmpty()) {
            GameRegistry.addShapedRecipe(location, null, output, PARAM_SHAPED.toArray());
        } else if (!PARAM_SHAPELESS.isEmpty()) {
            GameRegistry.addShapelessRecipe(location, null, output, PARAM_SHAPELESS.toArray(new Ingredient[0]));
        } else {
            throw new IllegalStateException("Either shaped parameters or shapeless parameters should be empty!");
        }
    }

}