package hiiragi283.ragi_materials.integration.jei.laboratory_table

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.integration.jei.JEICategoryBase
import hiiragi283.ragi_materials.integration.jei.JEICore
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawable
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import net.minecraft.util.ResourceLocation

class LaboCategory(guiHelper: IGuiHelper) : JEICategoryBase<LaboWrapper>(guiHelper) {

    var background: IDrawableStatic

    init {
        val location = ResourceLocation(Reference.MOD_ID, "textures/gui/jei/labo_table.png")
        background = guiHelper.createDrawable(location, 1, 1, 90, 54)
    }

    override fun getUid(): String  = JEICore.LaboTable

    override fun getBackground(): IDrawable = background

    override fun setRecipe(recipeLayout: IRecipeLayout, recipeWrapper: LaboWrapper, ingredients: IIngredients) {

        val inputs = recipeWrapper.inputs
        val outputs = recipeWrapper.output

        for (i in 0 .. 4) {
            recipeLayout.itemStacks.init(i, true, i * 18, 0)
            recipeLayout.itemStacks[i] = inputs[i]
        }
        for (i in outputs.indices) {
            recipeLayout.itemStacks.init(i + 5, true, i * 18, 18 * 2)
            recipeLayout.itemStacks[i + 5] = outputs[i]
        }
    }
}