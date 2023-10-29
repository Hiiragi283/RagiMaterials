package hiiragi283.material.item.material

import hiiragi283.material.api.item.MaterialItem
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.init.HiiragiItems
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.itemStack

object MaterialItemStick : MaterialItem(HiiragiShapes.STICK) {

    override fun registerRecipe(material: HiiragiMaterial) {
        // 1x Ingot/Gem + 1x Smithing Hammer -> 2x Stick
        val builder: CraftingBuilder = CraftingBuilder(itemStack(material, 2))
            .setPattern("A", "B")
            .setIngredient('B', HiiragiItems.SMITHING_HAMMER, true)
        if (material.isMetal()) {
            builder.setIngredient('A', HiiragiShapes.INGOT.getOreDict(material)).build()
        } else if (material.isGem()) {
            builder.setIngredient('A', HiiragiShapes.GEM.getOreDict(material)).build()
        }
        //Metal Former Recipe
        addMetalFormerRecipe(material, outputCount = 2)
    }

}