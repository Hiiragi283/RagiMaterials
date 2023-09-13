package hiiragi283.material

import hiiragi283.material.api.recipe.CrushingRecipe
import hiiragi283.material.api.recipe.RockGenerationRecipe
import hiiragi283.material.api.registry.HiiragiRegistry
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import rechellatek.camelToSnakeCase

object RMRecipes {

    fun init() {
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
        //岩石生成レシピの登録
        //A -> A
        listOf(
            Blocks.COBBLESTONE to 0,
            Blocks.STONE to 1,
            Blocks.STONE to 3,
            Blocks.STONE to 5,
            Blocks.MOSSY_COBBLESTONE to 0,
            Blocks.NETHERRACK to 0,
            Blocks.END_STONE to 0
        )
            .map { ItemStack(it.first, 1, it.second) }
            .map { RockGenerationRecipe(it) }
            .forEach { HiiragiRegistry.ROCK_GENERATION.register(it) }
        //A -> B
    }

}