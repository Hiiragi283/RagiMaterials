package hiiragi283.material.compat

import hiiragi283.material.api.registry.HiiragiRegistry
import hiiragi283.material.util.MetaResourceLocation
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent

object RailCraftIntegration : hiiragi283.material.compat.AbstractIntegration() {

    override fun onPostInit(event: FMLPostInitializationEvent) {
        HiiragiRegistry.registerHeatSource(MetaResourceLocation("railcraft", "ore_magic", 0), 1200 + 273)
    }

}