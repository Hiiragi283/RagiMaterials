package hiiragi283.integration.jei

import hiiragi283.api.material.MaterialStack
import hiiragi283.api.recipe.HiiragiRecipe
import hiiragi283.integration.jei.ingredients.HiiragiIngredientTypes
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import net.minecraft.item.ItemStack

class CrucibleRecipe(
    val input: MaterialStack,
    val cast: ItemStack,
    val output: ItemStack
) : HiiragiRecipe<CrucibleRecipe>() {

    //    IRecipeWrapper    //

    override fun getIngredients(p0: IIngredients) {
        p0.setInput(HiiragiIngredientTypes.MATERIAL, input)
        p0.setInput(VanillaTypes.ITEM, cast)
        p0.setOutput(VanillaTypes.ITEM, output)
    }

}