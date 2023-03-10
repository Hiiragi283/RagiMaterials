package hiiragi283.ragi_materials.recipe

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.util.RagiUtil

object RecipeRegistry {

    init {
        addCrafting()

        RecipeMaterial.init()
        //RecipeMaterialTool.init()
    }

    private fun addCrafting() {
        RagiRecipe.addShaped(RagiUtil.getStack("${Reference.MOD_ID}:block_bellow", 1, 0), "AAA", "BCB", "AAA", 'A', "logWood", 'B', "leather", 'C', RagiUtil.getStack("minecraft:piston", 1, 0))
        RagiRecipe.addShaped(RagiUtil.getStack("${Reference.MOD_ID}:blazing_cube", 1, 0), "ABA", "CDC", "ABA", 'A', RagiUtil.getStack("minecraft:blaze_powder", 1, 0), 'B', "dustPhosphorus", 'C', "dustSulfur", 'D', "blockCoal")
        //RagiRecipe.addShaped(RagiUtil.getStack("${Reference.MOD_ID}:blaze_heater", 1, 0), "A A", "ABA", "CCC", 'A', RagiUtil.getStack("minecraft:iron_bars", 1, 0), 'B', ItemStack(RagiInit.ItemBlazingCube, 1, 0), 'C', RagiUtil.getStack("minecraft:nether_brick", 1, 0))
        RagiRecipe.addShaped(RagiUtil.getStack("${Reference.MOD_ID}:forge_furnace", 1, 0), "A A", "A A", "BCB", 'A', "cobblestone", 'B', "stone", 'C', RagiUtil.getStack("minecraft:furnace", 1, 0))
        RagiRecipe.addShaped(RagiUtil.getStack("${Reference.MOD_ID}:oredict_converter", 1, 0), "ABA", "ACA", 'A', "logWood", 'B', RagiUtil.getStack("minecraft:bookshelf", 1, 0), 'C', "chest")
        RagiRecipe.addShaped(RagiUtil.getStack("${Reference.MOD_ID}:salt_pond", 1, 0), "ABA", "AAA", 'A', "slabWood", 'B', "sand")
        RagiRecipe.addShaped(RagiUtil.getStack("${Reference.MOD_ID}:seed_peat", 1, 0), "ABA", "BCB", "ABA", 'A', "dirt", 'B', "gemCharcoal", 'C', RagiUtil.getStack("minecraft:wheat_seeds", 1, 0))
    }
}