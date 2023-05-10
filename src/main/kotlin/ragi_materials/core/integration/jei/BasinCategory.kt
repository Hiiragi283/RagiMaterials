package ragi_materials.core.integration.jei

import mezz.jei.api.IGuiHelper
import mezz.jei.api.IModRegistry
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.recipe.IRecipeCategoryRegistration
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import ragi_materials.core.RagiMaterials
import ragi_materials.core.RagiRegistry
import ragi_materials.core.config.RagiConfig
import ragi_materials.core.recipe.BasinRecipe

class BasinCategory(guiHelper: IGuiHelper) : JEICategoryBase<BasinRecipe> {

    companion object {
        fun registerCategory(registry: IRecipeCategoryRegistration) {
            if (RagiConfig.module.enableMain) registry.addRecipeCategories(BasinCategory(registry.jeiHelpers.guiHelper))
        }

        fun register(registry: IModRegistry) {
            if (RagiConfig.module.enableMain) {
                registry.handleRecipes(BasinRecipe::class.java, { BasinRecipe(it) }, JEICore.Basin)
                registry.addRecipes(RagiRegistry.BASIN_RECIPE.valuesCollection, JEICore.Basin)
                registry.addRecipeCatalyst(ItemStack(RagiRegistry.BlockBasin), JEICore.Basin)
            }
        }
    }

    override var backGround: IDrawableStatic = guiHelper.createDrawable(ResourceLocation(RagiMaterials.MOD_ID, "textures/gui/jei/basin.png"), 0, 0, 135, 17)

    override fun getUid(): String = JEICore.Basin

    override fun setRecipe(layout: IRecipeLayout, wrapper: BasinRecipe, ingredients: IIngredients) {
        layout.itemStacks.init(0, true, 0, 0)
        layout.itemStacks[0] = wrapper.getInput()

        layout.fluidStacks.init(0, true, 18 * 2 + 1, 1, 16, 16, 4000, true, null)
        layout.fluidStacks[0] = wrapper.getInputFluid()

        layout.itemStacks.init(1, false, 18 * 4, 0)
        layout.itemStacks[1] = wrapper.getOutputs()[0]
        layout.itemStacks.init(2, false, 18 * 5, 0)
        layout.itemStacks[2] = wrapper.getOutputs()[1]
        layout.itemStacks.init(3, false, 18 * 6, 0)
        layout.itemStacks[3] = wrapper.getOutputs()[2]
    }

}