package hiiragi283.material.compat

import hiiragi283.material.config.HiiragiConfigs
import hiiragi283.material.proxy.IHiiragiProxy
import hiiragi283.material.util.HiiragiLogger
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.event.FMLConstructionEvent
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent

abstract class HiiragiPluginBase(
    val modid: String,
    val modName: String,
    val config: () -> Boolean
) : IHiiragiProxy {

    open fun enabled(): Boolean = HiiragiConfigs.INTEGRATION.forceIntegration || Loader.isModLoaded(modid) && config()

    fun apply(list: MutableList<HiiragiPluginBase>) {
        if (enabled()) {
            HiiragiLogger.info("Integration Enabled: $modName")
            list.add(this)
        }
    }

    fun getResourceLocation(path: String) = ResourceLocation(modid, path)

    override fun onConstruct(event: FMLConstructionEvent) {
        throw UnsupportedOperationException()
    }

    override fun onComplete(event: FMLLoadCompleteEvent) {
        throw UnsupportedOperationException()
    }

}