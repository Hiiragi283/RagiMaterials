package hiiragi283.ragi_materials.integration.jei.forge_furnace

import hiiragi283.ragi_materials.integration.jei.JEICore
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.builder.MaterialBuilder
import hiiragi283.ragi_materials.material.part.MaterialPart
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import hiiragi283.ragi_materials.material.type.TypeRegistry
import hiiragi283.ragi_materials.tile.TileForgeFurnace
import mezz.jei.api.IModRegistry

object FFMaker {

    fun register(registry: IModRegistry) {
        val list: MutableList<Recipe> = mutableListOf()
        for (pair in MaterialRegistry.validPair) {
            val part = pair.first
            val material = pair.second
            val type = part.type
            val scale = part.scale
            if (material.type == TypeRegistry.METAL && type != EnumMaterialType.INGOT_HOT && scale >= 1.0f) list.add(Recipe(part, pair.second)) //リストにレシピを追加
            //鉱石がある場合
            if (material.hasOre) {
                //リストにレシピを追加
                list.add(Recipe(PartRegistry.ORE, material))
                list.add(Recipe(PartRegistry.ORE_NETHER, material))
                list.add(Recipe(PartRegistry.ORE_END, material))
            }
        }
        registry.addRecipes(list, JEICore.ForgeFurnace)
    }

    class Recipe(val part: MaterialPart, val material: MaterialBuilder) {

        var input = MaterialUtil.getPart(part, material)
        var output = MaterialUtil.getPart(PartRegistry.INGOT_HOT, material, part.scale.toInt())
        var fuel = TileForgeFurnace.getFuelConsumption(input)

    }
}