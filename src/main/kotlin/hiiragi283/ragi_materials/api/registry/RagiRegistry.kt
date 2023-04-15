package hiiragi283.ragi_materials.api.registry

import hiiragi283.ragi_materials.api.recipe.FFRecipe
import hiiragi283.ragi_materials.api.recipe.LaboRecipe
import hiiragi283.ragi_materials.api.recipe.MillRecipe
import net.minecraftforge.registries.IForgeRegistry

object RagiRegistry {

    lateinit var FF_RECIPE: IForgeRegistry<FFRecipe>
    lateinit var LABO_RECIPE: IForgeRegistry<LaboRecipe>
    lateinit var MILL_RECIPE: IForgeRegistry<MillRecipe>

}