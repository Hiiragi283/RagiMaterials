package hiiragi283.integration.jei

import hiiragi283.api.recipe.RockGenerationRecipe
import hiiragi283.material.util.hiiragiLocation
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack

class RockGenerationCategory(guiHelper: IGuiHelper) :
    HiiragiRecipeCategory<RockGenerationRecipe>(ROCK_GENERATION, guiHelper) {

    override val backGround: IDrawableStatic =
        guiHelper.createDrawable(hiiragiLocation("textures/gui/jei/rock_generation.png"), 0, 0, 18 * 5, 18 * 3)

    override fun setRecipe(layout: IRecipeLayout, recipe: RockGenerationRecipe, iIngredients: IIngredients) {
        //input
        layout.itemStacks.init(0, true, 18 * 2, 0)
        layout.itemStacks[0] = recipe.getInput()
        //output
        layout.itemStacks.init(1, false, 18 * 2, 18 * 2)
        layout.itemStacks[1] = recipe.getOutput()
        //water
        layout.fluidStacks.init(0, true, 0 + 1, 18 * 2 + 1)
        layout.fluidStacks[0] = FluidStack(FluidRegistry.WATER, 1000)
        //lava
        layout.fluidStacks.init(1, true, 18 * 4 + 1, 18 * 2 + 1)
        layout.fluidStacks[1] = FluidStack(FluidRegistry.LAVA, 1000)
    }

}