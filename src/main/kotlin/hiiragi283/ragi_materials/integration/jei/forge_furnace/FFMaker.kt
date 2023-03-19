package hiiragi283.ragi_materials.integration.jei.forge_furnace

import hiiragi283.ragi_materials.integration.jei.JEICore
import hiiragi283.ragi_materials.material.MaterialUtil
import hiiragi283.ragi_materials.material.RagiMaterial
import hiiragi283.ragi_materials.material.part.MaterialPart
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import hiiragi283.ragi_materials.material.type.TypeRegistry
import hiiragi283.ragi_materials.tile.TileForgeFurnace
import mezz.jei.api.IModRegistry

object FFMaker {

    fun register(registry: IModRegistry) {
        val list: MutableList<Recipe> = mutableListOf()
        for (pair in RagiMaterial.validPair) {
            val part = pair.first
            val material = pair.second
            val type = part.type
            val scale = part.scale
            if (material.type == TypeRegistry.METAL && scale >= 1.0f && type != EnumMaterialType.INGOT_HOT) {
                list.add(Recipe(part, pair.second)) //リストにレシピを追加
            }
        }
        registry.addRecipes(list, JEICore.ForgeFurnace)
    }

    class Recipe(val part: MaterialPart, val material: RagiMaterial) {

        var input = MaterialUtil.getPartNew(part, material)
        var output = MaterialUtil.getPartNew(PartRegistry.INGOT_HOT, material, part.scale.toInt())
        var fuel = TileForgeFurnace.getFuelConsumption(input)

    }
}