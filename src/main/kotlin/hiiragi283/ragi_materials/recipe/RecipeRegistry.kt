package hiiragi283.ragi_materials.recipe

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiBlock
import hiiragi283.ragi_materials.init.RagiItem
import hiiragi283.ragi_materials.recipe.crafting.RecipeMaterial
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary

object RecipeRegistry {

    fun load() {
        addCrafting()

        RecipeMaterial.init()
    }

    private fun addCrafting() {
        RagiRecipe.addShaped(ItemStack(RagiItem.ItemBlazingCube, 1, 0),
                "ABA", "CDC", "ABA",
                'A', ItemStack(Items.BLAZE_POWDER),
                'B', "dustPhosphorus",
                'C', "dustSulfur",
                'D', "blockCoal"
        )
        RagiRecipe.addShaped(ItemStack(RagiBlock.BlockBlazingForge, 1, 0),
                "A A", "ABA", "CCC",
                'A', ItemStack(Blocks.IRON_BARS),
                'B', ItemStack(RagiItem.ItemBlazingCube),
                'C', ItemStack(Blocks.NETHER_BRICK)
        )
        RagiRecipe.addShaped(ItemStack(RagiBlock.BlockForgeFurnace, 1, 0),
                "A A", "A A", "BCB",
                'A', "cobblestone",
                'B', "stone",
                'C', ItemStack(Blocks.FURNACE)
        )
        RagiRecipe.addShaped(ItemStack(RagiBlock.BlockFullBottleStation, 1, 0),
                "AAA", "BCB", "AAA",
                'A', ItemStack(Blocks.STONE_SLAB),
                'B', "blockGlass",
                'C', ItemStack(Items.BUCKET)
        )
        RagiRecipe.addShaped(ItemStack(RagiBlock.BlockOreDictConv, 1, 0),
                "ABA", "ACA",
                'A', "logWood",
                'B', ItemStack(Blocks.BOOKSHELF),
                'C', "chest"
        )
        RagiRecipe.addShaped(ItemStack(RagiBlock.BlockOreRainbow, 1, 0),
                "CDE", "BHF", "AIG",
                'A', "dyeRed",
                'B', "dyeOrange",
                'C', "dyeYellow",
                'D', "slimeball",
                'E', "dyeLightBlue",
                'F', "dyeBlue",
                'G', "dyePurple",
                'H', "stone",
                'I', "gemDiamond"
        )
        RagiRecipe.addShaped(ItemStack(RagiBlock.BlockSaltPond, 1, 0),
                "ABA", "AAA",
                'A', "slabWood",
                'B', "sand"
        )
        RagiRecipe.addShaped(ItemStack(RagiBlock.BlockSoilCoal, 1, 0),
                "ABA",
                'A', "gemLignite",
                'B', ItemStack(Blocks.SOUL_SAND)
        )
        RagiRecipe.addShaped(ItemStack(RagiBlock.BlockSoilLignite, 1, 0),
                "ABA",
                'A', "gemPeat",
                'B', "gravel"
        )
        RagiRecipe.addShaped(ItemStack(RagiBlock.BlockSoilPeat, 1, 0),
                "AAA", "ABA", "AAA",
                'A', ItemStack(Blocks.TALLGRASS, 1, OreDictionary.WILDCARD_VALUE),
                'B', "dirt"
        )
        RagiRecipe.addShaped("${Reference.MOD_ID}:soil_peat_1", ItemStack(RagiBlock.BlockSoilPeat, 1, 0),
                " A ", "ABA", " A ",
                'A', "treeLeaves",
                'B', "dirt"
        )
        RagiRecipe.addShapeless("${Reference.MOD_ID}:soil_peat_2", ItemStack(RagiBlock.BlockSoilPeat, 1, 0),
                RagiRecipe.setOreDict("vine"),
                RagiRecipe.setOreDict("dirt")
        )
        RagiRecipe.addShaped(ItemStack(RagiBlock.BlockStoneMill, 1, 0),
                "A", "B", "B",
                'A', "stickWood",
                'B', ItemStack(Blocks.STONE_SLAB)
        )
    }
}