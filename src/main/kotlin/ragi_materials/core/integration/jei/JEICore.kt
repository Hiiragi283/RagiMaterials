package ragi_materials.core.integration.jei

import mezz.jei.api.IModPlugin
import mezz.jei.api.IModRegistry
import mezz.jei.api.JEIPlugin
import mezz.jei.api.recipe.IRecipeCategoryRegistration
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import ragi_materials.core.RagiMaterials
import ragi_materials.core.RagiRegistry
import ragi_materials.core.config.RagiConfig
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.recipe.FFRecipe
import ragi_materials.core.recipe.LaboRecipe
import ragi_materials.core.recipe.MillRecipe

@JEIPlugin
class JEICore : IModPlugin {

    companion object {
        const val Bloomery = "${RagiMaterials.MOD_ID}.bloomery"
        const val ForgeFurnace = "${RagiMaterials.MOD_ID}.forge_furnace"
        const val LaboTable = "${RagiMaterials.MOD_ID}.labo_table"
        const val MaterialInfo = "${RagiMaterials.MOD_ID}.material_info"
        const val StoneMill = "${RagiMaterials.MOD_ID}.stone_mill"
    }

    override fun registerCategories(registry: IRecipeCategoryRegistration) {
        val guiHelper = registry.jeiHelpers.guiHelper
        registry.addRecipeCategories(
                BloomeryCategory(guiHelper),
                FFCategory(guiHelper),
                LaboCategory(guiHelper),
                MaterialInfoCategory(guiHelper),
                StoneMillCategory(guiHelper)
        )
    }

    override fun register(registry: IModRegistry) {

        //Handler Registration
        registry.handleRecipes(RagiMaterial::class.java, { BloomeryWrapper(it) }, Bloomery)
        registry.handleRecipes(FFRecipe::class.java, { FFRecipe(it) }, ForgeFurnace)
        registry.handleRecipes(LaboRecipe::class.java, { LaboRecipe(it) }, LaboTable)
        registry.handleRecipes(RagiMaterial::class.java, { MaterialInfoWrapper(it) }, MaterialInfo)
        registry.handleRecipes(MillRecipe::class.java, { MillRecipe(it) }, StoneMill)

        //Recipe Registration
        registry.addRecipes(getListOre(), Bloomery)
        registry.addRecipes(RagiRegistry.FF_RECIPE.valuesCollection, ForgeFurnace)
        registry.addRecipes(RagiRegistry.LABO_RECIPE.valuesCollection, LaboTable)
        registry.addRecipes(MaterialRegistry.list, MaterialInfo)
        registry.addRecipes(RagiRegistry.MILL_RECIPE.valuesCollection, StoneMill)

        //Catalyst Registration
        registry.addRecipeCatalyst(ItemStack(Items.IRON_INGOT), MaterialInfo)

        if (RagiConfig.module.enableMain) {
            registry.addRecipeCatalyst(ItemStack(RagiRegistry.BlockLaboratoryTable), LaboTable)
            registry.addRecipeCatalyst(ItemStack(RagiRegistry.BlockIndustrialLabo), LaboTable)
            registry.addRecipeCatalyst(ItemStack(RagiRegistry.BlockStoneMill), StoneMill)
        }
        if (RagiConfig.module.enableMetallurgy) {
            registry.addRecipeCatalyst(ItemStack(RagiRegistry.BlockBloomery), Bloomery)
            registry.addRecipeCatalyst(ItemStack(RagiRegistry.BlockForgeFurnace), ForgeFurnace)
            registry.addRecipeCatalyst(ItemStack(RagiRegistry.BlockBlazingForge), ForgeFurnace)
        }

        RagiMaterials.LOGGER.info("The integration for JEI/HEI has loaded!")
    }
}