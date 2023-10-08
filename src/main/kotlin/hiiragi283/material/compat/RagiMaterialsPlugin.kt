package hiiragi283.material.compat

import hiiragi283.material.RMReference
import net.minecraftforge.fml.common.event.FMLConstructionEvent
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

object RagiMaterialsPlugin : HiiragiPluginBase(RMReference.MOD_ID, RMReference.MOD_NAME, { true }) {

    private val list: MutableList<HiiragiPluginBase> = mutableListOf(HiiragiVanillaPlugin)

    override fun onConstruct(event: FMLConstructionEvent) {
        HiiragiBotaniaPlugin.apply(list)
        HiiragiEmbersPlugin.apply(list)
        HiiragiEnderIOPlugin.apply(list)
        HiiragiIC2exPlugin.apply(list)
        HiiragiMekanismPlugin.apply(list)
        HiiragiProjectRedPlugin.apply(list)
        HiiragiRailCraftPlugin.apply(list)
        HiiragiTConPlugin.apply(list)
        HiiragiThaumPlugin.apply(list)
        HiiragiThermalPlugin.apply(list)
        HiiragiTOPPlugin.apply(list)
    }

    override fun onPreInit(event: FMLPreInitializationEvent) {
        list.forEach { it.onPreInit(event) }
    }

    override fun registerMaterial() {
        list.forEach { it.registerMaterial() }
    }

    override fun onInit(event: FMLInitializationEvent) {
        list.forEach { it.onInit(event) }
    }

    override fun onPostInit(event: FMLPostInitializationEvent) {
        list.forEach { it.onPostInit(event) }
    }

}