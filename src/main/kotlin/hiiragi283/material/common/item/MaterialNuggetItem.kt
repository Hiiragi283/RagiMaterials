package hiiragi283.material.common.item

import hiiragi283.material.api.item.MaterialPartItem
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.PartRegistry
import hiiragi283.material.common.RagiMaterials
import hiiragi283.material.common.util.addTag
import hiiragi283.material.common.util.append
import net.devtech.arrp.json.recipe.JIngredients
import net.devtech.arrp.json.recipe.JRecipe

class MaterialNuggetItem(material: HiiragiMaterial) : MaterialPartItem(material, PartRegistry.NUGGET) {

    override fun getTexture(): String = "minecraft:item/iron_nugget"

    override fun registerRecipe() {
        //1x Ingot -> 9x Nugget
        RagiMaterials.RESOURCE_PACK.addRecipe(
            part.getId(material).append("_shapeless"),
            JRecipe.shapeless(
                JIngredients.ingredients().addTag(PartRegistry.INGOT.getTag(material).toString()),
                part.getResult(material, 9)
            )
        )
    }
}