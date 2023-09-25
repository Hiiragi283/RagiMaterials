package hiiragi283.material.api.module

import hiiragi283.material.api.recipe.IMachineRecipe
import hiiragi283.material.api.registry.HiiragiEntry

interface IRecipeModuleItem : HiiragiEntry.ITEM {

    val recipeType: IMachineRecipe.Type

}