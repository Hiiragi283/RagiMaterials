package hiiragi283.ragi_materials.integration.jei

import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.integration.jei.forge_furnace.ForgeFurnaceCategory
import hiiragi283.ragi_materials.integration.jei.forge_furnace.ForgeFurnaceMaker
import hiiragi283.ragi_materials.integration.jei.forge_furnace.ForgeFurnaceRecipe
import hiiragi283.ragi_materials.integration.jei.forge_furnace.ForgeFurnaceWrapper
import hiiragi283.ragi_materials.util.RagiLogger
import mezz.jei.api.*
import mezz.jei.api.ingredients.IModIngredientRegistration
import mezz.jei.api.recipe.IRecipeCategoryRegistration
import net.minecraft.item.ItemStack

@JEIPlugin
class JEICore : IModPlugin {
    override fun registerCategories(registry: IRecipeCategoryRegistration) {
        val jeiHelpers = registry.jeiHelpers
        val guiHelper = jeiHelpers.guiHelper
        registry.addRecipeCategories(ForgeFurnaceCategory(guiHelper))
    }

    override fun register(registry: IModRegistry) {
        registry.handleRecipes(
            ForgeFurnaceRecipe::class.java,
            {ForgeFurnaceWrapper(it) },
            "ragi_materials.forge_furnace"
        )
        ForgeFurnaceMaker.register(registry)
        RagiLogger.info("The integration for JEI/HEI has loaded!")
        //catalystの登録
        registry.addRecipeCatalyst(ItemStack(RagiInit.ItemBlockForgeFurnace), "ragi_materials.forge_furnace")
        registry.addRecipeCatalyst(ItemStack(RagiInit.ItemBlockBlazeHeater, 1, 0), "ragi_materials.forge_furnace")
        registry.addRecipeCatalyst(ItemStack(RagiInit.ItemBlockBlazeHeater, 1, 1), "ragi_materials.forge_furnace")
    }

    @Deprecated("Deprecated in Java")
    override fun registerItemSubtypes(subtypeRegistry: ISubtypeRegistry) {}
    override fun registerIngredients(registry: IModIngredientRegistration) {}
    override fun onRuntimeAvailable(jeiRuntime: IJeiRuntime) {}
}