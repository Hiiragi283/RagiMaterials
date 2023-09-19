package hiiragi283.material.compat

import hiiragi283.material.HiiragiProxy
import net.minecraftforge.fml.common.event.FMLConstructionEvent
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent

interface IHiiragiPlugin : HiiragiProxy {

    override fun onConstruct(event: FMLConstructionEvent) {
        throw UnsupportedOperationException()
    }

    fun registerMaterial() {}

    override fun onComplete(event: FMLLoadCompleteEvent) {
        throw UnsupportedOperationException()
    }

}