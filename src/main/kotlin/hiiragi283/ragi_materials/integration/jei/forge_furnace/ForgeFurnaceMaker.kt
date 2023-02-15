package hiiragi283.ragi_materials.integration.jei.forge_furnace

import hiiragi283.ragi_materials.integration.jei.JEICore
import hiiragi283.ragi_materials.recipe.forge_furnace.ForgeFurnaceRegistry

import mezz.jei.api.IModRegistry

object ForgeFurnaceMaker {

    fun register(registry: IModRegistry) {
        //リストをレシピ一覧に登録
        registry.addRecipes(ForgeFurnaceRegistry.list, JEICore.ForgeFurnace)
    }
}