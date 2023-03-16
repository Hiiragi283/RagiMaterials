package hiiragi283.ragi_materials.integration.jei.stone_mill

import hiiragi283.ragi_materials.integration.jei.JEICore
import hiiragi283.ragi_materials.material.MaterialRegistry
import hiiragi283.ragi_materials.material.part.PartRegistry
import hiiragi283.ragi_materials.material.type.TypeRegistry
import mezz.jei.api.IModRegistry

object StoneMillMaker {

    fun register(registry: IModRegistry) {
        val list: MutableList<StoneMillRecipe> = mutableListOf()
        for (material in MaterialRegistry.mapIndex.values) {
            if (material.type != TypeRegistry.DUST) {
                for (part in PartRegistry.map.values) {
                    val type = part.type
                    val scale = part.scale
                    //素材が部品に対して有効，かつ倍率が1.0f以上の場合
                    if (type in material.type.list && scale >= 1.0f) {
                        list.add(StoneMillRecipe(part, material)) //リストにレシピを追加
                    }
                }
                //鉱石がある場合
                if (material.hasOre) {
                    //リストにレシピを追加
                    list.add(StoneMillRecipe(PartRegistry.ORE, material))
                    list.add(StoneMillRecipe(PartRegistry.ORE_NETHER, material))
                    list.add(StoneMillRecipe(PartRegistry.ORE_END, material))
                }
            }
        }
        registry.addRecipes(list, JEICore.StoneMill)
    }
}