package hiiragi283.material.init

import hiiragi283.material.api.shape.HiiragiShape
import hiiragi283.material.util.enableAccess
import java.lang.reflect.Field

object HiiragiShapes {

    //    Material Item    //

    @JvmField
    val BLOCK = HiiragiShape("block", 144 * 9)

    @JvmField
    val INGOT = HiiragiShape("ingot", 144)

    @JvmField
    val NUGGET = HiiragiShape("nugget", 144 / 9)

    @JvmField
    val BOTTLE = HiiragiShape("bottle", INGOT::getScale)

    @JvmField
    val CASING = HiiragiShape("casing") { INGOT.getScale(it) * 8 }

    @JvmField
    val DUST = HiiragiShape("dust", INGOT::getScale)

    @JvmField
    val GEAR = HiiragiShape("gear") { INGOT.getScale(it) * 4 }

    @JvmField
    val GEM = HiiragiShape("gem", INGOT::getScale)
        .addPrefixAlt("fuel")

    @JvmField
    val PLATE = HiiragiShape("plate", INGOT::getScale)

    @JvmField
    val STICK = HiiragiShape("stick") { INGOT.getScale(it) / 2 }
        .addPrefixAlt("rod")

    //    Compat    //

    @JvmField
    val BALL = HiiragiShape("ball") { INGOT.getScale(it) * 5 / 24 }

    @JvmField
    val CLUMP = HiiragiShape("clump", INGOT::getScale)

    @JvmField
    val CLUSTER = HiiragiShape("cluster") { INGOT.getScale(it) * 2 }

    @JvmField
    val COIN = HiiragiShape("coin") { INGOT.getScale(it) / 3 }

    @JvmField
    val CRUSHED = HiiragiShape("crushed", INGOT::getScale)

    @JvmField
    val CRYSTAL = HiiragiShape("crystal", INGOT::getScale)

    @JvmField
    val DUST_DIRTY = HiiragiShape("dust_dirty", INGOT::getScale)

    @JvmField
    val DUST_TINY = HiiragiShape("dust_tiny", NUGGET::getScale)

    @JvmField
    val FENCE = HiiragiShape("fence") { INGOT.getScale(it) * 5 / 3 }

    @JvmField
    val FRAME = HiiragiShape("frame") { INGOT.getScale(it) * 4 }

    @JvmField
    val LOG = HiiragiShape("log") { INGOT.getScale(it) * 4 }

    @JvmField
    val ORE = HiiragiShape("ore") { INGOT.getScale(it) * 2 }

    @JvmField
    val ORE_DENSE = HiiragiShape("ore_dense") { INGOT.getScale(it) * 2 }

    @JvmField
    val ORE_POOR = HiiragiShape("ore_poor") { INGOT.getScale(it) / 3 }

    @JvmField
    val PANE = HiiragiShape("pane", 375)

    @JvmField
    val PLANK = HiiragiShape("plank", INGOT::getScale)

    @JvmField
    val PLATE_DENSE = HiiragiShape("plate_dense", BLOCK::getScale)

    @JvmField
    val PURIFIED = HiiragiShape("crushed_purified", INGOT::getScale)

    @JvmField
    val SAND = HiiragiShape("sand", INGOT::getScale)

    @JvmField
    val SCAFFOLDING = HiiragiShape("scaffolding") { (INGOT.getScale(it) * 4 + INGOT.getScale(it) / 2) / 6 }

    @JvmField
    val SHARD = HiiragiShape("shard", INGOT::getScale)

    @JvmField
    val SHEETMETAL = HiiragiShape("block_sheetmetal", INGOT::getScale)

    @JvmField
    val SLAB = HiiragiShape("slab") { BLOCK.getScale(it) / 2 }

    @JvmField
    val SLAB_SHEETMETAL = HiiragiShape("slab_sheetmetal") { INGOT.getScale(it) / 2 }

    @JvmField
    val STONE = HiiragiShape("stone", INGOT::getScale)

    @JvmField
    val WIRE = HiiragiShape("wire") { INGOT.getScale(it) / 2 }

    //    State    //

    @JvmField
    val SOLID = HiiragiShape("solid", 0)

    @JvmField
    val LIQUID = HiiragiShape("liquid", 0)

    @JvmField
    val GAS = HiiragiShape("gas", 0)

    //    Type    //

    @JvmField
    val IS_GEM = HiiragiShape("is_gem", 0)

    @JvmField
    val IS_METAL = HiiragiShape("is_metal", 0)

    fun register() {
        this::class.java.declaredFields
            .map(Field::enableAccess)
            .map { it.get(this) }
            .filterIsInstance<HiiragiShape>()
            .forEach(HiiragiShape::register)
    }

}