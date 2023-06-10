package hiiragi283.material.material_old.type

enum class EnumCrystalType(val texture: String) {
    COAL("coal"),
    CUBIC("cubic"),
    DIAMOND("diamond"),
    EMERALD("emerald"),
    LAPIS("lapis"),
    NONE(""),
    QUARTZ("quartz"),
    RUBY("ruby");

    companion object {
        fun getType(name: String): EnumCrystalType {
            var result = NONE
            for (type in EnumCrystalType.values()) {
                if (name == type.texture) {
                    result = type
                    break
                }
            }
            return result
        }
    }
}