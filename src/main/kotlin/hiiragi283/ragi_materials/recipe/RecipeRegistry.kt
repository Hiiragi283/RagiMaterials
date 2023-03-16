package hiiragi283.ragi_materials.recipe

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiBlock
import hiiragi283.ragi_materials.init.RagiItem
import hiiragi283.ragi_materials.recipe.crafting.RecipeMaterial
import hiiragi283.ragi_materials.util.RagiUtil
import net.minecraft.item.ItemStack

object RecipeRegistry {

    init {
        addCrafting()

        RecipeMaterial.init()
    }

    private fun addCrafting() {
        RagiRecipe.addShaped(ItemStack(RagiItem.ItemBlazingCube, 1, 0),
                "ABA", "CDC", "ABA",
                'A', RagiUtil.getStack("minecraft:blaze_powder", 1, 0),
                'B', "dustPhosphorus",
                'C', "dustSulfur",
                'D', "blockCoal"
        )
        RagiRecipe.addShaped(ItemStack(RagiBlock.BlockBlazingForge, 1, 0),
                "A A", "ABA", "CCC",
                'A', RagiUtil.getStack("minecraft:iron_bars", 1, 0),
                'B', RagiUtil.getStack("${Reference.MOD_ID}:blazing_cube", 1, 0),
                'C', RagiUtil.getStack("minecraft:nether_brick", 1, 0)
        )
        RagiRecipe.addShaped(ItemStack(RagiBlock.BlockForgeFurnace, 1, 0),
                "A A", "A A", "BCB",
                'A', "cobblestone",
                'B', "stone",
                'C', RagiUtil.getStack("minecraft:furnace", 1, 0)
        )
        RagiRecipe.addShaped(ItemStack(RagiBlock.BlockFullBottleStation, 1, 0),
                "AAA", "BCB", "AAA",
                'A', RagiUtil.getStack("minecraft:stone_slab", 1, 0),
                'B', "blockGlass",
                'C', RagiUtil.getStack("minecraft:bucket", 1, 0)
        )
        RagiRecipe.addShaped(ItemStack(RagiBlock.BlockOreDictConv, 1, 0),
                "ABA", "ACA",
                'A', "logWood",
                'B', RagiUtil.getStack("minecraft:bookshelf", 1, 0),
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
        RagiRecipe.addShaped(ItemStack(RagiBlock.BlockSoilPeat, 1, 0),
                "ABA", "BCB", "ABA",
                'A', "dirt",
                'B', "gemCharcoal",
                'C', RagiUtil.getFilledBottle("water")
        )
        RagiRecipe.addShaped(ItemStack(RagiBlock.BlockStoneMill, 1, 0),
                "A", "B", "B",
                'A', "stickWood",
                'B', RagiUtil.getStack("minecraft:stone_slab", 1, 0)
        )
    }
}