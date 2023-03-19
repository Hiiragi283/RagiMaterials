package hiiragi283.ragi_materials.integration.jei.forge_furnace

import hiiragi283.ragi_materials.integration.jei.JEICore
import hiiragi283.ragi_materials.recipe.FFRecipe
import mezz.jei.api.IModRegistry

object FFMaker {

    fun register(registry: IModRegistry) {
        registry.addRecipes(FFRecipe.Registry.list, JEICore.ForgeFurnace)
    }
}