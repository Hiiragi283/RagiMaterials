package hiiragi283.integration.jei.ingredients

import hiiragi283.api.material.MaterialStack
import mezz.jei.api.recipe.IIngredientType

object HiiragiIngredientTypes {

    @JvmField
    val MATERIAL: IIngredientType<MaterialStack> = IIngredientType { MaterialStack::class.java }

}