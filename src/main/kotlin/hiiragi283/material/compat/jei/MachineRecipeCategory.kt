package hiiragi283.material.compat.jei

import hiiragi283.material.api.recipe.IMachineRecipe
import hiiragi283.material.util.copyKt
import hiiragi283.material.util.hiiragiLocation
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
        wrapper.inputItems.forEachIndexed { index: Int, list: List<ItemStack> ->
            iRecipeLayout.itemStacks.init(index, true, getSlotPosX(index % 3) - 1, getSlotPosY(index / 3) - 1)
            iRecipeLayout.itemStacks[index] = list
        }
        //Input - FluidStack
        wrapper.inputFluids.forEachIndexed { index: Int, fluidStack: FluidStack ->
            if (fluidStack.amount > 0) {
                iRecipeLayout.fluidStacks.init(
                    index,
                    true,
                    getSlotPosX(index % 3),
                    getSlotPosY(2),
                    16,
                    16,
                    fluidStack.amount,
                    false,
                    null
                )
                iRecipeLayout.fluidStacks[index] = fluidStack.copy()
            } else {
                iRecipeLayout.fluidStacks.init(index, true, getSlotPosX(index % 3), getSlotPosY(2))
                iRecipeLayout.fluidStacks[index] = fluidStack.copyKt(amount = 1000)
            }
        }
        //Output - ItemStack
        wrapper.outputItems.forEachIndexed { index: Int, itemStack: ItemStack ->
            iRecipeLayout.itemStacks.init(index + 6, false, getSlotPosX(index % 3 + 4) - 1, getSlotPosY(index / 3) - 1)
            iRecipeLayout.itemStacks[index + 6] = itemStack
        }
        //Output - FluidStack
        wrapper.outputFluids.forEachIndexed { index: Int, fluidStack: FluidStack ->
            if (fluidStack.amount > 0) {
                iRecipeLayout.fluidStacks.init(
                    index + 3,
                    false,
                    getSlotPosX(index % 3 + 4),
                    getSlotPosY(2),
                    16,
                    16,
                    fluidStack.amount,
                    false,
                    null
                )
                iRecipeLayout.fluidStacks[index + 3] = fluidStack.copy()
            } else {
                iRecipeLayout.fluidStacks.init(index, false, getSlotPosX(index % 3 + 4), getSlotPosY(2))
                iRecipeLayout.fluidStacks[index + 3] = fluidStack.copyKt(amount = 1000)
            }
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