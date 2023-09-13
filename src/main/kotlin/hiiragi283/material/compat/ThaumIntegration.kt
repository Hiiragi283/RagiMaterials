package hiiragi283.material.compat

import hiiragi283.api.material.MaterialCommon
import hiiragi283.api.material.MaterialIntegration
import hiiragi283.api.part.HiiragiPart
import hiiragi283.api.shape.HiiragiShapes
import hiiragi283.material.api.registry.HiiragiRegistry
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent

object ThaumIntegration : hiiragi283.material.compat.AbstractIntegration() {

    override fun registerMaterial() {
        HiiragiRegistry.registerMaterial(MaterialIntegration.THAUMIUM)
        HiiragiRegistry.registerMaterial(MaterialIntegration.VOID_METAL)
    }

    override fun onPostInit(event: FMLPostInitializationEvent) {
        HiiragiRegistry.registerTag("quicksilver", HiiragiPart(HiiragiShapes.GEM, MaterialCommon.CINNABAR))
        HiiragiRegistry.registerTag(
            "nuggetQuicksilver",
            HiiragiPart(HiiragiShapes.NUGGET, MaterialCommon.CINNABAR)
        )
    }

}