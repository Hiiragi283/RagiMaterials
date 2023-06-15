package hiiragi283.material.material

enum class CrystalType(val isCrystal: Boolean, val texture: String) {
    AMORPHOUS(false, "amorphous"),
    COAL(true, "coal"),
    CUBIC(true, "cubic"),
    DIAMOND(true, "diamond"),
    EMERALD(true, "emerald"),
    LAPIS(true, "lapis"),
    METAL(true, ""),
    NONE(false, ""),
    QUARTZ(true, "quartz"),
    RUBY(true, "ruby")
}