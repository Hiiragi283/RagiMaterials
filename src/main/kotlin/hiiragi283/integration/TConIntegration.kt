package hiiragi283.integration

import hiiragi283.api.material.HiiragiMaterial
import hiiragi283.api.material.MaterialIntegration
import hiiragi283.api.registry.HeatSourceRegistry
import hiiragi283.core.RagiMaterials
import net.minecraft.util.ResourceLocation

object TConIntegration : AbstractIntegration() {

    override fun onPreInit() {
    }

    override fun registerMaterial(registry: MutableList<HiiragiMaterial>) {
        RagiMaterials.LOGGER.info("Enabled integration: Tinker's Construct")
        registry.add(MaterialIntegration.ARDITE)
        registry.add(MaterialIntegration.MANYULLYN)
        registry.add(MaterialIntegration.ALUMINIUM_BRASS)
    }

    override fun onInit() {
    }

    override fun onPostInit() {
        HeatSourceRegistry.registerBlock(ResourceLocation("tocnstruct", "firewood"), 0, 800 + 273)
        HeatSourceRegistry.registerBlock(ResourceLocation("tocnstruct", "firewood"), 1, 1000 + 273)
    }

    override fun onComplete() {
    }

}