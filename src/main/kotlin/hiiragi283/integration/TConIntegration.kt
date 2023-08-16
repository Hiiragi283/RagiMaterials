package hiiragi283.integration

import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.material.MaterialIntegration
import hiiragi283.api.registry.HiiragiRegistry
import hiiragi283.material.util.MetaResourceLocation
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent

object TConIntegration : AbstractIntegration() {

    override fun registerMaterial(registry: MutableList<HiiragiMaterial>) {
        registry.add(MaterialIntegration.ARDITE)
        registry.add(MaterialIntegration.MANYULLYN)
        registry.add(MaterialIntegration.ALUMINIUM_BRASS)
    }

    override fun onPostInit(event: FMLPostInitializationEvent) {
        HiiragiRegistry.registerHeatSource(MetaResourceLocation("tconstruct", "firewood", 0), 800 + 273)
        HiiragiRegistry.registerHeatSource(MetaResourceLocation("tconstruct", "firewood", 1), 1000 + 273)
    }

}