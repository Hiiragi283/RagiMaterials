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
            //鉱石がある場合
            if (material.hasOre) {
                //リストにレシピを追加
                list.add(Recipe(PartRegistry.ORE, material))
                list.add(Recipe(PartRegistry.ORE_NETHER, material))
                list.add(Recipe(PartRegistry.ORE_END, material))
            }
        }
        registry.addRecipes(list, JEICore.StoneMill)
    }

    class Recipe(val part: MaterialPart, val material: MaterialBuilder) {

        var input = MaterialUtil.getPart(part, material)
        var output = MaterialUtil.getPart(PartRegistry.DUST, material, part.scale.toInt())

    }
}