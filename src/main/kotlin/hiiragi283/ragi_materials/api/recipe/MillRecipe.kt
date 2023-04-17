package hiiragi283.ragi_materials.api.recipe

import hiiragi283.ragi_materials.api.stack.ItemMetaNBT
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.Optional
import net.minecraftforge.registries.IForgeRegistryEntry

@Optional.Interface(iface = "mezz.jei.api.recipe.IRecipeWrapper", modid = "jei")
data class MillRecipe private constructor(private var input: ItemStack, private var output: ItemStack)
    : IForgeRegistryEntry.Impl<MillRecipe>(), IRecipeWrapper {

    constructor(recipe: MillRecipe) : this(recipe.getInput(), recipe.getOutput())

    fun getInput(): ItemStack = input.copy()

    fun getOutput(): ItemStack = output.copy()

    fun match(input: ItemStack) = ItemMetaNBT(this.input) == ItemMetaNBT(input)

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