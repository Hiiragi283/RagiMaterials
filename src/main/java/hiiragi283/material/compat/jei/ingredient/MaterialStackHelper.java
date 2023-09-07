package hiiragi283.material.compat.jei.ingredient;

import hiiragi283.material.RMReference;
import hiiragi283.material.api.material.HiiragiMaterial;
import hiiragi283.material.api.material.MaterialStack;
import mezz.jei.api.ingredients.IIngredientHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

public class MaterialStackHelper implements IIngredientHelper<MaterialStack> {

    @Nullable
    @Override
    public MaterialStack getMatch(@NotNull Iterable<MaterialStack> iterable, @NotNull MaterialStack materialStack) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterable.iterator(), Spliterator.ORDERED), false)
                .filter(materialStack::equalsMaterial)
                .findFirst().orElse(MaterialStack.EMPTY);
    }

    @NotNull
    @Override
    public String getDisplayName(@NotNull MaterialStack materialStack) {
        return materialStack.getTranslatedName();
    }

    @NotNull
    @Override
    public String getUniqueId(@NotNull MaterialStack materialStack) {
        return materialStack.getMaterial().map(HiiragiMaterial::name).orElse("");
    }

    @NotNull
    @Override
    public String getWildcardId(@NotNull MaterialStack materialStack) {
        return getUniqueId(materialStack);
    }

    @NotNull
    @Override
    public String getModId(@NotNull MaterialStack materialStack) {
        return RMReference.MOD_ID;
    }

    @NotNull
    @Override
    public String getResourceId(@NotNull MaterialStack materialStack) {
        return materialStack.getMaterial().map(HiiragiMaterial::name).orElse("");
    }

    @NotNull
    @Override
    public MaterialStack copyIngredient(@NotNull MaterialStack materialStack) {
        return new MaterialStack(materialStack, materialStack.amount);
    }

    @NotNull
    @Override
    public String getErrorInfo(@Nullable MaterialStack materialStack) {
        return materialStack != null ? "Errored with " + materialStack : "null";
    }

}