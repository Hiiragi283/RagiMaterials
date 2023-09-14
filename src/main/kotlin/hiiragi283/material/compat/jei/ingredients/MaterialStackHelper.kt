package hiiragi283.material.compat.jei.ingredients

import hiiragi283.material.HiiragiItems
import hiiragi283.material.RMReference
import hiiragi283.material.api.material.MaterialStack
import mezz.jei.api.ingredients.IIngredientHelper
import net.minecraft.item.ItemStack

object MaterialStackHelper : IIngredientHelper<MaterialStack> {

    override fun getMatch(iterator: MutableIterable<MaterialStack>, toMatch: MaterialStack): MaterialStack? =
        iterator.firstOrNull { it.equalsMaterial(toMatch) }

    override fun getDisplayName(stack: MaterialStack): String = stack.material.getTranslatedName()

    override fun getUniqueId(stack: MaterialStack): String = stack.material.toString()

    override fun getWildcardId(stack: MaterialStack): String = getUniqueId(stack)

    override fun getModId(stack: MaterialStack): String = RMReference.MOD_ID

    override fun getResourceId(stack: MaterialStack): String = stack.material.name

    override fun getCheatItemStack(ingredient: MaterialStack): ItemStack =
        HiiragiItems.MATERIAL_BOTTLE.getItemStack(ingredient.material)

    override fun copyIngredient(stack: MaterialStack): MaterialStack = stack.copy()

    override fun getErrorInfo(stack: MaterialStack?): String = stack?.let { "Errored with $stack" } ?: "null"

}