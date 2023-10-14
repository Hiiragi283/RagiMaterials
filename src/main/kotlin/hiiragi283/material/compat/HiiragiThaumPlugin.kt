package hiiragi283.material.compat

import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.config.HiiragiConfigs
import hiiragi283.material.init.HiiragiRegistries
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.init.materials.MaterialCommons
import hiiragi283.material.init.materials.MaterialCompats
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent

object HiiragiThaumPlugin : HiiragiPluginBase("thaumcraft", "Thaumcraft", { HiiragiConfigs.INTEGRATION.thaum }) {

    override fun registerMaterial() {
        MaterialCompats.THAUMIUM.register()
        MaterialCompats.VOID_METAL.register()
    }

    override fun onPostInit(event: FMLPostInitializationEvent) {
        HiiragiRegistries.PART.register("quicksilver", HiiragiPart(HiiragiShapes.GEM, MaterialCommons.CINNABAR))
        HiiragiRegistries.PART.register(
            "nuggetQuicksilver",
            HiiragiPart(HiiragiShapes.NUGGET, MaterialCommons.CINNABAR)
        )
    }

}