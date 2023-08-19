package hiiragi283.integration

import hiiragi283.api.HiiragiRegistry
import hiiragi283.api.material.MaterialIntegration
import hiiragi283.api.shape.HiiragiShapes
import hiiragi283.material.util.getItem
import hiiragi283.material.util.registerOreDict
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent

object EnderIOIntegration : AbstractIntegration() {

    override fun registerMaterial() {
        HiiragiRegistry.registerMaterial(MaterialIntegration.ELECTRICAL_STEEL)
        HiiragiRegistry.registerMaterial(MaterialIntegration.ENERGETIC_ALLOY)
        HiiragiRegistry.registerMaterial(MaterialIntegration.VIBRANT_ALLOY)
        HiiragiRegistry.registerMaterial(MaterialIntegration.REDSTONE_ALLOY)
        HiiragiRegistry.registerMaterial(MaterialIntegration.CONDUCTIVE_IRON)
        HiiragiRegistry.registerMaterial(MaterialIntegration.PULSATING_IRON)
        HiiragiRegistry.registerMaterial(MaterialIntegration.DARK_STEEL)
        HiiragiRegistry.registerMaterial(MaterialIntegration.SOULARIUM)
        HiiragiRegistry.registerMaterial(MaterialIntegration.END_STEEL)
        HiiragiRegistry.registerMaterial(MaterialIntegration.IRON_ALLOY)
    }

    override fun onPostInit(event: FMLPostInitializationEvent) {
        registerOreDict(
            HiiragiShapes.BALL.getOreDict(MaterialIntegration.SIGNALUM),
            getItem("enderio:item_material"),
            57
        )
        registerOreDict(
            HiiragiShapes.BALL.getOreDict(MaterialIntegration.LUMIUM),
            getItem("enderio:item_material"),
            58
        )
        registerOreDict(
            HiiragiShapes.BALL.getOreDict(MaterialIntegration.ENDERIUM),
            getItem("enderio:item_material"),
            59
        )
    }

}