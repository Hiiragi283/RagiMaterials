package hiiragi283.material.compat.jei

import hiiragi283.material.api.ingredient.FluidIngredient
import hiiragi283.material.api.ingredient.ItemIngredient
import hiiragi283.material.api.item.RecipeModuleItem
import hiiragi283.material.api.machine.IMachineRecipe
import hiiragi283.material.api.machine.MachineType
import hiiragi283.material.util.*
import mezz.jei.api.IGuiHelper
import mezz.jei.api.gui.IDrawable
import mezz.jei.api.gui.IDrawableStatic
import mezz.jei.api.gui.IRecipeLayout
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.client.Minecraft
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack

class MachineRecipeCategory(
    val type: MachineType,
    guiHelper: IGuiHelper
) : HiiragiRecipeCategory<MachineRecipeCategory.Wrapper>(HiiragiJEIPlugin.getRecipeTypeID(type), guiHelper) {

    override val backGround: IDrawableStatic =
        guiHelper.createDrawable(hiiragiLocation("textures/gui/jei/module_machine.png"), 0, 0, 176, 88)

    override val iconDrawable: IDrawable? =
        RecipeModuleItem.REGISTRY[type]!!.itemStack().let { guiHelper.createDrawableIngredient(it) }

    private fun getSlotPosX(index: Int): Int = 8 + 18 * (index + 1)

    private fun getSlotPosY(index: Int): Int = 18 * (index + 1)

    override fun setRecipe(iRecipeLayout: IRecipeLayout, wrapper: Wrapper, iIngredients: IIngredients) {
        //Input - ItemStack
        wrapper.getInputItems().forEachIndexed { index: Int, ingredient: ItemIngredient ->
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
            val index1: Int = index + wrapper.getInputItems().size
            iRecipeLayout.itemStacks.init(index1, false, getSlotPosX(index % 3 + 4) - 1, getSlotPosY(index / 3) - 1)
            iRecipeLayout.itemStacks[index1] = itemStack.copy()
        }
        //Output - FluidStack
        wrapper.getOutputFluids().forEachIndexed { index: Int, fluidStack: FluidStack ->
            val index1: Int = index + wrapper.getInputFluids().size
            if (fluidStack.amount > 0) {
                iRecipeLayout.fluidStacks.init(
                    index1,
                    false,
                    getSlotPosX(index % 3 + 4),
                    getSlotPosY(2),
                    16,
                    16,
                    fluidStack.amount,
                    false,
                    null
                )
                iRecipeLayout.fluidStacks[index1] = fluidStack.copy()
            } else {
                iRecipeLayout.fluidStacks.init(index, false, getSlotPosX(index % 3 + 4), getSlotPosY(2))
                iRecipeLayout.fluidStacks[index1] = fluidStack.copy().copyKt(amount = 1000)
            }
        }
    }

    class Wrapper(recipe: IMachineRecipe) : IMachineRecipe by recipe, IRecipeWrapper {

        override fun getIngredients(iIngredients: IIngredients) {
            iIngredients.setInputLists(
                VanillaTypes.ITEM,
                getInputItems().map(ItemIngredient::getMatchingStacks).map(Collection<ItemStack>::toList)
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

        private fun getSlotPosX(index: Int): Int = 8 + 18 * (index + 1)

        private fun getSlotPosY(index: Int): Int = 18 * (index + 1)

        override fun drawInfo(minecraft: Minecraft, recipeWidth: Int, recipeHeight: Int, mouseX: Int, mouseY: Int) {
            minecraft.fontRenderer.drawString(
                traitsString(),
                getSlotPosX(0),
                getSlotPosY(3) + 2,
                HiiragiColor.DARK_GRAY.rgb
            )
        }

    }

}