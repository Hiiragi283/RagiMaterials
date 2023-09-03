package hiiragi283.material.api.material;

import hiiragi283.material.api.registry.HiiragiRegistryEntries;
import hiiragi283.material.api.shape.HiiragiShapes;
import hiiragi283.material.api.shape.ShapeType;
import hiiragi283.material.util.HiiragiColor;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author MrKono
 * @author turtton
 */
public class CommonMaterials extends HiiragiRegistryEntries {

    public static final HiiragiMaterial HYDROXIDE = HiiragiMaterial.createCompound("hydroxide", -1, new HashMap<>() {{
        put(ElementMaterials.HYDROGEN, 1);
        put(ElementMaterials.OXYGEN, 1);
    }}, builder -> builder.hasFluid = false);

    public static final HiiragiMaterial CARBONATE = HiiragiMaterial.createCompound("carbonate", -1, new HashMap<>() {{
        put(ElementMaterials.CARBON, 1);
        put(ElementMaterials.OXYGEN, 3);
    }}, builder -> builder.hasFluid = false);

    public static final HiiragiMaterial NITRATE = HiiragiMaterial.createCompound("nitrate", -1, new HashMap<>() {{
        put(ElementMaterials.NITROGEN, 1);
        put(ElementMaterials.OXYGEN, 3);
    }}, builder -> builder.hasFluid = false);

    public static final HiiragiMaterial SILICATE = HiiragiMaterial.createCompound("silicate", -1, new HashMap<>() {{
        put(ElementMaterials.SILICON, 1);
        put(ElementMaterials.OXYGEN, 4);
    }}, builder -> builder.hasFluid = false);

    public static final HiiragiMaterial SULFATE = HiiragiMaterial.createCompound("sulfate", -1, new HashMap<>() {{
        put(ElementMaterials.SULFUR, 1);
        put(ElementMaterials.OXYGEN, 4);
    }}, builder -> builder.hasFluid = false);

    //    Hydrogen    //

    public static final HiiragiMaterial WOOD = HiiragiMaterial.createMixture("wood", 10100, Arrays.asList(ElementMaterials.CARBON, ElementMaterials.HYDROGEN, ElementMaterials.OXYGEN), builder -> {
        builder.color = HiiragiColor.blendColor(new HashMap<>() {{
            put(HiiragiColor.DARK_GRAY, 2);
            put(HiiragiColor.RED, 1);
            put(HiiragiColor.YELLOW, 1);
        }}).getRGB();
        builder.hasFluid = false;
        builder.shapeType = ShapeType.WOOD;
    });

    public static final HiiragiMaterial WATER = HiiragiMaterial.createCompound("water", 10101, new HashMap<>() {{
        put(ElementMaterials.HYDROGEN, 2);
        put(ElementMaterials.OXYGEN, 1);
    }}, builder -> {
        builder.color = HiiragiColor.BLUE.getRGB();
        builder.hasFluid = false;
        builder.shapeType = ShapeType.LIQUID;
        builder.tempBoil = 373;
        builder.tempMelt = 273;
    });

    //    Beryllium    //

    public static final HiiragiMaterial AQUAMARINE = HiiragiMaterial.createCompound("aquamarine", 10401, new HashMap<>() {{
        put(ElementMaterials.BERYLLIUM, 3);
        put(ElementMaterials.ALUMINIUM, 2);
        put(ElementMaterials.SILICON, 6);
        put(ElementMaterials.OXYGEN, 16);
    }}, builder -> {
        builder.color = HiiragiColor.AQUA.getRGB();
        builder.shapeType = ShapeType.GEM_EMERALD;
    });

    public static final HiiragiMaterial EMERALD = HiiragiMaterial.createCompound("aquamarine", 10401, new HashMap<>() {{
        put(ElementMaterials.BERYLLIUM, 3);
        put(ElementMaterials.ALUMINIUM, 2);
        put(ElementMaterials.SILICON, 6);
        put(ElementMaterials.OXYGEN, 16);
    }}, builder -> {
        builder.color = HiiragiColor.GREEN.getRGB();
        builder.shapeType = ShapeType.GEM_EMERALD;
    });

    //    Carbon    //

    public static final HiiragiMaterial COAL = HiiragiMaterial.createCompound("coal", 10600, Collections.singletonMap(ElementMaterials.CARBON, 1), builder -> {
        builder.hasFluid = false;
        builder.shapeType = ShapeType.GEM_COAL;
    });

    public static final HiiragiMaterial CHARCOAL = HiiragiMaterial.createCompound("charcoal", 10601, Collections.singletonMap(ElementMaterials.CARBON, 1), builder -> {
        builder.hasFluid = false;
        builder.shapeType = ShapeType.GEM_COAL;
    });

    public static final HiiragiMaterial COKE = HiiragiMaterial.createCompound("coke", 10602, Collections.singletonMap(ElementMaterials.CARBON, 1), builder -> {
        builder.color = HiiragiColor.DARK_GRAY.getRGB();
        builder.hasFluid = false;
        builder.shapeType = ShapeType.GEM_COAL;
    });

    public static final HiiragiMaterial DIAMOND = HiiragiMaterial.createCompound("diamond", 10603, Collections.singletonMap(ElementMaterials.CARBON, 1), builder -> {
        builder.color = HiiragiColor.AQUA.getRGB();
        builder.hasFluid = false;
        builder.shapeType = ShapeType.GEM_DIAMOND;
    });

    public static final HiiragiMaterial SUGAR = HiiragiMaterial.createCompound("sugar", 10604, new HashMap<>() {{
        put(ElementMaterials.CARBON, 6);
        put(ElementMaterials.HYDROGEN, 12);
        put(ElementMaterials.OXYGEN, 6);
    }}, builder -> {
        builder.color = HiiragiColor.WHITE.getRGB();
        builder.shapeType = ShapeType.SOLID;
    });

    public static final HiiragiMaterial AMBER = HiiragiMaterial.createCompound("amber", 10605, Collections.singletonMap(WOOD, 1), builder -> {
        builder.color = HiiragiColor.GOLD.getRGB();
        builder.hasFluid = false;
        builder.shapeType = ShapeType.GEM_AMORPHOUS;
    });

    public static final HiiragiMaterial ASH = HiiragiMaterial.createMixture("ash", 10606, Arrays.asList(ElementMaterials.CARBON, ElementMaterials.SODIUM, ElementMaterials.POTASSIUM), builder -> {
        builder.color = HiiragiColor.GRAY.getRGB();
        builder.hasFluid = false;
        builder.shapeType = ShapeType.SOLID;
    });

    public static final HiiragiMaterial RUBBER = HiiragiMaterial.createPolymer("rubber", 10607, new HashMap<>() {{
        put(ElementMaterials.CARBON, 5);
        put(ElementMaterials.HYDROGEN, 8);
    }}, builder -> {
        builder.color = HiiragiColor.mixColor(HiiragiColor.BLACK, HiiragiColor.DARK_GRAY).getRGB();
        builder.formula = "(CC(=C)C=C)n";
        builder.shapeType = ShapeType.SOLID.copyAndEdit(set -> {
            set.add(HiiragiShapes.BLOCK);
            set.add(HiiragiShapes.INGOT);
            set.add(HiiragiShapes.PLATE);
        });
    });

    public static final HiiragiMaterial PLASTIC = HiiragiMaterial.createPolymer("plastic", 10608, new HashMap<>() {{
        put(ElementMaterials.CARBON, 2);
        put(ElementMaterials.HYDROGEN, 4);
    }}, builder -> {
        builder.color = HiiragiColor.mixColor(HiiragiColor.GRAY, HiiragiColor.WHITE).getRGB();
        builder.oreDictAlt.add("polyethylene");
        builder.shapeType = ShapeType.SOLID.copyAndEdit(set -> {
            set.add(HiiragiShapes.BLOCK);
            set.add(HiiragiShapes.INGOT);
            set.add(HiiragiShapes.PLATE);
        });
    });

    //    Nitrogen    //

    public static final HiiragiMaterial NITER = HiiragiMaterial.createCompound("niter", 10700, new HashMap<>() {{
        put(ElementMaterials.POTASSIUM, 1);
        put(NITRATE, 1);
    }}, builder -> {
        builder.color = HiiragiColor.WHITE.getRGB();
        builder.hasFluid = false;
        builder.oreDictAlt.add("saltpeter");
        builder.shapeType = ShapeType.GEM_CUBIC;
    });

    public static final HiiragiMaterial NITRIC_ACID = HiiragiMaterial.createSolution("nitric_acid", 10701, new HashMap<>() {{
        put(ElementMaterials.HYDROGEN, 1);
        put(NITRATE, 1);
    }});

    public static final HiiragiMaterial GUNPOWDER = HiiragiMaterial.createCompound("gunpowder", 10702, new HashMap<>() {{
        put(NITER.addBracket(), 1);
        put(ElementMaterials.CARBON, 1);
        put(ElementMaterials.SULFUR, 1);
    }}, builder -> {
        builder.color = HiiragiColor.DARK_GRAY.getRGB();
        builder.hasFluid = false;
        builder.shapeType = ShapeType.SOLID;
    });

    //    Fluorine    //

    public static final HiiragiMaterial CRYOLITE = HiiragiMaterial.createCompound("cryolite", 10900, new HashMap<>() {{
        put(ElementMaterials.SODIUM, 3);
        put(ElementMaterials.ALUMINIUM, 1);
        put(ElementMaterials.FLUORINE, 6);
    }}, builder -> {
        builder.color = HiiragiColor.WHITE.getRGB();
        builder.shapeType = ShapeType.GEM_CUBIC;
    });

    public static final HiiragiMaterial FLUORITE = HiiragiMaterial.createCompound("fluorite", 10901, new HashMap<>() {{
        put(ElementMaterials.CALCIUM, 1);
        put(ElementMaterials.FLUORINE, 2);
    }}, builder -> {
        builder.color = HiiragiColor.mixColor(HiiragiColor.GREEN, HiiragiColor.AQUA).getRGB();
        builder.shapeType = ShapeType.GEM_CUBIC;
    });

    public static final HiiragiMaterial HYDROGEN_FLUORIDE = HiiragiMaterial.createSolution("hydrogen_fluoride", 10902, new HashMap<>() {{
        put(ElementMaterials.HYDROGEN, 1);
        put(ElementMaterials.FLUORINE, 1);
    }});

    //    Sodium    //

    public static final HiiragiMaterial SALT = HiiragiMaterial.createCompound("salt", 11100, new HashMap<>() {{
        put(ElementMaterials.SODIUM, 1);
        put(ElementMaterials.CHLORINE, 1);
    }}, builder -> {
        builder.color = HiiragiColor.WHITE.getRGB();
        builder.shapeType = ShapeType.SOLID;
    });

    //    Magnesium    //

    public static final HiiragiMaterial PERIDOT = HiiragiMaterial.createCompound("peridot", 11200, new HashMap<>() {{
        put(ElementMaterials.MAGNESIUM, 2);
        put(SILICATE, 1);
    }}, builder -> {
        builder.color = HiiragiColor.GREEN.getRGB();
        builder.oreDictAlt.add("olivine");
        builder.shapeType = ShapeType.GEM_EMERALD;
    });

    //    Aluminium    //

    public static final HiiragiMaterial ALUMINA = HiiragiMaterial.createCompound("alumina", 11300, new HashMap<>() {{
        put(ElementMaterials.ALUMINIUM, 2);
        put(ElementMaterials.OXYGEN, 3);
    }}, builder -> {
        builder.color = HiiragiColor.WHITE.getRGB();
        builder.shapeType = ShapeType.SOLID;
    });

    public static final HiiragiMaterial BAUXITE = HiiragiMaterial.createHydrate("bauxite", 11301, ALUMINA, 2, builder -> {
        builder.color = HiiragiColor.blendColor(new HashMap<>() {{
            put(HiiragiColor.BLACK, 1);
            put(HiiragiColor.DARK_RED, 2);
            put(HiiragiColor.GOLD, 1);
        }}).getRGB();
        builder.hasFluid = false;
        builder.shapeType = ShapeType.SOLID;
    });

    public static final HiiragiMaterial RUBY = HiiragiMaterial.createCompound("ruby", 11302, Collections.singletonMap(ALUMINA, 1), builder -> {
        builder.color = HiiragiColor.RED.getRGB();
        builder.shapeType = ShapeType.GEM_RUBY;
    });

    public static final HiiragiMaterial SAPPHIRE = HiiragiMaterial.createCompound("sapphire", 11303, Collections.singletonMap(ALUMINA, 1), builder -> {
        builder.color = HiiragiColor.BLUE.getRGB();
        builder.shapeType = ShapeType.GEM_RUBY;
    });

    //    Silicon    //

    public static final HiiragiMaterial CLAY = HiiragiMaterial.createMixture("clay", 11400, Arrays.asList(ALUMINA, SILICATE), builder -> {
        builder.color = 0xC8C8DC;
        builder.hasFluid = false;
        builder.shapeType = ShapeType.SOLID;
    });

    public static final HiiragiMaterial END_STONE = HiiragiMaterial.createCompound("end_stone", 11401, Collections.singletonMap(SILICATE, 1), builder -> {
        builder.color = HiiragiColor.blendColor(new HashMap<>() {{
            put(HiiragiColor.YELLOW, 1);
            put(HiiragiColor.WHITE, 3);
        }}).getRGB();
        builder.hasFluid = false;
        builder.shapeType = ShapeType.SOLID;
    });

    public static final HiiragiMaterial GLASS = HiiragiMaterial.createCompound("glass", 11402, Collections.singletonMap(SILICATE, 1), builder -> {
        builder.color = HiiragiColor.WHITE.getRGB();
        builder.shapeType = ShapeType.SOLID;
    });

    public static final HiiragiMaterial LAVA = HiiragiMaterial.createCompound("lava", 11403, Collections.singletonMap(SILICATE, 1), builder -> {
        builder.color = HiiragiColor.mixColor(HiiragiColor.DARK_RED, HiiragiColor.GOLD).getRGB();
        builder.hasFluid = false;
        builder.shapeType = ShapeType.SOLID;
        builder.tempMelt = FluidRegistry.LAVA.getTemperature();
    });

    public static final HiiragiMaterial NETHERRACK = HiiragiMaterial.createCompound("netherrack", 11404, Collections.singletonMap(SILICATE, 1), builder -> {
        builder.color = HiiragiColor.DARK_RED.getRGB();
        builder.hasFluid = false;
        builder.shapeType = ShapeType.SOLID;
    });

    public static final HiiragiMaterial OBSIDIAN = HiiragiMaterial.createCompound("obsidian", 11405, Collections.singletonMap(SILICATE, 1), builder -> {
        builder.color = HiiragiColor.blendColor(new HashMap<>() {{
            put(HiiragiColor.BLACK, 2);
            put(HiiragiColor.BLUE, 1);
            put(HiiragiColor.RED, 1);
        }}).getRGB();
        builder.shapeType = ShapeType.SOLID;
    });

    public static final HiiragiMaterial QUARTZ = HiiragiMaterial.createCompound("quartz", 11406, Collections.singletonMap(SILICATE, 1), builder -> {
        builder.color = HiiragiColor.WHITE.getRGB();
        builder.oreDictAlt.add("nether_quartz");
        builder.shapeType = ShapeType.GEM_QUARTZ;
    });

    public static final HiiragiMaterial SOUL_SAND = HiiragiMaterial.createCompound("soul_sand", 11407, Collections.singletonMap(SILICATE, 1), builder -> {
        builder.color = HiiragiColor.blendColor(new HashMap<>() {{
            put(HiiragiColor.BLACK, 5);
            put(HiiragiColor.GOLD, 1);
        }}).getRGB();
        builder.hasFluid = false;
        builder.shapeType = ShapeType.SOLID;
    });

    public static final HiiragiMaterial STONE = HiiragiMaterial.createCompound("stone", 11408, Collections.singletonMap(SILICATE, 1), builder -> {
        builder.color = HiiragiColor.GRAY.getRGB();
        builder.hasFluid = false;
        builder.shapeType = ShapeType.STONE;
    });

    //    Sulfur    //

    public static final HiiragiMaterial SULFURIC_ACID = HiiragiMaterial.createSolution("sulfuric_acid", 11600, new HashMap<>() {{
        put(ElementMaterials.HYDROGEN, 2);
        put(SULFATE, 1);
    }}, builder -> builder.color = HiiragiColor.mixColor(HiiragiColor.GOLD, HiiragiColor.YELLOW).getRGB());

    //    Chlorine    //

    public static final HiiragiMaterial HYDROGEN_CHLORIDE = HiiragiMaterial.createSolution("hydrogen_chloride", 11700, new HashMap<>() {{
        put(ElementMaterials.HYDROGEN, 1);
        put(ElementMaterials.CHLORINE, 1);
    }});

    //    Calcium    //

    public static final HiiragiMaterial GYPSUM = HiiragiMaterial.createCompound("gypsum", 12001, new HashMap<>() {{
        put(ElementMaterials.CALCIUM, 1);
        put(SULFATE, 1);
    }}, builder -> {
        builder.hasFluid = false;
        builder.shapeType = ShapeType.GEM_CUBIC;
    });

    public static final HiiragiMaterial LIME = HiiragiMaterial.createCompound("lime", 12002, new HashMap<>() {{
        put(ElementMaterials.CALCIUM, 1);
        put(CARBONATE, 1);
    }}, builder -> {
        builder.color = HiiragiColor.WHITE.getRGB();
        builder.hasFluid = false;
        builder.shapeType = ShapeType.SOLID;
    });

    //    Titanium    //

    public static final HiiragiMaterial RUTILE = HiiragiMaterial.createCompound("rutile", 12200, new HashMap<>() {{
        put(ElementMaterials.TITANIUM, 1);
        put(ElementMaterials.OXYGEN, 2);
    }}, builder -> {
        builder.color = HiiragiColor.YELLOW.getRGB();
        builder.hasFluid = false;
        builder.shapeType = ShapeType.GEM_QUARTZ;
    });

    //    Chromium    //

    public static final HiiragiMaterial STAINLESS_STEEL = HiiragiMaterial.createAlloy("stainless_steel", 12400, new HashMap<>() {{
        put(ElementMaterials.IRON, 6);
        put(ElementMaterials.CHROMIUM, 1);
        put(ElementMaterials.MANGANESE, 1);
        put(ElementMaterials.NICKEL, 1);
    }}, builder -> {
        builder.color = HiiragiColor.mixColor(HiiragiColor.GRAY, HiiragiColor.WHITE).getRGB();
        builder.shapeType = ShapeType.METAL_ADVANCED;
    });

    //    Iron    //

    public static final HiiragiMaterial STEEL = HiiragiMaterial.createSteel("steel", 12600, Arrays.asList(ElementMaterials.IRON, ElementMaterials.CARBON), builder -> builder.color = HiiragiColor.GRAY.getRGB());

    //    Nickel    //

    public static final HiiragiMaterial CONSTANTAN = HiiragiMaterial.createAlloy("constantan", 12800, new HashMap<>() {{
        put(ElementMaterials.NICKEL, 1);
        put(ElementMaterials.COPPER, 1);
    }}, builder -> builder.shapeType = ShapeType.METAL_ADVANCED);

    public static final HiiragiMaterial INVAR = HiiragiMaterial.createAlloy("invar", 12801, new HashMap<>() {{
        put(ElementMaterials.NICKEL, 2);
        put(ElementMaterials.IRON, 1);
    }}, builder -> {
        builder.color = HiiragiColor.blendColor(new HashMap<>() {{
            put(HiiragiColor.GREEN, 1);
            put(HiiragiColor.GRAY, 3);
            put(HiiragiColor.WHITE, 4);
        }}).getRGB();
        builder.shapeType = ShapeType.METAL_ADVANCED;
    });

    //    Copper    //

    public static final HiiragiMaterial BRASS = HiiragiMaterial.createAlloy("brass", 12900, new HashMap<>() {{
        put(ElementMaterials.COPPER, 3);
        put(ElementMaterials.ZINC, 1);
    }}, builder -> {
        builder.color = HiiragiColor.GOLD.getRGB();
        builder.shapeType = ShapeType.METAL_ADVANCED;
    });

    public static final HiiragiMaterial BRONZE = HiiragiMaterial.createAlloy("bronze", 12901, new HashMap<>() {{
        put(ElementMaterials.COPPER, 3);
        put(ElementMaterials.TIN, 1);
    }}, builder -> builder.shapeType = ShapeType.METAL_ADVANCED);

    //    Silver    //

    public static final HiiragiMaterial ELECTRUM = HiiragiMaterial.createAlloy("electrum", 14700, new HashMap<>() {{
        put(ElementMaterials.SILVER, 1);
        put(ElementMaterials.GOLD, 1);
    }}, builder -> {
        builder.color = HiiragiColor.mixColor(HiiragiColor.GOLD, HiiragiColor.YELLOW, HiiragiColor.WHITE).getRGB();
        builder.shapeType = ShapeType.METAL_ADVANCED;
    });

    //    Tungsten    //

    public static final HiiragiMaterial TUNGSTEN_STEEL = HiiragiMaterial.createSteel("tungsten_steel", 17400, Arrays.asList(STEEL, ElementMaterials.TUNGSTEN), builder -> builder.color = HiiragiColor.mixColor(HiiragiColor.BLACK, HiiragiColor.DARK_GRAY).getRGB());

    //    Mercury    //

    public static final HiiragiMaterial CINNABAR = HiiragiMaterial.createCompound("cinnabar", 18000, new HashMap<>() {{
        put(ElementMaterials.MERCURY, 1);
        put(ElementMaterials.SULFUR, 1);
    }}, builder -> {
        builder.color = HiiragiColor.DARK_RED.getRGB();
        builder.hasFluid = false;
        builder.shapeType = ShapeType.GEM_EMERALD;
    });

}