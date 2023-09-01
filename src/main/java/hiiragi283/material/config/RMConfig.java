package hiiragi283.material.config;

import hiiragi283.material.RMReference;
import net.minecraftforge.common.config.Config;

@Config(modid = RMReference.MOD_ID)
public class RMConfig {

    @Config.Name("Experimental")
    public static Experimental EXPERIMENTAL = new Experimental();

    @Config.Name("Integration")
    public static Integration INTEGRATION = new Integration();

    public static class Experimental {

        @Config.Name("Enable MetaTileEntity Blocks")
        @Config.RequiresMcRestart
        public boolean enableMetaTileBlock = false;

    }

    public static class Integration {

        @Config.Name("Enable All Integration Forcibly")
        @Config.RequiresMcRestart
        public boolean forceIntegration = false;

        @Config.Name("Enable Botania")
        @Config.RequiresMcRestart
        public boolean botania = true;

        @Config.Name("Enable Ender IO")
        @Config.RequiresMcRestart
        public boolean enderIO = true;

        @Config.Name("Enable Embers")
        @Config.RequiresMcRestart
        public boolean embers = true;

        @Config.Name("Enable IC2ex")
        @Config.RequiresMcRestart
        public boolean ic2Ex = true;

        @Config.Name("Enable Mekanism")
        @Config.RequiresMcRestart
        public boolean mekanism = true;

        @Config.Name("Enable Project Red")
        @Config.RequiresMcRestart
        public boolean projectRed = true;

        @Config.Name("Enable RailCraft")
        @Config.RequiresMcRestart
        public boolean railCraft = true;

        @Config.Name("Enable Thermal Series")
        @Config.RequiresMcRestart
        public boolean thermal = true;

        @Config.Name("Enable Thaumcraft")
        @Config.RequiresMcRestart
        public boolean thaum = true;

        @Config.Name("Enable Tinker's Construct")
        @Config.RequiresMcRestart
        public boolean tCon = true;

    }

}