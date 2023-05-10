package ragi_materials.core.recipe

import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.items.IItemHandler
import ragi_materials.core.util.wrapper.FluidStackWrapper
import ragi_materials.core.util.wrapper.ItemStackWrapper

class BasinRecipe private constructor(private val input: ItemStack, private val fluid: FluidStack?, private val outputs: Array<ItemStack>) : RecipeBase<BasinRecipe>() {

    constructor(recipe: BasinRecipe) : this(recipe.input, recipe.fluid, recipe.outputs)

    fun getInput(): ItemStack = input.copy()

    fun getInputFluid() = fluid?.copy()

    fun getOutputs() = outputs

    override fun match(inventory: IItemHandler): Boolean {
        return ItemStackWrapper(inventory.getStackInSlot(0)).moreThan(ItemStackWrapper(getInput()))
    }

    fun match(stack: ItemStack, fluidStack: FluidStack?): Boolean {
        val matchItem = ItemStackWrapper(stack).moreThan(ItemStackWrapper(getInput()))
        val matchFluid = FluidStackWrapper(fluidStack).moreThan(FluidStackWrapper(getInputFluid()))
        return matchItem && matchFluid
    }

    //    IRecipeWrapper    //

    override fun getIngredients(ingredients: IIngredients) {
        ingredients.setInputs(VanillaTypes.ITEM, mutableListOf(getInput()))
        ingredients.setInputs(VanillaTypes.FLUID, mutableListOf(getInputFluid()))
        ingredients.setOutputLists(VanillaTypes.ITEM, mutableListOf(getOutputs().toList()))
    }

    class Builder {

        var input: ItemStack = ItemStack.EMPTY
        var fluid: FluidStack? = null
        var outputs: Array<ItemStack> = arrayOf(ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY)

        fun setInput(stack: ItemStack) = also { input = stack }

        fun setInput(fluidStack: FluidStack) = also { fluid = fluidStack }

        fun setOutputPrime(stack: ItemStack) = also { outputs[0] = stack }

        fun setOutputSecond(stack: ItemStack) = also { outputs[1] = stack }

        fun setOutputTert(stack: ItemStack) = also { outputs[2] = stack }

        fun build() = BasinRecipe(input, fluid, outputs)

    }
}