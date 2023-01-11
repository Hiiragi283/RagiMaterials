package hiiragi283.ragi_materials.init

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.materials.EnumMaterials
import hiiragi283.ragi_materials.util.RagiRecipe
import hiiragi283.ragi_materials.util.RagiUtils
import net.minecraft.item.ItemStack

object RagiInitRecipe {

    fun registerRecipes() {
        addCrafting()
    }

    private fun addCrafting() {
        for (material in EnumMaterials.values()) {
            //block -> ingotのレシピを登録
            RagiRecipe.addShaped(
                Reference.MOD_ID + ":block_to_ingot_" + material.index,
                RagiUtils.getStack(Reference.MOD_ID, "ingot", 9, material.index),
                "A",
                'A',
                "block${material.getOreDict()}"
            )
            //ingot -> blockのレシピを登録
            RagiRecipe.addShaped(
                Reference.MOD_ID + ":ingot_to_block_" + material.index,
                RagiUtils.getStack(Reference.MOD_ID, "block_metal", 1, material.index),
                "AAA",
                "AAA",
                "AAA",
                'A',
                "ingot${material.getOreDict()}"
            )
            //ingot -> nuggetのレシピを登録
            RagiRecipe.addShaped(
                Reference.MOD_ID + ":ingot_to_nugget_" + material.index,
                RagiUtils.getStack(Reference.MOD_ID, "nugget", 9, material.index),
                "A",
                'A',
                "ingot${material.getOreDict()}"
            )
            //ingot -> plateのレシピを登録
            RagiRecipe.addShaped(
                Reference.MOD_ID + ":ingot_to_plate_" + material.index,
                RagiUtils.getStack(Reference.MOD_ID, "plate", 1, material.index),
                "AB",
                'A',
                "ingot${material.getOreDict()}",
                'B',
                ItemStack(RagiInitItem.ItemCraftingHammer)
            )
            //nugget -> ingotのレシピを登録
            RagiRecipe.addShaped(
                Reference.MOD_ID + ":nugget_to_ingot_" + material.index,
                RagiUtils.getStack(Reference.MOD_ID, "ingot", 1, material.index),
                "AAA",
                "AAA",
                "AAA",
                'A',
                "nugget${material.getOreDict()}"
            )
        }
    }
}