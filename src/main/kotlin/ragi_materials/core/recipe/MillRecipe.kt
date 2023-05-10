package ragi_materials.core.recipe

import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import net.minecraft.item.ItemStack
import net.minecraftforge.items.IItemHandler
import ragi_materials.core.util.wrapper.ItemStackWrapper

data class MillRecipe private constructor(private var input: ItemStack, private var output: ItemStack)
    : RecipeBase<MillRecipe>() {

    constructor(recipe: MillRecipe) : this(recipe.getInput(), recipe.getOutput())

    fun getInput(): ItemStack = input.copy()

    fun getOutput(): ItemStack = output.copy()

    override fun match(inventory: IItemHandler) = ItemStackWrapper(inventory.getStackInSlot(0)).moreThan(ItemStackWrapper(getInput()))

    //    IRecipeWrapper    //

    override fun getIngredients(ings: IIngredients) {
        ings.setInputs(VanillaTypes.ITEM, mutableListOf(getInput()))
        ings.setOutputs(VanillaTypes.ITEM, mutableListOf(getOutput()))
    }

    class Builder {

        var input: ItemStack = ItemStack.EMPTY
        var output: ItemStack = ItemStack.EMPTY

        fun setInput(stack: ItemStack) = also { this.input = stack }

        fun setOutput(stack: ItemStack) = also { this.output = stack }

        fun build() = MillRecipe(input, output)

    }
}