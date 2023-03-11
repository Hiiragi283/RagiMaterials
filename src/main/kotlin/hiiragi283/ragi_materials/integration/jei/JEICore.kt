package hiiragi283.ragi_materials.integration.jei

import hiiragi283.ragi_materials.init.RagiInit
import hiiragi283.ragi_materials.integration.jei.forge_furnace.FFCategory
import hiiragi283.ragi_materials.integration.jei.forge_furnace.FFMaker
import hiiragi283.ragi_materials.integration.jei.forge_furnace.FFRecipe
import hiiragi283.ragi_materials.integration.jei.forge_furnace.FFWrapper
import hiiragi283.ragi_materials.integration.jei.laboratory_table.LaboCategory
import hiiragi283.ragi_materials.integration.jei.laboratory_table.LaboMaker
import hiiragi283.ragi_materials.integration.jei.laboratory_table.LaboWrapper
import hiiragi283.ragi_materials.integration.jei.salt_pond.SaltPondCategory
import hiiragi283.ragi_materials.integration.jei.salt_pond.SaltPondMaker
import hiiragi283.ragi_materials.integration.jei.salt_pond.SaltPondRecipe
import hiiragi283.ragi_materials.integration.jei.salt_pond.SaltPondWrapper
import hiiragi283.ragi_materials.recipe.laboratory.LaboRecipe
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
        const val SaltPond = "ragi_materials.salt_pond"
    }

    override fun registerCategories(registry: IRecipeCategoryRegistration) {
        val jeiHelpers = registry.jeiHelpers
        val guiHelper = jeiHelpers.guiHelper
        registry.addRecipeCategories(
                FFCategory(guiHelper),
                LaboCategory(guiHelper),
                SaltPondCategory(guiHelper)
        )
    }

    override fun register(registry: IModRegistry) {

        registry.handleRecipes(FFRecipe::class.java, { FFWrapper(it) }, ForgeFurnace)
        registry.handleRecipes(LaboRecipe::class.java, { LaboWrapper(it) }, LaboTable)
        registry.handleRecipes(SaltPondRecipe::class.java, { SaltPondWrapper(it) }, SaltPond)

        FFMaker.register(registry)
        LaboMaker.register(registry)
        SaltPondMaker.register(registry)

        registry.addRecipeCatalyst(ItemStack(RagiInit.BlockForgeFurnace), ForgeFurnace)
        registry.addRecipeCatalyst(ItemStack(RagiInit.BlockLaboratoryTable), LaboTable)
        registry.addRecipeCatalyst(ItemStack(RagiInit.BlockSaltPond), SaltPond)

        RagiLogger.info("The integration for JEI/HEI has loaded!")
    }

    @Deprecated("Deprecated in Java")
    override fun registerItemSubtypes(subtypeRegistry: ISubtypeRegistry) {
    }

    override fun registerIngredients(registry: IModIngredientRegistration) {}
    override fun onRuntimeAvailable(jeiRuntime: IJeiRuntime) {}
}