package hiiragi283.material.item.material

import hiiragi283.material.api.item.MaterialItem
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.init.HiiragiItems
import hiiragi283.material.init.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.itemStack

object MaterialItemDust : MaterialItem(HiiragiShapes.DUST) {

    override fun registerRecipe(material: HiiragiMaterial) {
        // 1x Ingot/Gem + 1x Smithing Hammer -> 1x Dust
        val builder: CraftingBuilder = CraftingBuilder(this.itemStack(material))
            .setPattern("A", "B")
            .setIngredient('A', HiiragiItems.SMITHING_HAMMER, true)
        if (material.isMetal()) {
            builder.setIngredient('B', HiiragiShapes.INGOT.getOreDict(material)).build()
        } else if (material.isGem()) {
            builder.setIngredient('B', HiiragiShapes.GEM.getOreDict(material)).build()
        }
    }

}