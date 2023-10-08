package hiiragi283.material.compat.jei.ingredients

import hiiragi283.material.api.material.MaterialStack
import mezz.jei.api.recipe.IIngredientType

object HiiragiIngredientTypes {

    @JvmField
    val MATERIAL: IIngredientType<MaterialStack> = IIngredientType { MaterialStack::class.java }

}