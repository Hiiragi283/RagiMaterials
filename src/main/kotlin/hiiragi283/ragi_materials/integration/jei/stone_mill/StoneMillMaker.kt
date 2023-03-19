package hiiragi283.ragi_materials.integration.jei.stone_mill

import hiiragi283.ragi_materials.integration.jei.JEICore
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.RagiMaterial
import hiiragi283.ragi_materials.material.part.MaterialPart
import hiiragi283.ragi_materials.material.part.PartRegistry
import mezz.jei.api.IModRegistry

object StoneMillMaker {

    fun register(registry: IModRegistry) {
        val list: MutableList<Recipe> = mutableListOf()
        for (pair in RagiMaterial.validPair) {
            val part = pair.first
            val material = pair.second
            if (part.scale >= 1.0f) list.add(Recipe(part, material)) //リストにレシピを追加
        }
        registry.addRecipes(list, JEICore.StoneMill)
    }

    class Recipe(val part: MaterialPart, val material: RagiMaterial) {

        var input = MaterialUtil.getPartNew(part, material)
        var output = MaterialUtil.getPartNew(PartRegistry.DUST, material, part.scale.toInt())

    }
}