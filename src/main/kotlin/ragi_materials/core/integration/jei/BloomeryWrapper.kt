package ragi_materials.core.integration.jei

import mezz.jei.api.ingredients.IIngredients
import mezz.jei.api.ingredients.VanillaTypes
import mezz.jei.api.recipe.IRecipeWrapper
import ragi_materials.core.material.EnumSubMaterial
import ragi_materials.core.material.MaterialRegistry
import ragi_materials.core.material.RagiMaterial
import ragi_materials.core.material.part.PartRegistry
import ragi_materials.core.util.getPart

fun getListOre(): Set<RagiMaterial> {
    val set: MutableSet<RagiMaterial> = mutableSetOf()
    for (material in MaterialRegistry.list) {
        material.mapSubMaterials[EnumSubMaterial.NATIVE]?.let { set.add(material) }
    }
    return set
}

class BloomeryWrapper(val material: RagiMaterial) : IRecipeWrapper {

    fun getInput() = getPart(PartRegistry.ORE, material)

    fun getOutput() = getPart(PartRegistry.INGOT, material.mapSubMaterials[EnumSubMaterial.NATIVE]!!)

    override fun getIngredients(ingredients: IIngredients) {
        ingredients.setInputs(VanillaTypes.ITEM, mutableListOf(getInput()))
        ingredients.setOutputs(VanillaTypes.ITEM, mutableListOf(getOutput()))
    }
}