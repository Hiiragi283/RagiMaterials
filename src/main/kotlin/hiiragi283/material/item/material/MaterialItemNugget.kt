package hiiragi283.material.item.material

import hiiragi283.material.api.item.MaterialItem
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.itemStack

object MaterialItemNugget : MaterialItem(HiiragiShapes.NUGGET) {

    override fun registerRecipe(material: HiiragiMaterial) {
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