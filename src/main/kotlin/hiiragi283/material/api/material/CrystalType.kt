package hiiragi283.material.api.material

enum class CrystalType(val isCrystal: Boolean, val texture: String) {
    AMORPHOUS(true, "amorphous"),
    COAL(true, "coal"),
    CUBIC(true, "cubic"),
    DIAMOND(true, "diamond"),
    EMERALD(true, "emerald"),
    LAPIS(true, "lapis"),
    METAL(false, ""),
    NONE(false, ""),
    QUARTZ(true, "quartz"),
    RUBY(true, "ruby")
}