package hiiragi283.ragi_materials.integration.jei.salt_pond

import hiiragi283.ragi_materials.integration.jei.JEICore
import mezz.jei.api.IModRegistry

object SaltPondMaker {

    fun register(registry: IModRegistry) {
        //リストをレシピ一覧に登録
        registry.addRecipes(listOf(SaltPondRecipe("water"), SaltPondRecipe("saltwater"), SaltPondRecipe("brine")), JEICore.SaltPond)
    }
}