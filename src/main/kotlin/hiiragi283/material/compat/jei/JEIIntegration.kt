package hiiragi283.material.compat.jei

import hiiragi283.material.HiiragiItems
import hiiragi283.material.RMReference
import hiiragi283.material.RagiMaterials
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.registry.HiiragiRegistries
import hiiragi283.material.compat.jei.ingredients.HiiragiIngredientTypes
import hiiragi283.material.compat.jei.ingredients.MaterialStackHelper
import hiiragi283.material.compat.jei.ingredients.MaterialStackRenderer
import mezz.jei.api.IGuiHelper
import mezz.jei.api.IModPlugin
import mezz.jei.api.IModRegistry
import mezz.jei.api.JEIPlugin
import mezz.jei.api.ingredients.IModIngredientRegistration
import mezz.jei.api.recipe.IRecipeCategoryRegistration
import net.minecraft.item.ItemStack

const val MATERIAL = "${RMReference.MOD_ID}.material"

@JEIPlugin
class JEIIntegration : IModPlugin {

    init {
        RagiMaterials.LOGGER.info("Enabled Integration: JEI / HEI")
    }

    override fun registerCategories(registry: IRecipeCategoryRegistration) {
        registry.jeiHelpers.guiHelper.let { guiHelper: IGuiHelper ->
            registry.addRecipeCategories(
                HiiragiMaterialCategory(guiHelper)
            )
        }
    }

    override fun register(registry: IModRegistry) {
        //HiiragiMaterial
        registry.handleRecipes(HiiragiMaterial::class.java, HiiragiMaterialCategory::Wrapper, MATERIAL)
        registry.addRecipes(HiiragiRegistries.MATERIAL.getValues(), MATERIAL)
        registry.addRecipeCatalyst(ItemStack(HiiragiItems.MATERIAL_BOTTLE, 1, Short.MAX_VALUE.toInt()), MATERIAL)
    }

    override fun registerIngredients(registry: IModIngredientRegistration) {
        registry.register(
            HiiragiIngredientTypes.MATERIAL,
            HiiragiRegistries.MATERIAL.getValues().map { it.toMaterialStack() },
            MaterialStackHelper,
            MaterialStackRenderer,
        )
    }

}