package hiiragi283.material.item.material

import hiiragi283.material.api.item.MaterialItem
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.part.HiiragiPart
import hiiragi283.material.init.HiiragiItems
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder

object MaterialItemPlate : MaterialItem(HiiragiShapes.PLATE) {

    override fun registerRecipe(material: HiiragiMaterial) {
        // 1x Ingot + 1x Smithing Hammer -> 1x Plate
        val part: HiiragiPart = material.getPart(shape)
        if (!part.hasItemStack()) return
        CraftingBuilder(part.getItemStack())
                .setPattern("AB")
                .setIngredient('A', HiiragiShapes.INGOT.getOreDict(material))
                .setIngredient('B', HiiragiItems.SMITHING_HAMMER, true)
                .build()
        //Metal Former Recipe
        addMetalFormerRecipe(material)
        // Grinder Recipe
        addGrinderRecipe(material)
    }

}