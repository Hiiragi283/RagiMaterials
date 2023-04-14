package hiiragi283.ragi_materials.api.capability

enum class EnumIOType(val canInput: Boolean, val canOutput: Boolean) {
    INPUT(true, false),
    OUTPUT(false, true),
    GENERAL(true, true),
    CATALYST(false, false)
}