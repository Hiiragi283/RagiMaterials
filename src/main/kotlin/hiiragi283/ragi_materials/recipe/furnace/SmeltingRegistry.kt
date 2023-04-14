package hiiragi283.ragi_materials.recipe.furnace

import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.RagiMaterial
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.material.type.EnumMaterialType

object SmeltingRegistry {

    fun load() {
        heatingMetal()
    }

    private fun heatingMetal() {
        for (pair in RagiMaterial.validPair) {
            val part = pair.first
            val material = pair.second
            val type = part.type
            val scale = part.scale
            if (EnumMaterialType.METAL in material.type.list && scale >= 1.0f && type != EnumMaterialType.INGOT_HOT) {
                val output = MaterialUtil.getPart(PartRegistry.INGOT_HOT, material, part.scale.toInt())
                val input = MaterialUtil.getPart(part, material)
                SmeltingManager.addRecipe(output, input)
            }
        }
    }

}