package hiiragi283.ragi_materials.crafting

import hiiragi283.ragi_materials.RagiRegistry
import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.RagiMaterial
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary

object CraftingMaterial {

    fun load() {
        for (material in RagiMaterial.list) {
            if (EnumMaterialType.DUST in material.type.list) {
                //dust -> tiny dustのレシピを登録
                CraftingManager.addShaped("${Reference.MOD_ID}:dust_to_tiny_${material.index}", MaterialUtil.getPart(PartRegistry.DUST_TINY, material, 9), "A", 'A', "dust${material.getOreDict()}")
                //tiny -> dustのレシピを登録
                CraftingManager.addShaped("${Reference.MOD_ID}:tiny_to_dust_${material.index}", MaterialUtil.getPart(PartRegistry.DUST, material), "AAA", "AAA", "AAA", 'A', "dustTiny${material.getOreDict()}")
            }

            if (material.type.list.containsAll(listOf(EnumMaterialType.BLOCK_MATERIAL, EnumMaterialType.CRYSTAL))) {
                //block -> ingotのレシピを登録
                CraftingManager.addShaped("${Reference.MOD_ID}:block_to_crystal_${material.index}", MaterialUtil.getPart(PartRegistry.CRYSTAL, material, 9), "A", 'A', "block${material.getOreDict()}")
                //ingot -> blockのレシピを登録
                CraftingManager.addShaped("${Reference.MOD_ID}:crystal_to_block_${material.index}", MaterialUtil.getPart(PartRegistry.BLOCK, material), "AAA", "AAA", "AAA", 'A', "gem${material.getOreDict()}")
            }

            if (material.type.list.containsAll(listOf(EnumMaterialType.BLOCK_MATERIAL, EnumMaterialType.INGOT))) {
                //block -> ingotのレシピを登録
                CraftingManager.addShaped("${Reference.MOD_ID}:block_to_ingot_${material.index}", MaterialUtil.getPart(PartRegistry.INGOT, material, 9), "A", 'A', "block${material.getOreDict()}")
                //ingot -> blockのレシピを登録
                CraftingManager.addShaped("${Reference.MOD_ID}:ingot_to_block_${material.index}", MaterialUtil.getPart(PartRegistry.BLOCK, material), "AAA", "AAA", "AAA", 'A', "ingot${material.getOreDict()}")
            }

            if (material.type.list.containsAll(listOf(EnumMaterialType.INGOT, EnumMaterialType.NUGGET))) {
                //ingot -> nuggetのレシピを登録
                CraftingManager.addShaped("${Reference.MOD_ID}:ingot_to_nugget_${material.index}", MaterialUtil.getPart(PartRegistry.NUGGET, material, 9), "A", 'A', "ingot${material.getOreDict()}")
                //nugget -> ingotのレシピを登録
                CraftingManager.addShaped("${Reference.MOD_ID}:nugget_to_ingot_${material.index}", MaterialUtil.getPart(PartRegistry.INGOT, material), "AAA", "AAA", "AAA", 'A', "nugget${material.getOreDict()}")
            }

            if (EnumMaterialType.INGOT_HOT in material.type.list) {
                //hot ingot -> gearのレシピを登録
                CraftingManager.addShaped("${Reference.MOD_ID}:hot_to_gear_${material.index}", MaterialUtil.getPart(PartRegistry.GEAR, material), " A ", "ABA", " A ", 'A', "ingotHot${material.getOreDict()}", 'B', ItemStack(RagiRegistry.ITEM.ItemForgeHammer, 1, OreDictionary.WILDCARD_VALUE))
                //hot ingot -> plateのレシピを登録
                CraftingManager.addShaped("${Reference.MOD_ID}:hot_to_plate_${material.index}", MaterialUtil.getPart(PartRegistry.PLATE, material), "AB", 'A', "ingotHot${material.getOreDict()}", 'B', ItemStack(RagiRegistry.ITEM.ItemForgeHammer, 1, OreDictionary.WILDCARD_VALUE))
                //hot ingot -> stickのレシピを登録
                CraftingManager.addShaped("${Reference.MOD_ID}:hot_to_stick_${material.index}", MaterialUtil.getPart(PartRegistry.STICK, material, 4), "AB", "A ", 'A', "ingotHot${material.getOreDict()}", 'B', ItemStack(RagiRegistry.ITEM.ItemForgeHammer, 1, OreDictionary.WILDCARD_VALUE))
            }
        }
    }
}