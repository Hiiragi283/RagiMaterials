package hiiragi283.integration.jei

import hiiragi283.api.material.MaterialStack
import hiiragi283.integration.jei.ingredients.HiiragiIngredientTypes
import hiiragi283.material.util.hiiragiLocation
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IGuiIngredientGroup
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients

class CrucibleCategory(guiHelper: IGuiHelper) : HiiragiRecipeCategory<CrucibleRecipe>(CRUCIBLE, guiHelper) {

    override val backGround: IDrawableStatic =
        guiHelper.createDrawable(hiiragiLocation("textures/gui/jei/process1_1_1_jei.png"), 1, 1, 18 * 5, 18)

    override fun setRecipe(layout: IRecipeLayout, wrapper: CrucibleRecipe, p2: IIngredients) {
        val groupMaterial: IGuiIngredientGroup<MaterialStack> =
            layout.getIngredientsGroup(HiiragiIngredientTypes.MATERIAL)
        //input
        groupMaterial.init(0, true, 0 + 1, 0 + 1)
        groupMaterial.set(0, wrapper.input)
        //cast
        layout.itemStacks.init(0, true, 18 * 2, 0)
        layout.itemStacks[0] = wrapper.cast.copy()
        //output
        layout.itemStacks.init(1, true, 18 * 4, 0)
        layout.itemStacks[1] = wrapper.output.copy()
    }

}