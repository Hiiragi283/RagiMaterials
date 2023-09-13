package hiiragi283.material.compat

import hiiragi283.material.HiiragiProxy
import net.minecraftforge.fml.common.event.*

abstract class AbstractIntegration : HiiragiProxy {

    override fun onConstruct(event: FMLConstructionEvent) {}

    override fun onPreInit(event: FMLPreInitializationEvent) {}

    open fun registerMaterial() {}

    override fun onInit(event: FMLInitializationEvent) {}

    override fun onPostInit(event: FMLPostInitializationEvent) {}

    override fun onComplete(event: FMLLoadCompleteEvent) {}

}