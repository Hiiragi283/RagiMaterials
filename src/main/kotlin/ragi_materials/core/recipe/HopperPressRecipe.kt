package ragi_materials.core.recipe

import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.items.IItemHandler
import ragi_materials.core.util.getFluidStack
import ragi_materials.core.util.wrapper.ItemStackWrapper

class HopperPressRecipe private constructor(private val input: ItemStack, private val output: FluidStack) : RecipeBase<HopperPressRecipe>() {

    constructor(recipe: HopperPressRecipe) : this(recipe.getInput(), recipe.getOutput())

    fun getInput(): ItemStack = input.copy()

    fun getOutput(): FluidStack = output.copy()

    override fun match(inventory: IItemHandler): Boolean {
        return ItemStackWrapper(inventory.getStackInSlot(0)).moreThan(ItemStackWrapper(getInput()))
    }

    fun match(stack: ItemStack): Boolean {
        return ItemStackWrapper(stack).moreThan(ItemStackWrapper(getInput()))
    }

    //    IRecipeWrapper    //

    override fun getIngredients(ingredients: IIngredients) {
        ingredients.setInputs(VanillaTypes.ITEM, mutableListOf(getInput()))
        ingredients.setOutputs(VanillaTypes.FLUID, mutableListOf(getOutput()))
    }

    class Builder {

        var input: ItemStack = ItemStack.EMPTY
        var output = getFluidStack("water", 1000)

        fun setInput(stack: ItemStack) = also { input = stack }

        fun setOutput(stack: FluidStack) = also { output = stack }

        fun build() = HopperPressRecipe(input, output)

    }

}