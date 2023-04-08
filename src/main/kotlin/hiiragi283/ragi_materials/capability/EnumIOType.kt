package hiiragi283.ragi_materials.capability

enum class EnumIOType(val canInput: Boolean, val canOutput: Boolean) {
    INPUT(true, false),
    OUTPUT(false, true),
    GENERAL(true, true)
}