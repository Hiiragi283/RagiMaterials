package hiiragi283.integration.jei

import hiiragi283.core.RagiMaterials
import hiiragi283.material.RMReference
import mezz.jei.api.IModPlugin
import mezz.jei.api.IModRegistry
import mezz.jei.api.JEIPlugin
import mezz.jei.api.recipe.IRecipeCategoryRegistration

@JEIPlugin
class JeiIntegration : IModPlugin {

    init {
        RagiMaterials.LOGGER.info("Enabled Integration: JEI / HEI")
    }

    companion object {
        const val CRUCIBLE_MELT = "${RMReference.MOD_ID}.crucible_melt"
    }

    override fun registerCategories(registry: IRecipeCategoryRegistration) {
        CrucibleMeltCategory.registerCategory(registry)
    }

    override fun register(registry: IModRegistry) {
        CrucibleMeltCategory.register(registry)
    }

}