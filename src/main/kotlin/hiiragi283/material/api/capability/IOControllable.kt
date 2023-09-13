package hiiragi283.material.api.capability

interface IOControllable {

    val ioType: Type

    enum class Type(val canInsert: Boolean, val canExtract: Boolean) {
        INPUT(true, false),
        OUTPUT(false, true),
        GENERAL(true, true),
        CATALYST(false, false)
    }

}