package hiiragi283.ragi_materials.config

import hiiragi283.ragi_materials.Reference
import hiiragi283.ragi_materials.material.MaterialBuilder
import hiiragi283.ragi_materials.material.type.TypeRegistry
import hiiragi283.ragi_materials.recipe.forge_furnace.FFRecipe
import net.minecraftforge.common.config.Config
import java.awt.Color

@Config(modid = Reference.MOD_ID, category = "")
object RagiConfig {

    @Config.Name("Debug Mode")
    @Config.LangKey(DebugMode.prefix)
    @JvmField
    var debugMode = DebugMode()

    @Config.Name("Integration")
    @Config.LangKey(Integration.prefix)
    @JvmField
    var integration = Integration()

    @Config.Name("Custom Material Registry")
    @Config.Comment(
        "Add your custom materials in this format: index:name:type:color:formula:molar_mass:melting:boiling" +
                "\nindex: Int ... used for metadata, limited in 1023 <= index <= maxMaterials" +
                "\nname: String ... used for translation key and ore dictionary" +
                "\ntype: Enum ... only available: dust, fluid, liquid, metal, gas, metal_radio, semimetal" +
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

    class Integration {

        companion object {
            const val prefix = "config.${Reference.MOD_ID}.integration"
        }

        @Config.LangKey("${prefix}.ender_io")
        @Config.Comment("Enable integration for Ender IO")
        @JvmField
        var enableEIO = true

        @Config.LangKey("${prefix}.mekanism")
        @Config.Comment("Enable integration for Mekanism")
        @JvmField
        var enableMek = true

        @Config.LangKey("${prefix}.expansion")
        @Config.Comment("Enable integration for Thermal Expansion")
        @JvmField
        var enableTE = true

        @Config.LangKey("${prefix}.foundation")
        @Config.Comment("Enable integration for Thermal Foundation")
        @JvmField
        var enableTF = true
    }

    class Material {

        companion object {
            const val prefix = "config.${Reference.MOD_ID}.material"
        }

        @Config.Name("Enable radioactive decay")
        @Config.LangKey("$prefix.enable_decay")
        @JvmField
        var enableDecay = false


        @Config.Name("List for Custom Material")
        @Config.Comment("Register your custom materials in above format")
        @Config.LangKey("$prefix.custom")
        @JvmField
        var listMaterials = arrayOf(
            "1024:hiiragi_tsubasa:metal:FF003F:H.T.:110.9f:283:1109"
        )

        @Config.Name("Max MaterialRegistry")
        @Config.Comment("Set max number of materials registered")
        @Config.LangKey("$prefix.max")
        @Config.RangeInt(min = 1024, max = 32767)
        @JvmField
        var maxMaterials = 1024

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
            //"forge:bucketfilled",
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
            //"minecraft:lava_bucket",
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
            //"minecraft:water_bucket",
            "minecraft:written_book"
        )
    }

    //configからmaterialを登録するメソッド
    fun registerMaterial() {
        for (value in material.listMaterials) {
            //valueをばらしてプロパティを得る
            val listProperty = value.split(":")
            val index = listProperty[0].toInt()
            val name = listProperty[1]
            val typeRaw = listProperty[2]
            var type = TypeRegistry.WILDCARD
            val color = Color(listProperty[3].toIntOrNull(16)!!)
            val formula = listProperty[4]
            val molar = listProperty[5].toFloat()
            val tempMelt = listProperty[6].toInt()
            val tempBoil = listProperty[7].toInt()
            //MaterialTypeの確認
            if (TypeRegistry.map[typeRaw] !== null) {
                type = TypeRegistry.map[typeRaw]!!
            }
            //indexが1023以上maxMaterials以下，かつtypeがWILDCARDでない場合，materialを登録する
            if (index in 1023..material.maxMaterials && type != TypeRegistry.WILDCARD) {
                val material = MaterialBuilder(index, name, type).apply {
                    this.color = color
                    this.formula = formula
                    this.molar = molar
                    this.tempMelt = tempMelt
                    this.tempBoil = tempBoil
                }.register()
            }
        }
    }

    //configからレシピを登録するメソッド
    fun registerRecipe() {}
}