package hiiragi283.ragi_materials.integration.jei.forge_furnace

import hiiragi283.ragi_materials.integration.jei.JEICore
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.material.type.EnumMaterialType
import hiiragi283.ragi_materials.material.type.TypeRegistry

import mezz.jei.api.IModRegistry

object FFMaker {

    fun register(registry: IModRegistry) {
        val list: MutableList<FFRecipe> = mutableListOf()
        for (material in MaterialRegistry.mapIndex.values) {
            if (material.type == TypeRegistry.METAL) {
                for (part in PartRegistry.map.values) {
                    val type = part.type
                    val scale = part.scale
                    //素材が部品に対して有効，かつ倍率が1.0f以上の場合
                    if (type in material.type.list && type != EnumMaterialType.INGOT_HOT && scale >= 1.0f) {
                        list.add(FFRecipe(part, material)) //リストにレシピを追加
                    }
                }
            }
        }
        registry.addRecipes(list, JEICore.ForgeFurnace)
    }
}