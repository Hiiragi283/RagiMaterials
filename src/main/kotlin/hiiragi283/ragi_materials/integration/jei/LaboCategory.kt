package hiiragi283.ragi_materials.integration.jei

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.api.recipe.LaboRecipe
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawable
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import net.minecraft.util.ResourceLocation

class LaboCategory(guiHelper: IGuiHelper) : JEICategoryBase<LaboRecipe>() {

    var background: IDrawableStatic = guiHelper.createDrawable(ResourceLocation(RagiMaterials.MOD_ID, "textures/gui/jei/labo_table.png"), 1, 1, 90, 54)

    override fun getUid(): String = JEICore.LaboTable

    override fun getBackground(): IDrawable = background

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
            layout.itemStacks.init(i + 6, true, 18 * i, 18 * 2)
            layout.itemStacks[i + 6] = wrapper.getOutput(i)
        }
    }
}