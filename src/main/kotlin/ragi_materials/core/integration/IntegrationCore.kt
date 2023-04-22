package ragi_materials.core.integration

import ragi_materials.core.config.RagiConfig
import ragi_materials.core.proxy.IProxy
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.event.FMLConstructionEvent
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

object IntegrationCore : IProxy {

    val enableEIO = Loader.isModLoaded("enderio") && RagiConfig.integration.enableEIO
    val enableJEI = Loader.isModLoaded("jei")
    val enableMek = Loader.isModLoaded("mekanism") && RagiConfig.integration.enableMek
    val enableTE = Loader.isModLoaded("thermalexpansion") && RagiConfig.integration.enableTE
    val enableTF = Loader.isModLoaded("thermalfoundation") && RagiConfig.integration.enableTF
    val enableTOP = Loader.isModLoaded("theoneprobe")

    override fun onConstruct(event: FMLConstructionEvent) {}

    override fun onPreInit(event: FMLPreInitializationEvent) {
        if (enableMek) PluginMekanism.onPreInit(event)
    }

    override fun onInit(event: FMLInitializationEvent) {}

    override fun onPostInit(event: FMLPostInitializationEvent) {
        PluginVanilla.loadPostInit()
    }
}