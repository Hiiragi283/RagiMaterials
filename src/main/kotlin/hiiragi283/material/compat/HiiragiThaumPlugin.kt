package hiiragi283.material.compat

import hiiragi283.material.api.material.MaterialCommon
import hiiragi283.material.api.material.MaterialCompat
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.api.shape.HiiragiShapes
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent

object HiiragiThaumPlugin : IHiiragiPlugin {

    override fun registerMaterial() {
        MaterialCompat.THAUMIUM.register()
        MaterialCompat.VOID_METAL.register()
    }

    override fun onPostInit(event: FMLPostInitializationEvent) {
        HiiragiRegistries.PART.register("quicksilver", HiiragiPart(HiiragiShapes.GEM, MaterialCommon.CINNABAR))
        HiiragiRegistries.PART.register(
            "nuggetQuicksilver",
            HiiragiPart(HiiragiShapes.NUGGET, MaterialCommon.CINNABAR)
        )
    }

}