package hiiragi283.api.recipe

import hiiragi283.material.util.isSameWithoutCount
import hiiragi283.material.util.toLocation
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import net.minecraft.item.ItemStack

class RockGenerationRecipe(
    input: ItemStack,
    output: ItemStack
) : HiiragiRecipe<RockGenerationRecipe>() {

    private val input: ItemStack = ItemStack(input.item, 1, input.metadata)
    private val output: ItemStack = ItemStack(output.item, 1, output.metadata)

    constructor(stack: ItemStack) : this(stack, stack)

    init {
        this.setRegistryName(input.toLocation())
    }

    fun getInput(): ItemStack = input.copy()

    fun getOutput(): ItemStack = output.copy()

    fun matches(stack: ItemStack): Boolean = input.isSameWithoutCount(stack)

    //    IRecipeWrapper    //
    override fun getIngredients(iIngredients: IIngredients) {
        iIngredients.setInput(VanillaTypes.ITEM, getInput())
        iIngredients.setOutput(VanillaTypes.ITEM, getOutput())
    }
}