package hiiragi283.material

import hiiragi283.api.HiiragiRegistry
import hiiragi283.api.recipe.CrushingRecipe
import hiiragi283.api.recipe.RockGenerationRecipe
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
        HiiragiRegistry.ROCK_GENERATION.registerAll(
            RockGenerationRecipe(ItemStack(Blocks.COBBLESTONE)),
            RockGenerationRecipe(ItemStack(Blocks.STONE, 1, 1)),
            RockGenerationRecipe(ItemStack(Blocks.STONE, 1, 3)),
            RockGenerationRecipe(ItemStack(Blocks.STONE, 1, 5))
        )
    }

}