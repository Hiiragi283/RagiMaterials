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
import ragi_materials.core.recipe.ForgeFurnaceRecipe

class FFCategory(guiHelper: IGuiHelper) : JEICategoryBase<ForgeFurnaceRecipe> {

    companion object {
        fun registerCategory(registry: IRecipeCategoryRegistration) {
            if (RagiConfig.module.enableMetallurgy) registry.addRecipeCategories(FFCategory(registry.jeiHelpers.guiHelper))
        }

        fun register(registry: IModRegistry) {
            if (RagiConfig.module.enableMetallurgy) {
                registry.handleRecipes(ForgeFurnaceRecipe::class.java, { ForgeFurnaceRecipe(it) }, JEICore.ForgeFurnace)
                registry.addRecipes(RagiRegistry.FF_RECIPE.valuesCollection, JEICore.ForgeFurnace)
                registry.addRecipeCatalyst(ItemStack(RagiRegistry.BlockForgeFurnace), JEICore.ForgeFurnace)
                registry.addRecipeCatalyst(ItemStack(RagiRegistry.BlockBlazingForge), JEICore.ForgeFurnace)
            }
        }
    }

    override var backGround: IDrawableStatic = guiHelper.createDrawable(ResourceLocation(RagiMaterials.MOD_ID, "textures/gui/jei/stone_mill.png"), 1, 1, 54, 18)

    override fun getUid(): String = JEICore.ForgeFurnace

    override fun setRecipe(layout: IRecipeLayout, wrapper: ForgeFurnaceRecipe, ingredients: IIngredients) {
        //Input
        layout.itemStacks.init(0, true, 0, 0)
        layout.itemStacks[0] = wrapper.getInput()
        //Output
        layout.itemStacks.init(1, false, 36, 0)
        layout.itemStacks[1] = wrapper.getOutput()
    }
}