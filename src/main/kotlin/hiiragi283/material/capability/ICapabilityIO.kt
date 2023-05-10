package hiiragi283.material.capability

interface ICapabilityIO<T : Any> {

    var ioType: EnumIOType

    fun getIOType(): EnumIOType

    fun setIOType(type: EnumIOType): T

}