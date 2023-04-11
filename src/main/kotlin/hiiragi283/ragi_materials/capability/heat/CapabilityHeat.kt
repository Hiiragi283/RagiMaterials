package hiiragi283.ragi_materials.capability.heat

import hiiragi283.ragi_materials.util.RagiLogger
import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagInt
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.Capability.IStorage
import net.minecraftforge.common.capabilities.CapabilityInject
import net.minecraftforge.common.capabilities.CapabilityManager

object CapabilityHeat {

    @CapabilityInject(IHeatStorage::class)
    lateinit var HEAT: Capability<IHeatStorage>

    fun register() {

        CapabilityManager.INSTANCE.register(IHeatStorage::class.java, object : IStorage<IHeatStorage> {

            override fun writeNBT(capability: Capability<IHeatStorage>, instance: IHeatStorage, side: EnumFacing) = NBTTagInt(instance.getHeatStored())

            override fun readNBT(capability: Capability<IHeatStorage>, instance: IHeatStorage, side: EnumFacing, nbt: NBTBase) {
                require(instance is HeatStorage) { "Can not deserialize to an instance that isn't the default implementation" }
                instance.stored = (nbt as NBTTagInt).int
            }
        }
        ) { HeatStorage(1000) }

        RagiLogger.infoDebug("The new capability HEAT is registered!")
    }
}