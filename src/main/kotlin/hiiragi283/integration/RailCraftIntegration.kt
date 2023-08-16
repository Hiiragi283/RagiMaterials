package hiiragi283.integration

import hiiragi283.api.registry.HiiragiRegistry
import hiiragi283.material.util.MetaResourceLocation
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent

object RailCraftIntegration : AbstractIntegration() {

    override fun onPostInit(event: FMLPostInitializationEvent) {
        HiiragiRegistry.registerHeatSource(MetaResourceLocation("railcraft", "ore_magic", 0), 1200 + 273)
    }

}