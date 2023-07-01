package hiiragi283.material.api.material

enum class MaterialType(
    val isMetal: Boolean,
    val hasGear: Boolean,
    val hasPlate: Boolean,
    val hasRod: Boolean,
) {
    ACTINOID(true, false, false, false),
    ALKALI_METAL(true, false, false, false),
    ALKALINE_METAL(true, false, false, false),
    INTERNAL(false, false, false, false),
    METAL(true, true, true, true),
    METALLOID(true, false, true, true),
    OTHER(false, false, false, false),
    RARE_EARTH(true, false, false, false),
    TRANS_URANIUM(false, false, false, false)
}