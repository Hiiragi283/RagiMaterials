package hiiragi283.api.capability

import hiiragi283.api.capability.material.IMaterialHandler
import hiiragi283.api.capability.material.MaterialHandler
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityInject
import net.minecraftforge.common.capabilities.CapabilityManager

object HiiragiCapability {

    @CapabilityInject(IMaterialHandler::class)
    lateinit var MATERIAL: Capability<IMaterialHandler>

    fun register() {

        CapabilityManager.INSTANCE.register(
            IMaterialHandler::class.java,
            HiiragiStorage<IMaterialHandler>()
        ) { MaterialHandler(capacity = 1000) }

    }

}