package hiiragi283.integration.jei.ingredients

import hiiragi283.api.material.MaterialStack
import hiiragi283.material.RMItems
import hiiragi283.material.RMReference
import mezz.jei.api.ingredients.IIngredientHelper
import net.minecraft.item.ItemStack

object MaterialStackHelper : IIngredientHelper<MaterialStack> {

    override fun getMatch(iterator: MutableIterable<MaterialStack>, toMatch: MaterialStack): MaterialStack =
        iterator.firstOrNull { it.equalsMaterial(toMatch) } ?: MaterialStack.EMPTY

    override fun getDisplayName(stack: MaterialStack): String = stack.material.getTranslatedName()

    override fun getUniqueId(stack: MaterialStack): String = stack.toString()

    override fun getWildcardId(stack: MaterialStack): String = getUniqueId(stack)

    override fun getModId(stack: MaterialStack): String = RMReference.MOD_ID

    override fun getResourceId(stack: MaterialStack): String = stack.material.toString()

    override fun getCheatItemStack(ingredient: MaterialStack): ItemStack =
        RMItems.MATERIAL_BOTTLE.getItemStack(ingredient.material)

    override fun copyIngredient(stack: MaterialStack): MaterialStack = stack.copy()

    override fun getErrorInfo(stack: MaterialStack?): String = stack?.let { "Errored with $stack" } ?: "null"

}