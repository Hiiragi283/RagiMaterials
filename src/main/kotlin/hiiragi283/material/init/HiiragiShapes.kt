package hiiragi283.material.init

import hiiragi283.material.api.shape.HiiragiShape
import java.util.function.Function

object HiiragiShapes {

    fun init() {}

    //    Material Item    //

    @JvmField
    val BLOCK = HiiragiShape.build("block") {
        scaleFunction = Function(HiiragiShape.Companion::getBlockScale)
    }

    @JvmField
    val INGOT = HiiragiShape.build("ingot") {
        scaleFunction = Function(HiiragiShape.Companion::getIngotScale)
    }

    @JvmField
    val NUGGET = HiiragiShape.build("nugget") {
        scaleFunction = Function { INGOT.getScale(it) / 9 }
    }

    @JvmField
    val BOTTLE = HiiragiShape.build("bottle") {
        scaleFunction = Function(INGOT::getScale)
    }

    @JvmField
    val CASING = HiiragiShape.build("casing") {
        scaleFunction = Function { INGOT.getScale(it) * 8 }
    }

    @JvmField
    val DUST = HiiragiShape.build("dust") {
        scaleFunction = Function(INGOT::getScale)
    }

    @JvmField
    val GEAR = HiiragiShape.build("gear") {
        scaleFunction = Function { INGOT.getScale(it) * 4 }
    }

    @JvmField
    val GEM = HiiragiShape.build("gem") {
        scaleFunction = Function(INGOT::getScale)
    }.addAlternativeName("fuel")

    @JvmField
    val PLATE = HiiragiShape.build("plate") {
        scaleFunction = Function(INGOT::getScale)
    }

    @JvmField
    val STICK = HiiragiShape.build("stick") {
        scaleFunction = Function { INGOT.getScale(it) / 2 }
    }.addAlternativeName("rod")

    //    Compat    //

    @JvmField
    val BALL = HiiragiShape.build("ball") {
        scaleFunction = Function { INGOT.getScale(it) * 5 / 24 }
    }

    @JvmField
    val CLUMP = HiiragiShape.build("clump") {
        scaleFunction = Function(INGOT::getScale)
    }

    @JvmField
    val CLUSTER = HiiragiShape.build("cluster") {
        scaleFunction = Function { INGOT.getScale(it) * 2 }
    }

    @JvmField
    val COIN = HiiragiShape.build("coin") {
        scaleFunction = Function { INGOT.getScale(it) / 3 }
    }

    @JvmField
    val CRUSHED = HiiragiShape.build("crushed") {
        scaleFunction = Function(INGOT::getScale)
    }

    @JvmField
    val CRYSTAL = HiiragiShape.build("crystal") {
        scaleFunction = Function(INGOT::getScale)
    }

    @JvmField
    val DUST_DIRTY = HiiragiShape.build("dust_dirty") {
        scaleFunction = Function(INGOT::getScale)
    }

    @JvmField
    val DUST_TINY = HiiragiShape.build("dust_tiny") {
        scaleFunction = Function(NUGGET::getScale)
    }

    @JvmField
    val FENCE = HiiragiShape.build("fence") {
        scaleFunction = Function { INGOT.getScale(it) * 5 / 3 }
    }

    @JvmField
    val FRAME = HiiragiShape.build("frame") {
        scaleFunction = Function { INGOT.getScale(it) * 4 }
    }

    @JvmField
    val LOG = HiiragiShape.build("log") {
        scaleFunction = Function { INGOT.getScale(it) * 4 }
    }

    @JvmField
    val ORE = HiiragiShape.build("ore") {
        scaleFunction = Function { INGOT.getScale(it) * 2 }
    }

    @JvmField
    val ORE_DENSE = HiiragiShape.build("ore_dense") {
        scaleFunction = Function { INGOT.getScale(it) * 2 }
    }

    @JvmField
    val ORE_POOR = HiiragiShape.build("ore_poor") {
        scaleFunction = Function { INGOT.getScale(it) / 3 }
    }

    @JvmField
    val PANE = HiiragiShape.build("pane") {
        scaleFunction = Function { 375 }
    }

    @JvmField
    val PLANK = HiiragiShape.build("plank") {
        scaleFunction = Function(INGOT::getScale)
    }

    @JvmField
    val PLATE_DENSE = HiiragiShape.build("plate_dense") {
        scaleFunction = Function { INGOT.getScale(it) * 9 }
    }

    @JvmField
    val PURIFIED = HiiragiShape.build("crushed_purified") {
        scaleFunction = Function(INGOT::getScale)
    }

    @JvmField
    val SAND = HiiragiShape.build("sand") {
        scaleFunction = Function(INGOT::getScale)
    }

    @JvmField
    val SCAFFOLDING = HiiragiShape.build("scaffolding") {
        scaleFunction = Function { (INGOT.getScale(it) * 4 + INGOT.getScale(it) / 2) / 6 }
    }

    @JvmField
    val SHARD = HiiragiShape.build("shard") {
        scaleFunction = Function(INGOT::getScale)
    }

    @JvmField
    val SHEETMETAL = HiiragiShape.build("block_sheetmetal") {
        scaleFunction = Function(INGOT::getScale)
    }

    @JvmField
    val SLAB = HiiragiShape.build("slab") {
        scaleFunction = Function { BLOCK.getScale(it) / 2 }
    }

    @JvmField
    val SLAB_SHEETMETAL = HiiragiShape.build("slab_sheetmetal") {
        scaleFunction = Function { INGOT.getScale(it) / 2 }
    }

    @JvmField
    val STONE = HiiragiShape.build("stone") {
        scaleFunction = Function(INGOT::getScale)
    }

    @JvmField
    val WIRE = HiiragiShape.build("wire") {
        scaleFunction = Function { INGOT.getScale(it) / 2 }
    }

    //    State    //

    @JvmField
    val SOLID = HiiragiShape.build("solid")

    @JvmField
    val LIQUID = HiiragiShape.build("liquid")

    @JvmField
    val GAS = HiiragiShape.build("gas")

    //    Type    //

    @JvmField
    val IS_GEM = HiiragiShape.build("is_gem")

    @JvmField
    val IS_METAL = HiiragiShape.build("is_metal")

}