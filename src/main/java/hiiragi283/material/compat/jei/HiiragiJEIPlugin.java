package hiiragi283.material.compat.jei;

import hiiragi283.material.HiiragiItems;
import hiiragi283.material.RMReference;
import hiiragi283.material.RagiMaterials;
import hiiragi283.material.api.material.HiiragiMaterial;
import hiiragi283.material.compat.jei.ingredient.HiiragiIngredientTypes;
import hiiragi283.material.compat.jei.ingredient.MaterialStackHelper;
import hiiragi283.material.compat.jei.ingredient.MaterialStackRenderer;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;

@JEIPlugin
public class HiiragiJEIPlugin implements IModPlugin {

    public static final String MATERIAL = RMReference.MOD_ID + ".material";

    public HiiragiJEIPlugin() {
        RagiMaterials.LOGGER.info("Enabled Integration: HEI");
    }

    @Override
    public void registerIngredients(@NotNull IModIngredientRegistration registry) {
        registry.register(
                HiiragiIngredientTypes.MATERIAL,
                HiiragiMaterial.REGISTRY.getValues().stream().map(HiiragiMaterial::toMaterialStack).collect(Collectors.toList()),
                new MaterialStackHelper(),
                new MaterialStackRenderer()
        );
    }

    @Override
    public void registerCategories(@NotNull IRecipeCategoryRegistration registry) {
        MaterialCategory.register(registry);
    }

    @Override
    public void register(@NotNull IModRegistry registry) {
        //HiiragiMaterial
        registry.handleRecipes(MaterialCategory.Recipe.class, wrapper -> wrapper, MATERIAL);
        registry.addRecipes(HiiragiMaterial.REGISTRY.getValues().stream().map(MaterialCategory.Recipe::new).collect(Collectors.toList()), MATERIAL);
        registry.addRecipeCatalyst(new ItemStack(HiiragiItems.MATERIAL_BOTTLE, 1, Short.MAX_VALUE), MATERIAL);
    }

}