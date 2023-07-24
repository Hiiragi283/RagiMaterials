package hiiragi283.material.api.material

object MaterialType {

    @JvmField
    val INTERNAL: Set<String> = setOf("bottle", "wildcard")

    @JvmField
    val SOLID: Set<String> = setOf(
        "dust",
        "dust_tiny",
        "ore_deep",
        "ore_end",
        "ore_nether",
        "ore_stone"
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
        it.add("block_gem")
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
                "block_metal",
                "ingot",
                "nugget"
            )
        )
    }

    @JvmField
    val METAL_ADVANCED: Set<String> = METAL_COMMON.toMutableSet().also {
        it.addAll(
            setOf(
                "gear",
                "plate",
                "rod"
            )
        )
    }

}