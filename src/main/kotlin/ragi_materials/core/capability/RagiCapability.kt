package ragi_materials.core.capability

import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityInject
import net.minecraftforge.common.capabilities.CapabilityManager
import ragi_materials.core.RagiMaterials
import ragi_materials.core.capability.heat.HeatStorage
import ragi_materials.core.capability.heat.IHeatStorage

object RagiCapability {

    @CapabilityInject(IHeatStorage::class)
    lateinit var HEAT: Capability<IHeatStorage>

    fun register() {

        CapabilityManager.INSTANCE.register(IHeatStorage::class.java, RagiStorage<IHeatStorage>()) { HeatStorage(1000) }

        /*CapabilityManager.INSTANCE.register(IHeatStorage::class.java, object : IStorage<IHeatStorage> {

            override fun writeNBT(capability: Capability<IHeatStorage>, instance: IHeatStorage, side: EnumFacing) = NBTTagInt(instance.getHeatStored())

            override fun readNBT(capability: Capability<IHeatStorage>, instance: IHeatStorage, side: EnumFacing, nbt: NBTBase) {
                require(instance is HeatStorage) { "Can not deserialize to an instance that isn't the default implementation" }
                instance.stored = (nbt as NBTTagInt).int
            }
        }
        ) { HeatStorage(1000) }*/

        RagiMaterials.LOGGER.debug("The new capability HEAT is registered!")
    }
}