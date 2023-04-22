package ragi_materials.core.capability

enum class EnumIOType(val canInput: Boolean, val canOutput: Boolean) {
    INPUT(true, false),
    OUTPUT(false, true),
    GENERAL(true, true),
    CATALYST(false, false)
}