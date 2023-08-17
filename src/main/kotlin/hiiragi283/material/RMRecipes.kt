package hiiragi283.material

import hiiragi283.api.material.MaterialRegistry
import hiiragi283.api.part.HiiragiPart
import hiiragi283.api.recipe.CrushingRecipe
import hiiragi283.api.registry.HiiragiRegistry
import hiiragi283.api.shape.ShapeRegistry
import rechellatek.camelToSnakeCase

object RMRecipes {

    fun init() {
        registerCrushingRecipe()
    }

    private fun registerCrushingRecipe() {
        //粉砕レシピの登録
        MaterialRegistry.getMaterials()
            .filter { it.isSolid() }
            .forEach { material ->
                ShapeRegistry.getShapes()
                    .filter { it.hasScale() && it.scale >= 144 }
                    .map { HiiragiPart(it, material) }
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

}