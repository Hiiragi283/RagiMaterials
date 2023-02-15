package hiiragi283.ragi_materials.integration.jei

import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.integration.jei.forge_furnace.ForgeFurnaceCategory
import hiiragi283.ragi_materials.integration.jei.forge_furnace.ForgeFurnaceMaker
import hiiragi283.ragi_materials.integration.jei.forge_furnace.ForgeFurnaceWrapper
import hiiragi283.ragi_materials.integration.jei.salt_pond.SaltPondCategory
import hiiragi283.ragi_materials.integration.jei.salt_pond.SaltPondMaker
import hiiragi283.ragi_materials.integration.jei.salt_pond.SaltPondRecipe
import hiiragi283.ragi_materials.integration.jei.salt_pond.SaltPondWrapper
import hiiragi283.ragi_materials.recipe.forge_furnace.ForgeFurnaceRecipe
import hiiragi283.ragi_materials.util.RagiLogger
import mezz.jei.api.*
import mezz.jei.api.ingredients.IModIngredientRegistration
import mezz.jei.api.recipe.IRecipeCategoryRegistration
import net.minecraft.item.ItemStack

@JEIPlugin
class JEICore : IModPlugin {

    companion object {
        const val ForgeFurnace = "ragi_materials.forge_furnace"
        const val SaltPond = "ragi_materials.salt_pond"
    }

    override fun registerCategories(registry: IRecipeCategoryRegistration) {
        val jeiHelpers = registry.jeiHelpers
        val guiHelper = jeiHelpers.guiHelper
        registry.addRecipeCategories(ForgeFurnaceCategory(guiHelper), SaltPondCategory(guiHelper))
    }

    override fun register(registry: IModRegistry) {

        registry.handleRecipes(
            ForgeFurnaceRecipe::class.java,
            { ForgeFurnaceWrapper(it) },
            ForgeFurnace
        )
        registry.handleRecipes(
            SaltPondRecipe::class.java,
            { SaltPondWrapper(it) },
            SaltPond
        )

        ForgeFurnaceMaker.register(registry)
        SaltPondMaker.register(registry)

        registry.addRecipeCatalyst(ItemStack(RagiInit.ItemBlockForgeFurnace), ForgeFurnace)
        registry.addRecipeCatalyst(ItemStack(RagiInit.ItemBlockBlazeHeater, 1, 0), ForgeFurnace)
        registry.addRecipeCatalyst(ItemStack(RagiInit.ItemBlockBlazeHeater, 1, 1), ForgeFurnace)
        registry.addRecipeCatalyst(ItemStack(RagiInit.ItemBlockSaltPond), SaltPond)

        RagiLogger.info("The integration for JEI/HEI has loaded!")
    }

    @Deprecated("Deprecated in Java")
    override fun registerItemSubtypes(subtypeRegistry: ISubtypeRegistry) {}
    override fun registerIngredients(registry: IModIngredientRegistration) {}
    override fun onRuntimeAvailable(jeiRuntime: IJeiRuntime) {}
}