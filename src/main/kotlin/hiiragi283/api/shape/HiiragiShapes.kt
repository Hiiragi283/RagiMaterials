package hiiragi283.api.shape

import hiiragi283.api.HiiragiRegistry
import java.util.function.Function

object HiiragiShapes {

    @JvmField
    val COMMON: Function<Int, HiiragiShape> = Function { HiiragiShape("common", it) }

    @JvmField
    val BALL = HiiragiShape("ball", 30)

    @JvmField
    val BLOCK = HiiragiShape("block", 144 * 9)

    @JvmField
    val BOTTLE = HiiragiShape("bottle", 144)

    @JvmField
    val CLUMP = HiiragiShape("clump", 144)

    @JvmField
    val CLUSTER = HiiragiShape("cluster", 144 * 2)

    @JvmField
    val COIN = HiiragiShape("coin", 144 / 3)

    @JvmField
    val CRUSHED = HiiragiShape("crushed", 144)

    @JvmField
    val CRYSTAL = HiiragiShape("crystal", 144)

    @JvmField
    val DUST = HiiragiShape("dust", 144)

    @JvmField
    val DUST_DIRTY = HiiragiShape("dust_dirty", 144)

    @JvmField
    val DUST_TINY = HiiragiShape("dust_tiny", 144 / 9)

    @JvmField
    val FENCE = HiiragiShape("fence", 144 * 5 / 3)

    @JvmField
    val GEAR = HiiragiShape("gear", 144 * 4)

    @JvmField
    val GEM = HiiragiShape("gem", 144)

    @JvmField
    val INGOT = HiiragiShape("ingot", 144)

    @JvmField
    val LOG = HiiragiShape("log", 144 * 4)

    @JvmField
    val NUGGET = HiiragiShape("nugget", 144 / 9)

    @JvmField
    val ORE = HiiragiShape("ore", 144 * 2)

    @JvmField
    val ORE_DENSE = HiiragiShape("ore_dense", 144 * 2)

    @JvmField
    val ORE_POOR = HiiragiShape("ore_poor", 144 / 3)

    @JvmField
    val PLANK = HiiragiShape("plank", 144)

    @JvmField
    val PLATE = HiiragiShape("plate", 144)

    @JvmField
    val PLATE_DENSE = HiiragiShape("plate_dense", 144 * 9)

    @JvmField
    val PURIFIED = HiiragiShape("crushed_purified", 144)

    @JvmField
    val SAND = HiiragiShape("sand", 144)

    @JvmField
    val SCAFFOLDING = HiiragiShape("scaffolding", (144 * 4 + 72) / 6)

    @JvmField
    val SHARD = HiiragiShape("shard", 144)

    @JvmField
    val SHEETMETAL = HiiragiShape("block_sheetmetal", 144)

    @JvmField
    val SLAB = HiiragiShape("slab", 144 * 9 / 2)

    @JvmField
    val SLAB_SHEETMETAL = HiiragiShape("slab_sheetmetal", 144 / 2)

    @JvmField
    val STICK = HiiragiShape("stick", 144 / 2)

    @JvmField
    val STONE = HiiragiShape("stone", 144)

    @JvmField
    val WIRE = HiiragiShape("wire", 144 / 2)

    fun register() {
        this::class.java.declaredFields
            .map { it.also { it.isAccessible = true } }
            .map { it.get(this) }
            .filterIsInstance<HiiragiShape>()
            .forEach { HiiragiRegistry.registerShape(it) }
    }

}