package hiiragi283.material.item.material

import hiiragi283.material.api.item.MaterialItem
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.init.HiiragiItems
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.itemStack

object MaterialItemPlate : MaterialItem(HiiragiShapes.PLATE) {

    override fun registerRecipe(material: HiiragiMaterial) {
        // 1x Ingot + 1x Smithing Hammer -> 1x Plate
        if (material.isMetal()) {
            CraftingBuilder(itemStack(material))
                .setPattern("AB")
                .setIngredient('A', HiiragiShapes.INGOT.getOreDict(material))
                .setIngredient('B', HiiragiItems.SMITHING_HAMMER, true)
                .build()
        }
        //Metal Former Recipe
        addMetalFormerRecipe(material)
    }

}