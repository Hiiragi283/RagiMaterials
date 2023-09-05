package hiiragi283.material.compat.jei;

import hiiragi283.material.RMReference;
import hiiragi283.material.api.material.MaterialStack;
import hiiragi283.material.compat.jei.ingredient.HiiragiIngredientTypes;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IGuiIngredientGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.resources.I18n;
import org.jetbrains.annotations.NotNull;

public abstract class HiiragiRecipeCategory<T extends IRecipeWrapper> implements IRecipeCategory<T> {

    protected final String id;
    protected final IGuiHelper guiHelper;

    public HiiragiRecipeCategory(String id, IGuiHelper guiHelper) {
        this.id = id;
        this.guiHelper = guiHelper;
    }

    protected IGuiIngredientGroup<MaterialStack> getMaterialStacks(IRecipeLayout iRecipeLayout) {
        return iRecipeLayout.getIngredientsGroup(HiiragiIngredientTypes.MATERIAL);
    }

    //    HiiragiRecipeCategory    //

    @NotNull
    @Override
    public String getUid() {
        return id;
    }

    @NotNull
    @Override
    public String getTitle() {
        return I18n.format("gui." + id);
    }

    @NotNull
    @Override
    public String getModName() {
        return RMReference.MOD_NAME;
    }

}