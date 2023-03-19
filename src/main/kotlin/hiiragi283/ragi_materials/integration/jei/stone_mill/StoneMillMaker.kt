package hiiragi283.ragi_materials.integration.jei.stone_mill

import hiiragi283.ragi_materials.integration.jei.JEICore
import hiiragi283.ragi_materials.recipe.MillRecipe
import mezz.jei.api.IModRegistry

object StoneMillMaker {

    fun register(registry: IModRegistry) {
        registry.addRecipes(MillRecipe.Registry.list, JEICore.StoneMill)
    }
}