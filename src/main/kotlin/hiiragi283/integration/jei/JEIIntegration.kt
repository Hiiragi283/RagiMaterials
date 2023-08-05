package hiiragi283.integration.jei

import hiiragi283.api.recipe.CrucibleRecipe
import hiiragi283.api.registry.HiiragiRegistry
import hiiragi283.chemistry.RCBlocks
import hiiragi283.core.RagiMaterials
import hiiragi283.material.RMReference
import mezz.jei.api.IModPlugin
import mezz.jei.api.IModRegistry
import mezz.jei.api.JEIPlugin
import mezz.jei.api.recipe.IRecipeCategoryRegistration
import net.minecraft.item.ItemStack

const val CRUCIBLE_MELT = "${RMReference.MOD_ID}.crucible_melt"

@JEIPlugin
class JEIIntegration : IModPlugin {

    init {
        RagiMaterials.LOGGER.info("Enabled Integration: JEI / HEI")
    }

    override fun registerCategories(registry: IRecipeCategoryRegistration) {
        val guiHelper = registry.jeiHelpers.guiHelper
        registry.addRecipeCategories(CrucibleMeltCategory(guiHelper))
    }

    override fun register(registry: IModRegistry) {
        //Crucible - Melt
        registry.handleRecipes(CrucibleRecipe::class.java, { CrucibleRecipe(it) }, CRUCIBLE_MELT)
        registry.addRecipes(HiiragiRegistry.CRUCIBLE.valuesCollection, CRUCIBLE_MELT)
        registry.addRecipeCatalyst(ItemStack(RCBlocks.CRUCIBLE), CRUCIBLE_MELT)
    }

}