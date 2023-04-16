package hiiragi283.ragi_materials.recipe.workbench

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.api.init.RagiBlocks
import hiiragi283.ragi_materials.api.init.RagiItems
import hiiragi283.ragi_materials.api.material.MaterialUtil
import hiiragi283.ragi_materials.api.material.RagiMaterial
import hiiragi283.ragi_materials.api.material.part.PartRegistry
import hiiragi283.ragi_materials.api.material.type.EnumMaterialType
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.oredict.OreDictionary

object CraftingRegistry {

    fun addRecipes() {
        addCrafting()
        addCraftingMaterial()
    }

    fun removeRecipes() {
        //RagiCraftingManager.remove("minecraft:furnace")
    }

    private fun addCrafting() {
        //BLOCK
        RagiCraftingManager.addShaped(ItemStack(RagiItems.ItemBlazingCube, 1, 0),
                "ABA", "CDC", "ABA",
                'A', ItemStack(Items.BLAZE_POWDER),
                'B', "dustPhosphorus",
                'C', "dustSulfur",
                'D', "blockCoal"
        )
        RagiCraftingManager.addShaped(ItemStack(RagiBlocks.BlockBlazingForge.itemBlock!!, 1, 0),
                "A A", "ABA", "CCC",
                'A', ItemStack(Blocks.IRON_BARS),
                'B', ItemStack(RagiItems.ItemBlazingCube),
                'C', ItemStack(Blocks.NETHER_BRICK)
        )
        RagiCraftingManager.addShaped(ItemStack(RagiBlocks.BlockForgeFurnace.itemBlock!!, 1, 0),
                "A A", "A A", "BCB",
                'A', "cobblestone",
                'B', "stone",
                'C', ItemStack(Blocks.FURNACE)
        )
        RagiCraftingManager.addShaped(ItemStack(RagiBlocks.BlockFullBottleStation.itemBlock!!, 1, 0),
                "AAA", "BCB", "AAA",
                'A', ItemStack(Blocks.STONE_SLAB),
                'B', "blockGlass",
                'C', ItemStack(Items.BUCKET)
        )
        RagiCraftingManager.addShaped(ItemStack(RagiBlocks.BlockOreDictConv.itemBlock!!, 1, 0),
                "ABA", "ACA",
                'A', "logWood",
                'B', ItemStack(Blocks.BOOKSHELF),
                'C', "chest"
        )
        RagiCraftingManager.addShaped(ItemStack(RagiBlocks.BlockOreRainbow.itemBlock, 1, 0),
                "CDE", "BHF", "AIG",
                'A', "gemRuby",
                'B', "gemHafnon",
                'C', "gemPyrite",
                'D', "gemFluorite",
                'E', "gemAquamarine",
                'F', "gemSapphire",
                'G', "gemZircon",
                'H', "stone",
                'I', "gemDiamond"
        )
        RagiCraftingManager.addShaped(ItemStack(RagiBlocks.BlockSoilCoal.itemBlock, 1, 0),
                "ABA",
                'A', "gemLignite",
                'B', ItemStack(Blocks.SOUL_SAND)
        )
        RagiCraftingManager.addShaped(ItemStack(RagiBlocks.BlockSoilLignite.itemBlock, 1, 0),
                "ABA",
                'A', "gemPeat",
                'B', "gravel"
        )
        RagiCraftingManager.addShaped(ItemStack(RagiBlocks.BlockSoilPeat.itemBlock, 1, 0),
                "AAA", "ABA", "AAA",
                'A', ItemStack(Blocks.TALLGRASS, 1, OreDictionary.WILDCARD_VALUE),
                'B', "dirt"
        )
        RagiCraftingManager.addShaped("${RagiMaterials.MOD_ID}:soil_peat_1", ItemStack(RagiBlocks.BlockSoilPeat.itemBlock, 1, 0),
                " A ", "ABA", " A ",
                'A', "treeLeaves",
                'B', "dirt"
        )
        RagiCraftingManager.addShapeless("${RagiMaterials.MOD_ID}:soil_peat_2", ItemStack(RagiBlocks.BlockSoilPeat.itemBlock, 1, 0),
                RagiIngredient("vine"),
                RagiIngredient("dirt")
        )
        RagiCraftingManager.addShaped(ItemStack(RagiBlocks.BlockStoneMill.itemBlock!!, 1, 0),
                "A", "B", "B",
                'A', "stickWood",
                'B', ItemStack(Blocks.STONE_SLAB)
        )

        //ITEM
        RagiCraftingManager.addShaped(ItemStack(RagiItems.ItemForgeHammer),
                "AAA", "AAA", " B ",
                'A', "ingotIron",
                'B', ItemStack(Items.SIGN)
        )
    }

    private fun addCraftingMaterial() {
        for (material in RagiMaterial.list) {

            val listType = material.type.list

            if (EnumMaterialType.DUST in listType) {
                //dust -> tiny dustのレシピを登録
                RagiCraftingManager.addShapeless(MaterialUtil.getPart(PartRegistry.DUST_TINY, material, 9), NonNullList.withSize(1, RagiIngredient("dust${material.getOreDict()}")), "${RagiMaterials.MOD_ID}:dust_to_tiny_${material.index}")
                //tiny -> dustのレシピを登録
                RagiCraftingManager.addShaped(MaterialUtil.getPart(PartRegistry.DUST, material), NonNullList.withSize(9, RagiIngredient("dustTiny${material.getOreDict()}")), 3, 3, "${RagiMaterials.MOD_ID}:tiny_to_dust_${material.index}")
            }

            if (EnumMaterialType.BLOCK_MATERIAL in listType) {
                if (EnumMaterialType.CRYSTAL in listType) {
                    //block -> ingotのレシピを登録
                    RagiCraftingManager.addShapeless(MaterialUtil.getPart(PartRegistry.CRYSTAL, material, 9), NonNullList.withSize(1, RagiIngredient("block${material.getOreDict()}")), "${RagiMaterials.MOD_ID}:block_to_crystal_${material.index}")
                    //ingot -> blockのレシピを登録
                    RagiCraftingManager.addShaped(MaterialUtil.getPart(PartRegistry.BLOCK, material), NonNullList.withSize(9, RagiIngredient("gem${material.getOreDict()}")), 3, 3, "${RagiMaterials.MOD_ID}:crystal_to_block_${material.index}")
                } else if (EnumMaterialType.INGOT in listType) {
                    //block -> ingotのレシピを登録
                    RagiCraftingManager.addShapeless(MaterialUtil.getPart(PartRegistry.INGOT, material, 9), NonNullList.withSize(1, RagiIngredient("block${material.getOreDict()}")), "${RagiMaterials.MOD_ID}:block_to_ingot_${material.index}")
                    //ingot -> blockのレシピを登録
                    RagiCraftingManager.addShaped(MaterialUtil.getPart(PartRegistry.BLOCK, material), NonNullList.withSize(9, RagiIngredient("ingot${material.getOreDict()}")), 3, 3, "${RagiMaterials.MOD_ID}:ingot_to_block_${material.index}")
                }
            }

            if (EnumMaterialType.INGOT in listType && EnumMaterialType.NUGGET in listType) {
                //ingot -> nuggetのレシピを登録
                RagiCraftingManager.addShapeless(MaterialUtil.getPart(PartRegistry.NUGGET, material, 9), NonNullList.withSize(1, RagiIngredient("ingot${material.getOreDict()}")), "${RagiMaterials.MOD_ID}:ingot_to_nugget_${material.index}")
                //nugget -> ingotのレシピを登録
                RagiCraftingManager.addShaped(MaterialUtil.getPart(PartRegistry.INGOT, material), NonNullList.withSize(9, RagiIngredient("nugget${material.getOreDict()}")), 3, 3, "${RagiMaterials.MOD_ID}:nugget_to_ingot_${material.index}")
            }

            if (EnumMaterialType.INGOT_HOT in listType) {
                //hot ingot -> gearのレシピを登録
                RagiCraftingManager.addShaped("${RagiMaterials.MOD_ID}:hot_to_gear_${material.index}", MaterialUtil.getPart(PartRegistry.GEAR, material), " A ", "ABA", " A ", 'A', "ingotHot${material.getOreDict()}", 'B', ItemStack(RagiItems.ItemForgeHammer, 1, OreDictionary.WILDCARD_VALUE))
                //hot ingot -> plateのレシピを登録
                RagiCraftingManager.addShaped("${RagiMaterials.MOD_ID}:hot_to_plate_${material.index}", MaterialUtil.getPart(PartRegistry.PLATE, material), "AB", 'A', "ingotHot${material.getOreDict()}", 'B', ItemStack(RagiItems.ItemForgeHammer, 1, OreDictionary.WILDCARD_VALUE))
                //hot ingot -> stickのレシピを登録
                RagiCraftingManager.addShaped("${RagiMaterials.MOD_ID}:hot_to_stick_${material.index}", MaterialUtil.getPart(PartRegistry.STICK, material, 4), "AB", "A ", 'A', "ingotHot${material.getOreDict()}", 'B', ItemStack(RagiItems.ItemForgeHammer, 1, OreDictionary.WILDCARD_VALUE))
            }
        }
    }
}