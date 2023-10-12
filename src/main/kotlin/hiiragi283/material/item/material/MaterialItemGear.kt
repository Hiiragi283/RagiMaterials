package hiiragi283.material.item.material

import hiiragi283.material.HiiragiItems
import hiiragi283.material.api.item.MaterialItemNew
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.itemStack

object MaterialItemGear : MaterialItemNew(HiiragiShapes.GEAR) {

    override fun addRecipes(material: HiiragiMaterial) {
        // 4x Ingot + 1x Smithing Hammer -> 1x Gear
        if (HiiragiShapes.INGOT.isValid(material)) {
            CraftingBuilder(itemStack(material))
                .setPattern(" A ", "ABA", " A ")
                .setIngredient('A', HiiragiShapes.INGOT.getOreDict(material))
                .setIngredient('B', HiiragiItems.SMITHING_HAMMER, true)
                .build()
        }
        //Metal Former Recipe
        addMetalFormerRecipe(material, inputCount = 4)
        //Grinder Recipe
        addGrinderRecipe(material)
    }

}