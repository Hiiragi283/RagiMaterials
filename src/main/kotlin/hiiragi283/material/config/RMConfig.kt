package hiiragi283.material.config

import hiiragi283.material.RagiMaterials
import net.minecraftforge.common.config.Config

@Config(modid = RagiMaterials.MODID)
object RMConfig {

    @Config.Name("Misc")
    @JvmField
    val MISC = Misc()

    class Misc {

        @Config.Name("Enable Lanthanides")
        @Config.RequiresMcRestart
        @JvmField
        var LANTHANIDES: Boolean = true

        @Config.Name("Enable Transuranium Elements")
        @Config.RequiresMcRestart
        @JvmField
        var TRANSURAN: Boolean = true

        @Config.Name("Generate Sample Json")
        @JvmField
        var SAMPLE_JSON: Boolean = true

        @Config.Name("Print Registered Materials on Log")
        @JvmField
        var PRINT_MATERIALS: Boolean = false

    }

    @JvmField
    var enableLanthanides: Boolean = true

    @JvmField
    var enableTransuraniumElement: Boolean = true

    @JvmField
    var generateSampleJson: Boolean = true

    @JvmField
    var printRegisteredMaterials: Boolean = false

}