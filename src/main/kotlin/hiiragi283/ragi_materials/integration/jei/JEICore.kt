package hiiragi283.ragi_materials.integration.jei

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.RagiRegistry
import hiiragi283.ragi_materials.api.material.RagiMaterial
import hiiragi283.ragi_materials.api.recipe.FFRecipe
import hiiragi283.ragi_materials.api.recipe.LaboRecipe
import hiiragi283.ragi_materials.api.recipe.MillRecipe
import hiiragi283.ragi_materials.util.RagiLogger
import mezz.jei.api.*
import mezz.jei.api.ingredients.IModIngredientRegistration
import mezz.jei.api.recipe.IRecipeCategoryRegistration
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

@JEIPlugin
class JEICore : IModPlugin {

    companion object {
        const val ForgeFurnace = "${RagiMaterials.MOD_ID}.forge_furnace"
        const val LaboTable = "${RagiMaterials.MOD_ID}.labo_table"
        const val StoneMill = "${RagiMaterials.MOD_ID}.stone_mill"
        const val MaterialInfo = "${RagiMaterials.MOD_ID}.material_info"
    }

    override fun registerCategories(registry: IRecipeCategoryRegistration) {
        val guiHelper = registry.jeiHelpers.guiHelper
        registry.addRecipeCategories(
                FFCategory(guiHelper),
                LaboCategory(guiHelper),
                MaterialInfoCategory(guiHelper),
                StoneMillCategory(guiHelper)
        )
    }

    override fun register(registry: IModRegistry) {

        registry.handleRecipes(FFRecipe::class.java, { FFRecipe(it) }, ForgeFurnace)
        registry.handleRecipes(LaboRecipe::class.java, { LaboRecipe(it) }, LaboTable)
        registry.handleRecipes(RagiMaterial::class.java, { MaterialInfoWrapper(it) }, MaterialInfo)
        registry.handleRecipes(MillRecipe::class.java, { MillRecipe(it) }, StoneMill)

        registry.addRecipes(FFRecipe.Registry.list, ForgeFurnace)
        registry.addRecipes(LaboRecipe.Registry.list, LaboTable)
        registry.addRecipes(RagiMaterial.list, MaterialInfo)
        registry.addRecipes(MillRecipe.Registry.list, StoneMill)

        registry.addRecipeCatalyst(ItemStack(RagiRegistry.BlockForgeFurnace), ForgeFurnace)
        registry.addRecipeCatalyst(ItemStack(RagiRegistry.BlockBlazingForge), ForgeFurnace)
        registry.addRecipeCatalyst(ItemStack(RagiRegistry.BlockLaboratoryTable), LaboTable)
        registry.addRecipeCatalyst(ItemStack(RagiRegistry.BlockIndustrialLabo), LaboTable)
        registry.addRecipeCatalyst(ItemStack(Items.IRON_INGOT), MaterialInfo)
        registry.addRecipeCatalyst(ItemStack(RagiRegistry.BlockStoneMill), StoneMill)

        RagiLogger.info("The integration for JEI/HEI has loaded!")
    }

    @Deprecated("Deprecated in Java")
    override fun registerItemSubtypes(subtypeRegistry: ISubtypeRegistry) {
    }

    override fun registerIngredients(registry: IModIngredientRegistration) {}
    override fun onRuntimeAvailable(jeiRuntime: IJeiRuntime) {}
}