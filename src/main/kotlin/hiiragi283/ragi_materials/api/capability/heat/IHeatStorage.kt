package hiiragi283.ragi_materials.api.capability.heat

interface IHeatStorage {

    fun receiveHeat(maxReceive: Int, simulate: Boolean): Int

    fun extractHeat(maxExtract: Int, simulate: Boolean): Int

    fun getHeatStored(): Int

    fun getMaxHeatStored(): Int

    fun canExtract(): Boolean

    fun canReceive(): Boolean

    fun onOverheated()

}