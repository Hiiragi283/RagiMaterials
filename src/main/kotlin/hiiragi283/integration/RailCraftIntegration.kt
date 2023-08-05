package hiiragi283.integration

import hiiragi283.api.registry.HiiragiRegistry
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent

object RailCraftIntegration : AbstractIntegration() {

    override fun onPostInit(event: FMLPostInitializationEvent) {
        HiiragiRegistry.registerHeatSource(ResourceLocation("railcraft", "ore_magic"), 1200 + 273)
    }

}