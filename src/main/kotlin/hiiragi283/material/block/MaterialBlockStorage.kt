package hiiragi283.material.block

import hiiragi283.material.api.block.MaterialBlock
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.itemStack

object MaterialBlockStorage : MaterialBlock(
    HiiragiShapes.BLOCK,
    recipe = { block: MaterialBlock, material: HiiragiMaterial ->
        if (HiiragiShapes.INGOT.isValid(material)) {
            CraftingBuilder(block.itemStack(material))
                .setPattern("AAA", "AAA", "AAA")
                .setIngredient('A', HiiragiShapes.INGOT.getOreDict(material))
                .build()
        } else if (HiiragiShapes.GEM.isValid(material)) {
            CraftingBuilder(block.itemStack(material))
                .setPattern("AAA", "AAA", "AAA")
                .setIngredient('A', HiiragiShapes.GEM.getOreDict(material))
                .build()
        }
    }
)