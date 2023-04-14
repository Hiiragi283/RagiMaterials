package hiiragi283.ragi_materials.recipe.workbench

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.RagiRegistry
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary

object CraftingRegistry {

    fun load() {
        addCrafting()
        CraftingMaterial.load()
        RagiCraftingManager.removeCrafting("minecraft:furnace")
    }

    private fun addCrafting() {
        //BLOCK
        RagiCraftingManager.addShaped(ItemStack(RagiRegistry.ItemBlazingCube, 1, 0),
                "ABA", "CDC", "ABA",
                'A', ItemStack(Items.BLAZE_POWDER),
                'B', "dustPhosphorus",
                'C', "dustSulfur",
                'D', "blockCoal"
        )
        RagiCraftingManager.addShaped(ItemStack(RagiRegistry.BlockBlazingForge, 1, 0),
                "A A", "ABA", "CCC",
                'A', ItemStack(Blocks.IRON_BARS),
                'B', ItemStack(RagiRegistry.ItemBlazingCube),
                'C', ItemStack(Blocks.NETHER_BRICK)
        )
        RagiCraftingManager.addShaped(ItemStack(RagiRegistry.BlockForgeFurnace, 1, 0),
                "A A", "A A", "BCB",
                'A', "cobblestone",
                'B', "stone",
                'C', ItemStack(Blocks.FURNACE)
        )
        RagiCraftingManager.addShaped(ItemStack(RagiRegistry.BlockFullBottleStation, 1, 0),
                "AAA", "BCB", "AAA",
                'A', ItemStack(Blocks.STONE_SLAB),
                'B', "blockGlass",
                'C', ItemStack(Items.BUCKET)
        )
        RagiCraftingManager.addShaped(ItemStack(RagiRegistry.BlockOreDictConv, 1, 0),
                "ABA", "ACA",
                'A', "logWood",
                'B', ItemStack(Blocks.BOOKSHELF),
                'C', "chest"
        )
        RagiCraftingManager.addShaped(ItemStack(RagiRegistry.BlockOreRainbow, 1, 0),
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
        RagiCraftingManager.addShaped(ItemStack(RagiRegistry.BlockSoilCoal, 1, 0),
                "ABA",
                'A', "gemLignite",
                'B', ItemStack(Blocks.SOUL_SAND)
        )
        RagiCraftingManager.addShaped(ItemStack(RagiRegistry.BlockSoilLignite, 1, 0),
                "ABA",
                'A', "gemPeat",
                'B', "gravel"
        )
        RagiCraftingManager.addShaped(ItemStack(RagiRegistry.BlockSoilPeat, 1, 0),
                "AAA", "ABA", "AAA",
                'A', ItemStack(Blocks.TALLGRASS, 1, OreDictionary.WILDCARD_VALUE),
                'B', "dirt"
        )
        RagiCraftingManager.addShaped("${RagiMaterials.MOD_ID}:soil_peat_1", ItemStack(RagiRegistry.BlockSoilPeat, 1, 0),
                " A ", "ABA", " A ",
                'A', "treeLeaves",
                'B', "dirt"
        )
        RagiCraftingManager.addShapeless("${RagiMaterials.MOD_ID}:soil_peat_2", ItemStack(RagiRegistry.BlockSoilPeat, 1, 0),
                RagiIngredient("vine"),
                RagiIngredient("dirt")
        )
        RagiCraftingManager.addShaped(ItemStack(RagiRegistry.BlockStoneMill, 1, 0),
                "A", "B", "B",
                'A', "stickWood",
                'B', ItemStack(Blocks.STONE_SLAB)
        )

        //ITEM
        RagiCraftingManager.addShaped(
                ItemStack(RagiRegistry.ItemForgeHammer),
                "AAA", "AAA", " B ",
                'A', "ingotIron",
                'B', ItemStack(Items.SIGN)
        )
    }
}