package hiiragi283.api.recipe

import hiiragi283.api.HiiragiRegistry
import hiiragi283.api.part.HiiragiPart
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import java.util.*

/**
 * Drop chance should be ranged in 0 to 100
 */

class CrushingRecipe(
    private val input: HiiragiPart,
    private val outputs: Map<ItemStack, Int>
) : HiiragiRecipe<CrushingRecipe>() {

    fun getInput(): HiiragiPart = input.copy()

    fun getAllOutputs(): List<ItemStack> = outputs.keys.toList()

    fun getWeightedOutputs(): List<ItemStack> {
        val random = Random().nextInt(100)
        return outputs.filter { it.value >= random }.map { it.key }
    }

    fun matches(entry: Any): Boolean = when (entry) {
        is IBlockState -> input in HiiragiRegistry.getParts(entry)
        is ItemStack -> input in HiiragiRegistry.getParts(entry)
        else -> false
    }

    //    IRecipeWrapper    //

    override fun getIngredients(p0: IIngredients) {
        p0.setInputs(VanillaTypes.ITEM, input.getAllItemStack().toMutableList())
        p0.setOutputs(VanillaTypes.ITEM, getAllOutputs().toMutableList())
    }

}