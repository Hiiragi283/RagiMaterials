package ragi_materials.core.integration.jei

import mezz.jei.api.IModPlugin
import mezz.jei.api.IModRegistry
import mezz.jei.api.JEIPlugin
import mezz.jei.api.recipe.IRecipeCategoryRegistration
import ragi_materials.core.RagiMaterials

@JEIPlugin
class JEICore : IModPlugin {

    companion object {
        const val Basin = "${RagiMaterials.MOD_ID}.basin"
        const val Bloomery = "${RagiMaterials.MOD_ID}.bloomery"
        const val ForgeFurnace = "${RagiMaterials.MOD_ID}.forge_furnace"
        const val LaboTable = "${RagiMaterials.MOD_ID}.labo_table"
        const val MaterialInfo = "${RagiMaterials.MOD_ID}.material_info"
        const val StoneMill = "${RagiMaterials.MOD_ID}.stone_mill"
    }

    override fun registerCategories(registry: IRecipeCategoryRegistration) {
        BasinCategory.registerCategory(registry)
        BloomeryJEI.Category.registerCategory(registry)
        FFCategory.registerCategory(registry)
        LaboCategory.registerCategory(registry)
        MaterialInfoJEI.Category.registerCategory(registry)
        StoneMillCategory.registerCategory(registry)
    }

    override fun register(registry: IModRegistry) {
        BasinCategory.register(registry)
        BloomeryJEI.Category.register(registry)
        FFCategory.register(registry)
        LaboCategory.register(registry)
        MaterialInfoJEI.Category.register(registry)
        StoneMillCategory.register(registry)
        RagiMaterials.LOGGER.info("The integration for JEI/HEI has loaded!")
    }
}