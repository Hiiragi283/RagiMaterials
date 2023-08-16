package hiiragi283.integration.jei

import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.recipe.HiiragiRecipe
import hiiragi283.integration.jei.ingredients.HiiragiIngredientTypes
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import net.minecraft.item.ItemStack

class HiiragiMaterialRecipe(
    val material: HiiragiMaterial,
    val items: List<ItemStack>
) : HiiragiRecipe<HiiragiMaterialRecipe>() {

    override fun getIngredients(p0: IIngredients) {
        p0.setInputs(VanillaTypes.ITEM, items)
        p0.setOutput(HiiragiIngredientTypes.MATERIAL, material)
    }

}