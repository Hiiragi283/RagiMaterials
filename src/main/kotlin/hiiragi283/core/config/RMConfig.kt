package hiiragi283.core.config

import hiiragi283.material.RMReference
import net.minecraftforge.common.config.Config

@Config(modid = RMReference.MOD_ID)
object RMConfig {

    @Config.Name("Experimental")
    @JvmField
    val EXPERIMENTAL = Experimental()

    @Config.Name("Integration")
    @JvmField
    val INTEGRATION = Integration()

    @Config.Name("Material")
    @JvmField
    val MATERIAL = Material()

    class Experimental {

        @Config.Name("Enable MetaTileEntity Blocks")
        @Config.RequiresMcRestart
        @JvmField
        var enableMetaTileBlock: Boolean = false

    }

    class Integration {

        @Config.Name("Enable All Integration Forcibly")
        @Config.RequiresMcRestart
        @JvmField
        var forceIntegration: Boolean = false

        @Config.Name("Enable Botania")
        @Config.RequiresMcRestart
        @JvmField
        var botania: Boolean = true

        @Config.Name("Enable Ender IO")
        @Config.RequiresMcRestart
        @JvmField
        var enderIO: Boolean = true

        @Config.Name("Enable Embers")
        @Config.RequiresMcRestart
        @JvmField
        var embers: Boolean = true

        @Config.Name("Enable IC2ex")
        @Config.RequiresMcRestart
        @JvmField
        var ic2Ex: Boolean = true

        @Config.Name("Enable Mekanism")
        @Config.RequiresMcRestart
        @JvmField
        var mekanism: Boolean = true

        @Config.Name("Enable Project Red")
        @Config.RequiresMcRestart
        @JvmField
        var projectRed: Boolean = true

        @Config.Name("Enable RailCraft")
        @Config.RequiresMcRestart
        @JvmField
        var railCraft: Boolean = true

        @Config.Name("Enable Thermal Series")
        @Config.RequiresMcRestart
        @JvmField
        var thermal: Boolean = true

        @Config.Name("Enable Thaumcraft")
        @Config.RequiresMcRestart
        @JvmField
        var thaum: Boolean = true

        @Config.Name("Enable Tinker's Construct")
        @Config.RequiresMcRestart
        @JvmField
        var tCon: Boolean = true

    }

    class Material {

        @Config.Name("Disable Vanilla Parts")
        @Config.RequiresMcRestart
        @JvmField
        var disableVanillaParts: Boolean = false

        @Config.Name("Enable Lanthanides")
        @Config.RequiresMcRestart
        @JvmField
        var lanthanoides: Boolean = true

        @Config.Name("Enable Transuranium Elements")
        @Config.RequiresMcRestart
        @JvmField
        var transUran: Boolean = true

        @Config.Name("Generate Sample Json")
        @JvmField
        var sampleJson: Boolean = true

        @Config.Name("Print Registered Materials on Log")
        @JvmField
        var printMaterials: Boolean = false

    }

}