package hiiragi283.material.proxy

import hiiragi283.material.registry.RagiRegistry
import net.minecraftforge.common.MinecraftForge

class ClientProxy : CommonProxy() {

    override fun onConstruct() {
        super.onConstruct()
        MinecraftForge.EVENT_BUS.register(RagiRegistry.ClientEvent)
    }

}