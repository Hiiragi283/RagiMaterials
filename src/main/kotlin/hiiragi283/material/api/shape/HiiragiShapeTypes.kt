package hiiragi283.material.api.shape

object HiiragiShapeTypes {

    @JvmField
    val INTERNAL: HiiragiShapeType = HiiragiShapeType("internal", listOf(HiiragiShapes.BOTTLE))

    @JvmField
    val WILDCARD: HiiragiShapeType = HiiragiShapeType("wildcard")

    @JvmField
    val SOLID: HiiragiShapeType = INTERNAL.child(
        "solid", listOf(
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

    @JvmField
    val GAS: HiiragiShapeType = INTERNAL.child("gas", listOf(HiiragiShapes.GAS))

    @JvmField
    val GEM_4x: HiiragiShapeType = SOLID.child("gem_4x", listOf(HiiragiShapes.GEM))

    @JvmField
    val GEM_4x_ADVANCED: HiiragiShapeType = SOLID.child(
        "gem_4x", listOf(
            HiiragiShapes.PLATE,
            HiiragiShapes.PLATE_DENSE,
            HiiragiShapes.STICK
        )
    )

    @JvmField
    val GEM_9x: HiiragiShapeType = GEM_4x.child("gem_9x", listOf(HiiragiShapes.BLOCK, HiiragiShapes.SLAB))

    @JvmField
    val GEM_9x_ADVANCED: HiiragiShapeType = GEM_9x.child(
        "gem_4x_advanced", listOf(
            HiiragiShapes.PLATE,
            HiiragiShapes.PLATE_DENSE,
            HiiragiShapes.STICK
        )
    )

    @JvmField
    val GEM_AMORPHOUS: HiiragiShapeType = GEM_9x.child("amorphous")
    @JvmField
    val GEM_COAL: HiiragiShapeType = GEM_9x.child("coal")
    @JvmField
    val GEM_CUBIC: HiiragiShapeType = GEM_9x.child("cubic")
    @JvmField
    val GEM_DIAMOND: HiiragiShapeType = GEM_9x_ADVANCED.child("diamond")
    @JvmField
    val GEM_EMERALD: HiiragiShapeType = GEM_9x_ADVANCED.child("emerald")
    @JvmField
    val GEM_LAPIS: HiiragiShapeType = GEM_9x_ADVANCED.child("lapis")
    @JvmField
    val GEM_QUARTZ: HiiragiShapeType = GEM_4x_ADVANCED.child("quartz")
    @JvmField
    val GEM_RUBY: HiiragiShapeType = GEM_9x_ADVANCED.child("ruby")

    @JvmField
    val LIQUID: HiiragiShapeType = INTERNAL.child("liquid", listOf(HiiragiShapes.LIQUID))

    @JvmField
    val METAL_COMMON: HiiragiShapeType = SOLID.child(
        "metal_common", listOf(
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

    @JvmField
    val METAL_ADVANCED: HiiragiShapeType = METAL_COMMON.child(
        "metal_advanced", listOf(
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

    @JvmField
    val SAND: HiiragiShapeType = SOLID.child("sand", listOf(HiiragiShapes.SAND))

    @JvmField
    val STONE: HiiragiShapeType = SOLID.child(
        "stone", listOf(
            HiiragiShapes.GEAR,
            HiiragiShapes.PLATE,
            HiiragiShapes.STICK,
            HiiragiShapes.STONE
        )
    )

    @JvmField
    val WOOD: HiiragiShapeType = SOLID.child(
        "wood", listOf(
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