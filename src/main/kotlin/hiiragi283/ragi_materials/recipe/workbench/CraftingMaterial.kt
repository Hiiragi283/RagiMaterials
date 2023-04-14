package hiiragi283.ragi_materials.recipe.workbench

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.RagiRegistry
import hiiragi283.ragi_materials.api.material.MaterialUtil
import hiiragi283.ragi_materials.api.material.RagiMaterial
import hiiragi283.ragi_materials.api.material.part.PartRegistry
import hiiragi283.ragi_materials.api.material.type.EnumMaterialType
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary

object CraftingMaterial {

    fun load() {
        for (material in RagiMaterial.list) {

            val listType = material.type.list

            if (EnumMaterialType.DUST in listType) {
                //dust -> tiny dustのレシピを登録
                RagiCraftingManager.addShaped("${RagiMaterials.MOD_ID}:dust_to_tiny_${material.index}", MaterialUtil.getPart(PartRegistry.DUST_TINY, material, 9), "A", 'A', "dust${material.getOreDict()}")
                //tiny -> dustのレシピを登録
                RagiCraftingManager.addShaped("${RagiMaterials.MOD_ID}:tiny_to_dust_${material.index}", MaterialUtil.getPart(PartRegistry.DUST, material), "AAA", "AAA", "AAA", 'A', "dustTiny${material.getOreDict()}")
            }

            if (EnumMaterialType.BLOCK_MATERIAL in listType) {
                if (EnumMaterialType.CRYSTAL in listType) {
                    //block -> ingotのレシピを登録
                    RagiCraftingManager.addShaped("${RagiMaterials.MOD_ID}:block_to_crystal_${material.index}", MaterialUtil.getPart(PartRegistry.CRYSTAL, material, 9), "A", 'A', "block${material.getOreDict()}")
                    //ingot -> blockのレシピを登録
                    RagiCraftingManager.addShaped("${RagiMaterials.MOD_ID}:crystal_to_block_${material.index}", MaterialUtil.getPart(PartRegistry.BLOCK, material), "AAA", "AAA", "AAA", 'A', "gem${material.getOreDict()}")
                } else if (EnumMaterialType.INGOT in listType) {
                    //block -> ingotのレシピを登録
                    RagiCraftingManager.addShaped("${RagiMaterials.MOD_ID}:block_to_ingot_${material.index}", MaterialUtil.getPart(PartRegistry.INGOT, material, 9), "A", 'A', "block${material.getOreDict()}")
                    //ingot -> blockのレシピを登録
                    RagiCraftingManager.addShaped("${RagiMaterials.MOD_ID}:ingot_to_block_${material.index}", MaterialUtil.getPart(PartRegistry.BLOCK, material), "AAA", "AAA", "AAA", 'A', "ingot${material.getOreDict()}")

                }
            }

            if (EnumMaterialType.INGOT in listType && EnumMaterialType.NUGGET in listType) {
                //ingot -> nuggetのレシピを登録
                RagiCraftingManager.addShaped("${RagiMaterials.MOD_ID}:ingot_to_nugget_${material.index}", MaterialUtil.getPart(PartRegistry.NUGGET, material, 9), "A", 'A', "ingot${material.getOreDict()}")
                //nugget -> ingotのレシピを登録
                RagiCraftingManager.addShaped("${RagiMaterials.MOD_ID}:nugget_to_ingot_${material.index}", MaterialUtil.getPart(PartRegistry.INGOT, material), "AAA", "AAA", "AAA", 'A', "nugget${material.getOreDict()}")
            }

            if (EnumMaterialType.INGOT_HOT in listType) {
                //hot ingot -> gearのレシピを登録
                RagiCraftingManager.addShaped("${RagiMaterials.MOD_ID}:hot_to_gear_${material.index}", MaterialUtil.getPart(PartRegistry.GEAR, material), " A ", "ABA", " A ", 'A', "ingotHot${material.getOreDict()}", 'B', ItemStack(RagiRegistry.ItemForgeHammer, 1, OreDictionary.WILDCARD_VALUE))
                //hot ingot -> plateのレシピを登録
                RagiCraftingManager.addShaped("${RagiMaterials.MOD_ID}:hot_to_plate_${material.index}", MaterialUtil.getPart(PartRegistry.PLATE, material), "AB", 'A', "ingotHot${material.getOreDict()}", 'B', ItemStack(RagiRegistry.ItemForgeHammer, 1, OreDictionary.WILDCARD_VALUE))
                //hot ingot -> stickのレシピを登録
                RagiCraftingManager.addShaped("${RagiMaterials.MOD_ID}:hot_to_stick_${material.index}", MaterialUtil.getPart(PartRegistry.STICK, material, 4), "AB", "A ", 'A', "ingotHot${material.getOreDict()}", 'B', ItemStack(RagiRegistry.ItemForgeHammer, 1, OreDictionary.WILDCARD_VALUE))
            }
        }
    }
}