package hiiragi283.integration.jei

import hiiragi283.api.recipe.HiiragiRecipe
import hiiragi283.material.util.MetaResourceLocation
import hiiragi283.material.util.toItemStack
import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import net.minecraft.client.Minecraft
import net.minecraft.item.ItemStack

class HeatSourceRecipe(metaLocation: MetaResourceLocation, val temp: Int) : HiiragiRecipe<HeatSourceRecipe>() {

    val stack: ItemStack = metaLocation.toBlockState().toItemStack()

    //    IRecipeWrapper    //

    override fun getIngredients(p0: IIngredients) {
        p0.setInput(VanillaTypes.ITEM, stack.copy())
    }

    override fun drawInfo(minecraft: Minecraft, recipeWidth: Int, recipeHeight: Int, mouseX: Int, mouseY: Int) {
        minecraft.fontRenderer.drawString(temp.toString(), 24, 24, java.awt.Color.gray.rgb)
    }

}