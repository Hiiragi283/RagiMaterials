package hiiragi283.ragi_materials.config

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.block.ForgeFurnaceHelper
import net.minecraftforge.common.config.Config

@Config(modid = Reference.MOD_ID, category = "")
object RagiConfig {

    @Config.Name("Debug Mode")
    @Config.LangKey(DebugMode.prefix)
    @JvmField
    var debugMode = DebugMode()

    @Config.Name("Custom Material Registry")
    @Config.Comment(
        "Add your custom materials in this format: index:name:type:color:formula:molar_mass:melting:boiling" +
        "\nindex: Int ... used for metadata, limited in 1025 <= index <= 2048" +
        "\nname: String ... used for translation key and ore dictionary" +
        "\ntype: Enum ... only available: CARBON, DUST, GAS, INTERNAl, LIQUID, METAL, WILDCARD" +
        "\ncolor: Int ... use color code" +
        "\nformula: String ... show its chemical formula" +
        "\nmolar_mass: Float ... show its molar mass [g/mol]" +
        "\nmelting: Int ... show its melting point [°C]" +
        "\nboiling: Int ... show its boiling point [°C]"
    )
    @Config.LangKey(Material.prefix)
    @JvmField
    var material = Material()

    @Config.Name("Recipe Map")
    @Config.Comment(
        "Add your custom recipes in this format: input;output\nThe stack format: mod:id:meta"
    )
    @Config.LangKey(RecipeMap.prefix)
    @JvmField
    var recipeMap = RecipeMap()

    @Config.Name("Utility")
    @Config.LangKey(Utility.prefix)
    @JvmField
    var utility = Utility()

    class DebugMode {

        companion object {
            const val prefix = "config.${Reference.MOD_ID}.debug"
        }

        @Config.LangKey("${prefix}.debug")
        @Config.Comment("If true, you can use some debug items but Ragi Library throws sooo many debug logs...")
        @JvmField
        var isDebug = false

    }

    class Display

    class Material {

        companion object {
            const val prefix = "config.${Reference.MOD_ID}.material"
        }

        @Config.Name("List for Custom Material")
        @Config.Comment("Register your custom materials in above format")
        @Config.LangKey("$prefix.custom")
        @JvmField
        var listMaterials = arrayOf(
            "1025:hiiragi_tsubasa:METAL:FF003F:H.T.:110.9f:283:1109"
        )

    }

    class RecipeMap {

        companion object {
            const val prefix = "config.${Reference.MOD_ID}.recipe_map"
        }

        @Config.Name("Forge Furnace - Tier: Burning")
        @Config.LangKey("${prefix}.forge_burning")
        @Config.RequiresMcRestart
        @JvmField
        var listForgeBurning = arrayOf(
            "minecraft:cobblestone:0;minecraft:magma:32767"
        )

        @Config.Name("Forge Furnace - Tier: Boosted")
        @Config.LangKey("${prefix}.forge_boosted")
        @Config.RequiresMcRestart
        @JvmField
        var listForgeBoosted: Array<String> = arrayOf()

        @Config.Name("Forge Furnace - Tier: Hell-rise")
        @Config.LangKey("${prefix}.forge_hellrise")
        @Config.RequiresMcRestart
        @JvmField
        var listForgeHellrise: Array<String> = arrayOf()

        init {
            ForgeFurnaceHelper.mapForgeBurning =
                RagiConfigHelper.convertListToMap(listForgeBurning, ForgeFurnaceHelper.mapForgeBurning)
            ForgeFurnaceHelper.mapForgeBoosted =
                RagiConfigHelper.convertListToMap(listForgeBoosted, ForgeFurnaceHelper.mapForgeBoosted)
            ForgeFurnaceHelper.mapForgeHellrise =
                RagiConfigHelper.convertListToMap(listForgeHellrise, ForgeFurnaceHelper.mapForgeHellrise)
        }
    }

    class Utility {

        companion object {
            const val prefix = "config.${Reference.MOD_ID}.utility"
        }

        @Config.Name("Override Max Stack Size")
        @Config.Comment("The maximum stack size of items added to this list will be changed to 64")
        @Config.LangKey("${prefix}.max_stack")
        @Config.RequiresMcRestart
        @JvmField
        var listMaxStack = arrayOf(
            "forge:bucketfilled",
            "minecraft:bed",
            "minecraft:beetroot_soup",
            "minecraft:birch_boat",
            "minecraft:boat",
            "minecraft:bucket",
            "minecraft:cake",
            "minecraft:chest_minecart",
            "minecraft:command_block_minecart",
            "minecraft:dark_oak_boat",
            "minecraft:diamond_horse_armor",
            "minecraft:egg",
            "minecraft:enchanted_book",
            "minecraft:ender_pearl",
            "minecraft:furnace_minecart",
            "minecraft:golden_horse_armor",
            "minecraft:hopper_minecart",
            "minecraft:iron_horse_armor",
            "minecraft:jungle_boat",
            "minecraft:lava_bucket",
            "minecraft:minecart",
            "minecraft:mushroom_stew",
            "minecraft:rabbit_stew",
            "minecraft:record_11",
            "minecraft:record_13",
            "minecraft:record_blocks",
            "minecraft:record_cat",
            "minecraft:record_chirp",
            "minecraft:record_far",
            "minecraft:record_mall",
            "minecraft:record_mellohi",
            "minecraft:record_stal",
            "minecraft:record_strad",
            "minecraft:record_wait",
            "minecraft:record_ward",
            "minecraft:saddle",
            "minecraft:sign",
            "minecraft:snowball",
            "minecraft:spruce_boat",
            "minecraft:tnt_minecart",
            "minecraft:water_bucket",
            "minecraft:written_book"
        )
    }

}