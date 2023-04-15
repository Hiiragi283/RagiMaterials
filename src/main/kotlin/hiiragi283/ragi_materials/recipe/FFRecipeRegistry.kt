package hiiragi283.ragi_materials.recipe

import hiiragi283.ragi_materials.RagiMaterials
import hiiragi283.ragi_materials.api.material.MaterialUtil
import hiiragi283.ragi_materials.api.material.RagiMaterial
import hiiragi283.ragi_materials.api.material.part.PartRegistry
import hiiragi283.ragi_materials.api.material.type.EnumMaterialType
import hiiragi283.ragi_materials.api.material.type.TypeRegistry
import hiiragi283.ragi_materials.api.recipe.FFRecipe

object FFRecipeRegistry {

    val set: MutableSet<FFRecipe> = mutableSetOf()

    fun load() {
        materialRecipe()
    }

    private fun materialRecipe() {
        for (pair in RagiMaterial.validPair) {
            val part = pair.first
            val material = pair.second
            val type = part.type
            val scale = part.scale
            if (material.type.match(TypeRegistry.METAL) && scale >= 1.0f && type != EnumMaterialType.INGOT_HOT) {
                val recipe = FFRecipe.Builder().apply {
                    input = MaterialUtil.getPart(part, material)
                    output = MaterialUtil.getPart(PartRegistry.INGOT_HOT, material, part.scale.toInt())
                }.build().setRegistryName(RagiMaterials.MOD_ID, "${part.name}_${material.name}")
                set.add(recipe)
            }
        }
    }
}