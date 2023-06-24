package hiiragi283.material.common.item

import hiiragi283.material.api.item.MaterialPartItem
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.PartRegistry
import hiiragi283.material.common.RagiMaterials
import hiiragi283.material.common.util.addTag
import hiiragi283.material.common.util.append
import hiiragi283.material.common.util.get3x3
import net.devtech.arrp.json.recipe.JIngredients
import net.devtech.arrp.json.recipe.JKeys
import net.devtech.arrp.json.recipe.JRecipe

class MaterialIngotItem(material: HiiragiMaterial) : MaterialPartItem(material, PartRegistry.INGOT) {

    override fun getTexture(): String = "minecraft:item/iron_ingot"

    override fun registerRecipe() {
        //9x Nugget -> 1x Ingot
        RagiMaterials.RESOURCE_PACK.addRecipe(
            part.getId(material).append("_shaped"),
            JRecipe.shaped(
                get3x3('A'),
                JKeys.keys().addTag("A", PartRegistry.NUGGET.getTag(material).toString()),
                part.getResult(material)
            )
        )
        //1x Block -> 9x Ingot
        RagiMaterials.RESOURCE_PACK.addRecipe(
            part.getId(material).append("_shapeless"),
            JRecipe.shapeless(
                JIngredients.ingredients().addTag(PartRegistry.BLOCK.getTag(material).toString()),
                part.getResult(material, 9)
            )
        )
    }
}