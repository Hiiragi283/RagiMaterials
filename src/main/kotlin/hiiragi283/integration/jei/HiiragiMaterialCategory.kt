package hiiragi283.integration.jei

import hiiragi283.api.material.MaterialStack
import hiiragi283.integration.jei.ingredients.HiiragiIngredientTypes
import hiiragi283.material.util.hiiragiLocation
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IGuiIngredientGroup
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients

class HiiragiMaterialCategory(guiHelper: IGuiHelper) :
    HiiragiRecipeCategory<HiiragiMaterialRecipe>(MATERIAL, guiHelper) {

    override val backGround: IDrawableStatic =
        guiHelper.createDrawable(hiiragiLocation("textures/gui/jei/material_info.png"), 0, 0, 170, 116)

    override fun setRecipe(layout: IRecipeLayout, wrapper: HiiragiMaterialRecipe, p2: IIngredients) {
        val groupMaterial: IGuiIngredientGroup<MaterialStack> =
            layout.getIngredientsGroup(HiiragiIngredientTypes.MATERIAL)
        groupMaterial.init(0, false, 4 + 1, 4 + 1)
        groupMaterial.set(0, wrapper.stack)
        (0 until wrapper.items.size).forEach {
            layout.itemStacks.init(it, true, 18 * (it % 9) + 4, 18 * (it / 9) + 18 + 4)
            layout.itemStacks[it] = wrapper.items[it]
        }
    }

}