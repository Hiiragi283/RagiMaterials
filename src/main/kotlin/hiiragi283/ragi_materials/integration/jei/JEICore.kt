package hiiragi283.ragi_materials.integration.jei

import hiiragi283.ragi_materials.init.RagiBlock
import hiiragi283.ragi_materials.recipe.FFRecipe
import hiiragi283.ragi_materials.recipe.LaboRecipe
import hiiragi283.ragi_materials.recipe.MillRecipe
import hiiragi283.ragi_materials.util.RagiLogger
import mezz.jei.api.*
import mezz.jei.api.ingredients.IModIngredientRegistration
import mezz.jei.api.recipe.IRecipeCategoryRegistration
import net.minecraft.item.ItemStack

@JEIPlugin
class JEICore : IModPlugin {

    companion object {
        const val ForgeFurnace = "ragi_materials.forge_furnace"
        const val LaboTable = "ragi_materials.labo_table"
        const val StoneMill = "ragi_materials.stone_mill"
    }

    override fun registerCategories(registry: IRecipeCategoryRegistration) {
        val guiHelper = registry.jeiHelpers.guiHelper
        registry.addRecipeCategories(
                FFCategory(guiHelper),
                LaboCategory(guiHelper),
                StoneMillCategory(guiHelper)
        )
    }

    override fun register(registry: IModRegistry) {

        registry.handleRecipes(FFRecipe::class.java, { FFRecipe.Wrapper(it) }, ForgeFurnace)
        registry.handleRecipes(LaboRecipe::class.java, { LaboRecipe.Wrapper(it) }, LaboTable)
        registry.handleRecipes(MillRecipe::class.java, { MillRecipe.Wrapper(it) }, StoneMill)

        registry.addRecipes(FFRecipe.Registry.list, ForgeFurnace)
        registry.addRecipes(LaboRecipe.Registry.list, LaboTable)
        registry.addRecipes(MillRecipe.Registry.list, StoneMill)

        registry.addRecipeCatalyst(ItemStack(RagiBlock.BlockForgeFurnace), ForgeFurnace)
        registry.addRecipeCatalyst(ItemStack(RagiBlock.BlockBlazingForge), ForgeFurnace)
        registry.addRecipeCatalyst(ItemStack(RagiBlock.BlockLaboratoryTable), LaboTable)
        registry.addRecipeCatalyst(ItemStack(RagiBlock.BlockIndustrialLabo), LaboTable)
        registry.addRecipeCatalyst(ItemStack(RagiBlock.BlockStoneMill), StoneMill)

        RagiLogger.info("The integration for JEI/HEI has loaded!")
    }

    @Deprecated("Deprecated in Java")
    override fun registerItemSubtypes(subtypeRegistry: ISubtypeRegistry) {
    }

    override fun registerIngredients(registry: IModIngredientRegistration) {}
    override fun onRuntimeAvailable(jeiRuntime: IJeiRuntime) {}
}