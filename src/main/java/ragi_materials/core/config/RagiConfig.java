package ragi_materials.core.config;

import net.minecraftforge.common.config.Config;
import ragi_materials.core.RagiMaterials;

@Config(modid = RagiMaterials.MOD_ID, category = "")
public class RagiConfig {

    @Config.Name("Integration")
    public static Integration integration = new Integration();

    @Config.Name("Material")
    public static Material material = new Material();

    @Config.Name("Module")
    public static Module module = new Module();

    @Config.Name("Recipe Map")
    public static RecipeMap recipeMap = new RecipeMap();

    public static class Integration {

        @Config.Name("Enable integration for Ender IO")
        public Boolean enableEIO = true;

        @Config.Name("Enable integration for Mekanism")
        public Boolean enableMek = true;

        @Config.Name("Enable integration for Thermal Expansion")
        public Boolean enableTE = true;

        @Config.Name("Enable integration for Thermal Foundation")
        public Boolean enableTF = true;

    }

    public static class Material {

        @Config.Name("Enable radioactive decay")
        public Boolean enableDecay = false;

    }

    public static class Module {

        @Config.Name("Enable Experimental Module")
        public Boolean enableExperimental = true;

        @Config.Name("Enable Magic Module")
        public Boolean enableMagic = true;

        @Config.Name("Enable Main Module")
        public Boolean enableMain = true;
        @Config.Name("Enable Metallurgy Module")
        public Boolean enableMetallurgy = true;

    }

    public static class RecipeMap {

        @Config.Name("Blazing Forge - Fuel Map")
        public String[] fuelBlazingForge = {"lava;100", "pyrotheum;10"};

    }

}