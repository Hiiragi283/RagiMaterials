package hiiragi283.integration.jei.ingredients

import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.material.RMItems
import hiiragi283.material.RMReference
import mezz.jei.api.ingredients.IIngredientHelper
import net.minecraft.item.ItemStack
import java.awt.Color

object HiiragiMaterialHelperJEI : IIngredientHelper<HiiragiMaterial> {

    override fun getMatch(ingredients: MutableIterable<HiiragiMaterial>, toMatch: HiiragiMaterial): HiiragiMaterial {
        var material: HiiragiMaterial = HiiragiMaterial.EMPTY
        ingredients.forEach { if (toMatch == it) material = it }
        return material
    }

    override fun getDisplayName(material: HiiragiMaterial): String = material.getTranslatedName()

    override fun getUniqueId(material: HiiragiMaterial): String = material.toString()

    override fun getWildcardId(material: HiiragiMaterial): String = getUniqueId(material)

    override fun getModId(material: HiiragiMaterial): String = RMReference.MOD_ID

    override fun getColors(ingredient: HiiragiMaterial): MutableIterable<Color> = mutableListOf(Color(ingredient.color))

    override fun getResourceId(material: HiiragiMaterial): String = material.name

    override fun getCheatItemStack(ingredient: HiiragiMaterial): ItemStack =
        RMItems.MATERIAL_BOTTLE.getItemStack(ingredient)

    override fun copyIngredient(material: HiiragiMaterial): HiiragiMaterial = material.copy()

    override fun getErrorInfo(material: HiiragiMaterial?): String =
        if (material == null) "null" else "Errored with $material"

}