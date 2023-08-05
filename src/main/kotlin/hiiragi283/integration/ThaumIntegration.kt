package hiiragi283.integration

import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.material.MaterialCommon
import hiiragi283.api.material.MaterialIntegration
import hiiragi283.api.part.HiiragiPart
import hiiragi283.api.part.PartRegistry
import hiiragi283.api.shape.HiiragiShapes
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent

object ThaumIntegration : AbstractIntegration() {

    override fun registerMaterial(registry: MutableList<HiiragiMaterial>) {
        registry.add(MaterialIntegration.THAUMIUM)
        registry.add(MaterialIntegration.VOID_METAL)
    }

    override fun onPostInit(event: FMLPostInitializationEvent) {
        PartRegistry.registerTag("quicksilver", HiiragiPart(HiiragiShapes.GEM, MaterialCommon.CINNABAR))
        PartRegistry.registerTag(
            "nuggetQuicksilver",
            HiiragiPart(HiiragiShapes.NUGGET, MaterialCommon.CINNABAR)
        )
    }

}