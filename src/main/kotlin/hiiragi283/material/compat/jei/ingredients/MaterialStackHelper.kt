package hiiragi283.material.compat.jei.ingredients

import hiiragi283.material.RMReference
import hiiragi283.material.api.material.HiiragiMaterial
import mezz.jei.api.ingredients.IIngredientHelper

object MaterialStackHelper : IIngredientHelper<HiiragiMaterial> {

    override fun getMatch(iterator: MutableIterable<HiiragiMaterial>, toMatch: HiiragiMaterial): HiiragiMaterial? =
        iterator.firstOrNull { it == toMatch }

    override fun getDisplayName(material: HiiragiMaterial): String = material.getTranslatedName()

    override fun getUniqueId(material: HiiragiMaterial): String = material.toString()

    override fun getWildcardId(material: HiiragiMaterial): String = getUniqueId(material)

    override fun getModId(material: HiiragiMaterial): String = RMReference.MOD_ID

    override fun getResourceId(material: HiiragiMaterial): String = material.name

    override fun copyIngredient(material: HiiragiMaterial): HiiragiMaterial = material.copy()

    override fun getErrorInfo(material: HiiragiMaterial?): String = material?.let { "Errored with $material" } ?: "null"

}