package hiiragi283.material.compat.jei

import hiiragi283.material.api.recipe.IMachineRecipe
import hiiragi283.material.util.hiiragiLocation
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.item.ItemStack

class MachineRecipeCategory(
    val type: IMachineRecipe.Type,
    guiHelper: IGuiHelper
) : HiiragiRecipeCategory<MachineRecipeCategory.Wrapper>(HiiragiJEIPlugin.getRecipeTypeID(type), guiHelper) {

    override val backGround: IDrawableStatic =
        guiHelper.createDrawable(hiiragiLocation("textures/gui/jei/module_machine.png"), 0, 0, 176, 88)

    private fun getSlotPosX(index: Int): Int = 8 + 18 * (index + 1)

    private fun getSlotPosY(index: Int): Int = 18 * (index + 1)

    override fun setRecipe(iRecipeLayout: IRecipeLayout, wrapper: Wrapper, iIngredients: IIngredients) {
        //init slot position
        (0..5).forEach { iRecipeLayout.itemStacks.init(it, true, getSlotPosX(it % 3) - 1, getSlotPosY(it / 3) - 1) }
        (0..5).forEach { iRecipeLayout.itemStacks.init(it + 6, true, getSlotPosX(it % 3 + 4) - 1, getSlotPosY(it / 3) - 1) }
        (0..2).forEach {
            iRecipeLayout.fluidStacks.init(
                it,
                true,
                getSlotPosX(it % 3),
                getSlotPosY(2),
                16,
                16,
                64000,
                false,
                null
            )
        }
        (0..2).forEach {
            iRecipeLayout.fluidStacks.init(
                it + 3,
                true,
                getSlotPosX(it % 3 + 4),
                getSlotPosY(2),
                16,
                16,
                64000,
                false,
                null
            )
        }
        //Input - ItemStack
        (0..5).forEach { index ->
            iRecipeLayout.itemStacks[index] = wrapper.inputItems.getOrElse(index) { listOf(ItemStack.EMPTY) }
        }
        //Input - FluidStack
        wrapper.inputFluids.indices.forEach { index ->
            iRecipeLayout.fluidStacks[index] = wrapper.inputFluids[index]
        }
        //Output - ItemStack
        (0..5).forEach { index ->
            iRecipeLayout.itemStacks[index + 6] = wrapper.outputItems.getOrElse(index) { ItemStack.EMPTY }
        }
        //Output - FluidStack
        wrapper.outputFluids.indices.forEach { index ->
            iRecipeLayout.fluidStacks[index + 3] = wrapper.outputFluids[index]
        }
    }

    class Wrapper(recipe: IMachineRecipe) : IMachineRecipe by recipe, IRecipeWrapper {

        override fun getIngredients(iIngredients: IIngredients) {
            iIngredients.setInputLists(VanillaTypes.ITEM, inputItems)
            iIngredients.setInputs(VanillaTypes.FLUID, inputFluids)
            iIngredients.setOutputs(VanillaTypes.ITEM, outputItems)
            iIngredients.setOutputs(VanillaTypes.FLUID, outputFluids)
        }

    }

}