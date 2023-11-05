package hiiragi283.material.init

import hiiragi283.material.api.material.MaterialScaleFunction
import hiiragi283.material.api.shape.HiiragiShape

object HiiragiShapes {

    fun init() {}

    //    Material Item    //

    @JvmField
    val BLOCK = HiiragiShape.build("block") {
        scaleFunction = MaterialScaleFunction(HiiragiShape.Companion::getBlockScale)
    }

    @JvmField
    val INGOT = HiiragiShape.build("ingot") {
        scaleFunction = MaterialScaleFunction(HiiragiShape.Companion::getIngotScale)
    }

    @JvmField
    val NUGGET = HiiragiShape.build("nugget") {
        scaleFunction = MaterialScaleFunction { INGOT.getScale(it) / 9 }
    }

    @JvmField
    val BOTTLE = HiiragiShape.build("bottle") {
        scaleFunction = MaterialScaleFunction(INGOT::getScale)
    }

    @JvmField
    val CASING = HiiragiShape.build("casing") {
        scaleFunction = MaterialScaleFunction { INGOT.getScale(it) * 8 }
    }

    @JvmField
    val DUST = HiiragiShape.build("dust") {
        scaleFunction = MaterialScaleFunction(INGOT::getScale)
    }

    @JvmField
    val GEAR = HiiragiShape.build("gear") {
        scaleFunction = MaterialScaleFunction { INGOT.getScale(it) * 4 }
    }

    @JvmField
    val GEM = HiiragiShape.build("gem") {
        scaleFunction = MaterialScaleFunction(INGOT::getScale)
    }.addAlternativeName("fuel")

    @JvmField
    val PLATE = HiiragiShape.build("plate") {
        scaleFunction = MaterialScaleFunction(INGOT::getScale)
    }

    @JvmField
    val STICK = HiiragiShape.build("stick") {
        scaleFunction = MaterialScaleFunction { INGOT.getScale(it) / 2 }
    }.addAlternativeName("rod")

    //    Compat    //

    @JvmField
    val BALL = HiiragiShape.build("ball") {
        scaleFunction = MaterialScaleFunction { INGOT.getScale(it) * 5 / 24 }
    }

    @JvmField
    val CLUMP = HiiragiShape.build("clump") {
        scaleFunction = MaterialScaleFunction(INGOT::getScale)
    }

    @JvmField
    val CLUSTER = HiiragiShape.build("cluster") {
        scaleFunction = MaterialScaleFunction { INGOT.getScale(it) * 2 }
    }

    @JvmField
    val COIN = HiiragiShape.build("coin") {
        scaleFunction = MaterialScaleFunction { INGOT.getScale(it) / 3 }
    }

    @JvmField
    val CRUSHED = HiiragiShape.build("crushed") {
        scaleFunction = MaterialScaleFunction(INGOT::getScale)
    }

    @JvmField
    val CRYSTAL = HiiragiShape.build("crystal") {
        scaleFunction = MaterialScaleFunction(INGOT::getScale)
    }

    @JvmField
    val DUST_DIRTY = HiiragiShape.build("dust_dirty") {
        scaleFunction = MaterialScaleFunction(INGOT::getScale)
    }

    @JvmField
    val DUST_TINY = HiiragiShape.build("dust_tiny") {
        scaleFunction = MaterialScaleFunction(NUGGET::getScale)
    }

    @JvmField
    val FENCE = HiiragiShape.build("fence") {
        scaleFunction = MaterialScaleFunction { INGOT.getScale(it) * 5 / 3 }
    }

    @JvmField
    val FRAME = HiiragiShape.build("frame") {
        scaleFunction = MaterialScaleFunction { INGOT.getScale(it) * 4 }
    }

    @JvmField
    val LOG = HiiragiShape.build("log") {
        scaleFunction = MaterialScaleFunction { INGOT.getScale(it) * 4 }
    }

    @JvmField
    val ORE = HiiragiShape.build("ore") {
        scaleFunction = MaterialScaleFunction { INGOT.getScale(it) * 2 }
    }

    @JvmField
    val ORE_DENSE = HiiragiShape.build("ore_dense") {
        scaleFunction = MaterialScaleFunction { INGOT.getScale(it) * 2 }
    }

    @JvmField
    val ORE_POOR = HiiragiShape.build("ore_poor") {
        scaleFunction = MaterialScaleFunction { INGOT.getScale(it) / 3 }
    }

    @JvmField
    val PANE = HiiragiShape.build("pane") {
        scaleFunction = MaterialScaleFunction { 375 }
    }

    @JvmField
    val PLANK = HiiragiShape.build("plank") {
        scaleFunction = MaterialScaleFunction(INGOT::getScale)
    }

    @JvmField
    val PLATE_DENSE = HiiragiShape.build("plate_dense") {
        scaleFunction = MaterialScaleFunction { INGOT.getScale(it) * 9 }
    }

    @JvmField
    val PURIFIED = HiiragiShape.build("crushed_purified") {
        scaleFunction = MaterialScaleFunction(INGOT::getScale)
    }

    @JvmField
    val SAND = HiiragiShape.build("sand") {
        scaleFunction = MaterialScaleFunction(INGOT::getScale)
    }

    @JvmField
    val SCAFFOLDING = HiiragiShape.build("scaffolding") {
        scaleFunction = MaterialScaleFunction { (INGOT.getScale(it) * 4 + INGOT.getScale(it) / 2) / 6 }
    }

    @JvmField
    val SHARD = HiiragiShape.build("shard") {
        scaleFunction = MaterialScaleFunction(INGOT::getScale)
    }

    @JvmField
    val SHEETMETAL = HiiragiShape.build("block_sheetmetal") {
        scaleFunction = MaterialScaleFunction(INGOT::getScale)
    }

    @JvmField
    val SLAB = HiiragiShape.build("slab") {
        scaleFunction = MaterialScaleFunction { BLOCK.getScale(it) / 2 }
    }

    @JvmField
    val SLAB_SHEETMETAL = HiiragiShape.build("slab_sheetmetal") {
        scaleFunction = MaterialScaleFunction { INGOT.getScale(it) / 2 }
    }

    @JvmField
    val STONE = HiiragiShape.build("stone") {
        scaleFunction = MaterialScaleFunction(INGOT::getScale)
    }

    @JvmField
    val WIRE = HiiragiShape.build("wire") {
        scaleFunction = MaterialScaleFunction { INGOT.getScale(it) / 2 }
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