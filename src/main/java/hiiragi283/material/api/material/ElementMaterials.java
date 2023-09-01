package hiiragi283.material.api.material;

import hiiragi283.material.api.registry.HiiragiRegistryEntries;
import hiiragi283.material.api.shape.HiiragiShapes;
import hiiragi283.material.api.shape.ShapeType;
import hiiragi283.material.util.HiiragiColor;

import java.util.HashMap;

/**
 * @author MrKono
 * @author turtton
 */
public class ElementMaterials extends HiiragiRegistryEntries {

    //    Elements    //

    //    1st Period    //

    public static final HiiragiMaterial HYDROGEN = HiiragiMaterial.create("hydrogen", 1, builder -> {
        builder.color = HiiragiColor.BLUE.getRGB();
        builder.formula = "H";
        builder.molar = 1.0;
        builder.shapeType = ShapeType.GAS;
        builder.tempBoil = 20;
        builder.tempMelt = 14;
    });

    public static final HiiragiMaterial HELIUM = HiiragiMaterial.create("helium", 2, builder -> {
        builder.color = HiiragiColor.YELLOW.getRGB();
        builder.formula = "He";
        builder.molar = 4.0;
        builder.shapeType = ShapeType.GAS;
        builder.tempBoil = 3;
        builder.tempMelt = 1;
    });

    //    2nd Period    //

    public static final HiiragiMaterial LITHIUM = HiiragiMaterial.create("lithium", 3, builder -> {
        builder.color = HiiragiColor.GRAY.getRGB();
        builder.formula = "Li";
        builder.molar = 6.9;
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 1560;
        builder.tempMelt = 454;
    });

    public static final HiiragiMaterial BERYLLIUM = HiiragiMaterial.create("beryllium", 4, builder -> {
        builder.color = HiiragiColor.DARK_GREEN.getRGB();
        builder.formula = "Be";
        builder.molar = 9.0;
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 2742;
        builder.tempMelt = 1560;
    });

    public static final HiiragiMaterial BORON = HiiragiMaterial.create("boron", 5, builder -> {
        builder.color = HiiragiColor.GRAY.getRGB();
        builder.formula = "B";
        builder.molar = 10.8;
        builder.shapeType = ShapeType.SOLID;
        builder.tempBoil = 4200;
        builder.tempMelt = 2349;
    });

    public static final HiiragiMaterial CARBON = HiiragiMaterial.create("carbon", 6, builder -> {
        builder.color = HiiragiColor.mixColor(HiiragiColor.BLACK, HiiragiColor.DARK_GRAY).getRGB();
        builder.formula = "C";
        builder.molar = 12.0;
        builder.shapeType = ShapeType.SOLID;
    });

    public static final HiiragiMaterial NITROGEN = HiiragiMaterial.create("nitrogen", 7, builder -> {
        builder.color = HiiragiColor.AQUA.getRGB();
        builder.formula = "N";
        builder.molar = 14.0;
        builder.shapeType = ShapeType.GAS;
        builder.tempBoil = 77;
        builder.tempMelt = 63;
    });

    public static final HiiragiMaterial OXYGEN = HiiragiMaterial.create("oxygen", 8, builder -> {
        builder.formula = "O";
        builder.molar = 16.0;
        builder.shapeType = ShapeType.GAS;
        builder.tempBoil = 90;
        builder.tempMelt = 54;
    });

    public static final HiiragiMaterial FLUORINE = HiiragiMaterial.create("fluorine", 9, builder -> {
        builder.color = HiiragiColor.GREEN.getRGB();
        builder.formula = "F";
        builder.molar = 19.0;
        builder.shapeType = ShapeType.GAS;
        builder.tempBoil = 85;
        builder.tempMelt = 54;
    });

    public static final HiiragiMaterial NEON = HiiragiMaterial.create("neon", 10, builder -> {
        builder.color = HiiragiColor.LIGHT_PURPLE.getRGB();
        builder.formula = "Ne";
        builder.molar = 20.2;
        builder.shapeType = ShapeType.GAS;
        builder.tempBoil = 27;
        builder.tempMelt = 25;
    });

    //    3rd Period    //

    public static final HiiragiMaterial SODIUM = HiiragiMaterial.create("sodium", 11, builder -> {
        builder.color = HiiragiColor.blendColor(new HashMap<>() {{
            put(HiiragiColor.DARK_BLUE, 1);
            put(HiiragiColor.BLUE, 4);
        }}).getRGB();
        builder.formula = "Na";
        builder.molar = 23.0;
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 1156;
        builder.tempMelt = 371;
    });

    public static final HiiragiMaterial MAGNESIUM = HiiragiMaterial.create("magnesium", 12, builder -> {
        builder.color = HiiragiColor.mixColor(HiiragiColor.LIGHT_PURPLE, HiiragiColor.WHITE).getRGB();
        builder.formula = "Mg";
        builder.molar = 24.3;
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 1363;
        builder.tempMelt = 923;
    });

    public static final HiiragiMaterial ALUMINIUM = HiiragiMaterial.create("aluminium", 13, builder -> {
        builder.color = HiiragiColor.blendColor(new HashMap<>() {{
            put(HiiragiColor.BLUE, 1);
            put(HiiragiColor.WHITE, 5);
        }}).getRGB();
        builder.formula = "Al";
        builder.molar = 27.0;
        builder.shapeType = ShapeType.METAL_ADVANCED;
        builder.tempBoil = 2792;
        builder.tempMelt = 933;
    });

    public static final HiiragiMaterial SILICON = HiiragiMaterial.create("silicon", 14, builder -> {
        builder.color = HiiragiColor.blendColor(new HashMap<>() {{
            put(HiiragiColor.BLACK, 2);
            put(HiiragiColor.GRAY, 1);
            put(HiiragiColor.BLUE, 1);
        }}).getRGB();
        builder.formula = "Si";
        builder.molar = 28.1;
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 3538;
        builder.tempMelt = 1687;
    });

    public static final HiiragiMaterial SULFUR = HiiragiMaterial.create("sulfur", 16, builder -> {
        builder.color = HiiragiColor.mixColor(HiiragiColor.GOLD, HiiragiColor.YELLOW).getRGB();
        builder.formula = "S";
        builder.molar = 32.1;
        builder.shapeType = ShapeType.SOLID;
        builder.tempBoil = 718;
        builder.tempMelt = 388;
    });

    public static final HiiragiMaterial CHLORINE = HiiragiMaterial.create("chlorine", 17, builder -> {
        builder.color = HiiragiColor.YELLOW.getRGB();
        builder.formula = "Cl";
        builder.molar = 35.5;
        builder.shapeType = ShapeType.GAS;
        builder.tempBoil = 239;
        builder.tempMelt = 171;
    });

    public static final HiiragiMaterial ARGON = HiiragiMaterial.create("argon", 18, builder -> {
        builder.color = HiiragiColor.LIGHT_PURPLE.getRGB();
        builder.formula = "Ar";
        builder.molar = 40.0;
        builder.shapeType = ShapeType.GAS;
        builder.tempBoil = 87;
        builder.tempMelt = 84;
    });

    //    4th Period    //

    public static final HiiragiMaterial POTASSIUM = HiiragiMaterial.create("potassium", 19, builder -> {
        builder.color = HiiragiColor.blendColor(new HashMap<>() {{
            put(HiiragiColor.DARK_BLUE, 2);
            put(HiiragiColor.BLUE, 3);
        }}).getRGB();
        builder.formula = "K";
        builder.molar = 39.1;
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 1032;
        builder.tempMelt = 337;
    });

    public static final HiiragiMaterial CALCIUM = HiiragiMaterial.create("calcium", 20, builder -> {
        builder.color = HiiragiColor.GRAY.getRGB();
        builder.formula = "Ar";
        builder.molar = 40.1;
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 1757;
        builder.tempMelt = 1115;
    });

    public static final HiiragiMaterial TITANIUM = HiiragiMaterial.create("titanium", 22, builder -> {
        builder.color = HiiragiColor.blendColor(new HashMap<>() {{
            put(HiiragiColor.GOLD, 1);
            put(HiiragiColor.WHITE, 2);
        }}).getRGB();
        builder.formula = "Ti";
        builder.molar = 47.9;
        builder.shapeType = ShapeType.METAL_ADVANCED;
        builder.tempBoil = 3560;
        builder.tempMelt = 1941;
    });

    public static final HiiragiMaterial CHROMIUM = HiiragiMaterial.create("chromium", 24, builder -> {
        builder.color = HiiragiColor.GREEN.getRGB();
        builder.formula = "Cr";
        builder.molar = 52.0;
        builder.oreDictAlt.add("chrome");
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 2944;
        builder.tempMelt = 2180;
    });

    public static final HiiragiMaterial MANGANESE = HiiragiMaterial.create("manganese", 25, builder -> {
        builder.color = HiiragiColor.mixColor(HiiragiColor.RED, HiiragiColor.WHITE).getRGB();
        builder.formula = "Mn";
        builder.molar = 54.9;
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 2334;
        builder.tempMelt = 1519;
    });

    public static final HiiragiMaterial IRON = HiiragiMaterial.create("iron", 26, builder -> {
        builder.color = HiiragiColor.blendColor(new HashMap<>() {{
            put(HiiragiColor.GRAY, 1);
            put(HiiragiColor.WHITE, 2);
        }}).getRGB();
        builder.formula = "Fe";
        builder.molar = 55.8;
        builder.shapeType = ShapeType.METAL_ADVANCED.copyAndEdit(set -> {
            set.remove(HiiragiShapes.BLOCK);
            set.remove(HiiragiShapes.INGOT);
            set.remove(HiiragiShapes.NUGGET);
        });
        builder.tempBoil = 3134;
        builder.tempMelt = 811;
    });

    public static final HiiragiMaterial COBALT = HiiragiMaterial.create("cobalt", 27, builder -> {
        builder.color = HiiragiColor.BLUE.getRGB();
        builder.formula = "Co";
        builder.molar = 58.9;
        builder.shapeType = ShapeType.METAL_ADVANCED;
        builder.tempBoil = 3200;
        builder.tempMelt = 1768;
    });

    public static final HiiragiMaterial NICKEL = HiiragiMaterial.create("nickel", 28, builder -> {
        builder.color = HiiragiColor.mixColor(HiiragiColor.GOLD, HiiragiColor.WHITE).getRGB();
        builder.formula = "Ni";
        builder.molar = 58.7;
        builder.shapeType = ShapeType.METAL_ADVANCED;
        builder.tempBoil = 3186;
        builder.tempMelt = 1728;
    });

    public static final HiiragiMaterial COPPER = HiiragiMaterial.create("copper", 29, builder -> {
        builder.color = HiiragiColor.mixColor(HiiragiColor.GOLD, HiiragiColor.RED).getRGB();
        builder.formula = "Cu";
        builder.molar = 63.5;
        builder.shapeType = ShapeType.METAL_ADVANCED;
        builder.tempBoil = 2835;
        builder.tempMelt = 1358;
    });

    public static final HiiragiMaterial ZINC = HiiragiMaterial.create("zinc", 30, builder -> {
        builder.color = HiiragiColor.blendColor(new HashMap<>() {{
            put(HiiragiColor.GREEN, 1);
            put(HiiragiColor.WHITE, 2);
        }}).getRGB();
        builder.formula = "Zn";
        builder.molar = 65.4;
        builder.shapeType = ShapeType.METAL_ADVANCED;
        builder.tempBoil = 1180;
        builder.tempMelt = 693;
    });

    public static final HiiragiMaterial GALLIUM = HiiragiMaterial.create("gallium", 31, builder -> {
        builder.color = HiiragiColor.GRAY.getRGB();
        builder.formula = "Ga";
        builder.molar = 69.7;
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 2673;
        builder.tempMelt = 303;
    });

    public static final HiiragiMaterial ARSENIC = HiiragiMaterial.create("arsenic", 33, builder -> {
        builder.color = HiiragiColor.GRAY.getRGB();
        builder.formula = "As";
        builder.molar = 74.9;
        builder.shapeType = ShapeType.SOLID;
        //builder.tempBoil = 887;
        //builder.tempMelt = 1090;
    });

    //    5th Period    //

    public static final HiiragiMaterial RUBIDIUM = HiiragiMaterial.create("rubidium", 37, builder -> {
        builder.color = HiiragiColor.blendColor(new HashMap<>() {{
            put(HiiragiColor.DARK_BLUE, 3);
            put(HiiragiColor.BLUE, 2);
        }}).getRGB();
        builder.formula = "Rb";
        builder.molar = 85.5;
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 961;
        builder.tempMelt = 312;
    });

    public static final HiiragiMaterial STRONTIUM = HiiragiMaterial.create("strontium", 38, builder -> {
        builder.color = HiiragiColor.GRAY.getRGB();
        builder.formula = "Sr";
        builder.molar = 87.6;
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 1655;
        builder.tempMelt = 1050;
    });

    public static final HiiragiMaterial ZIRCONIUM = HiiragiMaterial.create("zirconium", 40, builder -> {
        builder.color = HiiragiColor.GRAY.getRGB();
        builder.formula = "Zr";
        builder.molar = 91.2;
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 4682;
        builder.tempMelt = 2128;
    });

    public static final HiiragiMaterial NIOBIUM = HiiragiMaterial.create("niobium", 41, builder -> {
        builder.color = HiiragiColor.GRAY.getRGB();
        builder.formula = "Nb";
        builder.molar = 92.9;
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 5017;
        builder.tempMelt = 2750;
    });

    public static final HiiragiMaterial MOLYBDENUM = HiiragiMaterial.create("molybdenum", 42, builder -> {
        builder.color = HiiragiColor.GRAY.getRGB();
        builder.formula = "Mo";
        builder.molar = 96.0;
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 4912;
        builder.tempMelt = 2896;
    });

    public static final HiiragiMaterial RUTHENIUM = HiiragiMaterial.create("ruthenium", 44, builder -> {
        builder.color = HiiragiColor.blendColor(new HashMap<>() {{
            put(HiiragiColor.BLUE, 1);
            put(HiiragiColor.LIGHT_PURPLE, 3);
        }}).getRGB();
        builder.formula = "Ru";
        builder.molar = 101.1;
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 4423;
        builder.tempMelt = 2607;
    });

    public static final HiiragiMaterial RHODIUM = HiiragiMaterial.create("rhodium", 45, builder -> {
        builder.color = HiiragiColor.blendColor(new HashMap<>() {{
            put(HiiragiColor.BLUE, 1);
            put(HiiragiColor.RED, 3);
        }}).getRGB();
        builder.formula = "Rh";
        builder.molar = 102.9;
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 3968;
        builder.tempMelt = 2237;
    });

    public static final HiiragiMaterial PALLADIUM = HiiragiMaterial.create("palladium", 46, builder -> {
        builder.color = HiiragiColor.blendColor(new HashMap<>() {{
            put(HiiragiColor.BLUE, 1);
            put(HiiragiColor.YELLOW, 3);
        }}).getRGB();
        builder.formula = "Pd";
        builder.molar = 106.4;
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 3236;
        builder.tempMelt = 1828;
    });

    public static final HiiragiMaterial SILVER = HiiragiMaterial.create("silver", 47, builder -> {
        builder.color = HiiragiColor.blendColor(new HashMap<>() {{
            put(HiiragiColor.AQUA, 1);
            put(HiiragiColor.WHITE, 3);
        }}).getRGB();
        builder.formula = "Ag";
        builder.molar = 107.9;
        builder.shapeType = ShapeType.METAL_ADVANCED;
        builder.tempBoil = 2435;
        builder.tempMelt = 1235;
    });

    public static final HiiragiMaterial TIN = HiiragiMaterial.create("tin", 50, builder -> {
        builder.color = HiiragiColor.blendColor(new HashMap<>() {{
            put(HiiragiColor.BLUE, 1);
            put(HiiragiColor.AQUA, 1);
            put(HiiragiColor.WHITE, 3);
        }}).getRGB();
        builder.formula = "Sn";
        builder.molar = 118.7;
        builder.shapeType = ShapeType.METAL_ADVANCED;
        builder.tempBoil = 2875;
        builder.tempMelt = 505;
    });

    public static final HiiragiMaterial ANTIMONY = HiiragiMaterial.create("antimony", 51, builder -> {
        builder.color = HiiragiColor.GRAY.getRGB();
        builder.formula = "Sb";
        builder.molar = 121.8;
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 1860;
        builder.tempMelt = 904;
    });

    //    6th Period    //
    static final public HiiragiMaterial CAESIUM = HiiragiMaterial.create("caesium", 55, builder -> {
        builder.color = HiiragiColor.blendColor(new HashMap<>() {{
            put(HiiragiColor.DARK_BLUE, 1);
            put(HiiragiColor.BLUE, 1);
        }}).getRGB();
        builder.formula = "Cs";
        builder.molar = 132.9;
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 944;
        builder.tempMelt = 302;
    });

    static final public HiiragiMaterial BARIUM = HiiragiMaterial.create("barium", 56, builder -> {
        builder.color = HiiragiColor.GRAY.getRGB();
        builder.formula = "Ba";
        builder.molar = 137.3;
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 2170;
        builder.tempMelt = 1000;
    });

    //    Lanthanides Start    //

    public static final HiiragiMaterial NEODYMIUM = HiiragiMaterial.create("neodymium", 60, builder -> {
        builder.color = HiiragiColor.GRAY.getRGB();
        builder.formula = "Nd";
        builder.molar = 144.2;
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 3347;
        builder.tempMelt = 1297;
    });

    public static final HiiragiMaterial SAMARIUM = HiiragiMaterial.create("samarium", 62, builder -> {
        builder.color = HiiragiColor.GRAY.getRGB();
        builder.formula = "Sm";
        builder.molar = 150.4;
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 2067;
        builder.tempMelt = 1345;
    });

    //    Lanthanides End    //

    public static final HiiragiMaterial HAFNIUM = HiiragiMaterial.create("hafnium", 72, builder -> {
        builder.color = HiiragiColor.GRAY.getRGB();
        builder.formula = "Sm";
        builder.molar = 150.4;
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 2067;
        builder.tempMelt = 1345;
    });

    public static final HiiragiMaterial TANTALUM = HiiragiMaterial.create("tantalum", 73, builder -> {
        builder.color = HiiragiColor.GRAY.getRGB();
        builder.formula = "Ta";
        builder.molar = 180.9;
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 5731;
        builder.tempMelt = 3290;
    });

    public static final HiiragiMaterial TUNGSTEN = HiiragiMaterial.create("tungsten", 74, builder -> {
        builder.color = HiiragiColor.blendColor(new HashMap<>() {{
            put(HiiragiColor.BLACK, 2);
            put(HiiragiColor.DARK_GRAY, 1);
        }}).getRGB();
        builder.formula = "W";
        builder.molar = 183.8;
        builder.shapeType = ShapeType.METAL_ADVANCED;
        builder.tempBoil = 5828;
        builder.tempMelt = 3695;
    });

    public static final HiiragiMaterial OSMIUM = HiiragiMaterial.create("osmium", 76, builder -> {
        builder.color = HiiragiColor.blendColor(new HashMap<>() {{
            put(HiiragiColor.BLUE, 1);
            put(HiiragiColor.WHITE, 3);
        }}).getRGB();
        builder.formula = "Os";
        builder.molar = 190.2;
        builder.shapeType = ShapeType.METAL_ADVANCED;
        builder.tempBoil = 5285;
        builder.tempMelt = 3306;
    });

    public static final HiiragiMaterial IRIDIUM = HiiragiMaterial.create("iridium", 77, builder -> {
        builder.color = HiiragiColor.blendColor(new HashMap<>() {{
            put(HiiragiColor.AQUA, 1);
            put(HiiragiColor.WHITE, 3);
        }}).getRGB();
        builder.formula = "Ir";
        builder.molar = 192.2;
        builder.shapeType = ShapeType.METAL_ADVANCED;
        builder.tempBoil = 4701;
        builder.tempMelt = 2719;
    });

    public static final HiiragiMaterial PLATINUM = HiiragiMaterial.create("platinum", 78, builder -> {
        builder.color = HiiragiColor.blendColor(new HashMap<>() {{
            put(HiiragiColor.GREEN, 1);
            put(HiiragiColor.WHITE, 3);
        }}).getRGB();
        builder.formula = "Pt";
        builder.molar = 195.1;
        builder.shapeType = ShapeType.METAL_ADVANCED;
        builder.tempBoil = 4098;
        builder.tempMelt = 2041;
    });

    public static final HiiragiMaterial GOLD = HiiragiMaterial.create("gold", 79, builder -> {
        builder.color = HiiragiColor.mixColor(HiiragiColor.GOLD, HiiragiColor.YELLOW).getRGB();
        builder.formula = "Au";
        builder.molar = 197.0;
        builder.shapeType = ShapeType.METAL_ADVANCED;
        builder.tempBoil = 3129;
        builder.tempMelt = 1337;
    });

    public static final HiiragiMaterial MERCURY = HiiragiMaterial.create("mercury", 80, builder -> {
        builder.formula = "Hg";
        builder.molar = 200.6;
        builder.shapeType = ShapeType.LIQUID;
        builder.tempBoil = 670;
        builder.tempMelt = 234;
    });

    public static final HiiragiMaterial LEAD = HiiragiMaterial.create("lead", 82, builder -> {
        builder.color = HiiragiColor.mixColor(HiiragiColor.DARK_BLUE, HiiragiColor.DARK_GRAY, HiiragiColor.WHITE).getRGB();
        builder.formula = "Pb";
        builder.molar = 207.2;
        builder.shapeType = ShapeType.METAL_ADVANCED;
        builder.tempBoil = 2022;
        builder.tempMelt = 601;
    });

    public static final HiiragiMaterial BISMUTH = HiiragiMaterial.create("bismuth", 83, builder -> {
        builder.color = HiiragiColor.AQUA.getRGB();
        builder.formula = "Bi";
        builder.molar = 209.0;
        builder.shapeType = ShapeType.METAL_ADVANCED;
        builder.tempBoil = 1837;
        builder.tempMelt = 545;
    });


    //    7th Period    //

    public static final HiiragiMaterial FRANCIUM = HiiragiMaterial.create("francium", 87, builder -> {
        builder.color = HiiragiColor.DARK_BLUE.getRGB();
        builder.formula = "Fr";
        //builder.molar = 223.0;
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 890;
        builder.tempMelt = 281;
    });

    public static final HiiragiMaterial RADIUM = HiiragiMaterial.create("radium", 88, builder -> {
        builder.color = HiiragiColor.GRAY.getRGB();
        builder.formula = "Ra";
        //builder.molar = 226.0;
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 2010;
        builder.tempMelt = 973;
    });

    //    Actinides Start    //

    public static final HiiragiMaterial THORIUM = HiiragiMaterial.create("thorium", 90, builder -> {
        builder.color = HiiragiColor.blendColor(new HashMap<>() {{
            put(HiiragiColor.BLACK, 2);
            put(HiiragiColor.DARK_GREEN, 1);
            put(HiiragiColor.GRAY, 1);
        }}).getRGB();
        builder.formula = "Th";
        builder.molar = 232.0;
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 5061;
        builder.tempMelt = 2115;
    });

    public static final HiiragiMaterial URANIUM = HiiragiMaterial.create("uranium", 92, builder -> {
        builder.color = HiiragiColor.GREEN.getRGB();
        builder.formula = "U";
        builder.molar = 238.0;
        builder.shapeType = ShapeType.METAL_ADVANCED;
        builder.tempBoil = 4404;
        builder.tempMelt = 1405;
    });

    public static final HiiragiMaterial NEPTUNIUM = HiiragiMaterial.create("neptunium", 93, builder -> {
        builder.color = HiiragiColor.BLUE.getRGB();
        builder.formula = "Np";
        //builder.molar = 237.0;
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 4273;
        builder.tempMelt = 917;
    });

    public static final HiiragiMaterial PLUTONIUM = HiiragiMaterial.create("plutonium", 94, builder -> {
        builder.color = HiiragiColor.RED.getRGB();
        builder.formula = "Pu";
        builder.molar = 244.1;
        builder.shapeType = ShapeType.METAL_COMMON;
        builder.tempBoil = 3501;
        builder.tempMelt = 913;
    });

    //    Actinides End    //

    //    8th Period    //

    //    Isotopes    //

    public static final HiiragiMaterial DEUTERIUM = HiiragiMaterial.createIsotope("deuterium", 120, HYDROGEN, builder -> {
        builder.formula = "D";
        builder.molar = 2.0;
    });

    public static final HiiragiMaterial TRITIUM = HiiragiMaterial.createIsotope("tritium", 121, HYDROGEN, builder -> {
        builder.formula = "T";
        builder.molar = 3.0;
    });

    public static final HiiragiMaterial URANIUM235 = HiiragiMaterial.createIsotope("uranium235", 122, URANIUM, builder -> {
        builder.formula = "U235";
        builder.molar = 235.0;
    });

}