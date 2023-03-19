package hiiragi283.ragi_materials.integration.jei.laboratory_table

import hiiragi283.ragi_materials.integration.jei.JEICore
import hiiragi283.ragi_materials.recipe.LaboRecipe
import mezz.jei.api.IModRegistry

object LaboMaker {

    fun register(registry: IModRegistry) {
        registry.addRecipes(LaboRecipe.Registry.list, JEICore.LaboTable)
    }
}