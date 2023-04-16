package hiiragi283.ragi_materials.recipe

import hiiragi283.ragi_materials.api.init.RagiBlocks
import hiiragi283.ragi_materials.api.init.RagiItems
import hiiragi283.ragi_materials.api.material.MaterialUtil
import hiiragi283.ragi_materials.api.material.RagiMaterial
import hiiragi283.ragi_materials.api.material.part.PartRegistry
import hiiragi283.ragi_materials.api.material.type.EnumMaterialType
import hiiragi283.ragi_materials.api.recipe.MillRecipe
import hiiragi283.ragi_materials.material.OreProperties
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack

object MillRecipeRegistry {

    val set: MutableSet<MillRecipe> = mutableSetOf()

    fun load() {
        recipeMaterial()
        recipeOre()
        recipeOreVanilla()
    }

    private fun recipeMaterial() {
        for (pair in RagiMaterial.validPair) {
            val part = pair.first
            val material = pair.second
            if (part.scale >= 1.0f && part.type != EnumMaterialType.DUST) {
                val recipe = MillRecipe.Builder().apply {
                    input = MaterialUtil.getPart(part, material)
                    output = MaterialUtil.getPart(PartRegistry.DUST, material, part.scale.toInt())
                }.build().setRegistryName("${part.name}_${material.name}")
                set.add(recipe)
            }
        }
    }

    private fun recipeOre() {
        val blockOre = RagiBlocks.BlockOre1
        val itemCrushed = RagiItems.ItemOreCrushed
        for (i in OreProperties.listOre1.indices) {
            //Ore -> Crushed Ore
            MillRecipe.Builder().apply {
                input = ItemStack(blockOre, 1, i)
                output = ItemStack(itemCrushed, 2, i)
            }.build().setRegistryName("${blockOre.registryName}_$i").also { set.add(it) }
            //Crushed Ore -> Dust
            MillRecipe.Builder().apply {
                input = ItemStack(itemCrushed, 1, i)
                output = MaterialUtil.getPart(PartRegistry.DUST, OreProperties.listOre1[i].second.first)
            }.build().setRegistryName("${itemCrushed.registryName}_$i").also { set.add(it) }
        }
    }

    private fun recipeOreVanilla() {
        val itemCrushed = RagiItems.ItemOreCrushedVanilla
        val list = listOf(
                Blocks.GOLD_ORE,
                Blocks.IRON_ORE,
                Blocks.COAL_ORE,
                Blocks.LAPIS_ORE,
                Blocks.DIAMOND_ORE,
                Blocks.REDSTONE_ORE,
                Blocks.EMERALD_ORE,
                Blocks.QUARTZ_ORE
        )
        for (i in list.indices) {
            MillRecipe.Builder().apply {
                input = ItemStack(list[i])
                output = ItemStack(itemCrushed, 2, i)
            }.build().setRegistryName(list[i].registryName!!).also { set.add(it) }
            MillRecipe.Builder().apply {
                input = ItemStack(itemCrushed, 1, i)
                //ラピスとレッドストーン用の処理
                val amount = if (i == 3 || i == 5) 8 else 1
                output = MaterialUtil.getPart(PartRegistry.DUST, OreProperties.listVanilla[i].second.first, amount)
            }.build().setRegistryName("${itemCrushed.registryName}_$i").also { set.add(it) }
        }
    }
}