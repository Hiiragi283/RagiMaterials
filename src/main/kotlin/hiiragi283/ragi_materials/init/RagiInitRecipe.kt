package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.util.RagiRecipe
import hiiragi283.ragi_materials.util.RagiUtils
import net.minecraftforge.oredict.OreDictionary

object RagiInitRecipe {

    fun registerRecipes() {
        addCrafting()
        addCraftingMaterial()
        //ForgeRegistries.RECIPES.register(RecipeOreDictConv())
    }

    private fun addCrafting() {
        RagiRecipe.addShaped(
            RagiUtils.getStack("${Reference.MOD_ID}:forge_furnace", 1, 0),
            "A A",
            "A A",
            "BCB",
            'A',
            "cobblestone",
            'B',
            "stone",
            'C',
            RagiUtils.getStack("minecraft:furnace", 1, 0)
        )
        RagiRecipe.addShaped(
            RagiUtils.getStack("${Reference.MOD_ID}:forge_hammer", 1, 0),
            "AAA",
            "AAA",
            " B ",
            'A',
            "ingotIron",
            'B',
            RagiUtils.getStack("minecraft:sign", 1, 0)
        )
    }

    private fun addCraftingMaterial() {
        for (material in MaterialRegistry.list) {
            //block -> ingotのレシピを登録
            RagiRecipe.addShaped(
                Reference.MOD_ID + ":block_to_ingot_" + material.index,
                RagiUtils.getStack("${Reference.MOD_ID}:ingot", 9, material.index),
                "A",
                'A',
                "block${material.getOreDict()}"
            )
            //ingot -> blockのレシピを登録
            RagiRecipe.addShaped(
                Reference.MOD_ID + ":ingot_to_block_" + material.index,
                RagiUtils.getStack("${Reference.MOD_ID}:block_metal", 1, material.index),
                "AAA",
                "AAA",
                "AAA",
                'A',
                "ingot${material.getOreDict()}"
            )
            //ingot -> nuggetのレシピを登録
            RagiRecipe.addShaped(
                Reference.MOD_ID + ":ingot_to_nugget_" + material.index,
                RagiUtils.getStack("${Reference.MOD_ID}:nugget", 9, material.index),
                "A",
                'A',
                "ingot${material.getOreDict()}"
            )
            //hot ingot -> plateのレシピを登録
            RagiRecipe.addShaped(
                Reference.MOD_ID + ":ingot_to_plate_" + material.index,
                RagiUtils.getStack("${Reference.MOD_ID}:plate", 1, material.index),
                "AB",
                'A',
                "ingotHot${material.getOreDict()}",
                'B',
                RagiUtils.getStack("${Reference.MOD_ID}:forge_hammer", 1, OreDictionary.WILDCARD_VALUE)
            )
            //nugget -> ingotのレシピを登録
            RagiRecipe.addShaped(
                Reference.MOD_ID + ":nugget_to_ingot_" + material.index,
                RagiUtils.getStack("${Reference.MOD_ID}:ingot", 1, material.index),
                "AAA",
                "AAA",
                "AAA",
                'A',
                "nugget${material.getOreDict()}"
            )
        }
    }
}