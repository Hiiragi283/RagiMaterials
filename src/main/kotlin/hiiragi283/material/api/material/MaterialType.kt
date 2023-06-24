package hiiragi283.material.api.material

enum class MaterialType(val isMetal: Boolean) {
    ACTINOID(true),
    ALKALI_METAL(true),
    ALKALINE_METAL(true),
    INTERNAL(false),
    METAL(true),
    METALLOID(true),
    OTHER(false),
    RARE_EARTH(true)
}