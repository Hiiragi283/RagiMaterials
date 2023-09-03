package hiiragi283.material.api.shape;

import hiiragi283.material.api.registry.HiiragiRegistryEntries;

public class HiiragiShapes extends HiiragiRegistryEntries {

    public static final HiiragiShape BALL = new HiiragiShape("ball", 30);
    public static final HiiragiShape BLOCK = new HiiragiShape("block", 144 * 9);
    public static final HiiragiShape BOTTLE = new HiiragiShape("bottle", 144);
    public static final HiiragiShape CASING = new HiiragiShape("casing", (144 * 4) + (144 * 6));
    public static final HiiragiShape CLUMP = new HiiragiShape("clump", 144);
    public static final HiiragiShape CLUSTER = new HiiragiShape("cluster", 144 * 2);
    public static final HiiragiShape COIN = new HiiragiShape("coin", 144 / 3);
    public static final HiiragiShape CRUSHED = new HiiragiShape("crushed", 144);
    public static final HiiragiShape CRYSTAL = new HiiragiShape("crystal", 144);
    public static final HiiragiShape DUST = new HiiragiShape("dust", 144);
    public static final HiiragiShape DUST_DIRTY = new HiiragiShape("dust_dirty", 144);
    public static final HiiragiShape DUST_TINY = new HiiragiShape("dust_tiny", 144 / 9);
    public static final HiiragiShape FENCE = new HiiragiShape("fence", 144 * 5 / 3);
    public static final HiiragiShape FRAME = new HiiragiShape("frame", 144 * 4);
    public static final HiiragiShape GEAR = new HiiragiShape("gear", 144 * 4);
    public static final HiiragiShape GEM = new HiiragiShape("gem", 144);
    public static final HiiragiShape INGOT = new HiiragiShape("ingot", 144);
    public static final HiiragiShape LOG = new HiiragiShape("log", 144 * 4);
    public static final HiiragiShape NUGGET = new HiiragiShape("nugget", 144 / 9);
    public static final HiiragiShape ORE = new HiiragiShape("ore", 144 * 2);
    public static final HiiragiShape ORE_DENSE = new HiiragiShape("ore_dense", 144 * 2);
    public static final HiiragiShape ORE_POOR = new HiiragiShape("ore_poor", 144 / 3);
    public static final HiiragiShape PLANK = new HiiragiShape("plank", 144);
    public static final HiiragiShape PLATE = new HiiragiShape("plate", 144);
    public static final HiiragiShape PLATE_DENSE = new HiiragiShape("plate_dense", 144 * 9);
    public static final HiiragiShape PURIFIED = new HiiragiShape("crushed_purified", 144);
    public static final HiiragiShape SAND = new HiiragiShape("sand", 144);
    public static final HiiragiShape SCAFFOLDING = new HiiragiShape("scaffolding", (144 * 4 + 72) / 6);
    public static final HiiragiShape SHARD = new HiiragiShape("shard", 144);
    public static final HiiragiShape SHEETMETAL = new HiiragiShape("block_sheetmetal", 144);
    public static final HiiragiShape SLAB = new HiiragiShape("slab", 144 * 9 / 2);
    public static final HiiragiShape SLAB_SHEETMETAL = new HiiragiShape("slab_sheetmetal", 144 / 2);
    public static final HiiragiShape STICK = new HiiragiShape("stick", 144 / 2);
    public static final HiiragiShape STONE = new HiiragiShape("stone", 144);
    public static final HiiragiShape WIRE = new HiiragiShape("wire", 144 / 2);

    //    State    //

    public static final HiiragiShape SOLID = new HiiragiShape("solid", 0);

    public static final HiiragiShape LIQUID = new HiiragiShape("liquid", 0);

    public static final HiiragiShape GAS = new HiiragiShape("gas", 0);

    //    Type    //

    public static final HiiragiShape METAL = new HiiragiShape("metal", 0);

}