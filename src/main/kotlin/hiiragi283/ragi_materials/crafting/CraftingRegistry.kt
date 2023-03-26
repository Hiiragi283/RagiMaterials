package hiiragi283.ragi_materials.crafting

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiBlock
import hiiragi283.ragi_materials.init.RagiItem
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary

object CraftingRegistry {

    fun load() {
        addCrafting()
        CraftingMaterial.load()
    }

    private fun addCrafting() {
        //Blocks
        CraftingManager.addShaped(ItemStack(RagiItem.ItemBlazingCube, 1, 0),
                "ABA", "CDC", "ABA",
                'A', ItemStack(Items.BLAZE_POWDER),
                'B', "dustPhosphorus",
                'C', "dustSulfur",
                'D', "blockCoal"
        )
        CraftingManager.addShaped(ItemStack(RagiBlock.BlockBlazingForge, 1, 0),
                "A A", "ABA", "CCC",
                'A', ItemStack(Blocks.IRON_BARS),
                'B', ItemStack(RagiItem.ItemBlazingCube),
                'C', ItemStack(Blocks.NETHER_BRICK)
        )
        CraftingManager.addShaped(ItemStack(RagiBlock.BlockForgeFurnace, 1, 0),
                "A A", "A A", "BCB",
                'A', "cobblestone",
                'B', "stone",
                'C', ItemStack(Blocks.FURNACE)
        )
        CraftingManager.addShaped(ItemStack(RagiBlock.BlockFullBottleStation, 1, 0),
                "AAA", "BCB", "AAA",
                'A', ItemStack(Blocks.STONE_SLAB),
                'B', "blockGlass",
                'C', ItemStack(Items.BUCKET)
        )
        CraftingManager.addShaped(ItemStack(RagiBlock.BlockOreDictConv, 1, 0),
                "ABA", "ACA",
                'A', "logWood",
                'B', ItemStack(Blocks.BOOKSHELF),
                'C', "chest"
        )
        CraftingManager.addShaped(ItemStack(RagiBlock.BlockOreRainbow, 1, 0),
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
        CraftingManager.addShaped(ItemStack(RagiBlock.BlockSoilCoal, 1, 0),
                "ABA",
                'A', "gemLignite",
                'B', ItemStack(Blocks.SOUL_SAND)
        )
        CraftingManager.addShaped(ItemStack(RagiBlock.BlockSoilLignite, 1, 0),
                "ABA",
                'A', "gemPeat",
                'B', "gravel"
        )
        CraftingManager.addShaped(ItemStack(RagiBlock.BlockSoilPeat, 1, 0),
                "AAA", "ABA", "AAA",
                'A', ItemStack(Blocks.TALLGRASS, 1, OreDictionary.WILDCARD_VALUE),
                'B', "dirt"
        )
        CraftingManager.addShaped("${Reference.MOD_ID}:soil_peat_1", ItemStack(RagiBlock.BlockSoilPeat, 1, 0),
                " A ", "ABA", " A ",
                'A', "treeLeaves",
                'B', "dirt"
        )
        CraftingManager.addShapeless("${Reference.MOD_ID}:soil_peat_2", ItemStack(RagiBlock.BlockSoilPeat, 1, 0),
                CraftingManager.setOreDict("vine"),
                CraftingManager.setOreDict("dirt")
        )
        CraftingManager.addShaped(ItemStack(RagiBlock.BlockStoneMill, 1, 0),
                "A", "B", "B",
                'A', "stickWood",
                'B', ItemStack(Blocks.STONE_SLAB)
        )

        //Items
        CraftingManager.addShaped(
                ItemStack(RagiItem.ItemForgeHammer),
                "AAA", "AAA", " B ",
                'A', "ingotIron",
                'B', ItemStack(Items.SIGN)
        )
    }
}