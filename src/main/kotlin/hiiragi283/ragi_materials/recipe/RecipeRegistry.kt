package hiiragi283.ragi_materials.recipe

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.util.RagiUtils
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary

object RecipeRegistry {

    fun init() {
        addCrafting()
        addCraftingMaterial()
    }

    private fun addCrafting() {
        RagiRecipe.addShaped(
            RagiUtils.getStack("${Reference.MOD_ID}:block_bellow", 1, 0),
            "AAA",
            "BCB",
            "AAA",
            'A',
            "logWood",
            'B',
            "leather",
            'C',
            RagiUtils.getStack("minecraft:piston", 1, 0)
        )
        RagiRecipe.addShaped(
            RagiUtils.getStack("${Reference.MOD_ID}:blazing_cube", 1, 0),
            "ABA",
            "CDC",
            "ABA",
            'A',
            RagiUtils.getStack("minecraft:blaze_powder", 1, 0),
            'B',
            "dustPhosphorus",
            'C',
            "dustSulfur",
            'D',
            "blockCoal"
        )
        RagiRecipe.addShaped(
            RagiUtils.getStack("${Reference.MOD_ID}:blaze_heater", 1, 0),
            "A A",
            "ABA",
            "CCC",
            'A',
            RagiUtils.getStack("minecraft:iron_bars", 1, 0),
            'B',
            ItemStack(RagiInit.ItemBlazingCube, 1, 0),
            'C',
            RagiUtils.getStack("minecraft:nether_brick", 1, 0)
        )
        RagiRecipe.addShaped(
            RagiUtils.getStack("${Reference.MOD_ID}:forge_furnace", 1, 0),
            "A A",
            "A A",
            "BCB",
            'A',
            "cobblestone",
            'B',
            "stone",
            'C',
            RagiUtils.getStack("minecraft:furnace", 1, 0)
        )
        RagiRecipe.addShaped(
            RagiUtils.getStack("${Reference.MOD_ID}:forge_hammer", 1, 0),
            "AAA",
            "AAA",
            " B ",
            'A',
            "ingotIron",
            'B',
            RagiUtils.getStack("minecraft:sign", 1, 0)
        )
        RagiRecipe.addShaped(
            RagiUtils.getStack("${Reference.MOD_ID}:oredict_converter", 1, 0),
            "ABA",
            "ACA",
            'A',
            "logWood",
            'B',
            RagiUtils.getStack("minecraft:bookshelf", 1, 0),
            'C',
            "chest"
        )
        RagiRecipe.addShaped(
            RagiUtils.getStack("${Reference.MOD_ID}:salt_pond", 1, 0),
            "ABA",
            "AAA",
            'A',
            "slabWood",
            'B',
            "sand"
        )
        RagiRecipe.addShaped(
            RagiUtils.getStack("${Reference.MOD_ID}:seed_peat", 1, 0),
            "ABA",
            "BCB",
            "ABA",
            'A',
            "dirt",
            'B',
            "gemCharcoal",
            'C',
            RagiUtils.getStack("minecraft:wheat_seeds", 1, 0)
        )
    }

    private fun addCraftingMaterial() {
        for (material in MaterialRegistry.mapIndex.values) {
            if (material.type.getTypeBase().contains("dust")) {
                //dust -> tiny dustのレシピを登録
                RagiRecipe.addShaped(
                    Reference.MOD_ID + ":dust_to_tiny_" + material.index,
                    RagiUtils.getStack("${Reference.MOD_ID}:dust_tiny", 9, material.index),
                    "A",
                    'A',
                    "dust${material.getOreDict()}"
                )
                //tiny -> dustのレシピを登録
                RagiRecipe.addShaped(
                    Reference.MOD_ID + ":tiny_to_dust_" + material.index,
                    RagiUtils.getStack("${Reference.MOD_ID}:dust", 1, material.index),
                    "AAA",
                    "AAA",
                    "AAA",
                    'A',
                    "dustTiny${material.getOreDict()}"
                )
            }
            if (material.type.getTypeBase().contains("ingot")) {
            }
            if (material.type.getTypeBase().contains("metal")) {
                //block -> ingotのレシピを登録
                RagiRecipe.addShaped(
                    Reference.MOD_ID + ":block_to_ingot_" + material.index,
                    RagiUtils.getStack("${Reference.MOD_ID}:ingot", 9, material.index),
                    "A",
                    'A',
                    "block${material.getOreDict()}"
                )
                //ingot -> blockのレシピを登録
                RagiRecipe.addShaped(
                    Reference.MOD_ID + ":ingot_to_block_" + material.index,
                    RagiUtils.getStack("${Reference.MOD_ID}:block_metal", 1, material.index),
                    "AAA",
                    "AAA",
                    "AAA",
                    'A',
                    "ingot${material.getOreDict()}"
                )
                //ingot -> nuggetのレシピを登録
                RagiRecipe.addShaped(
                    Reference.MOD_ID + ":ingot_to_nugget_" + material.index,
                    RagiUtils.getStack("${Reference.MOD_ID}:nugget", 9, material.index),
                    "A",
                    'A',
                    "ingot${material.getOreDict()}"
                )
                //hot ingot -> gearのレシピを登録
                RagiRecipe.addShaped(
                    Reference.MOD_ID + ":hot_ingot_to_gear_" + material.index,
                    RagiUtils.getStack("${Reference.MOD_ID}:gear", 1, material.index),
                    " A ",
                    "ABA",
                    " A ",
                    'A',
                    "ingotHot${material.getOreDict()}",
                    'B',
                    RagiUtils.getStack("${Reference.MOD_ID}:forge_hammer", 1, OreDictionary.WILDCARD_VALUE)
                )
                //hot ingot -> plateのレシピを登録
                RagiRecipe.addShaped(
                    Reference.MOD_ID + ":hot_ingot_to_plate_" + material.index,
                    RagiUtils.getStack("${Reference.MOD_ID}:plate", 1, material.index),
                    "AB",
                    'A',
                    "ingotHot${material.getOreDict()}",
                    'B',
                    RagiUtils.getStack("${Reference.MOD_ID}:forge_hammer", 1, OreDictionary.WILDCARD_VALUE)
                )
                //hot ingot -> stickのレシピを登録
                RagiRecipe.addShaped(
                    Reference.MOD_ID + ":hot_ingot_to_stick_" + material.index,
                    RagiUtils.getStack("${Reference.MOD_ID}:stick", 4, material.index),
                    "AB",
                    "A ",
                    'A',
                    "ingotHot${material.getOreDict()}",
                    'B',
                    RagiUtils.getStack("${Reference.MOD_ID}:forge_hammer", 1, OreDictionary.WILDCARD_VALUE)
                )
                //nugget -> ingotのレシピを登録
                RagiRecipe.addShaped(
                    Reference.MOD_ID + ":nugget_to_ingot_" + material.index,
                    RagiUtils.getStack("${Reference.MOD_ID}:ingot", 1, material.index),
                    "AAA",
                    "AAA",
                    "AAA",
                    'A',
                    "nugget${material.getOreDict()}"
                )
            }
        }
    }
}