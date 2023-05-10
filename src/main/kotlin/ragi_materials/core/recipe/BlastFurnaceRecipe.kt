package ragi_materials.core.recipe

import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.items.IItemHandler
import ragi_materials.core.util.getFluidStack
import ragi_materials.core.util.wrapper.ItemStackWrapper

data class BlastFurnaceRecipe private constructor(
        private val ore: ItemStack,
        private val fuel: ItemStack,
        private val flux: ItemStack,
        private val output: FluidStack,
        private val slag: ItemStack
) : RecipeBase<BlastFurnaceRecipe>() {

    constructor(recipe: BlastFurnaceRecipe) : this(recipe.ore, recipe.fuel, recipe.flux, recipe.output, recipe.slag)

    fun getInputOre(): ItemStack = ore.copy()

    fun getInputFuel(): ItemStack = fuel.copy()

    fun getInputFlux(): ItemStack = flux.copy()

    fun getOutput(): FluidStack = output.copy()

    fun getOutputSlag(): ItemStack = slag.copy()

    override fun match(inventory: IItemHandler): Boolean {
        return matchSlot(inventory, 0, ore) && matchSlot(inventory, 1, fuel) && matchSlot(inventory, 2, flux)
    }

    private fun matchSlot(inventory: IItemHandler, slot: Int, stack: ItemStack): Boolean = ItemStackWrapper(inventory.getStackInSlot(slot), 1).moreThan(ItemStackWrapper(stack))

    //    IRecipeWrapper    //

    override fun getIngredients(ing: IIngredients) {
        ing.setInputs(VanillaTypes.ITEM, mutableListOf(getInputOre(), getInputFuel(), getInputFlux()))
        ing.setOutputs(VanillaTypes.FLUID, mutableListOf(getOutput()))
        ing.setOutputs(VanillaTypes.ITEM, mutableListOf(getOutputSlag()))
    }

    class Builder {

        var ore: ItemStack = ItemStack.EMPTY
        var fuel: ItemStack = ItemStack.EMPTY
        var flux: ItemStack = ItemStack.EMPTY
        var fluid: FluidStack = getFluidStack("water", 1000)
        var slag: ItemStack = ItemStack.EMPTY

        fun setOre(stack: ItemStack) = also { ore = stack }

        fun setFuel(stack: ItemStack) = also { fuel = stack }

        fun setFlux(stack: ItemStack) = also { flux = stack }

        fun setOutput(stack: FluidStack) = also { fluid = stack }

        fun setSlag(stack: ItemStack) = also { slag = stack }

        fun build() = BlastFurnaceRecipe(ore, fuel, flux, fluid, slag)

    }
}