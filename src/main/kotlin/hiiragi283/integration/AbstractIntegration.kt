package hiiragi283.integration

import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.core.HiiragiProxy
import net.minecraftforge.fml.common.event.*

abstract class AbstractIntegration : HiiragiProxy {

    override fun onConstruct(event: FMLConstructionEvent) {}

    override fun onPreInit(event: FMLPreInitializationEvent) {}

    open fun registerMaterial(registry: MutableList<HiiragiMaterial>) {}

    override fun onInit(event: FMLInitializationEvent) {}

    override fun onPostInit(event: FMLPostInitializationEvent) {}

    override fun onComplete(event: FMLLoadCompleteEvent) {}

}