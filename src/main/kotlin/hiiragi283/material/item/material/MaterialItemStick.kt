package hiiragi283.material.item.material

import hiiragi283.material.HiiragiItems
import hiiragi283.material.api.item.MaterialItemNew
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.itemStack

object MaterialItemStick : MaterialItemNew(HiiragiShapes.STICK) {

    override fun addRecipes(material: HiiragiMaterial) {
        // 1x Ingot + 1x Smithing Hammer -> 2x Stick
        if (HiiragiShapes.INGOT.isValid(material)) {
            CraftingBuilder(itemStack(material, 2))
                .setPattern("A", "B")
                .setIngredient('A', HiiragiShapes.INGOT.getOreDict(material))
                .setIngredient('B', HiiragiItems.SMITHING_HAMMER, true)
                .build()
        }
        //Metal Former Recipe
        addMetalFormerRecipe(material, outputCount = 2)
    }

}