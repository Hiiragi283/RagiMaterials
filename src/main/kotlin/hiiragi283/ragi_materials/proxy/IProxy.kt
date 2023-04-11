package hiiragi283.ragi_materials.proxy

import net.minecraftforge.fml.common.event.FMLConstructionEvent
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

interface IProxy {

    fun onConstruct(event: FMLConstructionEvent)

    fun onPreInit(event: FMLPreInitializationEvent)

    fun onInit(event: FMLInitializationEvent)

    fun onPostInit(event: FMLPostInitializationEvent)

}