package hiiragi283.material.api.block

import hiiragi283.material.api.item.MaterialItemBlock
import hiiragi283.material.api.item.MaterialItemBlockCasing
import hiiragi283.material.api.material.HiiragiMaterial
import hiiragi283.material.api.registry.HiiragiEntry
import hiiragi283.material.api.shape.HiiragiShapes
import hiiragi283.material.util.CraftingBuilder
import hiiragi283.material.util.setModelSame

object MaterialBlockCasing : MaterialBlock(HiiragiShapes.CASING) {

    override val itemBlock: MaterialItemBlock = MaterialItemBlockCasing(this)

    override fun getRecipe(entry: HiiragiEntry<*>, material: HiiragiMaterial) {
        CraftingBuilder(entry.getItemStack(material))
            .setPattern("A A", "ABA", "A A")
            .setIngredient('A', HiiragiShapes.PLATE.getOreDict(material))
            .setIngredient('B', HiiragiShapes.FRAME.getOreDict(material))
            .build()
    }

    override fun getModel(entry: HiiragiEntry<*>) {
        this.setModelSame()
    }

}