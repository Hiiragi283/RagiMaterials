package hiiragi283.material

import net.minecraftforge.fml.common.event.*

interface HiiragiProxy {

    fun onConstruct(event: FMLConstructionEvent) {}

    fun onPreInit(event: FMLPreInitializationEvent) {}

    fun onInit(event: FMLInitializationEvent) {}

    fun onPostInit(event: FMLPostInitializationEvent) {}

    fun onComplete(event: FMLLoadCompleteEvent) {}

}