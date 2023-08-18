package hiiragi283.integration.jei

import hiiragi283.api.material.MaterialStack
import hiiragi283.api.recipe.HiiragiRecipe
import hiiragi283.integration.jei.ingredients.HiiragiIngredientTypes
import hiiragi283.material.util.HiiragiColor
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import net.minecraft.client.Minecraft
import net.minecraft.item.ItemStack

class HiiragiMaterialRecipe(
    val stack: MaterialStack,
    val items: List<ItemStack>
) : HiiragiRecipe<HiiragiMaterialRecipe>() {

    //    IRecipeWrapper    //

    override fun getIngredients(p0: IIngredients) {
        p0.setInputs(VanillaTypes.ITEM, items)
        p0.setOutput(HiiragiIngredientTypes.MATERIAL, stack)
    }

    override fun drawInfo(minecraft: Minecraft, recipeWidth: Int, recipeHeight: Int, mouseX: Int, mouseY: Int) {
        minecraft.fontRenderer.drawString(stack.material.getTranslatedName(), 24, 10, HiiragiColor.WHITE.rgb)
    }

}