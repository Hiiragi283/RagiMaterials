package hiiragi283.integration.jei.ingredients

import hiiragi283.api.material.HiiragiMaterial
import mezz.jei.api.recipe.IIngredientType

object HiiragiIngredientTypes {

    @JvmField
    val MATERIAL: IIngredientType<HiiragiMaterial> = IIngredientType { HiiragiMaterial::class.java }

}