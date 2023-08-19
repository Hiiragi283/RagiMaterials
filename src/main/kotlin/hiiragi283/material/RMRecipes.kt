package hiiragi283.material

import hiiragi283.api.HiiragiRegistry
import hiiragi283.api.recipe.CrushingRecipe
import rechellatek.camelToSnakeCase

object RMRecipes {

    fun init() {
        registerCrushingRecipe()
    }

    private fun registerCrushingRecipe() {
        //粉砕レシピの登録
        HiiragiRegistry.createAllParts()
            .filter { it.material.isSolid() && it.shape.hasScale() && it.shape.scale >= 144 }
            .forEach {
                HiiragiRegistry.CRUSHING.register(
                    CrushingRecipe(
                        it,
                        mapOf(RMItems.MATERIAL_DUST.getItemStack(it) to 100)
                    ).setRegistryName(RMReference.MOD_ID, it.getOreDict().camelToSnakeCase())
                )
            }
    }

}