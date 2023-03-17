package hiiragi283.ragi_materials.recipe.crafting

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import hiiragi283.ragi_materials.recipe.RagiRecipe
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraftforge.oredict.OreDictionary

object RecipeMaterial {

    fun init() {
        for (material in MaterialRegistry.list) {
            if (EnumMaterialType.DUST in material.type.list) {
                //dust -> tiny dustのレシピを登録
                RagiRecipe.addShaped(Reference.MOD_ID + ":dust_to_tiny_" + material.index, RagiUtil.getStack("${Reference.MOD_ID}:dust_tiny", 9, material.index), "A", 'A', "dust${material.getOreDict()}")
                //tiny -> dustのレシピを登録
                RagiRecipe.addShaped(Reference.MOD_ID + ":tiny_to_dust_" + material.index, RagiUtil.getStack("${Reference.MOD_ID}:dust", 1, material.index), "AAA", "AAA", "AAA", 'A', "dustTiny${material.getOreDict()}")
            }

            if (material.type.list.containsAll(listOf(EnumMaterialType.BLOCK_MATERIAL, EnumMaterialType.CRYSTAL))) {
                //block -> ingotのレシピを登録
                RagiRecipe.addShaped(Reference.MOD_ID + ":block_to_crystal_" + material.index, RagiUtil.getStack("${Reference.MOD_ID}:crystal", 9, material.index), "A", 'A', "block${material.getOreDict()}")
                //ingot -> blockのレシピを登録
                RagiRecipe.addShaped(Reference.MOD_ID + ":crystal_to_block_" + material.index, RagiUtil.getStack("${Reference.MOD_ID}:block_material", 1, material.index), "AAA", "AAA", "AAA", 'A', "gem${material.getOreDict()}")
            }

            if (material.type.list.containsAll(listOf(EnumMaterialType.BLOCK_MATERIAL, EnumMaterialType.INGOT))) {
                //block -> ingotのレシピを登録
                RagiRecipe.addShaped(Reference.MOD_ID + ":block_to_ingot_" + material.index, RagiUtil.getStack("${Reference.MOD_ID}:ingot", 9, material.index), "A", 'A', "block${material.getOreDict()}")
                //ingot -> blockのレシピを登録
                RagiRecipe.addShaped(Reference.MOD_ID + ":ingot_to_block_" + material.index, RagiUtil.getStack("${Reference.MOD_ID}:block_material", 1, material.index), "AAA", "AAA", "AAA", 'A', "ingot${material.getOreDict()}")
            }

            if (material.type.list.containsAll(listOf(EnumMaterialType.INGOT, EnumMaterialType.NUGGET))) {
                //ingot -> nuggetのレシピを登録
                RagiRecipe.addShaped(Reference.MOD_ID + ":ingot_to_nugget_" + material.index, RagiUtil.getStack("${Reference.MOD_ID}:nugget", 9, material.index), "A", 'A', "ingot${material.getOreDict()}")
                //nugget -> ingotのレシピを登録
                RagiRecipe.addShaped(Reference.MOD_ID + ":nugget_to_ingot_" + material.index, RagiUtil.getStack("${Reference.MOD_ID}:ingot", 1, material.index), "AAA", "AAA", "AAA", 'A', "nugget${material.getOreDict()}")
            }

            if (EnumMaterialType.INGOT_HOT in material.type.list) {
                //hot ingot -> gearのレシピを登録
                RagiRecipe.addShaped(Reference.MOD_ID + ":hot_ingot_to_gear_" + material.index, RagiUtil.getStack("${Reference.MOD_ID}:gear", 1, material.index), " A ", "ABA", " A ", 'A', "ingotHot${material.getOreDict()}", 'B', RagiUtil.getStack("${Reference.MOD_ID}:forge_hammer", 1, OreDictionary.WILDCARD_VALUE))
                //hot ingot -> plateのレシピを登録
                RagiRecipe.addShaped(Reference.MOD_ID + ":hot_ingot_to_plate_" + material.index, RagiUtil.getStack("${Reference.MOD_ID}:plate", 1, material.index), "AB", 'A', "ingotHot${material.getOreDict()}", 'B', RagiUtil.getStack("${Reference.MOD_ID}:forge_hammer", 1, OreDictionary.WILDCARD_VALUE))
                //hot ingot -> stickのレシピを登録
                RagiRecipe.addShaped(Reference.MOD_ID + ":hot_ingot_to_stick_" + material.index, RagiUtil.getStack("${Reference.MOD_ID}:stick", 4, material.index), "AB", "A ", 'A', "ingotHot${material.getOreDict()}", 'B', RagiUtil.getStack("${Reference.MOD_ID}:forge_hammer", 1, OreDictionary.WILDCARD_VALUE))
            }
        }
    }
}