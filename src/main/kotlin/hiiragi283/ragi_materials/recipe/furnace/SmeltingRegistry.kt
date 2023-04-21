package hiiragi283.ragi_materials.recipe.furnace

import hiiragi283.ragi_materials.api.material.MaterialRegistry
import hiiragi283.ragi_materials.api.material.part.PartRegistry
import hiiragi283.ragi_materials.api.material.type.EnumMaterialType
import hiiragi283.ragi_materials.util.getPart

object SmeltingRegistry {

    fun load() {
        heatingMetal()
    }

    private fun heatingMetal() {
        for (pair in MaterialRegistry.validPair) {
            val part = pair.first
            val material = pair.second
            val type = part.type
            val scale = part.scale
            if (EnumMaterialType.METAL in material.type.list && scale >= 1.0f && type != EnumMaterialType.INGOT_HOT) {
                val output = getPart(PartRegistry.INGOT_HOT, material, part.scale.toInt())
                val input = getPart(part, material)
                SmeltingManager.addRecipe(output, input)
            }
        }
    }

}