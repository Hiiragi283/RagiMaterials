package hiiragi283.api.recipe

import hiiragi283.api.part.HiiragiPart
import hiiragi283.api.part.PartRegistry
import hiiragi283.core.util.toFluidStack
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper
import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fml.common.Optional
import net.minecraftforge.oredict.OreDictionary
import net.minecraftforge.registries.IForgeRegistryEntry

@Optional.Interface(iface = "mezz.jei.api.recipe.IRecipeWrapper", modid = "jei")
class CrucibleRecipe(
    val input: HiiragiPart,
    val tempMin: Int,
    output: Pair<String, Int>
) : IForgeRegistryEntry.Impl<CrucibleRecipe>(), IRecipeWrapper {

    val output: FluidStack by lazy { output.toFluidStack() }

    constructor(oredict: String, minTemp: Int, output: Pair<String, Int>) : this(
        PartRegistry.getPart(oredict),
        minTemp,
        output
    )

    constructor(recipe: CrucibleRecipe) : this(
        recipe.input,
        recipe.tempMin,
        recipe.output.fluid.name to recipe.output.amount
    )

    fun matches(stack: ItemStack): Boolean {
        if (stack.isEmpty) return false
        return input in PartRegistry.getParts(stack)
    }

    //    IRecipeWrapper    //

    override fun getIngredients(p0: IIngredients) {
        p0.setInputs(VanillaTypes.ITEM, OreDictionary.getOres(input.getOreDict()))
        p0.setOutputs(VanillaTypes.FLUID, mutableListOf(output))
    }

}