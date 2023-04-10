package hiiragi283.ragi_materials.capability.heat

class HeatStorage(private val capacity: Int, private val maxIn: Int = capacity, private val maxOut: Int = capacity, var stored: Int = 0) : IHeatStorage {

    override fun receiveHeat(maxReceive: Int, simulate: Boolean): Int {
        if (!canReceive()) return 0
        val heatReceived = (capacity - stored).coerceAtMost(maxIn.coerceAtMost(maxReceive))
        if (!simulate) stored += heatReceived
        return heatReceived
    }

    override fun extractHeat(maxExtract: Int, simulate: Boolean): Int {
        if (!canExtract()) return 0
        val heatExtracted = stored.coerceAtMost(maxOut.coerceAtMost(maxExtract))
        if (!simulate) stored -= heatExtracted
        return heatExtracted
    }

    override fun getHeatStored() = stored

    override fun getMaxHeatStored() = capacity

    override fun canExtract() = maxOut > 0 && stored in 1..capacity

    override fun canReceive() = maxIn > 0 && stored in 0 until capacity

    override fun onOverheated() {}

}