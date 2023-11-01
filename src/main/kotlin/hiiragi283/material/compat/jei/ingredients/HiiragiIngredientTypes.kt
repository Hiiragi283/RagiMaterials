package hiiragi283.material.compat.jei.ingredients

import hiiragi283.material.api.material.HiiragiMaterial
import mezz.jei.api.recipe.IIngredientType

object HiiragiIngredientTypes {

    @JvmField
    val MATERIAL: IIngredientType<HiiragiMaterial> = IIngredientType { HiiragiMaterial::class.java }

}