package ragi_materials.core.recipe

import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.oredict.OreDictionary
import ragi_materials.core.RagiMaterials
import ragi_materials.core.RagiRegistry
import ragi_materials.core.config.RagiConfig
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.core.material.part.PartRegistry
import ragi_materials.core.material.type.EnumMaterialType
import ragi_materials.core.util.addCraftingShaped
import ragi_materials.core.util.addCraftingShapeless
import ragi_materials.core.util.getPart

object CraftingRegistry {

    fun addRecipes() {
        addCraftingCore()
        //Experimental Feature
        if (RagiConfig.module.enableExperimental) addCraftingExp()
        //Magical Feature
        if (RagiConfig.module.enableMagic) addCraftingMagic()
        //Main Feature
        if (RagiConfig.module.enableMain) addCraftingMain()
        //Metallurgic Feature
        if (RagiConfig.module.enableMetallurgy) addCraftingMetallurgy()
    }

    fun removeRecipes() {
        //RagiCraftingManager.remove("minecraft:furnace")
    }

    private fun addCraftingCore() {

        addCraftingShaped(ItemStack(RagiRegistry.ItemForgeHammer),
                "AAA", "AAA", " B ",
                'A', "ingotIron",
                'B', ItemStack(Items.SIGN)
        )

        for (material in MaterialRegistry.list) {

            val listType = material.type.list

            if (EnumMaterialType.DUST in listType) {
                //dust -> tiny dustのレシピを登録
                addCraftingShapeless(getPart(PartRegistry.DUST_TINY, material, 9), NonNullList.withSize(1, RagiIngredient("dust${material.getOreDict()}")), "${RagiMaterials.MOD_ID}:dust_to_tiny_${material.index}")
                //tiny -> dustのレシピを登録
                addCraftingShaped(getPart(PartRegistry.DUST, material), NonNullList.withSize(9, RagiIngredient("dustTiny${material.getOreDict()}")), 3, 3, "${RagiMaterials.MOD_ID}:tiny_to_dust_${material.index}")
            }

            if (EnumMaterialType.BLOCK_MATERIAL in listType) {
                if (EnumMaterialType.CRYSTAL in listType) {
                    //block -> ingotのレシピを登録
                    addCraftingShapeless(getPart(PartRegistry.CRYSTAL, material, 9), NonNullList.withSize(1, RagiIngredient("block${material.getOreDict()}")), "${RagiMaterials.MOD_ID}:block_to_crystal_${material.index}")
                    //ingot -> blockのレシピを登録
                    addCraftingShaped(getPart(PartRegistry.BLOCK, material), NonNullList.withSize(9, RagiIngredient("gem${material.getOreDict()}")), 3, 3, "${RagiMaterials.MOD_ID}:crystal_to_block_${material.index}")
                } else if (EnumMaterialType.INGOT in listType) {
                    //block -> ingotのレシピを登録
                    addCraftingShapeless(getPart(PartRegistry.INGOT, material, 9), NonNullList.withSize(1, RagiIngredient("block${material.getOreDict()}")), "${RagiMaterials.MOD_ID}:block_to_ingot_${material.index}")
                    //ingot -> blockのレシピを登録
                    addCraftingShaped(getPart(PartRegistry.BLOCK, material), NonNullList.withSize(9, RagiIngredient("ingot${material.getOreDict()}")), 3, 3, "${RagiMaterials.MOD_ID}:ingot_to_block_${material.index}")
                }
            }

            if (EnumMaterialType.INGOT in listType && EnumMaterialType.NUGGET in listType) {
                //ingot -> nuggetのレシピを登録
                addCraftingShapeless(getPart(PartRegistry.NUGGET, material, 9), NonNullList.withSize(1, RagiIngredient("ingot${material.getOreDict()}")), "${RagiMaterials.MOD_ID}:ingot_to_nugget_${material.index}")
                //nugget -> ingotのレシピを登録
                addCraftingShaped(getPart(PartRegistry.INGOT, material), NonNullList.withSize(9, RagiIngredient("nugget${material.getOreDict()}")), 3, 3, "${RagiMaterials.MOD_ID}:nugget_to_ingot_${material.index}")
            }

            if (EnumMaterialType.INGOT_HOT in listType) {
                //hot ingot -> gearのレシピを登録
                addCraftingShaped("${RagiMaterials.MOD_ID}:hot_to_gear_${material.index}", getPart(PartRegistry.GEAR, material), " A ", "ABA", " A ", 'A', "ingotHot${material.getOreDict()}", 'B', ItemStack(RagiRegistry.ItemForgeHammer, 1, OreDictionary.WILDCARD_VALUE))
                //hot ingot -> plateのレシピを登録
                addCraftingShaped("${RagiMaterials.MOD_ID}:hot_to_plate_${material.index}", getPart(PartRegistry.PLATE, material), "AB", 'A', "ingotHot${material.getOreDict()}", 'B', ItemStack(RagiRegistry.ItemForgeHammer, 1, OreDictionary.WILDCARD_VALUE))
                //hot ingot -> stickのレシピを登録
                addCraftingShaped("${RagiMaterials.MOD_ID}:hot_to_stick_${material.index}", getPart(PartRegistry.STICK, material, 4), "AB", "A ", 'A', "ingotHot${material.getOreDict()}", 'B', ItemStack(RagiRegistry.ItemForgeHammer, 1, OreDictionary.WILDCARD_VALUE))
            }
        }

    }


    private fun addCraftingExp() {}

    private fun addCraftingMagic() {}

    private fun addCraftingMain() {
        addCraftingShaped(ItemStack(RagiRegistry.ItemBlazingCube, 1, 0),
                "ABA", "CDC", "ABA",
                'A', ItemStack(Items.BLAZE_POWDER),
                'B', "dustPhosphorus",
                'C', "dustSulfur",
                'D', "blockCoal"
        )
        addCraftingShaped(ItemStack(RagiRegistry.BlockFullBottleStation.itemBlock!!, 1, 0),
                "AAA", "BCB", "AAA",
                'A', ItemStack(Blocks.STONE_SLAB),
                'B', "blockGlass",
                'C', ItemStack(Items.BUCKET)
        )
        addCraftingShaped(ItemStack(RagiRegistry.BlockOreDictConv.itemBlock!!, 1, 0),
                "ABA", "ACA",
                'A', "logWood",
                'B', ItemStack(Blocks.BOOKSHELF),
                'C', "chest"
        )
        addCraftingShaped(ItemStack(RagiRegistry.BlockOreRainbow.itemBlock!!, 1, 0),
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
        addCraftingShaped(ItemStack(RagiRegistry.BlockSoilCoal.itemBlock!!, 1, 0),
                "ABA",
                'A', "gemLignite",
                'B', ItemStack(Blocks.SOUL_SAND)
        )
        addCraftingShaped(ItemStack(RagiRegistry.BlockSoilLignite.itemBlock!!, 1, 0),
                "ABA",
                'A', "gemPeat",
                'B', "gravel"
        )
        addCraftingShaped(ItemStack(RagiRegistry.BlockSoilPeat.itemBlock!!, 1, 0),
                "AAA", "ABA", "AAA",
                'A', ItemStack(Blocks.TALLGRASS, 1, OreDictionary.WILDCARD_VALUE),
                'B', "dirt"
        )
        addCraftingShaped(1, ItemStack(RagiRegistry.BlockSoilPeat.itemBlock!!, 1, 0),
                " A ", "ABA", " A ",
                'A', "treeLeaves",
                'B', "dirt"
        )
        addCraftingShapeless(2, ItemStack(RagiRegistry.BlockSoilPeat.itemBlock!!, 1, 0),
                RagiIngredient("vine"),
                RagiIngredient("dirt")
        )
        addCraftingShaped(ItemStack(RagiRegistry.BlockStoneMill.itemBlock!!, 1, 0),
                "A", "B", "B",
                'A', "stickWood",
                'B', ItemStack(Blocks.STONE_SLAB)
        )
    }

    private fun addCraftingMetallurgy() {
        addCraftingShaped(ItemStack(RagiRegistry.BlockBlazingForge.itemBlock!!, 1, 0),
                "A A", "ABA", "CCC",
                'A', ItemStack(Blocks.IRON_BARS),
                'B', ItemStack(RagiRegistry.ItemBlazingCube),
                'C', ItemStack(Blocks.NETHER_BRICK)
        )
        addCraftingShaped(ItemStack(RagiRegistry.BlockBloomery.itemBlock!!, 1, 0),
                "AAA", "ABA", "AAA",
                'A', ItemStack(Blocks.HARDENED_CLAY),
                'B', "blockCoal"
        )
        addCraftingShaped(1, ItemStack(RagiRegistry.BlockBloomery.itemBlock!!, 1, 0),
                "AAA", "ABA", "AAA",
                'A', ItemStack(Blocks.HARDENED_CLAY),
                'B', "blockCharcoal"
        )
        addCraftingShaped(ItemStack(RagiRegistry.BlockForgeFurnace.itemBlock!!, 1, 0),
                "A A", "A A", "BCB",
                'A', "cobblestone",
                'B', "stone",
                'C', ItemStack(Blocks.FURNACE)
        )
    }
}