package hiiragi283.ragi_materials.recipe.forge_furnace

object FFRegistry {

    val list: MutableList<FFRecipe> = mutableListOf()

    fun init() {

        val burning = FFRecipe.EnumFire.BURNING
        val boosted = FFRecipe.EnumFire.BOOSTED
        val hellrise = FFRecipe.EnumFire.HELLRISE

        FFRecipe("ragi_materials:ingot:3", "ragi_materials:ingot_hot:3", burning)
        FFRecipe("ragi_materials:ingot:12", "ragi_materials:ingot_hot:12", burning)
        FFRecipe("ragi_materials:ingot:13", "ragi_materials:ingot_hot:13", burning)
        FFRecipe("minecraft:iron_ingot:0", "ragi_materials:ingot_hot:26", burning)
        FFRecipe("ragi_materials:ingot:26", "ragi_materials:ingot_hot:26", burning)
        FFRecipe("ragi_materials:ingot:29", "ragi_materials:ingot_hot:29", burning)
        FFRecipe("ragi_materials:ingot:30", "ragi_materials:ingot_hot:30", burning)
        FFRecipe("ragi_materials:ingot:31", "ragi_materials:ingot_hot:31", burning)
        FFRecipe("ragi_materials:ingot:47", "ragi_materials:ingot_hot:47", burning)
        FFRecipe("ragi_materials:ingot:49", "ragi_materials:ingot_hot:49", burning)
        FFRecipe("ragi_materials:ingot:50", "ragi_materials:ingot_hot:50", burning)
        FFRecipe("ragi_materials:ingot:51", "ragi_materials:ingot_hot:51", burning)
        FFRecipe("ragi_materials:ingot:60", "ragi_materials:ingot_hot:60", burning)
        FFRecipe("ragi_materials:ingot:62", "ragi_materials:ingot_hot:62", burning)
        FFRecipe("minecraft:gold_ingot:0", "ragi_materials:ingot_hot:79", burning)
        FFRecipe("ragi_materials:ingot:79", "ragi_materials:ingot_hot:79", burning)
        FFRecipe("ragi_materials:ingot:82", "ragi_materials:ingot_hot:82", burning)
        FFRecipe("ragi_materials:ingot:83", "ragi_materials:ingot_hot:83", burning)
s
        FFRecipe("ragi_materials:ingot:4", "ragi_materials:ingot_hot:4", boosted)
        FFRecipe("ragi_materials:ingot:14", "ragi_materials:ingot_hot:14", boosted)
        FFRecipe("ragi_materials:ingot:22", "ragi_materials:ingot_hot:22", boosted)
        FFRecipe("ragi_materials:ingot:24", "ragi_materials:ingot_hot:24", boosted)
        FFRecipe("ragi_materials:ingot:25", "ragi_materials:ingot_hot:25", boosted)
        FFRecipe("ragi_materials:ingot:27", "ragi_materials:ingot_hot:27", boosted)
        FFRecipe("ragi_materials:ingot:28", "ragi_materials:ingot_hot:28", boosted)
        FFRecipe("ragi_materials:ingot:40", "ragi_materials:ingot_hot:40", boosted)
        FFRecipe("ragi_materials:ingot:45", "ragi_materials:ingot_hot:45", boosted)
        FFRecipe("ragi_materials:ingot:46", "ragi_materials:ingot_hot:46", boosted)
        FFRecipe("ragi_materials:ingot:78", "ragi_materials:ingot_hot:78", boosted)

        FFRecipe("ragi_materials:ingot:41", "ragi_materials:ingot_hot:41", hellrise)
        FFRecipe("ragi_materials:ingot:42", "ragi_materials:ingot_hot:42", hellrise)
        FFRecipe("ragi_materials:ingot:44", "ragi_materials:ingot_hot:44", hellrise)
        FFRecipe("ragi_materials:ingot:72", "ragi_materials:ingot_hot:72", hellrise)
        FFRecipe("ragi_materials:ingot:73", "ragi_materials:ingot_hot:73", hellrise)
        FFRecipe("ragi_materials:ingot:74", "ragi_materials:ingot_hot:74", hellrise)
        FFRecipe("ragi_materials:ingot:76", "ragi_materials:ingot_hot:76", hellrise)
        FFRecipe("ragi_materials:ingot:77", "ragi_materials:ingot_hot:77", hellrise)

    }
}