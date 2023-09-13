package hiiragi283.material.compat

import hiiragi283.api.material.MaterialIntegration
import hiiragi283.material.api.registry.HiiragiRegistry
import hiiragi283.material.util.MetaResourceLocation
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent

object TConIntegration : hiiragi283.material.compat.AbstractIntegration() {

    override fun registerMaterial() {
        HiiragiRegistry.registerMaterial(MaterialIntegration.ARDITE)
        HiiragiRegistry.registerMaterial(MaterialIntegration.MANYULLYN)
        HiiragiRegistry.registerMaterial(MaterialIntegration.ALUMINIUM_BRASS)
    }

    override fun onPostInit(event: FMLPostInitializationEvent) {
        HiiragiRegistry.registerHeatSource(MetaResourceLocation("tconstruct", "firewood", 0), 800 + 273)
        HiiragiRegistry.registerHeatSource(MetaResourceLocation("tconstruct", "firewood", 1), 1000 + 273)
    }

}