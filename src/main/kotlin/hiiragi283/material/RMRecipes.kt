package hiiragi283.material

import hiiragi283.api.material.MaterialRegistry
import hiiragi283.api.part.HiiragiPart
import hiiragi283.api.recipe.CrucibleRecipe
import hiiragi283.api.recipe.CrushingRecipe
import hiiragi283.api.registry.HiiragiRegistry
import hiiragi283.api.shape.ShapeRegistry
import rechellatek.camelToSnakeCase

object RMRecipes {

    fun init() {
        registerCrucibleRecipes()
        registerCrushingRecipe()
    }

    private fun registerCrucibleRecipes() {
        //るつぼレシピの登録
        /*MaterialRegistry.getMaterials()
            .filter { it.isMetal() }
            .forEach { material ->
                ShapeRegistry.getShapes()
                    .filter { it.hasScale() }
                    .map { HiiragiPart(it, material) }
                    .forEach {
                        HiiragiRegistry.CRUCIBLE.register(
                            CrucibleRecipe(it, material.tempMelt, material.name to it.shape.scale)
                                .setRegistryName(RMReference.MOD_ID, it.getOreDict().camelToSnakeCase())
                        )
                    }
            }*/
        MaterialRegistry.getMaterials()
            .filter { it.isMetal() }
            .forEach {
                HiiragiRegistry.CRUCIBLE.register(
                    CrucibleRecipe(
                        it,
                        it.tempMelt,
                        it.name
                    ).setRegistryName(RMReference.MOD_ID, it.name)
                )
            }
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