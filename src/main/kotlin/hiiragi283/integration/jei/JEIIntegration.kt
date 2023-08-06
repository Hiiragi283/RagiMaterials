package hiiragi283.integration.jei

import hiiragi283.api.recipe.CrucibleRecipe
import hiiragi283.api.recipe.CrushingRecipe
import hiiragi283.api.registry.HiiragiRegistry
import hiiragi283.chemistry.RCBlocks
import hiiragi283.chemistry.RCItems
import hiiragi283.core.RagiMaterials
import hiiragi283.material.RMReference
import mezz.jei.api.IModPlugin
import mezz.jei.api.IModRegistry
import mezz.jei.api.JEIPlugin
import mezz.jei.api.recipe.IRecipeCategoryRegistration
import net.minecraft.item.ItemStack

const val CRUCIBLE_MELT = "${RMReference.MOD_ID}.crucible_melt"

const val CRUSHING = "${RMReference.MOD_ID}.crushing"

@JEIPlugin
class JEIIntegration : IModPlugin {

    init {
        RagiMaterials.LOGGER.info("Enabled Integration: JEI / HEI")
    }

    override fun registerCategories(registry: IRecipeCategoryRegistration) {
        val guiHelper = registry.jeiHelpers.guiHelper
        registry.addRecipeCategories(
            CrucibleMeltCategory(guiHelper),
            CrushingCategory(guiHelper)
        )
    }

    override fun register(registry: IModRegistry) {
        //Crucible - Melt
        registry.handleRecipes(CrucibleRecipe::class.java, { CrucibleRecipe(it) }, CRUCIBLE_MELT)
        registry.addRecipes(HiiragiRegistry.CRUCIBLE.valuesCollection, CRUCIBLE_MELT)
        registry.addRecipeCatalyst(ItemStack(RCBlocks.CRUCIBLE), CRUCIBLE_MELT)
        //Crushing
        registry.handleRecipes(CrushingRecipe::class.java, { CrushingRecipe(it) }, CRUSHING)
        registry.addRecipes(HiiragiRegistry.CRUSHING.valuesCollection, CRUSHING)
        registry.addRecipeCatalyst(ItemStack(RCItems.CRUSHING_HAMMER), CRUSHING)
    }

}