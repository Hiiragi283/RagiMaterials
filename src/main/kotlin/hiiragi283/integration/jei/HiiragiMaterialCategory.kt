package hiiragi283.integration.jei

import hiiragi283.api.recipe.HiiragiMaterialRecipe
import hiiragi283.material.util.hiiragiLocation
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients

class HiiragiMaterialCategory(guiHelper: IGuiHelper) :
    HiiragiRecipeCategory<HiiragiMaterialRecipe>(MATERIAL, guiHelper) {

    override val backGround: IDrawableStatic =
        guiHelper.createDrawable(hiiragiLocation("textures/gui/jei/material_info.png"), 0, 0, 170, 116)

    override fun setRecipe(layout: IRecipeLayout, recipe: HiiragiMaterialRecipe, iIngredients: IIngredients) {
        //MaterialStack
        getMaterialStacks(layout).init(0, false, 4 + 1, 4 + 1)
        getMaterialStacks(layout)[0] = recipe.getMaterial()
        //ItemStack
        for (i in recipe.getStacks().indices) {
            layout.itemStacks.init(i, true, 18 * (i % 9) + 4, 18 * (i / 9) + 18 + 4)
            layout.itemStacks[i] = recipe.getStacks()[i]
        }
    }
}