package hiiragi283.integration.jei

import hiiragi283.api.recipe.CrucibleRecipe
import hiiragi283.api.recipe.CrushingRecipe
import hiiragi283.api.registry.HiiragiRegistry
import hiiragi283.material.RMBlocks
import hiiragi283.material.RMItems
import hiiragi283.material.RMReference
import hiiragi283.material.RagiMaterials
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
        registry.addRecipeCatalyst(ItemStack(RMBlocks.CRUCIBLE), CRUCIBLE_MELT)
        //Crushing
        registry.handleRecipes(CrushingRecipe::class.java, { CrushingRecipe(it) }, CRUSHING)
        registry.addRecipes(HiiragiRegistry.CRUSHING.valuesCollection, CRUSHING)
        registry.addRecipeCatalyst(ItemStack(RMItems.CRUSHING_HAMMER), CRUSHING)
    }

}