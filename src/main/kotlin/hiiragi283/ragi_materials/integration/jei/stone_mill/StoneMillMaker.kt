package hiiragi283.ragi_materials.integration.jei.stone_mill

import hiiragi283.ragi_materials.integration.jei.JEICore
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.builder.MaterialBuilder
import hiiragi283.ragi_materials.material.part.MaterialPart
import hiiragi283.ragi_materials.material.part.PartRegistry
import mezz.jei.api.IModRegistry

object StoneMillMaker {

    fun register(registry: IModRegistry) {
        val list: MutableList<Recipe> = mutableListOf()
        for (pair in MaterialRegistry.validPair) {
            val part = pair.first
            val material = pair.second
            if (part.scale >= 1.0f) list.add(Recipe(part, material)) //リストにレシピを追加
        }
        registry.addRecipes(list, JEICore.StoneMill)
    }

    class Recipe(val part: MaterialPart, val material: MaterialBuilder) {

        var input = MaterialUtil.getPart(part, material)
        var output = MaterialUtil.getPart(PartRegistry.DUST, material, part.scale.toInt())

    }
}