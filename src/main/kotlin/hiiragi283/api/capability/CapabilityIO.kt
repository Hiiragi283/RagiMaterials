package hiiragi283.api.capability

interface CapabilityIO<T : Any> {

    var ioType: IOType

    fun getIOType(): IOType

    fun setIOType(type: IOType): T

}