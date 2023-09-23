package hiiragi283.material.compat.jei

import hiiragi283.material.api.recipe.IMachineRecipe
import hiiragi283.material.util.*
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack

class MachineRecipeCategory(
    val type: IMachineRecipe.Type,
    guiHelper: IGuiHelper
) : HiiragiRecipeCategory<MachineRecipeCategory.Wrapper>(HiiragiJEIPlugin.getRecipeTypeID(type), guiHelper) {

    override val backGround: IDrawableStatic =
        guiHelper.createDrawable(hiiragiLocation("textures/gui/jei/module_machine.png"), 0, 0, 176, 88)

    private fun getSlotPosX(index: Int): Int = 8 + 18 * (index + 1)

    private fun getSlotPosY(index: Int): Int = 18 * (index + 1)

    override fun setRecipe(iRecipeLayout: IRecipeLayout, wrapper: Wrapper, iIngredients: IIngredients) {
        //Input - ItemStack
        wrapper.getInputItems().forEachIndexed { index: Int, ingredient: HiiragiIngredient ->
            iRecipeLayout.itemStacks.init(index, true, getSlotPosX(index % 3) - 1, getSlotPosY(index / 3) - 1)
            iRecipeLayout.itemStacks[index] = ingredient.getMatchingStacks().map(ItemStack::copy)
        }
        //Input - FluidStack
        wrapper.getInputFluids().forEachIndexed { index: Int, ingredient: FluidIngredient ->
            if (ingredient.amount > 0) {
                iRecipeLayout.fluidStacks.init(
                    index,
                    true,
                    getSlotPosX(index % 3),
                    getSlotPosY(2),
                    16,
                    16,
                    ingredient.amount,
                    false,
                    null
                )
                iRecipeLayout.fluidStacks[index] = ingredient.getMatchingStack().map(FluidStack::copy)
            } else {
                iRecipeLayout.fluidStacks.init(index, true, getSlotPosX(index % 3), getSlotPosY(2))
                iRecipeLayout.fluidStacks[index] = ingredient.getMatchingStack().map { it.copyKt(amount = 1000) }
            }
        }
        //Output - ItemStack
        wrapper.getOutputItems().forEachIndexed { index: Int, itemStack: ItemStack ->
            iRecipeLayout.itemStacks.init(index + 6, false, getSlotPosX(index % 3 + 4) - 1, getSlotPosY(index / 3) - 1)
            iRecipeLayout.itemStacks[index + 6] = itemStack.copy()
        }
        //Output - FluidStack
        wrapper.getOutputFluids().forEachIndexed { index: Int, ingredient: FluidStack ->
            if (ingredient.amount > 0) {
                iRecipeLayout.fluidStacks.init(
                    index + 3,
                    false,
                    getSlotPosX(index % 3 + 4),
                    getSlotPosY(2),
                    16,
                    16,
                    ingredient.amount,
                    false,
                    null
                )
                iRecipeLayout.fluidStacks[index + 3] = ingredient.copy()
            } else {
                iRecipeLayout.fluidStacks.init(index, false, getSlotPosX(index % 3 + 4), getSlotPosY(2))
                iRecipeLayout.fluidStacks[index + 3] = ingredient.copyKt(amount = 1000)
            }
        }
    }

    class Wrapper(recipe: IMachineRecipe) : IMachineRecipe by recipe, IRecipeWrapper {

        override fun getIngredients(iIngredients: IIngredients) {
            iIngredients.setInputLists(
                VanillaTypes.ITEM,
                getInputItems().map(HiiragiIngredient::getMatchingStacks).map(Collection<ItemStack>::toList)
            )
            iIngredients.setInputLists(
                VanillaTypes.FLUID,
                getInputFluids().map(FluidIngredient::getMatchingStack).map(Collection<FluidStack>::toList)
            )
            iIngredients.setOutputs(
                VanillaTypes.ITEM,
                getOutputItems()
            )
            iIngredients.setOutputs(
                VanillaTypes.FLUID,
                getOutputFluids()
            )
        }

    }

}