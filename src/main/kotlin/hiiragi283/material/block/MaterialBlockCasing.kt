package hiiragi283.material.block

import hiiragi283.material.HiiragiItems
import hiiragi283.material.api.block.MaterialBlock
import hiiragi283.material.api.item.MaterialItemBlock
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.item.MaterialItemBlockCasing
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.itemStack

object MaterialBlockCasing : MaterialBlock(HiiragiShapes.CASING) {

    override val itemBlock: MaterialItemBlock = MaterialItemBlockCasing(this)

    override fun registerRecipe(material: HiiragiMaterial) {
        if (!HiiragiShapes.PLATE.isValid(material)) return
        CraftingBuilder(this.itemStack(material))
            .setPattern("AAA", "ABA", "AAA")
            .setIngredient('A', HiiragiShapes.PLATE.getOreDict(material))
            .setIngredient('B', HiiragiItems.SMITHING_HAMMER, true)
            .build()
    }

}