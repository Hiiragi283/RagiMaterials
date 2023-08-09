package hiiragi283.api.recipe

import hiiragi283.api.part.HiiragiPart
import hiiragi283.api.part.PartRegistry
import hiiragi283.material.util.toFluidStack
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.oredict.OreDictionary


class CrucibleRecipe(
    val input: HiiragiPart,
    val tempMin: Int,
    output: Pair<String, Int>
) : HiiragiRecipe<CrucibleRecipe>() {

    val output: FluidStack? by lazy { output.toFluidStack() }

    constructor(oredict: String, minTemp: Int, output: Pair<String, Int>) : this(
        PartRegistry.getPart(oredict),
        minTemp,
        output
    )

    companion object {
        @JvmField
        val EMPTY = CrucibleRecipe(HiiragiPart.EMPTY, -1, "water" to 0)
    }

    fun isEmpty() = this == EMPTY

    fun matches(stack: ItemStack): Boolean {
        if (stack.isEmpty) return false
        return input in PartRegistry.getParts(stack)
    }

    //    IRecipeWrapper    //

    override fun getIngredients(p0: IIngredients) {
        p0.setInputs(VanillaTypes.ITEM, OreDictionary.getOres(input.getOreDict()))
        output?.let { p0.setOutputs(VanillaTypes.FLUID, mutableListOf(it)) }
    }

}