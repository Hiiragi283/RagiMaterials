package hiiragi283.api.material

import hiiragi283.api.HiiragiRegistry

object MaterialType {

    @JvmField
    val INTERNAL: Set<String> = setOf("bottle")

    val WILDCARD: Set<String> by lazy { HiiragiRegistry.SHAPE.map { it.value.name }.toSet() }

    @JvmField
    val SOLID: Set<String> = setOf(
        "cluster",
        "dust",
        "dust_tiny",
        "ore"
    )

    @JvmField
    val GEM_4x: Set<String> = SOLID.toMutableSet().also {
        it.add("gem")
    }

    @JvmField
    val GEM_4xADVANCED: Set<String> = GEM_4x.toMutableSet().also {
        it.add("plate")
        it.add("stick")
    }

    @JvmField
    val GEM_9x: Set<String> = GEM_4x.toMutableSet().also {
        it.add("block")
        it.add("gem")
    }

    @JvmField
    val GEM_9xADVANCED: Set<String> = GEM_9x.toMutableSet().also {
        it.add("plate")
        it.add("stick")
    }

    @JvmField
    val METAL_COMMON: Set<String> = SOLID.toMutableSet().also {
        it.addAll(
            setOf(
                "block",
                "clump",
                "cluster",
                "crystal",
                "dust_dirty",
                "ingot",
                "nugget",
                "shard"
            )
        )
    }

    @JvmField
    val METAL_ADVANCED: Set<String> = METAL_COMMON.toMutableSet().also {
        it.addAll(
            setOf(
                "ball",
                "coin",
                "gear",
                "plate",
                "stick"
            )
        )
    }

    @JvmField
    val WOOD: Set<String> = SOLID.toMutableSet().also {
        it.addAll(
            setOf(
                "gear",
                "log",
                "plank",
                "plate"
            )
        )
    }

}