package hiiragi283.integration

import hiiragi283.api.HiiragiRegistry
import hiiragi283.api.material.MaterialCommon
import hiiragi283.api.material.MaterialIntegration
import hiiragi283.api.part.HiiragiPart
import hiiragi283.api.shape.HiiragiShapes
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent

object ThaumIntegration : AbstractIntegration() {

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