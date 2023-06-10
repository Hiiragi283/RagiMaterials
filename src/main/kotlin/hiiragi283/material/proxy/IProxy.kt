package hiiragi283.material.proxy

import net.minecraftforge.fml.common.event.*

interface IProxy {

    fun onConstruct(event: FMLConstructionEvent)

    fun onPreInit(event: FMLPreInitializationEvent)

    fun onInit(event: FMLInitializationEvent)

    fun onPostInit(event: FMLPostInitializationEvent)

    fun onComplete(event: FMLLoadCompleteEvent)

}