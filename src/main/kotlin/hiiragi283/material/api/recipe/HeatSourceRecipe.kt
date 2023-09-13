package hiiragi283.material.api.recipe

import hiiragi283.material.util.MetaResourceLocation
import hiiragi283.material.util.toItemStack
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import net.minecraft.client.Minecraft
import net.minecraft.item.ItemStack
import java.awt.Color

class HeatSourceRecipe(
    metaLocation: MetaResourceLocation,
    private val temp: Int
) : HiiragiRecipe<HeatSourceRecipe>() {

    private val stack: ItemStack = metaLocation.toBlockState().toItemStack()

    val heatSource: ItemStack = stack.copy()

    //    IRecipeWrapper    //
    override fun getIngredients(iIngredients: IIngredients) {
        iIngredients.setInput(VanillaTypes.ITEM, heatSource)
    }

    override fun drawInfo(minecraft: Minecraft, recipeWidth: Int, recipeHeight: Int, mouseX: Int, mouseY: Int) {
        minecraft.fontRenderer.drawString(temp.toString(), 24, 24, Color.gray.rgb)
    }

}