package hiiragi283.material.api.material

enum class MaterialType(
    val isGem: Boolean,
    val isMetal: Boolean,
    val hasGear: Boolean,
    val hasPlate: Boolean,
    val hasRod: Boolean,
) {
    ACTINOID(false, true, false, false, false),
    ALKALI_METAL(false, true, false, false, false),
    ALKALINE_METAL(false, true, false, false, false),
    FUEL(true, false, false, false, false),
    GEM(true, false, true, true, true),
    INTERNAL(false, false, false, false, false),
    METAL(false, true, true, true, true),
    METALLOID(false, true, false, true, true),
    OTHER(false, false, false, false, false),
    RARE_EARTH(false, true, false, false, false),
    STONE(false, false, true, true, true),
    TRANS_URANIUM(false, false, false, false, false)
}