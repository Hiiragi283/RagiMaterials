package hiiragi283.material.init

import hiiragi283.material.api.shape.HiiragiShapeType

object HiiragiShapeTypes {

    @JvmField
    val INTERNAL: HiiragiShapeType = HiiragiShapeType.build { shapes.add(HiiragiShapes.BOTTLE) }

    @JvmField
    val WILDCARD: HiiragiShapeType = HiiragiShapeType.build()

    @JvmField
    val SOLID: HiiragiShapeType = INTERNAL.copy {
        shapes.addAll(
            listOf(
                HiiragiShapes.CLUSTER,
                HiiragiShapes.CRUSHED,
                HiiragiShapes.DUST,
                HiiragiShapes.DUST_TINY,
                HiiragiShapes.ORE,
                HiiragiShapes.ORE_DENSE,
                HiiragiShapes.ORE_POOR,
                HiiragiShapes.PURIFIED,
                HiiragiShapes.SOLID
            )
        )
    }

    @JvmField
    val GAS: HiiragiShapeType = INTERNAL.copy { shapes.add(HiiragiShapes.GAS) }

    @JvmField
    val GEM_4x: HiiragiShapeType = SOLID.copy { shapes.add(HiiragiShapes.GEM) }

    @JvmField
    val GEM_4x_ADVANCED: HiiragiShapeType = SOLID.copy {
        shapes.addAll(
            listOf(
                HiiragiShapes.PLATE,
                HiiragiShapes.PLATE_DENSE,
                HiiragiShapes.STICK
            )
        )
    }

    @JvmField
    val GEM_9x: HiiragiShapeType = GEM_4x.copy {
        shapes.add(HiiragiShapes.BLOCK)
        shapes.add(HiiragiShapes.SLAB)
    }

    @JvmField
    val GEM_9x_ADVANCED: HiiragiShapeType = GEM_9x.copy {
        shapes.addAll(
            listOf(
                HiiragiShapes.PLATE,
                HiiragiShapes.PLATE_DENSE,
                HiiragiShapes.STICK
            )
        )
    }

    @JvmField
    val LIQUID: HiiragiShapeType = INTERNAL.copy { shapes.add(HiiragiShapes.LIQUID) }

    @JvmField
    val METAL_COMMON: HiiragiShapeType = SOLID.copy {
        shapes.addAll(
            listOf(
                HiiragiShapes.BLOCK,
                HiiragiShapes.CLUMP,
                HiiragiShapes.CLUSTER,
                HiiragiShapes.CRYSTAL,
                HiiragiShapes.DUST_DIRTY,
                HiiragiShapes.INGOT,
                HiiragiShapes.METAL,
                HiiragiShapes.NUGGET,
                HiiragiShapes.SHARD,
                HiiragiShapes.SLAB
            )
        )
    }

    @JvmField
    val METAL_ADVANCED: HiiragiShapeType = METAL_COMMON.copy {
        shapes.addAll(
            listOf(
                HiiragiShapes.BALL,
                HiiragiShapes.CASING,
                HiiragiShapes.COIN,
                HiiragiShapes.FENCE,
                HiiragiShapes.FRAME,
                HiiragiShapes.GEAR,
                HiiragiShapes.PLATE,
                HiiragiShapes.SCAFFOLDING,
                HiiragiShapes.SHEETMETAL,
                HiiragiShapes.SLAB_SHEETMETAL,
                HiiragiShapes.STICK,
                HiiragiShapes.WIRE
            )
        )
    }

    @JvmField
    val SAND: HiiragiShapeType = SOLID.copy { shapes.add(HiiragiShapes.SAND) }

    @JvmField
    val STONE: HiiragiShapeType = SOLID.copy {
        shapes.addAll(
            listOf(
                HiiragiShapes.CASING,
                HiiragiShapes.GEAR,
                HiiragiShapes.PLATE,
                HiiragiShapes.STICK,
                HiiragiShapes.STONE
            )
        )
    }

    @JvmField
    val WOOD: HiiragiShapeType = SOLID.copy {
        shapes.addAll(
            listOf(
                HiiragiShapes.CASING,
                HiiragiShapes.FENCE,
                HiiragiShapes.FRAME,
                HiiragiShapes.GEAR,
                HiiragiShapes.LOG,
                HiiragiShapes.PLANK,
                HiiragiShapes.PLATE,
                HiiragiShapes.SCAFFOLDING,
                HiiragiShapes.STICK
            )
        )
    }

}