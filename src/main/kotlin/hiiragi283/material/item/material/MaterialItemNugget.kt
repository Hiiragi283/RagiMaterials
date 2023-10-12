package hiiragi283.material.item.material

import hiiragi283.material.api.item.MaterialItemNew
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.itemStack

object MaterialItemNugget : MaterialItemNew(HiiragiShapes.NUGGET) {

    override fun addRecipes(material: HiiragiMaterial) {
        // 1x Ingot -> 9x Nugget
        if (HiiragiShapes.INGOT.isValid(material)) {
            CraftingBuilder(itemStack(material, 9))
                .addIngredient(HiiragiShapes.INGOT.getOreDict(material))
                .build()
        }
        //Metal Former Recipe
        addMetalFormerRecipe(material, outputCount = 9)
    }

}