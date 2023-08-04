package hiiragi283.api.capability

enum class IOType(val canInput: Boolean, val canOutput: Boolean) {
    INPUT(true, false),
    OUTPUT(false, true),
    GENERAL(true, true),
    CATALYST(false, false)
}