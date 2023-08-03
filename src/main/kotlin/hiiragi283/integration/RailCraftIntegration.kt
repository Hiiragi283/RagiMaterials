package hiiragi283.integration

import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.registry.HeatSourceRegistry
import net.minecraft.util.ResourceLocation

object RailCraftIntegration : AbstractIntegration() {

    override fun onPreInit() {
    }

    override fun registerMaterial(registry: MutableList<HiiragiMaterial>) {
    }

    override fun onInit() {
    }

    override fun onPostInit() {
        HeatSourceRegistry.registerBlock(ResourceLocation("railcraft", "ore_magic"), 1200 + 273)
    }

    override fun onComplete() {
    }

}