package hiiragi283.material.api.material

object MaterialType {

    @JvmField
    val INTERNAL: Set<String> = setOf("bottle")

    @JvmField
    val WILDCARD: Set<String> = setOf(
        "ball",
        "block",
        "bottle",
        "clump",
        "cluster",
        "coin",
        "crystal",
        "dust",
        "dust_dirty",
        "dust_tiny",
        "gear",
        "gem",
        "ingot",
        "log",
        "nugget",
        "ore",
        "plank",
        "plate",
        "shard",
        "rod",
        "stone"
    )

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
    val GEM_4xADVANCED: Set<String> = SOLID.toMutableSet().also {
        it.add("plate")
        it.add("rod")
    }

    @JvmField
    val GEM_9x: Set<String> = GEM_4x.toMutableSet().also {
        it.add("block")
        it.add("gem")
    }

    @JvmField
    val GEM_9xADVANCED: Set<String> = GEM_9x.toMutableSet().also {
        it.add("plate")
        it.add("rod")
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
                "rod"
            )
        )
    }

}