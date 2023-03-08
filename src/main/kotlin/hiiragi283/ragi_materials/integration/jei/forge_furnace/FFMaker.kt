package hiiragi283.ragi_materials.integration.jei.forge_furnace

import hiiragi283.ragi_materials.integration.jei.JEICore
import hiiragi283.ragi_materials.recipe.forge_furnace.FFRegistry

import mezz.jei.api.IModRegistry

object FFMaker {

    fun register(registry: IModRegistry) {
        //リストをレシピ一覧に登録
        //registry.addRecipes(FFRegistry.list, JEICore.ForgeFurnace)
    }
}