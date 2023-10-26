package hiiragi283.material.compat

import hiiragi283.material.config.HiiragiConfigs
import hiiragi283.material.init.materials.MaterialCompats
import net.minecraftforge.fml.common.event.FMLInitializationEvent

object HiiragiThaumPlugin : HiiragiPluginBase("thaumcraft", "Thaumcraft", { HiiragiConfigs.INTEGRATION.thaum }) {

    override fun registerMaterial() {
        MaterialCompats.THAUMIUM.register()
        MaterialCompats.VOID_METAL.register()
    }

    override fun onInit(event: FMLInitializationEvent) {
        //HiiragiRegistries.PART.register("quicksilver", HiiragiPart(HiiragiShapes.GEM, MaterialCommons.CINNABAR))
    }

}