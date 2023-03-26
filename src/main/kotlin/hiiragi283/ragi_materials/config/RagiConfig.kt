package hiiragi283.ragi_materials.config

import hiiragi283.ragi_materials.Reference
import net.minecraftforge.common.config.Config

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
    @Config.LangKey(Material.prefix)
    @JvmField
    var material = Material()

    @Config.Name("Recipe Map")
    @Config.Comment("Add your custom recipes in this format: input;output\nThe stack format: mod:id:meta")
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

    }

    class RecipeMap {

        companion object {
            const val prefix = "config.${Reference.MOD_ID}.recipe_map"
        }

        @Config.Name("Blazing Forge - Fuel Map")
        @Config.LangKey("${prefix}.blazing_forge")
        @Config.RequiresMcRestart
        @JvmField
        var fuelBlazingForge = arrayOf(
                "lava;100",
                "pyrotheum;10"
        )

    }

    class Utility {

        companion object {
            const val prefix = "config.${Reference.MOD_ID}.utility"
        }

    }
}