package hiiragi283.material.block

import hiiragi283.material.api.block.MaterialBlock
import hiiragi283.material.api.item.MaterialItemBlock
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.item.MaterialItemBlockCasing
import hiiragi283.material.util.CraftingBuilder

object MaterialBlockCasing : MaterialBlock(
    HiiragiShapes.CASING,
    recipe = { entry, material ->
        CraftingBuilder(entry.getItemStack(material))
            .setPattern("A A", "ABA", "A A")
            .setIngredient('A', HiiragiShapes.PLATE.getOreDict(material))
            .setIngredient('B', HiiragiShapes.FRAME.getOreDict(material))
            .build()
    }
) {

    override val itemBlock: MaterialItemBlock = MaterialItemBlockCasing(this)

}