package ragi_materials.core.integration.jei

import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import net.minecraft.util.ResourceLocation
import ragi_materials.core.RagiMaterials
import ragi_materials.core.recipe.LaboRecipe

class LaboCategory(guiHelper: IGuiHelper) : JEICategoryBase<LaboRecipe>() {

    override var backGround: IDrawableStatic = guiHelper.createDrawable(ResourceLocation(RagiMaterials.MOD_ID, "textures/gui/jei/labo_table.png"), 1, 1, 90, 54)

    override fun getUid(): String = JEICore.LaboTable

    override fun setRecipe(layout: IRecipeLayout, wrapper: LaboRecipe, ingredients: IIngredients) {
        //Inputs
        for (i in 0..4) {
            layout.itemStacks.init(i, true, 18 * i, 18 * 0)
            layout.itemStacks[i] = wrapper.getInput(i)
        }
        //Catalyst
        layout.itemStacks.init(5, true, 18 * 1, 18 * 1)
        layout.itemStacks[5] = wrapper.getCatalyst()
        //Outputs
        for (i in wrapper.getOutputs().indices) {
            layout.itemStacks.init(i + 6, false, 18 * i, 18 * 2)
            layout.itemStacks[i + 6] = wrapper.getOutput(i)
        }
    }
}