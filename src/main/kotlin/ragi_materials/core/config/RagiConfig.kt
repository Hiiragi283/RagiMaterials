package ragi_materials.core.config

import net.minecraftforge.common.config.Config
import ragi_materials.core.RagiMaterials

@Config(modid = RagiMaterials.MOD_ID, category = "")
object RagiConfig {

    @Config.Name("Integration")
    @Config.LangKey(Integration.prefix)
    @JvmField
    var integration = Integration()

    @Config.Name("Material")
    @Config.LangKey(Material.prefix)
    @JvmField
    var material = Material

    @Config.Name("Module")
    @Config.LangKey(Module.prefix)
    @JvmField
    var module = Module

    @Config.Name("Recipe Map")
    @Config.LangKey(RecipeMap.prefix)
    @JvmField
    var recipeMap = RecipeMap

    class Integration {

        companion object {
            const val prefix = "config.${RagiMaterials.MOD_ID}.integration"

        }

        @Config.LangKey("$prefix.ender_io")
        @Config.Comment("Enable integration for Ender IO")
        @JvmField
        var enableEIO = true

        @Config.LangKey("$prefix.mekanism")
        @Config.Comment("Enable integration for Mekanism")
        @JvmField
        var enableMek = true

        @Config.LangKey("$prefix.expansion")
        @Config.Comment("Enable integration for Thermal Expansion")
        @JvmField
        var enableTE = true

        @Config.LangKey("$prefix.foundation")
        @Config.Comment("Enable integration for Thermal Foundation")
        @JvmField
        var enableTF = true

    }

    object Material {

        const val prefix = "config.${RagiMaterials.MOD_ID}.material"

        @Config.Name("Enable radioactive decay")
        @Config.LangKey("$prefix.enable_decay")
        @JvmField
        var enableDecay = false

    }

    object Module {

        const val prefix = "config.${RagiMaterials.MOD_ID}.module"

        @Config.Name("Enable Magic Module")
        @Config.LangKey("${prefix}.magic")
        @JvmField
        var enableMagic = true

        @Config.Name("Enable Main Module")
        @Config.LangKey("${prefix}.main")
        @JvmField
        var enableMain = true

        @Config.Name("Enable Metallurgy Module")
        @Config.LangKey("${prefix}.metallurgy")
        @JvmField
        var enableMetallurgy = true

        @Config.Name("Enable Experimental Features")
        @Config.LangKey("${prefix}.experimental")
        @JvmField
        var enableExperimental = true

    }

    object RecipeMap {

        const val prefix = "config.${RagiMaterials.MOD_ID}.recipe_map"

        @Config.Name("Blazing Forge - Fuel Map")
        @Config.LangKey("$prefix.blazing_forge")
        @JvmField
        var fuelBlazingForge = arrayOf(
                "lava;100",
                "pyrotheum;10"
        )
    }
}