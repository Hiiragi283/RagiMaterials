package hiiragi283.ragi_materials.integration.jei

import hiiragi283.ragi_materials.init.RagiBlock
import hiiragi283.ragi_materials.integration.jei.forge_furnace.FFCategory
import hiiragi283.ragi_materials.integration.jei.forge_furnace.FFMaker
import hiiragi283.ragi_materials.integration.jei.forge_furnace.FFWrapper
import hiiragi283.ragi_materials.integration.jei.laboratory_table.LaboCategory
import hiiragi283.ragi_materials.integration.jei.laboratory_table.LaboMaker
import hiiragi283.ragi_materials.integration.jei.laboratory_table.LaboWrapper
import hiiragi283.ragi_materials.integration.jei.salt_pond.SaltPondCategory
import hiiragi283.ragi_materials.integration.jei.salt_pond.SaltPondMaker
import hiiragi283.ragi_materials.integration.jei.salt_pond.SaltPondWrapper
import hiiragi283.ragi_materials.integration.jei.stone_mill.StoneMillCategory
import hiiragi283.ragi_materials.integration.jei.stone_mill.StoneMillMaker
import hiiragi283.ragi_materials.integration.jei.stone_mill.StoneMillWrapper
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
        const val StoneMill = "ragi_materials.stone_mill"
    }

    override fun registerCategories(registry: IRecipeCategoryRegistration) {
        val guiHelper = registry.jeiHelpers.guiHelper
        registry.addRecipeCategories(
                FFCategory(guiHelper),
                LaboCategory(guiHelper),
                SaltPondCategory(guiHelper),
                StoneMillCategory(guiHelper)
        )
    }

    override fun register(registry: IModRegistry) {

        registry.handleRecipes(FFMaker.Recipe::class.java, { FFWrapper(it) }, ForgeFurnace)
        registry.handleRecipes(LaboRecipe::class.java, { LaboWrapper(it) }, LaboTable)
        registry.handleRecipes(SaltPondMaker.Recipe::class.java, { SaltPondWrapper(it) }, SaltPond)
        registry.handleRecipes(StoneMillMaker.Recipe::class.java, { StoneMillWrapper(it) }, StoneMill)

        FFMaker.register(registry)
        LaboMaker.register(registry)
        SaltPondMaker.register(registry)
        StoneMillMaker.register(registry)

        registry.addRecipeCatalyst(ItemStack(RagiBlock.BlockForgeFurnace), ForgeFurnace)
        registry.addRecipeCatalyst(ItemStack(RagiBlock.BlockBlazingForge), ForgeFurnace)

        registry.addRecipeCatalyst(ItemStack(RagiBlock.BlockLaboratoryTable), LaboTable)

        registry.addRecipeCatalyst(ItemStack(RagiBlock.BlockSaltPond), SaltPond)

        registry.addRecipeCatalyst(ItemStack(RagiBlock.BlockStoneMill), StoneMill)

        RagiLogger.info("The integration for JEI/HEI has loaded!")
    }

    @Deprecated("Deprecated in Java")
    override fun registerItemSubtypes(subtypeRegistry: ISubtypeRegistry) {}
    override fun registerIngredients(registry: IModIngredientRegistration) {}
    override fun onRuntimeAvailable(jeiRuntime: IJeiRuntime) {}
}