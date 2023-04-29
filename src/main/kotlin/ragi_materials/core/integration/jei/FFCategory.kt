package ragi_materials.core.integration.jei

import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import net.minecraft.util.ResourceLocation
import ragi_materials.core.RagiMaterials
import ragi_materials.core.recipe.FFRecipe

class FFCategory(guiHelper: IGuiHelper) : JEICategoryBase<FFRecipe>() {

    override var backGround: IDrawableStatic = guiHelper.createDrawable(ResourceLocation(RagiMaterials.MOD_ID, "textures/gui/jei/forge_furnace.png"), 1, 1, 54, 18)

    override fun getUid(): String = JEICore.ForgeFurnace

    override fun setRecipe(layout: IRecipeLayout, wrapper: FFRecipe, ingredients: IIngredients) {
        //Input
        layout.itemStacks.init(0, true, 0, 0)
        layout.itemStacks[0] = wrapper.getInput()
        //Output
        layout.itemStacks.init(1, false, 36, 0)
        layout.itemStacks[1] = wrapper.getOutput()
    }
}