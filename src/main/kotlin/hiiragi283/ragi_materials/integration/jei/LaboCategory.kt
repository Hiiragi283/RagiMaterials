package hiiragi283.ragi_materials.integration.jei

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.recipe.LaboRecipe
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawable
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import net.minecraft.util.ResourceLocation

class LaboCategory(guiHelper: IGuiHelper) : JEICategoryBase<LaboRecipe.Wrapper>() {

    var background: IDrawableStatic = guiHelper.createDrawable(ResourceLocation(Reference.MOD_ID, "textures/gui/jei/labo_table.png"), 1, 1, 90, 54)

    override fun getUid(): String = JEICore.LaboTable

    override fun getBackground(): IDrawable = background

    override fun setRecipe(layout: IRecipeLayout, wrapper: LaboRecipe.Wrapper, ingredients: IIngredients) {

        val inputs = wrapper.inputs
        val outputs = wrapper.output
        val catalyst = wrapper.catalyst

        for (i in 0..4) {
            layout.itemStacks.init(i, true, i * 18, 0)
            layout.itemStacks[i] = inputs[i]
        }
        layout.itemStacks.init(5, true, 9 + 5 * 18, 0)
        layout.itemStacks[5] = catalyst
        for (i in outputs.indices) {
            layout.itemStacks.init(i + 6, true, i * 18, 18 * 2)
            layout.itemStacks[i + 6] = outputs[i]
        }
    }
}